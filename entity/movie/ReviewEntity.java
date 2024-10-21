package kr.hs.dge.dgsw.ex1.entity.movie;

import jakarta.persistence.*;
import kr.hs.dge.dgsw.ex1.entity.BaseEntity;
import kr.hs.dge.dgsw.ex1.entity.MemberEntity;
import lombok.*;

@Entity
@Table(name = "tbl_review")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"movieEntity", "memberEntity"})
public class ReviewEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private int grade;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private MovieEntity movieEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memberEntity;

}
