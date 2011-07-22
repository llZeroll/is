import java.*;
import java.util.*;
import java.lang.*;

class Patient implements java.io.Serializable{

 private String name;
 private String ic;
 private String gender;
 private DateTime dob;
 private String address;
 private int phone;


 //Default Constructor
 Patient() {

 }
 
 //Constructor
 public Patient (String name, String ic, String gender, DateTime dob, String address, int phone) {
   this.name = name;
   this.ic = ic;
   this.gender = gender;
   this.dob = dob;
   this.address = address;
   this.phone = phone;
 }
 
 public String getName() {
  return this.name;
 } 

 public String getIC() { 
   return this.ic;
 }
 
 public String getGender() { 
   return this.gender;
 }
 
 public DateTime getDob() { 
   return this.dob;
 }
 
 public String getAddress() { 
   return this.address;
 }
 
 public int getPhone() { 
   return this.phone;
 }
 
 public void setName(String n) {
   this.name = n;
 }
 
 public void setIC(String n) {
   this.ic = n;
 }
 
 public void setGender(String n) {
   this.gender = n;
 }
 
 public void setDob(DateTime n) {
   this.dob = n;
 }
 
 public void setAddress(String n) {
   this.address = n;
 }
 
 public void setPhone(int n) {
   this.phone = n;
 }
 
 public void setDetails(String name, String ic, String gender, DateTime dob, String address, int phone) {
   this.name = name;
   this.ic = ic;
   this.gender = gender;
   this.dob = dob;
   this.address = address;
   this.phone = phone;
 }

 public String toString(){
   return getIC()+", "+getName()+", "+getGender()+", "+getDob().getDate()+", "+getAddress()+", "+getPhone();
 }
}//class

