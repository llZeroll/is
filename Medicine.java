import java.*;
import java.util.*;
import java.lang.*;

class Medicine implements java.io.Serializable{

 private String supplier;
 private String medName;
 private String type;
 private int bulkOrder;
 private int thresholdValue;
 private int quantity;

 //Default Constructor
 Medicine() {

 }

 //Constructor
 Medicine (String supplier, String medName, String type, int bulkOrder, int thresholdValue, int quantity) {
  this.supplier = supplier;
  this.medName = medName;
  this.type = type;
  this.bulkOrder = bulkOrder;
  this.thresholdValue = thresholdValue;
  this.quantity = quantity;
 }

 public void decreaseMedicine(int byHowMany){
  this.quantity -= byHowMany;
 }

 public void restockMedicine() {
  this.quantity += this.bulkOrder;
 }
 
 public String getSupplier() {
   return this.supplier;
 } 
 
 public String getName() { 
   return this.medName;
 }
 
 public String getType() { 
   return this.type;
 }
 
 public int getBulkOrder() { 
   return this.bulkOrder;
 }
 
 public int getThreshold() { 
   return this.thresholdValue;
 }
 
 public int getQty() { 
   return this.quantity;
 }
 
 public void setQty(int n) {
  this.quantity = n;
 }
 
 public void setDetails (String supplier, String medName, String type, int bulkOrder, int thresholdValue, int quantity) {
  this.supplier = supplier;
  this.medName = medName;
  this.type = type;
  this.bulkOrder = bulkOrder;
  this.thresholdValue = thresholdValue;
  this.quantity = quantity;
 }

}//class


