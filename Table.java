/**
*@Author: Rithvik Allamaneni
*4/8/2023
*The Table class is the main file that has most of the methods that, extract data, create event and person classes from the data, create a schedule based on the data, and finally assign seminars to a person based on their preferences. That information is then used to refill the person class for all the students. Then 2 txt files are created based on events and the person name.
* This class produces the Event_Roster file that 
**/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors


 /*
creates a schedule based on data from SrSeminar_RawData.csv and then fills a Person class with Seminar based on their preferences
*/
class Table{
  int ne = 18; // number of events
  int np = 74; // number of people
  ArrayList<Person> people = new ArrayList<Person>();
  Event[] events = new Event[ne+1];
  int[][] sce = new int[5][5];//schedule with id numbers of events
  int[] er = new int[ne+1]; // event ranking
  int[] order = new int[ne];
  static int MAXC = 16;
  /*
  fills the person classes in the people ArrayList and the event classes in the er array with information from the SrSeminar_RawData.csv") csv file
  no returns and args
  */
  public void getRFile() {
    File myObj = new File("SrSeminar_RawData.csv");
    try {// read file
      Scanner myReader = new Scanner(myObj);
      String line;
      int k = 0;
      while (myReader.hasNextLine()) {// loop until their are no more lines of data
        line = myReader.nextLine();// moves to next line of data
        String[] data = line.split(","); // splits the S
        people.add(new Person(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5])));
        er[Integer.parseInt(data[1])]+=5;  
        er[Integer.parseInt(data[2])]+=4;
        er[Integer.parseInt(data[3])]+=3;
        er[Integer.parseInt(data[4])]+=2;
        er[Integer.parseInt(data[5])]+=1;
        if(k+1<=18){
          events[k] = new Event(data[6], data[8],k);
        }
        k++;
        

      } // close while
      myReader.close();
    } // close try
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } // close catch
  }// close getCfile
  /*
  creates a 2d array that is a schedule and checks if there are any events horizontally that have the same instructor teaching that seminar
  no returns and args
  */
  public void makeSchedule(){
    int max;
    int[] replace = new int[ne];
    for (int i = 0; i < ne; i++) {//creates a duplicate of er. You have to copy each element or else the pointer gets copied
      replace[i] = er[i];
    }
    
    for (int i = 0; i < ne; i++) {//orders the array order from lowest nopc to highest
      max = 0;// any number below 
      for (int j = 0; j < ne; j++) {//finds the company with least people in the company
        if (replace[j] > max) {
          max = replace[j];
        }
      }
      for (int j = 0; j < ne; j++) {//creates order array with least nopc to most
        if (order[i] == 0 && replace[j] == max) {
          order[i] = j;
          replace[j] = 0;
        } // close if
      } // close j for loop
    }
    
    for (int i = 0; i < ne; i++) {//creates a duplicate of order. You have to copy each element or else the pointer gets copied
      replace[i] = order[i];
    }
    
    
    order = new int[25];
    for(int i = 0; i<order.length-1;i++){
			order[i] = replace[i%replace.length];
	}

	  int n = 0;
	  for(int i = 0; i<5 ;i++){//creates a 2d array from the array order
		  for(int j = 0; j<5 ;j++){
			  sce[i][j] = order[n];
		      n++;
	      }//close j for loop
	  }// close i for loop	
	  
	  //the following sections checks for times when presenters have 2 seminars and fixes it
	  boolean pass = false;
	  while (pass == false){
		  pass = true;
		  for(int i = 0; i<4; i++){
			  for(int j = 0; j<5; j++){
				  for (int k = 1; k<sce[i].length-i; k++){
					  if(events[sce[i][j]].getT().equals(events[sce[i+k][j]].getT())){
						  int m = j + 1;//the place the current session will swap with
						  if(j >= 5){
							  m = 0;
						  }
						  int temp = sce[i][j];
						  sce[i][j] = sce[i][(j+1)%5];
						  sce[i][j+1] = temp;
						  pass = false;
					  }
				  }
			  }// close j loop
		  }//close i loop
	  }// close while 
	  create();
  }// close makeSchedule	
 
  /*
  checks if int n can be placed in array choices which is the schedule for a specific person.
  the events can not be placed in if the person is already taking that seminars or if that seminar is full
  takes an int array anf int n as arguments
  */  
  public boolean check(int[] choices, int n){
	  for(int i = 0; i<choices.length ; i++){// checks if seminar already exists in the person's schedule
		  if(choices[i] == n){
			  return false;
		  }
	  }
	  if(events[n].getC()>=MAXC){// checks if the seminar is filled
		  return false;
	  }
	  return true;
  }// close check
   /*
  fills every person in the people arraylist with seminars based on their preferences. If a prefered seminar is not there, they are placed in the least occupied seminar.
  no returns and args
  */ 
  public void create(){
	  for(int i = 0; i<people.size(); i++){// loops through the people array list to create schedule for the seminars
		  int[] choices = {-1,-1,-1,-1,-1};
		  for (int j = 0; j<choices .length; j++){// the following loops check if the first pref of a person exists in a session, then a second one and then adds the the one with the highest pref to the array choices 
			  for (int k = 0; k<sce[j].length && choices[j] == -1; k++){
				  if(sce[k][j] == people.get(i).get1() && check(choices, sce[k][j])){// checks if pref1 exists in that time slot and is acceptable
					  choices[j] = sce[k][j];
					  events[sce[k][j]].fill(people.get(i));
				  }
			  }
			  for (int k = 0; k<sce[j].length && choices[j] == -1; k++){
				  if(sce[k][j] == people.get(i).get2() && check(choices, sce[k][j])){// checks if pref2 exists in that time slot and is acceptable
					  choices[j] = sce[k][j];
					  events[sce[k][j]].fill(people.get(i));
				  }
			  }
			  for (int k = 0; k<sce[j].length && choices[j] == -1; k++){
				  if(sce[k][j] == people.get(i).get3() && check(choices, sce[k][j])){// checks if that person's pref3 exists in that time slot and is acceptable
					  choices[j] = sce[k][j];
					  events[sce[k][j]].fill(people.get(i));
				  }
			  }
			  for (int k = 0; k<sce[j].length && choices[j] == -1; k++){
				  if(sce[k][j] == people.get(i).get4() && check(choices, sce[k][j])){// checks if that person's pref4 exists in that time slot and is acceptable
					  choices[j] = sce[k][j];
					  events[sce[k][j]].fill(people.get(i));
				  }
			  }
			  for (int k = 0; k<sce[j].length && choices[j] == -1; k++){
				  if(sce[k][j] == people.get(i).get5() && check(choices, sce[k][j])){// checks if pref5 exists in that time slot and is acceptable
					  choices[j] = sce[k][j];
					  events[sce[k][j]].fill(people.get(i));
				  }
			  }  
				  
			  if(choices[j] == -1){// if none of the events are acceptable or prefered, the lowest capacity event will be chosen 
				  int min = sce[0][j];
				  for (int k = 1; k<sce[j].length; k++){//finds event in timeslot that has least amount of people;
					  if(events[min].getC()>=events[sce[k][j]].getC() && check(choices, sce[k][j])){
						  min = sce[k][j];
					  }
				  }
				  choices[j] = min;
			  }// close if loop
		  }// close j loop
		  
		  people.get(i).fill(choices);// places schedule in person's schedule array in person class
	  }
	  writeFFile();
	  writeRFile();

		  
  }
  /*
  creates a txt file called Event Roster and writes the participants of each event 
  no returns and args
  */
  public void writeFFile(){// write file by events
	  try {
		FileWriter file = new FileWriter("Event_Roster.txt");

		// for some reason when I use a loop, it always doens't work and says i is null
		file.write(events[0].roster() + "\n\n");
			file.write(events[1].roster() + "\n\n");
			file.write(events[2].roster() + "\n\n");
			file.write(events[3].roster() + "\n\n");
			file.write(events[4].roster() + "\n\n");
			file.write(events[5].roster() + "\n\n");
			file.write(events[6].roster() + "\n\n");
			file.write(events[7].roster() + "\n\n");
			file.write(events[8].roster() + "\n\n");
			file.write(events[9].roster() + "\n\n");
			file.write(events[10].roster() + "\n\n");
			file.write(events[11].roster() + "\n\n");
			file.write(events[12].roster() + "\n\n");
			file.write(events[13].roster() + "\n\n");
			file.write(events[14].roster() + "\n\n");
			file.write(events[15].roster() + "\n\n");
			file.write(events[16].roster() + "\n\n");
			file.write(events[17].roster() + "\n\n");

		file.close();
	  } catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	  }
  }

  /*
  creates a txt file called Student Roster and Prints the seminars of each person one by one
  no returns and args
  */
  public void writeRFile(){// write file by person name and their sessions
	  try {
		FileWriter file = new FileWriter("Student_Roster.txt");
		StringBuilder line;
		for(int j = 0; j<people.size(); j++){

			line = new StringBuilder();
			line.append(people.get(j).getN() + ":\t");
			for (int i = 0; i<5;i++){
				line.append("Session " + (i+1) + ": " + events[people.get(j).getS(i)].toString(1) + "\t\t");
			}
			file.write(line.toString() + "\n\n");
		}
		file.close();
	  } catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	  }

  }
}
