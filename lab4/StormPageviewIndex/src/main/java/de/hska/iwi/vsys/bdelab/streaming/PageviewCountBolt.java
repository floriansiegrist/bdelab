package de.hska.iwi.vsys.bdelab.streaming;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

public class PageviewCountBolt extends NoisyBolt {
    private Map<String, Integer> counts = new HashMap<>();

    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
        System.out.println(getIDs() + " executes tuple: " + tuple);

        String timestampWithUrl = tuple.getString(0);        
        Integer count = counts.get(timestampWithUrl);
        if (count == null) {
            count = 0;
        }
        count++;
        counts.put(timestampWithUrl, count);

        Values values = new Values(timestampWithUrl, count);
        System.out.println(getIDs() + " result values: " + values);

        collector.emit(values);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("timestampWithUrl", "count"));
    }
}
