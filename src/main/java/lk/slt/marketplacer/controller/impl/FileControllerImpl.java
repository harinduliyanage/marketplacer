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
        UUID uuid = UUID.randomUUID();
        String filePath = "";
        if (createFileDto.getType() == FolderType.PROFILE_PICTURE) {
            filePath += "users/" + createFileDto.getUserId() + "/profile-picture/" + uuid + "." + createFileDto.getExtension().toString().toLowerCase();
        } else if (createFileDto.getType() == FolderType.STORE_LOGO) {
            filePath += "stores/" + createFileDto.getStoreId() + "/asset/" + uuid + "." + createFileDto.getExtension().toString().toLowerCase();
        } else if (createFileDto.getType() == FolderType.PRODUCT_IMAGE) {
            filePath += "stores/" + createFileDto.getStoreId() + "/products/" + createFileDto.getProductId() + "/images/" + uuid + "." + createFileDto.getExtension().toString().toLowerCase();
        } else if (createFileDto.getType() == FolderType.PRODUCT_VIDEO) {
            filePath += "stores/" + createFileDto.getStoreId() + "/products/" + createFileDto.getProductId() + "/videos/" + uuid + "." + createFileDto.getExtension().toString().toLowerCase();
        } else {
            filePath += "stores/" + createFileDto.getStoreId() + "/documents/business-registrations/" + uuid + "." + createFileDto.getExtension().toString().toLowerCase();
        }
        //
        System.out.println(filePath);
        URL url = s3FileService.createUploadUrl(filePath);
        FileDto fileDto = new FileDto();
        fileDto.setUrl(url);
        //
        return fileDto;
    }

    @Override
    public FileDto getDownloadUrl() {
        return null;
    }
}
