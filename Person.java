class Person{

  private int pref1;
  private int pref2;
  private int pref3;
  private int pref4;
  private int pref5;
  private String name;
  private int[] schedule;

  public Person(String name, int pref1, int pref2, int pref3, int pref4, int pref5){
  this.name = name;
  this.pref1 = pref1-1;
  this.pref2 = pref2-1;
  this.pref3 = pref3-1;
  this.pref4 = pref4-1;
  this.pref5 = pref5-1;
  }
  
  public void fill(int[] choices){
	  
	  schedule = new int[choices.length];
	  for (int i = 0; i < choices.length; i++) {//copies choices to schedule. You have to copy each element or else just the pointer gets copied
		schedule[i] = choices[i];
		//System.out.println(schedule[i]);
      }
      //System.out.println(choices[0] + "," + choices[1] + "," + choices[2] + "," + choices[3] + "," + choices[4] + " " + name);
  }
  
  public int getS(int n){
	  if(schedule!=null){
		  //System.out.println(schedule[n]);
		  return schedule[n];
	  }
	  return -1;
  }
  
  public Person(String name, int id){
	this.name = name;
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
  
  
  public String getN(){
	  return name;
  }
  
  public int get1(){
	  return pref1;
  }
  
  public int get2(){
	  return pref2;
  }
  
  public int get3(){
	  return pref3;
  }
  
  public int get4(){
	  return pref4;
  }
  
  public int get5(){
	  return pref5;
  }
  
    
}
