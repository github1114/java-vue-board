package com.board.backend.api.v1;

import com.board.backend.service.BoardService;
import com.board.backend.domain.Board.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardApi {

    private static final Logger logger = LoggerFactory.getLogger(BoardApi.class);
    private final BoardService boardService;

    public BoardApi(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<List<Board>> getList(@RequestParam(value="page", required=true) int page,
                               @RequestParam(value="size", required=true) int size){
        logger.info("Board List request param page : ["+page+"] size: ["+size+"]");
        return  new ResponseEntity<>(boardService.boardList(page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Board board){
        logger.info("Board Insert request body : "+board);
        return new ResponseEntity<>( boardService.insertBoard(board), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody Board board){
        logger.info("Board Update request body : "+board);
        return  new ResponseEntity<>( boardService.updateBoard(board), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(value = "boardId", required = true)long boardId){
        logger.info("Board Delete request param : "+boardId);
        return new ResponseEntity<>( boardService.deleteBoard(boardId), HttpStatus.OK);
    }

}
