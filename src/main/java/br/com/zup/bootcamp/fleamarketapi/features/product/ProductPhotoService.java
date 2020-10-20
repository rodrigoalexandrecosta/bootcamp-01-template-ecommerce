package br.com.zup.bootcamp.fleamarketapi.features.product;

import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductPhoto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
/* Persisting file in the filesystem, at project root, inside unique product directory:
 * .sample/product-photos/{accountId}/{productId}
*/
public class ProductPhotoService {

    private final ProductPhotoRepository productPhotoRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ProductPhoto create(final UUID productId, final UUID accountId, final MultipartFile photo) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("message.product.not-found"));

        String photoLocation = this.persistProductPhoto(accountId, productId, photo);
        return this.productPhotoRepository.save(new ProductPhoto(photoLocation, product));
    }

    // Persists a product photo in the filesystem at ${project.basedir}/.sample/product-photos/{accountId}/{productId}
    private String persistProductPhoto(final UUID accountId, final UUID productId, final MultipartFile multipartPhoto) {
        try {
            final String productDirectory = this.getProductDirectory(accountId, productId);
            final String filename = this.generateFilename(multipartPhoto.getName());

            File photoFile = new File(String.format("%s/%s", productDirectory, filename));
            final byte[] photoBytes = multipartPhoto.getBytes();
            return Files.write(photoFile.toPath(), photoBytes).toString();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException("message.product.photo.persistence.error", e);
        }
    }

    private String getProductDirectory(final UUID accountId, final UUID productId) {
        final String directory = String.format("%s/%s/%s/%s", ".sample", "product-photos", accountId, productId);
        new File(directory).mkdirs(); // Creates account dir if it does not exist.
        return directory;
    }

    private String generateFilename(final String multipartPhotoName) {
        return String.format("%s%s",
                UUID.randomUUID().toString().replace("-", ""),
                this.getFileExtension(multipartPhotoName));
    }

    private String getFileExtension(final String multipartPhotoName) {
        return Optional.ofNullable(multipartPhotoName)
                .filter(photoName -> photoName.contains("."))
                .map(photoName -> photoName.substring(multipartPhotoName.lastIndexOf(".")))
                .orElse(".png");
    }

}
