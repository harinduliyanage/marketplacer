package lk.slt.marketplacer.dto;

import lombok.Data;

import java.net.URL;

@Data
public class FileDto {
    private URL url;
    private String key;
    private String fileName;
    private String xAmzAlgorithm;
    private String xAmzCredential;
    private String xAmzDate;
    private String xAmzSignature;
    private String xAmzExpires;
}
