import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class joinGame extends JFrame implements ActionListener{

    private TextArea entIp;
    private JLabel lIp;
    private TextArea entPort;
    private JLabel lPort;
    private JButton join;
    private JButton exit;

    public joinGame(){

        setTitle("Join a game");
        Container cp = getContentPane();
        cp.setLayout(null);

        entIp = new TextArea("", 1, 1, TextArea.SCROLLBARS_NONE);
        entIp.setBounds(110,10, 200, 20);
        cp.add(entIp);

        lIp = new JLabel("IP:");
        lIp.setBounds(93, 9, 30, 20);
        cp.add(lIp);

        entPort = new TextArea("", 1, 1, TextArea.SCROLLBARS_NONE);
        entPort.setBounds(110,40, 200, 20);
        cp.add(entPort);

        lIp = new JLabel("Port:");
        lIp.setBounds(80, 39, 30, 20);

        cp.add(lIp);


        join = new JButton("join game");
        join.setBounds(110, 70, 200, 20);
        join.addActionListener(this);
        cp.add(join);


        exit = new JButton("cancel");
        exit.setBounds(110, 100, 200, 20);
        exit.addActionListener(this);
        cp.add(exit);

        setSize(380, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == exit){
            Menu tMenu = new Menu();
            dispose();
        }

    }
}
