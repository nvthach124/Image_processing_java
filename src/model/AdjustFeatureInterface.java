package model;

import java.awt.image.BufferedImage;

public interface AdjustFeatureInterface <T>{
	  public T setRed(T t);
	  public T setGreen(T t);
	  public T setBlue(T t);
      public T setLightAndColor(T t, double bright_Factor, double warmth_Factor,
  			double tint_Factor, double contrast_Factor) ;
      public T setGrayImage(T t);
      public T setNegativeImage(T t);
      public T setBlurImage(T t);
      public T setLaplace(T t, int type);
      public T setBitPlance(T t,int bit);
      public T setLowPassFilter(T t);
      public T setHighPassFilter(T t,int type);
      public T setBorderSobel(T t);
      public T setBorderGradient(T t);
      public T setLog(T t);
      public T setPowerLaw(T t,double gammma);
}
