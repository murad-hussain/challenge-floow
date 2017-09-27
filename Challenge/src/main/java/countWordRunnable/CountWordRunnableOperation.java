package countWordRunnable;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import countWord.Operation;
import countWord.CountWordMain;

public class CountWordRunnableOperation implements Runnable{

	private BlockingQueue<List<String>> queue;
	private BlockingQueue<List<String>> queueToPrint;
	private AtomicInteger threadNumber;
	private int numberOfThread;
	private static Logger LOGGER = Logger.getLogger(CountWordMain.class.toString());

	public CountWordRunnableOperation(BlockingQueue<List<String>> queue, BlockingQueue<List<String>> queueToPrint, AtomicInteger threadNumber, int numberOfThread){
		this.queue = queue;
		this.queueToPrint = queueToPrint;
		this.threadNumber = threadNumber;
		this.numberOfThread = numberOfThread;
	}
	
	public void run() {
		List<String> s = null;
		List<String> sum = new LinkedList<>();
		List<String> mySeparator = new LinkedList<>();
		boolean forward = true;
		while(forward){
			try {
				if((s = queue.take()).size() == 0){
					forward = false;
				}else {
					sum = Operation.mergeAllLists(s);
					queueToPrint.add(sum);
				}	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		threadNumber.incrementAndGet();
		if(threadNumber.get() == numberOfThread) queueToPrint.add(mySeparator);;
		queue.add(mySeparator);
		
	}	

}
