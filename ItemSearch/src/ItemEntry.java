import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale.Category;

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
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

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
	JLabel catalogueIdLabel = new JLabel("Catalog Id:");
	JLabel tagLabel = new JLabel("Tags: ");
	JLabel pictureLabel = new JLabel("Picture: ");

	// text fields
	JTextField nameField = new JTextField(47);
	JTextField catalogueIdField = new JTextField(42);
	JTextField tagField = new JTextField(47);
	JTextField pictureField = new JTextField(40);
	
	String destinationFile = null;

	public static void main(String args[]) throws JDOMException, IOException {
		new ItemEntry();
		// String[] strings = new String[2];
		// strings[0] = "hello";
		// strings[1] = "world";
		// File file = new File("../sample.xml");
		// writeItem("testID", strings, "fakecategoryid", file);
	}

	private void writeItem(String uId, String[] tags,
			String catalogueId, File xmlFile) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document document = (Document) builder.build(xmlFile);
		Element rootNode = document.getRootElement();
		System.out.println(rootNode.getName());
		Element fileName = new Element("filename");
		fileName.setAttribute("uid", uId);
		String[] extension = pictureFile.getName().split("\\.");
		fileName.setAttribute("filename", uId + "." + extension[extension.length-1]);
		destinationFile = uId+extension[extension.length-1];
		fileName.setAttribute("name", catalogueId);
		for (int i = 0; i < tags.length; i++) {
			Element attribute = new Element("attribute");
			attribute.setText(tags[i]);
			fileName.addContent(attribute);
		}
		rootNode.addContent(fileName);
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(document, new FileWriter(xmlFile));

		System.out.println("File updated!");

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
		panel.add(catalogueIdLabel);
		panel.add(catalogueIdField);
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

	String[] parseTags() {
		String tagText = tagField.getText();
		String tags[] = tagText.split(",");
		for (int i = 0; i < tags.length; i++) {
			System.out.println(tags[i]);
		}
		return tags;
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
				 FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        "JPG & GIF Images", "jpg", "gif");
				 fileChooser.setFileFilter(filter);

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
				String tags[] = parseTags();
				if (picLoaded && fileLoaded) {
					try {
						String baseImgDir = xmlFile.getParentFile()+"/img/";
						String[] extension = pictureFile.getName().split("\\.");
						File tmpFile = new File(baseImgDir+nameField.getText()+"."+extension[extension.length-1]);
						pictureFile.renameTo(tmpFile);
						System.out.println("Writing fiel!!\n");
						writeItem(nameField.getText(), tags, catalogueIdField.getText(),
								xmlFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, null, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
