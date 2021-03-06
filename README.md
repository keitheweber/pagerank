# PageRank

Computes the page rank of each node of a directed graph.

** Problem 1 **

Problem 1 (5 points): Simple PageRank Scores
Write a program using any programing language of your choice (C/C++, Python, Java, MatLab, R,
etc.) to compute PageRank scores for an input web-graph. You must use the power iteration
method (based on Matrix-Vector multiplication) discussed in the class. You must continue
iterating until convergence is achieved or you pass 100 iterations, whichever is earlier. When
none of the values changes more than 5% of that of the previous iteration, we call this a
convergence.
Requirements:
1. The input should come from a file. The input graph may be specified in any convenient
format, e.g, adjacency list, adjacency matrix, edge list, etc. For example, in an edge-list
format, a two-vertex graph with vertex IDs 0 and 1 and directed links from 0 to 1, 1 to 0,
a self-link from 0 to 0 can be specified in the input file as follows:
0 1
1 0
0 0
(One link per line, where the first number is the source ID and the second the destination
ID of the link.)
2. You must write the results in another output file. The output file should contain in each
line a pair of numbers as <Vertex ID, PageRank Value>.

** Problem 2 **

Problem 2 (5 points): PageRank with Teleportation
Repeat problem 1 using Google’s formulation of PageRank. Use a teleportation parameter value
of 0.85. Find in lecture slides an example of PageRank computation with teleportation
parameter using power iteration method.

For both problem 1 and problem 2: provide four test input files—one for a simple 3-
vertex graph from lecture slides, another 3-vertex graph with spider trap, yet another 3-
vertex graph with a dead end, and a larger graph (with >5 vertices). You may use the
same test files for both problems. For each of them, provide the output file generated by
your code. Add a report (text or doc file) that tells how many iterations it took for each
input graph to achieve convergence. Put all files and report for each problem in a
folder/directory using the following naming convention:
Folder for problem 1: Firstname_Lastname_HW2_Prob1
Folder for problem 2: Firstname_Lastname_HW2_Prob2


# Submission

data/Keith_Weber_HW2.zip

# Compile

Execute the following command to build the application.

./gradlew fatJar

The executable jar will be available at build/lib/pagerank-all-1.0-SNAPSHOT.fatJar

# Running the application

Command line arguments
-i input file
-o output file
-c convergence factor (optional: default value .05)
-m maximum number of iterations (optional: default value 100)
-t teleport parameter (optional: default value .85)

example:
java -jar build/lib/pagerank-all-1.0-SNAPSHOT.fatJar -i data/input_file.txt -o data/output_file.txt -c .1 -m 50 -t .8

# Input file format

Each line should contain 2 symbols separated with a space. This represents a directional link from symbol 1 to symbol 2.

Example file contents:   
a b    
b c    
c a    

# Output file format

The output file contains a header consisting of 4 lines that describe the inputs that were used to run the application and the number of iterations that occurred. The header is followed by a single blank line, then a line for each node in the directed graph and it's computed page rank value.

Example file contents:    
Max number of iterations: 100    
Teleport parameter: 0.85    
Convergence parameter: 0.05    
Number of iterations occurred: 1    

a 0.3333333333333333    
b 0.3333333333333333    
c 0.3333333333333333    


# Details

Further details are available in the logs. Logback is the log engine used by the application. By default, the log output will print to the console and print values of each iteration.
