package game.UI;

import java.util.Scanner;

public class ConsoleReader implements IReader{
    private Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int[] readCoordinates() {
        int result[] = new int[2];
        result[0] = scanner.nextInt();
        result[1] = scanner.nextInt();
        return result;
    }

    @Override
    public int readOption() {
        return scanner.nextInt();
    }
}
