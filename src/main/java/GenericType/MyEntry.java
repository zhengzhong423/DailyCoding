package GenericType;

import java.util.Map;

/**
 * Created by zhonzhen on 12/23/16.
 */
public class MyEntry <K, V> implements Map.Entry<K, V>{

    final K key;
    V value;
    MyEntry<K, V> next;
    final int hash;

    public MyEntry(K k, V v, int h) {
        this.key = k;
        this.value = v;
        next = null;
        hash = h;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
    
    public int getHash() {
		return hash;
	}

    public V setValue(V newValue) {
        V old = value;
        value = newValue;
        return old;
    }
    
    public MyEntry<K, V> clone() {
    	return new MyEntry<K, V>(key, value, hash);
    }
}
