package LendingClub;

/**
 * Created by zhonzhen on 2/4/17.
 */
public class Dog extends Base {
    public static void call() {
        try {
            System.out.println("Dog Call");
        } catch (RuntimeException r) {
            System.out.println(r);
        } finally {
            System.out.println("End");
        }
    }

    public static void main(String[] args) {
        Base dog = new Dog();
        dog.call();
    }
}
