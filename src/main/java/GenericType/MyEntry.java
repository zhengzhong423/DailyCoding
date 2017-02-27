package GenericType;

import java.util.Map;

/**
 * Created by zhonzhen on 12/23/16.
 */
public class MyEntry <K, V> implements Map.Entry{

    final K key;
    V value;
    MyEntry next;
    final int hash;

    public MyEntry(K k, V v, int h) {
        this.key = k;
        this.value = v;
        next = null;
        hash = h;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public Object setValue(Object newValue) {
        V old = value;
        value = (V)newValue;
        return old;
    }
}
