package com.agmednet.judi.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha Shynin on 8/15/2016.
 */
public class EventDataGenerator {
  @Parameter(names = "-c", description = "Account count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    EventDataGenerator generator = new EventDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<UserAccountData> accounts = generateAccounts(count);
    if (format.equals("csv")) {
      saveasCSV(accounts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(accounts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(accounts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private static void saveasCSV(List<UserAccountData> accounts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (UserAccountData account : accounts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s\n", account.getFirstname(), account.getLastname(),
                account.getCity(), account.getPhonenumber(), account.getEmailaddress(), account.getTitle()));
      }
    }
  }

  private void saveAsJson(List<UserAccountData> accounts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(accounts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<UserAccountData> accounts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(UserAccountData.class);
    String xml = xStream.toXML(accounts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private static List<UserAccountData> generateAccounts(int count) {
    List<UserAccountData> accounts = new ArrayList<UserAccountData>();
    for (int i = 0; i < count; i++) {
      accounts.add(new UserAccountData().withFirstname(String.format("FirsnameTest%s", i)).withLastname(String.format("LastnameTest%s", i))
              .withCity(String.format("City%s", i)).withPhonenumber(String.format("123-123%s", i))
              .withEmailaddress(String.format("pavlo-test%s@@mx-intr.agmednet.net", i)).withTitle(String.format("testTitle%s", i)));
    }
    return accounts;
  }
}
