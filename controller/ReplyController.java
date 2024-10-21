package kr.hs.dge.dgsw.ex1.controller;

import kr.hs.dge.dgsw.ex1.dto.ReplyDTO;
import kr.hs.dge.dgsw.ex1.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO) {
        Long rno = replyService.register(replyDTO);
        return ResponseEntity.ok(rno);
    }

    @GetMapping("/board/{bno}")
    public ResponseEntity<List<ReplyDTO>> getList(@PathVariable("bno") Long bno) {
        List<ReplyDTO> result = replyService.getList(bno);
        return ResponseEntity.ok(result);
    }

    @PutMapping("")
    public void modify(@RequestBody ReplyDTO replyDTO) {
        replyService.modify(replyDTO);
    }

    @DeleteMapping("/{rno}")
    public void remove(@PathVariable("rno") Long rno) {
        replyService.remove(rno);
    }
}
