package test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Handle_Operations;
import view.UI;

public class Main {
	public static void main(String[] args) {
     try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		new Handle_Operations(new UI());
	}
}
  