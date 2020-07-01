package com.board.backend.domain.Board;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BoardRepository extends PagingAndSortingRepository<Board,Long> {
}
