# totechallenge
In response to a programming challenge.

*tl;dr*: After building via `mvn package`, `cd` to the `target` directory and run `java -jar totechallange-0.0.1-SNAPSHOT-jar-with-dependencies.jar .\classes\products.csv BranchAndBound` to get the answer to the challenge.


## Description

Implements the following approaches for solving the challenge:

* Greedy
* Dynamic Programming
* Memory Function
* Brute Force - for reference only since it theoretically provides the optimal value but is not feasible to run on such a large data set.
* Branch and Bound - like Brute Force provides the optimal value but with a potentially lesser time complexity, and in the case of this challenge's data set is indeed a very significant improvement over Brute Force, allowing the optimal value to be arrived upon in under 30 minutes.

There are other possible approaches or combination of approaches such as Genetic Algorithm, Divide and Conquer + DP which aren't implemented.

## Pre-requisites
* Java 8
* Maven 3.x
* Enough memory to run this Java console application with the -Xmx5120m and -Xss8m parameters if you want to run Dynamic Programming or Memory Function algorithms. The -Xss option is required only for Memory Function.

## Building

    mvn package

## Running

    cd ./target
    java -Xmx5120m -Xss8m -jar totechallange-0.0.1-SNAPSHOT-jar-with-dependencies.jar <csv file path> [algorithm]

Where algorithm is one of `Greedy`, `DynamicProgramming`, `MemoryFunction`, `BranchAndBound`, or `BruteForce`. If you run BruteForce you won't get to see the results. If algorithm is omitted, all algorithms are run except BruteForce.

You can use .\classes\products.csv as the file path if running from the `target` directory.