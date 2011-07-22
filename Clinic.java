import java.*;
import java.util.*;
import java.lang.*;
import java.io.*;

class Clinic {
  
  private Hashtable <String, Patient> thePatients;
  private Vector <Consultation> theConsultations;
  private Hashtable <String, Medicine> theMedicines;
  
  public Clinic() {
    
    try { 
      ObjectInputStream inP =  new ObjectInputStream(new FileInputStream("Patient.dat"));  
      thePatients = (Hashtable<String, Patient>)inP.readObject();
      inP.close();
    }
    
    catch (Exception e) {
      thePatients = new Hashtable<String, Patient>(100);
    }
    
    try {
      ObjectInputStream inC =  new ObjectInputStream(new FileInputStream("Consultation.dat")); 
      theConsultations = (Vector<Consultation>)inC.readObject();
      inC.close();
    }
    
    catch (Exception e) {
      theConsultations = new Vector <Consultation> (100,100);
    }
    
    try {
      ObjectInputStream inM =  new ObjectInputStream(new FileInputStream("Medicine.dat"));
      theMedicines = (Hashtable<String, Medicine>)inM.readObject();
      inM.close();
    }
    
    catch (Exception e) {
      theMedicines = new Hashtable<String, Medicine>(100);         
    }
    
  }
  
  public Patient addPatient(String name, String nric, String gender, DateTime dob, String address, int phone) throws Exception{ 
    
    if (thePatients.containsKey(nric)) {
      throw new Exception(); // patient exists
    }
    else {
      
      Patient newPatient = new Patient(name, nric, gender, dob, address, phone);
      thePatients.put(nric, newPatient);
      
      return newPatient;
    }
  }
  
  public Patient[] getAllPatients(){
    Patient[] tempPatient;
    if (thePatients.isEmpty()) {
      return null;
    }
    else {
      int k = 0;
      tempPatient = new Patient[thePatients.size()];
      
      for(Enumeration e = thePatients.elements(); e.hasMoreElements();) {
        tempPatient[k] = (Patient)e.nextElement();
        k++;
        
      }
    }
    return tempPatient; 
  }
  
  public Medicine[] getAllMedicine(){
    Medicine[] tempMedicine;
    if (theMedicines.isEmpty()) {
      return null;
    }
    else {
      int k = 0;
      tempMedicine = new Medicine[theMedicines.size()];
      
      for(Enumeration e = theMedicines.elements(); e.hasMoreElements();) {
        tempMedicine[k] = (Medicine)e.nextElement();
        k++;
        
      }
    }
    return tempMedicine; 
  }//done Faz
  
  public Consultation addConsultation( String nric, DateTime dateTime,String illnessDesc, Prescription [] prescription, boolean medicineDispensed){
    
    Consultation newConsultation = new Consultation(nric, dateTime, illnessDesc, prescription, medicineDispensed);
    theConsultations.addElement(newConsultation);
    
    return newConsultation;
    
  }
  
  public Consultation[] getConsultations(String ic){
    
    Consultation [] tempConsultation;
    if (theConsultations.isEmpty()) {
      return null; // no consultations
    }
    else {
      int k = 0;
      Consultation thisConsultation;
      tempConsultation = new Consultation[theConsultations.size()];
      for(Enumeration e = theConsultations.elements(); e.hasMoreElements();) {
        thisConsultation = (Consultation)e.nextElement();
        if (thisConsultation.getIC().equals(ic)) {
          tempConsultation[k] = thisConsultation;
          k++;
        }
      }
      return tempConsultation;
    }
  }//done -faz
  
  public Consultation[] getUnprescribed(){
    Consultation [] tempConsultation;
    if (theConsultations.isEmpty()) {
      return null; // no consultations
    }
    else {
      int k = 0;
      Consultation thisConsultation;
      tempConsultation = new Consultation[theConsultations.size()];
      for(Enumeration e = theConsultations.elements(); e.hasMoreElements();) {
        thisConsultation = (Consultation)e.nextElement();
        if (!thisConsultation.getDispensed()) {
          tempConsultation[k] = thisConsultation;
          k++;
        }
      }
      return tempConsultation;
    }
  }
  
  public Medicine addMedicine(String supplier,String medName,String type,int bulkOrder,int thresholdValue,int qty) throws Exception{
    if (theMedicines.containsKey(medName)) {
      throw new Exception();//medicine exists
    }
    else {
      Medicine newMedicine = new Medicine(supplier, medName, type, bulkOrder, thresholdValue, qty);
      theMedicines.put(medName, newMedicine);
      
      return newMedicine;
      
    }
  }
  
  public Medicine getMedicine(String name) {
    return (Medicine)theMedicines.get(name);
  }
  
  public Patient getPatient(String ic) {
    return (Patient)thePatients.get(ic);
  }
  
  public Medicine[] getLowStockMed(){
    Medicine [] tempMedicine;
    if (theMedicines.isEmpty()) {
      return null; //no medicine
    }
    else {
      int k = 0;
      tempMedicine = new Medicine[theMedicines.size()];
      Medicine thisMedicine;
      for(Enumeration e = theMedicines.elements(); e.hasMoreElements();) {
        thisMedicine = (Medicine)e.nextElement();
        if (thisMedicine.getQty() < thisMedicine.getThreshold()) {
          tempMedicine[k] = thisMedicine;
          k++;
        }
      }
      return tempMedicine; 
    }
  }
  
  public Patient[] searchPatientIllness(String illness, DateTime dateFrom, DateTime dateTo){
    
    Patient [] tempPatient; 
    boolean exists;
    
    if (thePatients.isEmpty() || illness == null) {
      return null;// no patient
    }
    else {
      Consultation thisConsultation;
      Patient thisPatient;
      
      int k = 0;
      
      tempPatient = new Patient[thePatients.size()];
      
      for(Enumeration e = theConsultations.elements(); e.hasMoreElements();) {
        thisConsultation = (Consultation)e.nextElement();
        if((dateFrom != null && thisConsultation.getDateTime().compareTo(dateFrom) < 0) || (dateTo != null && thisConsultation.getDateTime().compareTo(dateTo) > 0))
          continue;
        if (thisConsultation.getIllnessDesc().toLowerCase().contains(illness.toLowerCase())) {
          thisPatient = getPatient(thisConsultation.getIC());  
          exists = false;
          for(int x = 0; x < k; x++) {
            if(tempPatient[x].getIC() == thisPatient.getIC()){exists = true;break;}
          }
          if(!exists){
            tempPatient[k] = thisPatient;
            k++;
          }
        }
      } 
      return tempPatient;
    }    
  }
  
  public Patient[] searchPatientPrescription(String presName, DateTime dateFrom, DateTime dateTo){
    Patient [] tempPatient;
    
    boolean found, exists;
    
    if (thePatients.isEmpty() || presName == null) {
      return null;//no patients
    }
    else {
      
      Consultation thisConsultation;
      Patient thisPatient;
      
      int k = 0;
      
      tempPatient = new Patient[thePatients.size()];
      
      for (Enumeration e = theConsultations.elements(); e.hasMoreElements();) {
        thisConsultation = (Consultation)e.nextElement();
        
        if((dateFrom != null && thisConsultation.getDateTime().compareTo(dateFrom) < 0) || (dateTo != null && thisConsultation.getDateTime().compareTo(dateTo) > 0))
          continue;
        
        Prescription [] thisPrescription = thisConsultation.getPrescription();
        found = false;
        for(int i = 0; i < thisPrescription.length; i++) {
          
          if (thisPrescription[i].medicineName.toLowerCase().contains(presName.toLowerCase())) {
            found = true;
            break;
          }
        }
        if(found)
        {
          thisPatient = getPatient(thisConsultation.getIC());
          exists = false;
          for(int x = 0; x < k; x++) {
            if(tempPatient[x].getIC() == thisPatient.getIC()){exists = true;break;}
          }
          if(!exists){
            tempPatient[k] = thisPatient;
            k++;
          }
        }
      }
      
      return tempPatient;
    }
  }    
  
  public Patient[] searchPatient(String keyword){
    Patient [] tempPatient;
    boolean found = false;
    
    if (thePatients.isEmpty() || keyword == null) {
      return null;//no patients
    }
    else {
      
      Patient thisPatient;
      
      int k = 0;
      
      tempPatient = new Patient[thePatients.size()];
      
      for(Enumeration e = thePatients.elements(); e.hasMoreElements();) {
        thisPatient = (Patient)e.nextElement();
        if(thisPatient.getName().toLowerCase().contains(keyword.toLowerCase())||thisPatient.getIC().toLowerCase().contains(keyword.toLowerCase()))
        {
          tempPatient[k] = thisPatient;
          k++;
        }
      } 
      return tempPatient;
    }
  }    
  
  public void saveFile() {
    try {
      ObjectOutputStream outP = new ObjectOutputStream(new FileOutputStream("Patient.dat"));
      outP.writeObject(thePatients);
      outP.close();
    }
    
    catch (Exception e) {
    }
    
    try {
      ObjectOutputStream outM = new ObjectOutputStream(new FileOutputStream("Medicine.dat"));   
      outM.writeObject(theMedicines);
      outM.close();
    }
    
    catch (Exception e){    
    }     
    
    try {
      ObjectOutputStream outC = new ObjectOutputStream(new FileOutputStream("Consultation.dat"));
      outC.writeObject(theConsultations);
      outC.close();
    }
    
    catch (Exception e) {
    }
  } //saveFile
  
}//class
