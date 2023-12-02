package com.shop.controller;

import com.shop.dto.MemberFormDTO;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    public MemberFormDTO createMemberFormDTO(String email, String password) {
        MemberFormDTO memberFormDTO = new MemberFormDTO();
        memberFormDTO.setName("이희주");
        memberFormDTO.setEmail(email);
        memberFormDTO.setPassword(password);
        memberFormDTO.setAddress("서울시 마포구 테스트동");
        return memberFormDTO;
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginSuccessTest() throws Exception {
        String email = "test@email.com";
        String password = "12345678";
        MemberFormDTO memberFormDTO = this.createMemberFormDTO(email, password);
        Member member = memberService.saveMember(memberFormDTO);

        mockMvc.perform(formLogin().userParameter("email")
                        .loginProcessingUrl("/member/login")
                        .user(email)
                        .password(password))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }


    @Test
    @DisplayName("로그인 실패 테스트")
    void loginFailTest() throws Exception {
        String email = "test@email.com";
        String password = "12345678";
        MemberFormDTO memberFormDTO = this.createMemberFormDTO(email, password);
        Member member = memberService.saveMember(memberFormDTO);

        mockMvc.perform(formLogin().userParameter("email")
                        .loginProcessingUrl("/member/login")
                        .user(email)
                        .password("1234"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }
}