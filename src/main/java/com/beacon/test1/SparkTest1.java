package com.beacon.test1;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * @author Admin
 */
public class SparkTest1 {


    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        SparkConf conf = new SparkConf().setMaster("local").setAppName("HelloSpark");
        try (JavaSparkContext jsc = new JavaSparkContext(conf)) {
            // do something here
            System.out.println("ok");
            JavaRDD<Integer> distData = jsc.parallelize(data);
            JavaRDD<String> distFile = jsc.textFile("file//log.txt");
            System.out.println(distFile.count());
            distData.map(a -> {
                System.out.println(a);
                return (a + 1);
            }).reduce((a, b) -> (a + b));
            JavaPairRDD<String, Integer> pairs = distFile.mapToPair(s -> new Tuple2(s, 1));
            List<Tuple2<String, Integer>> output = pairs.collect();
            for (Tuple2<?, ?> tuple : output) {
                System.out.println(tuple._1() + " : " + tuple._2());
            }

        }

    }
}
