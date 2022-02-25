package toyproject.juniorforum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.juniorforum.domain.BoardDTO;
import toyproject.juniorforum.domain.BoardVO;
import toyproject.juniorforum.domain.BoardSaveForm;
import toyproject.juniorforum.service.BoardService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardVO> boardList = boardService.getList();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/{boardId}")
    public String read(@PathVariable Long boardId, Model model) {
        BoardVO board = boardService.read(boardId);
        model.addAttribute("board", board);
        return "board/read";
    }

    @GetMapping("/{boardId}/update")
    public String updateForm(@PathVariable Long boardId, Model model) {
        BoardVO boardVO = boardService.read(boardId);
        BoardDTO board = boardVO.convertToDTO();
        model.addAttribute("board", board);
        return "board/update";
    }

    @PostMapping("/{boardId}/update")
    public String update(@Validated @ModelAttribute("board") BoardDTO board, BindingResult bindingResult, @PathVariable Long boardId, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "board/update";
        }
        boardService.update(board);
        model.addAttribute("boardList", boardService.getList());
        return "board/list";
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
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(boardSaveForm.getTitle());
        boardDTO.setContent(boardSaveForm.getContent().
                replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
        boardService.create(boardDTO);
        redirectAttributes.addAttribute("boardId", boardDTO.getBoardId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/{boardId}";
    }
}