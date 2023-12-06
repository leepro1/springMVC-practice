package com.shop.entity;

import com.shop.dto.MemberFormDTO;
import com.shop.repository.CartRepository;
import com.shop.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberService memberService;

    @PersistenceContext
    EntityManager em;

    public MemberFormDTO createMember() {
        MemberFormDTO memberFormDTO = new MemberFormDTO();
        memberFormDTO.setName("이희주");
        memberFormDTO.setEmail("test@email.com");
        memberFormDTO.setPassword("1234");
        memberFormDTO.setAddress("서울시 마포구 테스트동");
        return memberFormDTO;
    }

    @Test
    @DisplayName("장바구니 회원 매핑 조회 테스트")
    void findCartAndMemberTest() {
        MemberFormDTO memberFormDTO = createMember();
        Member member = memberService.saveMember(memberFormDTO);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush(); // db에 반영
        em.clear(); // 테스트를 위해 비우기

        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(savedCart.getMember().getId(), member.getId());
    }
}