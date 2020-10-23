package br.com.zup.bootcamp.fleamarketapi.features.account.authentication;

import br.com.zup.bootcamp.fleamarketapi.model.response.AccountResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(HEADER_STRING);

        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            UsernamePasswordAuthenticationToken authentication = this.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.warn("Token: {}, message: {}", token, e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        return Optional.of(token)
                .map(this::decodeToken)
                .orElse(null);
    }

    private UsernamePasswordAuthenticationToken decodeToken(String token) {
        try {
            final DecodedJWT decoded = JWT.require(Algorithm.HMAC256("12345"))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));

            final String email = decoded.getClaim("email").asString();
            final AccountResponse accountResponse = new AccountResponse(email);

            // Needed here only for UsernamePasswordAuthenticationToken works correctly,
            // otherwise always returns false;
            List<SimpleGrantedAuthority> authorities = Optional.of("ALL")
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(accountResponse, accountResponse.getEmail(), authorities);

        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage());
        }
    }

}
