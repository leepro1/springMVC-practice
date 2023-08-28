package um.study.board.service;

import um.study.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    void save(BoardDTO boardDTO);

    List<BoardDTO> findAll();
}
