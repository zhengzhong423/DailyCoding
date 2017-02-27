package GenericType;

/**
 * Created by zhonzhen on 12/22/16.
 */
public class MyHashMap<T1, T2> {
    static final int LEN = 16;
    MyEntry[] myList= new MyEntry[LEN];
    static final float load_factor = 0.75f;
    static int count = 0;

    public MyHashMap() {}

    public void put(T1 k, T2 v) {
        if (k == null) {
            throw new RuntimeException("Key couldn't be null");
        }
        int hashCode = k.hashCode();
        MyEntry entry = new MyEntry(k, v, hashCode);

        int index = hashCode % LEN;
        if (myList[index] == null) {
            myList[index] = entry;
        } else {
            MyEntry lastEntry = findLastEntry(myList[index]);
            lastEntry.next = entry;
        }

        count++;
//        checkLoadFactor();
    }

    private MyEntry findLastEntry(MyEntry myEntry) {
        if (myEntry == null) {
            throw new RuntimeException("Entry is null");
        }
        MyEntry tmpEntry = myEntry;
        while(tmpEntry.next != null) {
            tmpEntry = tmpEntry.next;
        }
        return tmpEntry;
    }
}
