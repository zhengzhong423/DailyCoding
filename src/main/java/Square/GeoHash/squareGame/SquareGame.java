package Square.GeoHash.squareGame;

import java.util.ArrayList;
import java.util.List;

class Square {
    double s;
    double e;
    double height;

    public Square(double s, double e, double height) {
        this.s = s;
        this.e = e;
        this.height = height;
    }
}

public class SquareGame {
    List<Square> list = new ArrayList<>();

    public static void main(String[] args) {
        SquareGame sg = new SquareGame();
        sg.addSquare(0.0d, 20.0d);
        sg.addSquare(0.5d, 3.0d);
//        sg.addSquare(4.0d, 10.0d);
        sg.addSquare(0.8d, 7.0d);
        sg.addSquare(9.7d, 20.0d);
        System.out.println(sg.getHeight(1.0d));
    }

    public double getHeight(double x) {
        for (Square sq : list) {
            if (sq.s <= x && x <= sq.e) {
                return sq.height;
            }
        }
        return 0.0d;
    }


    public void addSquare(double x, double size) {
        double curHeight = 0d;
        List<Square> mergedList = new ArrayList<>();
        int i = 0;
        boolean added = false;
        if (list.size() == 0) {
            list.add(new Square(x, x + size, size));
            return;
        }
        while (i < list.size()) {
            Square sq = list.get(i);

            if (x < sq.s) {
                if (x + size < sq.s) {
                    mergedList.add(sq);
                    break;
                } else if (x + size < sq.e) {
                    curHeight = Math.max(curHeight, sq.height);
                    if (!added) {
                        mergedList.add(new Square(x, x + size, size + curHeight));
                        added = true;
                    }
                    mergedList.add(new Square(x + size, sq.e, sq.height));
                } else {
                    curHeight = Math.max(curHeight, sq.height);
                }
            } else if (x > sq.s && x <= sq.e) { // x >= sq.s
                if (x + size > sq.s && x + size <= sq.e) {
                    mergedList.add(new Square(sq.s, x, sq.height));
                    mergedList.add(new Square(x, x + size, sq.height + size));
                    mergedList.add(new Square(x + size, sq.e, sq.height));
                    added = true;
                } else { // x+size > sq.e
                    mergedList.add(new Square(sq.s, x, sq.height));
                    curHeight = Math.max(curHeight, sq.height);
                }
            } else { //x >= sq.e
                mergedList.add(sq);
            }

            i++;
        }

        if (!added) {
            mergedList.add(new Square(x, x + size, size + curHeight));
        }
        list = mergedList;
    }
}
