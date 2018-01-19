package de.hska.iwi.vsys.bdelab.streaming;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class ExtractDataBolt extends NoisyBolt {

	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		System.out.println(getIDs() + " executes tuple: " + tuple);

		String[] data = tuple.getString(4).split("\\s");
		String url = data[1];
		int timestamp = Integer.parseInt(data[2]);

		String hourbucket = calculateTimeInterval(timestamp);
		String normalizedUrl = normalizeUrl(url);
		
		String timestampWithUrl = hourbucket + " " + normalizedUrl;

		collector.emit(new Values(timestampWithUrl));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("timestampWithUrl"));
	}

	private String normalizeUrl(String url) {
		URL tempUrl;
		String normalizedUrlAsString = "";

		try {
			tempUrl = new URL(url);

			String protocol = tempUrl.getProtocol();
			String host = tempUrl.getHost();
			String path = tempUrl.getPath();

			URL normalizedUrl = new URL(protocol, host, path);
			normalizedUrlAsString = normalizedUrl.toExternalForm();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return normalizedUrlAsString;
	}

	private String calculateTimeInterval(int timestamp) {
		Instant instant = Instant.ofEpochSecond(timestamp);
		LocalDate date = LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toLocalDate();
		int hour = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC).getHour();

		String timestampWithDate = date + "/" + hour;

		return timestampWithDate;
	}

}
