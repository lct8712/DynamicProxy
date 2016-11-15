package com.chentian.proxy;

import com.chentian.proxy.factory.ServiceFactory;
import com.chentian.proxy.service.ApiService;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FactoryUnitTest {
    @Test
    public void serviceFactory() throws Exception {
        ApiService apiService = ServiceFactory.create(ApiService.class);

        apiService.getRunningLog("number", 5);
        System.out.println();

        apiService.saveRunningLog("count", 100);
    }
}
