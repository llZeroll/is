import java.*;
import java.util.*;
import java.lang.*;

 class Consultation implements java.io.Serializable{

 private String nric;
 private DateTime dateTime;
 private String illnessDesc;
 private Prescription[] prescription;
 private boolean medicineDispensed;


 /*//Default Constructor
 public Consultation() {

 }*/

 //Constructor
 public Consultation (String nric, DateTime dateTime, String illnessDesc, Prescription[] prescription, boolean medicineDispensed) {
  this.nric = nric;
  this.dateTime = dateTime;
  this.illnessDesc = illnessDesc;
  this.prescription = prescription;
  this.medicineDispensed = medicineDispensed;
 }

 public boolean getDispensed() {
   return medicineDispensed;
 }

 public String getIC() {
   return nric;
 }
 
 public DateTime getDateTime() {
   return dateTime;
 }
 
 public String getIllnessDesc() {
   return illnessDesc;
 }
 
 public Prescription [] getPrescription() {
   return prescription;
 }
  
 public String getPrescriptionDesc() {
   if(prescription.length <= 0) return "";

   String p = "" + prescription[0];
   for(int i = 1; i < prescription.length; i++) p += "\n" + prescription[i];
   return p;
 }//done Faz
 
 public void prescribeMedicine()
 {
   this.medicineDispensed = true;
 }
 
}//class

