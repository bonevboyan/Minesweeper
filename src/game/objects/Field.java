package game.objects;

public class Field {
    private final int HEIGHT = 10;
    private final int WIDTH = 15;
    private final int MIN_BOMB_COUNT = 1;
    private final int MAX_BOMB_COUNT = 150;


    private Cell[][] cells;
    private int bombCount = 10;
    private boolean hasEnded = false;

    public Field() {
        initializeBoard();
    }

    public Field(int bombCount) {
        this.setBombCount(bombCount);
        initializeBoard();
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombCount) {
        if (MIN_BOMB_COUNT < bombCount && bombCount < MAX_BOMB_COUNT) {
            this.bombCount = bombCount;
        } else {
            this.bombCount = 10;
        }
    }

    public boolean isHasEnded() {
        return hasEnded;
    }

    public void initializeBoard() {
        cells = new Cell[HEIGHT][WIDTH];

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j] = new ClearCell();
            }
        }

        for (int i = 0; i < bombCount; ) {
            int randomX = (int) (Math.random() * WIDTH);
            int randomY = (int) (Math.random() * HEIGHT);

            if (cells[randomY][randomX].getClass().toString().equals("class game.objects.ClearCell")) {
                cells[randomY][randomX] = new BombCell();
                i++;
            }
        }
    }

    public void play(int y, int x) throws IllegalArgumentException{
        if (0 > y || y > HEIGHT || 0 > x || x > WIDTH) {
            throw new IllegalArgumentException("Cell non-existent");
        }

        if (cells[y][x].isFlagged || cells[y][x].isOpened) {
            return;
        }


    }
}
