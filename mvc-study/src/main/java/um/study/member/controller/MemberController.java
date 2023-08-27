package um.study.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import um.study.member.dto.MemberDTO;
import um.study.member.service.MemberService;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm() {
        return "addForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.join(memberDTO);
        return "login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null) {
            //로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            return "login";
        }
    }

    @GetMapping("/")
    public String finAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findALl();
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }
}
