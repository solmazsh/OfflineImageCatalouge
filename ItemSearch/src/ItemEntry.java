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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemEntry extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1369810694073517702L;

	// states
	static boolean fileLoaded = false;
	static boolean picLoaded = false;

	// app name
	static String appName = "ItemEntry";

	// files
	File xmlFile = null;
	File pictureFile = null;

	// menu
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("File");
	JMenuItem openMenuItem = new JMenuItem("Open");

	// panel
	JPanel panel = new JPanel(new FlowLayout());

	// labels
	JLabel nameLabel = new JLabel("Name: ");
	JLabel tagLabel = new JLabel("Tags: ");
	JLabel pictureLabel = new JLabel("Picture: ");

	// text fields
	JTextField nameField = new JTextField(47);
	JTextField tagField = new JTextField(47);
	JTextField pictureField = new JTextField(40);

	public static void main(String args[]) {
		new ItemEntry();
	}

	ItemEntry() {

		this.add(panel);

		// menu
		openMenuItem.setName("open");
		openMenuItem.addActionListener(this);
		menu.add(openMenuItem);
		menuBar.add(menu);

		// buttons
		JButton browseButton = new JButton("Browse");
		browseButton.setName("browse");
		JButton submitButton = new JButton("Submit");
		submitButton.setName("submit");

		// listener register
		browseButton.addActionListener(this);
		submitButton.addActionListener(this);

		// add components
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(tagLabel);
		panel.add(tagField);
		panel.add(pictureLabel);
		panel.add(pictureField);
		panel.add(browseButton);
		panel.add(submitButton);

		// frame properties
		this.setJMenuBar(menuBar);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setTitle(appName);
		this.setResizable(false);

		setVisible(true);
	}

	private void parseTags() {
		String tagText = tagField.getText();
		String tags[] = tagText.split(",");
		for (int i = 0; i < tags.length; i++) {
			System.out.println(tags[i]);
		}
	}

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
				} else {
					fileLoaded = false;
					xmlFile = null;
					this.setTitle(appName);
				}
			}
		}
		if (event.getSource() instanceof JButton) {
			JButton button = (JButton) event.getSource();
			if (button.getName() == "browse") {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pictureFile = fileChooser.getSelectedFile();
					picLoaded = true;
					pictureField.setText(pictureFile.getAbsolutePath());
				} else {
					picLoaded = false;
					pictureFile = null;
					pictureField.setText(null);
				}
			} else if (button.getName() == "submit") {
				parseTags();
				if (picLoaded && fileLoaded) {

				} else {
					JOptionPane.showMessageDialog(null, null, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
