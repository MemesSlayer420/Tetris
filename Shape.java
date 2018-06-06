import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Shape {
	//define each piece's color, could have ghost piece
	final static Color L_COLOR = new Color(255,150,0);
	final static Color J_COLOR = new Color(30,30,255);
	final static Color O_COLOR = Color.YELLOW;
	final static Color T_COLOR = Color.MAGENTA;
	final static Color S_COLOR = Color.GREEN;
	final static Color Z_COLOR = Color.RED;
	final static Color I_COLOR = Color.CYAN;
	final static Color C_COLOR = Color.BLACK;


	//define each piece
	private final Point[] L = {new Point(3,1),new Point(4,1),new Point(5,0),new Point(5,1)};
	private final Point[] J = {new Point(3,1),new Point(4,1),new Point(5,1),new Point(3,0)};
	private final Point[] O = {new Point(3,0),new Point(3,1),new Point(4,0),new Point(4,1)};
	private final Point[] T = {new Point(3,1),new Point(4,0),new Point(5,1),new Point(4,1)};
	private final Point[] S = {new Point(3,1),new Point(4,0),new Point(4,1),new Point(5,0)};
	private final Point[] Z = {new Point(3,0),new Point(4,0),new Point(4,1),new Point(5,1)};
	private final Point[] I = {new Point(4,0),new Point(3,0),new Point(5,0),new Point(6,0)};

	private final Point[][] SHAPES = {L,J,O,T,S,Z,I};
	final static Color[] COLORS = {L_COLOR,J_COLOR,O_COLOR,T_COLOR,S_COLOR,Z_COLOR,I_COLOR,C_COLOR};

	public Point[] thisShape;
	public Color thisColor;

	public Shape(Point[] shape, Color color) {
		this.thisShape = new Point[shape.length];
		for(int i = 0;i < shape.length; i++) {
			thisShape[i] = (Point) shape[i].clone();
		}
		this.thisColor = color;
	}

	public void translateX(int delta) {
		for(int i = 0; i < this.thisShape.length; i++) {
			this.thisShape[i].x +=delta;
		}
	}

	public void translateY(int delta) {
		for(int i = 0; i < this.thisShape.length; i++) {
			this.thisShape[i].y +=delta;
		}
	}

	public Point[] newShape(int s) {
		return SHAPES[s];
	}

	public boolean canFall() {
		for(int i = 0; i < thisShape.length; i++) {
			if(thisShape[i].y >= Frame.boardHeight - 1) return false;
			if(Frame.board[thisShape[i].x][thisShape[i].y + 1]) return false;
		}
		return true;
	}

	public boolean canMoveLeft() {
		for(int i = 0; i < thisShape.length; i++) {
			if(thisShape[i].x == 0) return false;
			if(Frame.board[thisShape[i].x - 1][thisShape[i].y]) return false;
		}
		return true;
	}

	public boolean canMoveRight() {
		for(int i = 0; i < thisShape.length; i++) {
			if(thisShape[i].x + 1 == Frame.boardWidth) return false;
			if(Frame.board[thisShape[i].x + 1][thisShape[i].y]) return false;
		}
		return true;
	}

	public void rotateRightIfCan() {
		boolean can = true;
		Point[] rotated = new Point[thisShape.length];
		int[] origin = {thisShape[0].x, thisShape[0].y};
		
		for(int i = 0; i < thisShape.length; i++) {
			rotated[i] = new Point(origin[0] - (thisShape[i].y - origin[1]),origin[1] + (thisShape[i].x - origin[0]));
			if(Frame.board[rotated[i].x][rotated[i].y]) {
				return;
			}
		}
		for(int i = 0; i < rotated.length; i++) {
			thisShape[i].x = rotated[i].x;
			thisShape[i].y = rotated[i].y;
		}
	}
	
	public void rotateLeftIfCan() {

	}
}
