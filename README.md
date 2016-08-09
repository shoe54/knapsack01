# totechallenge

In response to the programming challenge at http://geeks.redmart.com/2015/10/26/1000000th-customer-prize-another-programming-challenge/

tl;dr: After building via `mvn package`, `cd` to the `target` directory and run `java -jar totechallange-0.0.2-SNAPSHOT-jar-with-dependencies.jar .\classes\products.csv BranchAndBound` to get the answer to the challenge.


## Description

Implements the following approaches:

* Greedy - example of an approximate optimization
* Dynamic Programming
* Memory Function
* Brute Force - for reference only since it is not feasible to run to completion on such a large data set.
* Branch and Bound

There are other approximation approaches such as Genetic Algorithm, Monte Carlo, and randomized heuristics which are not implemented.

## Pre-requisites
* Java 8 (must be 64-bit if you want to run Dynamic Programming or Memory Function algorithms)
* Maven 3.x
* Enough memory to run this Java console application with the -Xmx6144m and -Xss8m parameters if you want to run the Dynamic Programming or Memory Function algorithms. The -Xss option is required only for Memory Function. More heap space improves the performance of these algorithms. 

## Building

    mvn package

## Running

    cd ./target
    java -Xmx6144m -Xss8m -jar totechallange-0.0.2-SNAPSHOT-jar-with-dependencies.jar <csv file path> [algorithm]

Where `algorithm` is one of `Greedy`, `DynamicProgramming`, `MemoryFunction`, `BranchAndBound`, or `BruteForce`. If you run BruteForce you won't get to see the results. If algorithm is omitted, all algorithms are run except BruteForce.

You can use .\classes\products.csv as the CSV file path if running from the `target` directory.

See notes in Pre-requisites section about whether you need to include the `-Xmx` and `-Xss` options.

## Changelog

* 0.0.2-SNAPSHOT: I previously interpreted the language *"...fit into the tote both individually and together by total volume.."* to mean *...fit into the tote both individually by volume and...* . On hindsight it probably meant *...fit into the tote both individually by dimensions and...*. Corrected my program logic accordingly.
* 0.0.1-SNAPSHOT: Submitted to RedMart.