package um.study.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.study.member.dto.MemberDTO;
import um.study.member.entity.MemberEntity;
import um.study.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void join(MemberDTO memberDTO) {
        //1. dto를 entity로 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        //2. repository.save 호출
        memberRepository.save(memberEntity);
    }
}
