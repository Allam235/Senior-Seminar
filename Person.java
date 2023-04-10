/**
*@Author: Rithvik Allamaneni
*4/8/2023
*The Person class is an Object that contains information about one person. Information such as their preferences, name, and their assigned seminars.
**/


  /*
  the person class that contain info on a person such as preferences and their schedule
  no returns and args
  */
class Person{

  private int pref1;
  private int pref2;
  private int pref3;
  private int pref4;
  private int pref5;
  private String name;
  private int[] schedule;
  /*
  the constructor
  takes in the name and preferences of the person and returns the object
  */
  public Person(String name, int pref1, int pref2, int pref3, int pref4, int pref5){
  this.name = name;
  this.pref1 = pref1-1;
  this.pref2 = pref2-1;
  this.pref3 = pref3-1;
  this.pref4 = pref4-1;
  this.pref5 = pref5-1;
  }
  /*
  fills the person schedule array with the inputed choice array
  takes an int array as input and returns void
  */
  public void fill(int[] choices){
	  
	  schedule = new int[choices.length];
	  for (int i = 0; i < choices.length; i++) {//copies choices to schedule. You have to copy each element or else just the pointer gets copied
		schedule[i] = choices[i];
		//System.out.println(schedule[i]);
      }
      System.out.println(choices[0] + "," + choices[1] + "," + choices[2] + "," + choices[3] + "," + choices[4] + " " + name);
  }
  
  /*
  returns a specific index in the array
  * takes an int as arg and returns int
  */
  public int getS(int n){
	  if(schedule!=null){
		  //System.out.println(schedule[n]);
		  return schedule[n];
	  }
	  return -1;
  }

  
  public String toString (){
	  if(schedule != null){
		  String str = "";
		  for(int i = 0; i<schedule.length; i++){
			  str += str + " Seminar " + i + ": " + schedule[i];
		  }
		  return "Name: " + name + " Schedule: " + str;
	  }
		  
	  return "Name: " + name + " Pref1: " + pref1 + " Pref2: " + pref2 + " Pref3: " + pref3 + " Pref4: " + pref4 + " Pref5: " + pref5;
  }
  
  /*
  returns name of person
  */

  public String getN(){
	  return name;
  }
  /*
  returns pref1 of person
  */  
  public int get1(){
	  return pref1;
  }
  /*
  returns pref2 of person
  */  
  public int get2(){
	  return pref2;
  }
  /*
  returns pref3 of person
  */  
  public int get3(){
	  return pref3;
  }
  /*
  returns pref4 of person
  */  
  public int get4(){
	  return pref4;
  }
  /*
  returns pref5 of person
  */  
  public int get5(){
	  return pref5;
  }
  
    
}
