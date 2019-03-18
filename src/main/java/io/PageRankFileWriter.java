package io;

import cli.PageRankOptions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class PageRankFileWriter {

    public void writeFile(Map<String, Double> pageRankResults, PageRankOptions options, int numberOfIterations) throws IOException {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(options.outputFile)))) {
            bufferedWriter.write("Max number of iterations: " + options.maxIterations + System.lineSeparator());
            bufferedWriter.write("Teleport parameter: " + options.teleport + System.lineSeparator());
            bufferedWriter.write("Convergence parameter: " + options.convergenceFactor + System.lineSeparator());
            bufferedWriter.write("Number of iterations occurred: " + numberOfIterations + System.lineSeparator());
            bufferedWriter.write(System.lineSeparator());

            for (String key : pageRankResults.keySet()) {
                bufferedWriter.write(key + " " + pageRankResults.get(key) + System.lineSeparator());
            }
        }
    }
}
