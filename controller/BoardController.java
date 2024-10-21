package kr.hs.dge.dgsw.ex1.controller;

import kr.hs.dge.dgsw.ex1.dto.BoardDTO;
import kr.hs.dge.dgsw.ex1.dto.PageRequest2DTO;
import kr.hs.dge.dgsw.ex1.dto.PageRequestDTO;
import kr.hs.dge.dgsw.ex1.dto.PageResultDTO;
import kr.hs.dge.dgsw.ex1.entity.BoardEntity;
import kr.hs.dge.dgsw.ex1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public void register(@RequestBody BoardDTO dto) {
        boardService.register(dto);
    }

    // GET : localhost:8080/board/10
    @GetMapping("/{bno}")
    public ResponseEntity<BoardDTO> read(
            @PathVariable("bno") long bno
    ) {
        BoardDTO boardDTO = boardService.get(bno);
        return ResponseEntity.ok(boardDTO);
    }

    // localhost:8080/board/list?page=2&size=20
    @GetMapping("/list")
    public ResponseEntity list(PageRequestDTO requestDTO) {
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(requestDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("")
    public void modify(@RequestBody BoardDTO dto) {
        boardService.modify(dto);
    }

    // localhost:8080/board/{bno}
    @DeleteMapping("/{bno}")
    public void remove(
            @PathVariable("bno") long bno
    ) {
//        boardService.remove(bno);
        boardService.removeWithReplies(bno);
    }

}
