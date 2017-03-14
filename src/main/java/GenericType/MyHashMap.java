package GenericType;

/**
 * Created by zhonzhen on 12/22/16.
 */
public class MyHashMap<T1, T2> {
    int LEN = 2;
    MyEntry<T1, T2>[] myList= new MyEntry[LEN];
    static final float load_factor = 0.75f;
    int count = 0;

    public MyHashMap() {}

    public void put(T1 k, T2 v) {
        if (k == null) {
            throw new RuntimeException("Key couldn't be null");
        }
        int hashCode = k.hashCode();
        MyEntry<T1, T2> entry = new MyEntry<T1, T2>(k, v, hashCode);

        int index = hashCode % LEN;
        if (myList[index] == null) {
            myList[index] = entry;
        } else {
            MyEntry<T1, T2> lastEntry = findNewLastEntry(myList[index], k, v);
            if (lastEntry == null)
            	return;
            lastEntry.next = entry;
        }

        count++;
        checkLoadFactor();
    }
    
    private MyEntry<T1, T2> findNewLastEntry(MyEntry<T1, T2> myEntry, T1 k, T2 v) {
    	if (myEntry == null) {
            throw new RuntimeException("Entry is null");
        }
        MyEntry<T1, T2> tmpEntry = myEntry;
        if (tmpEntry.getKey() == k) {
    		tmpEntry.setValue(v);
    		return null;
    	}
        while(tmpEntry.next != null) {
            tmpEntry = tmpEntry.next;
            if (tmpEntry.getKey() == k) {
        		tmpEntry.setValue(v);
        		return null;
        	}
        }
        return tmpEntry;
	}

	public T2 get(T1 k) {
		int index = k.hashCode() % LEN;
		MyEntry<T1, T2> cur = myList[index];
		while(cur != null) {
			if (cur.getHash() == k.hashCode() && cur.getKey() == k) {
				return cur.getValue();
			}
			cur = cur.next;
		}
		return cur.getValue();
	}

    private void checkLoadFactor() {
		if (count / myList.length - load_factor > 0f) {
			MyEntry<T1, T2>[] newList = new MyEntry[LEN * 2];
			copyElement(this.myList, newList);
			this.myList = newList;
		}
	}

	private void copyElement(MyEntry<T1, T2>[] myList, MyEntry<T1, T2>[] newList) {
		for (int i = 0; i < myList.length; i++) {
			if(myList[i] != null) {
				MyEntry<T1, T2> cur = myList[i].clone();
				while(cur != null) {
					if ((cur.getHash() & LEN) == 0) {
						addEntry(newList, cur, i);
					} else {
						addEntry(newList, cur, i + LEN);
					}
					cur = cur.next;
				}
			}
		}
		LEN = LEN * 2;
	}
	
	private void addEntry(MyEntry<T1, T2>[] newList, MyEntry<T1, T2> cur, int i) {
		if (newList[i] == null) {
			newList[i] = cur;
		} else {
			findLastEntry(newList[i]).next = cur;
		}
	}

	private MyEntry<T1, T2> findLastEntry(MyEntry<T1, T2> myEntry) {
        if (myEntry == null) {
            throw new RuntimeException("Entry is null");
        }
        MyEntry<T1, T2> tmpEntry = myEntry;
        while(tmpEntry.next != null) {
            tmpEntry = tmpEntry.next;
        }
        return tmpEntry;
    }
}
