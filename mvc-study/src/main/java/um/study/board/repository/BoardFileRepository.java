package um.study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.study.board.entity.BoardFileEntity;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
}
