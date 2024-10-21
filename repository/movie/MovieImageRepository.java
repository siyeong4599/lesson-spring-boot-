package kr.hs.dge.dgsw.ex1.repository.movie;

import kr.hs.dge.dgsw.ex1.entity.movie.MovieImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImageEntity, Long> {

}
