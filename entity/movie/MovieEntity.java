package kr.hs.dge.dgsw.ex1.entity.movie;

import jakarta.persistence.*;
import kr.hs.dge.dgsw.ex1.entity.BaseEntity;
import lombok.*;

@Entity
@Table(name = "tbl_movie")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class MovieEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(nullable = false)
    private String title;

}
