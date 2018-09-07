package com.fgl.ib.testing;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by dmarley on 2015-05-19.
 */
@Configuration
@ComponentScan(basePackages = "com.fgl.ib.testing.pmm.publisher")
@Import(WmqConfig.class)
public class TestingScaffoldRouteBuilder extends SingleRouteCamelConfiguration {
    @Override
    public RouteBuilder route() {
        return new RouteBuilder() {

            public void configure() {
                getContext().disableJMX();

                //STORE ROUTING HANDLER
                //from("jms:RECV.MWW.PMM.EVENT_STORE_IN.IBM_IB.DV").to("jms:OUT");

                //VENDOR ROUTING HANDLER
                //from("jms:DEREK_VENDOR_IN").to("jms:DEREK_OUT");

                //Route Message to the Mock Endpoint for assertions
                from("jms:OUT").to("mock:result");

            }

        };
    }
}
