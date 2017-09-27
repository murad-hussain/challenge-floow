package dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "words")
public class Entity {

    @Id
    private String id;

    @Indexed
    private Long count;

    private String word;

    public Entity() {
    }

    public Entity(Long count, String words) {
        super();
        this.count = count;
        this.word = words;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id='" + id + '\'' +
                ", count=" + count +
                ", word='" + word + '\'' +
                '}';
    }
}
