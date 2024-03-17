package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import view.UI;

public class ActionKey implements KeyListener {
	UI view;

	public ActionKey(UI view) {
		this.view = view;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		JTextField nameText = (JTextField) e.getSource();
		if (c == KeyEvent.VK_ENTER) {

			for (int i = 1; i <= Integer.parseInt(view.ConstantBlur.getText()); i++) {
				view.adjustBlurImage();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
