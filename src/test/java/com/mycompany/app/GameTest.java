package com.mycompany.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class GameTest {

    @Test
    public void testGameInitialization() {
        Game game = new Game(Player.X, Player.O);
        assertEquals(Player.X, game.getCurrentPlayer());
        assertNotNull(game.getBoard());
        assertEquals(Player.NONE, game.getWinner());
    }

    @Test
    public void testSwitchPlayer() {
        Game game = new Game(Player.X, Player.O);
        assertEquals(Player.X, game.getCurrentPlayer());
        game.switchPlayer();
        assertEquals(Player.O, game.getCurrentPlayer());
        game.switchPlayer();
        assertEquals(Player.X, game.getCurrentPlayer());
    }

    @Test
    public void testAiTurn() {
        Game game = new Game(Player.O, Player.X);
        game.aiTurn();
        assertFalse(game.getBoard().isBoardFull());
        long filledCells = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(game.getBoard().getCell(i, j) != Player.NONE) {
                    filledCells++;
                }
            }
        }
        assertEquals(1, filledCells);
    }

    @Test
    public void testGetWinner() {
        Game game = new Game(Player.X, Player.O);
        game.getBoard().makeMove(0, 0, Player.X);
        game.getBoard().makeMove(0, 1, Player.X);
        game.getBoard().makeMove(0, 2, Player.X);
        assertEquals(Player.X, game.getWinner());
    }
    
    @Test
    public void testHumanTurn() {
        Game game = new Game(Player.X, Player.O);
        String input = "0\n0\n"; // row 0, col 0
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game.humanTurn(new java.util.Scanner(System.in));
        assertEquals(Player.X, game.getBoard().getCell(0, 0));
        

        System.setIn(System.in);
    }
    
    @Test
    public void testHumanTurnInvalidThenValid() {
        Game game = new Game(Player.X, Player.O);
        String input = "4\n4\n0\n0\n"; 
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game.humanTurn(new java.util.Scanner(System.in));
        assertEquals(Player.X, game.getBoard().getCell(0, 0));

        System.setIn(System.in);
    }
}
