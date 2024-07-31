package engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public final class Window{
	
	private JFrame frame;
	private BufferedImage image;
	private Canvas canvas;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;

	Dimension dimension;

	private int[] pixels;

	public Window(GameContainer gc) {
		image = new BufferedImage(gc.getWIDTH(), gc.getHEIGHT(), BufferedImage.TYPE_INT_RGB);
		dimension = new Dimension((int) (gc.getWIDTH() * gc.getScale()), (int) (gc.getHEIGHT() * gc.getScale()));
		canvas = new Canvas();
		canvas.setPreferredSize(dimension);
		canvas.setMinimumSize(dimension);
		canvas.setMaximumSize(dimension);

		frame.setTitle(gc.getTITLE());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		graphics = bufferStrategy.getDrawGraphics();

		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public void update() {
		graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bufferStrategy.show();
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
}