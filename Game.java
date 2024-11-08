import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game implements MouseMotionListener, KeyListener {
    private final JFrame mainWindow = new JFrame();
    private final JPanel gameMainPanel = new JPanel();
    private final JPanel gameLockPanel = new JPanel();
    private final JLabel lockpickHealthLabel = new JLabel();
    private final JProgressBar lock = new JProgressBar(0);

    private final Dimension END_DIMENSION = new Dimension(300, 150);
    private final Font TEXT_FONT = new Font("Arial", 1, 20);

    private float MouseX;
    private float access;
    private float offset;
    private float lockpickHP = 100.0f;

    private boolean doOnce = true;
    private boolean blockMouseXUpdate;

    private float distance;
    private int maxProgressBar = 100;
    private int maxProgressBarClamp;

    public Game(int Difficulty){

        initWindow(); // Standard initialization of a swing window

        randomAccess();

        switch(Difficulty){ // do actions when the difficulty is set to:
            case 1:
                mainWindow.setTitle("Lockpicking_Simulator: Easy");
                offset = mainWindow.getX() / (mainWindow.getX() / 4f);
                maxProgressBarClamp = 25;
                break;
            case 2:
                mainWindow.setTitle("Lockpicking_Simulator: Normal");
                offset = mainWindow.getX() / (mainWindow.getX() / 2f);
                maxProgressBarClamp = 15;
                break;
            case 3:
                mainWindow.setTitle("Lockpicking_Simulator: Hard");
                offset = mainWindow.getX() / (mainWindow.getX() / 0.3f);
                maxProgressBarClamp = 5;
                break;
            default:
                mainWindow.setTitle("Burn in Hell hacker!"); //Easter egg for "Reverse Engineers"
                break;
        }
    }

    private void randomAccess(){
        Random rand = new Random();
        access = rand.nextInt(mainWindow.getWidth());
    }

    private void initWindow(){

        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        mainWindow.addMouseMotionListener(this);
        mainWindow.addKeyListener(this);

            gameMainPanel.setBorder(BorderFactory.createTitledBorder("gameMainPanel: BoxLayout"));
            gameMainPanel.setLayout(new BoxLayout(gameMainPanel, BoxLayout.PAGE_AXIS));
            gameMainPanel.setBackground(Color.yellow);

                gameLockPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                gameLockPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                gameLockPanel.setBorder(BorderFactory.createBevelBorder(0,Color.cyan,Color.blue));
                gameLockPanel.setBackground(Color.orange);
                gameLockPanel.setLayout(new BoxLayout(gameLockPanel, BoxLayout.PAGE_AXIS));

                    lockpickHealthLabel.setText(String.valueOf(lockpickHP));
                    lockpickHealthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    lockpickHealthLabel.setMinimumSize(new Dimension(30,20));
                    lockpickHealthLabel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
                gameLockPanel.add(lockpickHealthLabel);


                    lock.setPreferredSize(new Dimension(200, 20));
                    lock.setMinimumSize(lock.getPreferredSize());
                    lock.setMaximumSize(lock.getPreferredSize());
                    lock.setAlignmentX(Component.CENTER_ALIGNMENT);
                gameLockPanel.add(lock);

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

    private void updateLockpickHealthLabel(float newLockpickHealth){
        lockpickHealthLabel.setText(String.valueOf(newLockpickHealth));
    }

    private void distance(){ // called every ms of beign held down

        if(doOnce){
            doOnce = false;
        }else{
            return;
        }


        // Count the distance between current Mouse position and the 'access' position
        if(MouseX>access){
            distance=MouseX-access;
        }
        else{
            distance=access-MouseX;
        }

        int prctg = (int)(distance / (mainWindow.getX() / 100));

        maxProgressBar = 100;
        if(prctg < maxProgressBarClamp){
            if((maxProgressBar-prctg)+offset<0){
                maxProgressBar = 0;
            }else{
                maxProgressBar = (int) ((maxProgressBar-prctg)+offset);
            }
        }else{
            maxProgressBar = 0;
        }

    }

    private void incrementLock(){
        if(lock.getValue()<100){
            if(lock.getValue()<maxProgressBar){
                lock.setValue(lock.getValue()+1);
            }else{
                damageLockpick();
            }
        }else{
            win();
        }
    }
    private void resetLock(){
        lock.setValue(0);
    }

    private void damageLockpick(){
        if(lockpickHP>0f){
            lockpickHP-=1f;
            updateLockpickHealthLabel(lockpickHP);
        }else{
            lose();
        }
    }

    private void win(){
        JFrame winWindow = new JFrame(mainWindow.getTitle());
        JPanel winPanel = new JPanel();
        JLabel winText = new JLabel("You opened the lock successfully!");

        winWindow.setResizable(false);
        winWindow.setVisible(true);

        winText.setFont(TEXT_FONT);

        winPanel.add(winText);
        winWindow.add(winPanel);

        winWindow.pack();

        winWindow.setPreferredSize(END_DIMENSION);

        winWindow.setLocationRelativeTo(null);

        mainWindow.dispose();
    }

    private void lose(){
        JFrame loseWindow = new JFrame(mainWindow.getTitle());
        JPanel losePanel = new JPanel();
        JLabel loseText = new JLabel("Your lockpick broke!");

        loseWindow.setResizable(false);
        loseWindow.setVisible(true);

        loseText.setFont(TEXT_FONT);

        losePanel.add(loseText);
        loseWindow.add(losePanel);

        loseWindow.pack();

        loseWindow.setPreferredSize(END_DIMENSION);
        loseWindow.setLocationRelativeTo(null);

        mainWindow.dispose();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(!blockMouseXUpdate){
            MouseX = e.getX();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        blockMouseXUpdate = true;
        distance();
        incrementLock();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        blockMouseXUpdate = false;
        doOnce = true;
        resetLock();
    }
}