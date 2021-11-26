package game.objects;

public abstract class Difficulties {

    public static MinesweeperDifficulty easy = new MinesweeperDifficulty(9, 9, 8);

    public static MinesweeperDifficulty medium = new MinesweeperDifficulty(16, 16, 35);

    public static MinesweeperDifficulty expert = new MinesweeperDifficulty(16, 30, 86);

    public static MinesweeperDifficulty none = new MinesweeperDifficulty(10, 15, 10);
}
