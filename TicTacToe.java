import java.util.HashMap;
import java.util.Scanner;

record Player(String name, char symbol) {
}

public class TicTacToe {
    static char[][] board;
    static int filledBoxesCount;
    static char result;
    static HashMap<Character, Player> map;

    public static void main(String[] args) {
        board = new char[3][3];
        filledBoxesCount = 0;
        String name = "";
        char symbol = ' ';
        map = new HashMap<>();

        System.out.println("\nWELCOME TO THE TIC TAC TOE GAME!");
        System.out.println("This a Simple Terminal Based Tic Tac Toe game for 2 players.");
        System.out.println("============================================================\n");

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter name of first player: ");
            name = sc.nextLine();
            Player p1 = new Player(name, 'X');

            System.out.print("Enter name of second player: ");
            name = sc.nextLine();
            Player p2 = new Player(name, 'O');

            map.put(p1.symbol(), p1);
            map.put(p2.symbol(), p2);

            System.out.println();
            System.out.println(String.format("%s will play as %c", p1.name(), p1.symbol()));
            System.out.println(String.format("%s will play as %c", p2.name(), p2.symbol()));

            while (true) {
                System.out.println("\nThe board: \n");
                displayBoard();

                if (checkGameDrawn()) {
                    System.out.println("\n============================================================\n");
                    System.out.println("It's a draw!");
                    return;
                } else if (checkGameWon()) {
                    System.out.println("\n============================================================\n");
                    getResult();
                    return;
                } else {
                    symbol = (filledBoxesCount % 2 == 0) ? p1.symbol() : p2.symbol();
                    name = (filledBoxesCount % 2 == 0) ? p1.name() : p2.name();
                    int r = -1, c = -1;

                    System.out.println(String.format("\n%s's Turn: ", name));

                    while (r < 0 && c < 0) {
                        try {
                            System.out.print(
                                    String.format("Enter position to put %c (row,coloumn separated by space): ",
                                            symbol));
                            r = Integer.parseInt(sc.next()) - 1;
                            c = Integer.parseInt(sc.next()) - 1;

                            if(r<0 || r>=3 || c<0 || c>=3)
                            {
                                System.out.println("Invalid position!\nEnter again.");
                                r=c=-1;
                            }
                        } catch (NumberFormatException e) {
                            // Handle NumberFormatException
                            System.err.println("Enter valid Number!");
                        }
                    }

                    if (board[r][c] != '\u0000') {
                        System.out.println("\nInvalid Move!\nEnter Again:");
                        continue;
                    }

                    board[r][c] = symbol;
                }
                filledBoxesCount++;
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    private static void displayBoard() {
        System.out.println("====+===+====");
        for (char[] row : board) {
            System.out.print("|");
            for (char elem : row) {
                if (elem == '\u0000')
                    System.out.print("   |");
                else
                    System.out.print(" " + elem + " |");
            }
            System.out.println("\n====+===+====");
        }
    }

    private static boolean checkGameWon() {
        for (int i = 0; i < 3; i++) {
            // Check if the symbols in a column is same
            if (board[0][i] != '\u0000' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                result = board[0][i];
                return true;
            }

            // Check if the symbols in a row is same
            if (board[i][0] != '\u0000' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                result = board[i][0];
                return true;
            }
        }

        if (board[1][1] != '\u0000') {
            // Check if the symbols in a right diagonal is same
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                result = board[0][0];
                return true;
            }

            // Check if the symbols in a left diagonal is same
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                result = board[0][2];
                return true;
            }

        }
        return false;
    }

    private static boolean checkGameDrawn() {

        if (filledBoxesCount == 9)
            return true;

        return false;
    }

    private static void getResult() {
        System.out.println(String.format("%s Wins!", map.get(result).name()));
    }
}
