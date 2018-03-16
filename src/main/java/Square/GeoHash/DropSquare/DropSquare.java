package Square.GeoHash.DropSquare;


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

public class DropSquare {
    List<Square> heightLine = new ArrayList<>();

    public static void main(String[] args) {
        DropSquare dropSquare = new DropSquare();
        dropSquare.drop(0.0d, 20.0d);
        dropSquare.drop(0.5d, 3.0d);
//        sg.addSquare(4.0d, 10.0d);
        dropSquare.drop(3.8d, 7.0d);
        dropSquare.drop(3.7d, 20.0d);
        System.out.println(dropSquare.getHeight(3.6d));
    }

    public double getHeight (double x) {
        for (Square sq: heightLine) {
            if (x >= sq.s && x <= sq.e) {
                return sq.height;
            }
        }
        return 0.0d;
    }

    public void drop(double x, double size) {
        double curMax = 0d;
        List<Square> newLine = new ArrayList<>();
        boolean added = false;

        for (int i = 0; i < heightLine.size(); i++) {
            Square sq = heightLine.get(i);
            if (x > sq.e) {
                newLine.add(sq);
            } else {
                if (x > sq.s && x < sq.e) {
                    if (x + size > sq.e) {
                        newLine.add(new Square(sq.s, x, sq.height));
                        curMax = Math.max(curMax, sq.height);
                    } else {
                        newLine.add(new Square(sq.s, x, sq.height));
                        newLine.add(new Square(x, x + size, sq.height + size));
                        newLine.add(new Square(x + size, sq.e, sq.height));
                        added = true;
                    }
                } else if (x < sq.s && x < sq.e) {
                    if (x + size < sq.s) {
                        if (!added) {
                            newLine.add(new Square(x, x + size, curMax + sq.height));
                            added = true;
                            newLine.add(sq);
                        } else {
                            newLine.add(sq);
                        }
                    } else if (x + size > sq.e) {
                        curMax = Math.max(curMax, sq.height);
                    } else {
                        newLine.add(new Square(x, x + size, size + curMax));
                        newLine.add(new Square(x + size, sq.e, sq.height));
                        added = true;
                    }
                }
            }
        }
        if (!added) {
            newLine.add(new Square(x, x + size, curMax + size));
        }
        heightLine = newLine;
    }
}
