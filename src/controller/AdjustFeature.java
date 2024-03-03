package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Console;

import model.AdjustFeatureInterface;

public class AdjustFeature implements AdjustFeatureInterface<BufferedImage> {

	public static AdjustFeature getInstance() {
		return new AdjustFeature();
	}

	@Override
	public BufferedImage setRed(BufferedImage t) {
		// TODO Auto-generated method stub
		for (int y = 0; y < t.getHeight(); y++) {
			for (int x = 0; x < t.getWidth(); x++) {
				int pixel = t.getRGB(x, y);
				int red = (pixel >> 16) & 0xFF;
				t.setRGB(x, y, red << 16);
			}
		}
		return t;
	}

	@Override
	public BufferedImage setGreen(BufferedImage t) {
		for (int y = 0; y < t.getHeight(); y++) {
			for (int x = 0; x < t.getWidth(); x++) {
				int pixel = t.getRGB(x, y);
				int green = (pixel >> 8) & 0xFF;
				t.setRGB(x, y, green << 8);
			}
		}
		return t;
	}

	@Override
	public BufferedImage setBlue(BufferedImage t) {
		for (int y = 0; y < t.getHeight(); y++) {
			for (int x = 0; x < t.getWidth(); x++) {
				int pixel = t.getRGB(x, y);
				int blue = pixel & 0xFF;
				t.setRGB(x, y, blue);
			}
		}
		return t;
	}


	public BufferedImage setFeature(BufferedImage t, double bright_Factor, double warmth_Factor,
			double tint_Factor, double contrast_Factor) {
		
		
		 int threadsCount = Runtime.getRuntime().availableProcessors();;
	        int width = t.getWidth();
	        int height = t.getHeight();
	        Thread[] threads = new Thread[threadsCount];

	        for (int i = 0; i < threadsCount; i++) {
	            final int left = width * i / threadsCount;
	            final int right = width * (i + 1) / threadsCount;
	            threads[i] = new Thread(() -> {
	                for (int x = left; x < right; x++) {
	                    for (int y = 0; y < height; y++) {
	                    	//brightness
	                    	Color color = new Color(t.getRGB(x, y));
	        				int red = (int) bound((color.getRed() + bright_Factor));
	        				int green = (int) bound((color.getGreen() + bright_Factor));
	        				int blue = (int) bound((color.getBlue() + bright_Factor));

	        				//tint
	        				green = (int) bound(green + tint_Factor);

	        				//warmth
	        				red = (int) bound((red + warmth_Factor));
	        				blue = (int) bound((blue - warmth_Factor));

	        				//contrast
	        				red = (int) bound((red - 128) * contrast_Factor + 128);
	        				green = (int) bound((green - 128) * contrast_Factor + 128);
	        				blue = (int) bound((blue - 128) * contrast_Factor + 128);

	        				Color newColor = new Color(red, green, blue);
	        				t.setRGB(x, y, newColor.getRGB());
	                    }
	                }
	            });
	            threads[i].start();
	        }

	        for (int i = 0; i < threadsCount; i++) {
	            try {
					threads[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		
	        
	        
		
		return t;
	
	}

	private static double bound(double value) {
		return Math.max(0, Math.min(255, value));
	}
}
