//package ucb.validador.backend.s3;
//
//import java.io.IOException;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//
//@Service
//public class FileService {
//    private AmazonS3 amazonS3;
//
//    @Autowired
//    public FileService(AmazonS3 amazonS3) {
//        this.amazonS3 = amazonS3;
//    }
//
//    @Value("${file.bucketname}")
//    private String bucketName;
//
//    public String uploadFile(MultipartFile file) {
//        String fileName = String.format("%s", UUID.randomUUID()).replace("-", "");
//
//        try {
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentLength(file.getSize());
//            metadata.setContentType(file.getContentType());
//            amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
//            return "https://validadorpartidos.s3.amazonaws.com/" + fileName;
//        } catch (IOException ioe) {
//            return "IOException error uploaded: " + fileName;
//        } catch (AmazonServiceException serviceException) {
//            return "AmazonServiceException error uploaded: " + fileName;
//        } catch (AmazonClientException clientException) {
//            return "AmazonClientException error uploaded: " + fileName;
//        }
//    }
//}
