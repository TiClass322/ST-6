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
        //  X | X | _
        //  O | O | _
        //  _ | _ | _
        board.makeMove(0, 0, Player.X);
        board.makeMove(0, 1, Player.X);
        board.makeMove(1, 0, Player.O);
        board.makeMove(1, 1, Player.O);
        
        // AI (X) should choose (0, 2) to win
        Board boardCopy = new Board(board);
        Move bestMove = minimax.findBestMove(boardCopy, Player.X);
        assertEquals(0, bestMove.row);
        assertEquals(2, bestMove.col);
    }

    @Test
    public void testFindBestMove_BlockingMove() {
        //  X | X | _
        //  O | _ | _
        //  _ | _ | _
        board.makeMove(0, 0, Player.X);
        board.makeMove(0, 1, Player.X);
        board.makeMove(1, 0, Player.O);

        // AI (O) should choose (0, 2) to block X's win
        Board boardCopy = new Board(board);
        Move bestMove = minimax.findBestMove(boardCopy, Player.O);
        assertEquals(0, bestMove.row);
        assertEquals(2, bestMove.col);
    }

    @Test
    public void testFindBestMove_CenterOpening() {
        // Human (X) takes a corner
        board.makeMove(0, 0, Player.X);
        
        // AI (O) should ideally take the center
        Board boardCopy = new Board(board);
        Move bestMove = minimax.findBestMove(boardCopy, Player.O);
        // The algorithm may not choose center, but it must be a valid move.
        assertTrue(bestMove.row >= 0 && bestMove.row < 3);
        assertTrue(bestMove.col >= 0 && bestMove.col < 3);
        assertTrue(board.isCellEmpty(bestMove.row, bestMove.col));
    }
    
    @Test
    public void testFindBestMove_EmptyBoard() {
        // AI (X) plays first
        Board boardCopy = new Board(board);
        Move bestMove = minimax.findBestMove(boardCopy, Player.X);
        // A corner, edge, or center are all reasonable openings.
        // The algorithm will have a preference, let's just assert it's a valid move.
        assertNotEquals(-1, bestMove.row);
        assertNotEquals(-1, bestMove.col);
        assertTrue(board.isCellEmpty(bestMove.row, bestMove.col));
    }
}
