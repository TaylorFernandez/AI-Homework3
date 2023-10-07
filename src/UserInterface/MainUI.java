package UserInterface;
import Game.Cell;
import Game.Player;
import Observer.Observable;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainUI implements ActionListener, Observable {
    //6 * 8 grid of buttons.
    JFrame mainUI;

    JPanel grid;
    Cell[][] board = new Cell[8][6];

    Player player;
    Player AI;
    volatile Cell lastClicked;

    static MainUI singleton;
    private MainUI(){
        player = new Player(false, 7,2);
        AI = new Player(true, 0, 3);

        mainUI = new JFrame("Isolation");
        grid = new JPanel(new GridLayout(8,6));
        grid.setBackground(Color.YELLOW);
        grid.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int i = 0; i < 8; i++){
            for(int j =0; j < 6; j++){
                board[i][j] = new Cell(false, true, i, j);
                board[i][j].addActionListener(this);
                board[i][j].setBackground(Color.YELLOW);
                board[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                grid.add(board[i][j]);

            }
        }

        board[0][3].setPlayer(AI);
        board[7][2].setPlayer(player);

        refreshGameUI();

        mainUI.add(grid);
        mainUI.setMinimumSize(new Dimension(500,500));
        mainUI.setVisible(true);

        runGameLoop();

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
                    board[i][j].setLabel("");
                }
                else if (!board[i][j].getOccupied() && !board[i][j].getHasToken()){
                    board[i][j].setBackground(Color.WHITE);
                    board[i][j].setLabel("");
                }
                else if(board[i][j].getOccupied() && board[i][j].getPlayer().getIsAI()){
                    board[i][j].setBackground(Color.BLUE);
                    board[i][j].setLabel("AI");
                }
                else if (board[i][j].getOccupied() && !board[i][j].getPlayer().getIsAI()){
                    board[i][j].setBackground(Color.GREEN);
                    board[i][j].setLabel("Player");
                }else if(board[i][j].getBackground() == Color.ORANGE){
                    board[i][j].setBackground(Color.YELLOW);
                    board[i][j].setLabel("");
                }
            }
        }

        mainUI.repaint();
        grid.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lastClicked = (Cell) e.getSource();
    }

    public Cell[][] getBoard() {
        return board;
    }

    @Override
    public void update() {
        refreshGameUI();
    }

    //Player selects one of the cells surrounding the player; can only select a cell if it has a token left
    //game runs until a player is surrounded with cells that don't have tokens (cannot move)
    private void runGameLoop(){
        Random r = new Random();
        Player current;

        if(r.nextInt(50) % 2 == 0){
            current = AI;
        }else{
            current = player;
        }
        List<Cell> moves;
        while(!(moves = getAvailableMoves(current.getRowCol()[0], current.getRowCol()[1])).isEmpty()) {
            System.out.println("Current Player: " + current.toString());

            System.out.println(moves);

            boolean hasClickedValidCell = false;
            while (!hasClickedValidCell) {
                lastClicked = null;
                while (lastClicked == null) {
                    Thread.onSpinWait();
                }
                System.out.println("Checking if selected cell is valid");
                if (moves.contains(lastClicked)) {
                    hasClickedValidCell = true;
                }else{
                    System.out.println("Move not valid");
                }
            }

            System.out.println("Valid Move!");

            board[current.getRowCol()[0]][current.getRowCol()[1]].removePlayer();
            lastClicked.setPlayer(current);
            current.setRowCol(lastClicked.getRowCol()[0], lastClicked.getRowCol()[1]);

            if(!current.getIsAI()){
                current = AI;
            }else{
                current = player;
            }
            refreshGameUI();
        }

        if(player.getNumTokens() > AI.getNumTokens()){
            System.out.println("The winner is the player with " + player.getNumTokens() + " tokens!");
        }else if(player.getNumTokens() < AI.getNumTokens()){
            System.out.println("The winner is the AI with " + AI.getNumTokens() + " tokens!");
        }else{
            System.out.println("The game ends in a tie!");
        }
    }


    //Used for checking each of the surrounding cells for a given input
    int[][] directions = {
            {-1,-1}, {-1,0}, {-1,1},
            {0,-1},          {0,1},
            {1,-1}, {1,0}, {1,1}
    };
    int numRows = board.length;
    int numCols = board[0].length;

    private List<Cell> getAvailableMoves(int row, int col){
        List<Cell> availableMoves = new ArrayList<Cell>();
        int curRow;
        int curCol;
        // Loop through the surrounding cells
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if the new coordinates are within bounds
            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
                // Check the condition and add the value to the sum
                if (board[newRow][newCol].getHasToken() && !board[newRow][newCol].getOccupied()) {
                    availableMoves.add(board[newRow][newCol]);
                    board[newRow][newCol].setBackground(Color.ORANGE);
                }
            }
        }

        mainUI.repaint();
        grid.repaint();
        return availableMoves;
    }
}
