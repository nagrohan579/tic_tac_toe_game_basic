import java.util.Scanner;

public class TicTacToe
{
    static char[][] board;
    static int filledBoxesCount; 

    TicTacToe()
    {
        board = new char[3][3];
        filledBoxesCount = 0;
    }

    public static void main(String[] args) {
        System.out.println("\nWELCOME TO THE TIC TAC TOE GAME!");
        System.out.println("This a Simple Terminal Based Tci Tac Toe game for 2 players.");
        System.out.println("============================================================\n");

        Player p1 = new Player(); 
        Player p2 = new Player(); 

        try(Scanner sc = new Scanner(System.in))
        {
            System.out.print("Enter name of first player: ");
            p1.name = sc.nextLine();
            p1.symbol = 'X';
            System.out.print("Enter name of second player: ");
            p2.name = sc.nextLine();
            p2.symbol = 'O';
        }
        
        System.out.println();
        System.out.println(String.format("%s will play as %c", p1.name,p1.symbol));
        System.out.println(String.format("%s will play as %c", p2.name, p2.symbol));
        
    }
}

class Player
{
    String name;
    char symbol;

    Player()
    {
        name = "";
        symbol = ' ';
    }

    Player(String name)
    {
        this.name = name;
        symbol = ' ';
    }
}