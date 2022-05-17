import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIgrid extends JFrame implements ActionListener {

    private JPanel fWin;
    private JPanel[][] grid;
    private JButton[] fillB;
    private control con;
    private JLabel patTurn;
    private int cSize = 65;
    private int xBor = 120;
    private int yBor = 80;
    private int tBor = 80;
    private int minsize = 2;
    //TODO: resolve clickable Jpanel or button


    public GUIgrid(control pCon){


        con = pCon;

        setTitle("4connect!");
        Container cp = getContentPane();
        cp.setLayout(null);

        grid = new JPanel[pCon.getGrid().length][pCon.getGrid()[0].length];

        int x = 0;
        for(int i = 0; i < grid.length; i++){
            x = 0;
            for(int j = grid[0].length-1; j >= 0; j--){
                grid[i][j] = new JPanel();
                grid[i][j].setBounds(xBor + cSize*i +10, yBor + cSize*x +10, cSize-20, cSize-20);

                cp.add(grid[i][j]);
                x++;
            }

        }

        fillB = new JButton[grid.length];
        for(int i = 0; i < grid.length; i++){
            fillB[i] = new JButton("place");
            fillB[i].setBounds(xBor + cSize*i, yBor - 30, cSize, 20);
            fillB[i].addActionListener(this);

            cp.add(fillB[i]);
        }

        colorGrid();

        patTurn = new JLabel();
        patTurn.setBounds(xBor, yBor + pCon.getGrid()[0].length*cSize + (tBor/2)-25, 350, 20);
        cp.add(patTurn);

        setSize(new Dimension(xBor*2 + pCon.getGrid().length*cSize, yBor + pCon.getGrid()[0].length*cSize + tBor));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        updateTurn();
    } //TODO: constructor

    private void updateTurn(){
        patTurn.setText("it's " + con.getCurrentPlayerName() + "s turn" );
    } //TODO: create

    private void updateWon(){
        patTurn.setText("Congratulations! " + con.getWinningPlayerName() + " won the game!");
    }

    private void colorGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++) {
                if (con.getGrid()[i][j] == con.getpChar(0)) {
                    grid[i][j].setBackground(Color.RED);
                } else if (con.getGrid()[i][j] == con.getpChar(1)) {
                    grid[i][j].setBackground(Color.YELLOW);
                }
                else {
                    grid[i][j].setBackground(Color.GRAY);
                }
            }
        }
    }



    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < grid.length; i++) {
            if(e.getSource() == fillB[i]){
                if(con.placeStone(i) == true){
                    updateTurn();
                } else {
                    fillB[i].setVisible(false);
                }
            }

            colorGrid();
        }
        if(con.getStatus() == true){
            updateWon();
        }
    }


}
