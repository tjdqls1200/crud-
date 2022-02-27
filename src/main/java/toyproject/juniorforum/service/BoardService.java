package toyproject.juniorforum.service;

import toyproject.juniorforum.domain.BoardDTO;
import toyproject.juniorforum.domain.BoardVO;

import java.util.List;

public interface BoardService {

    List<BoardVO> getList();

    int create(BoardDTO board);

    BoardVO read(int boardId);

    int update(BoardDTO board);
}
