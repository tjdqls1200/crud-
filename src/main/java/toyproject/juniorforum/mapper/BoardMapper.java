package toyproject.juniorforum.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import toyproject.juniorforum.domain.BoardDTO;
import toyproject.juniorforum.domain.BoardVO;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {
    List<BoardVO> getList();

    //Create
    int insert(BoardDTO board);

    //Read
    BoardVO read(int boardId);

    //Update
    int update(BoardDTO board);

    //Delete
    //int delete (Long boardId);
}
