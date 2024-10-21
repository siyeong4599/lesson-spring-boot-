package kr.hs.dge.dgsw.ex1.controller;

import kr.hs.dge.dgsw.ex1.dto.UploadResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UploadController {
    @Value("${project.upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<List<UploadResultDTO>>
    uploadFile(
            @RequestParam("uploadFiles")
            List<MultipartFile> uploadFiles
            ) {
        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            // image/jpg, image/jpeg
            if (!uploadFile.getContentType().startsWith("image")) {
                log.warn("This file is not image type");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            String originalName = uploadFile.getOriginalFilename();
            // app.png
            // IE, Edge : C:\\upload\\app.png
            String fileName = originalName.substring(
                    originalName.lastIndexOf("\\") + 1
            );
            log.info("fileName : {}", fileName);

            String folderPath = makeFolder();
            log.info("folderPath : {}", folderPath);

            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath + File.separator
                    + folderPath + File.separator + uuid + "_" + fileName;
            log.info("saveName : {}", saveName);
            Path path = Paths.get(saveName);
            try {
                uploadFile.transferTo(path);
                String thumbnailSaveName
                        = uploadPath
                            + File.separator
                        + folderPath
                            + File.separator
                        + "s_" + uuid + "_" + fileName;

                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(path.toFile(),
                        thumbnailFile, 200, 200);

                resultDTOList.add(
                        new UploadResultDTO(fileName, uuid, folderPath)
                );
            } catch (IOException e) {

            }
        }
        return ResponseEntity.ok(
                resultDTOList
        );
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(
            @RequestParam("fileName") String fileName
    ){
        ResponseEntity<byte[]> result = null;

        try {
            String srtFileName = URLDecoder.decode(fileName, "UTF-8");

            log.info("fileName : {}", srtFileName);

            File file = new File(
                    uploadPath
                            + File.separator
                            + srtFileName
            );

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));

            result = new ResponseEntity<>(
                    FileCopyUtils.copyToByteArray(file),
                    headers,
                    HttpStatus.OK
            );

        } catch (Exception e) {

        }

        return result;
    }

    private String makeFolder(){
    // str = "2024/05/23"
    String str = LocalDate.now().format(
            DateTimeFormatter.ofPattern("yyyy/MM/dd")
    );
    // str = "2024/05/23"
    String folderPath = str.replace("//", File.separator);
    File uploadPathFolder = new File(uploadPath, folderPath);
       if (!uploadPathFolder.exists()) {
        uploadPathFolder.mkdirs();
    }
       return folderPath;
}
    @PostMapping("/removeFile")
    public ResponseEntity removeFile(
            @RequestParam("fileName") String fileName){
        String srcFileName = null;
        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");

            File file = new File(uploadPath
                    + File.separator + srcFileName);
            boolean result = file.delete();
            //  "s_"
            // /Users/kimwongi/Desktop/upload/2024/06/05
            File thumbnailFile = new File(file.getParent(), "s_" + file.getName());
            result = thumbnailFile.delete();

            return ResponseEntity.ok(result);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }


    }
