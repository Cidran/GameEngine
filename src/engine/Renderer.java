package engine;

import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;

public class Renderer {

	private int[] imagePixels;

	public Renderer(GameContainer gc) {
		Raster raster = gc.getWindow().getImage().getRaster();
		DataBuffer dataBuffer = raster.getDataBuffer();
		imagePixels = ((DataBufferInt) dataBuffer).getData();
	}

	public void clear() {
		for (int i = 0; i < imagePixels.length; i++) {
			imagePixels[i] = 0;
		}
	}
}
