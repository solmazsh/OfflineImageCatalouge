import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class ItemEntry extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1369810694073517702L;

	
	public static void main(String args[]){
		new ItemEntry();
	}
	
	ItemEntry(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");
	    menu.add(openMenuItem);
	    menuBar.add(menu);
	    this.setJMenuBar(menuBar);
	    this.setSize(600,400);
		setVisible(true);
	}
}
