package ucb.validador.backend.service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ucb.validador.backend.s3.MinioConfig;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;


@Service
public class FileService {

    @Autowired
    private MinioClient minioClient;

    public String uploadFile(MultipartFile file)
    {
        try {

            minioClient.putObject(
                    io.minio.PutObjectArgs.builder()
                            .bucket("validadorpartidos")
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build()
            );
            // consigue el etag del archivo que se acaba de subir
            String etag = minioClient.statObject(
                    io.minio.StatObjectArgs.builder()
                            .bucket("validadorpartidos")
                            .object(file.getOriginalFilename())
                            .build()
            ).etag();

            return etag;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error uploading file: " + e.getMessage();
        }
    }

    public String getDownloadUrl(String etag)
    {
        try {

            Iterable<Result<Item>> myObjects = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket("validadorpartidos")
                            .build()
            );

            for (Result<Item> result : myObjects) {
                Item item = result.get();
                String etag1 = item.etag().substring(1, item.etag().length() - 1);

                if (etag1.equals(etag)) {
                    return minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket("validadorpartidos")
                                    .object(item.objectName())
                                    .expiry(1, TimeUnit.HOURS)
                                    .build());
                }
            }
            return "No se encontro el archivo";
        } catch (MinioException e) {
            e.printStackTrace();
            return "Error getting file URL: " + e.getMessage();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
