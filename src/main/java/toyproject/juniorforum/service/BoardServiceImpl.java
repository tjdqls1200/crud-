package toyproject.juniorforum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.juniorforum.domain.BoardDTO;
import toyproject.juniorforum.domain.BoardVO;
import toyproject.juniorforum.mapper.BoardMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public List<BoardVO> getList() {
         return boardMapper.getList();
    }

    @Override
    public int create(BoardDTO board) {
        return boardMapper.insert(board);
    }

    @Override
    public BoardVO read(Long boardId) {
        return boardMapper.read(boardId);
    }

    @Override
    public int update(BoardDTO board) {
        return boardMapper.update(board);
    }
}
