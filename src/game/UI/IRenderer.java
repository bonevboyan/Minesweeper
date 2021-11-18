package game.UI;

import game.objects.Cell;
import game.objects.Field;

public interface IRenderer {
    void printCell(Cell cell);

    void printField(Field field);

    void printOptions();
}
