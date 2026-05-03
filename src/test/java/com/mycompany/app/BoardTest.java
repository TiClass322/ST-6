package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testInitialBoardIsEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(board.isCellEmpty(i, j));
                assertEquals(Player.NONE, board.getCell(i, j));
            }
        }
        assertFalse(board.isBoardFull());
    }

    @Test
    public void testMakeMove() {
        assertTrue(board.makeMove(0, 0, Player.X));
        assertFalse(board.isCellEmpty(0, 0));
        assertEquals(Player.X, board.getCell(0, 0));
    }

    @Test
    public void testMakeInvalidMove() {
        board.makeMove(0, 0, Player.X);
        assertFalse(board.makeMove(0, 0, Player.O)); // Cell already taken
        assertFalse(board.makeMove(-1, 0, Player.O)); // Invalid coordinates
        assertFalse(board.makeMove(3, 0, Player.O)); // Invalid coordinates
    }

    @Test
    public void testIsBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.makeMove(i, j, Player.X);
            }
        }
        assertTrue(board.isBoardFull());
    }

    @Test
    public void testCheckWinRow() {
        board.makeMove(0, 0, Player.X);
        board.makeMove(0, 1, Player.X);
        board.makeMove(0, 2, Player.X);
        assertEquals(Player.X, board.checkWin());
    }

    @Test
    public void testCheckWinColumn() {
        board.makeMove(0, 0, Player.O);
        board.makeMove(1, 0, Player.O);
        board.makeMove(2, 0, Player.O);
        assertEquals(Player.O, board.checkWin());
    }

    @Test
    public void testCheckWinDiagonal() {
        board.makeMove(0, 0, Player.X);
        board.makeMove(1, 1, Player.X);
        board.makeMove(2, 2, Player.X);
        assertEquals(Player.X, board.checkWin());

        board = new Board();
        board.makeMove(0, 2, Player.O);
        board.makeMove(1, 1, Player.O);
        board.makeMove(2, 0, Player.O);
        assertEquals(Player.O, board.checkWin());
    }

    @Test
    public void testNoWin() {
        board.makeMove(0, 0, Player.X);
        board.makeMove(0, 1, Player.O);
        board.makeMove(0, 2, Player.X);
        assertEquals(Player.NONE, board.checkWin());
    }
    
    @Test
    public void testTie() {
        board.makeMove(0,0,Player.X);
        board.makeMove(0,1,Player.O);
        board.makeMove(0,2,Player.X);
        board.makeMove(1,0,Player.X);
        board.makeMove(1,1,Player.O);
        board.makeMove(1,2,Player.O);
        board.makeMove(2,0,Player.O);
        board.makeMove(2,1,Player.X);
        board.makeMove(2,2,Player.X);
        assertTrue(board.isBoardFull());
        assertEquals(Player.NONE, board.checkWin());
    }

    @Test
    public void testCopyConstructor() {
        board.makeMove(1, 1, Player.X);
        Board copiedBoard = new Board(board);
        assertEquals(Player.X, copiedBoard.getCell(1, 1));
        assertTrue(copiedBoard.makeMove(0, 0, Player.O));
        // Ensure original board is not affected
        assertTrue(board.isCellEmpty(0, 0));
    }
    
    @Test
    public void testGetCellInvalid() {
        assertEquals(Player.NONE, board.getCell(-1, 0));
    }

    @Test
    public void testPrintBoard() {
        // This is tricky to test without capturing stdout.
        // For coverage, we just call it.
        board.printBoard();
    }
}
