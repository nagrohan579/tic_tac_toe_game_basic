import java.util.HashMap;
import java.util.Scanner;
class Player {
    private String name;
    private char symbol;

    Player() {
        name = "";
        symbol = ' ';
    }

    Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    String getName()
    {
        return name;
    }

    char getSymbol()
    {
        return symbol;
    }
}

public class TicTacToe {
    static char[][] board;
    static int filledBoxesCount;
    static char result;
    static HashMap<Character,Player> map;

    public static void main(String[] args) {
        board = new char[3][3];
        filledBoxesCount = 0;
        String name= "";
        char symbol = ' '; 
        map = new HashMap<>();

        System.out.println("\nWELCOME TO THE TIC TAC TOE GAME!");
        System.out.println("This a Simple Terminal Based Tci Tac Toe game for 2 players.");
        System.out.println("============================================================\n");


        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter name of first player: ");
            name = sc.nextLine();
            Player p1 = new Player(name, 'X');
            
            System.out.print("Enter name of second player: ");
            name = sc.nextLine();
            Player p2 = new Player(name, 'O');
            
            map.put(p1.getSymbol(), p1);
            map.put(p2.getSymbol(), p2);

            System.out.println();
            System.out.println(String.format("%s will play as %c", p1.getName(), p1.getSymbol()));
            System.out.println(String.format("%s will play as %c", p2.getName(), p2.getSymbol()));

            while(true) {
                System.out.println("\nThe board: \n");
                for (char[] row : board) {
                    System.out.print("|");
                    for (char elem : row)
                    {
                        if(elem == '\u0000')
                            System.out.print("   |");
                        else
                            System.out.print(" " + elem + " |");

                    }
                    System.out.println();
                }
                
                if(checkGameDrawn())
                {
                    System.out.println("\n============================================================\n");
                    System.out.println("It's a draw!");
                    return;
                }
                else if(checkGameWon())
                {
                    System.out.println("\n============================================================\n");
                    getResult();
                    return;
                }
                else
                {
                    symbol = (filledBoxesCount % 2 == 0) ? p1.getSymbol() : p2.getSymbol();
                    name = (filledBoxesCount % 2 == 0) ? p1.getName() : p2.getName();
                    int r = 0, c = 0;
                    
                    System.out.println(String.format("\n%s's Turn: ", name));
                    System.out.print(String.format("Enter position to put %c: ", symbol));
                    r = Integer.parseInt(sc.next()) - 1;
                    c = Integer.parseInt(sc.next()) - 1;

                    if(board[r][c] != '\u0000')
                    {
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

    private static boolean checkGameWon() {
        for(int i=0; i<3; i++)
        {
            // Check if the symbols in a column is same
            if(board[0][i] != '\u0000' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
            {
                result = board[0][i];
                return true;   
            }

            // Check if the symbols in a row is same
            if(board[i][0] != '\u0000' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
            {
                result = board[i][0];
                return true;   
            }
        }
        
        if(board[1][1] != '\u0000')
        {
            // Check if the symbols in a right diagonal is same
            if(board[0][0] == board[1][1] && board[1][1] == board[2][2])
            {
                result = board[0][0];
                return true;
            }
    
            // Check if the symbols in a left diagonal is same
            if(board[0][2] == board[1][1] && board[1][1] == board[2][0])
            {
                result = board[0][2];
                return true;
            }

        }
        return false;
    }

    private static void getResult() {
        System.out.println(String.format("%s Wins!", map.get(result).getName()));
    }

    private static boolean checkGameDrawn() {
        
        if(filledBoxesCount == 9)
            return true;
        
        return false;
    }
}
