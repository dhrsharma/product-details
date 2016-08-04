package com.dsworks.retail.health;

import com.codahale.metrics.health.HealthCheck;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RetailHealthCheckTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();


    @Test
    public void healthyCheck() throws Exception {
        RetailHealthCheck retailHealthCheck = new RetailHealthCheck();
        HealthCheck.Result result = retailHealthCheck.check();
        assertThat(result.isHealthy()).isEqualTo(true);
    }

    @Test
    public void testExceptionFromCheck() throws Exception {
        expectedException.expect(Exception.class);
        RetailHealthCheck retailHealthCheck = mock(RetailHealthCheck.class);
        when(retailHealthCheck.check()).thenThrow(Exception.class);
        retailHealthCheck.check();
    }
}
