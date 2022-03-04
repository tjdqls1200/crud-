package toyproject.juniorforum.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import toyproject.juniorforum.domain.BoardDTO;
import toyproject.juniorforum.domain.BoardVO;
import toyproject.juniorforum.mapper.BoardMapper;
import toyproject.juniorforum.service.BoardServiceImpl;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class BoardServiceImplTest {
    @Spy
    private BoardMapper boardMapper;

    @Spy
    @InjectMocks
    private BoardServiceImpl boardService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("게시글 생성하기")
    @Test
    void create() {
        //given
        BoardDTO board1 = new BoardDTO();
        BoardDTO board2 = new BoardDTO();
        doAnswer(invocation -> {
            log.info("Spy BoardMapper Insert!");
            return null;
        }).when(boardMapper).insert(any());

        //when
        boardService.create(board1);
        boardService.create(board2);

        //then
        verify(boardService, times(2)).create(any());
    }

    @DisplayName("게시글 목록 가져오기")
    @Test
    void list() {
        //given
        doAnswer(invocation -> {
            log.info("Spy BoardMapper getList!");
            return null;
        }).when(boardMapper).getList();

        //when
        boardService.getList();

        //then
        verify(boardService, times(1)).getList();
    }

//    BoardVO read(int boardId);
//1
//    int update(BoardDTO board);

    @DisplayName("게시글 가져오기")
    @Test
    void read() {
        //given
        doAnswer(invocation -> {
            log.info("Spy BoardMapper read");
            return null;
        }).when(boardMapper).read(1);

        //when
        boardService.read(1);

        //then
        verify(boardService, times(1)).read(1);
    }

    @DisplayName("게시글 업데이트")
    @Test
    void update() {
        //given
        doAnswer(invocation -> {
            log.info("Spy BoardMapper update!");
            return null;
        }).when(boardMapper).update(any());

        //when
        boardService.update(new BoardDTO());

        //then
        verify(boardService, times(1)).update(any());
    }


//
//    @Transactional
//    @DisplayName("게시글 작성")
//    @Test
//    public void create() {
//        //given
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setTitle("테스트제목");
//        boardDTO.setContent("테스트내용");
//        boardDTO.setWriter("테스트작성자");
//
//
//        //when
//        int result = boardService.create(boardDTO);
//        int boardDtoId = boardDTO.getBoardId();
//        BoardVO boardVO = boardService.read(boardDtoId);
//
//        //then
//        assertThat(result).isEqualTo(1);
//        assertThat(boardDtoId).isEqualTo(boardVO.getBoardId());
//
//    }
}