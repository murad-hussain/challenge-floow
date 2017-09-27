package countWordRunnable;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

import countWord.CountWordMain;
import countWord.CountWordReadDAO;

public class CountWordRunnableRead implements Runnable{

	private CountWordReadDAO accessPointRead;
	private BlockingQueue<List<String>> queue;
	private static Logger LOGGER = Logger.getLogger(CountWordMain.class.toString());
	

	public CountWordRunnableRead(CountWordReadDAO accessPointRead, BlockingQueue<List<String> >queue){
		this.accessPointRead = accessPointRead;
		this.queue = queue;
	}
	
	public void run() {
		List<String> temp = null;
		int n = 0;
		LOGGER.info("Starting read...");
		while((temp = accessPointRead.readLine()) != null ){
			queue.add(temp);
			n++;
			}
		LOGGER.info("Ending read...");
//		separator for each List<String>
		List<String> mySeparator = new LinkedList<>();
		queue.add(mySeparator);
		
		
	}	

}
