package um.study.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import um.study.member.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
