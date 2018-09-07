package com.marks.eai.iib.testing;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.marks.eai.iib.testing")
@Import(WmqConfig.class)
public class EmptyRouteCamelConfiguration extends SingleRouteCamelConfiguration {

	/**
	 * An empty route, returning null causes NPE in the abstract CamelConfiguration parent.
	 * As a side-effect it disables JMX since that is not needed when testing.
	 */
	@Override
	public RouteBuilder route() {
		return new RouteBuilder() {

//			public void configure() {
//				from("direct:start").filter(header("foo").isEqualTo("bar")).to("mock:result");
//			}

			@Override
			public void configure() throws Exception {
				getContext().disableJMX();

			}
		};
	}

}
