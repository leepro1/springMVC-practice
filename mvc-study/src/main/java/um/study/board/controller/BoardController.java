package um.study.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import um.study.board.dto.BoardDTO;
import um.study.board.service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "board/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "index";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "board/list";
    }
}
