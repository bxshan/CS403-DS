import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Anu Datar
 * 
 * Changed block size and added a split panel display for next block and Score
 * 
 * @author Ryan Adolf
 * @version 1.0
 * 
 * Fixed the lag issue with block rendering 
 * Removed the JPanel
 */
// Used to display the contents of a game board
public class BlockDisplay extends JComponent implements KeyListener
{
    private static final Color BACKGROUND = Color.BLACK;
    private static final Color BORDER = Color.BLACK;

    private static final int OUTLINE = 2;
    private static final int BLOCKSIZE = 20;

    private MyBoundedGrid<Block> board;
    private JFrame frame;
    private ArrowListener listener;

    // Constructs a new display for displaying the given board
    public BlockDisplay(MyBoundedGrid<Block> board)
    {
        this.board = board;

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            });

        //Wait until display has been drawn
        try
        {
            while (frame == null || !frame.isVisible())
                Thread.sleep(1);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() {
      frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(this);
      frame.addKeyListener(this);

      JPanel scorePanel = new JPanel();
      scorePanel.setLayout(new BorderLayout());

      JLabel scoreLabel = new JLabel("0", SwingConstants.CENTER);
      scorePanel.add(scoreLabel, BorderLayout.WEST);

      JLabel levelLabel = new JLabel("1", SwingConstants.CENTER);
      scorePanel.add(levelLabel, BorderLayout.EAST);

      frame.getContentPane().add(scorePanel, BorderLayout.NORTH);

      this.setPreferredSize(new Dimension(
            BLOCKSIZE * board.getNumCols(),
            BLOCKSIZE * board.getNumRows()
            ));

      frame.pack();
      frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(BORDER);
        g.fillRect(0, 0, BLOCKSIZE * board.getNumCols() + OUTLINE, BLOCKSIZE * board.getNumRows());
        for (int row = 0; row < board.getNumRows(); row++)
            for (int col = 0; col < board.getNumCols(); col++)
            {
                Location loc = new Location(row, col);

                Block square = board.get(loc);

                if (square == null)
                    g.setColor(BACKGROUND);
                else
                    g.setColor(square.getColor());

                g.fillRect(col * BLOCKSIZE + OUTLINE/2, row * BLOCKSIZE + OUTLINE/2,
                    BLOCKSIZE - OUTLINE, BLOCKSIZE - OUTLINE);
            }

    }

    //Redraws the board to include the pieces and border colors.
    public void showBlocks()
    {
        repaint();
    }

    // Sets the title of the window.
    public void setTitle(String title)
    {
        frame.setTitle(title);
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
        if (listener == null)
            return;
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT)
            listener.leftPressed();
        else if (code == KeyEvent.VK_RIGHT)
            listener.rightPressed();
        else if (code == KeyEvent.VK_DOWN)
            listener.downPressed();
        else if (code == KeyEvent.VK_UP)
            listener.upPressed();
    }

    public void setArrowListener(ArrowListener listener)
    {
        this.listener = listener;
    }

    //--------

    public void incrementScore(int increment) {
      JLabel sl = (JLabel) ((JPanel) frame.getContentPane().getComponent(1)).getComponent(0);
      sl.setText(Integer.toString(Integer.parseInt(sl.getText()) + increment));
    }

    public void setLvl(int lvl) {
      JLabel ll = (JLabel) ((JPanel) frame.getContentPane().getComponent(1)).getComponent(1);
      ll.setText(Integer.toString(lvl));
    }

    public void setLost() {
      JLabel sl = (JLabel) ((JPanel) frame.getContentPane().getComponent(1)).getComponent(0);
      sl.setText(sl.getText() + "\t\t\t\t\t\tGame");

      JLabel ll = (JLabel) ((JPanel) frame.getContentPane().getComponent(1)).getComponent(1);
      ll.setText("Over\t\t\t\t\t\t" + ll.getText());
    }
}
