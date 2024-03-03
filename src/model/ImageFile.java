package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFile {
	private String FileName;
	private int width;
	private int height;
	private BufferedImage img;

	/**
	 * @param fileName
	 */
	public ImageFile() {
		FileName = "";
		width = height = 0;
	}

	/**
	 * @return the weight
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	public ImageFile(String fileName) {
		FileName = fileName;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return  FileName ;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public void readImg() {

		try {
			this.img = ImageIO.read(new File(this.getFileName()));
			this.width = img.getWidth();
			this.height = img.getHeight();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the img
	 */
	public BufferedImage getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(BufferedImage img) {
		this.img = img;
	}

}
