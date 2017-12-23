package de.hska.iwi.bdelab.batchstore;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import de.hska.iwi.bdelab.schema.Data;
import de.hska.iwi.bdelab.schema.DataUnit;
import de.hska.iwi.bdelab.schema.Page;
import de.hska.iwi.bdelab.schema.PageView;
import de.hska.iwi.bdelab.schema.Pedigree;
import de.hska.iwi.bdelab.schema.UserID;

import org.apache.hadoop.fs.FileSystem;

public class Batchloader {

	private void readPageviewsAsStream() {
		try {
			URI uri = Batchloader.class.getClassLoader().getResource("pageviews.txt").toURI();
			try (Stream<String> stream = Files.lines(Paths.get(uri))) {
				stream.forEach(line -> writeToPail(getDatafromString(line)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	private Data getDatafromString(String pageview) {
		Data result = null;

		StringTokenizer tokenizer = new StringTokenizer(pageview);
		String ip = tokenizer.nextToken();
		String url = tokenizer.nextToken();
		String time = tokenizer.nextToken();

		System.out.println(ip + " " + url + " " + time);

		UserID uid = new UserID();
		uid.set_user_id(ip);

		Page page = new Page();
		page.set_url(url);

		PageView pageView = new PageView(uid, page, 12345);

		DataUnit dataUnit = new DataUnit();
		dataUnit.set_pageview(pageView);

		Pedigree pedigree = new Pedigree(Integer.parseInt(time));

		result.set_dataunit(dataUnit);
		result.set_pedigree(pedigree);

		return result;
	}

	private void writeToPail(Data data) {
	}

	private void importPageviews() {

		// change this to "true" if you want to work
		// on the local machines' file system instead of hdfs
		boolean LOCAL = true;

		try {
			// set up filesystem
			FileSystem fs = FileUtils.getFs(LOCAL);

			// prepare temporary pail folder
			String newPath = FileUtils.prepareNewFactsPath(true, LOCAL);

			// master pail goes to permanent fact store
			String masterPath = FileUtils.prepareMasterFactsPath(false, LOCAL);

			// set up new pail and a stream
			// ...

			// write facts to new pail
			readPageviewsAsStream();

			// set up master pail and absorb new pail
			// ...

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
        Batchloader loader = new Batchloader();
//        loader.importPageviews();
        loader.getDatafromString(pageview)
    	
//    }
}