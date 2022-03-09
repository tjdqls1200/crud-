package toyproject.juniorforum.service;

import toyproject.juniorforum.domain.BoardVO;

import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;

public interface BoardService {
    int create(BoardDTO board);

    List<BoardVO> getList(Criteria criteria);

    BoardVO read(int boardId);

    int update(BoardUpdateForm board);

    int getTotal(Criteria criteria);
}
