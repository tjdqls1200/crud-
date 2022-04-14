package toyproject.juniorforum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import toyproject.juniorforum.exception.ReplyNotFoundException;
import toyproject.juniorforum.service.ReplyService;

import java.net.URI;
import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;
import static toyproject.juniorforum.domain.VO.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping("/{boardId}/{replyId}")
    public ReplyVO read(@PathVariable int boardId,@PathVariable int replyId) {
        ReplyVO read = replyService.read(replyId);

        if (read == null) {
            throw new ReplyNotFoundException(replyId);
        }
        return read;
    }

    @GetMapping("/{boardId}")
    public List<ReplyVO> list(Criteria criteria, @PathVariable int boardId) {
        log.info("--------- Reply List --------");
        log.info("{}", criteria.getPageNum());
        List<ReplyVO> list = replyService.getList(criteria, boardId);
        log.info("{}", list.get(0).getContent());
        return list;
    }

    @PostMapping("/{boardId}")
    public ResponseEntity<ReplyDTO> create(@PathVariable int boardId, @Validated @RequestBody ReplyDTO replyDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
        }
        replyDTO.setBoardId(boardId);
        replyService.create(replyDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{replyId}")
                .buildAndExpand(replyDTO.getReplyId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

//    @PatchMapping("/{boardId}/replies/{replyId}")
//    public ResponseEntity<ReplyDTO> update(@PathVariable int boardId, @PathVariable int replyId,
//                                           @Validated @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            log.info("errors = {}", bindingResult);
//        }
//    }
}
