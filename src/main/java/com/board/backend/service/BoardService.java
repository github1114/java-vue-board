package com.board.backend.service;

import com.board.backend.code.ResultCode;
import com.board.backend.domain.Board.Board;
import com.board.backend.domain.Board.BoardRepository;
import com.board.backend.utils.ErrorUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ErrorUtil errorUtil;

    public BoardService(BoardRepository boardRepository , ErrorUtil errorUtil){
        this.boardRepository =boardRepository;
        this.errorUtil = errorUtil;
    }

    public List<Board> boardList(int page, int size) {
        Pageable pages = PageRequest.of(page, size, Sort.by("boardId").descending());
        return boardRepository.findAll(pages).getContent();
    }

    @Transactional
    public ResultCode insertBoard(Board board) {
        try{
            board.setCreatedDate(new Date());
            board.setModifyDate(new Date());
            boardRepository.save(board);
            return ResultCode.SUCCESS;
        }catch (Exception e) {
            return errorUtil.addErrorMessage(e.getMessage());
        }
    }

    @Transactional
    public ResultCode updateBoard(Board board) {
        try{
            Optional<Board> selectedBoard = boardRepository.findById(board.getBoardId());
            if(selectedBoard.get().getUserId().equals(board.getUserId())){
                boardRepository.save(board);
                return ResultCode.SUCCESS;
            }else {
                return errorUtil.addErrorMessage("UNAUTHORIZED");
            }
        }catch (Exception e) {
            return errorUtil.addErrorMessage(e.getMessage());
        }
    }

    @Transactional
    public ResultCode deleteBoard(long boardId) {
        try{
            boardRepository.deleteById(boardId);
            return ResultCode.SUCCESS;
        }catch (Exception e) {
            return errorUtil.addErrorMessage(e.getMessage());
        }
    }
}
