package com.knowit.AnalyticsService.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
	
	@Bean
	public SparkConf sparkConf()
	{
		return new SparkConf().setAppName("spark for analytics service")
							  .setMaster("local[*]");
	}
	
	@Bean
	public JavaSparkContext javaSparkContext()
	{
		return new JavaSparkContext(sparkConf());
	}
	
	@Bean
	public SparkSession sparkSession()
	{
		return SparkSession.builder().sparkContext(javaSparkContext().sc()).getOrCreate();
	}
	

}
