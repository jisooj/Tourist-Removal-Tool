package sequenceSummary;
import java.awt.Point;
import imageManagement.FrameSequence;
import imageManagement.ImageManagementException;
import imageManagement.PixelImage;
import imageManagement.Pixlet;

/**
 * Contains functionality for summarizing a frame sequence and returning 
 * and image representing this summary.
 *
 */
public class SequenceSummarizer
{
	//private SummaryStatistic<Pixlet> summarizer;
	private FrameSequence seq;
	
	/**
	 * Given a FrameSequence, returns a PixelImage which summarizes
	 * the information using the appropriate SummaryStatistic
	 * @param seq
	 * @throws ImageManagementException 
	 */
	public SequenceSummarizer(FrameSequence seq) throws SequenceSummaryException, ImageManagementException
	{
		this.seq = seq;	
	}

	/**
	 * Returns a new PixelImage composed of two different components:
	 * for the area inside the bounding box defined by points p1 and p2,
	 * returns the pixels which summarize the sequence using 
	 * summarystatistic summStat.  For the area of the image outside of
	 * the bounding box, returns the pixels from the image at index
	 * currFrame in the frame sequence.
	 * 
	 * @param summStat the summary statistic object to use in calculation 
	 * over the sequence represented by the sequenceSummarizer.
	 * @param p1 one point used to form the bounding box
	 * @param p2 the second point used to form the bounding box.
	 * @param currFrame the time/index of the frame to use in the area
	 * outside the bounding box.
	 * @return a PixelImage where the summary statistic is calculated
	 * over all pixels in the image.
	 * @throws SequenceSummaryException (Note that although we allow you
	 * to throw an exception in this method if you so choose, you do not
	 * need to do so.)
	 * @throws ImageManagementException (Note that although we allow you
	 * to throw an exception in this method if you so choose, you do not
	 * need to do so.)
	 */
	public PixelImage getSummary(SummaryStatistic<Pixlet> summStat, 
			Point p1, Point p2, int currFrame) 
			throws SequenceSummaryException, ImageManagementException
	{
		PixelImage result = seq.getImage(currFrame);
		for (int x = p1.x; x < p2.x; x++) {
			for (int y = p1.y; y < p2.y; y++) {
				result.setPixlet(summStat.getValue(seq.getPixletArray(x, y, Pixlet.colorType.R)));
				result.setPixlet(summStat.getValue(seq.getPixletArray(x, y, Pixlet.colorType.G)));
				result.setPixlet(summStat.getValue(seq.getPixletArray(x, y, Pixlet.colorType.B)));
			}
		}
		return result;
	}
	/**
	 * Returns a summary image of the sequence of images
	 * provided in the constructor
	 * of this SequenceSummarizer using the summarystatistic summStat.
	 * 
	 * @param summStat the summary statistic object to use in calculation 
	 * over the sequence represented by the sequenceSummarizer.
	 * @return a PixelImage where the summary statistic is calculated
	 * over all pixels in the image.
	 * @throws SequenceSummaryException (Note that although we allow you
	 * to throw an exception in this method if you so choose, you do not
	 * need to do so.)
	 * @throws ImageManagementException (Note that although we allow you
	 * to throw an exception in this method if you so choose, you do not
	 * need to do so.)
	 */
	public PixelImage getSummary(SummaryStatistic<Pixlet> summStat) 
			throws SequenceSummaryException, ImageManagementException{
		return getSummary(summStat, new Point(0, 0), 
				new Point(seq.getWidth() - 1, seq.getHeight() - 1), 0);
	}
	
}
