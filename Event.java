import java.util.ArrayList;
import java.io.FileWriter;   // Import the FileWriter class




class Event{

  private String teacher;
  private String name;
  private int id;
  private ArrayList<Person> people = new ArrayList<Person>();
  

  public Event(String name, String s1, int id){
    this.name = name;
    this.teacher = s1.substring(0, s1.length()-6);
    this.id = id;

  }
  
  public String getT(){
		return teacher;
  }
  
  public int getC(){
	  return people.size();
  }
  public void fill(Person p){
	  people.add(p);
  }
  

  
  public String toString(int n){
	  if(n == 1){
		  return name;
	  }
	  return("Teacher: " + teacher + " Name: " + name + " id: " + id);
  }
  
  public StringBuilder roster(){
	  StringBuilder line = new StringBuilder();
	  line.append("Event: " + name + "\n");
	  for (int i = 0; i<people.size(); i++){
		  line.append(people.get(i).getN() + "\n");
	  }
	  return line;
  }

  
}
