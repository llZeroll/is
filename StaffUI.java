interface StaffUI{
  
  public void mainMenu();
  public void registerPatient();
  public void modifyPatient(); 
  public void displayPatient(Patient p);
  public void displayPatients(Patient [] p);
  public void displayAllPatients();
  public void addConsultation();
  public void displayConsultation(Consultation c);
  public void displayConsultations(Consultation [] c);
  public void addMedicine();
  public void displayMedicine(Medicine m);
  public void displayMedicines(Medicine [] m);
  public void prescribeMedicine();
  public void reportMedicine();
  public void restockMedicine();
  public void searchPrescription();
  public void searchIllness();
  public void displayError(String message);
  
}