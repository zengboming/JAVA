package is;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import is.FrameSize;
import is.strings;
import is.OpenOnClickListener;
import is.PacketActivity2;
import is.AnalysisActivity;

public class Layout extends JFrame{
	private JMenuItem openMenuItem;
	private JButton extractTcpBtn;
	private JButton extractUdpBtn;
	private String currentFilter;
	private AnalysisActivity analyzer;
	private boolean isWorking = false;

	public Layout(){
		this.setTitle(strings.Title.JFRAME_VIEW_TITLE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle r = new Rectangle((screenSize.width-FrameSize.FRAMEWIDHT)/2,
									(screenSize.height-FrameSize.FRAMEHEIGHT)/2,
									FrameSize.FRAMEWIDHT,
									FrameSize.FRAMEHEIGHT);
		this.setBounds(r);
		Container container = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);
		
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu(strings.Menu.FILE_NAME);
		fileMenu.setHorizontalAlignment(SwingConstants.CENTER);
		openMenuItem = new JMenuItem(strings.MenuItem.FILE_OPEN_NAME);
		fileMenu.add(openMenuItem);
		openMenuItem.addActionListener(new OpenOnClickListener(this));
		bar.add(fileMenu);
		this.setJMenuBar(bar);

		extractTcpBtn = new JButton(strings.Button.EXTRACTTCP);
		extractTcpBtn.addActionListener(new PacketActivity2(this));
		extractTcpBtn.setEnabled(false);
		container.add(extractTcpBtn,"North");
		extractUdpBtn = new JButton(strings.Button.EXTRACTUDP);
		extractUdpBtn.addActionListener(new PacketActivity2(this));
		extractUdpBtn.setEnabled(false);
		container.add(extractUdpBtn,"Center");
		JLabel lable = new JLabel(strings.label,JLabel.CENTER);
		container.add(lable,"South");

		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public boolean isWorking() {
		return isWorking;
	}

	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}
	
	public String getCurrentFilter() {
		return currentFilter;
	}

	public void setCurrentFilter(String currentFilter) {
		this.currentFilter = currentFilter;
	}

	public AnalysisActivity getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(AnalysisActivity analyzer) {
		this.analyzer = analyzer;
	}


	public JButton getExtractTcpBtn() {
		return extractTcpBtn;
	}

	public void setExtractTcpBtn(JButton extractTcpBtn) {
		this.extractTcpBtn = extractTcpBtn;
	}
	
	public JButton getExtractUdpBtn() {
		return extractUdpBtn;
	}

	public void setExtractUdpBtn(JButton extractUdpBtn) {
		this.extractUdpBtn = extractUdpBtn;
	}


}
