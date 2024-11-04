import javax.swing.*;
import java.awt.*;

public class Game {
    private JFrame mainWindow = new JFrame();
    private JPanel gameMainPanel = new JPanel();
    private JPanel gameLockPanel = new JPanel();
    public Game(int Difficulty){

        initWindow(); // Standard initialization of a swing window

        switch(Difficulty){ // do actions when the difficulty is set to:
            case 1:
                mainWindow.setTitle("Lockpicking_Simulator: Easy");
                break;
            case 2:
                mainWindow.setTitle("Lockpicking_Simulator: Normal");
                break;
            case 3:
                mainWindow.setTitle("Lockpicking_Simulator: Hard");
                break;
            default:
                mainWindow.setTitle("Burn in Hell hacker!");
                break;
        }



    }
    private void initWindow(){

        mainWindow.setResizable(false);
        mainWindow.setVisible(true);

        gameMainPanel.add(gameLockPanel);
        mainWindow.add(gameMainPanel);

        mainWindow.pack();

        mainWindow.setPreferredSize(new Dimension(600,400)); // set Preferred/Min/Max size
        mainWindow.setMinimumSize(mainWindow.getPreferredSize());
        mainWindow.setMaximumSize(mainWindow.getPreferredSize());

        mainWindow.setLocationRelativeTo(null); // Spawn on center of the screen
    }
    private void pause(){
        // 1. Calls the stopTimer function from class Timer
        // 2. B̶l̶o̶c̶k̶s̶ i̶m̶a̶g̶e̶ u̶p̶d̶a̶t̶i̶n̶g̶ f̶o̶r̶ t̶h̶e̶ g̶r̶a̶p̶h̶i̶c̶a̶l̶ i̶n̶t̶e̶r̶p̶r̶e̶t̶a̶t̶i̶o̶n̶
    }
}