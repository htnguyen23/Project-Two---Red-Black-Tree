// --== CS400 File Header Information ==--
// Name: Xiaohan Zhu
// Email: xzhu274@wisc.edu
// Team: KB blue
// Role: Backend Developer
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.IOException;
import java.io.StringReader;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class BackEndDeveloperTests {
	
	protected BackendInterface backendToTest;

	/**
	 * createInstance() instantiates the back end with three heros                                                                                                                                   
     * before each test 
	 * @throws DataFormatException when the number of columns do not match
	 * @throws IOException when there is I/O problems with input data file 
     */                                                                                                                                                                                     
    @BeforeEach
    public void createInstance() throws IOException, DataFormatException {
    	backendToTest = new Backend(new StringReader(                                                                                                                              
                "Name,Alignment,Intelligence,Strength,Speed,Durability,Power,Combat,Total\n"                                        
                + "3-D Man,good,50,31,43,32,25,52,233\n"                                                                    
                + "A-Bomb,good,38,100,17,80,17,64,316\n"                                                                                                                             
                + "Abe Sapien,good,88,14,35,42,35,85,299\n"));
    }
    


    /**
	 * This test tests whether the getCharacterByName returns the correct character object 
	 * given the input name by comparing the character's name (getName()). It passes 
	 * when "3-D Man" is returned and fails in all other cases, including when an
	 * exception is thrown.
	 */
	@Test
    public void testGetCharacterByName() {
		                                                                                                                                    
		assertEquals(backendToTest.getCharacterByName("3-D Man").get(0).getName(),"3-D Man");
//        fail("This test has not been implemented.");

    }
	

	/**
	 * This test tests whether the filterCharacters filters the correct character objects 
	 * given the input totalPower range by checking the size of returning hero 
	 * objects (getThreeCharacters(0)). It passes when 2 is returned and fails in all 
	 * other cases, including when an exception is thrown.
	 */
	@Test
    public void testFilterCharacters() {
		backendToTest.filterCharacters("200","300");
    	assertEquals(backendToTest.getThreeCharacters(0).size(), 2);
//        fail("This test has not been implemented.");
    }
	
//	/**
//	 * This test tests whether the filterCharacters returns null if the input is not a 
//	 * valid range, i.e, minPower > maxPower. It passes when null is returned and 
//	 * fails otherwise.
//	 * @return true if the test passed, false if it failed
//	 */
//	@Test
//    public void testNotValidFilterRange() {
//		backendToTest.filterCharacters("300","200");
//		System.out.println(backendToTest);
//    	assertEquals(backendToTest.getThreeCharacters(0), null);
////        fail("This test has not been implemented.");
//    }
	
	/**
	 * This test tests whether the getThreeCharacters returns the filtered character objects
	 * in descending power level order by checking the power of the first character 
	 * object in the list (getTotalPower()). It passes when 299 is returned and 
	 * fails in all other cases, including when an exception is thrown.
	 */
	@Test
    public void testGetThreeCharactersDescendingPower() {
		backendToTest.filterCharacters("200","300");
    	assertEquals(backendToTest.getThreeCharacters(0).get(0).getTotalPower(), new Integer(299));
//        fail("This test has not been implemented.");
    }
	
	
	/**
	 * This test tests whether the getThreeCharacters returns null if the starting index is 
	 * larger than or equal to the size of the list. It passes when null is returned and 
	 * fails in all other cases, including when an exception is thrown.
	 */
	@Test
    public void testGetThreeCharactersIndexOutOfBound() {
		backendToTest.filterCharacters("200","300");
    	assertEquals(backendToTest.getThreeCharacters(2), null);
//        fail("This test has not been implemented.");
    }
	
	/**
	 * This test tests whether the getNumCharacters returns the correct number of Character
	 * objects in the filtered list. It passes when 2 is returned and fails in all other cases.
	 */
	@Test
    public void testGetNumCharacters() {
		backendToTest.filterCharacters("200","300");
    	assertEquals(backendToTest.getNumCharacters(), 2);
//        fail("This test has not been implemented.");
    }
	  
	/**
	 * This test tests whether the getCharacterByPower returns the correct filtered list of 
	 * Character objects by checking the size of the list. It passes when 2 is returned and
	 * fails in all other cases.
	 */
	@Test
    public void testGetCharacterByPower() {
		backendToTest.filterCharacters("200","300");
    	assertEquals(backendToTest.getCharacterByPower().size(), 2);
//        fail("This test has not been implemented.");
    }
}
