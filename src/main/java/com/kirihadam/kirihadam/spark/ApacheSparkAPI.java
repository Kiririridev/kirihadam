package com.kirihadam.kirihadam.spark;

import com.kirihadam.kirihadam.misc.TextCountPOJO;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.spark.sql.functions.desc;


@Component
public class ApacheSparkAPI {

	public List<TextCountPOJO> computeTextOccurence(List<String> textList, SQLContext sqlContext) {

		List<TextCountPOJO> stringBeanList = convertStringsToStringBeansClass(textList);

		return computeOccurences(stringBeanList, sqlContext);
	}

	private List<TextCountPOJO> computeOccurences(List<TextCountPOJO> stringBeanList, SQLContext sqlContext) {
		Dataset<Row> textListDF = sqlContext.createDataFrame(stringBeanList, TextCountPOJO.class);
		Dataset<Row> groupedCountedText = textListDF.groupBy("text").count().orderBy(desc("count"));
		List<Row> count = groupedCountedText.collectAsList();

		List<TextCountPOJO> stringCounterList = count
				.stream()
				.map(this::createStringBeanFromRow)
				.collect(toList());

		return stringCounterList;
	}

	private TextCountPOJO createStringBeanFromRow(Row row) {
		return new TextCountPOJO(
				row.getString(0),
				Math.toIntExact(row.getLong(1))
		);
	}

	private List<TextCountPOJO> convertStringsToStringBeansClass(List<String> textList) {
		return textList.stream()
				.map(string -> string.replaceAll("[^’`'A-Za-z0-9\\-]", ""))
				.map(string -> string.toLowerCase())
				.map(TextCountPOJO::new)
				.collect(toList());
	}
}
