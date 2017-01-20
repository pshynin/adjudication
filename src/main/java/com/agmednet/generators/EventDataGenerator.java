package com.agmednet.generators;

import com.agmednet.model.EventData;
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
  @Parameter(names = "-c", description = "Event count")
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
    List<EventData> events = generateEvents(count);
    if (format.equals("csv")) {
      saveasCSV(events, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(events, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(events, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private static void saveasCSV(List<EventData> events, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (EventData event : events) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s\n", event.getTrial(), event.getSite(),
                event.getSubject()));
      }
    }
  }

  private void saveAsJson(List<EventData> accounts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(accounts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<EventData> accounts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(EventData.class);
    String xml = xStream.toXML(accounts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private static List<EventData> generateEvents(int count) {
    List<EventData> accounts = new ArrayList<EventData>();
    for (int i = 0; i < count; i++) {
      accounts.add(new EventData().withTrial(String.format("FirsnameTest%s", i)).withSite(String.format("LastnameTest%s", i))
              .withSubject(String.format("City%s", i)));
    }
    return accounts;
  }
}
