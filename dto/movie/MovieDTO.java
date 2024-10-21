package kr.hs.dge.dgsw.ex1.dto.movie;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class MovieDTO {
    private Long mno;
    private String title;

    // imgs
    @Builder.Default
    private List<MovieImageDTO>
    imageDTOList = new ArrayList<>();

    private double avg;
    private int reviewCnt;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
