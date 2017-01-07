package com.agmednet.judi.tests;

import com.agmednet.judi.appmanager.HttpSession;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by pshynin on 9/15/16.
 */
public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("operations", "Sail2Fast!"));
    assertTrue(session.isLoggedInAs("operations"));
  }
}
