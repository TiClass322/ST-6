package com.mycompany.app;

public class Program {
    public static void main(String[] args) {
        Game game = new Game(Player.X, Player.O);
        game.startGame();
    }
}
