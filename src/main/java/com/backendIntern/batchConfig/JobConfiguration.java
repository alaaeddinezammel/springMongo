package com.backendIntern.batchConfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.backendIntern.model.logLine;
import com.mongodb.MongoClient;

@Configuration
public class JobConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	

	@Bean
	public FlatFileItemReader<String> CusItemReader() {
		FlatFileItemReader<String> reader = new FlatFileItemReader<>();

		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("/data/sle-sle69_300.2017-12-28.log"));

		// DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		// tokenizer.setNames(new String[] {"id", "firstName", "lastName",
		// "birthdate"});

		// customerLineMapper.setLineTokenizer(tokenizer);
		// customerLineMapper.setFieldSetMapper(new CustomerFieldSetMapper());
		// customerLineMapper.afterPropertiesSet();

		reader.setLineMapper(new PassThroughLineMapper());

		return reader;
	}

	/*
	 * @Bean public ItemWriter<logLine> LogItemWriter() { return items ->{
	 * for(logLine item:items) { System.out.println(item.toString()); } }; }
	 */



	@Bean
	public MongoItemWriter<logLine> LogItemWriter() {
		MongoItemWriter<logLine> writer = new MongoItemWriter<logLine>();
	        try {
	            writer.setTemplate(mongoTemplate());
	        } catch (Exception e) {
	            System.out.println(e.toString());
	        }
	        writer.setCollection("logCollection");
	        return writer;
	    }
	
	
	@Bean
	public ItemProcessor<String, logLine> itemProcessorr() {
		return new FiltringItemProcessor();

	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<String, logLine>chunk(10).reader(CusItemReader())
				.processor(itemProcessorr()).writer(LogItemWriter()).build();
	}

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").start(step1()).build();
	}

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "springbatch");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
	
	
	
	
	
	
	
	
	

}
