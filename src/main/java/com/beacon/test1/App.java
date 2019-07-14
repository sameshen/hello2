package com.beacon.test1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.clearspring.analytics.stream.cardinality.CardinalityMergeException;
import com.clearspring.analytics.stream.cardinality.HyperLogLog;

import java.util.Arrays;

/**
 * Hello world!
 * @author
 */
public class App {
    public static void main(String[] args)  throws CardinalityMergeException {
        System.out.println("hello world");
        //String responseStr = "11112";
        //JSONObject object = JSON.parseObject(responseStr);


        {
            int numToMerge = 5;
            int bits = 16;
            int cardinality = 1000000;

            HyperLogLog[] hyperLogLogs = new HyperLogLog[numToMerge];
            HyperLogLog baseline = new HyperLogLog(bits);
            for (int i = 0; i < numToMerge; i++) {
                hyperLogLogs[i] = new HyperLogLog(bits);
                for (int j = 0; j < cardinality; j++) {
                    double val = Math.random();
                    hyperLogLogs[i].offer(val);
                    baseline.offer(val);
                }
            }


            long expectedCardinality = numToMerge * cardinality;
            HyperLogLog hll = hyperLogLogs[0];
            hyperLogLogs = Arrays.asList(hyperLogLogs).subList(1, hyperLogLogs.length).toArray(new HyperLogLog[0]);
            long mergedEstimate = hll.merge(hyperLogLogs).cardinality();
            long baselineEstimate = baseline.cardinality();
            double se = expectedCardinality * (1.04 / Math.sqrt(Math.pow(2, bits)));

            System.out.println("Baseline estimate: " + baselineEstimate);
            System.out.println("Expect estimate: " + mergedEstimate + " is between " + (expectedCardinality - (3 * se)) + " and " + (expectedCardinality + (3 * se)));

        }
    }
}
