package toyproject.juniorforum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.juniorforum.domain.BoardVO;
import toyproject.juniorforum.domain.Criteria;
import toyproject.juniorforum.mapper.BoardMapper;

import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    @Override
    public int create(BoardDTO board) {
        return boardMapper.insert(board);
    }

    @Override
    public List<BoardVO> getList(Criteria criteria) {
        return boardMapper.getListWithPaging(criteria);
    }

    @Override
    public BoardVO read(int boardId) {

        return boardMapper.read(boardId);
    }

    @Override
    public int update(BoardUpdateForm board) {
        return boardMapper.update(board);
    }
}
