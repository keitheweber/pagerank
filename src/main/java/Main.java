import cli.PageRankOptions;
import com.google.devtools.common.options.OptionsParser;
import io.DirectedGraphReader;
import io.PageRankFileWriter;
import models.DirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.PageRank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String args[]) throws IOException {
        OptionsParser optionsParser = OptionsParser.newOptionsParser(PageRankOptions.class);
        optionsParser.parseAndExitUponError(args);

        PageRankOptions pageRankOptions = optionsParser.getOptions(PageRankOptions.class);

        File inputFile = new File(pageRankOptions.inputFile);
        if (!inputFile.exists()) {
            throw new FileNotFoundException(String.format("Input file does not exist. '%s'", pageRankOptions.inputFile));
        }
        if (pageRankOptions.outputFile.isEmpty() || pageRankOptions.convergenceFactor <= 0.0 || pageRankOptions.convergenceFactor > 100.0 ||
                pageRankOptions.maxIterations <= 0 || pageRankOptions.teleport <= 0.0 || pageRankOptions.teleport > 100.0) {
            printUsage(optionsParser);
            return;
        }

        DirectedGraphReader reader = new DirectedGraphReader();
        DirectedGraph directedGraph = reader.readFile(pageRankOptions.inputFile);

        PageRank pageRank = new PageRank(pageRankOptions.maxIterations, pageRankOptions.convergenceFactor, pageRankOptions.teleport);
        Map<String, Double> results = pageRank.compute(directedGraph);
        PageRankFileWriter writer = new PageRankFileWriter();
        writer.writeFile(results, pageRankOptions, pageRank.getNumberOfIterations());
    }

    private static void printUsage(OptionsParser optionsParser) {
        System.out.println("Usage: java -jar pagerank.jar OPTIONS");
        System.out.println(optionsParser.describeOptions(Collections.emptyMap(),
                OptionsParser.HelpVerbosity.LONG));
    }
}
