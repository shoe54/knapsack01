# totechallenge
In response to RedMart's Millionth's Customer Prize challenge at http://geeks.redmart.com/2015/10/26/1000000th-customer-prize-another-programming-challenge/

## Description

Implements the following approaches for solving the problem:
* Greedy
* Dynamic Programming
* Memory Function
* Brute Force (for reference only since it theoretically provides the optimal solution but is not feasible to run on such a large data set)

There are other approaches or combination of approaches which aren't implemented such as Genetic Algorithm, Divide and Conquer + DP.

## Pre-requisites
* Java 8
* Maven 3.x
* Enough memory to run this Java console application with the -Xmx5120m and -Xss8m parameters (required for the Dynamic Programming and Memory Function algorithms respectively)

## Building

mvn package

## Running

cd ./target
java -Xmx5120m -jar totechallange-0.0.1-SNAPSHOT-jar-with-dependencies.jar <csv file path>