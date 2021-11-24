package game.UI;

import game.objects.Coordinate;

import java.util.Scanner;

public class ConsoleReader implements IReader{
    private final Scanner SCANNER;

    public ConsoleReader() {
        this.SCANNER = new Scanner(System.in);
    }

    @Override
    public Coordinate readCoordinates() {
        int x = SCANNER.nextInt();
        int y = SCANNER.nextInt();
        return new Coordinate(y, x);
    }

    @Override
    public int readOption() {
        return SCANNER.nextInt();
    }

    @Override
    public char readCharOption() {
        return SCANNER.next().toLowerCase().charAt(0);
    }
}
