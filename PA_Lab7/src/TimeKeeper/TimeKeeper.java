package TimeKeeper;

import Board.Board;

import static java.lang.Thread.sleep;

public class TimeKeeper implements Runnable {
    private final Board gameBoard;
    private final long roundDuration;
    private long startTime = -1;

    public TimeKeeper(Board gameBoard, long roundDuration) {
        this.gameBoard = gameBoard;
        this.roundDuration = roundDuration;
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public void run() {
        this.startTime = System.currentTimeMillis();
        try {
            sleep(this.roundDuration);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.gameBoard.setTokensAvailable(0);
        System.out.println("\n[TimeKeeper] The time limit (" + this.roundDuration + " millisecond(s)) for this round has finished! Round ending after current Player makes its choice!...\n");
        System.out.print("cmd>");
        // Motivul pentru care am pus cmd> este ca singurul scenariu in care acest mesaj chiar este vazut este atunci cand un user trebuie sa aleaga si programul asteapta un input de la acesta. Daca este un meci de boti, mesajul nici nu se va vedea!
    }
}
