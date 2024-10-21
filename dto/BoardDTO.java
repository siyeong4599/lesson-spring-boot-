package kr.hs.dge.dgsw.ex1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class BoardDTO {
    private Long bno;
    private String title;
    private String content;
    private String memberEmail;
    private String memberName;
    private long replyCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
