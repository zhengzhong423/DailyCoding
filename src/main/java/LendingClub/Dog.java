package LendingClub;

/**
 * Created by zhonzhen on 2/4/17.
 */
public class Dog extends Base {
	
	public Dog(int k) {
		System.out.println(k);
	}
	
    public void call() {
        try {
        	System.out.println(Long.toBinaryString((long)(1l<<32)));
            System.out.println("Dog Call");
        } catch (RuntimeException r) {
            System.out.println(r);
        } finally {
            System.out.println("End");
        }
    }
    
    public void bark() {
		System.out.println("Dog Bark!");
	}

    public static void main(String[] args) {
        Dog dog = new Dog(8);
        dog.bark();
        dog.call();
    }
}
