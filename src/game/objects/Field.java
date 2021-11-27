package game.objects;

public class Field {
    private int height;
    private int width;
    private final Timer TIMER = new Timer();

    private final Coordinate[] adjacents = {
            new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(0, 1),
            new Coordinate(-1, 1), new Coordinate(-1, 0), new Coordinate(-1, -1),
            new Coordinate(0, -1), new Coordinate(1, -1)
    };

    private Cell[][] cells;
    private int bombCount;
    private boolean hasLost = false;
    private int closedCells;

    public Field() {
        this(1);
    }

    public Field(int difficulty) {

        MinesweeperDifficulty minesweeperDifficulty = Difficulties.none;

        switch (difficulty) {
            case 0 -> minesweeperDifficulty = Difficulties.easy;
            case 1 -> minesweeperDifficulty = Difficulties.medium;
            case 2 -> minesweeperDifficulty = Difficulties.expert;
            default -> { }
        }

        this.height = minesweeperDifficulty.height;
        this.width = minesweeperDifficulty.width;
        this.closedCells = this.height * this.width;
        this.bombCount = minesweeperDifficulty.bombCount;

        TIMER.startTimer();
        initializeBoard();
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public long getTime() { 
    	return TIMER.getElapsedSeconds();
    }
    
    public boolean hasLost() {
        return hasLost;
    }

    public boolean hasWon() {
        return closedCells == bombCount;
    }

    public boolean hasEnded() {
        return hasLost || hasWon();
    }

    public void initializeBoard() {
    
        cells = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new ClearCell();
            }
        }

        for (int i = 0; i < bombCount; ) {
            int randomX = (int) (Math.random() * width);
            int randomY = (int) (Math.random() * height);

            if (cells[randomY][randomX] instanceof ClearCell) {
                cells[randomY][randomX] = new BombCell();
                i++;
                for (Coordinate coord : adjacents) {
                    if (isInBorderLimits(randomY + coord.y, randomX + coord.x)
                            && (cells[randomY + coord.y][randomX + coord.x] instanceof ClearCell clearCell)) {
                        clearCell.increaseAdjacentBombsByOne();
                    }
                }
            }
        }
    }

    public void play(int y, int x) throws IllegalArgumentException, TypeNotPresentException {
        if (!isInHeightLimits(y) || !isInWidthLimits(x)) {
            throw new IllegalArgumentException("Cell non-existent");
        }

        if (cells[y][x].isFlagged) {
            throw new IllegalArgumentException("Trying to open a flagged cell");
        }

        if (cells[y][x] instanceof BombCell) {
            hasLost = true;
            TIMER.stopTimer();
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    if (cells[h][w] instanceof BombCell cell) {
                        cell.isOpened = true;
                    }
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
            if(hasWon()) {
            	TIMER.stopTimer();
            }
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
            if (isInBorderLimits(y + coord.y, x + coord.x)
                    && !cells[y + coord.y][x + coord.x].isFlagged
                    && !cells[y + coord.y][x + coord.x].isOpened) {
                play(y + coord.y, x + coord.x);
            }
        }
    }

    private int getAdjacentFlagsCount(int y, int x) {
        int adjFlags = 0;

        for (Coordinate coord : adjacents) {
            if (isInBorderLimits(y + coord.y, x + coord.x)
                    && cells[y + coord.y][x + coord.x].isFlagged) {
                adjFlags++;
            }
        }

        return adjFlags;
    }

    public void changeFlagCell(int y, int x) throws IllegalArgumentException {

        if (!isInBorderLimits(y, x)) {
            throw new IllegalArgumentException("Cell non-existent");
        }

        cells[y][x].isFlagged = !cells[y][x].isFlagged;
    }

    private boolean isInHeightLimits(int height) {
        return height < this.height && height >= 0;
    }

    private boolean isInWidthLimits(int width) {
        return width < this.width && width >= 0;
    }

    private boolean isInBorderLimits(int height, int width) {
        return isInHeightLimits(height) && isInWidthLimits(width);
    }

}
