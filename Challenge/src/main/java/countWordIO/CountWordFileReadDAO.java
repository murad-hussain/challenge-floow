package countWordIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import countWord.CountWordReadDAO;

public class CountWordFileReadDAO implements CountWordReadDAO {

	private BufferedReader br;
	
	public CountWordFileReadDAO(FileReader fr) {
		this.br = new BufferedReader(fr);
	}
	
	
	public List<String> readLine() {
		String s= null;
		List<String> result = new LinkedList<>();
	    try {
			s = br.readLine();
			String[] words = s.trim().split(" ");
			for(int i = 0; i < words.length-1; i++){
				result.add(words[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void close() throws IOException{
		if(br != null) br.close();
	}
	

}
