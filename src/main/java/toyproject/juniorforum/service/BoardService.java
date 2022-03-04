package toyproject.juniorforum.service;

import toyproject.juniorforum.domain.BoardDTO;
import toyproject.juniorforum.domain.BoardVO;

import java.util.List;

public interface BoardService {
    int create(BoardDTO board);

    List<BoardVO> getList();

    BoardVO read(int boardId);

    int update(BoardDTO board);
}
