/*
 * Copyright (c) 2015 Daniel Higuero.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spark.examples.streaming

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel

/**
 * Spark Streaming skeleton application. The application uses a socket stream to localhost
 * on port 9999 and reads plain text messages from it. It then proceeds to group and count
 * elements together and prints 10 on standard output.
 */
object StreamingSkeleton {

  def main(args : Array[String]) {

    //Suppress Spark output
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)

    //Define the Spark configuration. In this case we are using the local mode
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("Streaming Skeleton")
    //Define a SparkStreamingContext with a batch interval of 10 seconds
    val ssc = new StreamingContext(sparkConf, Seconds(10))

    //Connect to a socket and read a text stream
    val events = ssc.socketTextStream("localhost", 9999, StorageLevel.MEMORY_AND_DISK_SER)
    //Filter out empty lines and print the count
    val numberEvents = events.filter(e => !e.isEmpty).countByValue()
    //Print 10 of them
    numberEvents.print(10)

    //Start the streaming context
    ssc.start()
    ssc.awaitTermination()

  }

}
