package application.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingsPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton goBack;
	private Icon backIcon;
	private BufferedImage background;
	
	
	public SettingsPage() {
		
		try {
			background = ImageIO.read(new File(getClass().getResource("/application/resources/menu/settings/SettingsBg.jpg/").getPath()));
		} catch (IOException e) {
			System.out.println("Can't load background image");
		}
		backIcon = new ImageIcon(getClass().getResource("/application/resources/menu/settings/backDefault.jpg/").getPath());
		
		goBack = new JButton(backIcon);
		goBack.setBounds(550, 800, 400, 100);
		this.setLayout(null);
		this.add(goBack);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH),0,0,this);
	}
	
	public JButton getGoBack() {
		return goBack;
	}

	public void setGoBack(JButton goBack) {
		this.goBack = goBack;
	}
}
