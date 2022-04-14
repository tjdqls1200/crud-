package toyproject.juniorforum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.juniorforum.mapper.BoardMapper;

import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;
import static toyproject.juniorforum.domain.VO.*;

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
        criteria.calculateStarNum();
        return boardMapper.getListWithPaging(criteria);
    }

    @Override
    public BoardVO read(int boardId) {
        boardMapper.addHit(boardId);
        return boardMapper.read(boardId);
    }

    @Override
    public int update(BoardUpdateForm board) {

        return boardMapper.update(board);
    }

    @Override
    public int getTotal(Criteria criteria) {
        return boardMapper.getTotalCount(criteria);
    }

    @Override
    public int delete(int boardId) {
        return boardMapper.delete(boardId);
    }
}
