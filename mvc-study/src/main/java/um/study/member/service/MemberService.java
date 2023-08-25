package um.study.member.service;

import um.study.member.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    void join(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    List<MemberDTO> findALl();

    MemberDTO findById(Long id);

    MemberDTO updateForm(String myEmail);

    void update(MemberDTO memberDTO);
}
