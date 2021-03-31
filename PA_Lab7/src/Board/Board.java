package Board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final ReentrantLock lock = new ReentrantLock(true);
    final private List<Token> tokens;
    List<String> threadNames;
    private String currentThreadName;
    private int tokensAvailable;

    public Board(int index) {
        this.currentThreadName = null;
        this.threadNames = new ArrayList<>();
        this.tokensAvailable = index*index;
        tokens = new ArrayList<>();
        for (int i = 0; i <= index - 1; i++) {
            for (int j = 0; j <= index - 1; j++) {
                if (i != j) {
                    tokens.add(new Token(i, j, (i * 10 + j)));
                }
            }
        }
    }

    public void setCurrentThreadName(String currentThreadName) {
        this.currentThreadName = currentThreadName;
    }

    public void setThreadNames(List<String> threadNames) {
        this.threadNames = threadNames;
    }

    public int getTokensAvailable() {
        return tokensAvailable;
    }

    public synchronized Token getExtraction() {
        boolean skipped = false;
        lock.lock();
        try {
            if (!Thread.currentThread().getName().equals(this.currentThreadName)) {
                skipped = true;
                lock.unlock();
                return null;
            } else {
                if (this.threadNames.indexOf(this.currentThreadName) == this.threadNames.size() - 1)
                    this.currentThreadName = this.threadNames.get(0);
                else
                    this.currentThreadName = this.threadNames.get(this.threadNames.indexOf(this.currentThreadName) + 1);
            }
            this.tokensAvailable--;
            if (tokensAvailable > 0) {
                for (Token x : this.tokens) {
                    if (x.isAvailable()) {
                        x.setAvailable(false);
                        return x;
                    }
                }
            }
            return null;
        } finally {
            if (!skipped)
                lock.unlock();
        }
    }
}
