package kr.hs.dge.dgsw.ex1.controller.movie;

import kr.hs.dge.dgsw.ex1.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    @PostMapping("")
    public void register() {
    }

    @GetMapping("/list")
    public ResponseEntity list(
            PageRequestDTO pageRequestDTO
    ){
        return null;
    }

    @GetMapping("/read/{mno}")
    public ResponseEntity read(
            @PathVariable("mno") Long mno
    ) {
        return null;
    }
}
