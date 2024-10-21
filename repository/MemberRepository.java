package kr.hs.dge.dgsw.ex1.repository;

import kr.hs.dge.dgsw.ex1.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

    Optional<MemberEntity> findByEmail(String email);


}
