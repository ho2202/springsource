package com.example.movie.controller;

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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.movie.dto.UploadResultDTO;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Value("${com.example.movie.upload.path}")
    private String uploadPath;

    @GetMapping("/create")
    public String getUploadForm() {
        return "/upload/test";
    }

    @PostMapping("/files")
    public ResponseEntity<List<UploadResultDTO>> postUpload(MultipartFile[] uploadFiles) {

        List<UploadResultDTO> uploadResultDTOs = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            // String oriName = uploadFile.getOriginalFilename();
            // String fileName = oriName.substring(oriName.lastIndexOf("\\") + 1);
            // log.info("oriName {}", oriName);
            // log.info("fileName {}", fileName);
            // 이미지가 아니면 돌려보냄
            if (!uploadFile.getContentType().startsWith("image")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String oriName = uploadFile.getOriginalFilename();
            String saveFolderPath = makeFolder();
            // UUID 고유 식별자 생성
            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath + File.separator + saveFolderPath + File.separator + uuid + "_" + oriName;
            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);
                // 썸네일 저장
                String thumbnailSavedName = uploadPath + File.separator + saveFolderPath + File.separator + "s_" + uuid
                        + "_" + oriName;
                File thumbFile = new File(thumbnailSavedName);
                Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 100, 100);
            } catch (IOException e) {
                e.printStackTrace();
            }
            uploadResultDTOs.add(new UploadResultDTO(oriName, uuid, saveFolderPath));
        }
        return new ResponseEntity<>(uploadResultDTOs, HttpStatus.OK);
    }

    @PostMapping("/removeFile")
    public ResponseEntity<String> postRemove(String fileName) {
        // TODO: process POST request
        log.info("파일 삭제 요청 {}", fileName);
        String oriFileName;
        try {
            oriFileName = URLDecoder.decode(fileName, "utf-8");

            File file = new File(uploadPath + File.separator + oriFileName);
            file.delete();

            File thum = new File(file.getParent(), "s_" + file.getName());
            thum.delete();
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName, String size) {
        ResponseEntity<byte[]> result = null;
        try {
            String srcFileName = URLDecoder.decode(fileName, "utf-8");
            File file = new File(uploadPath + File.separator + srcFileName);

            if (size != null && size.equals("1")) {
                file = new File(file.getParent(), file.getName().substring(2));
            }
            HttpHeaders headers = new HttpHeaders();
            // "Content-Type"파일 타입 알려주기
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    private String makeFolder() {
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        // OS별 폴더 구분 기호로 변경
        String folderPath = dateStr.replace("/", File.separator);
        File uploadPathFolder = new File(uploadPath, folderPath);
        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs(); // 디렉토리 생성
        }
        return folderPath;
    }

}