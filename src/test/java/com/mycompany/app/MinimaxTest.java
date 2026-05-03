package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinimaxTest {

    private Minimax minimax;
    private Board board;

    @BeforeEach
    public void setUp() {
        minimax = new Minimax();
        board = new Board();
    }

    @Test
    public void testFindBestMove_WinningMove() {
        board.makeMove(0, 0, Player.X);
        board.makeMove(0, 1, Player.X);
        board.makeMove(1, 0, Player.O);
        board.makeMove(1, 1, Player.O);
        
        Board boardCopy = new Board(board);
        Move bestMove = minimax.findBestMove(boardCopy, Player.X);
        assertEquals(0, bestMove.row);
        assertEquals(2, bestMove.col);
    }

    @Test
    public void testFindBestMove_BlockingMove() {
        board.makeMove(0, 0, Player.X);
        board.makeMove(0, 1, Player.X);
        board.makeMove(1, 0, Player.O);

        Board boardCopy = new Board(board);
        Move bestMove = minimax.findBestMove(boardCopy, Player.O);
        assertEquals(0, bestMove.row);
        assertEquals(2, bestMove.col);
    }

    @Test
    public void testFindBestMove_CenterOpening() {
        board.makeMove(0, 0, Player.X);
        
        Board boardCopy = new Board(board);
        Move bestMove = minimax.findBestMove(boardCopy, Player.O);
        assertTrue(bestMove.row >= 0 && bestMove.row < 3);
        assertTrue(bestMove.col >= 0 && bestMove.col < 3);
        assertTrue(board.isCellEmpty(bestMove.row, bestMove.col));
    }
    
    @Test
    public void testFindBestMove_EmptyBoard() {
        Board boardCopy = new Board(board);
        Move bestMove = minimax.findBestMove(boardCopy, Player.X);
        assertNotEquals(-1, bestMove.row);
        assertNotEquals(-1, bestMove.col);
        assertTrue(board.isCellEmpty(bestMove.row, bestMove.col));
    }
}
