package um.study.member.service;

import um.study.member.dto.MemberDTO;

public interface MemberService {
    void join(MemberDTO memberDTO);
    MemberDTO login(MemberDTO memberDTO);
}
