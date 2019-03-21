package com.graphite.demo.config;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

@Configuration
public class MetricsConfiguration {
	@Value("${metrics.graphite.host}")
	private String host;
	@Value("${metrics.graphite.port}")
	private Integer port;
	@Value("${metrics.graphite.period}")
	private Long period;
	@Value("${metrics.graphite.prefix}")
	private String prefix;

	@Bean
	public MetricRegistry metricsRegistry() {
		return new MetricRegistry();
	}
	
	@Bean
	public GraphiteReporter graphiteReporter() {
		final GraphiteReporter reporter = GraphiteReporter.forRegistry(metricsRegistry())
				.convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).prefixedWith(this.prefix+"."+1)
				.build(new Graphite(new InetSocketAddress(this.host, this.port)));
		reporter.start(this.period, TimeUnit.SECONDS);
		return reporter;
	}

//	@Bean
//	public ConsoleReporter consoleReporter() {
//		ConsoleReporter reporter = ConsoleReporter.forRegistry(metricsRegistry()).convertRatesTo(TimeUnit.SECONDS)
//				.convertDurationsTo(TimeUnit.MILLISECONDS).build();
//		reporter.start(this.period, TimeUnit.SECONDS);
//		return reporter;
//	}
}
