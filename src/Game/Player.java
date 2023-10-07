package Game;

public class Player {

    private final boolean isAI;

    private int row;
    private int col;

    private int numTokens;
    public Player(boolean isAI, int row, int col){
        this.isAI = isAI;
        this.row = row;
        this.col = col;
        numTokens = 0;
    }

    public boolean getIsAI(){
        return isAI;
    }

    public void pickupToken(){
        numTokens++;
    }

    public int getNumTokens(){
        return numTokens;
    }

    public int[] getRowCol(){
        int[] coords = new int[2];
        coords[0] = row;
        coords[1] = col;
        return coords;
    }

    public void setRowCol(int row, int col){
        this.row = row;
        this.col = col;
    }

    public String toString(){
        return "Player: " + row + "," + col;
    }
}
