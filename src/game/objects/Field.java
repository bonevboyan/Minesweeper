package game.objects;

public class Field {
    private final int HEIGHT = 10;
    private final int WIDTH = 15;
    private Cell[][] cells;

    public Field() {
        initiliazeBoard(10);
    }

    public Field(int bombCount) {
        initiliazeBoard(bombCount);
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

    public void initiliazeBoard(int bombCount) {
        cells = new Cell[HEIGHT][WIDTH];

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j] = new ClearCell(i, j);
            }
        }

        for (int i = 0; i < bombCount; ) {
            int randomX = (int) (Math.random() * WIDTH);
            int randomY = (int) (Math.random() * HEIGHT);

            if (cells[randomY][randomX].getClass().toString().equals("class game.objects.ClearCell")) {
                cells[randomY][randomX] = new BombCell(randomY, randomX);
                i++;
            }
        }
    }
}
