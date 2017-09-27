package utility;

/**
 * Created by mo on 26/09/17.
 */

import java.util.Map;

public final class MyEntry<K, V> implements Map.Entry<K, V> {

    private K key;
    private V value;

    public MyEntry(){}

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    public K setKey(K key){
        K old = this.key;
        this.key = key;
        return old;
    }
}
