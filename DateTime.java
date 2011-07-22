import java.util.*;

class DateTime implements Comparable, java.io.Serializable{
  
  private int day;
  private int month;
  private int year;
  private int hours;
  private int mins;
  
  public DateTime(int day, int month, int year){
    this.day = day;
    this.month = month;
    this.year = year;
  }
  
  public DateTime(int hours, int mins){
    this.hours = hours;
    this.mins = mins;
  }
    
  public DateTime(int day, int month, int year, int hours, int mins){
    this.day = day;
    this.month = month;
    this.year = year;
    this.hours = hours;
    this.mins = mins;
  }
  
  public int getDay(){
    return this.day;
  }
  
  public int getMonth(){
    return this.month;
  }
    
  public int getYear(){
    return this.year;
  }
  
  public int getHour(){
    return this.hours;
  }
  
  public int getMin(){
    return this.mins;
  }
  
  public String getDate(){
    return "" + day + "/" + month + "/" + year;
  }
  
  public String getTime(){
    return "" + hours + ":" + mins;
  }
  
  public String getDateTime(){
    return "" + day + "/" + month + "/" + year + hours + ":" + mins;
  }
  
  public int compareTo(Object o)
  {
    int value = 0;
    
    DateTime c = (DateTime)o;
    
    if(c.getYear() > this.getYear()) return -1;
    if(c.getYear() < this.getYear()) return 1;
    
    if(c.getMonth() > this.getMonth()) return -1;
    if(c.getMonth() < this.getMonth()) return 1;
    
    if(c.getDay() > this.getDay()) return -1;
    if(c.getMonth() < this.getMonth()) return 1;
    
    if(c.getHour() > this.getHour()) return -1;
    if(c.getHour() < this.getHour()) return 1;
    
    if(c.getMin() > this.getMin()) return -1;
    if(c.getMin() < this.getMin()) return 1;
    
    return 0;
  }
}