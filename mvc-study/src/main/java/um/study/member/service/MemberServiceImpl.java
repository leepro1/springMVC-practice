package um.study.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.study.member.dto.MemberDTO;
import um.study.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void join(MemberDTO memberDTO) {
        //memberRepository.save(memberDTO);
    }
}
