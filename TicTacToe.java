import java.util.InputMismatchException;
import java.util.Scanner;


class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }
}

public class TicTacToe {

    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    static Scanner sc = new Scanner(System.in);
    static boolean gameOver = false;

    public static void main(String[] args) {

        char currentPlayer = 'X';

        System.out.println("=== TIC TAC TOE GAME (With Exception Handling) ===");

        while (!gameOver) {

            printBoard();

            try {
                int row = getInput("Enter row (0-2): ");
                int col = getInput("Enter column (0-2): ");

                makeMove(row, col, currentPlayer);

                if (checkWin()) {
                    printBoard();
                    System.out.println("🎉 Player " + currentPlayer + " Wins!");
                    gameOver = true;
                } 
                else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a Draw!");
                    gameOver = true;
                } 
                else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }

            } catch (InvalidMoveException e) {
                System.out.println("⚠ " + e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println("⚠ Game State Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }

        sc.close();
    }

    
    static int getInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = sc.nextInt();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                sc.next(); 
            }
        }
    }

    
    static void makeMove(int row, int col, char player) throws InvalidMoveException {

        if (gameOver) {
            throw new IllegalStateException("Game is already over!");
        }

        try {
            if (board[row][col] != ' ') {
                throw new InvalidMoveException("Cell already occupied! Try another.");
            }

            board[row][col] = player;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidMoveException("Row and Column must be between 0 and 2.");
        }
    }

    
    static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    
    static boolean checkWin() {

        for (int i = 0; i < 3; i++)
            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2])
                return true;

        for (int i = 0; i < 3; i++)
            if (board[0][i] != ' ' &&
                board[0][i] == board[1][i] &&
                board[1][i] == board[2][i])
                return true;

        if (board[0][0] != ' ' &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2])
            return true;

        if (board[0][2] != ' ' &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0])
            return true;

        return false;
    }

    
    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;

        return true;
    }
}
