import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame implements ActionListener{

    private JButton exit;
    private JButton joinGame;
    private JLabel  title;


    public Menu(){

        setTitle("4connect");
        Container cp = getContentPane();
        cp.setLayout(null);

        title = new JLabel("4connect");
        title.setBounds(20, 35, 300, 55);
        title.setFont(new Font ("Comic Sans", Font.BOLD, 40));
        cp.add(title);

        joinGame = new JButton("Join game");
        joinGame.setBounds(20, 110, 177, 25);
        joinGame.addActionListener(this);
        cp.add(joinGame);

        exit = new JButton("exit");
        exit.setBounds(20, 155, 177, 25);
        exit.addActionListener(this);
        cp.add(exit);

        setSize(new Dimension(232, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == exit){
            dispose();
        } else if (e.getSource() == joinGame){
            joinGame jGame = new joinGame();
            dispose();
        }
    }

}
