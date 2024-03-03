package model;

import java.awt.image.BufferedImage;

public interface AdjustFeatureInterface <T>{
	  public T setRed(T t);
	  public T setGreen(T t);
	  public T setBlue(T t);
      public T setFeature(T t, double bright_Factor, double warmth_Factor,
  			double tint_Factor, double contrast_Factor) ;
}
