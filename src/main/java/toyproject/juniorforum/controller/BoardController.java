package toyproject.juniorforum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.juniorforum.domain.*;
import toyproject.juniorforum.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Criteria criteria, Model model) {
//        if (pageNum != null) {
//            criteria.setPageNum((int) pageNum);
//        }
        log.info("criteria.pageNum = {}", criteria.getPageNum());
        model.addAttribute("boardList", boardService.getList(criteria));
        model.addAttribute("pageDTO", new PageDTO(5, boardService.getTotal(criteria), criteria));
        log.info("list controller");
        return "board/list";
    }



    @GetMapping
    public String createForm (Model model){
        model.addAttribute("board", new BoardSaveForm());
        log.info("createForm controller");
        return "board/create";
    }

    @PostMapping
    public String create(@Validated @ModelAttribute("board") BoardSaveForm boardSaveForm, BindingResult
            bindingResult, RedirectAttributes redirectAttributes) {
        log.info("create");
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "board/create";
        }
        BoardDTO boardDTO = BoardDTO.builder()
                .title(boardSaveForm.getTitle())
                .writer("이성빈")
                .content(boardSaveForm.getContent()
                        .replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
                .build();
        boardService.create(boardDTO);
        redirectAttributes.addAttribute("boardId", boardDTO.getBoardId());
        redirectAttributes.addAttribute("status", true);
        log.info("create controller");
        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/update"})
    public String read(Criteria criteria, int boardId, Model model, HttpServletRequest request) {
        log.info("read controller");

        model.addAttribute("board", boardService.read(boardId));
        model.addAttribute("criteria", criteria);
        return "board/read";
    }

    @GetMapping("/{boardId}/update")
    public String updateForm(@PathVariable int boardId, Model model) {
        model.addAttribute("board", boardService.read(boardId).convertToUpdateDTO());
        log.info("updateForm controller");
        return "board/update";
    }

    @PostMapping("/{boardId}/update")
    public String update(@Validated @ModelAttribute("board") BoardUpdateForm board, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "board/update";
        }
        boardService.update(board);
        model.addAttribute("boardList", boardService.getList(new Criteria()));
        log.info("update controller");
        return "board/list";
    }

}