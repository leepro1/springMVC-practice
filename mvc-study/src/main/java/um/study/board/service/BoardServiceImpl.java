package um.study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import um.study.board.dto.BoardDTO;
import um.study.board.entity.BoardEntity;
import um.study.board.entity.BoardFileEntity;
import um.study.board.repository.BoardFileRepository;
import um.study.board.repository.BoardRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    @Override
    public void save(BoardDTO boardDTO) throws IOException {
        //파일 첨부 여부에 따라 로직 구분
        if (boardDTO.getBoardFile().isEmpty()) {
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        } else {
            /*
                1. DTO에 담긴 파일을 꺼냄
                2. 파일의 이름 가저옴 
                3. 서버 저장용 이름 생성
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. board_table에 해당 데이터 save 처리 + id 가져옴
                7. board_file_table에 해당 데이터 save 처리
             */
            MultipartFile boardFile = boardDTO.getBoardFile();
            String originalFilename = boardFile.getOriginalFilename();  //DTO 필드가 아니라 MultipartFile 메서드다
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;    //1970년을 기준으로 몇 밀리세크가 지났는지
            String savePath = "C:/Users/82108/mvcstudy_img/" + storedFileName;
            boardFile.transferTo(new File(savePath));

            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);   //db에 저장하기 전이라 id값이 없음
            Long saveId = boardRepository.save(boardEntity).getId();
            BoardEntity savedEntity = boardRepository.findById(saveId).get();

            BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(savedEntity, originalFilename, storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
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

    @Transactional
    @Override
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Override
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            return BoardDTO.toBoardDTO(optionalBoardEntity.get());
        } else {
            return null;
        }
    }

    @Override
    public BoardDTO update(BoardDTO boardDTO) {
        boardRepository.save(BoardEntity.toUpdateBoardEntity(boardDTO));
        return findById(boardDTO.getId());
    }

    @Override
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 게시글 수

        //한 페이지당 3개씩의 글, id 기준 내림차순 정렬
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        /*
            System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
            System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
            System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
            System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
            System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
            System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
            System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
            System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부
        */

        // 목록에서는 id, writer, title, hits, createdTime 정도만 필요
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));

        return boardDTOS;
    }
}
