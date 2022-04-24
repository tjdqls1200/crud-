package toyproject.juniorforum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.juniorforum.service.BoardService;

import javax.servlet.http.HttpServletRequest;

import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;
import static toyproject.juniorforum.domain.VO.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Criteria criteria, Model model) {
        model.addAttribute("boardList", boardService.getList(criteria));
        model.addAttribute("pageDTO", new PageDTO(5, boardService.getTotal(criteria), criteria));

        log.info("--- list ---");
        return "board/list";
    }


    @GetMapping("/write")
    public String createForm(Model model) {
        model.addAttribute("board", new BoardSaveForm());
        log.info("--- createForm ---");
        return "board/create";
    }

    @PostMapping("/write")
    public String create(@Validated @ModelAttribute("board") BoardSaveForm boardSaveForm, BindingResult
            bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "board/create";
        }
        BoardDTO boardDTO = BoardDTO.builder()
                .title(boardSaveForm.getTitle())
                .writer("이성빈")
                .content(boardSaveForm.getContent())
                .build();
        boardService.create(boardDTO);
        redirectAttributes.addAttribute("boardId", boardDTO.getBoardId());
        redirectAttributes.addFlashAttribute("result", "true");
        log.info("--- create ---");
        return "redirect:/board/read";
    }

    @GetMapping({"/read", "/update"})
    public String read(Criteria criteria, int boardId, Model model, HttpServletRequest request) {
        BoardVO boardVO = boardService.read(boardId);
        String requestType = request.getRequestURI().substring(7);
        if (requestType.equals("read")) {
            model.addAttribute("board", boardVO.convertDTO());
            log.info("--- read ---");
        } else if (requestType.equals("update")) {
            model.addAttribute("board", boardVO.convertToUpdateDTO());
            log.info("--- updateForm ---");
        }
        log.info("read board = {}", boardVO.convertDTO().getContent());
        return "board/" + requestType;
    }

    @PostMapping("/update")
    public String update(@Validated @ModelAttribute("board") BoardUpdateForm board, BindingResult bindingResult,
                         Criteria criteria, RedirectAttributes redirectAttributes) {
        log.info("--- update ---");
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "board/update";
        }
        boardService.update(board);
        redirectAttributes.addFlashAttribute("result", "true");
        return "redirect:/board/list" + criteria.getListLink();
    }

    @GetMapping("/delete")
    public String delete(Criteria criteria, int boardId, Model model, RedirectAttributes redirectAttributes) {
        log.info("--- delete ---");
        boardService.delete(boardId);
        redirectAttributes.addFlashAttribute("result", "true");
        return "redirect:/board/list" + criteria.getListLink();
    }

}