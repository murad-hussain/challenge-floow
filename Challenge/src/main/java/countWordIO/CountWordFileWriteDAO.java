package countWordIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


import dto.Entity;
import countWord.CountWordWriteDAO;
import org.springframework.data.mongodb.core.MongoOperations;

public class CountWordFileWriteDAO implements CountWordWriteDAO, Runnable {

	private BufferedWriter bw;

	private MongoOperations mongoOperations;
	
	
//	public CountWordFileWriteDAO(FileWriter fw, MongoOperations mongoOperations) {
//		this.bw = new BufferedWriter(fw);
//		this.mongoOperations = mongoOperations;
//	}

	public CountWordFileWriteDAO(FileWriter fw ) {
		this.bw = new BufferedWriter(fw);
	}

	public void writeLine(Map.Entry<String, Long> mapEntry) {
		try {
			bw.write(mapEntry.getKey() + " " + mapEntry.getValue() + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() throws IOException{
		if(bw != null) bw.close();
	}


//	@Override
//	public void writeToDB(Entity entity) {
//		mongoOperations.save(entity);
//	}


	public void run() {
		
		
	}

}
