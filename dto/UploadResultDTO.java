package kr.hs.dge.dgsw.ex1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Getter
@Setter
@AllArgsConstructor
public class UploadResultDTO {
    private String fileName;
    private String uuid;
    private String folderPath;

    public String getImageURL(){
        try {
          return  URLEncoder.encode(
                    folderPath + "/" + uuid + "_" + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        return "";

    }

    public String getThumbnailURL(){
        try {
            return  URLEncoder.encode(
                    folderPath + "/s_" + uuid + "_" + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        return "";

    }
}
