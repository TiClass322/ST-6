package com.mycompany.app;

public class Board {
    private Player[][] board;
    private static final int SIZE = 3;

    public Board() {
        board = new Player[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Player.NONE;
            }
        }
    }

    public Board(Board other) {
        this(); // Initialize new board
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = other.board[i][j];
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }
        return board[row][col] == Player.NONE;
    }

    public boolean makeMove(int row, int col, Player player) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || !isCellEmpty(row, col)) {
            return false;
        }
        board[row][col] = player;
        return true;
    }

    public Player checkWin() {
        // Check rows
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != Player.NONE && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }

        // Check columns
        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] != Player.NONE && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return board[0][j];
            }
        }

        // Check diagonals
        if (board[0][0] != Player.NONE && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != Player.NONE && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return Player.NONE; // No winner yet
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Player.NONE) {
                    return false;
                }
            }
        }
        return true;
    }

    public Player getCell(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return Player.NONE;
        }
        return board[row][col];
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                char symbol = ' ';
                if (board[i][j] == Player.X) {
                    symbol = 'X';
                } else if (board[i][j] == Player.O) {
                    symbol = 'O';
                }
                System.out.print(symbol + " | ");
            }
            System.out.println("\n-------------");
        }
    }
}
