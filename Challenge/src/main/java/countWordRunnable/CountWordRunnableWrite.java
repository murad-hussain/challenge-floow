package countWordRunnable;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import dto.Entity;
import countWord.CountWordMain;
import countWord.CountWordWriteDAO;
import utility.MyEntry;

public class CountWordRunnableWrite implements Runnable{


	private CountWordWriteDAO accessPointWrite;
	private BlockingQueue<List<String>> queueToPrint;
	private static Logger LOGGER = Logger.getLogger(CountWordMain.class.toString());
	private Map<String, Long> toPrint = new HashMap<>();
	MyEntry<String, Long> entry = new MyEntry<>();

	public CountWordRunnableWrite(CountWordWriteDAO accessPointWrite, BlockingQueue<List<String>>queueToPrint){
		this.accessPointWrite = accessPointWrite;
		this.queueToPrint = queueToPrint;
	}
	
	public void run() {
		Entity entity = new Entity();
		boolean forward = true;
		List<String> s = new LinkedList<>();
		LOGGER.info("Preparing for write...");
		while(forward){
			try {
				if((s = queueToPrint.take()).size() == 0){
					forward = false;
				}else {
					toPrint = s.stream().map(w -> w.replaceAll("[^a-zA-Z]", "").toLowerCase()).filter(word -> word.length() > 0).collect(Collectors.groupingBy(w -> w, Collectors.counting()));
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("Starting write...");
		toPrint.entrySet().stream().forEach(entry -> {
			Map.Entry<String, Long> input = new MyEntry<String, Long>(entry.getKey(), entry.getValue());
			accessPointWrite.writeLine(input); // writing to a file just for verifying if the program works
//			entity.setId("_id");
//			entity.setWord(entry.getKey());
//			entity.setCount(entry.getValue());
//			accessPointWrite.writeToDB(entity);
		});

		LOGGER.info("Ending write...");

	}

}
