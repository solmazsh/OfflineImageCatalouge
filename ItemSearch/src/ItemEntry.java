import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ItemEntry extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1369810694073517702L;

	static boolean fileLoaded = false;
	static String appName = "ItemEntry";
	File xmlFile = null;

	public static void main(String args[]) {
		ItemEntry program = new ItemEntry();
		program.setLook();
	}

	public void setLook() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
	}

	ItemEntry() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");

		// layout
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());

		// menu
		openMenuItem.setName("open");
		openMenuItem.addActionListener(this);
		menu.add(openMenuItem);
		menuBar.add(menu);

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
