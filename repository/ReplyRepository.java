package kr.hs.dge.dgsw.ex1.repository;

import kr.hs.dge.dgsw.ex1.entity.BoardEntity;
import kr.hs.dge.dgsw.ex1.entity.MemberEntity;
import kr.hs.dge.dgsw.ex1.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

    List<ReplyEntity> findByBoardOrderByRno(BoardEntity board);

    @Modifying
    @Query("DELETE FROM ReplyEntity r " +
            "WHERE r.board.bno=:bno") // JPQL
    void deleteByBno(@Param("bno") Long bno);
}
// ReplyEntity -> BoardEntity -> bno