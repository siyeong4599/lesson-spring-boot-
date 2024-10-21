package kr.hs.dge.dgsw.ex1.service;

import kr.hs.dge.dgsw.ex1.dto.ReplyDTO;
import kr.hs.dge.dgsw.ex1.entity.BoardEntity;
import kr.hs.dge.dgsw.ex1.entity.ReplyEntity;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO dto);

    List<ReplyDTO> getList(Long bno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    default ReplyEntity dtoToEntity(ReplyDTO dto){
        BoardEntity boardEntity = BoardEntity.builder().bno(dto.getBno()).build();
        return ReplyEntity.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .board(boardEntity)
                .build();
    }

    default ReplyDTO entityToDTO(ReplyEntity entity){
        return ReplyDTO.builder()
                .rno(entity.getRno())
                .text(entity.getText())
                .replyer(entity.getReplyer())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .build();
    }
}
