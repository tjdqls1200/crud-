package toyproject.juniorforum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.juniorforum.domain.DTO;
import toyproject.juniorforum.domain.Paging;
import toyproject.juniorforum.domain.Paging.Criteria;
import toyproject.juniorforum.domain.VO;
import toyproject.juniorforum.exception.BoardNotFoundException;
import toyproject.juniorforum.exception.ReplyNotFoundException;
import toyproject.juniorforum.mapper.BoardMapper;
import toyproject.juniorforum.mapper.ReplyMapper;

import java.util.Collections;
import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;
import static toyproject.juniorforum.domain.VO.*;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyMapper replyMapper;
    private final BoardMapper boardMapper;

    @Override
    public int create(ReplyDTO replyDTO) {
        return replyMapper.createReply(replyDTO);
    }

    @Override
    public ReplyVO read(int replyId) {
        ReplyVO reply = replyMapper.readReply(replyId);
        if (reply == null) {
            throw new ReplyNotFoundException(replyId);
        }
        return reply;
    }

    @Override
    public int update(ReplyDTO replyDTO) {
        return replyMapper.updateReply(replyDTO);
    }

    @Override
    public int delete(int replyId) {
        return replyMapper.deleteReply(replyId);
    }


    @Override
    public ReplyPagingDTO getList(Criteria criteria, int boardId, int pageNum) {
        criteria.calculateStarNum();

        if (boardMapper.read(boardId) == null) {
            throw new BoardNotFoundException(boardId);
        }
        criteria.setPageNum(pageNum);
        List<ReplyVO> list = replyMapper.getListWithReplyPaging(criteria, boardId);
        if (list == null) {
            list = Collections.emptyList();
        }
        PageDTO pageDTO = new PageDTO(5, replyMapper.getReplyCount(boardId), criteria);
        return new ReplyPagingDTO(list, pageDTO);
    }
}
