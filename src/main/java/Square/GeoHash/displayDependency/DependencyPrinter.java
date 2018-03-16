package Square.GeoHash.displayDependency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Tag {
    String name;
    List<Tag> subTags = new ArrayList<>();

    public Tag (String name) {
        this.name = name;
    }

    public Tag add(Tag t, Map<String, Set<Tag>> map) {
        subTags.add(t);
        map.putIfAbsent(t.name, new HashSet<>());
        map.get(t.name).add(t);
        return t;
    }
}
public class DependencyPrinter {
    Tag root = new Tag("root");

    public static void main(String[] args) {
        DependencyPrinter dp = new DependencyPrinter();
        Map<String, Set<Tag>> map = new HashMap<>();
        Tag tag = dp.root.add(new Tag("html"), map);
        Tag head = tag.add(new Tag("head"), map);
        Tag body = tag.add(new Tag("body"), map);
        body.add(new Tag("h1"), map);
        body.add(new Tag("h2"), map);
        head.add(new Tag("h4"), map);

        dp.getOutput(dp.root, 0);

    }

    public void getOutput(Tag root, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }

        System.out.print(root.name);
        System.out.println();

        for (Tag subTag : root.subTags) {
            getOutput(subTag, level + 1);
        }

    }
}
