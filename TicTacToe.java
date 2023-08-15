public class TicTacToe
{
    public static void main(String[] args) {
        System.out.println("WELCOME TO THE TIC TAC TOE GAME!");
        System.out.println();
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