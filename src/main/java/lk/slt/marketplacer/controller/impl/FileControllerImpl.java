package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.FileController;
import lk.slt.marketplacer.dto.CreateFileDto;
import lk.slt.marketplacer.dto.FileDto;
import lk.slt.marketplacer.service.FileService;
import lk.slt.marketplacer.util.FolderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class FileControllerImpl implements FileController {
    @Autowired
    FileService fileService;

    @Override
    public FileDto getUploadUrl(CreateFileDto createFileDto) {
        FolderType fileType = createFileDto.getType();
        UUID uuid = UUID.randomUUID();
        StringBuilder filePathBuilder = new StringBuilder();
        //
        switch (fileType){
            case PROFILE_PICTURE :
                filePathBuilder.append("users/").append(createFileDto.getUserId()).append("/profile-picture/");
                break;
            case STORE_LOGO :
                filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/asset/");
                break;
            case PRODUCT_IMAGE :
                filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/products/").append(createFileDto.getProductId()).append("/images/");
                break;
            case PRODUCT_VIDEO :
                filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/products/").append(createFileDto.getProductId()).append("/videos/");
                break;
            case BUSINESS_REGISTRATION:
                filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/documents/business-registrations/");
                break;
            case CATEGORY_IMAGE:
                filePathBuilder.append("category/").append(createFileDto.getCategoryId()).append("/images/");
                break;
            case BANNER_IMAGE:
                filePathBuilder.append("banner/").append(createFileDto.getBannerId()).append("/images/");
                break;
        }
//        if (fileType == FolderType.PROFILE_PICTURE) {
//            filePathBuilder.append("users/").append(createFileDto.getUserId()).append("/profile-picture/");
//        } else if (fileType == FolderType.STORE_LOGO) {
//            filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/asset/");
//        } else if (fileType == FolderType.PRODUCT_IMAGE) {
//            filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/products/").append(createFileDto.getProductId()).append("/images/");
//        } else if (fileType == FolderType.PRODUCT_VIDEO) {
//            filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/products/").append(createFileDto.getProductId()).append("/videos/");
//        } else {
//            filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/documents/business-registrations/");
//        }
        //
        String fileName = String.format("%s.%s", uuid, createFileDto.getExtension().toString().toLowerCase());
        filePathBuilder.append(fileName);
        String filePath = filePathBuilder.toString();
        URL url = fileService.createUploadUrl(filePath);
        //
        Map<String, String> query = splitQuery(url);
        //
        FileDto fileDto = new FileDto();
        fileDto.setKey(uuid.toString());
        fileDto.setUrl(url);
        fileDto.setXAmzAlgorithm(query.get("X-Amz-Algorithm"));
        fileDto.setXAmzSignature(query.get("X-Amz-Signature"));
        fileDto.setXAmzDate(query.get("X-Amz-Date"));
        fileDto.setXAmzCredential(query.get("X-Amz-Credential"));
        fileDto.setXAmzExpires(query.get("X-Amz-Expires"));
        fileDto.setFileName(fileName);
        //
        return fileDto;
    }

    @Override
    public FileDto getDownloadUrl() {
        return null;
    }

    private static Map<String, String> splitQuery(URL url) {
        Map<String, String> queryPairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8), URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
        }
        return queryPairs;
    }
}
