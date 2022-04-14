package toyproject.juniorforum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;
import static toyproject.juniorforum.domain.VO.*;

@Repository
@Mapper
public interface BoardMapper {
    List<BoardVO> getListWithPaging(Criteria criteria);

    //Create
    int insert(BoardDTO board);

    //Read
    BoardVO read(int boardId);

    //Update
    int update(BoardUpdateForm board);

    int getTotalCount(Criteria criteria);

    void addHit(int boardId);
    //Delete
    int delete (int boardId);
}
