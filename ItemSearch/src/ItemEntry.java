import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ItemEntry extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1369810694073517702L;

	public static void main(String args[]) {
		new ItemEntry();
	}

	ItemEntry() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setName("open");
		openMenuItem.addActionListener(this);
		menu.add(openMenuItem);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setTitle("ItemEntry");
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
	    if(event.getSource() instanceof JMenuItem){
	    	JMenuItem menuItem = (JMenuItem)event.getSource();
	    	if(menuItem.getName() == "open"){
	    		//open file
	    		//System.out.println("open");
	    	}
	    }
	}
}
