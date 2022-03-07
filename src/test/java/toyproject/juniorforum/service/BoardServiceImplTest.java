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
import toyproject.juniorforum.domain.Criteria;
import toyproject.juniorforum.domain.DTO;
import toyproject.juniorforum.mapper.BoardMapper;
import static org.mockito.Mockito.*;
import static toyproject.juniorforum.domain.DTO.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class BoardServiceImplTest {
    /**
     *  Spy : 실제 객체로 또는 Stub 객체로도 활용할 수 있으며,  특정 메서드가 제대로 호출되었는지 여부 확인 가능 (verify)
     *  Stub : 테스트에서 호출된 요청에 대해 미리 준비해둔 결과를 제공(when().thenReturn() OR doAnswer().when() ..)
     **/
    @Spy
    private BoardMapper boardMapper;

    @Spy
    @InjectMocks //의존성 주입
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
        }).when(boardMapper).getListWithPaging(new Criteria());

        //when
        boardService.getList(new Criteria());

        //then
        verify(boardService, times(1)).getList(new Criteria());
    }

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
        // any() : 컴파일러가 타입을 자동으로 유추 (기본 자료형은 인식 못 함)
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
        boardService.update(new BoardUpdateForm());

        //then
        verify(boardService, times(1)).update(any());
    }
}