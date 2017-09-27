package countWord;

import java.util.LinkedList;
import java.util.List;

public class Operation {

    private static List<String> list = new LinkedList<>();

    public static synchronized List<String> mergeAllLists(List<String> s) {
        list.addAll(s);
        return list;
    }
}
