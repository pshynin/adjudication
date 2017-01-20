package com.agmednet.debug;

import com.codepine.api.testrail.TestRail;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 1/19/17.
 */
public class TestRailAPITest {

    TestRail testRail = TestRail.builder("https://some.testrail.net/", "username", "password")
            .applicationName("playground").build();

    @Test
    public void testAPI() {
    }
}
