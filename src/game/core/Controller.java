package game.core;

import game.UI.ConsoleReader;
import game.UI.ConsoleRenderer;
import game.UI.IReader;
import game.UI.IRenderer;
import game.objects.Coordinate;
import game.objects.Field;

public class Controller {
    private IRenderer renderer = new ConsoleRenderer();
    private IReader reader = new ConsoleReader();
    private int wins = 0;
    private int losses = 0;

    public Controller() {

    }

    public Controller(IRenderer renderer, IReader reader) {
        this.renderer = renderer;
        this.reader = reader;
    }

    public void run() throws Exception {
        boolean inSession = true;

        while (inSession) {
            Field field = new Field();

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
                            break;
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
            if (field.hasWon()) {
                wins++;
            } else {
                losses++;
            }

            renderer.displayEnd(field, wins, losses);
            char ans = reader.readCharOption();

            if (ans != 'y') {
                inSession = false;
            }
        }


    }
}
