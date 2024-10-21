package kr.hs.dge.dgsw.ex1.repository;

import kr.hs.dge.dgsw.ex1.entity.BoardEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query("SELECT b, m, COUNT(r) " +
            "FROM BoardEntity b " +
            "LEFT JOIN b.member m " +
            "LEFT JOIN ReplyEntity r ON r.board=b " +
            "WHERE bno=:bno")
    Object getBoardByBno(@Param("bno") Long bno);

    @Query(value = "SELECT b, m, COUNT(r) " +
            "FROM BoardEntity b " +
            "LEFT JOIN b.member m " +
            "LEFT JOIN ReplyEntity r ON r.board=b " +
            "GROUP BY b",
            countQuery = "SELECT COUNT(b) FROM BoardEntity b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);
    @Query("SELECT b, r " +
            "FROM BoardEntity b " +
            "LEFT JOIN ReplyEntity r ON r.board = b " +
            "WHERE b.bno=:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);
    @Query("SELECT b, m " +
            "FROM BoardEntity b " +
            "LEFT JOIN b.member m " +
            "WHERE b.bno=:bno")
        // JPQL
    Object getBoardWithMember(@Param("bno") Long bno);
}
