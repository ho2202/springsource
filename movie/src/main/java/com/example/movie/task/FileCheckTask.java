package com.example.movie.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.movie.dto.MovieImageDTO;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class FileCheckTask {
    @Autowired
    private MovieImageRepository movieImageRepository;
    @Value("${com.example.movie.upload.path}")
    private String uploadPath;

    private String getFolderYesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String str = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return str.replace("-", File.separator);
    }

    // cron : unix 계열에서 사용하는 작업 스케쥴러
    @Scheduled(cron = "0 0 3 * * *")
    public void chekcFile() {
        log.info("file Check Task...");

        List<MovieImage> oldImages = movieImageRepository.getOldImages();
        List<MovieImageDTO> movieImageDTOs = oldImages.stream().map(img -> {
            return MovieImageDTO.builder()
                    .inum(img.getInum())
                    .uuid(img.getUuid())
                    .imgName(img.getImgName())
                    .path(img.getPath())
                    .build();
        }).collect(Collectors.toList());

        // 폴더 속 파일명과 일치하도록 DB 내용 변경
        List<Path> fileListPaths = movieImageDTOs.stream().map(dto -> Paths.get(uploadPath, dto.getImageURL(),
                dto.getUuid() + "_" + dto.getImgName()))
                .collect(Collectors.toList());

        movieImageDTOs.stream().map(dto -> Paths.get(uploadPath, dto.getImageURL(),
                "s_" + dto.getUuid() + "_" + dto.getImgName()))
                .forEach(p -> fileListPaths.add(p));

        // 어제 이미지 저장한 폴더
        File targetDir = Paths.get(uploadPath, getFolderYesterday()).toFile();
        File[] removeFiles = targetDir.listFiles(f -> fileListPaths.contains(f.toPath()) == false);

        if (removeFiles != null) {
            for (File file : removeFiles) {
                log.info("이미지 삭제" + file.getAbsolutePath());
                file.delete();
            }
        }
    }
}
