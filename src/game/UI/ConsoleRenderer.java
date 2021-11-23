package game.UI;

import game.objects.Cell;
import game.objects.ClearCell;
import game.objects.Field;

public class ConsoleRenderer implements IRenderer {
    @Override
    public String cellFactory(Cell cell) throws Exception {
        if (!cell.isOpened()) {
            if (cell.isFlagged()) {
                return "F";
            } else {
                return "#";
            }
        } else if (cell.getClass().toString().equals("class game.objects.ClearCell")) {
            int number = ((ClearCell) cell).getAdjacentBombs();
            return number == 0 ? "." : String.valueOf(number);
        } else if (cell.getClass().toString().equals("class game.objects.BombCell")) {
            return "*";
        } else {
            throw new Exception("Invalid object. Supposed to be Cell.");
        }
    }

    @Override
    public void printField(Field field) throws Exception {
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
    public void printSelect() {
        System.out.print("Select x and y of the cell you want to choose: ");
    }

    @Override
    public void printOptions() {
        System.out.print("""
                Choose 0-2:
                0. Open cell.
                1. Flag/unflag cell.
                2. Back.
                """);
    }

    @Override
    public void printSetup() {
        System.out.println("How many bombs do you want in the game? ");
    }

    @Override
    public void printEnd(Field field) throws Exception {
        String endText = field.hasWon() ? "Congratulations, you won! \n" : "That's a bomb! Game over! \n";
        System.out.println(endText);
        printField(field);
        System.out.println("Do you want to play again? (y/n) ");
    }

    @Override
    public void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
