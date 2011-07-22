
class Prescription implements java.io.Serializable{

 public String medicineName;
 public String medicineType;
 public int qty;

 public Prescription(String medicineName, String medicineType, int qty){
 
   this.medicineName = medicineName;
   this.medicineType = medicineType;
   this.qty = qty;
 
 }
 public String toString(){
   return medicineName + " (" + qty + ((medicineType.equals("Pill"))?"pills":medicineType) +")";
 }
}//class Prescription

