package kr.hs.dge.dgsw.ex1.service;

import kr.hs.dge.dgsw.ex1.dto.BoardDTO;
import kr.hs.dge.dgsw.ex1.dto.PageRequestDTO;
import kr.hs.dge.dgsw.ex1.dto.PageResultDTO;
import kr.hs.dge.dgsw.ex1.entity.BoardEntity;
import kr.hs.dge.dgsw.ex1.entity.MemberEntity;

public interface BoardService {

    PageResultDTO<BoardDTO, Object[]>  getList(PageRequestDTO dto);

    Long register(BoardDTO dto);

    BoardDTO get(Long bno);

    void modify(BoardDTO dto);

    void remove(Long bno);

    void removeWithReplies(Long bno);

    default BoardDTO entityToDTO(BoardEntity entity) {
        return BoardDTO.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .build();
    }

    default BoardDTO entityToDTO(BoardEntity boardEntity, MemberEntity memberEntity, long replyCount
    ) {
        return BoardDTO.builder()
                .bno(boardEntity.getBno())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .createdDate(boardEntity.getCreatedDate())
                .modifiedDate(boardEntity.getModifiedDate())
                .memberEmail(memberEntity.getEmail())
                .memberName(memberEntity.getName())
                .replyCount(replyCount)
                .build();

    }


    default BoardEntity dtoToEntity(BoardDTO dto) {
        MemberEntity memberEntity
                = MemberEntity.builder()
                .email(dto.getMemberEmail())
                .build();

        return BoardEntity.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .member(memberEntity)
                .build();
    }

}
