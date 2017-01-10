package com.agmednet.judi.tests;

import org.testng.annotations.Test;


/**
 * Created by Pasha Shynin on 8/30/2016.
 */
public class DbConnectionTest {

  @Test
  public void testDbConnection() {
//    Connection conn = null;
//    try {
//      conn =
//              DriverManager.getConnection("jdbc:psql[10.0.8.186] -U agmednet portal_db?&user=root&password=");
//      Statement st = conn.createStatement();
//      ResultSet rs = st.executeQuery("select clinicaltrialsiteid, clinicaltrialsitename, country_id from trialsite limit 1");
//      Sites trialSites = new Sites();
//      while (rs.next()) {
//        trialSites.add(new SiteData().withSiteId(rs.getString("clinicaltrialsiteid")).withSiteDescription(rs.getString("clinicaltrialsitename"))
//                .withCountry(rs.getString("country_id")));
//      }
//      rs.close();
//      st.close();
//      conn.close();
//
//      System.out.println(trialSites);
//
//    } catch (SQLException ex) {
//      System.out.println("SQLException: " + ex.getMessage());
//      System.out.println("SQLState: " + ex.getSQLState());
//      System.out.println("VendorError: " + ex.getErrorCode());
//    }

  }
}