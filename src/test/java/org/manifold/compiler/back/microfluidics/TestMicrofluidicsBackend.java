package org.manifold.compiler.back.microfluidics;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMicrofluidicsBackend {
  
  @BeforeClass
  public static void setUpClass() {
    LogManager.getRootLogger().setLevel(Level.ALL);
    PatternLayout layout = new PatternLayout(
        "%-5p [%t]: %m%n");
    LogManager.getRootLogger().addAppender(
        new ConsoleAppender(layout, ConsoleAppender.SYSTEM_ERR));
  }
  
  @Test
  public void testOptionProcessParameters_FromCLI() 
      throws ParseException, IOException {
    MicrofluidicsBackend backend = new MicrofluidicsBackend();
    String[] args = {
      "-bProcessMinimumNodeDistance", "0.0001",
      "-bProcessMinimumChannelLength", "0.0001",
      "-bProcessMaximumChipSizeX", "0.04",
      "-bProcessMaximumChipSizeY", "0.04",
      // both single and double dash are correct
      "--bProcessCriticalCrossingAngle", "0.0872664626"
    };
    backend.readArguments(args);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testOptionProcessParameters_FromCLI_MissingOptions() 
      throws ParseException, IOException {
    MicrofluidicsBackend backend = new MicrofluidicsBackend();
    String[] args = {
      "-bProcessMinimumNodeDistance", "0.0001",
      "-bProcessMinimumChannelLength", "0.0001",
      "-bProcessMaximumChipSizeX", "0.04",
      "-bProcessMaximumChipSizeY", "0.04",
      //"-bProcessCriticalCrossingAngle", "0.0872664626" // leave out
    };
    backend.readArguments(args);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testOptionProcessParameters_FromCLI_NotANumber() 
      throws ParseException, IOException {
    MicrofluidicsBackend backend = new MicrofluidicsBackend();
    String[] args = {
      "-bProcessMinimumNodeDistance", "foo",
      "-bProcessMinimumChannelLength", "bar",
      "-bProcessMaximumChipSizeX", "baz",
      "-bProcessMaximumChipSizeY", "doge",
      "-bProcessCriticalCrossingAngle", "wow"
    };
    backend.readArguments(args);
  }
  
}
