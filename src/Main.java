import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		int squareSize = 800;
		frame.setSize(squareSize, squareSize);
		Langton antie = new Langton(400,400,squareSize);
		frame.add(antie);
		
		
		frame.setTitle("Langton's Ant Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		antie.start();
	}

}
