package Players;

import Board.Board;
import Board.Token;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private final List<Token> myTokens;
    private final Board gameBoard;

    public Player(String name, Board gameBoard) {
        this.gameBoard = gameBoard;
        this.myTokens = new ArrayList<>();
        this.name = name;
    }

    @Override
    public void run() {
        while (this.gameBoard.getTokensAvailable() > 0) {
            Token tempToken = this.gameBoard.getExtraction();
            if (tempToken != null) {
                this.myTokens.add(tempToken);
                System.out.println("Player " + this.name + " has extracted the token: " + tempToken);
            }
        }
    }
}
