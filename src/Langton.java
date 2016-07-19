import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JComponent;

public class Langton extends JComponent{
	private int posX;
	private int posY;
	private int boardSize;
	private final int squareSize = 4;
	private final boolean[][] squareArray;
	private Random r;
	private int xChange;
	private int yChange;
	
	public Langton(int startX, int startY, int boardWidth) {
		this.r = new Random();
		this.posX = startX;
		this.posY = startY;
		this.xChange = 0;
		this.yChange = -this.squareSize;
		int number = (boardWidth/squareSize);
		this.boardSize = boardWidth;
		this.squareArray = new boolean [number][number];
		for (int k = 0; k < this.squareArray.length;k++) {
			for (int i = 0; i < this.squareArray[0].length; i++) {
				this.squareArray[k][i] = false;
			}
		}
	}
	
	public void start() {
		int fps = 120;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		while(this.posX >= 0 && this.posX < this.boardSize && this.posY >= 0 & this.posY < this.boardSize){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1){
				move();
				this.repaint();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				ticks = 0;
				timer = 0;
			}
		
		}
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2 = (Graphics2D) graphics;
		for (int k = 0; k < this.squareArray.length;k++) {
			for (int i = 0; i < this.squareArray[0].length; i++) {
				if (this.squareArray[k][i]) {
					g2.setColor(Color.BLACK);
				}
				else {
					g2.setColor(Color.WHITE);
				}
				g2.fillRect(k*this.squareSize, i*this.squareSize, this.squareSize, this.squareSize);
				
			}
		}
	}

	private void move() {
		int xNum = this.posX / this.squareSize;
		int yNum = this.posY / this.squareSize;
		
		if (this.squareArray[yNum][xNum]) {
			//turn left
			if(xChange == 0){ //if moving up or down
				xChange = yChange;
				yChange = 0;
			}else{ //if moving left or right
				yChange = -xChange;
				xChange = 0;
			}
		}
		else {
			//turn right
			if(xChange == 0){ //if moving up or down
				xChange = -yChange;
				yChange = 0;
			}else{ //if moving left or right
				yChange = xChange;
				xChange = 0;
			}
		}
		this.posX += xChange;
		this.posY += yChange;
		this.squareArray[yNum][xNum] = !this.squareArray[yNum][xNum];
		System.out.println(this.posX + " " + this.posY);
	}

}
