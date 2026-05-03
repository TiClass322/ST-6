package com.mycompany.app;

import java.util.Scanner;

public class Game {
    private Board board;
    private Minimax minimax;
    private Player humanPlayer;
    private Player aiPlayer;
    private Player currentPlayer;

    public Game(Player human, Player ai) {
        board = new Board();
        minimax = new Minimax();
        this.humanPlayer = human;
        this.aiPlayer = ai;
        this.currentPlayer = Player.X; 
    }
    
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("You are " + humanPlayer + ", AI is " + aiPlayer);
        board.printBoard();

        while (getWinner() == Player.NONE && !board.isBoardFull()) {
            if (currentPlayer == humanPlayer) {
                humanTurn(scanner);
            } else {
                aiTurn();
            }

            board.printBoard();
            if (getWinner() != Player.NONE || board.isBoardFull()) {
                break;
            }
            
            switchPlayer();
        }
        
        Player winner = getWinner();
        if (winner != Player.NONE) {
            if (winner == humanPlayer) {
                System.out.println("Congratulations! You won!");
            } else {
                System.out.println("AI wins! Better luck next time.");
            }
        } else {
            System.out.println("It's a tie!");
        }
        scanner.close();
    }
    
    public Board getBoard() {
        return board;
    }

    public Player getWinner() {
        return board.checkWin();
    }

    public void humanTurn(Scanner scanner) {
        System.out.println("Your turn (" + humanPlayer + "). Enter row (0-2) and column (0-2):");
        int row, col;
        while (true) {
            try {
                System.out.print("Row: ");
                row = scanner.nextInt();
                System.out.print("Col: ");
                col = scanner.nextInt();

                if (board.makeMove(row, col, humanPlayer)) {
                    break;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter numbers for row and column.");
                scanner.next(); 
            }
        }
    }

    public void aiTurn() {
        System.out.println("AI's turn (" + aiPlayer + ")...");
        Board boardCopy = new Board(board);
        Move bestMove = minimax.findBestMove(boardCopy, aiPlayer);
        board.makeMove(bestMove.row, bestMove.col, aiPlayer);
        System.out.println("AI chose row " + bestMove.row + ", col " + bestMove.col);
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
