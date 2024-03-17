package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

	public BufferedImage setLightAndColor(BufferedImage t, double bright_Factor, double warmth_Factor,
			double tint_Factor, double contrast_Factor) {
		int threadsCount = Runtime.getRuntime().availableProcessors();
		
		int width = t.getWidth();
		int height = t.getHeight();
		Thread[] threads = new Thread[threadsCount];

		for (int i = 0; i < threadsCount; i++) {
			final int left = width * i / threadsCount;
			final int right = width * (i + 1) / threadsCount;
			threads[i] = new Thread(() -> {
				for (int x = left; x < right; x++) {
					for (int y = 0; y < height; y++) {
						// brightness
						Color color = new Color(t.getRGB(x, y));
						int red = (int) bound((color.getRed() + bright_Factor));
						int green = (int) bound((color.getGreen() + bright_Factor));
						int blue = (int) bound((color.getBlue() + bright_Factor));

						// tint
						green = (int) bound(green + tint_Factor);

						// warmth
						red = (int) bound((red + warmth_Factor));
						blue = (int) bound((blue - warmth_Factor));

						
						// contrast
						red = (int) bound((red - 128) * contrast_Factor + 128);
						green = (int) bound((green - 128) * contrast_Factor + 128);
						blue = (int) bound((blue - 128) * contrast_Factor + 128);
						
						Color newColor = new Color(red,green,blue);
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

	public BufferedImage setGrayImage(BufferedImage t) {
		for (int x = 0; x < t.getWidth(); x++) {
			for (int y = 0; y < t.getHeight(); y++) {
				Color color = new Color(t.getRGB(x, y));

				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();

				int gray = (red + green + blue) / 3;

				Color newColor = new Color(gray, gray, gray);
				t.setRGB(x, y, newColor.getRGB());
			}
		}
		return t;
	}

	public BufferedImage setNegativeImage(BufferedImage t) {
		for (int y = 0; y < t.getHeight(); y++) {
			for (int x = 0; x < t.getWidth(); x++) {
				Color color = new Color(t.getRGB(x, y));

				int red = 255 - color.getRed();
				int green = 255 - color.getGreen();
				int blue = 255 - color.getBlue();

				Color newColor = new Color(red, green, blue);
				t.setRGB(x, y, newColor.getRGB());
			}
		}
		return t;
	}
     

	public BufferedImage setNegativeGrayImage(BufferedImage t) {
		for (int y = 0; y < t.getHeight(); y++) {
			for (int x = 0; x < t.getWidth(); x++) {
				Color color = new Color(t.getRGB(x, y));
				
				int red = 255 - color.getRed();
				int green = 255 - color.getGreen();
				int blue = 255 - color.getBlue();
 
				int gray = (red + green + blue) / 3;
				Color newColor = new Color(gray, gray, gray);
				t.setRGB(x, y, newColor.getRGB());
			}
		}
		return t;
	}
	
	public BufferedImage setBlurImage(BufferedImage t) {
		// TODO Auto-generated method stub
		BufferedImage result = new BufferedImage(t.getWidth(), t.getHeight(), BufferedImage.TYPE_INT_RGB);
	    for (int y = 1; y < t.getHeight() - 1; y++) {
	        for (int x = 1; x < t.getWidth() - 1; x++) {
	            int sumRed = 0;
	            int sumGreen = 0;
	            int sumBlue = 0;
	            for (int dy = -1; dy <= 1; dy++) {
	                for (int dx = -1; dx <= 1; dx++) {
	                    Color color = new Color(t.getRGB(x+dx, y+dy));
	                    sumRed += color.getRed() ; 
	                    sumGreen += color.getGreen();
	                    sumBlue += color.getBlue();
	                }
	            }
	            int avgRed = sumRed / 9;
	            int avgGreen = sumGreen / 9;
	            int avgBlue = sumBlue / 9;

	            Color newcolor= new Color(avgRed, avgGreen, avgBlue);
	            result.setRGB(x, y, newcolor.getRGB());
	        }
	    }
		return result;
	}

	public BufferedImage setBinaryImage(BufferedImage t,int threshold) {
		// TODO Auto-generated method stub
		for(int y=0;y<t.getHeight();y++)
		{
			for(int x=0;x<t.getWidth();x++)
			{
				Color color=new Color(t.getRGB(x, y));
				
				int red=(int) (color.getRed()*0.299);
				int green=(int)(color.getGreen()*0.587);
				int blue=(int)(color.getBlue()*0.114);
				
				int avg=red+green+blue;
				if(avg>threshold)
				{
					t.setRGB(x, y, Color.WHITE.getRGB());
				}else
				{
					t.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		return t;
	}

	@Override
	public BufferedImage setLaplace(BufferedImage t,int type) {
		// TODO Auto-generated method stub
	    int mat1[][]= {
				 { 0,-1, 0}
				,{-1, 4,-1}
				,{ 0,-1, 0}
	     };
	    int mat2[][]= {
				 { 1,-2, 1}
				,{-2, 4,-2}
				,{ 1,-2, 1}
		};
		int mat3[][]= {
				 { 1, 1, 1}
				,{ 1,-8, 1}
				,{ 1, 1, 1}
		};
		
		BufferedImage newimg = new BufferedImage(t.getWidth(),t.getHeight(),BufferedImage.TYPE_INT_RGB);
		for(int y=1;y<t.getHeight()-1;y++)
		{
			for(int x=1;x<t.getWidth()-1;x++)
			{
				int colorRed=0;
				int colorGreen=0;
				int colorBlue=0;
				for(int i=-1;i<=1;i++)
				{
					for(int j=-1;j<=1;j++)
					{
						Color temp= new Color(t.getRGB(x+i, y+j));
						int red=temp.getRed();
						int green=temp.getGreen();
						int blue=temp.getBlue();
						if( type==1 )
						{
							colorRed+=mat1[i+1][j+1]*red;
							colorGreen+=mat1[i+1][j+1]*green;
							colorBlue+=mat1[i+1][j+1]*blue;
						}
						else if (type==2 )
						{
							colorRed+=mat2[i+1][j+1]*red;
							colorGreen+=mat2[i+1][j+1]*green;
							colorBlue+=mat2[i+1][j+1]*blue;
						}else
						{
							colorRed+=mat3[i+1][j+1]*red;
							colorGreen+=mat3[i+1][j+1]*green;
							colorBlue+=mat3[i+1][j+1]*blue;
						}
						
					}
				}
				colorRed=(int)bound(colorRed);
				colorGreen=(int)bound(colorGreen);
				colorBlue=(int)bound(colorBlue);
				Color newcolor=new Color(colorRed,colorGreen,colorBlue);
				newimg.setRGB(x, y, newcolor.getRGB());
			}
		}
		return newimg;
	}

	@Override
	public BufferedImage setBitPlance(BufferedImage t,int bit) {
		// TODO Auto-generated method stub
       
        for(int y=0;y<t.getHeight();y++)
        {
        	for(int x=0;x<t.getWidth();x++)
        	{
        		int color = t.getRGB(x, y);
        		int alpha = (color >> 24) & 0xff;
        		int red= (color >> 16) & 0xff;
        		int green = (color >> 8) & 0xff;
        		int blue = color & 0xff;
        		
        		red = ((red >> bit) & 1)*255;
        		green = ((green >> bit) & 1)*255;
        		blue = ((blue >> bit) & 1)*255;
        		
        		Color newcolor= new Color(red,green,blue);
        		t.setRGB(x, y, newcolor.getRGB());
        	}
        }
		return t;
	}

	@Override
	public BufferedImage setLowPassFilter(BufferedImage t) {
		// TODO Auto-generated method stub

		int mat[][]= {
				 { 0, 1, 1}
				,{ 1, 2, 1}
				,{ 0, 1, 0}
		};
		BufferedImage newimg=new BufferedImage(t.getWidth(), t.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y=1;y<t.getHeight()-1;y++)
		{
			for(int x=1;x<t.getWidth()-1;x++)
			{
				int colorRed=0;
				int colorGreen=0;
				int colorBlue=0;
				for(int i=-1;i<=1;i++)
				{
					for(int j=-1;j<=1;j++)
					{
						Color color=new Color(t.getRGB(x+i, y+j));
						
						colorRed+=color.getRed()*mat[i+1][j+1];
						colorGreen+=color.getGreen()*mat[i+1][j+1];
						colorBlue+=color.getBlue()*mat[i+1][j+1];
					}
				}
				colorRed=colorRed/8;
				colorGreen=colorGreen/8;
				colorBlue=colorBlue/8;
				Color newcolor= new Color(colorRed,colorGreen,colorBlue);
				newimg.setRGB(x, y, newcolor.getRGB());
			}
		}
		return newimg;	
	}

	@Override
	public BufferedImage setHighPassFilter(BufferedImage t, int type) {
		// TODO Auto-generated method stub
		
		int mat1[][]= {
				 {-1,-1,-1}
				,{-1, 9,-1}
				,{-1,-1,-1}
		};
		int mat2[][]= {
				 { 0,-1, 0}
				,{-1, 5,-1}
				,{ 0,-1, 0}
		};
	
		BufferedImage newimg=new BufferedImage(t.getWidth(), t.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y=1;y<t.getHeight()-1;y++)
		{
			for(int x=1;x<t.getWidth()-1;x++)
			{
				int colorRed=0;
				int colorGreen=0;
				int colorBlue=0;
				for(int i=-1;i<=1;i++)
				{
					for(int j=-1;j<=1;j++)
					{
						Color color=new Color(t.getRGB(x+i, y+j));
						
						if(type == 1)
						{
							colorRed+=color.getRed()*mat1[i+1][j+1];
						    colorGreen+=color.getGreen()*mat1[i+1][j+1];
						    colorBlue+=color.getBlue()*mat1[i+1][j+1];
						}else if(type == 2)
						{
							colorRed+=color.getRed()*mat2[i+1][j+1];
						    colorGreen+=color.getGreen()*mat2[i+1][j+1];
						    colorBlue+=color.getBlue()*mat2[i+1][j+1];
						}
						
					}
				}
				colorRed=(int)bound(colorRed);
				colorGreen=(int)bound(colorGreen);
				colorBlue=(int)bound(colorBlue);
				Color newcolor= new Color(colorRed,colorGreen,colorBlue);
				newimg.setRGB(x, y, newcolor.getRGB());
			}
		}
		return newimg;
	}

	@Override
	public BufferedImage setBorderSobel(BufferedImage t) {
		// TODO Auto-generated method stub
		int mat1[][]= {
				 {-1,-2,-1}
				,{ 0, 0, 0}
				,{ 1, 2, 1}
		};
		int mat2[][]= {
				 {-1, 0, 1}
				,{-2, 0, 2}
				,{-1, 0, 1}
		};
		
		BufferedImage newimg=new BufferedImage(t.getWidth(), t.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y=1;y<t.getHeight()-1;y++)
		{
			for(int x=1;x<t.getWidth()-1;x++)
			{
				int colorRedx=0;
				int colorGreenx=0;
				int colorBluex=0;
				
				int colorRedy=0;
				int colorGreeny=0;
				int colorBluey=0;
				for(int i=-1;i<=1;i++)
				{
					for(int j=-1;j<=1;j++)
					{
						Color color=new Color(t.getRGB(x+i, y+j));
						
							colorRedx+=color.getRed()*mat1[i+1][j+1];
						    colorGreenx+=color.getGreen()*mat1[i+1][j+1];
						    colorBluex+=color.getBlue()*mat1[i+1][j+1];
						
							colorRedy+=color.getRed()*mat2[i+1][j+1];
						    colorGreeny+=color.getGreen()*mat2[i+1][j+1];
						    colorBluey+=color.getBlue()*mat2[i+1][j+1];	
						
					}
				}
				
				int colorRedval=(int) Math.sqrt(Math.pow(colorRedx,2) + Math.pow(colorRedy,2));
				int colorGreenval= (int)Math.sqrt(Math.pow(colorGreenx,2) + Math.pow(colorGreeny,2));
				int colorBlueval= (int)Math.sqrt(Math.pow(colorBluex,2) + Math.pow(colorBluey,2));

				colorRedval=(int)bound(colorRedval);
				colorGreenval=(int)bound(colorGreenval);
				colorBlueval=(int)bound(colorBlueval);
				Color newcolor= new Color(colorRedval,colorGreenval,colorBlueval);
				newimg.setRGB(x, y, newcolor.getRGB());
			}
		}
		return newimg;
	}

	@Override
	public BufferedImage setBorderGradient(BufferedImage t) {
		// TODO Auto-generated method stub
		
		BufferedImage newimg=new BufferedImage(t.getWidth(), t.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y=1;y<t.getHeight()-1;y++)
		{
			for(int x=1;x<t.getWidth()-1;x++)
			{
				
				Color c1 = new Color(t.getRGB(x-1, y));
                Color c2 = new Color(t.getRGB(x+1, y));
                Color c3 = new Color(t.getRGB(x, y-1));
                Color c4 = new Color(t.getRGB(x, y+1));

                int red = (int)Math.sqrt(Math.pow(c2.getRed() - c1.getRed(),2) + 
                        Math.pow(c4.getRed() - c3.getRed(),2));
                int green = (int)Math.sqrt(Math.pow(c2.getGreen() - c1.getGreen(),2) + 
                        Math.pow(c4.getGreen() - c3.getGreen(),2));
                int blue = (int)Math.sqrt(Math.pow(c2.getBlue() - c1.getBlue(),2) + 
                        Math.pow(c4.getBlue() - c3.getBlue(),2));

                Color newColor = new Color(red, green, blue);

                newimg.setRGB(x,y,newColor.getRGB());
            }
		}
		return newimg;
	}

	@Override
	public BufferedImage setLog(BufferedImage t) {
		// TODO Auto-generated method stub
		 for (int y = 0; y < t.getHeight(); y++) {
	            for (int x = 0; x < t.getWidth(); x++) {
	               
	                Color color = new Color(t.getRGB(x, y));
	                int r = color.getRed();
	                int g = color.getGreen();
	                int b = color.getBlue();
	                
	                r = (int)(Math.log(1 + r) * 255 / Math.log(256));
	                g = (int)(Math.log(1 + g) * 255 / Math.log(256));
	                b = (int)(Math.log(1 + b) * 255 / Math.log(256));
	                
	                Color newColor = new Color(r, g, b);
	                t.setRGB(x, y, newColor.getRGB());
	            }
	        }
		return t;
	}

	@Override
	public BufferedImage setPowerLaw(BufferedImage t,double gamma) {
		// TODO Auto-generated method stub
		 for(int y = 0; y < t.getHeight(); y++){
	            for(int x = 0; x < t.getWidth(); x++){
	            	
                     Color color=new Color(t.getRGB(x, y));
	                
	                int red = color.getRed();
	                int green = color.getGreen();
	                int blue = color.getBlue();

	                red = (int) (255 * Math.pow((double) red / 255, gamma));
	                green = (int) (255 * Math.pow((double) green / 255, gamma));
	                blue = (int) (255 * Math.pow((double) blue / 255, gamma));
	                
	                Color newcolor=new Color(red,green,blue);
	                t.setRGB(x, y, newcolor.getRGB());
	            }
	        }
		return t;
	}

	private static double bound(double value) {
		return Math.max(0, Math.min(255, value));
	}
	
}
