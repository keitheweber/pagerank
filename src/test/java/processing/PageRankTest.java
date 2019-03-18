package processing;

import models.DirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PageRankTest {

    @Test
    public void simplePageRank() {
        PageRank pageRank = new PageRank(100, .05, .8);

        String y = "y";
        String a = "a";
        String m = "m";
        DirectedGraph directedGraph = new DirectedGraph();
        directedGraph.addLink(y, y);
        directedGraph.addLink(y, a);
        directedGraph.addLink(a, y);
        directedGraph.addLink(a, m);
        directedGraph.addLink(m, m);

        Map<String, Double> results = pageRank.compute(directedGraph);
    }

    @Test
    public void simplePageRank2() {
        PageRank pageRank = new PageRank(100, .05, 1);

        String y = "y";
        String a = "a";
        String m = "m";
        DirectedGraph directedGraph = new DirectedGraph();
        directedGraph.addLink(y, m);
        directedGraph.addLink(y, a);
        directedGraph.addLink(a, y);
        directedGraph.addLink(a, m);
        directedGraph.addLink(m, a);
        directedGraph.addLink(m, y);

        Map<String, Double> results = pageRank.compute(directedGraph);
    }
}
