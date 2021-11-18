package game;

import game.objects.Field;
import game.UI.*;


public class Main {

    public static void main(String[] args) throws Exception {
        Field field = new Field();
        IRenderer renderer = new ConsoleRenderer();
        renderer.printSelect();
    }
}
