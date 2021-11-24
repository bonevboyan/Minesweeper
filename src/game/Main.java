package game;

import game.objects.Coordinate;
import game.objects.Field;
import game.UI.*;


public class Main {

    public static void main(String[] args) throws Exception {
        IRenderer renderer = new ConsoleRenderer();
        IReader reader = new ConsoleReader();
        boolean inSession = true;

        while (inSession) {
            Field field = new Field();

            while (!field.hasEnded()) {
                try {
                    renderer.printField(field);

                    renderer.printSelect();
                    Coordinate coord = reader.readCoordinates();

                    renderer.printOptions();
                    int option = reader.readOption();

                    switch (option) {
                        case 0 -> field.play(coord.y, coord.x);
                        case 1 -> field.changeFlagCell(coord.y, coord.x);
                        default -> {
                        }
                    }
                } catch (Exception ex) {
                    renderer.printException(ex);
                }
            }

            renderer.printEnd(field);
            char ans = reader.readCharOption();

            if (ans != 'y') {
                inSession = false;
            }
        }

    }
}
