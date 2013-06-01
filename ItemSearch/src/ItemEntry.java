import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemEntry extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1369810694073517702L;

	static boolean fileLoaded = false;
	static String appName = "ItemEntry";
	File xmlFile = null;

	public static void main(String args[]) {
		new ItemEntry();
	}

	ItemEntry() {
		// menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");

		// panel
		JPanel panel = new JPanel(new FlowLayout());
		this.add(panel);

		// menu
		openMenuItem.setName("open");
		openMenuItem.addActionListener(this);
		menu.add(openMenuItem);
		menuBar.add(menu);

		// labels
		JLabel nameLabel = new JLabel("Name: ");
		JLabel tagLabel = new JLabel("Tags: ");
		JLabel pictureLabel = new JLabel("Picture: ");

		// text fields
		JTextField nameField = new JTextField(25);
		JTextField tagField = new JTextField(25);
		JTextField pictureField = new JTextField(25);

		// buttons
		JButton browseButton = new JButton("Browse");

		// add components
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(tagLabel);
		panel.add(tagField);
		panel.add(pictureLabel);
		panel.add(pictureField);
		panel.add(browseButton);

		// frame properties
		this.setJMenuBar(menuBar);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setTitle(appName);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof JMenuItem) {
			JMenuItem menuItem = (JMenuItem) event.getSource();
			if (menuItem.getName() == "open") {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					xmlFile = fileChooser.getSelectedFile();
					fileLoaded = true;
					this.setTitle(appName + " - " + xmlFile.getAbsolutePath());
				}
			}
		}
	}
}
