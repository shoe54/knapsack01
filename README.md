# knapsack01

General library for solving 0/1 knapsack problems.

## Description

This library provides one of several approaches for solving the 0/1 knapsack problem. The approaches are:

* Greedy - provides an approximate optimization
* Dynamic Programming
* Memory Function
* Brute Force - not feasible to run to completion on large data sets.
* Branch and Bound

There are other approximation approaches such as Genetic Algorithm, Monte Carlo, and randomized heuristics which are not implemented.

In this library, a general `Pool` interface is used to represent the 'knapsack' and a general `Item` interface is used to represent the knapsack items. Items can be allocated/added
to/from the Pool depending on how you are interpreting the particular knapsack problem you are working on. `Item`s have a generalized value (e.g. price/value of the knapsack item to pack) and cost (e.g. weight/volume of the item). `Pool`s have a total allowed cost (e.g. total size/weight allowance of the knapsack).

Several examples on using this library are provided, listed in order of increasing complexity:

* An example of allocating advertising budget (`Pool`) to online ad spending (`Item`s). The value of the online ad spend in the expected number of ad impressions. The cost is the price of the ad spend. main() is in `nz.co.lingo.algos.knapsack01.examples.advertising.App`
* A traditional 0/1 knapsack example where `Item` value is the dollar price and cost is the weight. main() is in `nz.co.lingo.algos.knapsack01.examples.knapsack.App`
* A more complex example. This example is a solution to the problem described at http://geeks.redmart.com/2015/10/26/1000000th-customer-prize-another-programming-challenge/ . main() is at `nz.co.lingo.algos.knapsack01.examples.shopping.App`

An `Item`'s value can be a more complex object instead of just a Number. This is represented by the `ComplexItemValue` interface. This allows the delegation of value comparisons to your own implementations. For simple `Item`s with Integer values the `DefaultItem` and `DefaultPool` classes are provided for convenience. Other items with numeric values can be subclassed from `AbstractNumberItem`. Unless your domain's Pool object already subclasses from another class, `GenericPool` is a sensible default implementation.

## Pre-requisites

* Java 8 (must be 64-bit if you want to run Dynamic Programming or Memory Function algorithms on large data sets).
* Maven 3.x if you want to build this library.

## Building

    mvn package

## Running the examples

After building via `mvn package`, `cd` to the `target` directory and run one of the following:

* Advertising budget example: `java -cp knapsack01-0.0.3-SNAPSHOT-jar-with-dependencies.jar nz.co.lingo.algos.knapsack01.examples.advertising.App`
* Knapsack example: `java -cp knapsack01-0.0.3-SNAPSHOT-jar-with-dependencies.jar nz.co.lingo.algos.knapsack01.examples.knapsack.App`
* Shopping example: `java -cp knapsack01-0.0.3-SNAPSHOT-jar-with-dependencies.jar nz.co.lingo.algos.knapsack01.examples.shopping.App .\classes\nz\co\lingo\algos\knapsack01\examples\shopping\products.csv BranchAndBound` to get the answer to the challenge.
    * The general form for this example is:

`cd ./target`
`java [-Xmx6144m] [-Xss8m] -cp knapsack01-0.0.3-SNAPSHOT-jar-with-dependencies.jar nz.co.lingo.algos.knapsack01.examples.shopping.App <csv file path> [algorithm]`

    * Where `algorithm` is one of `Greedy`, `DynamicProgramming`, `MemoryFunction`, `BranchAndBound`, or `BruteForce`. If you run BruteForce you won't get to see the results. If algorithm is omitted, all algorithms are run except BruteForce.
    * You will only need the -Xmx6144m and -Xss8m parameters if you want to run the Dynamic Programming or Memory Function algorithms. The -Xss option is required only for Memory Function. More heap space improves the performance of these algorithms. 

## Changelog

* 0.0.3-SNAPSHOT: Generalized library, no longer limited to solving RedMart's challenge
* 0.0.2-SNAPSHOT: I previously interpreted the language *"...fit into the tote both individually and together by total volume.."* to mean *...fit into the tote both individually by volume and...* . On hindsight it probably meant *...fit into the tote both individually by dimensions and...*. Corrected my program logic accordingly.
* 0.0.1-SNAPSHOT: Submitted to RedMart.