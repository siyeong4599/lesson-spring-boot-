package kr.hs.dge.dgsw.ex1.entity.movie;

import jakarta.persistence.*;
import kr.hs.dge.dgsw.ex1.entity.BaseEntity;
import lombok.*;

@Entity
@Table(name = "tbl_movie_image")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = "movieEntity")
public class MovieImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String uuid;
    private String imgName;
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private MovieEntity movieEntity;

}
