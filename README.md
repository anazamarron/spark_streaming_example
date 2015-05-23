# spark-streaming-skeleton
Basic Spark Streaming application

# Introduction

   The purpose of this application is to provide a basic skeleton of a Spark Streaming application that can be used to further develop streaming programs. The code is based on the NetworkWordCount example provided with the Spark distribution.

   The program reads plain text data from a socket and computes a group-by operation on the input data.

# How to use the skeleton

   To use this example, perform the following steps:

   * Launch *nc* to open a output socket on port 9999. You can write data through the terminal, and it will be send through the socket. Make sure the socket is ready before launching the streaming application. 

    nc -lk 9999

   * Execute the skeleton

    mvn compile exec:java -Dexec.mainClass=org.spark.examples.streaming.StreamingSkeleton

  After that, you can enter data through the nc terminal, and the streaming application will show the groups as in the following example:

```
  -------------------------------------------
  Time: 1432378930000 ms
  -------------------------------------------

  -------------------------------------------
  Time: 1432378940000 ms
  -------------------------------------------
  (2,8)
  (3,3)
  (12,1)
  (1,12)
```


