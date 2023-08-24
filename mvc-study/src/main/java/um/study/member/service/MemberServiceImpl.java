package um.study.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.study.member.dto.MemberDTO;
import um.study.member.entity.MemberEntity;
import um.study.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public MemberDTO login(MemberDTO memberDTO) {
        //1. db에서 이메일 조회
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

        //2. 비밀번호 일치 확인
        if (byMemberEmail.isPresent()) {
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                //entity를 dto로 변횐
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<MemberDTO> findALl() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }

        return memberDTOList;
    }
}
