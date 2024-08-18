package engine;

import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;

import engine.gfx.Image;

public class Renderer {

	private int pixelWidth, pixelHeight;
	private int[] imagePixels;

	public Renderer(GameContainer gc) {

		pixelWidth = gc.getWidth();
		pixelHeight = gc.getHeight();

		Raster raster = gc.getWindow().getImage().getRaster();
		DataBuffer dataBuffer = raster.getDataBuffer();
		imagePixels = ((DataBufferInt) dataBuffer).getData();
	}

	public void clear() {
		for (int i = 0; i < imagePixels.length; i++) {
			imagePixels[i] = 0;
		}
	}

	public void drawImage(Image image, int offX, int offY) {
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				setPixel(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
			}
		}
	}

	private void setPixel(int x, int y, int value) {
		if ((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || value == 0xffff00ff) {
			return;
		}
		imagePixels[x + y * pixelWidth] = value;
	}
}
