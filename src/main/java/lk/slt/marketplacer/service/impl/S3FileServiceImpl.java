package lk.slt.marketplacer.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jakarta.annotation.PostConstruct;
import lk.slt.marketplacer.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;


@Service
@Slf4j
public class S3FileServiceImpl implements FileService {
    private AmazonS3 amazonS3;
    //
    @Value("${aws.accessKey}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secretKey;
    @Value("${aws.region}")
    private String region;
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Value("${aws.s3.preSignUrlValidTime}")
    private Integer validTimeAmount;
    //
    @PostConstruct
    private void init() {
        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    /**
     * Create a pre-signed URL for uploading with a PUT request.
     *
     * @param filePath - The name of the object.
     * @return - The pre-signed URL for an HTTP PUT.
     */
    public URL createUploadUrl(String filePath) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, validTimeAmount);
        //
        URL preSignedUrl = amazonS3.generatePresignedUrl(bucketName, filePath, calendar.getTime(), HttpMethod.PUT);
        log.info("Pre-signed URL to upload a file to: [{}]", preSignedUrl.toString());
        //
        return preSignedUrl;
    }

    /**
     * Create a pre-signed URL for downloading with a GET request.
     *
     * @param filePath - The name of the object.
     * @return - The pre-signed URL for an HTTP GET.
     */
    public URL createDownloadUrl(String filePath) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 72);
        //
        URL preSignedUrl = amazonS3.generatePresignedUrl(bucketName, filePath, calendar.getTime(), HttpMethod.GET);
        log.info("Pre signed URL to download a file to: [{}]", preSignedUrl.toString());
        //
        return preSignedUrl;
    }
}
