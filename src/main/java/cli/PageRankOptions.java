package cli;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

public class PageRankOptions extends OptionsBase {

    @Option(
            name = "inputFile",
            abbrev = 'i',
            help = "Path to input file.",
            defaultValue = ""
    )
    public String inputFile;

    @Option(
            name = "outputFile",
            abbrev = 'o',
            help = "Path to output file.",
            defaultValue = ""
    )
    public String outputFile;

    @Option(
            name = "convergenceFactor",
            abbrev = 'c',
            help = "Convergence factor. (value between 0 and 1)",
            defaultValue = ".05"
    )
    public double convergenceFactor;


    @Option(
            name = "maxIterations",
            abbrev = 'm',
            help = "Maximum number of iterations.",
            defaultValue = "100"
    )
    public int maxIterations;

    @Option(
            name = "teleport",
            abbrev = 't',
            help = "Teleport parameter. (value between 0 and 1)",
            defaultValue = ".85"
    )
    public double teleport;
}
