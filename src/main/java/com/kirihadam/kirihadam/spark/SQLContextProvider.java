package com.kirihadam.kirihadam.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class SQLContextProvider {

	private SQLContext sqlContext;

	public SQLContext getSQLContext() {

		if(sqlContext == null) {
			sqlContext = createSQLContext();
		}

		return sqlContext;
	}

	private SparkConf getSparkConf() {

		return new SparkConf()
				.setMaster("local[*]")
				.setAppName("kiriSpark");
	}

	@PostConstruct
	public void initializeSparkContext() {
		sqlContext = createSQLContext();

	}

	private SQLContext createSQLContext() {
		SparkConf conf = getSparkConf();
		JavaSparkContext sc = new JavaSparkContext(conf);

		return new SQLContext(sc);
	}

}
