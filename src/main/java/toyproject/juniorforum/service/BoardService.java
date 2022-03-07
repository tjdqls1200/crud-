package toyproject.juniorforum.service;

import toyproject.juniorforum.domain.BoardVO;
import toyproject.juniorforum.domain.Criteria;
import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;

public interface BoardService {
    int create(BoardDTO board);

    List<BoardVO> getList(Criteria criteria);

    BoardVO read(int boardId);

    int update(BoardUpdateForm board);
}
