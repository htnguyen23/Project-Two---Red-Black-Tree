
public class BackendDummy implements BackEndInterfaceDummy
{
  public CharacterDummy searchName(String name)
  {
    String[] thorsPowers = {"lightning", "swag", "hulk hitter"};
    //code for the backend to find the string
    if(name.equalsIgnoreCase("thor"))
    {
      CharacterDummy dummy = new CharacterDummy("thor", 99, thorsPowers);
      return dummy;
    }
    else
    {
      CharacterDummy dummy2 = new CharacterDummy("shrek", 12);
      return dummy2;
    }
  }
  
  
  public CharacterDummy[] searchScore(int a, int b)
  {
    CharacterDummy[] returnList = new CharacterDummy[3];
    if(a >= 0 && b <= 50)
    {
      CharacterDummy dummy = new CharacterDummy("thor", 12);
      CharacterDummy dummy1 = new CharacterDummy("casey", 32);
      CharacterDummy dummy2 = new CharacterDummy("obama", 44);
      returnList[0] = dummy;
      returnList[1] = dummy1;
      returnList[2] = dummy2;
      return returnList;
    }
    if(a > 50 && b <= 100)
    {
      CharacterDummy dummy = new CharacterDummy("soulja boy", 65);
      CharacterDummy dummy1 = new CharacterDummy("nas", 77);
      CharacterDummy dummy2 = new CharacterDummy("tow mater", 99);
      returnList[0] = dummy;
      returnList[1] = dummy1;
      returnList[2] = dummy2;
      return returnList;
    }
    else
    {
      CharacterDummy dummy = new CharacterDummy("trippie redd", 772);
      CharacterDummy dummy1 = new CharacterDummy("lil.pump", 1231);
      CharacterDummy dummy2 = new CharacterDummy("BP oil spill", 2010);
      returnList[0] = dummy;
      returnList[1] = dummy1;
      returnList[2] = dummy2;
      return returnList;
    }   
  }
}
