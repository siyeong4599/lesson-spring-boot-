package kr.hs.dge.dgsw.ex1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ReplyDTO {
    private Long rno;
    private String text;
    private String replyer;
    private Long bno;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
