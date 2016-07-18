import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JComponent;

public class Langton extends JComponent{
	private int posX;
	private int posY;
	private final int squareSize = 20;
	private final boolean[][] squareArray;
	private Random r;
	
	public Langton(int startX, int startY, int boardWidth) {
		this.r = new Random();
		this.posX = startX;
		this.posY = startY;
		int number = (boardWidth/squareSize);
		this.squareArray = new boolean [number][number];
		for (int k = 0; k < this.squareArray.length;k++) {
			for (int i = 0; i < this.squareArray[0].length; i++) {
				if (r.nextBoolean()) {
					this.squareArray[k][i] = true;
				}
				else {
					this.squareArray[k][i] = false;
				}
			}
		}
	}
	
	public void start() {
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		while(true){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1){
				move();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				ticks = 0;
				timer = 0;
			}
		
		}
	}
	

	private void render() {
		Graphics2D g2 = (Graphics2D) g;
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
		int xNum = this.posX / 10;
		int yNum = this.posY / 10;
		int xChange = 0, yChange = -10;
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
		super.repaint();
		System.out.println(this.posX + " " + this.posY);
	}

}
