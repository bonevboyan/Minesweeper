package game.objects;

public class Field {
    private final int HEIGHT = 10;
    private final int WIDTH = 15;
    private final int MIN_BOMB_COUNT = 1;
    private final int MAX_BOMB_COUNT = HEIGHT * WIDTH;

    private final Coordinate[] adjacents = {
            new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(0, 1),
            new Coordinate(-1, 1), new Coordinate(-1, 0), new Coordinate(-1, -1),
            new Coordinate(0, -1), new Coordinate(1, -1)
    };

    private Cell[][] cells;
    private int bombCount = 10;
    private boolean hasLost = false;
    private int closedCells = HEIGHT * WIDTH;

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

    public boolean hasLost() {
        return hasLost;
    }

    public boolean hasWon() {
        return closedCells == bombCount;
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

    public void play(int y, int x) throws IllegalArgumentException, TypeNotPresentException{
        if (!isInHeightLimits(y) || !isInWidthLimits(x)) {
            throw new IllegalArgumentException("Cell non-existent");
        }

        if (cells[y][x].isFlagged) {
            throw new IllegalArgumentException("Trying to open a flagged cell");
        }

        if (cells[y][x] instanceof BombCell) {
            hasLost = true;

            for (int h = 0; h < HEIGHT; h++) {
                for (int w = 0; w < WIDTH; w++) {
                    cells[h][w].isOpened = true;
                }
            }

            return;
        }

        if (!(cells[y][x] instanceof ClearCell currCell)) {
            throw new TypeNotPresentException("Internal error. Cell of unrecognized type", new Throwable());
        }

        if (!currCell.isOpened) {
            currCell.isOpened = true;
            closedCells--;

            if (currCell.getAdjacentBombs() == 0) {
                openAllAdj(y, x);
            }

        } else {
            if (getAdjacentFlagsCount(y, x) != currCell.getAdjacentBombs()) {
                return;
            }

            openAllAdj(y, x);
        }
    }

    private void openAllAdj(int y, int x) {
        for (Coordinate coord : adjacents) {
            if (isInHeightLimits(y + coord.y) && isInWidthLimits(x + coord.x)
                    && !cells[y + coord.y][x + coord.x].isFlagged
                    && !cells[y + coord.y][x + coord.x].isOpened) {
                play(y + coord.y, x + coord.x);
            }
        }
    }

    private boolean isInHeightLimits(int height) {
        return height < HEIGHT && height >= 0;
    }

    private boolean isInWidthLimits(int width) {
        return width < WIDTH && width >= 0;
    }

    private int getAdjacentFlagsCount(int y, int x) {
        int adjFlags = 0;

        for (Coordinate coord : adjacents) {
            if (isInHeightLimits(y + coord.y) && isInWidthLimits(x + coord.x)
                    && cells[y + coord.y][x + coord.x].isFlagged) {
                adjFlags++;
            }
        }

        return adjFlags;
    }

}
