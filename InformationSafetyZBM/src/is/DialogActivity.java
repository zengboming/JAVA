package is;

import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import is.AnalysisActivity;

public class DialogActivity extends JDialog{
	private JLabel loadFileLbl;
	private JLabel percentageLbl;
	private JProgressBar percentageBar;
	private AnalysisActivity analyzer;
	private Layout view;
	
	public DialogActivity(Frame owner,String filename) {
		super(owner, "analysis");
		view = (Layout)owner;
		this.analyzer = view.getAnalyzer();
		this.setLocation(owner.getLocation());
		this.setSize(360, 150);
		this.setLayout(new GridLayout(4, 1));
		loadFileLbl = new JLabel("loading.....");
		this.add(loadFileLbl);
		percentageLbl = new JLabel();
		this.add(percentageLbl);
		percentageBar = new JProgressBar(JProgressBar.HORIZONTAL);
		this.add(percentageBar);
		this.add(new JPanel());
		new ShowCompleteThread(this).start();
		this.setVisible(true);
	}
	
	class ShowCompleteThread extends Thread{
		private DialogActivity dialog;
		
		public ShowCompleteThread(DialogActivity dialog){
			this.dialog = dialog;
		}
		
		public void run(){
			int percentage = 0;
			String progress = null;
			while(true){
				progress = analyzer.getProgress();
				percentage = (int)(analyzer.getPercentage()*100);
				percentageBar.setValue(percentage);
				percentageBar.repaint();
				percentageLbl.repaint();
				if(percentage == 100){
					dialog.dispose();
					view.getExtractTcpBtn().setEnabled(true);
					view.getExtractUdpBtn().setEnabled(true);
					view.repaint();
					break;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
