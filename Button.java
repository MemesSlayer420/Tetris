/** Class for creating buttons
 * 
 * Just use the power of abstraction 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Button { //just use the power of abstraction
	public static final String TYPE_MINUS = "MINUS";
	public static final String TYPE_PLUS = "PLUS";
	
	private Window w;
	private Color btnColor;
	private Color accColor; //accent color
	private Color pressedColor; //may use in future
	private Boolean pressed = false; //may use in future
	public String type = "";

	public Button(Window windowIn, Color c1, Color c2, Color c3) {
		w = windowIn;
		btnColor = c1;
		accColor = c2;
		pressedColor = c3;
	}
	
	public Button(Window windowIn, Color c1, Color c2, Color c3, String typeIn) {
		w = windowIn;
		btnColor = c1;
		accColor = c2;
		pressedColor = c3;
		type = typeIn;
	}
	
	public boolean onButton(Point p) {
		int x = p.x;
		int y = p.y;
		boolean on = x >= w.x1 && x <= w.x2 && y >= w.y1 && y <= w.y2;
		pressed = on;
		return on;
	}

	public void drawButton(Graphics G) {
		if(pressed) G.setColor(pressedColor);
		else G.setColor(btnColor);
		G.fillRect(w.x1, w.y1, w.x2 - w.x1, w.y2 - w.y1);
		G.setColor(accColor);
		G.drawRect(w.x1, w.y1, w.x2 - w.x1, w.y2 - w.y1);
		if(type.equals("PLUS")) {
			G.fillRect(w.x1 + (w.x2 - w.x1)/2 - 1, w.y1 + 3, 3, w.y2 - w.y1 - 5);
			G.fillRect(w.x1 + 3, w.y1 + (w.y2 - w.y1)/2 - 1, w.x2 - w.x1 - 5, 3);
		}
		else if(type.equals("MINUS")) {
			G.fillRect(w.x1 + 3, w.y1 + (w.y2 - w.y1)/2 - 1, w.x2 - w.x1 - 5, 3);
		}
	}
}