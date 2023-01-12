
public class CharacterDummy
{
  private String name;
  private int power;
  private String[] superpowers;
  public CharacterDummy(String name, int power, String[] superpowers)
  {
    this.name = name;
    this.power = power;
    this.superpowers = superpowers;
    
  }
  public CharacterDummy(String name, int power)
  {
    this.name = name;
    this.power = power;
    superpowers = new String[1];
    superpowers[0] = "none";
  }
  public String getName()
  {
    return name;
  }
  public int getPower()
  {
    return power;
  }
  
  public String getSuperpowers()
  {
    String r = "";
    for(int i = 0; i < superpowers.length; i++)
    {
      if(i == 0)
      {
        r += superpowers[i];
        continue;
      }
      else
      {
        r += ", " + superpowers[i];
      }
    }
    return r;
  }
}
