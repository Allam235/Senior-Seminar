class Event{

  private String teacher;
  private String name;
  private int id;
  private int capacity;
  

  public Event(String name, String s1, int id){
    this.name = name;
    this.teacher = s1.substring(0, s1.length()-6);
    this.id = id;
    capacity = 0;
  }
  
  public String getT(){
		return teacher;
  }
  
  public int getC(){
	  return capacity;
  }
  public void fill(){
	  capacity++;
  }
  
  public String toString(int n){
	  if(n == 1){
		  return name;
	  }
	  return("Teacher: " + teacher + " Name: " + name + " id: " + id);
  }

  
}
