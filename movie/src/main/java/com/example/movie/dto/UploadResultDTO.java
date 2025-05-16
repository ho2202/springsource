package com.example.movie.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UploadResultDTO {
    private String fileName;
    private String uuid;
    private String folderPath;

    public String getImageURL() {
        String fullPath = "";
        try {
            fullPath = URLEncoder.encode(folderPath + "" + uuid + "_" + fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fullPath;
    }

    public String getThumbnailURL() {
        String fullPath = "";
        try {
            fullPath = URLEncoder.encode(folderPath + "/s_" + uuid + "_" + fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fullPath;
    }
}
