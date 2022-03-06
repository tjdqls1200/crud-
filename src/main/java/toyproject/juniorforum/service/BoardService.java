package toyproject.juniorforum.service;

import toyproject.juniorforum.domain.BoardDTO;
import toyproject.juniorforum.domain.BoardVO;
import toyproject.juniorforum.domain.Criteria;
import toyproject.juniorforum.domain.UpdateFormDTO;

import java.util.List;

public interface BoardService {
    int create(BoardDTO board);

    List<BoardVO> getList(Criteria criteria);

    BoardVO read(int boardId);

    int update(UpdateFormDTO board);
}
