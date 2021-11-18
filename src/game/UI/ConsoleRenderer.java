package game.UI;

import game.objects.Cell;
import game.objects.Field;

public class ConsoleRenderer implements IRenderer {
    @Override
    public void printCell(Cell cell) {

    }

    @Override
    public void printField(Field field) {
        for (int i = 0; i < field.getHEIGHT(); i++) {
            for (int j = 0; j < field.getWIDTH(); j++) {
                
            }
        }
    }

    @Override
    public void printOptions() {

    }
}
