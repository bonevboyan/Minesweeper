package game.UI;

import game.objects.Cell;
import game.objects.Field;

public interface IRenderer {
    String cellFactory(Cell cell) throws Exception;

    void displayField(Field field) throws Exception;

    void displaySelect();

    void displayOptions();

    void displaySetup();

    void displayEnd(Field field, int wins, int losses) throws Exception;

    void displayException(Exception exception);
}
