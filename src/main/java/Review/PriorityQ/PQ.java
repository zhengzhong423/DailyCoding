package Review.PriorityQ;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zhonzhen on 7/24/16.
 */
public class PQ {
    static class People{
        int id;
        int age;
        int score;
        People(int id, int age, int score){
            this.id = id;
            this.age = age;
            this.score = score;
        }
    }
    public static void main(String[] args){
        PriorityQueue<People> pq = new PriorityQueue<People>(10, new Comparator<People>() {
            public int compare(People o1, People o2) {
                if(o1.score == o2.score)
                    return o1.age - o2.age;
                return o1.score - o2.score;
            }
        });
        People p0 = new PQ.People(0, 10, 90);
        People p1 = new PQ.People(1, 22, 80);
        People p2 = new PQ.People(2, 19, 80);
        People p3 = new PQ.People(3, 20, 80);

        pq.add(p0);
        pq.add(p1);
        pq.add(p2);
        pq.add(p3);

        for (People p: pq) {
            System.out.println(p.id);
        }
    }

}
