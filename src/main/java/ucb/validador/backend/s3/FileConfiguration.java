//package ucb.validador.backend.s3;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//
//@Configuration
//public class FileConfiguration {
//    @Value("${file.id}")
//    private String id;
//
//    @Value("${file.key}")
//    private String key;
//
//    @Value("${file.region}")
//    private String region;
//
//    @Bean
//    public AmazonS3 s3client() {
//
//        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(id, key);
//        AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .build();
//
//        return amazonS3Client;
//    }
//}
