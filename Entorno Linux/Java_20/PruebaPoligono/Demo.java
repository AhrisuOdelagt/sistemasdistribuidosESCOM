import java.io.*;
import java.util.Arrays;
 
class Demo implements java.io.Serializable
{
      public String a;
      public String b;
 
      // Default constructor
      public Demo(String a, String b)
      {
             this.a = a;
             this.b = b;
      }
	  
	  // Getters
	  public String getA() {
		return this.a;
	  }
	  public String getB() {
		return this.b;
	  }
	  
	  // Setters
	  public void setA(String a) {
		this.a = a;
	  }
	  public void setB(String b) {
		this.b = b;
	  }
}
