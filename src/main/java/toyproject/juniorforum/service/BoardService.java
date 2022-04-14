package toyproject.juniorforum.service;


import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;
import static toyproject.juniorforum.domain.VO.*;

public interface BoardService {
    int create(BoardDTO board);

    List<BoardVO> getList(Criteria criteria);

    BoardVO read(int boardId);

    int update(BoardUpdateForm board);

    int getTotal(Criteria criteria);

    int delete(int boardId);
}
