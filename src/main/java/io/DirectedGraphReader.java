package io;

import models.DirectedGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirectedGraphReader {

    public DirectedGraph readFile(String file) throws IOException {

        DirectedGraph directedGraph = new DirectedGraph();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] segments = line.split("\\s+");
                if (segments.length != 2) {
                    throw new IOException("Failed to read line '" + line + "'");
                }
                directedGraph.addLink(segments[0], segments[1]);
            }
        }
        return directedGraph;
    }
}
