// --== CS400 File Header Information ==--
// Name: Casey Waddell
// Email: ctwaddell@wisc.edu
// Team: KB Blue
// Role: Frontend Developer
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: N/a

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

class FrontEndDeveloperTests 
{
      /** 
       * testDisplayStartup will essentially test if the program launches as it should,
       *  as well as show the ASCII art and basic startup sequence of said program.
       *  The main emphasis of this test is to make sure the program gets off the ground.
       *  Specifically this will test: 
       *  1. Do the startup objects work? Do the constructors work?
       *  2. What happens if the startup objects are given weird formats? Are the exceptions caught?
       *  3. Startup sequence when run() is called
       **/
      @Test
      public void testDisplayStartup()
      {
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        try
        {
          Backend testBackend = new Backend("characters_stats_merged.doc");
        }
        catch(DataFormatException e)
        {
          //this is the route that should happen
          try
          {
            //if this instantiates correctly, the test therefore passes
            PrintStream originOutput = System.out;
            Backend testBackend = new Backend("characters_stats_merged.csv");
            Frontend testFrontend = new Frontend(testBackend); 
            System.setOut(new PrintStream(b1));
            testFrontend.run();
            System.out.flush();
            System.setOut(originOutput);
          }
          catch(DataFormatException f){ }
          catch(IOException f){ }
        }
        catch(IOException e){ }
        if(b1 == null)
        {
          fail("The program did not run!");
        }
      }

      /**
       * testSearchModeToggle will take place after the startup chronologically, so it will
       * logically be the next test. This test will mainly focus on the toggling between
       * different search modes and the main menu
       * Specifically this will test:
       * 1. Pressing one certain key to change to one type of search mode 
       * 2. Read to make sure the output display is what is to be expected (in our case, name)
       */
      @Test
      public void testSearchModeToggle()
      {
        PrintStream originOutput = System.out;
        InputStream originInput = System.in;
        String input = "n";
        InputStream newInputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream newOutputBAOS = new ByteArrayOutputStream();
        PrintStream newOutputStream = new PrintStream(newOutputBAOS);
        System.setOut(newOutputStream);
        System.setIn(newInputStream);
        try
        { 
          Backend testBackend = new Backend("characters_stats_merged.csv");
          Frontend testFrontend = new Frontend(testBackend); 
          try
          {
            testFrontend.baseMode();
          }
          catch(NoSuchElementException e)
          {

          }
          System.setIn(originInput);
          System.setOut(originOutput);
          if(!newOutputBAOS.toString().contains("Name"))
          {
            fail("The name mode was not properly engaged.");
          }
        }
        catch(DataFormatException f){ }
        catch(IOException f){ }
      }

      /**
       * testExitMode() will test to see if I can properly exit a mode by typing in 'x'
       * Specifically, this will test:
       * 1. if inputting x returns the user to base mode from name mode
       * 2. this should apply to all other methods because practically identical code is
       *    used each time i exit a method.
       */
      @Test
      public void testExitMode()
      {
        PrintStream originOutput = System.out;
        InputStream originInput = System.in;
        String input = "x";
        InputStream newInputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream newOutputBAOS = new ByteArrayOutputStream();
        PrintStream newOutputStream = new PrintStream(newOutputBAOS);
        System.setOut(newOutputStream);
        System.setIn(newInputStream);
        try
        { 
          Backend testBackend = new Backend("characters_stats_merged.csv");
          Frontend testFrontend = new Frontend(testBackend); 
          testFrontend.nameMode();;
          System.setIn(originInput);
          System.setOut(originOutput);
          if(!newOutputBAOS.toString().contains("Base"))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("Exiting Name mode didn't work as planned.");
          }
        }
        catch(DataFormatException f){ }
        catch(IOException f){ }
      }
      
      /**
       * testAddCritiaPartOne will take place using the main engine/code of a search mode (I imagine
       * no matter how many search modes we implement, the framework will be the same, therefore
       * one test method for all the various kinds won't be problematic as long as the fundamentals
       * of the methods are not changed between modes).
       * Specifically this will test:
       * ->1. Adding criteria to the search query array or whatever it will be
       * 2. Adding improper criteria to the search query and making sure it is caught
       * 3. Adding different types of data to the different queries
       */
      @Test
      public void testAddCriteriaPartOne()
      {
        PrintStream originOutput = System.out;
        InputStream originInput = System.in;
        String input = "null";
        InputStream newInputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream newOutputBAOS = new ByteArrayOutputStream();
        PrintStream newOutputStream = new PrintStream(newOutputBAOS);
        System.setOut(newOutputStream);
        System.setIn(newInputStream);
        try
        { 
          Backend testBackend = new Backend("characters_stats_merged.csv");
          Frontend testFrontend = new Frontend(testBackend); 
          try
          {
            testFrontend.nameMode();
          }
          catch(NoSuchElementException e)
          {

          }
          System.setIn(originInput);
          System.setOut(originOutput);
          if(!newOutputBAOS.toString().contains("No characters matched the name:"))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("Adding search parameter did not work!");
          }
        }
        catch(DataFormatException f){ }
        catch(IOException f){ }
      }

      /**
       * testAddCritia will take place using the main engine/code of a search mode (I imagine
       * no matter how many search modes we implement, the framework will be the same, therefore
       * one test method for all the various kinds won't be problematic as long as the fundamentals
       * of the methods are not changed between modes).
       * Specifically this will test:
       * 1. Adding criteria to the search query array or whatever it will be
       * ->2. Adding improper criteria to the search query and making sure it is caught
       * 3. Adding different types of data to the different queries
       */
      @Test
      public void testAddCriteriaPartTwo()
      {
        PrintStream originOutput = System.out;
        InputStream originInput = System.in;
        String input = "123";
        InputStream newInputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream newOutputBAOS = new ByteArrayOutputStream();
        PrintStream newOutputStream = new PrintStream(newOutputBAOS);
        System.setOut(newOutputStream);
        System.setIn(newInputStream);
        try
        { 
          Backend testBackend = new Backend("characters_stats_merged.csv");
          Frontend testFrontend = new Frontend(testBackend); 
          try
          {
            testFrontend.nameMode();
          }
          catch(NoSuchElementException e)
          {

          }
          System.setIn(originInput);
          System.setOut(originOutput);
          if(!newOutputBAOS.toString().contains("No characters"))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("Adding weird parameter did not work!");
          }
        }
        catch(DataFormatException f){ }
        catch(IOException f){ }
      }
      
      /**
       * testAddCritia will take place using the main engine/code of a search mode (I imagine
       * no matter how many search modes we implement, the framework will be the same, therefore
       * one test method for all the various kinds won't be problematic as long as the fundamentals
       * of the methods are not changed between modes).
       * Specifically this will test:
       * 1. Adding criteria to the search query array or whatever it will be
       * 2. Adding improper criteria to the search query and making sure it is caught
       * ->3. Adding different types of data to the different queries
       */
      @Test
      public void testAddCriteriaPartThree()
      {
        PrintStream originOutput = System.out;
        InputStream originInput = System.in;
        String input = "thor";
        InputStream newInputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream newOutputBAOS = new ByteArrayOutputStream();
        PrintStream newOutputStream = new PrintStream(newOutputBAOS);
        System.setOut(newOutputStream);
        System.setIn(newInputStream);
        try
        { 
          Backend testBackend = new Backend("characters_stats_merged.csv");
          Frontend testFrontend = new Frontend(testBackend); 
          try
          {
            testFrontend.scoreMode();
          }
          catch(NoSuchElementException e)
          {

          }
          System.setIn(originInput);
          System.setOut(originOutput);
          if(!newOutputBAOS.toString().contains("Input was not an integer."))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("Adding weird parameter did not work!");
          }
        }
        catch(DataFormatException f){ }
        catch(IOException f){ }
      }
      
      /**
       * testRemoveCriteria will basically be the same as testAddCriteria but with removing
       * Specifically this will test:
       * 1. Removing criteria from an actual list
       * 2. Removing criteria that is out of bounds
       * 3. Removing from an empty list
       */
      @Test
      public void testRemoveCriteria()
      {
        PrintStream originOutput = System.out;
        InputStream originInput = System.in;
        String input1 = "n\nthor\n0\nman\n0\nhelp\nx\ne\nr\n0\nr\n99\nr\n0\nr\n2";
        InputStream newInputStream1 = new ByteArrayInputStream(input1.getBytes());
        ByteArrayOutputStream newOutputBAOS = new ByteArrayOutputStream();
        PrintStream newOutputStream = new PrintStream(newOutputBAOS);
        System.setOut(newOutputStream);
        System.setIn(newInputStream1);
        
        try
        { 
          Backend testBackend = new Backend("characters_stats_merged.csv");
          Frontend testFrontend = new Frontend(testBackend); 
          try
          {
            testFrontend.run();
          }
          catch(NoSuchElementException e)
          {

          }
          System.setIn(originInput);
          System.setOut(originOutput);
          if(!newOutputBAOS.toString().contains("Lex Luthor has been removed."))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("Removing did not work.");
          }
          if(!newOutputBAOS.toString().contains("Cannot remove from index 99 as it is greater than the roster's length."))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("Removing an out of bounds element.");
          }
          if(!newOutputBAOS.toString().contains("Cannot remove from index 2 as it is greater than the roster's length."))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("Removing from an empty array didn't work.");
          }
          if(!newOutputBAOS.toString().contains("Cannot remove from index 2 as it is greater than the roster's length."))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("Removing from an empty array didn't work.");
          }
          if(!newOutputBAOS.toString().contains("Cannot remove from index 2 as it is greater than the roster's length."))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("Removing from an empty array didn't work.");
          }
        }
        catch(DataFormatException f){ }
        catch(IOException f){ }
      }
      
      /**
       * testScoreModeBounds will take a search array or query already determined and see if it is
       * able to retrieve the data we need from the backend. This is more reliant on the interaction
       * between frontend and backend and less something I can plan for
       * Specifically this will test:
       * 1. Asking invalid bounds
       * 2. Asking lower higher bounds
       * 3. Asking string when it should be an integer
       */
      @Test
      public void testScoreModeBounds()
      {
        PrintStream originOutput = System.out;
        InputStream originInput = System.in;
        String input1 = "\ns\n-1\n1\nhello";
        InputStream newInputStream1 = new ByteArrayInputStream(input1.getBytes());
        ByteArrayOutputStream newOutputBAOS = new ByteArrayOutputStream();
        PrintStream newOutputStream = new PrintStream(newOutputBAOS);
        System.setOut(newOutputStream);
        System.setIn(newInputStream1);
        
        try
        { 
          Backend testBackend = new Backend("characters_stats_merged.csv");
          Frontend testFrontend = new Frontend(testBackend); 
          try
          {
            testFrontend.run();
          }
          catch(NoSuchElementException e)
          {

          }
          System.setIn(originInput);
          System.setOut(originOutput);
          if(!newOutputBAOS.toString().contains("Input was less than 0."))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("The program couldn't handle an input lower than 0");
          }
          if(!newOutputBAOS.toString().contains("Input was not an integer."))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("The program couldn't handle a string input for the bounds.");
          }
        }
        catch(DataFormatException f){ }
        catch(IOException f){ }
      }

      /**
       * testDisplayDataRetrieval will be the concluding test to see if the data is returned in a 
       * legible, informative, and logical ordering pertaining to what the user had inputed
       * Specifically, this will test:
       * 1. The ordering of the data from data retrieval
       * 2. The formatting of the data (does it look as it should organized and such)
       * 3. The possible use of the scrolling function and whatnot
       */
      @Test
      public void testDisplayDataRetrieval()
      {
        PrintStream originOutput = System.out;
        InputStream originInput = System.in;
        String input1 = "\ns\n0\n100\nx\nn\np";
        InputStream newInputStream1 = new ByteArrayInputStream(input1.getBytes());
        ByteArrayOutputStream newOutputBAOS = new ByteArrayOutputStream();
        PrintStream newOutputStream = new PrintStream(newOutputBAOS);
        System.setOut(newOutputStream);
        System.setIn(newInputStream1);        
        try
        { 
          Backend testBackend = new Backend("characters_stats_merged.csv");
          Frontend testFrontend = new Frontend(testBackend); 
          try
          {
            testFrontend.run();
          }
          catch(NoSuchElementException e)
          {

          }
          System.setIn(originInput);
          System.setOut(originOutput);
          if(!newOutputBAOS.toString().contains("0 - [Name: Wyatt Wingfoot, Total Power Level: 90, Superpowers: []"))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("Ordering was not correct");
          }
          if(!newOutputBAOS.toString().contains("0 - [Name: Kool-Aid Man, Total Power Level: 85, Superpowers: [Durability, Thirstokinesis]"))
          {
            //if this doesn't display, we can tell the return call didn't work.
            fail("The formatting of the data or the scrolling didn't work as planned.");
          }
        }
        catch(DataFormatException f){ }
        catch(IOException f){ }
      }
}
