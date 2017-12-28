package de.hska.iwi.bdelab.batchjobs;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;

import de.hska.iwi.bdelab.batchstore.FileUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;

import com.backtype.hadoop.pail.PailFormat;
import com.backtype.hadoop.pail.PailFormatFactory;
import com.backtype.hadoop.pail.PailSpec;

import de.hska.iwi.bdelab.schema2.Data;
import manning.tap2.DataPailStructure;

public class PageviewIndex {

	public static class Map extends MapReduceBase implements Mapper<Text, BytesWritable, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private final static Text pageview = new Text();

		private transient TDeserializer des;

		private TDeserializer getDeserializer() {
			if (des == null)
				des = new TDeserializer();
			return des;
		}

		public Data deserialize(byte[] record) {
			Data ret = new Data();
			try {
				getDeserializer().deserialize((TBase) ret, record);
			} catch (TException e) {
				throw new RuntimeException(e);
			}
			return ret;
		}

		private String createKey(long timestamp, String url) {

			Instant instant = Instant.ofEpochSecond(timestamp);
			LocalDate date = LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toLocalDate();
			int hour = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC).getHour();

			String timestampWithUrl = date + "/" + hour + " " + url;

			return timestampWithUrl;
		}

		public void map(Text key, BytesWritable value, OutputCollector<Text, IntWritable> output, Reporter reporter)
				throws IOException {

			System.out.println(deserialize(value.getBytes()));

			Data data = deserialize(value.getBytes());
			String url = data.get_dataunit().get_pageview().get_page().get_url();
			long timestamp = data.get_pedigree().get_true_as_of_secs();

			pageview.set(createKey(timestamp, url));

			output.collect(pageview, one);
		}
	}

	public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
		public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
				Reporter reporter) throws IOException {
			int sum = 0;

			while (values.hasNext()) {
				sum += values.next().get();
			}
			output.collect(key, new IntWritable(sum));
		}
	}

	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(PageviewIndex.class);
		conf.setJobName("count pageview");
		
		int numberOfReducer = (int) (7 *0.95);
		conf.setNumReduceTasks(numberOfReducer);

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);

		conf.setMapperClass(Map.class);
		conf.setCombinerClass(Reduce.class);
		conf.setReducerClass(Reduce.class);

		// input as pails
		PailSpec spec = PailFormatFactory.getDefaultCopy().setStructure(new DataPailStructure());
		PailFormat format = PailFormatFactory.create(spec);
		String masterPath = FileUtils.prepareMasterFactsPath(false, false);
		conf.setInputFormat(format.getInputFormatClass());
		FileInputFormat.setInputPaths(conf, new Path(masterPath));

		// output as text
		conf.setOutputFormat(TextOutputFormat.class);
		FileSystem fs = FileUtils.getFs(false);
		FileOutputFormat.setOutputPath(conf, new Path(FileUtils.getTmpPath(fs, "pageview-index", true, false)));
		
		JobClient.runJob(conf);
	}
}