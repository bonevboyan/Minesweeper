package game.objects;

public class ClearCell extends Cell {
    private int adjacentBombs;

    public ClearCell(int y, int x) {
        super(y, x);
    }

    public int getAdjacentBombs() {
        return adjacentBombs;
    }

    public void setAdjacentBombs(int adjacentBombs) {
        this.adjacentBombs = adjacentBombs;
    }
}
