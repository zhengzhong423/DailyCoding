package Arrays;

import java.util.ArrayList;
import java.util.List;

class Obj {
    int val;

    public Obj(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
public class Features {
    public static void main(String[] args) {
        List<Obj> list = new ArrayList<>();
        list.add(new Obj(1));
        list.add(new Obj(2));
        list.add(new Obj(3));
        list.add(new Obj(4));

        for (Obj obj : list) {
            obj = new Obj(5);
        }

        System.out.println(list);
    }
}
