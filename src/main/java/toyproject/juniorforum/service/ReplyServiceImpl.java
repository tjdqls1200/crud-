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
    public List<ReplyVO> getList(Criteria criteria, int boardId) {
        criteria.calculateStarNum();

        if (boardMapper.read(boardId) == null) {
            throw new BoardNotFoundException(boardId);
        }
        List<ReplyVO> replyList = replyMapper.getListWithReplyPaging(criteria, boardId);
        if (replyList == null) {
            replyList = Collections.emptyList();
        }
        return replyList;
    }
}
