package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.net.FileNameMap;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.UI;

public class ActionButton implements ActionListener {
	UI view;
	public String fileNameIMG;

	/**
	 * @param view
	 */
	public ActionButton(UI view) {
		this.view = view;
	}

//Thao tác với nút bấm
	@Override
	public void actionPerformed(ActionEvent e) {
		// các nút bấm
		String button = e.getActionCommand();

		if (button.equals("Open File...")) {
			System.out.println("Access");
			JFrame frame = new JFrame();
			JFileChooser fileChooser = new JFileChooser();

			// Hiển thị hộp thoại chọn tệp tin và lấy phản hồi từ người dùng
			int returnValue = fileChooser.showOpenDialog(frame);

			// Kiểm tra xem người dùng đã chọn tệp tin hay không
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				// Lấy đường dẫn của tệp tin được chọn
				String filePath = fileChooser.getSelectedFile().getAbsolutePath();
				this.fileNameIMG = filePath;
			}
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.view.ShowImgOriginal(this.fileNameIMG);
		} else if (button.equals("R")) {
			this.view.changePictureToRed(this.fileNameIMG);
		} else if (button.equals("G")) {
			this.view.changePictureToGreen(this.fileNameIMG);
		} else if (button.equals("B")) {
			this.view.changePictureToBlue(this.fileNameIMG);
		} else if (button.equals("Save")) {
			JFrame frame = new JFrame();
			JFileChooser saveChooser = new JFileChooser();
			saveChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int saveReturnVal = saveChooser.showSaveDialog(frame);
			if (saveReturnVal == JFileChooser.APPROVE_OPTION) {
				File saveFile = saveChooser.getSelectedFile();
				try (FileOutputStream fos = new FileOutputStream(saveFile)) {
					ImageIO.write(this.view.imgfile.getImg(), "jpg", fos);
					JOptionPane.showMessageDialog(null, "Saved Successfully!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		// các nút radiobutton
		

	}



}
