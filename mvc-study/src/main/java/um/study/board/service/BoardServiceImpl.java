package um.study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.study.board.dto.BoardDTO;
import um.study.board.entity.BoardEntity;
import um.study.board.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    @Override
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            boardDTOList.add(boardDTO);
        }

        return boardDTOList;
    }
}
