package game.objects;

public abstract class Cell {
    protected boolean isFlagged = false;
    protected boolean isOpened = false;

    public Cell() { }

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
