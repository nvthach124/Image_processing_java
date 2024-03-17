package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;

import view.UI;

public class ActionRadioButton implements ActionListener {
	UI view;

	/**
	 * @param view
	 */
	public ActionRadioButton(UI view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Enumeration<AbstractButton> enume = view.buttonGroup.getElements();
		while (enume.hasMoreElements()) {
			AbstractButton ab = enume.nextElement();
			if (ab.isSelected()) {
				if (ab.getText().equals("Gray Image"))
					view.adjustGrayImage();

				if (ab.getText().equals("Negative Image"))
					view.adjustNegativeImage();

				if (ab.getText().equals("Negative Gray Image"))
					view.adjustNegativeGrayImage();

				if (ab.getText().equals("Binary Image")) {
					view.binaryimgSlider.setEnabled(true);
				} else {
					view.binaryimgSlider.setEnabled(false);
				}

				if (ab.getText().equals("RGB")) {
					view.buttonBlue.setEnabled(true);
					view.buttonGreen.setEnabled(true);
					view.buttonRed.setEnabled(true);
				} else {
					view.buttonBlue.setEnabled(false);
					view.buttonGreen.setEnabled(false);
					view.buttonRed.setEnabled(false);
				}

				if (ab.getText().equals("Color Image Processing")) {
					view.brightnessSlider.setEnabled(true);
					view.ContrastSlider.setEnabled(true);
					view.WarmthSlider.setEnabled(true);
					view.TintSlider.setEnabled(true);

				} else {
					view.brightnessSlider.setEnabled(false);
					view.ContrastSlider.setEnabled(false);
					view.WarmthSlider.setEnabled(false);
					view.TintSlider.setEnabled(false);
				}

				if (ab.getText().equals("Blur Image  || Constant:"))
					view.ConstantBlur.setEnabled(true);
				else
					view.ConstantBlur.setEnabled(false);

				if (ab.getText().equals("Laplace Image || Type:"))
					view.adjustLaplaceImage();

				if (ab.getText().equals("Bit Plance Sciling || Bit:"))
					view.adjustBitPlanceSciling();

				if (ab.getText().equals("High Pass Filter || Type:"))
					view.adjustHightPassFilter();

				if (ab.getText().equals("Low Pass Filter"))
					view.adjustLowPassFilter();

				if (ab.getText().equals("Border Sobel"))
					view.adjustSobel();

				if (ab.getText().equals("Border Gradient"))
					view.adjustGradient();

				if (ab.getText().equals("Logarit Image"))
					view.adjustLog();

				if (ab.getText().equals("Power Law || Gamma:")) {
					view.textGamma.setEnabled(true);
					view.adjustPowerLaw();
				} else
					view.textGamma.setEnabled(false);
			}
		}
	}

}
