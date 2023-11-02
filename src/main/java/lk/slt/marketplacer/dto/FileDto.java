package lk.slt.marketplacer.dto;

import lombok.Data;

import java.net.URL;

@Data
public class FileDto {
    private URL url;
    private String key;
    private String XAmzAlgorithm;
    private String XAmzCredential;
    private String XAmzDate;
    private String XAmzSignature;
}
