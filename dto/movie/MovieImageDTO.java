package kr.hs.dge.dgsw.ex1.dto.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Getter
@Setter
public class MovieImageDTO {
    private String uuid;
    private String imgName;
    private String path;

    public String getImageURL(){
        try {
            return  URLEncoder.encode(
                    path + "/" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        return "";
    }

    public String getThumbnailURL(){
        try {
            return  URLEncoder.encode(
                    path + "/s_" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        return "";
    }

}
