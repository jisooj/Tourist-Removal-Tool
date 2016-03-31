package sequenceSummary;

import imageManagement.ImageManagementException;
import imageManagement.FrameSequence;
import imageManagement.VideoPanel;
import imageManagement.Pixlet;
import imageManagement.PixelImage;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;

/**
* Your main method for your GUI.  The implementation is up to you--all we require
* is that Display has a main method, and that your GUI has the features described
* in the specifications.
**/ 

public class Display {
	private JFrame frame;

	public static void main(String[] args) 
			throws IOException, SequenceSummaryException, ImageManagementException {
		@SuppressWarnings("unused")
		Display show = new Display();
		
	}

	public Display() 
			throws IOException, SequenceSummaryException, ImageManagementException {
		String path = System.getProperty("user.dir");
		JFileChooser chooser = new JFileChooser(new File(path));
		FileFilter filter = new FileNameExtensionFilter("Directory that contains JPG Images", "jpg");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = chooser.showOpenDialog(null);
	
		if (result == JFileChooser.APPROVE_OPTION) {
			long start = System.currentTimeMillis();
			FrameSequence sequence = 
				new FrameSequence(chooser.getSelectedFile(), Sorter.sortType.MERGE_SORT);
			//System.out.println(Arrays.toString(chooser.getSelectedFile().listFiles()));
   			//File[] fileList = chooser.getSelectedFile().listFiles(); // not needed, this is internally done by FrameSequence
   			// file format filtering is also done by that class

			// desired time between each frame in milliseconds
				VideoPanel video = new VideoPanel(sequence, 50);
				frame = makeFrame(video);
				frame.setVisible(true);
			//imageManagement.PixelImage i = sequence.getImage(0);
			//imageManagement.PixelImagePanel p = i.getPanel();
			//System.out.println(p.getPixelImage().equals(i)); // true
			SequenceSummarizer s = new SequenceSummarizer(sequence);
			//Mode<Pixlet> mode = new Mode<Pixlet>();
			Percentile<Pixlet> p = new Percentile<Pixlet>(0.5, Sorter.sortType.MERGE_SORT);
			// resulting summarized image - save this
			PixelImage image = s.getSummary(p); 
			image.writeJPGImage("testing1.jpg");
			System.out.println("done");
			long end = System.currentTimeMillis();
			System.out.println("processing took " + (end-start)/1000.0 + " seconds");
		}
	}

	private JFrame makeFrame(VideoPanel video) {
		JFrame frame = new JFrame("Tourist Removal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(video, BorderLayout.CENTER);
		frame.add(buttonPanel(video), BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		return frame;
	}
	
	private JPanel buttonPanel(VideoPanel video) {
		JPanel panel = new JPanel(new FlowLayout());
		JButton start = new JButton("START");
		JButton stop = new JButton("STOP");
		start.addActionListener(video.getStartActionListener());
		stop.addActionListener(video.getStopActionListener());
		panel.add(start);
		panel.add(stop);
		return panel;
	}
}
