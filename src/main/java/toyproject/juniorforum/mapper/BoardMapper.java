package toyproject.juniorforum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import toyproject.juniorforum.domain.BoardVO;
import toyproject.juniorforum.domain.Criteria;
import java.util.List;
import static toyproject.juniorforum.domain.DTO.*;

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


    //Delete
    //int delete (Long boardId);
}
