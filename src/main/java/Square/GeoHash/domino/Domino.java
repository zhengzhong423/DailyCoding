package Square.GeoHash.domino;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DominoCard {
    int upperNum;
    int downNum;

    public DominoCard(int num1, int num2) {
        this.upperNum = num1;
        this.downNum = num2;
    }
}

class DominoBag {
    List<DominoCard> itemList = new ArrayList<>();
}
public class Domino {

    public static void main(String[] args) {
        Domino domino = new Domino();

        DominoBag bag = new DominoBag();
        bag.itemList.add(new DominoCard(0, 1));
        bag.itemList.add(new DominoCard(1, 2));
        bag.itemList.add(new DominoCard(2, 3));
        bag.itemList.add(new DominoCard(0, 3));
//        bag.itemList.add(new DominoCard(3, 1));

        System.out.println(domino.isCycle(bag));
    }

    public boolean isCycle(DominoBag dominoBag) {
        Set<DominoCard> gloablSet = new HashSet<>();
        for (DominoCard card: dominoBag.itemList) {
            if (detectCycle(card, dominoBag.itemList, gloablSet, new HashSet<>())) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycle(DominoCard card, List<DominoCard> itemList,
                                Set<DominoCard> globalVisited, Set<DominoCard> curVisited) {
        curVisited.add(card);
        if (!globalVisited.contains(card)) {
            for (DominoCard neigbour: itemList) {
                if (neigbour.upperNum == card.downNum) {
                    if (curVisited.contains(neigbour) || detectCycle(neigbour, itemList, globalVisited, curVisited)) {
                        return true;
                    }
                }
            }
            globalVisited.add(card);
        }
        curVisited.remove(card);
        return false;
    }
}
