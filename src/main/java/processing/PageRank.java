package processing;

import models.DirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class PageRank {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageRank.class);

    private double[] pageRank;
    private DirectedGraph directedGraph;

    private int maxIterations;
    private double convergenceFactor;
    private double teleport;

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    private int numberOfIterations = 0;

    public PageRank(int maxIterations, double convergenceFactor, double teleport) {
        this.maxIterations = maxIterations;
        this.convergenceFactor = convergenceFactor;
        this.teleport = teleport;
    }

    private void initializePageRankVector() {
        pageRank = new double[directedGraph.getLinks().size()];
        for (int i = 0; i < directedGraph.getLinks().size(); i++) {
            pageRank[i] = 1.0 / directedGraph.getLinks().size();
        }
        LOGGER.info("Initializing page rank vector with value {0}", 1.0 / directedGraph.getLinks().size());
    }

    private double[] iterate() {
        int rowIndex = 0;
        double[] newPageRank = new double[pageRank.length];
        Arrays.fill(newPageRank, 0.0);
        for (String node : directedGraph.getLinks().keySet()) {
            int columnIndex = 0;
            for (String outputLink : directedGraph.getLinks().keySet()) {
                double M = 0;
                if (directedGraph.getLinks().get(outputLink).contains(node)) {
                    M = 1.0 / directedGraph.getLinks().get(outputLink).size();
                }
                LOGGER.info("For element ({},{}), M = {}/{} = {}", node, outputLink, pageRank[columnIndex], directedGraph.getLinks().get(outputLink).size(), M);
                double valToAdd = pageRank[columnIndex] * ((teleport * M) + ((1 - teleport) * (1.0 / pageRank.length)));
                newPageRank[rowIndex] += valToAdd;
                LOGGER.info("Adding {} to new page rank for total of {}.", valToAdd, newPageRank[rowIndex]);
                columnIndex++;
            }
            LOGGER.info("Page rank for {} is {}", node, newPageRank[rowIndex]);
            rowIndex++;
        }
        LOGGER.info("Iteration {}", numberOfIterations + 1);
        return newPageRank;
    }

    public Map<String, Double> compute(DirectedGraph directedGraph) {
        this.directedGraph = directedGraph;
        initializePageRankVector();

        boolean convergence = false;
        numberOfIterations = 0;
        for (numberOfIterations = 0; numberOfIterations < maxIterations && !convergence; numberOfIterations++) {
            double[] newPageRank = iterate();
            convergence = true;
            for (int j = 0; j < newPageRank.length; j++) {
                double percentChanged = percentChanged(newPageRank[j], pageRank[j]);
                pageRank[j] = newPageRank[j];
                if (percentChanged > convergenceFactor) {
                    convergence = false;
                }
            }
        }
        Map<String, Double> results = new LinkedHashMap<>();
        int index = 0;
        for (String node : directedGraph.getLinks().keySet()) {
            results.put(node, pageRank[index++]);
        }
        return results;
    }

    private double percentChanged(double value1, double value2) {
        double max = Math.max(value1, value2);
        double min = Math.min(value1, value2);
        return 1 - (min / max);
    }
}
