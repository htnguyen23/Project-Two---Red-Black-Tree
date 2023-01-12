// --== CS400 File Header Information ==--
// Name: Casey Waddell
// Email: ctwaddell@wisc.edu
// Team: KB Blue
// Role: Frontend Developer
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: N/a

import java.util.Scanner;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.ArrayList;
import java.util.List;

public class Frontend
{
  private Scanner scanner = new Scanner(System.in);
  private Backend backend;
  private String searchRange = "n";
  private String toBreak = "";
  private String answer = "";
  private boolean loop = true;
  private Character[] roster;
  private int rosterPower = 1000;
  private final int MAXROSTERPOWER = 1000;
  private ArrayList<CharacterInterface> tempArray;
  private Character[] characterArray;
  private String lower;
  private String upper;
  private boolean firstTimeBaseMode = true;
  private int threeCharacterKey = 0;
  
  public Frontend(Backend backend)
  {
    this.backend = backend;
    roster = new Character[100];
  }
  
  public static void main(String[] args)
  {
    try
    {   
      Backend backendTwo = new Backend("characters_stats_merged.csv");
      Frontend driver = new Frontend(backendTwo);
      driver.run();
    }
    catch(IOException e)
    {
      System.out.println("The file type is not supported.");
    }
    catch(DataFormatException e)
    {
      System.out.println("The data format is not supported.");
    }
  }
  
  /**
   * run just runs the program after displaying the starting message. Basically used by main
   * to set the whole thing in motion
   */
  public void run()
  {
    System.out.println("Welcome to the S.A.D. Database. Type 'help' at any time to display commands.");
    displayCommands("base");
    baseMode();
  }
  
  /**
   * baseMode is based around some while loops that essentially iterate through each team a new input
   * is put in by the user. Once it finds the criteria or key inputted of relevance, it executes the commands
   * associated with that input, then uses the continue keyword to await another command in the while loop.
   * Unfortunately with the way I coded this, there is a little spill over with the data ranges from
   * scoreMode() makes the first half of base mode deal mostly with data ranges and such, but the main
   * and essential function of basemode is to direct the program which mode to enter.
   */
  public void baseMode()
  {
    String toDo = "B";
    while(loop == true)
    {
      if(!searchRange.equals("n"))
      {        
        System.out.println("The current search range being displayed is " + searchRange);
        String[] bounds = searchRange.split("-");
        lower = bounds[0];
        upper = bounds[1]; //this works
        backend.filterCharacters(lower, upper); //void method in backend
        
        ArrayList<CharacterInterface> a = (ArrayList<CharacterInterface>)backend.getPowerByScore();
        
        if(backend.getThreeCharacters(threeCharacterKey) == null)
        {
          System.out.println("No characters matched your power range. Try refining the range through Score Mode again.\nReturning to Base Mode...");
          searchRange = "n";
          continue;
        } //this works 
        
        characterArray = new Character[backend.getPowerByScore().size()];
        for(int i = 0; i < backend.getPowerByScore().size(); i++)
        {
          characterArray[i] = (Character)backend.getPowerByScore().get(i);
        } 
        
        List<CharacterInterface> tempThreeCharacterList = backend.getThreeCharacters(threeCharacterKey);
        Character[] tempThree = new Character[3];
        for(int j = 0; j < 3; j++)
        {
          tempThree[j] = (Character)tempThreeCharacterList.get(j);
        }
        tempThreeCharacterList.clear();
        System.out.println(arrayToString(tempThree)); //change to character strings
        System.out.println("\nIf you would like to add any of the above characters follow these commands:");
        displayCommands("adding");
        String userInput = scanner.nextLine();
        if(userInput.equalsIgnoreCase("X"))
        {
          System.out.println("Returning to Base Mode...");
          userInput = "?";
          searchRange = "n";
          threeCharacterKey = 0;
          continue;
          //do nothing
        }
        if(userInput.equalsIgnoreCase("N"))
        {
          if(threeCharacterKey + 3 >= characterArray.length)
          {
            System.out.println("Cannot scroll forwards any further.");
            continue;
          }
          threeCharacterKey++;
          tempThreeCharacterList.clear();
          continue;
          //use backend interface or getThree here
        }
        if(userInput.equalsIgnoreCase("P"))
        {
          if(threeCharacterKey - 1 < 0)
        {
          System.out.println("Cannot scroll back any further.");
          continue;
        }
          threeCharacterKey--;
          tempThreeCharacterList.clear();
          continue;
        }
        if(userInput.equalsIgnoreCase("help") || userInput.equalsIgnoreCase("ls"))
        {
          displayCommands("adding");
          continue;
        }
        if(userInput.equalsIgnoreCase("A"))
        {
          while(loop == true)
          {
            System.out.println("Adding character to roster. Enter index [0/1/2] to choose. Alternatively, press [X] to cancel.");
            userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("x"))
            {
              System.out.println("Done adding characters...\n");
              break;
            }
            try
            {
              int index = Integer.parseInt(userInput);
              if(index < 0 || index > 2)
              {
                System.out.println("You must enter a number [0/1/2] or quit by typing X.");
                continue;
              }
              addToRoster((Character)tempThree[index]);
              System.out.println("You may keep adding until you indicate you are done by typing [X].");
            }
            catch(NumberFormatException e)
            {
              System.out.println("Input was not an integer. Please try again.");
            }
          }
          continue;
        }
        else
        {
          System.out.println("Sorry, your input could not be recognized. Please try again.");
          continue;
        }
      }
      //DISPLAY/OUTPUT SETTINGS
      /*if(currentSearchMode.equals("name"))
      {
        System.out.println("----------------------------------------\nCurrently displaying " + currentSearchMode + " mode options\n----------------------------------------");
      }
      if(currentSearchMode.equals("score"))
      {
        System.out.println("----------------------------------------\nCurrently displaying " + currentSearchMode + " mode options\n----------------------------------------");
      }
      if(currentSearchMode.equals("fight"))
      {
        System.out.println("----------------------------------------\nCurrently displaying " + currentSearchMode + " mode options\n----------------------------------------");
      } */ 
      
      //INPUT SETTINGS
      if(firstTimeBaseMode == false)
      {
        System.out.println("You are currently in Base Mode. Type 'help' to display commands");
      }
      toDo = scanner.nextLine();
      firstTimeBaseMode = false;
      if(toDo.equalsIgnoreCase("help") || toDo.equalsIgnoreCase("ls"))
      {
        displayCommands("base");
        continue;
      }
      if(toDo.equalsIgnoreCase("N"))
      {
        System.out.println("Now entering Name Mode...");
        nameMode();
        continue;
      }
      if(toDo.equalsIgnoreCase("S"))
      {
        System.out.println("Now entering Score Mode...");
        searchRange = scoreMode();
        continue;
      }
      /*if(toDo.equalsIgnoreCase("F"))
      {
        System.out.println("Now entering fight mode...");
        battleMode();
        continue;
      }*/
      if(toDo.equalsIgnoreCase("R"))
      {
        System.out.println(getRoster());
        continue;
      }
      if(toDo.equalsIgnoreCase("E"))
      {
        System.out.println("Now entering Roster Editing Mode");
        rosterMode();
        continue;
      }
      if(toDo.equalsIgnoreCase("X"))
      {
        System.out.println("Goodbye!");
        System.exit(0);
      }
        System.out.println("Sorry, we did not understand your input. Try again");
    }
  }
  
  /**
   * nameMode is similar to baseMode in its looping functionality, except this one has users input
   * criteria of a name, searches through the given backend database, and then prompts users to add
   * a character by its given index. All of this is handled with text prompts informing the user,
   * so it is pretty user friendly. Typing help during any of these modes offers at least some indication
   * of what the user should do if they are stuck.
   */
  public void nameMode()
  {
    //duplicate, returning arrayList?
    while(loop == true)
    {
      System.out.println("\nYou are currently in Name Mode. Type 'help' for commands or a name to search for.");
      toBreak = scanner.nextLine();
      if(toBreak.equalsIgnoreCase("help") || toBreak.equalsIgnoreCase("ls"))
      {
        displayCommands("name");
        
        continue;
      }
      if(toBreak.equalsIgnoreCase("X"))
      {
        System.out.println("Returning to Base Mode...");
        return;
      }
      
      else
      {
        System.out.println("Searching the data base for: " + toBreak);
        ArrayList<CharacterInterface> resultTemp = (ArrayList<CharacterInterface>) backend.getCharacterByName(toBreak);
        Character[] result = new Character[resultTemp.size()];
        if(resultTemp.size() == 0)
        {
          System.out.println("No characters matched the name: " + toBreak);
          continue;
        }
        for(int i = 0; i < resultTemp.size(); i++) 
        {
          result[i] = (Character)resultTemp.get(i);
        }
        System.out.println(result.length + " results were found:"); //print index before each character
        System.out.println(arrayToString(result)); //formatting issue here? !!!
        while(loop == true)
        {
          System.out.println("Which would you like to add? Type [index] to add or [X] to cancel");
          String which = scanner.nextLine();
          if(which.equalsIgnoreCase("x"))
          {
            break;
          }
          
          int whichInt = -1;
          try
          {
            whichInt = Integer.parseInt(which);
            System.out.println(result.length);
            if(whichInt < 0 || whichInt >= result.length)
            {     
              System.out.println("Input was less than zero or greater than the list's size. Please try again.");
              continue;
            }
            else
            {
              addToRoster(result[whichInt]);
              resultTemp.clear();
              break;
            }
          }
          catch(NumberFormatException e)
          {
            System.out.println("Input was not an integer. Please try again.");
            continue;
          }
        }
      }
    }
    return;
  }
  
  /**
   * scoreMode also uses while loops like the above methods, except scoreMode asks the user for a range
   * of integers. It checks for obvious problematic inputs like negative numbers, strings, etc.. Once
   * the user inputs a valid range, it returns the range as a String of two numbers separated by a dash.
   * Once base mode receives this String, it breaks it down and displays the range during basemode. If
   * I implemented this cleaner I could've avoided the spillover, but I think it is fascinating that it
   * works in the way it does. I am proud of this method.
   * @return String representing the data range separated by a -
   */
  public String scoreMode()
  {
    String range = "";
    //int lower = 0;
    //int upper = 0;
    while(loop == true)
    {
      System.out.println("You are currently in score mode. Type a range of powers to search for.");
      range = searchQuery();   
      while(loop == true)
      {
        System.out.println("The range being searched is " + range + "\nIf you are happy with this range, press [X]. Otherwise, press [R] to refine the range.");
        toBreak = scanner.nextLine();
        if(toBreak.equalsIgnoreCase("r"))
        {
          break;
        }
        if(toBreak.equalsIgnoreCase("x"))
        {
          System.out.println("The range " + range + " has been confirmed.");
          return range;
        }
        else
        {
          System.out.println("The command was not recognized. Please try again.");
        }
      }
    }
    /*String bounds[] = range.split("-");
    lower = Integer.parseInt(bounds[0]);
    upper = Integer.parseInt(bounds[1]);*/
    return "ERROR";
  }
  
  /**
   * rosterMode is merely a driver to display roster related commands. It should be noted with most of
   * these mode() methods, the actual methods that manipulate the data are further below. These mode()
   * methods mainly deal with displaying the data into the terminal and stuff.
   */
  public void rosterMode()
  {
    while(loop == true)
    {
      System.out.println("You are currently in Roster Editing Mode. Type 'help' for commands");
      String rosterRead = scanner.nextLine();
      if(rosterRead.equalsIgnoreCase("help") || rosterRead.equalsIgnoreCase("ls"))
      {
        displayCommands("roster");
      }
      if(rosterRead.equalsIgnoreCase("d"))
      {
        System.out.println(getRoster());
      }
      if(rosterRead.equalsIgnoreCase("r"))
      {
        while(loop == true)
        {
          System.out.println(getRoster());
          System.out.println("Removing from roster. Type [index] of character to remove");
          String toRemove = scanner.nextLine();
          int toRemoveInt;
          try
          {
            toRemoveInt = Integer.parseInt(toRemove);
            if(toRemoveInt < 0 || toRemoveInt > roster.length)
            {
              
              System.out.println("Input was less than zero or greater than the roster's size. Please try again.");
              continue;
            }
          }
          catch(NumberFormatException e)
          {
            System.out.println("Input was not an integer. Please try again.");
            continue;
          }
          removeFromRoster(toRemoveInt);
          break;
        }
      }
      if(rosterRead.equalsIgnoreCase("x"))
      {
        System.out.println("Returning to Base Mode...");
        return;
      }
    }
  }
  
  /**
   * searchQuery is the method that asks the user for power level ranges. It checks for null,
   * negative inputs, string inputs, inputs where the lower bound would be higher than the upper, etc.
   * @return String representing the data range separated by a dash. This is also the string returned
   * by the scoreMode method.
   */
  private String searchQuery()
  {
    boolean hasLower = false;
    boolean hasUpper = false;
    String immediateAdd = "";
    int checker = 0;
    int checker2 = 0;
    String r = "";
    while(hasLower == false)
    {
      System.out.println("Enter a lower bound:");
      immediateAdd = scanner.nextLine();
      try
      {
        checker = Integer.parseInt(immediateAdd);
        if(checker < 0)
        {
          System.out.println("Input was less than 0. Please try again.");
          continue;
        }
        r += immediateAdd;
        hasLower = true;
      }
      catch(NumberFormatException e)
      {
        System.out.println("Input was not an integer. Please try again.");
      }
    }
    r += "-";
    while(hasUpper == false)
    {
      System.out.println("Enter an upper bound:");
      immediateAdd = scanner.nextLine();
      try
      {
        checker2 = Integer.parseInt(immediateAdd);
        if(checker2 < 0 || checker2 < checker)
        {
          System.out.println("Input was less than 0 or less than lower bound. Please try again.");
          continue;
        }
        r += immediateAdd;
        hasUpper = true;
      }
      catch(NumberFormatException e)
      {
        System.out.println("Input was not an integer. Please try again.");
      }
    }
    return r;
  }
  
  /**
   * getRoster is a glorified print method that just formats the roster of the user in a friendly
   * and intuitive way to relevance of the program. Therefore, it includes indexes, power level
   * utilization, etc. 
   * @return String, representing the roster formatted in a good way
   */
  private String getRoster()
  {
    if(roster[0] == null)
    {
      return "The roster is currently empty. (Power available: " + rosterPower + "/" + MAXROSTERPOWER;
    }
    String r = "Power available: " + rosterPower + "/" + MAXROSTERPOWER + "\n0 - [";
    for(int i = 0; i < roster.length; i++)
    {
      if(roster[i] != null)
      {
        if(i == 0)
        {
          r += characterFormatter(roster[0]);
          continue;
        }
        else
        {
          
        }
        r += ";\n"+ i + " -  " + characterFormatter(roster[i]);
      }
    }
    r += "]";
    return r;
  }
  
  /**
   * addToRoster adds the given parameter character and tries to insert it into the roster private field.
   * it inserts it at the earliest null index. This combined with the remove implementation makes sure
   * there is always a null reference at the end and only at the end of the roster.
   * @param c, character to be added.
   * @return boolean true if adding was successful and false if it fails.
   */
  public boolean addToRoster(Character c)
  {
    boolean r = false;
    if(rosterPower - c.getTotalPower() < 0)
    {
      System.out.println("The character cannot be added to the roster, as the maximum roster power would be overwhelmed.");
      return r;
    }
    if(roster[0] == null)
    {
      roster[0] = c;
      System.out.println(c.getName() + " has been added to your roster.");
      rosterPower = rosterPower - c.getTotalPower();
      return true;
    }
    for(int i = 0; i < roster.length; i++)
    {

      if(roster[i] == null)
      {
        roster[i] = c;
        rosterPower = rosterPower - c.getTotalPower();
        System.out.println(c.getName() + " has been added to your roster.");
        return true;
      }  
      if(roster[i].getName().equalsIgnoreCase(c.getName())) //this part is if we allow duplicates or not
      {
        System.out.println("Duplicate heros cannot be added.");
        return false;
      }
    }
    if(r == false)
    {
      System.out.println(c.getName() + " could not be added. The roster is likely full.");
      return false;
    }
    return false;
  }
  
  /**
   * removeFromRoster removes a character object from a given index. It then reformats the roster
   * array by smoothing out the null point to the end of the roster. There should therefore only be
   * null at the end of the roster and never in between it.
   * @param a, index of character object user wishes to remove.
   * @return boolean, true if remove succeeds and false if it fails.
   */
  public boolean removeFromRoster(int a)
  {
    if(roster[a] == null)
    {
      System.out.println("Cannot remove from index " + a + " as it is greater than the roster's length.\nPlease try again by calling [R] followed by a proper index.");
      return false;
    }
    System.out.println("Removing from index " + a);
    Character[] duplicateArray = roster;
    rosterPower = rosterPower + roster[a].getTotalPower();
    System.out.println(roster[a].getName() + " has been removed."); //adjust power correctly
    roster[a] = null;
    for(int i = a; i < duplicateArray.length; i++)
    {
      if(duplicateArray[i + 1] == null)
      {
        duplicateArray[i] = null; //handles the end of the list just fine...
        break;
      }
      duplicateArray[i] = duplicateArray[i+1]; //some error here, remove element 3 and it duplicates something
    }
    roster = duplicateArray;

    return true;
  }
  
  /**
   * This is very similar to getRoster() but it doesn't include the powerlevel statistics. It's
   * a more general print method for this program, as some arrays dont require the powerlevel information
   * that the roster does
   * @param a, character array to perform the print on.
   * @return String representing the array formatted all nicely.
   */
  private String arrayToString(Character[] a)
  {
    String r = "0 - [";
    for(int i = 0; i < a.length; i++)
    {
      if(i == 0)
      {
        r += characterFormatter(a[i]);
        continue;
      }
      else
      {
        r += ";\n"+ i + " - " + characterFormatter(a[i]);
      }
    }
    r += "]";
    return r;
  }
  
  /**
   * I don't really know if I ever use this one but at this point im too stubborn to delete it.
   * It could have some fringe uses if I ever considered using arraylists for this project.
   * @param a ArrayList of characters to perform print on
   * @return String representing the array formatted correctly.
   */
  private String arrayToString(ArrayList<CharacterInterface> a)
  {
    String r = "[";
    for(int i = 0; i < a.size(); i++)
    {
      if(i == 0)
      {
        r += characterFormatter((Character)a.get(i));
        continue;
      }
      else
      {
        r += ";\n " + characterFormatter((Character)a.get(i));
      }
    }
    r += "]";
    return r;
  }
  
  /**
   * characterFormatter is another useful printer method that displays relevant characteristics of
   * a character object for use in arrays and such. More statistics or less could've been added,
   * but my team and I agreed these were the most important traits to be included in this print method.
   * @param c, character to be formatted into a string
   * @return String representing the character's traits all formatted.
   */
  private String characterFormatter(Character c)
  {
    String r = "Name: ";
    r += c.getName();
    r += ", Total Power Level: ";
    r += c.getTotalPower();
    r += ", Superpowers: ";
    r += c.getSuperPowers();
//  r += ", Allignment: ";
//  r += c.getAllignment();
    return r;
  }

  /**
   * displayCommands is a convenience method. Basically if the user ever types help during a method
   * I call this method with the import parameter depending on what mode they were in when they
   * types help.
   * @param a, this parameter is either base, string, score, etc. depending on where the user typed
   * help. Once the string is known, it routes the print to the correct directory thingie.
   */
  private void displayCommands(String a)
  {
    if(a.equalsIgnoreCase("base"))    
    {
      System.out.println("[N] enters Name Mode");
      System.out.println("[S] enters Score Mode");
      System.out.println("[R] displays the current roster");
      System.out.println("[E] enters Roster Edit Mode");
      System.out.println("[X] at any time exits the mode/program");
      return;
    }
    if(a.equalsIgnoreCase("name"))
    {
      System.out.println("Type a name into the terminal to search for that character");
      System.out.println("[index] adds a character once it's found");
      System.out.println("[X] at any time exits the mode");
      return;
    }
    if(a.equalsIgnoreCase("score"))
    {
      System.out.println("Type a lower range followed by a higher range to search for that range");
      System.out.println("[R] allows to refine the power range");
      System.out.println("[X] at any time exits the search / confirms the range");
    }
    if(a.equalsIgnoreCase("adding"))
    {
      System.out.println("[A] indicates you would like add a hero");
      System.out.println("[0/1/2] after pressing [A] indicates which hero to add");
      System.out.println("[N] displays the next three characters within the power range");
      System.out.println("[P] displays the previous three characters within the power range");
      System.out.println("[X] cancels the adding process and returns to Base Mode");
    }
    if(a.equalsIgnoreCase("roster"))
    {
      System.out.println("In Roster Editing Mode, you may remove characters. If you wish to add, please add through either Name or Score Mode.");
      System.out.println("[D] displays the full roster");
      System.out.println("[R] indicates you would like to remove a character");
      System.out.println("[index] after [R] removes the character from the given index of your roster");
      System.out.println("[X] at any time exits Roster Editing Mode");
    }
  }
}
