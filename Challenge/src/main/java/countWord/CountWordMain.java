package countWord;

import config.SpringMongoConfig;
import dto.Entity;
import countWordIO.CountWordFileReadDAO;
import countWordIO.CountWordFileWriteDAO;
import countWordRunnable.CountWordRunnableOperation;
import countWordRunnable.CountWordRunnableRead;
import countWordRunnable.CountWordRunnableWrite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


public class CountWordMain {

    private static Logger LOGGER = Logger.getLogger(CountWordMain.class.toString());
//    private static String DIRECTORY = File.separator + "Users" + File.separator + "mo" + File.separator + "Desktop" + File.separator + "Floow" + File.separator + "Temp";
private static String DIRECTORY = "src" + File.separator;
    private static String FILE_NAME_READ = "input.txt";
    private static String FILE_NAME_WRITE = "output.txt";

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // start of preparation
        File fToRead = new File(DIRECTORY + File.separator + FILE_NAME_READ);
        File fToWrite = new File(DIRECTORY + File.separator + FILE_NAME_WRITE);
        FileReader fRead = new FileReader(fToRead);
        FileWriter fWrite = new FileWriter(fToWrite);

//		ApplicationContext ctx =
//				new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation =
//				(MongoOperations) ctx.getBean("mongoTemplate");


        CountWordFileReadDAO accessPointRead = new CountWordFileReadDAO(fRead);
//		CountWordFileWriteDAO accessPointWrite = new CountWordFileWriteDAO(fWrite, mongoOperation);
        CountWordFileWriteDAO accessPointWrite = new CountWordFileWriteDAO(fWrite);
        BlockingQueue<List<String>> queue = new LinkedBlockingQueue<>();
        BlockingQueue<List<String>> queueToPrint = new LinkedBlockingQueue<>();
        AtomicInteger threadNumber = new AtomicInteger(0);
        // end of preparation

        //Dynamic solution
        ExecutorService executor = Executors.newCachedThreadPool();
        LOGGER.info("Launch...");
        executor.submit(new CountWordRunnableRead(accessPointRead, queue));
        int numberOfThread = 3;
        for (int i = 0; i < numberOfThread; i++) {
            executor.submit(new CountWordRunnableOperation(queue, queueToPrint, threadNumber, numberOfThread));
        }

        executor.submit(new CountWordRunnableWrite(accessPointWrite, queueToPrint));

        executor.shutdown();
        LOGGER.info("Shutting down...");
        LOGGER.info("Awaiting termination...");
        try {
            if (executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
                accessPointRead.close();
                accessPointWrite.close();
                fRead.close();
                fWrite.close();
                LOGGER.info("THE END");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//		Entity leastCommonWord = mongoOperation.findOne(
//				new Query(Criteria.where("count").is(1)), Entity.class);
//		System.out.println(leastCommonWord);

    }


}
