package com.kirihadam.kirihadam;

import com.kirihadam.kirihadam.spark.ApacheSparkAPI;
import com.kirihadam.kirihadam.spark.SQLContextProvider;
import com.kirihadam.kirihadam.webscrapper.Webscrapper;

public class ApplicationCLIRunner {

	private static final String url = "https://jaunt-api.com/";

	public static void main(String[] args) throws Exception {
		ApplicationCLIRunner cliRunner = new ApplicationCLIRunner();

		cliRunner.run();
	}

	private void run() throws Exception {

		StatisticsScrapperAPI scrapperAPI = new StatisticsScrapperAPI(
				new Webscrapper(), new ApacheSparkAPI(), new SQLContextProvider()
		);

		scrapperAPI.scrapAndComputePage(url);
	}
}
