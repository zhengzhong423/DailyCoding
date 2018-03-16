package Twitter.mergeInterval;

import java.util.LinkedList;
import java.util.List;

public class MergeInterval {
    public static void main(String[] args) {
        MergeInterval mi = new MergeInterval();
        List<Interval> l1 = new LinkedList<>();
        l1.add(new Interval(0, 6));
        l1.add(new Interval(6, 6));

        List<Interval> l2 = new LinkedList<>();
        l2.add(new Interval(0,2));
        l2.add(new Interval(6,8));
        l2.add(new Interval(10,12));

        System.out.println(mi.mergeIntervals(l1, l2));
    }
    public List<Interval> mergeIntervals(List<Interval> l1, List<Interval> l2) {
        int idx1 = 0, idx2 = 0;
        List<Interval> result = new LinkedList<>();
        int start;
        int curEnd = Integer.MIN_VALUE;

        while (idx1 < l1.size() && idx2 < l2.size()) {


            if (l1.get(idx1).a < l2.get(idx2).a) {
                start = l1.get(idx1).a;
                curEnd = l1.get(idx1).b;
                idx1++;
            } else {
                start = l2.get(idx2).a;
                curEnd = l2.get(idx2).b;
                idx2++;
            }

            while (idx1 < l1.size() && idx2 < l2.size()) {
                if (l1.get(idx1).a < l2.get(idx2).a) {
                    if (l1.get(idx1).a <= curEnd) {
                        curEnd = Math.max(curEnd, l1.get(idx1).b);
                        idx1++;
                    } else {
                        break;
                    }
                } else {
                    if (l2.get(idx2).a <= curEnd) {
                        curEnd = Math.max(curEnd, l2.get(idx1).b);
                        idx2++;
                    } else {
                        break;
                    }
                }
            }
            result.add(new Interval(start, curEnd));
        }


        if (idx1 < l1.size()) {
            while (idx1 < l1.size() && l1.get(idx1).a <= curEnd) {
                result.get(result.size() - 1).b = Math.max(l1.get(idx1).b, curEnd);
                idx1++;
            }
            if (idx1 < l1.size()) {
                result.addAll(l1.subList(idx1, l1.size()));
            }
        } else {
            while (idx2 < l2.size() && l2.get(idx2).a <= curEnd) {
                result.get(result.size() - 1).b = Math.max(l2.get(idx2).b, curEnd);
                idx2++;
            }
            if (idx2 < l2.size()) {
                result.addAll(l2.subList(idx2, l2.size()));
            }
        }

        return result;
    }
}
