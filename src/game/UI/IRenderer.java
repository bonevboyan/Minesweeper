package game.UI;

import game.data.User;
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

    void displayLeaderboard(List<User> users);

    void displayPersonalScores(List<User> users);

    void displayUsernameChoice();

    void displayScoresOptions();
}
