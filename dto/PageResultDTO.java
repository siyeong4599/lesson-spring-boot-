package kr.hs.dge.dgsw.ex1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class PageResultDTO<DTO, EN> {
    //DTO 리스트
    private List<DTO> dtoList;
    //총 페이지 번호
    private int totalPage;
    //현재 페이지 번호
    private int page;
    //목록 사이즈
    private int size;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        Pageable pageable = result.getPageable();
        this.page = pageable.getPageNumber() + 1; // 0부터 시작, +1
        this.size = pageable.getPageSize();
    }


}
