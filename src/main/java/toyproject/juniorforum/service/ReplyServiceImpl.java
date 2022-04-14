package toyproject.juniorforum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.juniorforum.domain.DTO;
import toyproject.juniorforum.domain.Paging;
import toyproject.juniorforum.domain.Paging.Criteria;
import toyproject.juniorforum.domain.VO;
import toyproject.juniorforum.mapper.ReplyMapper;

import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.VO.*;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyMapper replyMapper;

    @Override
    public int create(ReplyDTO replyDTO) {
        return replyMapper.createReply(replyDTO);
    }

    @Override
    public ReplyVO read(int replyId) {
        return replyMapper.readReply(replyId);
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
    public int getTotal(int boardId) {
        // 해당 board ID를 가진 reply 개수
        return replyMapper.getReplyCount(boardId);
    }

    @Override
    public List<ReplyVO> getList(Criteria criteria, int boardId) {
        criteria.calculateStarNum();
        return replyMapper.getListWithReplyPaging(criteria, boardId);
    }
}
