package toyproject.juniorforum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import toyproject.juniorforum.domain.UploadFile;
import toyproject.juniorforum.service.ImageService;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/image")
public class UploadController {
    private final ImageService imageService;

    @PostMapping("")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        UploadFile uploadFile = imageService.storeFile(multipartFile);
        log.info("----------- image upload -----------");
        return ResponseEntity.ok().body("/image/" + uploadFile.getStoreFileName());
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> imageCall(@PathVariable String fileName) throws MalformedURLException {
        Resource resource = new UrlResource("file:" + imageService.getFullPath(fileName));

        log.info("---------- image call -------------");
        return ResponseEntity.ok().body(resource);
    }

}
