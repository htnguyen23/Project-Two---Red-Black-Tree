// --== CS400 File Header Information ==--
// Name: Xiaohan Zhu
// Email: xzhu274@wisc.edu
// Team: KB blue
// Role: Backend Developer
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.zip.DataFormatException;


public class Backend implements BackendInterface {
	private RedBlackTree<CharacterInterface> characterRBT; //one red black tree storing all characters

	
	 private static CharacterDataReader reader;
	 private static FileReader fr;
	 private List<Character> characterList; // list of all Character objects read from the file
	 private List<CharacterInterface> characterByPower; // list of filtered Character objects by power
	 private List<CharacterInterface> characterByName; // list of Character objects for getCharacterByName()

	
	 /**
	  * constructor for passing in a Reader with the file containing data directly 
	  * @param filename is the string containing data directly
	  * @throws IOException when there is I/O problems with input data file 
      * @throws DataFormatException when the number of columns do not match.
	  */
	 public Backend(String fileName) throws IOException, DataFormatException {
		    fr = new FileReader(fileName);
		    // read in data using CharacterDataReader class
		    reader = new CharacterDataReader();
		    characterList = reader.readData(fr);
		    characterRBT = new RedBlackTree<CharacterInterface>();
		    characterByName = new ArrayList<CharacterInterface>();
		    characterByPower = new ArrayList<CharacterInterface>();
	    
		    
		  //insert all the Character objects into the red black tree
		    for (int i = 0; i < characterList.size(); i++) 
		    {
		      try
		      {
		    	characterRBT.insert(characterList.get(i));
		      }
		      catch(IllegalArgumentException e)
		      {
		        
		      }
		    }

		  
		  }
	 
	 
	 /**
	  * constructor for arguments formatted in string 
	  * @param strReader
	  * @throws IOException when there is I/O problems with input data file 
	  * @throws DataFormatException when the number of columns do not match.
	  */
	  public Backend(StringReader strReader) throws IOException, DataFormatException {
		    // read in data using CharacterDataReader class
		    reader = new CharacterDataReader();
		    characterList = reader.readData(strReader);
		    characterRBT = new RedBlackTree<CharacterInterface>();
		    characterByName = new ArrayList<CharacterInterface>();
		    characterByPower = new ArrayList<CharacterInterface>();
		    
		    //insert all the Character objects into the red black tree
		    for (int i = 0; i < characterList.size(); i++) {
		    	characterRBT.insert(characterList.get(i));
		    }

			   

		    
		  }
		  
	/**
	 * Recursive helper method to search for Character objects in the red black tree by their name (getName()) with 
	 * name containing the input String, and store them in a list. It searches the tree in order: right -> root -> node 
	 * so that the characters in the list are sorted in descending total power level.
	 * @param characterByName is a list of character objects storing characters with names containing input 
	 * as a substring
	 * @param node is the node where search starts with
	 * @param input is the user input of the name to search
	 */
    private void characterByNameHelper(List<CharacterInterface> characterByName, RedBlackTree.Node<CharacterInterface> node, String input) {
    	if (node == null) { // if the node is a leaf, stop searching
    		return;
    	}
    	
    	characterByNameHelper(characterByName, node.rightChild,input); // check the right child first
    	if (node.data.getName().contains(input)) {
    		characterByName.add(node.data);//check the node itself
    		
    	}
    	characterByNameHelper(characterByName, node.leftChild,input);// check the left child last
    }

    /**
     * method to get Character objects with names containing given input name as a substring by calling characterByNameHelper method
     * @param name is the user input of the name to search
     * @return list of character objects containing the name as a substring
     */
	@Override
	public List<CharacterInterface> getCharacterByName(String name) {
		//clear the characterByName list every time calling getCharacterByName
		characterByName = new ArrayList<CharacterInterface>();
		characterByNameHelper(characterByName, characterRBT.root, name);

		return characterByName;
	}


     /**
      * Recursive helper method to filter Character objects in the red black tree within the given total power range (minPower, maxPower) 
      * and store them in a list. It searches the tree in order: right -> root -> node so that the characters in the list are 
      * sorted in descending total power level.
      * @param characterByPower is a list of character objects storing characters that have a total power level within
      * the power range
      * @param node is the node where search starts with
      * @param minPower lower bound of the range
      * @param maxPower upper bound of the range
      */
	 private void filterHelper(List<CharacterInterface> characterByPower, RedBlackTree.Node<CharacterInterface> node, int minPower, int maxPower) {
	    	if (node == null) {
	    		return;
	    	}
	    	
	    	filterHelper(characterByPower, node.rightChild, minPower, maxPower);// check the right child first
	    	if (node.data.getTotalPower().compareTo(minPower) >= 0 && node.data.getTotalPower().compareTo(maxPower) <= 0) {
	    		characterByPower.add(node.data); //if the total power of the character in within the range, add it to the list
	    	}
//	    	System.out.println(characterByPower.size());
	    	filterHelper(characterByPower, node.leftChild, minPower, maxPower);// check the left child last
//	    	System.out.println(characterRBT.size);
	    }
	 
	 /**
	  * method to get Character objects that have a total power level within 
	  * the power range by calling filterHelper method
	  * @param minPower lower bound of the range
      * @param maxPower upper bound of the range
	  */
	@Override
	public void filterCharacters(String minPower, String maxPower){

		    //clear the filtered list every time calling filterCharacters
            characterByPower = new ArrayList<CharacterInterface>();
			int minPower_int = Integer.parseInt(minPower);
			int maxPower_int = Integer.parseInt(maxPower);
			
			//check if the input is a valid range 
			if (minPower_int > maxPower_int) {
				throw new InputMismatchException("The input is not a valid range.");
			}
		
			filterHelper(characterByPower, characterRBT.root, minPower_int, maxPower_int);

//			System.out.println(characterByPower.size());
		
		
	}

	/**
	 * method to get three Character objects in the list of filtered total power level 
	 * starting from startingIndex
	 * @param startingIndex is the index of the first Character of three Character objects 
	 * @return list of three Character objects
	 */
	@Override
	public List<CharacterInterface> getThreeCharacters(int startingIndex) {
		if (startingIndex >= characterByPower.size()) {
			return null;			
		}else if (startingIndex + 3 > characterByPower.size()) {
			return characterByPower.subList(startingIndex, characterByPower.size()); // if there are fewer than three characters the 
			                                                             //list starting from the starting index, return all of them
		} else {
			return characterByPower.subList(startingIndex, startingIndex + 3);
		}
		
	}

	/**
	 * method to get the number of Character objects in the list of filtered total power level 
	 * @return size of the list of filtered total power level
	 */
	@Override
	public int getNumCharacters() {		
		return characterByPower.size();
	}

	/**
	 * method to return the filtered list of Character objects
	 * @return filtered list of Character objects
	 */
	@Override
	public List<CharacterInterface> getCharacterByPower() {
		return characterByPower;
	}

	
	
}
