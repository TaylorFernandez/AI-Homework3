package Game;

import UserInterface.MainUI;

public class GameRunner {
    Player p;
    Player ai;
    public GameRunner(){
        p = new Player(false);
        ai = new Player(true);
    }

    public void runGame(){
        MainUI ui = MainUI.getSingleton(p, ai);

    }
}
