package game.objects;

public abstract class Cell {
    protected int x;
    protected int y;
    protected boolean isFlagged = false;
    protected boolean isOpened = false;

    public Cell(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) throws Exception {
        if (!isOpened) {
            isFlagged = flagged;
        } else {
            throw new Exception("Cell is opened!");
        }
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void onSelect() {
        isOpened = true;
        isFlagged = false;
    }
}
