package com.fgl.stub.payload;

import static org.junit.Assert.assertNotNull;

/**
 * Created by dmarley on 2015-05-20.
 */
public class StubTests {
	public void testEmployeeSubscriberStubNotNull() {
        String xml = com.fgl.stub.payload.employee.EmployeeSubscriberStub.getXml("I");
        assertNotNull(xml);
    }
}
