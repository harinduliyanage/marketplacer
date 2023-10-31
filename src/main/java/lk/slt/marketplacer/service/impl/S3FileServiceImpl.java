package lk.slt.marketplacer.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import lk.slt.marketplacer.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;


@Service
@Slf4j
public class S3FileServiceImpl implements FileService {
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    /**
     * Create a presigned URL for uploading with a PUT request.
     *
     * @param fileName - The name of the object.
     * @return - The presigned URL for an HTTP PUT.
     */
    public URL createUploadUrl(String fileName) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10);
        //
        URL presignedUrl = amazonS3.generatePresignedUrl(bucketName, fileName, calendar.getTime(), HttpMethod.PUT);
        log.info("Presigned URL to upload a file to: [{}]", presignedUrl.toString());
        //
        return  presignedUrl;
    }

    /**
     * Create a presigned URL for downloading with a GET request.
     *
     * @param fileName - The name of the object.
     * @return - The presigned URL for an HTTP GET.
     */
    public URL createDownloadUrl(String fileName) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10);
        //
        URL presignedUrl = amazonS3.generatePresignedUrl(bucketName, fileName, calendar.getTime(), HttpMethod.GET);
        log.info("Presigned URL to download a file to: [{}]", presignedUrl.toString());
        //
        return  presignedUrl;
    }
}
