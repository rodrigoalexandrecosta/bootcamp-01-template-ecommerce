package br.com.zup.bootcamp.fleamarketapi.features.account;

import br.com.zup.bootcamp.fleamarketapi.model.Account;
import br.com.zup.bootcamp.fleamarketapi.model.request.CreateAccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/accounts", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountRepository accountRepository;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid CreateAccountRequest request) {
        Account account = this.accountRepository.save(request.toAccount());
        return ResponseEntity.created(URI.create(String.format("/api/v1/accounts/%s", account.getId()))).build();
    }

}