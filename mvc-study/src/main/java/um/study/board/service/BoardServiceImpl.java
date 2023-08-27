package um.study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.study.board.dto.BoardDTO;
import um.study.board.entity.BoardEntity;
import um.study.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
