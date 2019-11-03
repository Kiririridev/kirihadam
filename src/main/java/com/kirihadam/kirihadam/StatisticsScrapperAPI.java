package com.kirihadam.kirihadam;

import com.kirihadam.kirihadam.spark.ApacheSparkAPI;
import com.kirihadam.kirihadam.spark.SQLContextProvider;
import com.kirihadam.kirihadam.misc.TextCountPOJO;
import com.kirihadam.kirihadam.webscrapper.Webscrapper;
import lombok.AllArgsConstructor;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.split;

@Component
@AllArgsConstructor
public class StatisticsScrapperAPI {

	@Autowired
	private Webscrapper webscrapper;
	@Autowired
	private ApacheSparkAPI sparkAPI;
	@Autowired
	private SQLContextProvider sqlContextProvider;

	public List<TextCountPOJO> scrapAndComputePage(String url) throws Exception {

		SQLContext sqlContext = sqlContextProvider.getSQLContext();
		String scrappedText = webscrapper.scrap(url);

		List<String> splitedText = Arrays.asList(split(scrappedText));
		return sparkAPI.computeTextOccurence(splitedText, sqlContext);
	}
}
