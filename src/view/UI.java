package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.lang.invoke.ConstantBootstraps;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

import controller.ActionButton;
import controller.ActionKey;
import controller.ActionRadioButton;
import controller.ActionSlider;
import controller.AdjustFeature;
import model.ImageFile;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	private JLabel Display;
	private JLabel Tint_p;
	private JLabel Warmth_p;
	private JLabel Brightness_p;
	private JLabel Contrast_p;
	private JLabel binaryimg_p;
	private JLabel JBrightness;
	private JLabel Jcontrast;
	private JLabel JWarmth;
	private JLabel JTint;

	private ImageIcon icon;
	public ImageFile imgfile;
	private AdjustFeature adjustFeature;
	private int warmth_Factor;
	private double contrast_Factor;
	private int bright_Factor;
	private int tint_Factor;
	public int binaryimg_Factor = 128;

	public JSlider WarmthSlider;
	public JSlider TintSlider;
	public JSlider ContrastSlider;
	public JSlider brightnessSlider;
	public JSlider binaryimgSlider;
	
    public ButtonGroup buttonGroup;
	private JToggleButton ToggleButton;
	private JRadioButton RadioButtonNegativeImage;
	private JRadioButton RadioButtonGrayImage;
	private JRadioButton RadioButtonRgb;
	private JRadioButton RadioButtonLaC;
	private JRadioButton RadioButtonBlurImage;
	private JRadioButton RadioButtonNegativeGrayImage;
	private JRadioButton RadioButtonBinaryImage;
	private JRadioButton RadioButtonBitPlane;
	private JRadioButton RadioButtonLPF;
	private JRadioButton RadioButtonHPF;
	private JRadioButton RadioButtonBorderSobel;
	private JRadioButton RadioButtonBorderGradient;
	private JRadioButton RadioButtonLog;
	private JRadioButton RadioButtonPowerLaw;
	private JRadioButton RadioButtonLaplace;
	public Button buttonRed;
	public Button buttonGreen;
	public Button buttonBlue;

	private JButton buttonReset;
    private JButton buttonSave;
	private JButton buttonPlay;
	
	public JTextField ConstantBlur;
	public JTextField textGamma;

	private JComboBox comboBoxLaplace;
	private JComboBox comboBoxBPS;
	private JComboBox comboBoxHPF;
	
	
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
		setBounds(100, 100, 1374, 799);
		this.setLocationRelativeTo(null);

		imgfile = new ImageFile();
		imgfile = new ImageFile();
		ActionButton ab = new ActionButton(this);
		ActionSlider as = new ActionSlider(this);
		ActionRadioButton ar = new ActionRadioButton(this);
		ActionKey ak = new ActionKey(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu MenuFile = new JMenu("File");
		MenuFile.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(MenuFile);

		JMenuItem MenuItemOpen = new JMenuItem("Open File...", KeyEvent.VK_O);
		MenuItemOpen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		MenuItemOpen.addActionListener(ab);
		MenuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

		MenuFile.add(MenuItemOpen);

		JMenuItem MenuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
		MenuFile.add(MenuItemSave);
		MenuItemSave.addActionListener(ab);
		MenuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

		JMenu MenuAbout = new JMenu("About me");
		MenuAbout.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(MenuAbout);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(36, 10, 1071, 11);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(36, 724, 1071, 2);
		contentPane.add(separator_1);

		Display = new JLabel("");
		Display.setBounds(33, 24, 1074, 690);
		Display.setForeground(new Color(255, 255, 255));
		Display.setBackground(new Color(255, 255, 255));
		contentPane.add(Display);

		buttonRed = new Button("R");
		buttonRed.setBounds(1178, 189, 32, 22);
		buttonRed.setForeground(Color.WHITE);
		buttonRed.setFont(new Font("Dialog", Font.PLAIN, 16));
		buttonRed.setBackground(Color.RED);
		buttonRed.addActionListener(ab);
		contentPane.add(buttonRed);

		JBrightness = new JLabel("Brightness");
		JBrightness.setBounds(1117, 588, 66, 22);
		JBrightness.setForeground(new Color(0, 0, 0));
		JBrightness.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(JBrightness);

		buttonGreen = new Button("G");
		buttonGreen.setBounds(1234, 189, 32, 22);
		buttonGreen.setFont(new Font("Dialog", Font.PLAIN, 16));
		buttonGreen.setBackground(new Color(0, 255, 64));
		buttonGreen.addActionListener(ab);
		contentPane.add(buttonGreen);

		buttonBlue = new Button("B");
		buttonBlue.setBounds(1289, 189, 32, 22);
		buttonBlue.setForeground(Color.WHITE);
		buttonBlue.setFont(new Font("Dialog", Font.PLAIN, 16));
		buttonBlue.setBackground(Color.BLUE);
		buttonBlue.addActionListener(ab);
		contentPane.add(buttonBlue);

		buttonBlue.setEnabled(false);
		buttonGreen.setEnabled(false);
		buttonRed.setEnabled(false);

		Brightness_p = new JLabel("0");
		Brightness_p.setBounds(1307, 584, 32, 31);
		Brightness_p.setForeground(new Color(0, 0, 0));
		Brightness_p.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(Brightness_p);

		brightnessSlider = new JSlider();
		brightnessSlider.setBounds(1194, 589, 103, 22);
		brightnessSlider.setBackground(new Color(255, 255, 255));
		brightnessSlider.setName("brightnessSlider");
		brightnessSlider.addChangeListener(as);
		contentPane.add(brightnessSlider);

		Contrast_p = new JLabel("0");
		Contrast_p.setBounds(1307, 617, 32, 31);
		Contrast_p.setForeground(new Color(0, 0, 0));
		Contrast_p.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(Contrast_p);

		Jcontrast = new JLabel("Contrast");
		Jcontrast.setBounds(1117, 621, 58, 22);
		Jcontrast.setForeground(new Color(0, 0, 0));
		Jcontrast.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(Jcontrast);

		ContrastSlider = new JSlider();
		ContrastSlider.setBounds(1194, 621, 103, 22);
		ContrastSlider.setBackground(new Color(255, 255, 255));
		ContrastSlider.setName("contrastSlider");
		ContrastSlider.addChangeListener(as);
		contentPane.add(ContrastSlider);

		JWarmth = new JLabel("Warmth");
		JWarmth.setBounds(1117, 650, 66, 22);
		JWarmth.setForeground(new Color(0, 0, 0));
		JWarmth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(JWarmth);

		WarmthSlider = new JSlider();
		WarmthSlider.setBounds(1194, 650, 103, 22);
		WarmthSlider.setBackground(new Color(255, 255, 255));
		WarmthSlider.setForeground(new Color(0, 0, 0));
		WarmthSlider.setName("warmthSlider");
		WarmthSlider.addChangeListener(as);
		contentPane.add(WarmthSlider);

		JTint = new JLabel("Tint");
		JTint.setBounds(1117, 681, 47, 22);
		JTint.setForeground(new Color(0, 0, 0));
		JTint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(JTint);

		TintSlider = new JSlider();
		TintSlider.setBounds(1194, 682, 103, 22);
		TintSlider.setBackground(new Color(255, 255, 255));
		TintSlider.setName("tintSlider");
		TintSlider.addChangeListener(as);
		contentPane.add(TintSlider);

		Tint_p = new JLabel("0");
		Tint_p.setBounds(1307, 677, 39, 31);
		Tint_p.setForeground(new Color(0, 0, 0));
		Tint_p.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(Tint_p);

		brightnessSlider.setEnabled(false);
		ContrastSlider.setEnabled(false);
		WarmthSlider.setEnabled(false);
		TintSlider.setEnabled(false);

		Warmth_p = new JLabel("0");
		Warmth_p.setBounds(1307, 646, 39, 31);
		Warmth_p.setForeground(new Color(0, 0, 0));
		Warmth_p.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(Warmth_p);

		binaryimgSlider = new JSlider();
		binaryimgSlider.setName("binaryimgSlider");
		binaryimgSlider.setEnabled(false);
		binaryimgSlider.setBackground(Color.WHITE);
		binaryimgSlider.setBounds(1228, 254, 90, 22);
		binaryimgSlider.addChangeListener(as);
		contentPane.add(binaryimgSlider);

		binaryimg_p = new JLabel("128");
		binaryimg_p.setForeground(Color.BLACK);
		binaryimg_p.setFont(new Font("Tahoma", Font.PLAIN, 12));
		binaryimg_p.setBounds(1328, 251, 21, 31);
		contentPane.add(binaryimg_p);

		buttonSave = new JButton();
		buttonSave.setName("Save");
		buttonSave.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		buttonSave.setBounds(1129, 10, 39, 40);
		ImageIcon iconbuttonsave = new ImageIcon(UI.class.getResource("/image/saveBlack.png"));
		Image imgbuttonsave = iconbuttonsave.getImage().getScaledInstance(buttonSave.getWidth(), buttonSave.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		
		buttonSave.setIcon(new ImageIcon(imgbuttonsave));
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = new JFrame();
				JFileChooser saveChooser = new JFileChooser();
				saveChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int saveReturnVal = saveChooser.showSaveDialog(frame);
				if (saveReturnVal == JFileChooser.APPROVE_OPTION) {
					File saveFile = saveChooser.getSelectedFile();
					try (FileOutputStream fos = new FileOutputStream(saveFile)) {
						ImageIO.write(imgfile.getImg(), "jpg", fos);
						JOptionPane.showMessageDialog(null, "Saved Successfully!");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		contentPane.add(buttonSave);
		
		buttonPlay = new JButton();
		buttonPlay.setName("Play");
		buttonPlay.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		buttonPlay.setBounds(1194, 10, 39, 40);
		ImageIcon iconbuttonplay = new ImageIcon(UI.class.getResource("/image/playOrange.png"));
		Image imgbuttonplay = iconbuttonplay.getImage().getScaledInstance(buttonPlay.getWidth(), buttonPlay.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		buttonPlay.setIcon(new ImageIcon(imgbuttonplay));
		buttonPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonPlay.addActionListener(ar);
			}
		});
		contentPane.add(buttonPlay);
		
		buttonReset = new JButton();
		buttonReset.setBounds(1258, 10, 39, 40);
		ImageIcon iconbuttonreset = new ImageIcon(UI.class.getResource("/image/redoOrange.png"));
		Image imgbuttonreset = iconbuttonreset.getImage().getScaledInstance(buttonReset.getWidth(), buttonReset.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		buttonReset.setIcon(new ImageIcon(imgbuttonreset));
		buttonReset.setName("Reset");
		buttonReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		buttonReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ShowImgOriginal(ab.fileNameIMG);
			}
		});
		contentPane.add(buttonReset);

		ToggleButton = new JToggleButton();
		ToggleButton.setBounds(1311, 10, 39, 40);
		ImageIcon iconTogglebutton = new ImageIcon(UI.class.getResource("/image/dark_mode.png"));
		Image temp = iconTogglebutton.getImage().getScaledInstance(ToggleButton.getWidth(), ToggleButton.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		ToggleButton.setIcon(new ImageIcon(temp));
		ToggleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					System.out.println("Nút công tắc đã được bật.");
					setBackGroundToDark();
				} else {
					System.out.println("Nút công tắc đã được tắt.");
					setBackGroundToLight();
				}
			}
		});

		contentPane.add(ToggleButton);

		buttonGroup = new ButtonGroup();
		RadioButtonNegativeImage = new JRadioButton("Negative Image");
		RadioButtonNegativeImage.setBounds(1113, 132, 168, 21);
		RadioButtonNegativeImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonNegativeImage.setBackground(new Color(255, 255, 255));
		RadioButtonNegativeImage.setName("NegativeImage");
		RadioButtonNegativeImage.addActionListener(ar);
		contentPane.add(RadioButtonNegativeImage);

		RadioButtonGrayImage = new JRadioButton("Gray Image");
		RadioButtonGrayImage.setName("GrayImage");
		RadioButtonGrayImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonGrayImage.setBackground(new Color(255, 255, 255));
		RadioButtonGrayImage.setBounds(1113, 102, 168, 21);
		RadioButtonGrayImage.addActionListener(ar);
		contentPane.add(RadioButtonGrayImage);

		RadioButtonRgb = new JRadioButton("RGB");
		RadioButtonRgb.setName("GrayImage");
		RadioButtonRgb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonRgb.setBackground(new Color(255, 255, 255));
		RadioButtonRgb.setBounds(1113, 191, 59, 21);
		RadioButtonRgb.addActionListener(ar);
		contentPane.add(RadioButtonRgb);

		RadioButtonLaC = new JRadioButton("Color Image Processing");
		RadioButtonLaC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonLaC.setBackground(new Color(255, 255, 255));
		RadioButtonLaC.setBounds(1113, 546, 188, 21);
		RadioButtonLaC.addActionListener(ar);
		contentPane.add(RadioButtonLaC);

		RadioButtonBlurImage = new JRadioButton("Blur Image  || Constant:");
		RadioButtonBlurImage.setName("RadioButtonBlurImage");
		RadioButtonBlurImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonBlurImage.setBackground(Color.WHITE);
		RadioButtonBlurImage.setBounds(1113, 225, 184, 21);
		RadioButtonBlurImage.addActionListener(ar);
		contentPane.add(RadioButtonBlurImage);

		RadioButtonNegativeGrayImage = new JRadioButton("Negative Gray Image");
		RadioButtonNegativeGrayImage.setName("Negative Gray Image");
		RadioButtonNegativeGrayImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonNegativeGrayImage.setBackground(Color.WHITE);
		RadioButtonNegativeGrayImage.setBounds(1113, 160, 168, 21);
		RadioButtonNegativeGrayImage.addActionListener(ar);
		contentPane.add(RadioButtonNegativeGrayImage);

		RadioButtonBinaryImage = new JRadioButton("Binary Image");
		RadioButtonBinaryImage.setName("BinaryImage");
		RadioButtonBinaryImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonBinaryImage.setBackground(Color.WHITE);
		RadioButtonBinaryImage.setBounds(1113, 255, 109, 21);
		RadioButtonBinaryImage.addActionListener(ar);
		contentPane.add(RadioButtonBinaryImage);

		RadioButtonLaplace = new JRadioButton("Laplace Image || Type:");
		RadioButtonLaplace.setName("Laplace Image");
		RadioButtonLaplace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonLaplace.setBackground(Color.WHITE);
		RadioButtonLaplace.setBounds(1113, 287, 175, 21);
		RadioButtonLaplace.addActionListener(ar);
		contentPane.add(RadioButtonLaplace);

		RadioButtonBitPlane = new JRadioButton("Bit Plance Sciling || Bit:");
		RadioButtonBitPlane.setName("Bit Plance Sciling");
		RadioButtonBitPlane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonBitPlane.setBackground(Color.WHITE);
		RadioButtonBitPlane.setBounds(1113, 319, 175, 21);
		RadioButtonBitPlane.addActionListener(ar);
		contentPane.add(RadioButtonBitPlane);

		RadioButtonLPF = new JRadioButton("Low Pass Filter");
		RadioButtonLPF.setName("Low Pass Filter");
		RadioButtonLPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonLPF.setBackground(Color.WHITE);
		RadioButtonLPF.setBounds(1113, 351, 128, 21);
		RadioButtonLPF.addActionListener(ar);
		contentPane.add(RadioButtonLPF);

		RadioButtonHPF = new JRadioButton("High Pass Filter || Type:");
		RadioButtonHPF.setName("High Pass Filter");
		RadioButtonHPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonHPF.setBackground(Color.WHITE);
		RadioButtonHPF.setBounds(1113, 384, 184, 21);
		RadioButtonHPF.addActionListener(ar);
		contentPane.add(RadioButtonHPF);

		RadioButtonBorderSobel = new JRadioButton("Border Sobel");
		RadioButtonBorderSobel.setName("Border Sobel");
		RadioButtonBorderSobel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonBorderSobel.setBackground(Color.WHITE);
		RadioButtonBorderSobel.setBounds(1113, 451, 168, 21);
		RadioButtonBorderSobel.addActionListener(ar);
		contentPane.add(RadioButtonBorderSobel);

		RadioButtonBorderGradient = new JRadioButton("Border Gradient");
		RadioButtonBorderGradient.setName("Border Gradient");
		RadioButtonBorderGradient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonBorderGradient.setBackground(Color.WHITE);
		RadioButtonBorderGradient.setBounds(1113, 417, 128, 21);
		RadioButtonBorderGradient.addActionListener(ar);
		contentPane.add(RadioButtonBorderGradient);

		RadioButtonLog = new JRadioButton("Logarit Image");
		RadioButtonLog.setName("");
		RadioButtonLog.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonLog.setBackground(Color.WHITE);
		RadioButtonLog.setBounds(1113, 483, 128, 21);
		RadioButtonLog.addActionListener(ar);
		contentPane.add(RadioButtonLog);

		RadioButtonPowerLaw = new JRadioButton("Power Law || Gamma:");
		RadioButtonPowerLaw.setName("Power Law");
		RadioButtonPowerLaw.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RadioButtonPowerLaw.setBackground(Color.WHITE);
		RadioButtonPowerLaw.setBounds(1113, 516, 168, 21);
		RadioButtonPowerLaw.addActionListener(ar);
		contentPane.add(RadioButtonPowerLaw);

		buttonGroup.add(RadioButtonNegativeImage);
		buttonGroup.add(RadioButtonGrayImage);
		buttonGroup.add(RadioButtonRgb);
		buttonGroup.add(RadioButtonLaC);
		buttonGroup.add(RadioButtonBlurImage);
		buttonGroup.add(RadioButtonNegativeGrayImage);
		buttonGroup.add(RadioButtonBinaryImage);
		buttonGroup.add(RadioButtonLaplace);
		buttonGroup.add(RadioButtonBitPlane);
		buttonGroup.add(RadioButtonLPF);
		buttonGroup.add(RadioButtonHPF);
		buttonGroup.add(RadioButtonBorderGradient);
		buttonGroup.add(RadioButtonBorderSobel);
		buttonGroup.add(RadioButtonLog);
		buttonGroup.add(RadioButtonPowerLaw);

		ConstantBlur = new JTextField();
		ConstantBlur.setName("Blur");
		ConstantBlur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ConstantBlur.setText("0");
		ConstantBlur.setEnabled(false);
		ConstantBlur.addKeyListener(ak);
		ConstantBlur.setBounds(1299, 225, 32, 22);
		contentPane.add(ConstantBlur);
		
		comboBoxLaplace = new JComboBox();
		comboBoxLaplace.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		comboBoxLaplace.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxLaplace.setBounds(1289, 286, 32, 21);
		contentPane.add(comboBoxLaplace);
		
		comboBoxBPS = new JComboBox();
		comboBoxBPS.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		comboBoxBPS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxBPS.setBounds(1289, 320, 32, 21);
		contentPane.add(comboBoxBPS);
		
		comboBoxHPF = new JComboBox();
		comboBoxHPF.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		comboBoxHPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxHPF.setBounds(1297, 385, 32, 21);
		contentPane.add(comboBoxHPF);
		
		textGamma = new JTextField();
		textGamma.setName("Gamma");
		textGamma.setText("1");
		textGamma.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textGamma.setEnabled(false);
		textGamma.setBounds(1289, 517, 32, 22);
		textGamma.addKeyListener(ak);
		contentPane.add(textGamma);
		
		

		this.imgfile.setFileName("temp");
		try {
			File file = new File("C:\\Picture\\temp.jpg");
			File file2 = new File("C:\\Picture\\temp2.jpg");
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

			int newWidth = 1074, newHeight = 690;
			double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
			if (icon.getIconWidth() > icon.getIconHeight()) {
				newHeight = (int) (newWidth / aspectRatio);
			} else {
				newWidth = (int) (newHeight * aspectRatio);
			}
			ImageIcon scaledIcon = new ImageIcon(
					icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
			this.Display.setIcon(scaledIcon);

			String temp2 = "C:\\Picture\\temp2.jpg";
			try {
				ImageIO.write(this.imgfile.getImg(), "jpg", new File(temp2));
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.Display.setBounds(33, 24, 1074, 690);

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
		displayImg(redImage);
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

		displayImg(greenImage);

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
		displayImg(blueImage);
	}

	public void adjustBright(int val) {
		// TODO Auto-generated method stub
		int para = val * 2 - 100;
		bright_Factor = val - 50;
		Brightness_p.setText(para + "");

		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage tempImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		tempImage = adjustFeature.getInstance().setLightAndColor(this.imgfile.getImg(), bright_Factor, warmth_Factor,
				tint_Factor, contrast_Factor);
		displayImg(tempImage);
	}

	public void adjustContrast(double val) {
		int para = (int) val * 2 - 100;
		contrast_Factor = (double) val / 200 + 0.75;
		Contrast_p.setText(para + "");

		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage tempImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		tempImage = adjustFeature.getInstance().setLightAndColor(this.imgfile.getImg(), bright_Factor, warmth_Factor,
				tint_Factor, contrast_Factor);
		displayImg(tempImage);
	}

	public void adjustWarmth(int val) {
		// TODO Auto-generated method stub
		int para = val * 2 - 100;
		warmth_Factor = val - 50;
		Warmth_p.setText(para + "");

		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage tempImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		tempImage = adjustFeature.getInstance().setLightAndColor(this.imgfile.getImg(), bright_Factor, warmth_Factor,
				tint_Factor, contrast_Factor);
		displayImg(tempImage);
	}

	public void adjustTint(int val) {
		// TODO Auto-generated method stub
		int para = val * 2 - 100;
		tint_Factor = val - 50;
		Tint_p.setText(para + "");

		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage tempImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		tempImage = adjustFeature.getInstance().setLightAndColor(this.imgfile.getImg(), bright_Factor, warmth_Factor,
				tint_Factor, contrast_Factor);
		displayImg(tempImage);
	}

	public void adjustLaplaceImage() {
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage tempImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		tempImage = adjustFeature.getInstance().setLaplace(this.imgfile.getImg(),Integer.valueOf(comboBoxLaplace.getSelectedIndex())+1);
		displayImg(tempImage);
	}
	
	public void adjustNegativeImage() {
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage negativeImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		negativeImage = adjustFeature.getInstance().setNegativeImage(this.imgfile.getImg());
		displayImg(negativeImage);
	}

	public void adjustGrayImage() {
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage grayImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		grayImage = adjustFeature.getInstance().setGrayImage(this.imgfile.getImg());
		displayImg(grayImage);
	}

	public void adjustNegativeGrayImage() {
	
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage negativegrayImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		negativegrayImage = adjustFeature.getInstance().setNegativeGrayImage(this.imgfile.getImg());
		displayImg(negativegrayImage);
	}

	public void adjustBinaryImage(int val) {
		// TODO Auto-generated method stub
		binaryimg_Factor = (int) (val * 127.5 / 50);
		binaryimg_p.setText(binaryimg_Factor + "");

		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage BinaryImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		BinaryImage = adjustFeature.getInstance().setBinaryImage(this.imgfile.getImg(), binaryimg_Factor);
		displayImg(BinaryImage);
	}

	public void adjustBlurImage() {
		// TODO Auto-generated method stub
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage negativeImage = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		negativeImage = adjustFeature.getInstance().setBlurImage(this.imgfile.getImg());
		displayImg(negativeImage);
	}

	public void adjustBitPlanceSciling() {
		// TODO Auto-generated method stub
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage BPS = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		BPS = adjustFeature.getInstance().setBitPlance(this.imgfile.getImg(), Integer.valueOf(comboBoxBPS.getSelectedIndex()) );
		displayImg(BPS);
	}
	
	public void adjustLowPassFilter() {
		// TODO Auto-generated method stub
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage LPF = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		LPF = adjustFeature.getInstance().setLowPassFilter(this.imgfile.getImg());
		displayImg(LPF);
	}
	
	public void adjustHightPassFilter() {
		// TODO Auto-generated method stub
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage HPF = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		HPF = adjustFeature.getInstance().setHighPassFilter(this.imgfile.getImg(), Integer.valueOf(comboBoxHPF.getSelectedIndex()) +1);
		displayImg(HPF);
	}
	
	public void adjustSobel() {
		// TODO Auto-generated method stub
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage sobel = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		sobel = adjustFeature.getInstance().setBorderSobel(this.imgfile.getImg());
		displayImg(sobel);
	}
	
	public void adjustGradient() {
		// TODO Auto-generated method stub
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage sobel = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		sobel = adjustFeature.getInstance().setBorderGradient(this.imgfile.getImg());
		displayImg(sobel);
	}
	
	public void adjustLog() {
		// TODO Auto-generated method stub
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage log = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		log = adjustFeature.getInstance().setLog(this.imgfile.getImg());
		displayImg(log);
	}
	

	public void adjustPowerLaw() {
		// TODO Auto-generated method stub
		this.imgfile.setFileName("C:\\Picture\\temp2.jpg");
		imgfile.readImg();
		BufferedImage log = new BufferedImage(imgfile.getWidth(), imgfile.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		log = adjustFeature.getInstance().setPowerLaw(this.imgfile.getImg(),Double.valueOf(textGamma.getText()));
		displayImg(log);
	}
	
	private void displayImg(BufferedImage tempImage) {
		String temp = "C:\\Picture\\temp.jpg";
		try {
			ImageIO.write(tempImage, "jpg", new File(temp));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.imgfile.setFileName(temp);
		this.icon = new ImageIcon(this.imgfile.getFileName());
		int newWidth = 1074, newHeight = 690;
		double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
		if (icon.getIconWidth() > icon.getIconHeight()) {
			newHeight = (int) (newWidth / aspectRatio);
		} else {
			newWidth = (int) (newHeight * aspectRatio);
		}
		ImageIcon scaledIcon = new ImageIcon(
				icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
		this.Display.setIcon(scaledIcon);

		this.Display.setBounds(33, 24, 1074, 690);
		contentPane.add(Display);
	}

	public void setBackGroundToLight() {
		this.contentPane.setBackground(new Color(255, 255, 255));
		JBrightness.setForeground(new Color(0, 0, 0));
		Brightness_p.setForeground(new Color(0, 0, 0));
		Jcontrast.setForeground(new Color(0, 0, 0));
		Contrast_p.setForeground(new Color(0, 0, 0));
		JWarmth.setForeground(new Color(0, 0, 0));
		Warmth_p.setForeground(new Color(0, 0, 0));
		JTint.setForeground(new Color(0, 0, 0));
		Tint_p.setForeground(new Color(0, 0, 0));
		binaryimg_p.setForeground(new Color(0, 0, 0));
		RadioButtonNegativeImage.setBackground(new Color(255, 255, 255));
		RadioButtonNegativeImage.setForeground(new Color(0, 0, 0));
		RadioButtonGrayImage.setBackground(new Color(255, 255, 255));
		RadioButtonGrayImage.setForeground(new Color(0, 0, 0));
		RadioButtonRgb.setBackground(new Color(255, 255, 255));
		RadioButtonRgb.setForeground(new Color(0, 0, 0));
		RadioButtonLaC.setBackground(new Color(255, 255, 255));
		RadioButtonLaC.setForeground(new Color(0, 0, 0));
		RadioButtonBlurImage.setBackground(new Color(255, 255, 255));
		RadioButtonBlurImage.setForeground(new Color(0, 0, 0));
		RadioButtonNegativeGrayImage.setBackground(new Color(255, 255, 255));
		RadioButtonNegativeGrayImage.setForeground(new Color(0, 0, 0));
		RadioButtonBinaryImage.setBackground(new Color(255, 255, 255));
		RadioButtonBinaryImage.setForeground(new Color(0, 0, 0));
		RadioButtonLaplace.setBackground(new Color(255, 255, 255));
		RadioButtonLaplace.setForeground(new Color(0, 0, 0));
		RadioButtonBitPlane.setBackground(new Color(255, 255, 255));
		RadioButtonBitPlane.setForeground(new Color(0, 0, 0));
		RadioButtonLPF.setBackground(new Color(255, 255, 255));
		RadioButtonLPF.setForeground(new Color(0, 0, 0));
		RadioButtonHPF.setBackground(new Color(255, 255, 255));
		RadioButtonHPF.setForeground(new Color(0, 0, 0));
		RadioButtonBorderGradient.setBackground(new Color(255, 255, 255));
		RadioButtonBorderGradient.setForeground(new Color(0, 0, 0));
		RadioButtonBorderSobel.setBackground(new Color(255, 255, 255));
		RadioButtonBorderSobel.setForeground(new Color(0, 0, 0));
		RadioButtonLog.setBackground(new Color(255, 255, 255));
		RadioButtonLog.setForeground(new Color(0, 0, 0));
		RadioButtonPowerLaw.setBackground(new Color(255, 255, 255));
		RadioButtonPowerLaw.setForeground(new Color(0, 0, 0));

		ImageIcon iconbuttonreset = new ImageIcon(UI.class.getResource("/image/redoOrange.png"));
		Image imgbutton = iconbuttonreset.getImage().getScaledInstance(buttonReset.getWidth(), buttonReset.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		buttonReset.setIcon(new ImageIcon(imgbutton));

		ImageIcon iconTogglebutton = new ImageIcon(UI.class.getResource("/image/dark_mode.png"));
		Image temp = iconTogglebutton.getImage().getScaledInstance(ToggleButton.getWidth(), ToggleButton.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		ToggleButton.setIcon(new ImageIcon(temp));
		
		ImageIcon iconbuttonplay = new ImageIcon(UI.class.getResource("/image/playOrange.png"));
		Image imgbuttonplay = iconbuttonplay.getImage().getScaledInstance(buttonPlay.getWidth(), buttonPlay.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		buttonPlay.setIcon(new ImageIcon(imgbuttonplay));
		
		ImageIcon iconbuttonsave = new ImageIcon(UI.class.getResource("/image/saveBlack.png"));
		Image imgbuttonsave = iconbuttonsave.getImage().getScaledInstance(buttonSave.getWidth(), buttonSave.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		
		buttonSave.setIcon(new ImageIcon(imgbuttonsave));
	}

	public void setBackGroundToDark() {
		this.contentPane.setBackground(new Color(68, 68, 68));
		JBrightness.setForeground(new Color(255, 255, 255));
		Brightness_p.setForeground(new Color(255, 255, 255));
		Jcontrast.setForeground(new Color(255, 255, 255));
		Contrast_p.setForeground(new Color(255, 255, 255));
		JWarmth.setForeground(new Color(255, 255, 255));
		Warmth_p.setForeground(new Color(255, 255, 255));
		JTint.setForeground(new Color(255, 255, 255));
		Tint_p.setForeground(new Color(255, 255, 255));
		binaryimg_p.setForeground(new Color(255, 255, 255));
		RadioButtonNegativeImage.setBackground(new Color(68, 68, 68));
		RadioButtonNegativeImage.setForeground(new Color(255, 255, 255));
		RadioButtonGrayImage.setBackground(new Color(68, 68, 68));
		RadioButtonGrayImage.setForeground(new Color(255, 255, 255));
		RadioButtonRgb.setBackground(new Color(68, 68, 68));
		RadioButtonRgb.setForeground(new Color(255, 255, 255));
		RadioButtonLaC.setBackground(new Color(68, 68, 68));
		RadioButtonLaC.setForeground(new Color(255, 255, 255));
		RadioButtonBlurImage.setBackground(new Color(68, 68, 68));
		RadioButtonBlurImage.setForeground(new Color(255, 255, 255));
		RadioButtonNegativeGrayImage.setBackground(new Color(68, 68, 68));
		RadioButtonNegativeGrayImage.setForeground(new Color(255, 255, 255));
		RadioButtonBinaryImage.setBackground(new Color(68, 68, 68));
		RadioButtonBinaryImage.setForeground(new Color(255, 255, 255));
		RadioButtonLaplace.setBackground(new Color(68, 68, 68));
		RadioButtonLaplace.setForeground(new Color(255, 255, 255));
		RadioButtonBitPlane.setBackground(new Color(68, 68, 68));
		RadioButtonBitPlane.setForeground(new Color(255, 255, 255));
		RadioButtonLPF.setBackground(new Color(68, 68, 68));
		RadioButtonLPF.setForeground(new Color(255, 255, 255));
		RadioButtonHPF.setBackground(new Color(68, 68, 68));
		RadioButtonHPF.setForeground(new Color(255, 255, 255));
		RadioButtonBorderGradient.setBackground(new Color(68, 68, 68));
		RadioButtonBorderGradient.setForeground(new Color(255, 255, 255));
		RadioButtonBorderSobel.setBackground(new Color(68, 68, 68));
		RadioButtonBorderSobel.setForeground(new Color(255, 255, 255));
		RadioButtonLog.setBackground(new Color(68, 68, 68));
		RadioButtonLog.setForeground(new Color(255, 255, 255));
		RadioButtonPowerLaw.setBackground(new Color(68, 68, 68));
		RadioButtonPowerLaw.setForeground(new Color(255, 255, 255));

		ImageIcon iconbuttonreset = new ImageIcon(UI.class.getResource("/image/redoblue.png"));
		Image imgbutton = iconbuttonreset.getImage().getScaledInstance(buttonReset.getWidth(), buttonReset.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		buttonReset.setIcon(new ImageIcon(imgbutton));

		ImageIcon iconTogglebutton = new ImageIcon(UI.class.getResource("/image/moon.png"));
		Image temp = iconTogglebutton.getImage().getScaledInstance(ToggleButton.getWidth(), ToggleButton.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		ToggleButton.setIcon(new ImageIcon(temp));

		ImageIcon iconbuttonplay = new ImageIcon(UI.class.getResource("/image/playBlue.png"));
		Image imgbuttonplay = iconbuttonplay.getImage().getScaledInstance(buttonPlay.getWidth(), buttonPlay.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		buttonPlay.setIcon(new ImageIcon(imgbuttonplay));
		
		ImageIcon iconbuttonsave = new ImageIcon(UI.class.getResource("/image/saveBlue.png"));
		Image imgbuttonsave = iconbuttonsave.getImage().getScaledInstance(buttonSave.getWidth(), buttonSave.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		
		buttonSave.setIcon(new ImageIcon(imgbuttonsave));
	}

	private void setDefault() {
		brightnessSlider.setValue(50);
		ContrastSlider.setValue(50);
		WarmthSlider.setValue(50);
		TintSlider.setValue(50);
		binaryimgSlider.setValue(50);
		Brightness_p.setText("0");
		Contrast_p.setText("0");
		Warmth_p.setText("0");
		Tint_p.setText("0");
		binaryimg_p.setText("128");
		bright_Factor = warmth_Factor = tint_Factor = 0;
		contrast_Factor = 1;
	}
}
