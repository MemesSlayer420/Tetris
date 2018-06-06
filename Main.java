import java.awt.Color;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) throws InterruptedException {
		int width = 640;
		int height = 720;
		
		Frame f = new Frame(width, height);
        JFrame frame = new JFrame("Tetris"); //create a new window and set title on window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set the window to close when the cross in the corner is pressed
        frame.setSize(width + 17,height + 40);
        
        frame.add(f); //add the content of the game object to the window
        frame.setVisible(true);
        
        long interval = (long)10 * 1000000 * 0; //change
        long t = 0;
        while(true) {
        	if(System.nanoTime() - t >= interval) { //repaints at a certain fps
        		f.repaint();
        		t = System.nanoTime();
        	}
        	else TimeUnit.NANOSECONDS.sleep(10); //so while loop doesn't just run empty code continuously.
        }
	}
}

/* Features:
Show next
Show score, lines, level
Pause, restart
Level select

*/