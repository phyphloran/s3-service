package s3_service.Configs;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import java.net.URI;


@Configuration
public class S3Config {

    @Value("${s3.ip}")
    private String ip;

    @Value("${s3.access-key}")
    private String accessKey;

    @Value("${s3.secret-key}")
    private String secretKey;

    @Value("${s3.secure}")
    private boolean secure;

    @Value("${s3.port}")
    private int port;

    @Bean
    public S3Client s3Client() {
        String protocol = secure ? "https" : "http";
        URI uri = URI.create(protocol + "://" + ip + ":" + port);
        return S3Client.builder()
            .endpointOverride(uri)
            .region(Region.US_EAST_1)
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(accessKey, secretKey)
                )
            )
            .build();
    }

}
