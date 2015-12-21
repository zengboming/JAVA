package is;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import is.DialogActivity;
import is.Layout;

public class OpenOnClickListener extends PcapActivity {
	
	public OpenOnClickListener(Layout view){
		super(view);
	}
	
	@Override
	public void action(ActionEvent arg0) {
	
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new PacpFileFilter());
		if(chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION){
			File selectFile = chooser.getSelectedFile();
			if(selectFile != null){
				view.setAnalyzer(new AnalysisActivity(selectFile.getAbsolutePath()));
				new DialogActivity(view, selectFile.getName());
			}
		}
	}
	
	class PacpFileFilter extends FileFilter{
		@Override
		public boolean accept(File arg0) {
			return arg0.getName().endsWith(".pcap");
		}

		@Override
		public String getDescription() {
			return ".pcapÎÄ¼þ";
		}
	}
}
