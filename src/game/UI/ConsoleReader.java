package game.UI;

import game.objects.Coordinate;

import java.util.Scanner;

public class ConsoleReader implements IReader{
    private final Scanner SCANNER;

    public ConsoleReader() {
        this.SCANNER = new Scanner(System.in);
    }

    private int readInteger() {
        if (!SCANNER.hasNextInt()) {
            SCANNER.next();
            throw new IllegalArgumentException("Invalid input. Expected an integer.");
        }
        return SCANNER.nextInt();
    }

    @Override
    public Coordinate readCoordinates() {
        int x = readInteger();
        int y = readInteger();
        return new Coordinate(y, x);
    }

    @Override
    public int readOption() {
        return readInteger();
    }

    @Override
    public char readCharOption() {
        return SCANNER.next().toLowerCase().charAt(0);
    }
}
