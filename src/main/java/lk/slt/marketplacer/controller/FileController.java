package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lk.slt.marketplacer.dto.CreateFileDto;
import lk.slt.marketplacer.dto.FileDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "file-services")
@RequestMapping("/api/v1/file")
public interface FileController {

    @GetMapping(value = "/upload", produces = {"application/json"})
    public FileDto getUploadUrl(@ParameterObject CreateFileDto createFileDto);
    @GetMapping(value = "/download", produces = {"application/json"})
    public FileDto getDownloadUrl();
}
