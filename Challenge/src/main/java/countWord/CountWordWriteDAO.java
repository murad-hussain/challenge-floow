package countWord;

import dto.Entity;

import java.util.Map;

public interface CountWordWriteDAO {

	public void writeLine(Map.Entry<String, Long> mapEntry);

//	void writeToDB(Entity entity);
}
