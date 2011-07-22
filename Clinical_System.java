import java.*;
import java.util.*;
import java.lang.*;
import java.io.*;

class Clinical_System {

 Clinic cl = new Clinic();
 StaffUI ui;
  
 Clinical_System(StaffUI s) {
    ui = s;
 }

 public void registerPatient(String name, String nric, String gender, DateTime dob, String address, int phone) throws Exception{
   
     Patient a = cl.addPatient(name, nric, gender, dob, address, phone);
     ui.displayPatient(a); 

 } //done! -LQ //removed exception handling. -faz
 
 public void addMedicine(String supplier, String medName, String type, int bulkOrder, int thresholdValue, int qty) throws Exception{
   
   Medicine m = cl.addMedicine(supplier, medName, type, bulkOrder, thresholdValue, qty);
   ui.displayMedicine(m);
   
 } //done! - LQ //removed exception handling. -faz
 
 public void addConsultation(String ic,  DateTime dateTime, String illnessDesc, Prescription[] prescription, boolean medDispensed){

   Consultation c = cl.addConsultation(ic, dateTime, illnessDesc, prescription, medDispensed);
   ui.displayConsultation(c);
   
 } //done! - LQ
 
 public Patient[] displayAllPatients() {
   
   Patient[] p = cl.getAllPatients();
   return p;
   
 } //done! - LQ
 
 public Medicine[] displayAllMedicine() {
   
   Medicine[] m = cl.getAllMedicine();
   return m;
   
 } //done! - Faz
 
 //public Patient modifyPatient(String ic) { //note, i followed seq diagram for this. not the same with class diagram. - luq
 public Patient getPatient(String ic) { //i changed to getPatient
  Patient p = cl.getPatient(ic);
  return p;
 } //done! - Faz
 
 public Consultation[] getConsultations(String ic){
   Consultation [] c = cl.getConsultations(ic);
   return c;
 } // done! - Faz
 
 public Consultation[] getUnprescribed() {
  Consultation [] c = cl.getUnprescribed();
  return c;
 } //done! - LQ
 
 public void prescribeMedicine(Consultation c) {
  Prescription [] p = c.getPrescription();
  for(int i = 0; i<p.length; i++) {
   Medicine m = cl.getMedicine(p[i].medicineName);
   m.decreaseMedicine(p[i].qty);
  }
  c.prescribeMedicine(); //shouldn't have any return type for this method. As opposed to the class diagram
  cl.saveFile();
     
 } //done! - LQ
 
 public void reportMedicine(){
  Medicine[] m = cl.getLowStockMed();
  ui.displayMedicines(m);
 } //done! - LQ 
 
 public void restockMedicine(String name){
  Medicine m = cl.getMedicine(name);
  m.restockMedicine();
  ui.displayMedicine(m);
  cl.saveFile();   
 } //done! - LQ
 
 public void searchIllness(String illnessName, DateTime dateFrom, DateTime dateTo) {
  Patient[] p = cl.searchPatientIllness(illnessName, dateFrom, dateTo);
  ui.displayPatients(p);
 } //done! - LQ
 
 public void searchPrescription(String presName, DateTime dateFrom, DateTime dateTo) {
  Patient[] p = cl.searchPatientPrescription(presName, dateFrom, dateTo);
  ui.displayPatients(p);
 } //done! - LQ
  
 public void searchPatient(String keyword) {
  Patient[] p = cl.searchPatient(keyword);
  ui.displayPatients(p);  
 } //done! - LQ
 
 public void saveFile() {
  cl.saveFile();
 }
}//class
