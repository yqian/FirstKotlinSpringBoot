package org.yqian.job.configuration;

import org.yqian.job.FeeTrack;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<FeeTrack> reader() {
        return new FlatFileItemReaderBuilder<FeeTrack>()
                .name("FeeTrackItemReader")
                .resource(new ClassPathResource("fee.txt"))
                .delimited()
                .names(new String[]{"CORE_PROC_CLNT_ID", "CARD_PROC_APPL_ID", "ACCT_SUFX_NB", "ACCT_REF_NB", "FEE_TYPE_CD", "ACCT_TYPE_CD", "CRE_TS"})
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<FeeTrack>() {{
                    setTargetType(FeeTrack.class);
                }})
                .build();
    }

    @Bean
    public LineMapper<FeeTrack> lineMapper() {

        final DefaultLineMapper<FeeTrack> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("|");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] {"CORE_PROC_CLNT_ID", "CARD_PROC_APPL_ID", "ACCT_SUFX_NB", "FEE_TYPE_CD", "ACCT_REF_NB", "ACCT_TYPE_CD", "CRE_TS"});

        final FeeTrackFieldSetMapper fieldSetMapper = new FeeTrackFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    @Bean
    public FeeTrackProcessor processor() {
        return new FeeTrackProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<FeeTrack> writer(final DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<FeeTrack>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO fee_trck (CORE_PROC_CLNT_ID, CARD_PROC_APPL_ID, ACCT_SUFX_NB, FEE_TYPE_CD, ACCT_REF_NB, ACCT_TYPE_CD, CRE_TS) VALUES (:CORE_PROC_CLNT_ID, :CARD_PROC_APPL_ID, :ACCT_SUFX_NB, :FEE_TYPE_CD, :ACCT_REF_NB, :ACCT_TYPE_CD, :CRE_TS)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job feeTrackJob(NotificationListener listener, Step step1) {
        return jobBuilderFactory.get("feeTrackJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<FeeTrack> writer) {
        return stepBuilderFactory.get("step1")
                .<FeeTrack, FeeTrack> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
