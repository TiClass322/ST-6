package com.mycompany.app;

public class Minimax {

    public Move findBestMove(Board board, Player aiPlayer) {
        int bestVal = Integer.MIN_VALUE;
        Move bestMove = new Move(-1, -1);

        Player opponent = (aiPlayer == Player.X) ? Player.O : Player.X;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    board.makeMove(i, j, aiPlayer);
                    int moveVal = minimax(board, 0, false, aiPlayer, opponent);
                    board.makeMove(i, j, Player.NONE); // Undo the move

                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMax, Player aiPlayer, Player opponent) {
        Player winner = board.checkWin();

        if (winner == aiPlayer) {
            return 10 - depth;
        }
        if (winner == opponent) {
            return -10 + depth;
        }
        if (board.isBoardFull()) {
            return 0;
        }

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isCellEmpty(i, j)) {
                        board.makeMove(i, j, aiPlayer);
                        best = Math.max(best, minimax(board, depth + 1, !isMax, aiPlayer, opponent));
                        board.makeMove(i, j, Player.NONE);
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isCellEmpty(i, j)) {
                        board.makeMove(i, j, opponent);
                        best = Math.min(best, minimax(board, depth + 1, !isMax, aiPlayer, opponent));
                        board.makeMove(i, j, Player.NONE);
                    }
                }
            }
            return best;
        }
    }
}
