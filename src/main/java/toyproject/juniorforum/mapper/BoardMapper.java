package toyproject.juniorforum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import toyproject.juniorforum.domain.BoardDTO;
import toyproject.juniorforum.domain.BoardVO;
import toyproject.juniorforum.domain.Criteria;
import toyproject.juniorforum.domain.UpdateFormDTO;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {
    List<BoardVO> getListWithPaging(Criteria criteria);

    //Create
    int insert(BoardDTO board);

    //Read
    BoardVO read(int boardId);

    //Update
    int update(UpdateFormDTO board);


    //Delete
    //int delete (Long boardId);
}
