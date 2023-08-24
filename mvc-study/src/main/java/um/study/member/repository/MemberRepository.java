package um.study.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import um.study.member.dto.MemberDTO;
import um.study.member.entity.MemberEntity;

public interface MemberRepository  extends JpaRepository<MemberEntity, Long> {
}
