import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game implements MouseMotionListener, KeyListener {
    private JFrame mainWindow = new JFrame();
    private JPanel gameMainPanel = new JPanel();
    private JPanel gameLockPanel = new JPanel();
    private JLabel mousePosition = new JLabel();

    private final Dimension END_DIMENSION = new Dimension(300, 150);
    private final Font TEXT_FONT = new Font("Arial", 1, 20);

    private float MouseX;

    private float access;
    private float offset;

    private float lockpickHP = 100.0f;

    private boolean keyDown = false;
    public Game(int Difficulty){

        initWindow(); // Standard initialization of a swing window

        randomAccess();

        switch(Difficulty){ // do actions when the difficulty is set to:
            case 1:
                mainWindow.setTitle("Lockpicking_Simulator: Easy");
                offset = mainWindow.getX() / (mainWindow.getX() / 8f);
                break;
            case 2:
                mainWindow.setTitle("Lockpicking_Simulator: Normal");
                offset = mainWindow.getX() / (mainWindow.getX() / 4f);
                break;
            case 3:
                mainWindow.setTitle("Lockpicking_Simulator: Hard");
                offset = mainWindow.getX() / (mainWindow.getX() / 2f);
                break;
            default:
                mainWindow.setTitle("Burn in Hell hacker!");
                break;
        }
    }
    private void randomAccess(){
        Random rand = new Random();
        access = rand.nextFloat(mainWindow.getX());
    }
    private void initWindow(){

        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        mainWindow.addMouseMotionListener(this);
        mainWindow.addKeyListener(this);

        mousePosition.setText(String.valueOf(MouseX));
        gameMainPanel.add(mousePosition);

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

    private void updateMouseX(float newMouseX){
        mousePosition.setText(String.valueOf(newMouseX));
    }

    private void keyDown(){

    }

    private void tryOpening(){
        while(keyDown){
            System.out.println(keyDown);
        }
    }

    private void damageLockpick(){

        // Currently the lockpick is damaged everytime the key press refreshes
        // You can easily hold a key and then spam the mouse movement. Thus making a brute force in.
        // TODO 1. Make holding one key block the MouseX change
        // TODO 2. Make the ammount of time before the lockpick is going to be damaged according to how close the MouseX is to access

        if(lockpickHP>0f){
            lockpickHP-=1f;
        }else{
            mainWindow.removeMouseMotionListener(this);
            mainWindow.removeKeyListener(this);
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
        System.out.println("MouseX: "+MouseX);
        System.out.println("Offset: "+offset);
        System.out.println("Access: "+access);
    }

    private void lose(){
        JFrame loseWindow = new JFrame(mainWindow.getTitle());
        JPanel losePanel = new JPanel();
        JLabel loseText = new JLabel("You failed to open the lock!");

        loseWindow.setResizable(false);
        loseWindow.setVisible(true);

        loseText.setFont(TEXT_FONT);

        losePanel.add(loseText);
        loseWindow.add(losePanel);

        loseWindow.pack();

        loseWindow.setPreferredSize(END_DIMENSION);
        loseWindow.setLocationRelativeTo(null);

        mainWindow.dispose();
        System.out.println("MouseX: "+MouseX);
        System.out.println("Offset: "+offset);
        System.out.println("Access: "+access);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MouseX = e.getX();
        updateMouseX(MouseX);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyDown = true;
        tryOpening();
//        if(MouseX > access + offset || MouseX < access - offset){
//            damageLockpick();
//            System.out.println(lockpickHP);
//        }else{
//            mainWindow.removeMouseMotionListener(this);
//            mainWindow.removeKeyListener(this);
//            win();
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyDown = false;
    }
}