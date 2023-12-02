package com.shop.service;

import com.shop.dto.MemberFormDTO;
import com.shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    public MemberFormDTO createMember() {
        MemberFormDTO memberFormDTO = new MemberFormDTO();
        memberFormDTO.setName("이희주");
        memberFormDTO.setEmail("test@email.com");
        memberFormDTO.setPassword("1234");
        memberFormDTO.setAddress("서울시 마포구 테스트동");
        return memberFormDTO;
    }

    @Test
    @DisplayName("회원가입 테스트")
    void saveMemberTest() {
        MemberFormDTO memberFormDTO = createMember();
        Member saveMember = memberService.saveMember(memberFormDTO);

        assertEquals(memberFormDTO.getAddress(), saveMember.getAddress());
        assertEquals(memberFormDTO.getName(), saveMember.getName());
        assertEquals(memberFormDTO.getEmail(), saveMember.getEmail());
        //assertEquals(memberFormDTO.getPassword(),saveMember.getPassword());
    }
}