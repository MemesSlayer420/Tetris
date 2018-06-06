import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

public class Frame extends JPanel {
	int width, height;
	String currentCursor = ""; //holds name of current cursor.
	
	Window boardWindow = new Window(0,0,360,720);
	public static int boardWidth = 10;
	public static int boardHeight = 20;
	public static boolean[][] board = new boolean[boardWidth][boardHeight];
	public static Color[][] boardColor = new Color[boardWidth][boardHeight];
	
	final ArrayList<Point[]> shapes = new ArrayList<Point[]>(); //for easy randomization
	//final ArrayList<Color> colors = new ArrayList<Color>(); //for easy randomization
	
	final int startDelay = 500, downDelay = 100;
	
	int delay = startDelay;
	
	Shape miscShape;
	int inc = 1;
	
	long t = 0;
	int regInt = 500;
	int fastInt = 50;
	int interval = regInt;
	
	boolean gameOver = false;
	int lines = 0;
	int level = 1;
	//lines, points, level, level speed, down pressed speed
	
	private final Point[] L = {new Point(4,1),new Point(3,1),new Point(5,0),new Point(5,1)};
	private final Point[] J = {new Point(4,1),new Point(3,1),new Point(5,1),new Point(3,0)};
	private final Point[] O = {new Point(3,0),new Point(3,1),new Point(4,0),new Point(4,1)};
	private final Point[] T = {new Point(4,1),new Point(4,0),new Point(5,1),new Point(3,1)};
	private final Point[] S = {new Point(4,1),new Point(4,0),new Point(3,1),new Point(5,0)};
	private final Point[] Z = {new Point(4,1),new Point(4,0),new Point(3,0),new Point(5,1)};
	private final Point[] I = {new Point(4,0),new Point(3,0),new Point(5,0),new Point(6,0)};
	private final Point[] C = {new Point(5,0)};
	
	public Frame(int widthIn, int heightIn) { //runs once at the beginning

		shapes.add(L);
		shapes.add(J);
		shapes.add(O);
		shapes.add(T);
		shapes.add(S);
		shapes.add(Z);
		shapes.add(I);
		shapes.add(C);
		/*
		colors.add(Shape.L_COLOR);
		colors.add(Shape.J_COLOR);
		colors.add(Shape.O_COLOR);
		colors.add(Shape.T_COLOR);
		colors.add(Shape.S_COLOR);
		colors.add(Shape.Z_COLOR);
		colors.add(Shape.I_COLOR);
		*/
		
		miscShape = new Shape(shapes.get(0), Shape.COLORS[0]);
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) { //when mouse is pressed
				System.out.println("Press at " + e.getX() + "-" + e.getY());
				miscShape.translateY(-1);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) { //when mouse is released
				
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent e) { //probably won't use this
				
			}
			
			@Override
			public void mouseMoved(MouseEvent e) { //can be used to change cursor to "hand" when over a button
				/*
				if(!currentCursor.equals("HAND_CURSOR")) {
					currentCursor = "HAND_CURSOR";
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				*/
			}
		});
				
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Key Pressed: " + e.getKeyChar() + " Key Code: " + e.getKeyCode());
				//space is teleport down
				if(e.getKeyCode() == 40) interval = fastInt;
				if(e.getKeyCode() == 37) {
					if(miscShape.canMoveLeft()) miscShape.translateX(-1);
				}
				if(e.getKeyCode() == 39) {
					if(miscShape.canMoveRight()) miscShape.translateX(1);
				}
				if(e.getKeyCode() == 38) {
					miscShape.rotateRightIfCan();
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) { //probably won't use this

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == 40) interval = regInt;
			}
		});
		setFocusable(true);
	}
	
	public void paintComponent(Graphics G) { //runs once per update
		super.paintComponent(G);
		this.setBackground(Color.WHITE);
		G.setColor(Color.BLACK);
		G.drawRect(boardWindow.x1, boardWindow.y1, boardWindow.getWidth(), boardWindow.getHeight());

		if(System.currentTimeMillis() - t > interval) {
			t = System.currentTimeMillis();
			if(miscShape.canFall()) miscShape.translateY(1);
			else if(!gameOver) {
				addShapeToBoard(miscShape);
				int tempLine = lines;
				lines += removeRows();
				if(lines != tempLine) {
					level = lines/10 + 1;
					regInt = (int) (500*Math.pow(0.9, lines));
							;
					System.out.println(lines + " Lines");
				}
				
				
				int rand = (int)(Math.random() * shapes.size());
				miscShape = new Shape(shapes.get(rand), Shape.COLORS[rand]);
				for(Point p : miscShape.thisShape) {
					if(board[p.x][p.y] && !gameOver) {
						System.out.println("GAMEOVER");
						gameOver = true;
					}
				}
			}
		}
		
		//draw grid
		drawBoard(G);
		drawShape(G, miscShape);
		
		G.drawString("Lines: " + lines, 500, 200);
		G.drawString("Level: " + (lines/10 + 1), 500, 250);
		
	}
	
	public void drawBoard(Graphics G) {
		for(int y = 0; y < boardHeight; y++) {
			for(int x = 0; x < boardWidth; x++) {
				if(board[x][y]) {
					G.setColor(boardColor[x][y]);
					G.fillRect(boardWindow.x1 + x * boardWindow.getWidth() / boardWidth, 
							boardWindow.y1 + y * boardWindow.getHeight() / boardHeight, 
							boardWindow.getWidth() / boardWidth, 
							boardWindow.getHeight() / boardHeight);
					G.setColor(Color.BLACK);
					G.drawRect(boardWindow.x1 + x * boardWindow.getWidth() / boardWidth, 
							boardWindow.y1 + y * boardWindow.getHeight() / boardHeight, 
							boardWindow.getWidth() / boardWidth, 
							boardWindow.getHeight() / boardHeight);
				}
			}
		}
	}
	
	public void drawShape(Graphics G, Shape s) {
		G.setColor(s.thisColor);
		for(Point p : s.thisShape) {
			G.setColor(s.thisColor);
			G.fillRect(boardWindow.x1 + p.x * boardWindow.getWidth() / boardWidth, 
					boardWindow.y1 + p.y * boardWindow.getHeight() / boardHeight, 
					boardWindow.getWidth() / boardWidth, 
					boardWindow.getHeight() / boardHeight);
			G.setColor(Color.BLACK);
			G.drawRect(boardWindow.x1 + p.x * boardWindow.getWidth() / boardWidth, 
					boardWindow.y1 + p.y * boardWindow.getHeight() / boardHeight, 
					boardWindow.getWidth() / boardWidth, 
					boardWindow.getHeight() / boardHeight);
		}
	}
	
	public void addShapeToBoard(Shape s) {
		for(Point p : s.thisShape) {
			board[p.x][p.y] = true;
			boardColor[p.x][p.y] = s.thisColor;
		}
	}
	
	public boolean rowIsFull(int y) {
		for(int x = 0; x < boardWidth; x++) {
			if(!board[x][y]) return false;
		}
		return true;
	}
	
	public int removeRows() {
		int removed = 0;
		for(int y = 0; y < boardHeight; y++) {
			boolean full = true;
			for(int x = 0; x < boardWidth; x++) {
				if(!board[x][y]) full = false;
			}
			if(full) {
				for(int x = 0; x < boardWidth; x++) {
					board[x][y] = false;					
				}
				for(int y2 = y; y2 >= 1; y2--) {
					for(int x = 0; x < boardWidth; x++) {
						board[x][y2] = board[x][y2 - 1];
						boardColor[x][y2] = boardColor[x][y2 - 1];
					}
				}
				y--;
				removed++;
			}
		}
		return removed;
	}
}