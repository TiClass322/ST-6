package com.mycompany.app;

public class Program {
    public static void main(String[] args) {
        // Human is X, AI is O. X starts.
        Game game = new Game(Player.X, Player.O);
        game.startGame();
    }
}
