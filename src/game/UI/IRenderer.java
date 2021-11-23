package game.UI;

import game.objects.Cell;
import game.objects.Field;

public interface IRenderer {
    String cellFactory(Cell cell) throws Exception;

    void printField(Field field) throws Exception;

    void printSelect();

    void printOptions();

    void printSetup();

    void printEnd(Field field) throws Exception;

    void printException(Exception exception);
}
