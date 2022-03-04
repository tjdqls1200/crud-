package toyproject.juniorforum.mapper;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import toyproject.juniorforum.domain.BoardDTO;
import toyproject.juniorforum.domain.BoardVO;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;

    @BeforeEach
    void setup() {
    }

    @DisplayName("게시글 읽기")
    @Test
    void read() {
        //given
        //when
        BoardVO boardVO = boardMapper.read(1);

        //then
        assertThat(boardVO.getBoardId()).isEqualTo(1);
        assertThat(boardVO.getTitle()).isEqualTo("테스트1");
    }

    @DisplayName("게시글 생성")
    @Test
    void create() {
        //given
        BoardDTO boardDTO = BoardDTO.builder()
                .title("테스트제목1")
                .content("테스트내용1")
                .writer("테스트작가1")
                .build();

        //when
        int result = boardMapper.insert(boardDTO);
        // BoardVO boardVO = boardMapper.read(boardDTO.getBoardId());

        //then
        assertThat(result).isEqualTo(1);
//        assertThat(boardVO.getTitle()).isEqualTo("테스트제목1");
//        assertThat(boardVO.getContent()).isEqualTo("테스트내용1");
//        assertThat(boardVO.getWriter()).isEqualTo("테스트작가1");
    }

    @DisplayName("게시글 목록")
    @Test
    void list() {
        //given
        BoardDTO board1 = BoardDTO.builder()
                .title("테스트제목1")
                .content("테스트내용1")
                .writer("테스트작가1")
                .build();

        //when
        boardMapper.insert(board1);

        //then
        List<BoardVO> list = boardMapper.getList();
        BoardVO lastCreatedBoard = list.get(list.size() - 1);
        assertThat(lastCreatedBoard.getTitle()).isEqualTo("테스트제목1");
    }

    @DisplayName("게시글 업데이트")
    @Test
    void update() {
        //given
        BoardDTO board1 = BoardDTO.builder()
                .title("테스트제목1")
                .content("테스트내용1")
                .writer("테스트작가1")
                .build();
        boardMapper.insert(board1);

        //when
        board1.setTitle("테스트제목2");
        boardMapper.update(board1);

        //then
        BoardVO boardVO = boardMapper.read(board1.getBoardId());
        assertThat(boardVO.getTitle()).isEqualTo("테스트제목2");
    }
}