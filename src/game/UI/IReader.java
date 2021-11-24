package game.UI;

import game.objects.Coordinate;

public interface IReader {
    Coordinate readCoordinates();
    int readOption();
    char readCharOption();
}
