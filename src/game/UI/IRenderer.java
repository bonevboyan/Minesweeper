package game.UI;

import game.data.Record;
import game.objects.Cell;
import game.objects.Field;

import java.util.List;

public interface IRenderer {
    String cellFactory(Cell cell) throws Exception;

    void displayField(Field field) throws Exception;

    void displaySelect();

    void displayOptions();

    void displaySetup();

    void displayEnd(Field field, int wins, int losses, long time) throws Exception;

    void displayException(Exception exception);

    void displayLeaderboard(List<Record> records);

    void displayPersonalScores(List<Record> records);

    void displayUsernameChoice();

    void displayScoresOptions();
}
