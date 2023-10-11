package Game;

import javax.swing.*;
import java.util.Objects;

public class Cell extends JButton {
    private boolean isOccupied;
    private boolean hasToken;

    private int row;
    private int col;


    private Player p;

    public Cell(boolean isOccupied, boolean hasToken, int row, int col){
        super();
        this.isOccupied = isOccupied;
        this.hasToken = hasToken;
        this.row = row;
        this.col = col;
    }

    public boolean getOccupied(){
        return isOccupied;
    }

    public int[] getRowCol(){
        int coords[] = new int[2];
        coords[0] = row;
        coords[1] = col;
        return coords;
    }

    public void removeCell(){
        this.hasToken = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return isOccupied == cell.isOccupied && hasToken == cell.hasToken && row == cell.row && col == cell.col && Objects.equals(p, cell.p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOccupied, hasToken, row, col, p);
    }
    public boolean getHasToken() {
        return hasToken;
    }

    public Player getPlayer(){
        return p;
    }

    public void setPlayer(Player p){
        this.p = p;
        this.isOccupied = true;
    }

    public Player removePlayer(){
        Player temp = null;
        if(this.p != null){
            temp = p;
            isOccupied = false;
            p = null;

        }
        return temp;
    }

    public String toString(){
        return "Cell: " + row + "," + col;
    }
}
