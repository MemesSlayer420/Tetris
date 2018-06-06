import java.awt.Point;

public class Window { //holds two points, or a region
	public int x1,y1,x2,y2;
	
	public Window(int x1In, int y1In, int x2In, int y2In) {
		x1 = x1In;
		y1 = y1In;
		x2 = x2In;
		y2 = y2In;
	}
	
	public boolean inWindow(Point p) { //true when p is in the window
		return p.x >= x1 && p.x <= x2 && p.y >= y1 && p.y <= y2;
	}
	
	public int getWidth() {
		return x2 - x1;
	}
	
	public int getHeight() {
		return y2 - y1;
	}
}
