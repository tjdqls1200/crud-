package toyproject.juniorforum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.juniorforum.domain.*;
import toyproject.juniorforum.service.BoardService;

import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @ModelAttribute("cri") Criteria criteria) {
        List<BoardVO> boardList = boardService.getList(new Criteria());
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/{boardId}")
    public String read(@PathVariable int boardId, Model model) {
        BoardVO board = boardService.read(boardId);
        model.addAttribute("board", board);
        return "board/read";
    }

    @GetMapping
    public String createForm (Model model){
        log.info("createForm");
        model.addAttribute("board", new BoardSaveForm());
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
        return "redirect:/board/{boardId}";
    }

    @GetMapping("/{boardId}/update")
    public String updateForm(@PathVariable int boardId, Model model) {
        model.addAttribute("board", boardService.read(boardId).convertToUpdateDTO());
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
        return "board/list";
    }

}