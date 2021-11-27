package game.core;

import game.UI.*;
import game.data.Data;
import game.objects.Coordinate;
import game.objects.Field;

public class Controller {
    private final Data data = new Data();

    private IRenderer renderer = new ConsoleRenderer();
    private IReader reader = new ConsoleReader();

    private String username;
    private int wins = 0;
    private int losses = 0;

    public Controller() {

    }

    public Controller(IRenderer renderer, IReader reader) {
        this.renderer = renderer;
        this.reader = reader;
    }

    public void run() throws Exception {
        renderer.displayUsernameChoice();
        username = reader.readUsername();

        play();
        showLeaderboards();
    }

    private void play() throws Exception {
        boolean inSession = true;

        while (inSession) {
            renderer.displaySetup();
            int difficulty = reader.readOption();

            Field field = new Field(difficulty);

            while (!field.hasEnded()) {
                try {
                    renderer.displayField(field);

                    renderer.displaySelect();
                    Coordinate coord = reader.readCoordinates();

                    renderer.displayOptions();
                    int option = reader.readOption();

                    switch (option) {
                        case 0 -> field.play(coord.y, coord.x);
                        case 1 -> field.changeFlagCell(coord.y, coord.x);
                        case 2 -> {
                            inSession = false;
                        }
                        default -> {
                            throw new IllegalArgumentException("Invalid option. Select between 0 and 2.");
                        }
                    }
                } catch (IllegalArgumentException ex) {
                    renderer.displayException(ex);
                } catch (TypeNotPresentException ex) {
                    inSession = false;
                    renderer.displayException(ex);
                }
            }
            long time = field.getTime();

            if (field.hasWon()) {
                wins++;
                data.postRecord(username, time);
            } else {
                losses++;
            }

            renderer.displayEnd(field, wins, losses, time);

            char ans = reader.readCharOption();

            if (ans != 'y') {
                inSession = false;
            }
        }
    }

    private void showLeaderboards() throws Exception {
        boolean inSession = true;

        while(inSession){
            try {
                renderer.displayScoresOptions();
                int option = reader.readOption();

                switch (option) {
                    case 0 -> renderer.displayLeaderboard(data.getAllRecords());
                    case 1 -> renderer.displayPersonalScores(data.getOwnRecords(username));
                    case 2 -> {
                        inSession = false;
                    }
                    default -> {
                        throw new IllegalArgumentException("Invalid option. Select between 0 and 2.");
                    }
                }
            } catch(IllegalArgumentException ex){
                renderer.displayException(ex);
            }
        }
    }
}
