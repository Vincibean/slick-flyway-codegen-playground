# Slick Flyway Codegen Playground
Playing around with Flyway, Slick and Slick-Codegen

## Prerequisites
This project assumes that both [Java](https://java.com/en/download/) and [SBT](http://www.scala-sbt.org/) are installed.

## How To Run It
From the CLI, run:

`sbt clean flywayMigrate test`

Otherwise, for *nix systems, you can use the `run.sh` script.

## Dataset Description
The data consists of flight arrival and departure details for all commercial flights within the USA in 2008.

The Flight dataset is a modified version of the [dataset](http://www.stat.purdue.edu/~lfindsen/stat350/airline2008NovS.txt) 
provided by [Dr. Leonore Findsen](http://www.stat.purdue.edu/~lfindsen/).

The Plane dataset is a modified version of the [dataset](https://github.com/ProjectMOSAIC/databases/blob/master/Data/plane-data.csv) 
provided by [Project](https://github.com/ProjectMOSAIC) [Mosaic](http://mosaic-web.org/).