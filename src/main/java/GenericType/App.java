package GenericType;

public class App {

	public static void main(String[] args) {
		MyHashMap<Integer, String> hash = new MyHashMap<Integer, String>();
		hash.put(3, "3");
		hash.put(3, "3");
		hash.put(3, "3");
		hash.put(3, "3");
		hash.put(1, "1");
		hash.put(4, "4");
		hash.put(5, "5");
		hash.put(6, "6");
		hash.put(2, "2");
		System.out.println(hash.get(2));
		System.out.println(hash.LEN);

	}

}
