package game;

import game.core.Controller;
import game.objects.Coordinate;
import game.objects.Field;
import game.UI.*;


public class Main {

    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        controller.run();
    }
}
