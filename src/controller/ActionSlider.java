package controller;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.UI;

public class ActionSlider implements ChangeListener{
	UI view;
	
	public ActionSlider(UI view) {
		this.view = view;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JSlider source = (JSlider) e.getSource();
		if (source.getName().equals("brightnessSlider")) {
			view.adjustBright(source.getValue());
		} else if (source.getName().equals("contrastSlider")) {
			view.adjustContrast(source.getValue());
		} else if (source.getName().equals("warmthSlider")) {
			view.adjustWarmth(source.getValue());
		} else if (source.getName().equals("tintSlider")) {
			view.adjustTint(source.getValue());
		}else if (source.getName().equals("binaryimgSlider")) {
			view.adjustBinaryImage(source.getValue());
		}
	}

}
