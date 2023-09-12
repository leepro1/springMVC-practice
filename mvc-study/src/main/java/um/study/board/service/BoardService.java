package um.study.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import um.study.board.dto.BoardDTO;

import java.io.IOException;
import java.util.List;

public interface BoardService {

    void save(BoardDTO boardDTO) throws IOException;

    List<BoardDTO> findAll();

    void updateHits(Long id);

    BoardDTO findById(Long id);

    BoardDTO update(BoardDTO boardDTO);

    void delete(Long id);

    Page<BoardDTO> paging(Pageable pageable);
}
