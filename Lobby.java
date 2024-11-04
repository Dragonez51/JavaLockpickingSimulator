import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lobby {
    final JFrame lobbyFrame = new JFrame();
    JPanel lobbyMainPanel = new JPanel();
    JPanel Difficulty = new JPanel();
    JLabel tekst = new JLabel("", SwingConstants.CENTER);
    JButton Easy = new JButton();
    JButton Normal = new JButton();
    JButton Hard = new JButton();

    public Lobby(){
        lobbyFrame.setTitle("Lockpicking_Simulator: Lobby");
        lobbyFrame.add(lobbyMainPanel);
        lobbyFrame.setPreferredSize(new Dimension(500,200));
        lobbyFrame.setMinimumSize(lobbyFrame.getPreferredSize());
        lobbyFrame.setMaximumSize(lobbyFrame.getPreferredSize());
        lobbyFrame.setResizable(false);
        lobbyFrame.setVisible(true);
        lobbyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lobbyMainPanel.setLayout(new BoxLayout(lobbyMainPanel, BoxLayout.PAGE_AXIS));
        lobbyMainPanel.setBorder(BorderFactory.createTitledBorder("panel")); // Debugging stuff
        lobbyMainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Difficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
        Difficulty.setBorder(BorderFactory.createTitledBorder("Difficulty")); // Debugging stuff

        tekst.setText("Choose a lock difficulty");
        tekst.setPreferredSize(new Dimension(600,30));
        tekst.setFont(new Font("Arial", 1, 25));
        tekst.setBounds(30, 0, 30, 0);
        tekst.setHorizontalAlignment(SwingConstants.CENTER);
        Difficulty.add(tekst);

        Easy.setText("Easy");
        Easy.setPreferredSize(new Dimension(80, 60));
        Easy.setMinimumSize(Easy.getPreferredSize());
        Easy.setBounds(30, 30, 30, 30);
        Easy.setHorizontalAlignment(SwingConstants.CENTER);
        Easy.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new Game(1);
            }
        });
        Difficulty.add(Easy);

        Normal.setText("Normal");
        Normal.setPreferredSize(new Dimension(80, 60));
        Normal.setBounds(30,30,30,30);
        Normal.setHorizontalAlignment(SwingConstants.CENTER);
        Normal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new Game(2);
            }
        });
        Difficulty.add(Normal);

        Hard.setText("Hard");
        Hard.setPreferredSize(new Dimension(80, 60));
        Hard.setBounds(30, 30, 30, 30);
        Hard.setHorizontalAlignment(SwingConstants.CENTER);
        Hard.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new Game(3);
            }
        });
        Difficulty.add(Hard);

        lobbyMainPanel.add(Difficulty);

        lobbyFrame.pack();
        lobbyFrame.setLocationRelativeTo(null);
    }
}