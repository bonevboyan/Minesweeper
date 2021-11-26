package game.UI;

import game.objects.Cell;
import game.objects.ClearCell;
import game.objects.Field;

public class ConsoleRenderer implements IRenderer {
    private final String CLOSED_CELL = "\u2584";
    private final String EMPTY_CELL = ".";
    private final String BOMB_CELL = "\u2297";
    private final String FLAGGED_CELL = "F";

    @Override
    public String cellFactory(Cell cell) throws Exception {
        if (!cell.isOpened()) {
            if (cell.isFlagged()) {
                return FLAGGED_CELL;
            } else {
                return CLOSED_CELL;
            }
        } else if (cell.getClass().toString().equals("class game.objects.ClearCell")) {
            int number = ((ClearCell) cell).getAdjacentBombs();
            return number == 0 ? EMPTY_CELL : String.valueOf(number);
        } else if (cell.getClass().toString().equals("class game.objects.BombCell")) {
            return BOMB_CELL;
        } else {
            throw new TypeNotPresentException("Invalid object. Supposed to be Cell.", new Throwable());
        }
    }

    @Override
    public void displayField(Field field) throws Exception {
        System.out.print("  ");
        for (int i = -1; i < field.getHEIGHT(); i++) {
            for (int j = -1; j < field.getWIDTH(); j++) {
                if (i == -1) {
                    if (j != -1) {
                        System.out.printf("%2d ", j);
                    }
                } else if (j == -1) {
                    System.out.print(i + "  ");
                } else {
                    System.out.printf("%s  ", cellFactory(field.getCells()[i][j]));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void displaySelect() {
        System.out.print("Select x and y of the cell you want to choose: ");
    }

    @Override
    public void displayOptions() {
        System.out.print("""
                Choose 0-2:
                0. Open cell.
                1. Flag/unflag cell.
                2. Back.
                """);
    }

    @Override
    public void displaySetup() {
        System.out.println("How many bombs do you want in the game? ");
    }

    @Override
    public void displayEnd(Field field, int wins, int losses) throws Exception {
        displayField(field);

        String endText = field.hasWon() ? "Congratulations, you won! \n": "That's a bomb! Game over! \n";

        System.out.println(endText);
        System.out.println("Time wasted: " + formatTime(field.getTime()));
        System.out.printf("W/L Ratio: %d:%d\n\n", wins, losses);

        System.out.println("Do you want to play again? (y/n) ");
    }

    private String formatTime(long seconds) {
    	return String.format("%02d:%02d", seconds/60, seconds%60);
    }

    @Override
    public void displayException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
