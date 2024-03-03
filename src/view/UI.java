package view;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AdjustFeature;
import controller.Handle_Operations;
import model.ImageFile;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Label;
import java.awt.MenuItem;

import javax.swing.SpringLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel Display;
	private JLabel Warmth_p;
	private JLabel Contrast_p;
	private JLabel Brightness_p;
	private JLabel JRGB;
	private Button buttonBlue;
	private Button buttonGreen;
	private Button buttonRed;
	private ImageIcon icon;
	public ImageFile imgfile;
	private JSlider ContrastSlider;
	private JLabel JWarmth;
	private JSlider WarmthSlider;
	private JSlider brightnessSlider;
	private AdjustFeature adjustFeature;
	private JSlider TintSlider;
	private JLabel Tint_p;
	private int warmth_Factor;
	private double contrast_Factor;
	private int bright_Factor;
	private int tint_Factor;

	/**
	 * Launch the application.
	 */

	public UI() {
		setBackground(new Color(0, 0, 0));
		init();
		this.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		this.setTitle("Picture Processing");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1227, 683);
		this.setLocationRelativeTo(null);

		imgfile = new ImageFile();
		Handle_Operations ac = new Handle_Operations(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu MenuFile = new JMenu("File");
		MenuFile.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(MenuFile);

		JMenuItem MenuItemOpen = new JMenuItem("Open File...", KeyEvent.VK_O);
		MenuItemOpen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		MenuItemOpen.addActionListener(ac);
		MenuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

		MenuFile.add(MenuItemOpen);

		JMenuItem MenuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
		MenuFile.add(MenuItemSave);
		MenuItemSave.addActionListener(ac);
		MenuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

		JMenu MenuAbout = new JMenu("About me");
		MenuAbout.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(MenuAbout);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(68, 68, 68));

		setContentPane(contentPane);

		JLabel JBrightness = new JLabel("Brightness");
		JBrightness.setForeground(new Color(255, 255, 255));
		JBrightness.setBounds(962, 227, 87, 31);
		JBrightness.setFont(new Font("Tahoma", Font.PLAIN, 18));

		brightnessSlider = new JSlider();
		brightnessSlider.setBounds(962, 264, 200, 22);
		brightnessSlider.setName("brightnessSlider");
		brightnessSlider.addChangeListener(ac);

		JLabel JcontrastSlider = new JLabel("Contrast");
		JcontrastSlider.setForeground(new Color(255, 255, 255));
		JcontrastSlider.setBounds(962, 310, 87, 22);
		JcontrastSlider.setFont(new Font("Tahoma", Font.PLAIN, 18));

		ContrastSlider = new JSlider();
		ContrastSlider.setBounds(962, 342, 200, 22);
		ContrastSlider.setName("contrastSlider");
		ContrastSlider.addChangeListener(ac);

		JWarmth = new JLabel("Warmth");
		JWarmth.setForeground(new Color(255, 255, 255));
		JWarmth.setBounds(962, 392, 76, 22);
		JWarmth.setFont(new Font("Tahoma", Font.PLAIN, 18));

		WarmthSlider = new JSlider();
		WarmthSlider.setName("warmthSlider");
		WarmthSlider.setBounds(962, 420, 200, 22);
		WarmthSlider.addChangeListener(ac);

		contentPane.setLayout(null);
		contentPane.add(ContrastSlider);
		contentPane.add(JcontrastSlider);
		contentPane.add(brightnessSlider);
		contentPane.add(JBrightness);
		contentPane.add(JWarmth);
		contentPane.add(WarmthSlider);

		Button buttonReset = new Button("Reset");
		buttonReset.setBounds(1025, 34, 97, 40);
		buttonReset.addActionListener(ac);
		buttonReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(buttonReset);

		buttonBlue = new Button("Blue");
		buttonBlue.setBounds(1123, 174, 66, 22);
		buttonBlue.addActionListener(ac);
		buttonBlue.setForeground(new Color(255, 255, 255));
		buttonBlue.setBackground(new Color(0, 0, 255));
		buttonBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonBlue.setFont(new Font("Dialog", Font.PLAIN, 16));
		contentPane.add(buttonBlue);

		buttonGreen = new Button("Green");
		buttonGreen.setBounds(1045, 174, 66, 21);
		buttonGreen.addActionListener(ac);
		buttonGreen.setBackground(new Color(0, 255, 64));
		buttonGreen.setFont(new Font("Dialog", Font.PLAIN, 16));
		contentPane.add(buttonGreen);

		buttonRed = new Button("Red");
		buttonRed.setBounds(962, 173, 66, 22);
		buttonRed.addActionListener(ac);
		buttonRed.setForeground(new Color(255, 255, 255));
		buttonRed.setBackground(new Color(255, 0, 0));
		buttonRed.setFont(new Font("Dialog", Font.PLAIN, 16));
		contentPane.add(buttonRed);

		JRGB = new JLabel("RGB");
		JRGB.setForeground(new Color(255, 255, 255));
		JRGB.setBounds(962, 120, 87, 31);
		JRGB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(JRGB);

		Brightness_p = new JLabel("0");
		Brightness_p.setForeground(new Color(255, 255, 255));
		Brightness_p.setBounds(1123, 227, 50, 31);
		Brightness_p.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(Brightness_p);

		Contrast_p = new JLabel("0");
		Contrast_p.setForeground(new Color(255, 255, 255));
		Contrast_p.setBounds(1126, 301, 47, 31);
		Contrast_p.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(Contrast_p);

		Warmth_p = new JLabel("0");
		Warmth_p.setForeground(new Color(255, 255, 255));
		Warmth_p.setBounds(1123, 383, 39, 31);
		Warmth_p.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(Warmth_p);

		JSeparator separator = new JSeparator();
		separator.setBounds(36, 10, 910, 11);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(23, 596, 910, 2);
		contentPane.add(separator_1);

		Display = new JLabel("");
		Display.setForeground(new Color(255, 255, 255));
		Display.setBackground(new Color(255, 255, 255));
		Display.setBounds(33, 24, 901, 562);
		contentPane.add(Display);

		TintSlider = new JSlider();
		TintSlider.setName("tintSlider");
		TintSlider.setBounds(962, 489, 200, 22);
		TintSlider.addChangeListener(ac);
		contentPane.add(TintSlider);

		JLabel JTint = new JLabel("Tint");
		JTint.setForeground(Color.WHITE);
		JTint.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JTint.setBounds(962, 461, 76, 22);
		contentPane.add(JTint);

		Tint_p = new JLabel("0");
		Tint_p.setForeground(Color.WHITE);
		Tint_p.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Tint_p.setBounds(1123, 452, 39, 31);
		contentPane.add(Tint_p);
		this.imgfile.setFileName("temp");
		try {
            File file = new File("D:\\temp.jpg");
            File file2 = new File("D:\\temp2.jpg");
            file.delete();
            file2.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void ShowImgOriginal(String fileNameIMG) {
		setDefault();
		String nameImg = fileNameIMG;
		System.out.println("Image filename: " + nameImg);

		this.imgfile.setFileName(nameImg);
		this.imgfile.readImg();
		try {
			FileInputStream fis = new FileInputStream(this.imgfile.getFileName());
			this.icon = new ImageIcon(this.imgfile.getFileName());
			System.out.println(icon.getIconWidth() + " " + icon.getIconHeight());

			int newWidth = 901, newHeight = 562;
			double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
			if (icon.getIconWidth() > icon.getIconHeight()) {
				newHeight = (int) (newWidth / aspectRatio);
			} else {
				newWidth = (int) (newHeight * aspectRatio);
			}
			ImageIcon scaledIcon = new ImageIcon(
					icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
			this.Display.setIcon(scaledIcon);

			String temp2 = "D:\\temp2.jpg";
			try {
				ImageIO.write(this.imgfile.getImg(), "jpg", new File(temp2));
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.Display.setBounds(33, 24, 901, 562);

			contentPane.add(Display);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"File name " + this.imgfile.getFileName() + " was not found. Please check again !", "Information",
					JOptionPane.PLAIN_MESSAGE);
		}

	}

	public void changePictureToRed(String fileNameIMG) {
		// TODO Auto-generated method stub
		setDefault();
		String nameImg = fileNameIMG;
		this.imgfile.setFileName(nameImg);
		imgfile.readImg();
		int width = imgfile.getWidth();
		int height = imgfile.getHeight();
		BufferedImage redImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(), BufferedImage.TYPE_INT_RGB);

		redImage = adjustFeature.getInstance().setRed(this.imgfile.getImg());
		String temp = "D:\\temp.jpg";
		String temp2 = "D:\\temp2.jpg";
		try {
			ImageIO.write(redImage, "jpg", new File(temp));
			ImageIO.write(redImage, "jpg", new File(temp2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.imgfile.setFileName(temp);

		this.icon = new ImageIcon(this.imgfile.getFileName());
		System.out.println(icon.getIconWidth() + " " + icon.getIconHeight());

		int newWidth = 901, newHeight = 562;
		System.out.println(icon.getIconWidth() + " " + icon.getIconHeight());
		double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
		if (icon.getIconWidth() > icon.getIconHeight()) {
			newHeight = (int) (newWidth / aspectRatio);
		} else {
			newWidth = (int) (newHeight * aspectRatio);
		}
		ImageIcon scaledIcon = new ImageIcon(
				icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
		this.Display.setIcon(scaledIcon);

		this.Display.setBounds(33, 24, 901, 562);
		contentPane.add(Display);

	}

	public void changePictureToGreen(String fileNameIMG) {
		setDefault();
		String nameImg = fileNameIMG;
		this.imgfile.setFileName(nameImg);
		this.imgfile.readImg();
		int width = imgfile.getWidth();
		int height = imgfile.getHeight();
		BufferedImage greenImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		greenImage = adjustFeature.getInstance().setGreen(this.imgfile.getImg());

		String temp = "D:\\temp.jpg";
		String temp2 = "D:\\temp2.jpg";
		try {
			ImageIO.write(greenImage, "jpg", new File(temp));
			ImageIO.write(greenImage, "jpg", new File(temp2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.imgfile.setFileName(temp);
		System.out.println("Image filename: " + temp);

		this.icon = new ImageIcon(this.imgfile.getFileName());
		System.out.println(icon.getIconWidth() + " " + icon.getIconHeight());
		// Đặt kích thước mong muốn cho JLabel

		int newWidth = 901, newHeight = 562;

		// Điều chỉnh kích thước khi giữ nguyên tỉ lệ

		double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
		if (icon.getIconWidth() > icon.getIconHeight()) {
			newHeight = (int) (newWidth / aspectRatio);
		} else {
			newWidth = (int) (newHeight * aspectRatio);
		}
		ImageIcon scaledIcon = new ImageIcon(
				icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
		this.Display.setIcon(scaledIcon);

		this.Display.setBounds(33, 24, 901, 562);
		contentPane.add(Display);

	}

	public void changePictureToBlue(String fileNameIMG) {
		// TODO Auto-generated method stub
		setDefault();
		String nameImg = fileNameIMG;
		this.imgfile.setFileName(nameImg);
		imgfile.readImg();
		BufferedImage blueImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		blueImage = adjustFeature.getInstance().setBlue(this.imgfile.getImg());

		String temp = "D:\\temp.jpg";
		String temp2 = "D:\\temp2.jpg";
		try {
			ImageIO.write(blueImage, "jpg", new File(temp));
			ImageIO.write(blueImage, "jpg", new File(temp2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.imgfile.setFileName(temp);
		System.out.println("Image filename: " + temp);

		this.icon = new ImageIcon(this.imgfile.getFileName());
		System.out.println(icon.getIconWidth() + " " + icon.getIconHeight());
		// Đặt kích thước mong muốn cho JLabel

		int newWidth = 901, newHeight = 562;

		// Điều chỉnh kích thước khi giữ nguyên tỉ lệ

		double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
		if (icon.getIconWidth() > icon.getIconHeight()) {
			newHeight = (int) (newWidth / aspectRatio);
		} else {
			newWidth = (int) (newHeight * aspectRatio);
		}
		ImageIcon scaledIcon = new ImageIcon(
				icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
		this.Display.setIcon(scaledIcon);

		this.Display.setBounds(33, 24, 901, 562);
		contentPane.add(Display);

	}

	public void adjustBright(int val) {
		// TODO Auto-generated method stub
		int para = val * 2 - 100;
		bright_Factor = val - 50;
		Brightness_p.setText(para + "");

		this.imgfile.setFileName("D:\\temp2.jpg");
		imgfile.readImg();
		BufferedImage tempImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		tempImage = adjustFeature.getInstance().setFeature(this.imgfile.getImg(), bright_Factor, warmth_Factor,
				tint_Factor, contrast_Factor);
		String temp = "D:\\temp.jpg";
		try {
			ImageIO.write(tempImage, "jpg", new File(temp));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.imgfile.setFileName(temp);
		this.icon = new ImageIcon(this.imgfile.getFileName());
		int newWidth = 901, newHeight = 562;
		double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
		if (icon.getIconWidth() > icon.getIconHeight()) {
			newHeight = (int) (newWidth / aspectRatio);
		} else {
			newWidth = (int) (newHeight * aspectRatio);
		}
		ImageIcon scaledIcon = new ImageIcon(
				icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
		this.Display.setIcon(scaledIcon);

		this.Display.setBounds(33, 24, 901, 562);
		contentPane.add(Display);
	}

	public void adjustContrast(double val) {
		int para = (int) val * 2 - 100;
		contrast_Factor = (double) val / 200 + 0.75;
		Contrast_p.setText(para + "");

		this.imgfile.setFileName("D:\\temp2.jpg");
		imgfile.readImg();
		BufferedImage tempImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		tempImage = adjustFeature.getInstance().setFeature(this.imgfile.getImg(), bright_Factor, warmth_Factor,
				tint_Factor, contrast_Factor);

		String temp = "D:\\temp.jpg";
		try {
			ImageIO.write(tempImage, "jpg", new File(temp));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.imgfile.setFileName(temp);

		this.icon = new ImageIcon(this.imgfile.getFileName());
		int newWidth = 901, newHeight = 562;
		double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
		if (icon.getIconWidth() > icon.getIconHeight()) {
			newHeight = (int) (newWidth / aspectRatio);
		} else {
			newWidth = (int) (newHeight * aspectRatio);
		}
		ImageIcon scaledIcon = new ImageIcon(
				icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
		this.Display.setIcon(scaledIcon);

		this.Display.setBounds(33, 24, 901, 562);
		contentPane.add(Display);

	}

	public void adjustWarmth(int val) {
		// TODO Auto-generated method stub
		int para = val * 2 - 100;
		warmth_Factor = val - 50;
		Warmth_p.setText(para + "");

		this.imgfile.setFileName("D:\\temp2.jpg");
		imgfile.readImg();
		BufferedImage tempImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		tempImage = adjustFeature.getInstance().setFeature(this.imgfile.getImg(), bright_Factor, warmth_Factor,
				tint_Factor, contrast_Factor);

		String temp = "D:\\temp.jpg";
		try {
			ImageIO.write(tempImage, "jpg", new File(temp));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.imgfile.setFileName(temp);
		this.icon = new ImageIcon(this.imgfile.getFileName());
		int newWidth = 901, newHeight = 562;
		double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
		if (icon.getIconWidth() > icon.getIconHeight()) {
			newHeight = (int) (newWidth / aspectRatio);
		} else {
			newWidth = (int) (newHeight * aspectRatio);
		}
		ImageIcon scaledIcon = new ImageIcon(
				icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
		this.Display.setIcon(scaledIcon);

		this.Display.setBounds(33, 24, 901, 562);
		contentPane.add(Display);
	}

	public void adjustTint(int val) {
		// TODO Auto-generated method stub
		int para = val * 2 - 100;
		tint_Factor = val - 50;
		Tint_p.setText(para + "");

		this.imgfile.setFileName("D:\\temp2.jpg");
		imgfile.readImg();
		BufferedImage tempImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		tempImage = adjustFeature.getInstance().setFeature(this.imgfile.getImg(), bright_Factor, warmth_Factor,
				tint_Factor, contrast_Factor);

		String temp = "D:\\temp.jpg";
		try {
			ImageIO.write(tempImage, "jpg", new File(temp));
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.imgfile.setFileName(temp);
		this.icon = new ImageIcon(this.imgfile.getFileName());
		int newWidth = 901, newHeight = 562;
		double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
		if (icon.getIconWidth() > icon.getIconHeight()) {
			newHeight = (int) (newWidth / aspectRatio);
		} else {
			newWidth = (int) (newHeight * aspectRatio);
		}
		ImageIcon scaledIcon = new ImageIcon(
				icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
		this.Display.setIcon(scaledIcon);

		this.Display.setBounds(33, 24, 901, 562);
		contentPane.add(Display);
	}

	private void setDefault() {
		brightnessSlider.setValue(50);
		ContrastSlider.setValue(50);
		WarmthSlider.setValue(50);
		TintSlider.setValue(50);
		Brightness_p.setText("0");
		Contrast_p.setText("0");
		Warmth_p.setText("0");
		Tint_p.setText("0");
		bright_Factor = warmth_Factor = tint_Factor = 0;
		contrast_Factor = 1;
	}
}
