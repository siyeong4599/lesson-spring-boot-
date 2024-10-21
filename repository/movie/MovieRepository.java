package kr.hs.dge.dgsw.ex1.repository.movie;

import kr.hs.dge.dgsw.ex1.entity.movie.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}
