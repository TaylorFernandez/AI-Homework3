package Game;

public class Player {

    private final boolean isAI;
    public Player(boolean isAI){
        this.isAI = isAI;
    }

    public boolean getIsAI(){
        return isAI;
    }
}
