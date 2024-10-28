package dev.wilsondaniels.mydishes.infrasctructure.catalogemit.bucket;

import dev.wilsondaniels.mydishes.domain.catalogemit.BucketGateway;
import org.springframework.stereotype.Service;

@Service
public class BucketMinioGateway implements BucketGateway {
    @Override
    public void upload(String anOwnerId, String catalog) {
        System.err.println("uploaded....\nowner: " + anOwnerId + "\n" + catalog);
    }
}
