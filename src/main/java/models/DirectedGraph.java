package models;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;


/*
Contains a LinkedHashMap where the key is the name of the node and the value is a set of outlinks
 */
public class DirectedGraph {

    public DirectedGraph() {
        links = new LinkedHashMap<>();
    }


    private LinkedHashMap<String, Set<String>> links;

    public LinkedHashMap<String, Set<String>> getLinks() {
        return links;
    }


    public void addLink(String fromId, String toId) {
        if (links.containsKey(fromId)) {
            links.get(fromId).add(toId);
        } else {
            links.put(fromId, new HashSet<>(Collections.singletonList(toId)));
        }
        if (!links.containsKey(toId)) {
            links.put(toId, new HashSet<>());
        }
    }

    public void clear() {
        links.clear();
    }
}
