package game.objects;

public class ClearCell extends Cell {
    private int adjacentBombs;

    public ClearCell() {
        adjacentBombs = 0;
    }

    public int getAdjacentBombs() {
        return adjacentBombs;
    }

    public void increaseAdjacentBombsByOne() {
        this.adjacentBombs++;
    }
}
