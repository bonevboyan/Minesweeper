package game.UI;

import game.data.Record;
import game.objects.*;

import java.util.List;

public class ConsoleRenderer implements IRenderer {
    private final String CLOSED_CELL = "\u2584";
    private final String EMPTY_CELL = ".";
    private final String BOMB_CELL = "\u2297";
    private final String FLAGGED_CELL = "F";

    @Override
    public String cellFactory(Cell cell) {
        if (!cell.isOpened()) {
            if (cell.isFlagged()) {
                return FLAGGED_CELL;
            } else {
                return CLOSED_CELL;
            }
        } else if (cell instanceof ClearCell) {
            int number = ((ClearCell) cell).getAdjacentBombs();
            return number == 0 ? EMPTY_CELL : String.valueOf(number);
        } else if (cell instanceof BombCell) {
            return BOMB_CELL;
        } else {
            throw new TypeNotPresentException("Invalid object. Supposed to be Cell.", new Throwable());
        }
    }

    @Override
    public void displayField(Field field) {
        System.out.print("   ");
        for (int i = -1; i < field.getHeight(); i++) {
            for (int j = -1; j < field.getWidth(); j++) {
                if (i == -1) {
                    if (j != -1) {
                        System.out.printf("%2d ", j);
                    }
                } else if (j == -1) {
                    System.out.printf("%2d  ", i);
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
        System.out.println("Choose difficulty: ");
        System.out.printf("0. Easy (%dx%d, %d bombs)\n", Difficulties.easy.width,
                Difficulties.easy.height, Difficulties.easy.bombCount);
        System.out.printf("1. Medium (%dx%d, %d bombs)\n", Difficulties.medium.width,
                Difficulties.medium.height, Difficulties.medium.bombCount);
        System.out.printf("2. Expert (%dx%d, %d bombs)\n", Difficulties.expert.width,
                Difficulties.expert.height, Difficulties.expert.bombCount);
    }

    @Override
    public void displayEnd(Field field, int wins, int losses, long time) {
        displayField(field);

        String endText = field.hasWon() ? "Congratulations, you won! \n" : "That's a bomb! Game over! \n";

        System.out.println(endText);
        System.out.println("Time wasted: " + formatTime(time));
        System.out.printf("W/L Ratio: %d:%d\n\n", wins, losses);

        System.out.println("Do you want to play again? (y/n) ");
    }

    private String formatTime(long seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    @Override
    public void displayException(Exception exception) {
        System.out.println(exception.getMessage());
    }

    @Override
    public void displayLeaderboard(List<Record> records) {
        System.out.println("All scores:");
        displayUsers(records);
    }

    @Override
    public void displayPersonalScores(List<Record> records) {
        System.out.println("Personal scores:");
        displayUsers(records);
    }

    @Override
    public void displayUsernameChoice() {
        System.out.print("Enter your username: ");
    }

    @Override
    public void displayScoresOptions() {
        System.out.print("""
                Choose 0-2:
                0. Display leaderboard
                1. Display personal scores
                2. Exit
                """);
    }

    private void displayUsers(List<Record> records) {
        if (records.size() != 0) {
            for (Record record : records) {
                System.out.printf("%s: %s\n", record.user, formatTime(record.time));
            }
        } else {
            System.out.println("No available records.");
        }
        System.out.println();
    }
}
