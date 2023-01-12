// --== CS400 File Header Information ==--
// Name: Xiaohan Zhu
// Email: xzhu274@wisc.edu
// Team: KB blue
// Role: Backend Developer
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.List;

public interface BackendInterface {
    public List<CharacterInterface> getCharacterByName(String name);    
    public void filterCharacters(String minPower, String maxPower);
    public List<CharacterInterface> getThreeCharacters(int startingIndex);    
    public int getNumCharacters();
    public List<CharacterInterface> getCharacterByPower();
    /**
     * 
     * getCharacterByName()      This function searches for Character objects with names containing given input name as a substring 
     *                      by calling the recursive characterByNameHelper method. It returns the list of Character objects in 
     *                      descending power level.
     * 
     * filterCharacters()        This function searches the red black tree for Character objects that have a total power level 
     *                      within the input power range by calling the recursive filterHelper method.
     * 
     * getThreeCharacters()      This function gets and returns three Character objects in the filtered list starting from 
     *                      startingIndex. The Characters are returned in descending power level.
     *                      
     * getNumCharacters()        This function returns the number of Character objects in the filtered list.
     * 
     * getCharacterByPower()     This function returns the list of filtered Character objects by total power level.   
     * 
     * 
     */
}