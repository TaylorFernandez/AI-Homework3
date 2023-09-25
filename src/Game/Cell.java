package Game;

import javax.swing.*;

public class Cell extends JButton {
    private boolean isOccupied;
    private boolean hasToken;

    public Cell(boolean isOccupied, boolean hasToken){
        super();
        this.isOccupied = isOccupied;
        this.hasToken = hasToken;
    }

    public void setOccupied(boolean occupied){
        isOccupied = occupied;
    }

    public boolean getOccupied(boolean occupied){
        return isOccupied;
    }

    public void setHasToken(boolean hasToken){
        this.hasToken = hasToken;
    }

    public boolean getHasToken() {
        return hasToken;
    }
}
