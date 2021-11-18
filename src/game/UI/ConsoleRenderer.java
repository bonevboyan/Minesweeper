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
        for (int i = 0; i < field.getHEIGHT(); i++) {
            for (int j = 0; j < field.getWIDTH(); j++) {
                System.out.printf("%s ", cellFactory(field.getCells()[i][j]));
            }
            System.out.println();
        }
    }

    @Override
    public void printSelect() {
        System.out.print("Select x and y of the cell you want to choose: ");
    }

    @Override
    public void printOptions() {
        System.out.println("""
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
}
