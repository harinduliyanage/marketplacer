package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.FileController;
import lk.slt.marketplacer.dto.CreateFileDto;
import lk.slt.marketplacer.dto.FileDto;
import lk.slt.marketplacer.service.impl.S3FileServiceImpl;
import lk.slt.marketplacer.util.FolderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.UUID;

@RestController
public class FileControllerImpl implements FileController {
    @Autowired
    S3FileServiceImpl s3FileService;

    @Override
    public FileDto getUploadUrl(CreateFileDto createFileDto) {
        FolderType fileType = createFileDto.getType();
        UUID uuid = UUID.randomUUID();
        StringBuilder filePathBuilder = new StringBuilder();
        //
        if (fileType == FolderType.PROFILE_PICTURE) {
            filePathBuilder.append("users/").append(createFileDto.getUserId()).append("/profile-picture/");

        } else if (fileType == FolderType.STORE_LOGO) {
            filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/asset/");
        } else if (fileType == FolderType.PRODUCT_IMAGE) {
            filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/products/").append(createFileDto.getProductId()).append("/images/");
        } else if (fileType == FolderType.PRODUCT_VIDEO) {
            filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/products/").append(createFileDto.getProductId()).append("/videos/");
        } else {
            filePathBuilder.append("store/").append(createFileDto.getStoreId()).append("/documents/business-registrations/");
        }
        //
        filePathBuilder.append(uuid).append(".").append(createFileDto.getExtension().toString().toLowerCase());
        String filePath = filePathBuilder.toString();
        URL url = s3FileService.createUploadUrl(filePath);
        //
        FileDto fileDto = new FileDto();
        fileDto.setKey(uuid.toString());
        fileDto.setUrl(url);
        //
        return fileDto;
    }

    @Override
    public FileDto getDownloadUrl() {
        return null;
    }
}
