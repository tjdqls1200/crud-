package toyproject.juniorforum.mapper;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.juniorforum.domain.BoardDTO;
import toyproject.juniorforum.domain.BoardVO;
import toyproject.juniorforum.service.BoardService;
import toyproject.juniorforum.service.BoardServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureMybatis
@Slf4j
class BoardMapperTest {
    private final BoardMapper boardMapper;
    private final BoardService boardService;

    @Autowired
    public BoardMapperTest(BoardMapper boardMapper, BoardServiceImpl boardService) {
        this.boardMapper = boardMapper;
        this.boardService = boardService;
    }

    @DisplayName("리스트 가져오기")
    @Test
    public void getList() {

        //when
        int id = 1;
        List<BoardVO> list = boardService.getList();

        //then
        for (BoardVO boardVO : list) {
            assertThat(boardVO.getBoardId()).isEqualTo(id);
            id++;
        }
    }

    @Transactional
    @DisplayName("게시글 작성")
    @Test
    public void create() {
        //given
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("테스트제목");
        boardDTO.setContent("테스트내용");
        boardDTO.setWriter("테스트작성자");


        //when
        int result = boardService.create(boardDTO);
        int boardDtoId = boardDTO.getBoardId();
        BoardVO boardVO = boardService.read(boardDtoId);

        //then
        assertThat(result).isEqualTo(1);
        assertThat(boardService.read(boardDTO.getBoardId()).getBoardId());
        assertThat(boardDtoId).isEqualTo(boardVO.getBoardId());

    }
}