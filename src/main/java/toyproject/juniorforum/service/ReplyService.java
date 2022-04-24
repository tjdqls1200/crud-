package toyproject.juniorforum.service;

import toyproject.juniorforum.domain.DTO;
import toyproject.juniorforum.domain.DTO.ReplyDTO;
import toyproject.juniorforum.domain.Paging;

import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;
import static toyproject.juniorforum.domain.VO.*;

public interface ReplyService {
    int create(ReplyDTO replyDTO);

    ReplyVO read(int replyId);

    int update(ReplyDTO replyDTO);

    int delete(int replyId);
    ReplyPagingDTO getList(Criteria criteria, int boardId, int pageNum);
}
