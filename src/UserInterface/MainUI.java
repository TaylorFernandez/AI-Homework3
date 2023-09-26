package UserInterface;
import Game.Cell;
import Game.Player;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI implements ActionListener {
    //6 * 8 grid of buttons.
    JFrame mainUI;
    JPanel grid;
    Cell[][] board = new Cell[8][6];

    static MainUI singleton;
    private MainUI(){
        mainUI = new JFrame("Isolation");
        grid = new JPanel(new GridLayout(8,6));
        grid.setBackground(Color.YELLOW);
        grid.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int i = 0; i < 8; i++){
            for(int j =0; j < 6; j++){
                board[i][j] = new Cell(false, true);
                board[i][j].addActionListener(this);
                board[i][j].setBackground(Color.YELLOW);
                board[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                grid.add(board[i][j]);

            }
        }

        Player p = new Player(false);
        Player ai = new Player(true);

        board[0][3].setPlayer(ai);
        board[7][2].setPlayer(p);

        refreshGameUI();

        mainUI.add(grid);
        mainUI.setMinimumSize(new Dimension(500,500));
        mainUI.setVisible(true);

    }

    public static MainUI getSingleton(){
        if(singleton == null){
            singleton = new MainUI();
        }
        return singleton;
    }

    public void refreshGameUI() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 6; j++){
                if(!board[i][j].getOccupied() && board[i][j].getHasToken()){
                    board[i][j].setBackground(Color.YELLOW);
                }
                else if (!board[i][j].getOccupied() && !board[i][j].getHasToken()){
                    board[i][j].setBackground(Color.WHITE);
                }
                else if(board[i][j].getOccupied() && board[i][j].getPlayer().getIsAI()){
                    board[i][j].setBackground(Color.BLUE);
                }
                else if (board[i][j].getOccupied() && !board[i][j].getPlayer().getIsAI()){
                    board[i][j].setBackground(Color.GREEN);
                }
            }
        }

        mainUI.repaint();
        grid.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
