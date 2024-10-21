package kr.hs.dge.dgsw.ex1.repository.movie;

import kr.hs.dge.dgsw.ex1.entity.movie.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    
}
