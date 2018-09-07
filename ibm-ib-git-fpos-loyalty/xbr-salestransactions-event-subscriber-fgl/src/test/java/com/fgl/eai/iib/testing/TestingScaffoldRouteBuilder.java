package com.fgl.eai.iib.testing;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by dmarley on 2015-05-19.
 */
@Configuration
@ComponentScan(basePackages = "com.fgl.eai.iib.testing")
@Import(WmqConfig.class)
public class TestingScaffoldRouteBuilder extends SingleRouteCamelConfiguration {
    @Override
    public RouteBuilder route() {
        return new RouteBuilder() {

            public void configure() {
                getContext().disableJMX();

            }

        };
    }
}
