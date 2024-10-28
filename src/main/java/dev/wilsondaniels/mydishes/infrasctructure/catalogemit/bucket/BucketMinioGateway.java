package dev.wilsondaniels.mydishes.infrasctructure.catalogemit.bucket;

import dev.wilsondaniels.mydishes.domain.catalogemit.BucketGateway;
import dev.wilsondaniels.mydishes.domain.exception.DomainException;
import dev.wilsondaniels.mydishes.domain.validation.Error;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;


@Service
public class BucketMinioGateway implements BucketGateway {

    private final MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    public BucketMinioGateway(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void upload(String anOwnerId, String catalog) {
        try {
            uploadFileFromString(catalog, anOwnerId + "-catalog.json");
        } catch (Exception e) {
            throw DomainException.with(new Error("Error sending file to server."));
        }
    }


    private void uploadFileFromString(String content, String fileName) throws Exception {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(inputStream, inputStream.available(), -1)
                        .contentType("application/json")
                        .build()
        );
    }
}
