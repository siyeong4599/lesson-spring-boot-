package kr.hs.dge.dgsw.ex1.service;

import kr.hs.dge.dgsw.ex1.dto.ReplyDTO;
import kr.hs.dge.dgsw.ex1.entity.BoardEntity;
import kr.hs.dge.dgsw.ex1.entity.ReplyEntity;
import kr.hs.dge.dgsw.ex1.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO dto) {
        ReplyEntity replyEntity = dtoToEntity(dto);
        replyRepository.save(replyEntity);
        return replyEntity.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {
        BoardEntity boardEntity = BoardEntity.builder().bno(bno).build();
        List<ReplyEntity> result = replyRepository.findByBoardOrderByRno(boardEntity);
        return result.stream()
                .map(entity -> entityToDTO(entity))
                .toList();
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        ReplyEntity replyEntity = dtoToEntity(replyDTO);
        replyRepository.save(replyEntity);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }
}
