import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
  
public class StaffGUI extends JFrame implements ActionListener, StaffUI{

   public static void main(String [] args){
    
    StaffGUI ui = new StaffGUI();
    ui.setVisible(true);
    
  }
  private final int SCREEN_WIDTH = 800;
  private final int SCREEN_HEIGHT = 600;
  private final int SCREEN_X = 100;
  private final int SCREEN_Y = 100;
  
  private final String LOGO = "sucure.gif";
  
  private final int MAIN_MENU = 0;
  private final int REGISTER_PATIENT = 1;
  private final int MODIFY_PATIENT = 2;
  private final int CREATE_CONSULTATION = 3;
  private final int ADD_MEDICINE = 4;
  private final int PRESCRIBE_MEDICINE = 5;
  private final int RESTOCK_MEDICINE = 6;
  private final int REPORT_MEDICINE = 7;
  private final int SEARCH_PRESCRIPTION = 8;
  private final int SEARCH_ILLNESS = 9;
  
  private int state;
  
  String [] menu = {
      
      "Register Patient",
      "Modify Patient Records",
      "Create Consultation",
      "Add Medicine",
      "Prescribe Medicine",
      "Restock Medicine",
      "Report Medicine Stock",
      "Search Prescriptions",
      "Search Illnesses",
      "Quit"
      
  };
    
  Container windowPane;
  JPanel menuPane;
  JPanel contentPane;
  
  //register patient fields
  JLabel nric_label_DisplayPatient;
  JLabel name_label_DisplayPatient;
  JLabel gender_label_DisplayPatient;
  JLabel dob_label_DisplayPatient;
  JLabel address_label_DisplayPatient;
  JLabel phone_label_DisplayPatient;
  JTextField nric_DisplayPatient;
  JTextField name_DisplayPatient;
  JComboBox gender_DisplayPatient;
  JComboBox day_DisplayPatient;
  JComboBox month_DisplayPatient;
  JComboBox year_DisplayPatient;
  JTextArea address_DisplayPatient;
  JTextField phone_DisplayPatient;
  
  JButton submit_button_RegisterPatient;
  JButton cancel_button_RegisterPatient;
  
  JButton submit_button_ModifyPatient;
  JButton cancel_button_ModifyPatient;

  JButton submit_button_CreateConsultation;
  JButton cancel_button_CreateConsultation;

  JButton submit_button_PrescribeMedicine;
  JButton cancel_button_PrescribeMedicine;
  
  JButton submit_button_ModifyMedicine;
  JButton cancel_button_ModifyMedicine;
  
  JButton submit_button_RestockMedicine;
  JButton cancel_button_RestockMedicine;
  
  JButton submit_button_AddMedicine;
  JButton cancel_button_AddMedicine;
  
  JLabel nric_label_DisplayConsultation;
  JTextField nric_DisplayConsultation;
  JLabel date_label_DisplayConsultation;
  JComboBox day_DisplayConsultation;
  JComboBox month_DisplayConsultation;
  JComboBox year_DisplayConsultation;
  JLabel time_label_DisplayConsultation;
  JComboBox hour_DisplayConsultation;
  JComboBox min_DisplayConsultation;
  JLabel illness_label_DisplayConsultation;
  JTextArea illness_DisplayConsultation;
  
  JLabel prescription_label_DisplayConsultation;
  JButton prescription_bttn_DisplayConsultation;
  JButton prescription_delete_bttn_DisplayConsultation;
  JTable prescription_tble_DisplayConsultation;
  JLabel prescribed_label_DisplayConsultation;
  JCheckBox prescribed_DisplayConsultation;
  
  JButton done_button_DisplayConsultation;
  
  JLabel name_label_DisplayMedicine;
  JTextField name_DisplayMedicine;
  JLabel supplier_label_DisplayMedicine;
  JTextField supplier_DisplayMedicine;
  JLabel type_label_DisplayMedicine;
  JComboBox type_DisplayMedicine;
  JLabel quantity_label_DisplayMedicine;
  JTextField quantity_DisplayMedicine;
  JLabel orderQuantity_label_DisplayMedicine;
  JTextField orderQuantity_DisplayMedicine;
  JLabel thresholdValue_label_DisplayMedicine;
  JTextField thresholdValue_DisplayMedicine;
  
  JLabel search_label_SearchConsultation;
  JTextField search_SearchConsultation;
  JLabel type_label_SearchConsultation;
  JComboBox type_SearchConsultation;
  JLabel dateFrom_label_SearchConsultation;
  JComboBox dayFrom_SearchConsultation;
  JComboBox monthFrom_SearchConsultation;
  JComboBox yearFrom_SearchConsultation;
  JLabel dateTo_label_SearchConsultation;
  JComboBox dayTo_SearchConsultation;
  JComboBox monthTo_SearchConsultation;
  JComboBox yearTo_SearchConsultation;
  JButton searchBtn_SearchConsultation;
  
  JTable patientsTable; 
  JButton selectButton_SelectPatient;
  
  JTable consultationTable;
  JButton selectButton_SelectConsultation;
  
  JTable medicineTable;
  JButton selectButton_SelectMedicine;
  
  JButton logButton;
  
  JButton printButton_SelectPatient;
  
  Clinical_System cS;
  
  Patient selectedPatient;
  Patient [] selectedPatients;
  
  Consultation selectedConsultation;
  Consultation [] selectedConsultations;
  
  Medicine selectedMedicine;
  Medicine [] selectedMedicines;
    
  boolean logged;
  
  public StaffGUI(){
    
    cS = new Clinical_System(this);
    
    logged = false;
    
    initWindow();
    mainMenu();
    
  }
  
 // aqui estaba//
  
  public void initWindow(){
    
    windowPane = getContentPane();
    menuPane = new JPanel();
    
    //set the frame properties
    setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    setResizable(false);
    setTitle("SuCure Clinical System");
    setLocation(SCREEN_X, SCREEN_Y);
    
    menuPane.setSize(100, SCREEN_Y);
    
    //set the layout manager
    menuPane.setLayout(new GridLayout(10,1, 5, 5));
    windowPane.setLayout(new BorderLayout(10, 10));
    
    JButton btn;

    //create buttons
    for(int i = 0; i < 10; i++){
      
      btn = new JButton(menu[i]);
      btn.addActionListener(this);
      menuPane.add(btn);
      
    }
    windowPane.add(menuPane, BorderLayout.EAST);
    
  }
  
  public void mainMenu(){
    
    state = MAIN_MENU;
    
    if(contentPane != null)
      windowPane.remove(contentPane);
    
    contentPane = new JPanel();
    
    contentPane.setLayout(new BorderLayout());
    
    ImageIcon logo = new ImageIcon(LOGO);
    
    JLabel suCureLogo = new JLabel(logo);
    
    contentPane.add(suCureLogo, BorderLayout.CENTER);
    
    windowPane.add(contentPane, BorderLayout.CENTER);
    
    validate();
    
  }
  
  private boolean login()
  {
    String password;
    
    if(!logged){
      password = JOptionPane.showInputDialog(null,"Please Enter Password", "Doctor's Login", JOptionPane.PLAIN_MESSAGE);
      
      if(password == null) return false;
      
      if(password.equals("password"))
      {
        logged = true;
        logButton = new JButton("Logout");
        logButton.addActionListener(this);
        menuPane.setLayout(new GridLayout(11,1, 7, 7));
        menuPane.add(logButton);
        validate();
        return true;
      }
      else{
        displayError("Wrong Password.");
      }
    }
    else return true;
    return false;
  }
  
  private void logout()
  {
    if(logged)
    {
      logged = false;
      menuPane.setLayout(new GridLayout(10,1, 5, 5));
      menuPane.remove(logButton);
      validate();
      mainMenu();
    }
  }
  
  public void actionPerformed(ActionEvent event){
    
    if(event.getSource() instanceof JButton){
      
      JButton menuBtn = (JButton)event.getSource();
      
      if(menuBtn == submit_button_RegisterPatient){
        registerPatientSubmitHandler();
        return;
      }
      else if(menuBtn == cancel_button_RegisterPatient){
        registerPatientCancelHandler();
        return;
      }   
      else if(menuBtn == submit_button_ModifyPatient){
        modifyPatientSubmitHandler();
        return;
      }    
      else if(menuBtn == cancel_button_ModifyPatient){
        modifyPatientCancelHandler();
        return;
      }
      if(menuBtn == submit_button_AddMedicine){
        addMedicineSubmitHandler();
        return;
      }
      else if(menuBtn == cancel_button_AddMedicine){
        addMedicineCancelHandler();
        return;
      }
      if(menuBtn == submit_button_RestockMedicine){
        restockMedicineSubmitHandler();
        return;
      }
      else if(menuBtn == cancel_button_RestockMedicine){
        restockMedicineCancelHandler();
        return;
      }
      if(menuBtn == submit_button_CreateConsultation){
        addConsultationSubmitHandler();
        return;
      }
      else if(menuBtn == cancel_button_CreateConsultation){
        addConsultationCancelHandler();
        return;
      }
      else if(menuBtn == submit_button_ModifyMedicine){
        modifyMedicineSubmitHandler();
        return;
      }
      else if(menuBtn == cancel_button_ModifyMedicine){
        modifyMedicineCancelHandler();
        return;
      }
      else if(menuBtn == submit_button_PrescribeMedicine){
        prescribeMedicineSubmitHandler();
        return;
      }
      else if(menuBtn == cancel_button_PrescribeMedicine){
        prescribeMedicineCancelHandler();
        return;
      }
      else if(menuBtn == selectButton_SelectPatient && state == MODIFY_PATIENT){
        modifyPatientSelectHandler();
        return;
      }
      else if(menuBtn == selectButton_SelectPatient && (state == SEARCH_PRESCRIPTION || state == SEARCH_ILLNESS)){
        searchConsultationSelectHandler();
        return;
      }
      else if(menuBtn == selectButton_SelectConsultation && (state == SEARCH_PRESCRIPTION || state == SEARCH_ILLNESS)){
        displayConsultationSelectHandler();
        return;
      }
      else if(menuBtn == done_button_DisplayConsultation && (state == SEARCH_PRESCRIPTION || state == SEARCH_ILLNESS)){
        displayConsultationDoneHandler();
        return;
      }
      
      else if(menuBtn == selectButton_SelectPatient && state == CREATE_CONSULTATION){
        createConsultationSelectHandler();
        return;
      }
      else if(menuBtn == selectButton_SelectConsultation && state == PRESCRIBE_MEDICINE){
        prescribeMedicineSelectHandler();
        return;
      }
      else if(menuBtn == searchBtn_SearchConsultation){
        searchSubmitHandler();
        return;
      }
      else if(menuBtn == selectButton_SelectMedicine && state == REPORT_MEDICINE){
        reportMedicineSelectHandler();
        return;
      }
      else if(menuBtn == selectButton_SelectMedicine && state == RESTOCK_MEDICINE){
        restockMedicineSelectHandler();
        return;
      }
      else if(menuBtn == prescription_bttn_DisplayConsultation)
      {
        addPrescriptionHandler();
        return;
      }  
      else if(menuBtn == prescription_delete_bttn_DisplayConsultation)
      {
        removePrescriptionHandler();
        return;
      }
      else if(menuBtn == printButton_SelectPatient)
      {
        printButtonHandler();
        return;
      }
      else{
        mainMenuHandler(menuBtn);
      }        
    }
  }
  
  private void mainMenuHandler(JButton menuBtn){
       
    int i;
    
    if(menuBtn == logButton){
      logout();
      return;
    }
    
    for(i = 0; i < menu.length; i++){
      if(menu[i].equals(menuBtn.getText())) break;
    }
       
    int selection = i+1;
        
    switch(selection)
    {
      //Register Patient
      case 1:
        registerPatient();
        break;       
      //Modify Patient Record
      case 2:
        modifyPatient();
        break;
      //Create Consultation
      case 3:
        if(login())addConsultation();
        break;  
      //Add Medicine
      case 4:
        addMedicine();
        break;
      //Prescribe Medicine    
      case 5:
        prescribeMedicine();
        break;
      //Restock Medicine    
      case 6:
        restockMedicine();
        break;
      //Report Medicine Stock    
      case 7:
        reportMedicine();
        break;
      //Search Prescriptions      
      case 8:
        if(login())searchPrescription();
        break;
      //Search Illnesses
      case 9:
        if(login())searchIllness();
        break;
      //quit
      case 10:
        if(JOptionPane.showConfirmDialog(null, "Do you really want to exit?","Confirm",JOptionPane.YES_NO_OPTION) == 0)
          System.exit(0);
        break;
      default:
        displayError("No handler.");
        mainMenu();
      }
  }
  
  private void printButtonHandler(){
    try{
    
      String type="";
      
      if(state == SEARCH_PRESCRIPTION)
        type = "Prescription ";
      else if(state == SEARCH_ILLNESS)
        type = "Illness ";
      
      displayInformation("Please wait while system detects printer information.\n(Click OK to continue)");
      
      Calendar calendar = new GregorianCalendar();
      
      patientsTable.print(JTable.PrintMode.valueOf("FIT_WIDTH"), 
                          new MessageFormat("SuCure System Patient "+type+"Report"),
                          new MessageFormat("Reported on "+ calendar.get(Calendar.DAY_OF_MONTH) + " - " + 
                                                            (calendar.get(Calendar.MONTH) + 1) + " - " + 
                                                            calendar.get(Calendar.YEAR) + "    " + 
                                                            calendar.get(Calendar.HOUR_OF_DAY) + " : " + calendar.get(Calendar.MINUTE)
                                            ) 
                         );
              
    }
    catch(Exception e)
    {
      displayError("Unable to print report.");
    }
  }
    
  private void registerPatientSubmitHandler(){
    
    Patient p = patientSubmitHandler();
    
    if(p == null) return;
    
    //try to register patient
    try{
      
      cS.registerPatient(p.getName(), p.getIC(), p.getGender(), p.getDob(), p.getAddress(), p.getPhone());
      cS.saveFile();   
      displayInformation("Patient Registered Successfully.");
      mainMenu();
    }
    catch(Exception e)
    {
      displayError("Patient Exists.\nInput Not Registered.\n");      
    }      
  }
  
  private void modifyPatientSubmitHandler(){
    
    Patient p = patientSubmitHandler();
    if(p == null) return;
    
    //try to set patient details
    selectedPatient.setDetails(p.getName(), p.getIC(), p.getGender(), p.getDob(), p.getAddress(), p.getPhone());
    cS.saveFile();
    displayInformation("Patient Information Updated Successfully.");
    mainMenu();
  }
  
  private Patient patientSubmitHandler(){
    
    //read input and check validity
    
    String  nric = nric_DisplayPatient.getText().trim();
    if(nric.equals("")){ displayError("Empty NRIC Field!\nPlease enter details\n"); return null;}
    
    String name = name_DisplayPatient.getText().trim();
    if(name.equals("")){ displayError("Empty Name Field!\nPlease enter details\n"); return null;}
    
    String gender = (String)gender_DisplayPatient.getSelectedItem();
    
    String dayS = (String)day_DisplayPatient.getSelectedItem();
    String monthS = (String)month_DisplayPatient.getSelectedItem();
    String yearS = (String)year_DisplayPatient.getSelectedItem();
    
    int day = Integer.parseInt(dayS);
    int month = Integer.parseInt(monthS);
    int year = Integer.parseInt(yearS);
    
    String address = address_DisplayPatient.getText().trim();
    if(address.equals("")){ displayError("Empty Address Field!\nPlease enter details\n"); return null;}
    
    String phoneS = phone_DisplayPatient.getText().trim();
    if(phoneS.equals("")){ displayError("Empty Phone Field!\nPlease enter details\n"); return null;}
    
    int phone;
    
    try{
      phone = Integer.parseInt(phoneS);
    }
    catch(Exception e){
      displayError("Invalid Phone Number Field!\nPlease re-enter details\n"); 
      return null;  
    }
    
    return new Patient(name, nric, gender, (new DateTime(day, month, year)), address, phone);
  }
  
  private void addMedicineSubmitHandler(){
    
    String name = name_DisplayMedicine.getText().trim();
    if(name.equals("")){ displayError("Empty Name Field!\nPlease enter details\n"); return;}
    
    String supplier = supplier_DisplayMedicine.getText().trim();
    if(supplier.equals("")){ displayError("Empty Supplier Field!\nPlease enter details\n"); return;}
    
    String type = (String)type_DisplayMedicine.getSelectedItem();
    
    String quantityS = quantity_DisplayMedicine.getText().trim();
    if(quantityS.equals("")){ displayError("Empty Quantity Field!\nPlease enter details\n"); return;}
    
    String orderQuantityS = orderQuantity_DisplayMedicine.getText().trim();
    if(orderQuantityS.equals("")){ displayError("Empty Bulk Order Value Field!\nPlease enter details\n"); return;}
    
    String thresholdValueS = thresholdValue_DisplayMedicine.getText().trim();
    if(thresholdValueS.equals("")){ displayError("Empty Reorder Threshold Value Field!\nPlease enter details\n"); return;}
    
    int quantity;
    
    try{
    
      quantity = Integer.parseInt(quantityS);
    }
    catch(Exception e){
      displayError("Invalid Quantity Field!\nPlease re-enter details\n"); 
      return;  
    }
    
    int orderQuantity;
    
    try{
    
      orderQuantity = Integer.parseInt(orderQuantityS);
    }
    catch(Exception e){
      displayError("Invalid Bulk Order Value Field!\nPlease re-enter details\n"); 
      return;  
    }
    
    int thresholdValue;
    
    try{
    
      thresholdValue = Integer.parseInt(thresholdValueS);
    }
    catch(Exception e){
      displayError("Invalid Reorder Threshold Value Field!\nPlease re-enter details\n"); 
      return;  
    }
    
    //try to add medicine
    try{
      
      cS.addMedicine(supplier, name, type, orderQuantity, thresholdValue, quantity);
      displayInformation("Medicine Added Successfully.");
      cS.saveFile();   
      mainMenu();
    }
    catch(Exception e)
    {
      displayError("Medicine Exists.\nInput Not Saved.\n");      
    }
      
  }
  
  private void addConsultationSubmitHandler(){
    
    String nric = nric_DisplayConsultation.getText().trim();
    if(nric.equals("")){ displayError("Empty NRIC Value Field!\nPlease enter details\n"); return;}
    
    int day = Integer.parseInt((String)day_DisplayConsultation.getSelectedItem());
    int month = Integer.parseInt((String)month_DisplayConsultation.getSelectedItem());
    int year = Integer.parseInt((String)year_DisplayConsultation.getSelectedItem());
    int hour = Integer.parseInt((String)hour_DisplayConsultation.getSelectedItem());
    int min = Integer.parseInt((String)min_DisplayConsultation.getSelectedItem());

    String illnessDesc = illness_DisplayConsultation.getText().trim();
    if(illnessDesc.equals("")){ displayError("Empty Illness Description Field!\nPlease enter details\n"); return;}
    
    boolean medDispensed = prescribed_DisplayConsultation.isSelected();
    
    String nameMed;
    String typeMed;
    String amntMed;
    String tempMed;
    int qty;
    
    Prescription[] prescription = new Prescription[prescription_tble_DisplayConsultation.getRowCount()];
    
    for(int x = 0; x < prescription_tble_DisplayConsultation.getRowCount(); x++){
      
      tempMed = (String)prescription_tble_DisplayConsultation.getValueAt(x,0);
      amntMed = (String)prescription_tble_DisplayConsultation.getValueAt(x,1);
      
      nameMed = tempMed.substring(0, tempMed.lastIndexOf("("));
      typeMed = tempMed.substring(tempMed.lastIndexOf("(")+1, tempMed.length()-1);
      
      
      if(tempMed.equals("") || amntMed.equals("")){ 
        displayError("Incomplete Prescription Field!\nPlease enter details\n"); 
        return;
      }
      else {
        try{
          qty = Integer.parseInt(amntMed);
          if(qty <= 0) throw new Exception();
        }
        catch(Exception e)
        {
          displayError("Invalid Quantities!\nPlease re-enter details\n");
          return;
        }
        prescription[x] = new Prescription(nameMed,typeMed,qty);
      }
    }
    
    //add consultation
    cS.addConsultation(nric, new DateTime(day,month,year,hour,min), illnessDesc, prescription, medDispensed);
    displayInformation("Consultation Added Successfully.");
    cS.saveFile();
        
    mainMenu();
  }
  
  private void restockMedicineSubmitHandler(){
    cS.restockMedicine(selectedMedicine.getName());
    displayInformation(selectedMedicine.getName()+" successfully restocked.");
    restockMedicine();
    cS.saveFile();    
  }
  
  private void searchSubmitHandler(){
    
    int dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo;
    DateTime from, to;
    
    String search = search_SearchConsultation.getText().trim();
    if(search.equals("")){ displayError("Empty Search Field!\nPlease enter keyword(s)\n"); return;}
    
    String type = (String)type_SearchConsultation.getSelectedItem();
    
    String dayFromS = (String)dayFrom_SearchConsultation.getSelectedItem();
    String monthFromS = (String)monthFrom_SearchConsultation.getSelectedItem();
    String yearFromS = (String)yearFrom_SearchConsultation.getSelectedItem();
   
    String dayToS = (String)dayTo_SearchConsultation.getSelectedItem();
    String monthToS = (String)monthTo_SearchConsultation.getSelectedItem();
    String yearToS = (String)yearTo_SearchConsultation.getSelectedItem();
    
    if(dayFromS.equals(""))
      dayFrom = 1;
    else
      dayFrom = Integer.parseInt(dayFromS);
    
    if(monthFromS.equals(""))
      monthFrom = 1;
    else
      monthFrom = Integer.parseInt(monthFromS);
    
    if(yearFromS.equals(""))
      from = null;
    else
    {
      yearFrom = Integer.parseInt(yearFromS);
      from = new DateTime(dayFrom, monthFrom, yearFrom);
    }
    
    if(dayToS.equals(""))
      dayTo = 1;
    else
      dayTo = Integer.parseInt(dayToS);
    
    if(monthToS.equals(""))
      monthTo = 1;
    else
      monthTo = Integer.parseInt(monthToS);
    
    if(yearToS.equals(""))
      to = null;
    else
    {
      yearTo = Integer.parseInt(yearToS);
      to = new DateTime(dayTo, monthTo, yearTo);
    }
    
    if(type.equals("Prescription")){
      cS.searchPrescription(search,from,to);
    }
    else if(type.equals("Illness")){
      cS.searchIllness(search,from,to);
    }
       
  }
  
  private void modifyMedicineSubmitHandler(){
    
    String name = name_DisplayMedicine.getText().trim();
    
    String supplier = supplier_DisplayMedicine.getText().trim();
    if(supplier.equals("")){ displayError("Empty Supplier Field!\nPlease enter details\n"); return;}
    
    String type = (String)type_DisplayMedicine.getSelectedItem();
    
    String quantityS = quantity_DisplayMedicine.getText().trim();
    if(quantityS.equals("")){ displayError("Empty Quantity Field!\nPlease enter details\n"); return;}
    
    String orderQuantityS = orderQuantity_DisplayMedicine.getText().trim();
    if(orderQuantityS.equals("")){ displayError("Empty Bulk Order Value Field!\nPlease enter details\n"); return;}
    
    String thresholdValueS = thresholdValue_DisplayMedicine.getText().trim();
    if(thresholdValueS.equals("")){ displayError("Empty Reorder Threshold Value Field!\nPlease enter details\n"); return;}
    
    int quantity;
    
    try{
      quantity = Integer.parseInt(quantityS);
    }
    catch(Exception e){
      displayError("Invalid Quantity Field!\nPlease re-enter details\n"); 
      return;  
    }
    
    int orderQuantity;
    
    try{
      orderQuantity = Integer.parseInt(orderQuantityS);
    }
    catch(Exception e){
      displayError("Invalid Bulk Order Value Field!\nPlease re-enter details\n"); 
      return;  
    }
    
    int thresholdValue;
    
    try{
      thresholdValue = Integer.parseInt(thresholdValueS);
    }
    catch(Exception e){
      displayError("Invalid Reorder Threshold Value Field!\nPlease re-enter details\n"); 
      return;  
    }
    
    //modifyMedicine
    selectedMedicine.setDetails(supplier, name, type, orderQuantity, thresholdValue, quantity);
    displayInformation("Medicine Edited Successfully.");
    cS.saveFile();    
    mainMenu();
  }
  
  private void prescribeMedicineSubmitHandler(){
    cS.prescribeMedicine(selectedConsultation);
    prescribeMedicine();
    cS.saveFile();    
  }
  
  private void modifyMedicineCancelHandler(){
    reportMedicine();
  }
  private void prescribeMedicineCancelHandler(){
    prescribeMedicine();
  }
  private void restockMedicineCancelHandler(){
    restockMedicine();
  }
  private void registerPatientCancelHandler(){
    mainMenu();
  }
  
  private void modifyPatientCancelHandler(){
    mainMenu();
  }
  private void addMedicineCancelHandler(){
    mainMenu();
  }
  private void addConsultationCancelHandler(){
    mainMenu();
  }
  private void displayConsultationDoneHandler(){
    mainMenu();
  }
  
  private void modifyPatientSelectHandler(){
    
    int row = patientsTable.getSelectedRow();
    if(row < 0) 
    {
      displayError("Please Select Patient!");
      return;
    }
    selectedPatient = selectedPatients[row];
    
    displayPatient(selectedPatient);
    
    nric_DisplayPatient.setEditable(false);
    
    //create modify patient extensions
    contentPane.setBorder(BorderFactory.createTitledBorder("Modify Patient Record"));
    
    submit_button_ModifyPatient = new JButton("Save Changes");
    cancel_button_ModifyPatient = new JButton("Cancel");
    
    submit_button_ModifyPatient.setBounds(20, 440, 120, 25);
    cancel_button_ModifyPatient.setBounds(150, 440, 80, 25);
    
    submit_button_ModifyPatient.addActionListener(this);
    cancel_button_ModifyPatient.addActionListener(this);
    
    contentPane.add(submit_button_ModifyPatient);
    contentPane.add(cancel_button_ModifyPatient);
    
    validate();
  }
  
  private void searchConsultationSelectHandler(){
    
    int row = patientsTable.getSelectedRow();
    
    if(row < 0) 
    {
      displayError("Please Select Patient!");
      return;
    }
    
    String nric = (String)patientsTable.getValueAt(row,0);
    
    Consultation [] c = cS.getConsultations(nric);
    displayConsultations(c);
    
  }
    
  private void createConsultationSelectHandler()
  {    
    int row = patientsTable.getSelectedRow();
 
    if(row < 0) 
    {
      displayError("Please Select Patient!");
      return;
    }
 
    String nric = (String)patientsTable.getValueAt(row,0);
    
    Calendar calendar = new GregorianCalendar();
    
    Consultation c = new Consultation(nric,new DateTime(calendar.get(Calendar.DAY_OF_MONTH),(calendar.get(Calendar.MONTH) + 1),calendar.get(Calendar.YEAR),calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE)),"",null,false);
    displayConsultation(c);
      
    nric_DisplayConsultation.setEditable(false);
    
    
    //create modify patient extensions
    contentPane.setBorder(BorderFactory.createTitledBorder("Create Consultation Record"));
    
    submit_button_CreateConsultation = new JButton("Save Changes");
    cancel_button_CreateConsultation = new JButton("Cancel");
    
    submit_button_CreateConsultation.setBounds(20, 530, 120, 25);
    cancel_button_CreateConsultation.setBounds(150, 530, 80, 25);
    
    submit_button_CreateConsultation.addActionListener(this);
    cancel_button_CreateConsultation.addActionListener(this);
    
    contentPane.add(submit_button_CreateConsultation);
    contentPane.add(cancel_button_CreateConsultation);
    
    validate();
  }
  
  private void prescribeMedicineSelectHandler()
  {
    int row = consultationTable.getSelectedRow();
    if(row < 0) 
    {
      displayError("Please Select Consultation!");
      return;
    }
    selectedConsultation = selectedConsultations[row];
    
    displayConsultation(selectedConsultation);
    
    nric_DisplayConsultation.setEnabled(false);
    day_DisplayConsultation.setEnabled(false);
    month_DisplayConsultation.setEnabled(false);
    year_DisplayConsultation.setEnabled(false);
    hour_DisplayConsultation.setEnabled(false);
    min_DisplayConsultation.setEnabled(false);
    illness_DisplayConsultation.setEnabled(false);
    prescription_bttn_DisplayConsultation.setEnabled(false);
    prescription_delete_bttn_DisplayConsultation.setEnabled(false);
    prescription_tble_DisplayConsultation.setEnabled(false);
    prescribed_DisplayConsultation.setVisible(false);
    prescribed_label_DisplayConsultation.setVisible(false);
        
    //save
    contentPane.setBorder(BorderFactory.createTitledBorder("Prescribe Medicine"));
    
    submit_button_PrescribeMedicine = new JButton("Prescribe Medicine");
    cancel_button_PrescribeMedicine = new JButton("Cancel");
    
    submit_button_PrescribeMedicine.setBounds(20, 510, 150, 25);
    cancel_button_PrescribeMedicine.setBounds(180, 510, 80, 25);
    
    submit_button_PrescribeMedicine.addActionListener(this);
    cancel_button_PrescribeMedicine.addActionListener(this);
    
    contentPane.add(submit_button_PrescribeMedicine);
    contentPane.add(cancel_button_PrescribeMedicine);
    
    validate();
  }
  
  private void displayConsultationSelectHandler(){
    int row = consultationTable.getSelectedRow();
    if(row < 0) 
    {
      displayError("Please Select Consultation!");
      return;
    }
    selectedConsultation = selectedConsultations[row];
    
    displayConsultation(selectedConsultation);
    
    nric_DisplayConsultation.setEnabled(false);
    day_DisplayConsultation.setEnabled(false);
    month_DisplayConsultation.setEnabled(false);
    year_DisplayConsultation.setEnabled(false);
    hour_DisplayConsultation.setEnabled(false);
    min_DisplayConsultation.setEnabled(false);
    illness_DisplayConsultation.setEnabled(false);
    prescription_bttn_DisplayConsultation.setEnabled(false);
    prescription_delete_bttn_DisplayConsultation.setEnabled(false);
    prescription_tble_DisplayConsultation.setEnabled(false);
    prescribed_DisplayConsultation.setEnabled(false);
        
    //save
    done_button_DisplayConsultation = new JButton("Done");
    
    done_button_DisplayConsultation.setBounds(20, 530, 150, 25);
    
    done_button_DisplayConsultation.addActionListener(this);
    
    contentPane.add(done_button_DisplayConsultation);
    
    validate();
  }
  
  private void reportMedicineSelectHandler()
  {
    int row = medicineTable.getSelectedRow();
    if(row < 0) 
    {
      displayError("Please Select Medicine!");
      return;
    }
    selectedMedicine = selectedMedicines[row];
    
    displayMedicine(selectedMedicine);
    
    name_DisplayMedicine.setEnabled(false);
 
    
    //save
    contentPane.setBorder(BorderFactory.createTitledBorder("Modify Medicine"));
    
    submit_button_ModifyMedicine = new JButton("Save Changes");
    cancel_button_ModifyMedicine = new JButton("Cancel");
    
    submit_button_ModifyMedicine.setBounds(20, 390, 120, 25);
    cancel_button_ModifyMedicine.setBounds(150, 390, 80, 25);
    
    submit_button_ModifyMedicine.addActionListener(this);
    cancel_button_ModifyMedicine.addActionListener(this);
    
    contentPane.add(submit_button_ModifyMedicine);
    contentPane.add(cancel_button_ModifyMedicine);
    
    validate();
  }
  
  
  private void restockMedicineSelectHandler()
  {
    int row = medicineTable.getSelectedRow();
    if(row < 0) 
    {
      displayError("Please Select Medicine!");
      return;
    }
    selectedMedicine = selectedMedicines[row];
    
    displayMedicine(selectedMedicine);
    
    name_DisplayMedicine.setEnabled(false);
    supplier_DisplayMedicine.setEnabled(false);
    type_DisplayMedicine.setEnabled(false);
    quantity_DisplayMedicine.setEnabled(false);
    thresholdValue_DisplayMedicine.setEnabled(false);
    orderQuantity_DisplayMedicine.setEnabled(false);
  
    //save
    contentPane.setBorder(BorderFactory.createTitledBorder("Restock Medicine"));
    
    submit_button_RestockMedicine = new JButton("Restock Medicine");
    cancel_button_RestockMedicine = new JButton("Cancel");
    
    submit_button_RestockMedicine.setBounds(20, 390, 150, 25);
    cancel_button_RestockMedicine.setBounds(180, 390, 80, 25);
    
    submit_button_RestockMedicine.addActionListener(this);
    cancel_button_RestockMedicine.addActionListener(this);
    
    contentPane.add(submit_button_RestockMedicine);
    contentPane.add(cancel_button_RestockMedicine);
    
    validate();
  }
  
  private void addPrescriptionHandler(){
    Object[] s = {"",""};
    ((DefaultTableModel)prescription_tble_DisplayConsultation.getModel()).addRow(s);
  }
  
  private void removePrescriptionHandler(){
    int row = prescription_tble_DisplayConsultation.getSelectedRow();
    if(row < 0) 
    {
      displayError("Please Select Prescription to remove!");
      return;
    }
    else
    {
      if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + prescription_tble_DisplayConsultation.getValueAt(row,0) + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        ((DefaultTableModel)prescription_tble_DisplayConsultation.getModel()).removeRow(row);
    }
    
  }
  
  public void registerPatient(){
    
    state = REGISTER_PATIENT;
    
    displayPatient(null);
    
    contentPane.setBorder(BorderFactory.createTitledBorder("Register Patient"));
    submit_button_RegisterPatient = new JButton("Register");
    cancel_button_RegisterPatient = new JButton("Cancel");
    
    submit_button_RegisterPatient.setBounds(20, 440, 120, 25);
    cancel_button_RegisterPatient.setBounds(150, 440, 80, 25);
    
    submit_button_RegisterPatient.addActionListener(this);
    cancel_button_RegisterPatient.addActionListener(this);
    
    contentPane.add(submit_button_RegisterPatient);
    contentPane.add(cancel_button_RegisterPatient);
    
    //add pane to window
    windowPane.add(contentPane, BorderLayout.CENTER);
                          
    //redraw
    validate();    
  }


  public void modifyPatient(){
    
    state = MODIFY_PATIENT;
    
    String keyword = JOptionPane.showInputDialog(null,"Enter NRIC or Name", "Patient Search",JOptionPane.QUESTION_MESSAGE);

    if(keyword == null) return;
    cS.searchPatient(keyword);

  }

  public void displayPatient(Patient patient){

    if(contentPane != null)windowPane.remove(contentPane);
    contentPane = new JPanel();
    contentPane.setLayout(null);
    
    //create border with title
    contentPane.setBorder(BorderFactory.createTitledBorder("Display Patient Record"));
      
    //create contents
    nric_label_DisplayPatient = new JLabel("NRIC");
    nric_DisplayPatient = new JTextField("");
    
    name_label_DisplayPatient = new JLabel("Name");
    name_DisplayPatient = new JTextField("");
    
    gender_label_DisplayPatient = new JLabel("Gender");
    gender_DisplayPatient = new JComboBox();
    
    dob_label_DisplayPatient = new JLabel("Date Of Birth (DD/MM/YYYY)");
    day_DisplayPatient = new JComboBox();
    month_DisplayPatient = new JComboBox();
    year_DisplayPatient = new JComboBox();
    
    address_label_DisplayPatient = new JLabel("Address");
    address_DisplayPatient = new JTextArea("");
    
    phone_label_DisplayPatient = new JLabel("Phone");
    phone_DisplayPatient = new JTextField("");
    
    //set border
    nric_DisplayPatient.setBorder(BorderFactory.createLoweredBevelBorder());
    name_DisplayPatient.setBorder(BorderFactory.createLoweredBevelBorder());
    gender_DisplayPatient.setBorder(BorderFactory.createLoweredBevelBorder());
    day_DisplayPatient.setBorder(BorderFactory.createLoweredBevelBorder());
    month_DisplayPatient.setBorder(BorderFactory.createLoweredBevelBorder());
    year_DisplayPatient.setBorder(BorderFactory.createLoweredBevelBorder());
    phone_DisplayPatient.setBorder(BorderFactory.createLoweredBevelBorder());
    
    //set other miscellaneous behaviour
    gender_DisplayPatient.addItem("Male");
    gender_DisplayPatient.addItem("Female");
    gender_DisplayPatient.setEditable(false);
    gender_DisplayPatient.setBackground(Color.WHITE);
    
    
    for(int x = 1; x <= 31; x++)
      day_DisplayPatient.addItem(""+x);
    
    day_DisplayPatient.setEditable(false);
    day_DisplayPatient.setBackground(Color.WHITE);
    
    for(int x = 1; x <= 12; x++)
      month_DisplayPatient.addItem(""+x);
    
    month_DisplayPatient.setEditable(false);
    month_DisplayPatient.setBackground(Color.WHITE);
    
    for(int x = 1900; x <= 2010; x++)
      year_DisplayPatient.addItem(""+x);
    
    year_DisplayPatient.setEditable(false);
    year_DisplayPatient.setBackground(Color.WHITE);
    
    address_DisplayPatient.setRows(4);
    address_DisplayPatient.setLineWrap(true);
    address_DisplayPatient.setWrapStyleWord(true);
    address_DisplayPatient.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
    
    JScrollPane scrollPane = new JScrollPane(address_DisplayPatient);
    scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
    
    //set content positions
    nric_label_DisplayPatient.setBounds(20, 30, 200, 20);
    nric_DisplayPatient.setBounds(20, 50, 300, 25);
    
    name_label_DisplayPatient.setBounds(20, 90, 200, 20);
    name_DisplayPatient.setBounds(20, 110, 300, 25);
    
    gender_label_DisplayPatient.setBounds(20, 150, 200, 20);
    gender_DisplayPatient.setBounds(20, 170, 300, 25);
    
    dob_label_DisplayPatient.setBounds(20, 210, 200, 20);
    day_DisplayPatient.setBounds(20, 230, 50, 25);
    month_DisplayPatient.setBounds(80, 230, 50, 25);
    year_DisplayPatient.setBounds(140, 230, 100, 25);
    
    address_label_DisplayPatient.setBounds(20, 270, 200, 20);
    scrollPane.setPreferredSize(new Dimension(300,75));
    scrollPane.setBounds(20,290,300,75);
    
    phone_label_DisplayPatient.setBounds(20, 380, 200, 20);
    phone_DisplayPatient.setBounds(20, 400, 300, 25);
    
    //add content to pane
    contentPane.add(nric_label_DisplayPatient);
    contentPane.add(nric_DisplayPatient);
    
    contentPane.add(name_label_DisplayPatient);
    contentPane.add(name_DisplayPatient);
    
    contentPane.add(gender_label_DisplayPatient);
    contentPane.add(gender_DisplayPatient);
    
    contentPane.add(dob_label_DisplayPatient);
    contentPane.add(day_DisplayPatient);
    contentPane.add(month_DisplayPatient);
    contentPane.add(year_DisplayPatient);
    
    contentPane.add(address_label_DisplayPatient);
    contentPane.add(scrollPane);
    
    contentPane.add(phone_label_DisplayPatient);
    contentPane.add(phone_DisplayPatient);
    
    if(patient != null)
    {
      nric_DisplayPatient.setText(patient.getIC());
      name_DisplayPatient.setText(patient.getName());
      gender_DisplayPatient.setSelectedItem(patient.getGender());
      day_DisplayPatient.setSelectedItem(""+patient.getDob().getDay());
      month_DisplayPatient.setSelectedItem((""+patient.getDob().getMonth()));
      year_DisplayPatient.setSelectedItem((""+patient.getDob().getYear()));
      address_DisplayPatient.setText(patient.getAddress());
      phone_DisplayPatient.setText(""+patient.getPhone());
    }
    //add pane to window
    windowPane.add(contentPane, BorderLayout.CENTER);
                          
    //redraw
    validate();
  }

  private Object[] patientToArray(Patient patient){
    Object[] p = {patient.getIC(), patient.getName(), patient.getGender(), patient.getDob().getDate(), patient.getAddress(), patient.getPhone()};
    return p;
  }
  
  public void displayPatients(Patient [] patient){
    
    if(contentPane != null) windowPane.remove(contentPane);
    contentPane = new JPanel();
      
    //create border with title
    contentPane.setBorder(BorderFactory.createTitledBorder("View Patient Records"));
    if(patient != null && patient.length > 0 && patient[0] instanceof Patient){
    
      contentPane.setLayout(new FlowLayout());
    
      String [] colNames = {"NRIC","Name","Gender","Date of Birth","Address","Phone"};
      
      //save displayed patients
      selectedPatients = patient;
      
      
      DefaultTableModel model = new DefaultTableModel(colNames,0){
        public boolean isCellEditable(int rowIndex, int colIndex) {
          return false;
        }
      };
      
      patientsTable = new JTable(model);
      
      for(int i = 0; i < patient.length && patient[i] instanceof Patient; i++)
        model.addRow(patientToArray(patient[i]));
      
      patientsTable.getColumnModel().getColumn(4).setCellRenderer(new TextAreaRenderer());
      
      patientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      patientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      patientsTable.setRowSelectionAllowed(true);
      patientsTable.setColumnSelectionAllowed(false);
      
      
      JScrollPane scrollPane = new JScrollPane(patientsTable);
      scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
      scrollPane.setPreferredSize(new Dimension(580,500));
      
      selectButton_SelectPatient = new JButton("Select Patient");
      selectButton_SelectPatient.addActionListener(this);
      
      contentPane.add(scrollPane);
      contentPane.add(selectButton_SelectPatient);
      
      if(state == SEARCH_PRESCRIPTION || state == SEARCH_PRESCRIPTION){
        printButton_SelectPatient = new JButton("Print Report");
        printButton_SelectPatient.addActionListener(this);
        contentPane.add(printButton_SelectPatient);
      }
      
    }
    else
    {
      contentPane.setLayout(new FlowLayout());
      JLabel error = new JLabel("No Patient Records to Display");
      contentPane.add(error);
    }
    
    //add pane to window
    windowPane.add(contentPane, BorderLayout.CENTER);
                          
    //redraw
    validate();

  }


  public void displayAllPatients(){

     Patient [] p = cS.displayAllPatients();
     displayPatients(p);
     
  }
  
  public void addConsultation(){
    
    state = CREATE_CONSULTATION;
    
    String keyword = JOptionPane.showInputDialog(null,"Enter NRIC or Name", "Patient Search",JOptionPane.QUESTION_MESSAGE);

    if(keyword == null) return;
    cS.searchPatient(keyword);
  
  }
  
  public void displayConsultation(Consultation consultation){
    
    if(contentPane != null)windowPane.remove(contentPane);

    contentPane = new JPanel();
    contentPane.setLayout(null);
    
    //create border with title
    contentPane.setBorder(BorderFactory.createTitledBorder("Display Consultation Record"));
    
    //create contents
    nric_label_DisplayConsultation = new JLabel("NRIC");
    nric_DisplayConsultation = new JTextField("");
    date_label_DisplayConsultation = new JLabel("Date (DD/MM/YYYY)");
    day_DisplayConsultation = new JComboBox();
    month_DisplayConsultation = new JComboBox();
    year_DisplayConsultation = new JComboBox();
    time_label_DisplayConsultation = new JLabel("Time (HH : MM)");
    hour_DisplayConsultation = new JComboBox();
    min_DisplayConsultation = new JComboBox();
    prescribed_label_DisplayConsultation = new JLabel("Prescribed");
    prescribed_DisplayConsultation = new JCheckBox();

    illness_label_DisplayConsultation = new JLabel("Illness Description");
    illness_DisplayConsultation = new JTextArea();
   
    prescription_label_DisplayConsultation = new JLabel("Prescription");
    prescription_bttn_DisplayConsultation = new JButton("Add New Prescription");
    prescription_delete_bttn_DisplayConsultation = new JButton("Delete");
    
    String [] colNames = {"Name","Quantity"};

    DefaultTableModel model = new DefaultTableModel(null,colNames);
    prescription_tble_DisplayConsultation = new JTable(model);
    
    
    //set table attributes
    prescription_tble_DisplayConsultation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    prescription_tble_DisplayConsultation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    prescription_tble_DisplayConsultation.setRowSelectionAllowed(true);
    prescription_tble_DisplayConsultation.setColumnSelectionAllowed(false);
    
    JComboBox prescription_name_DisplayConsultation = new JComboBox();
    Medicine [] medicine = cS.displayAllMedicine();
    if(medicine != null)
    {
      for(int x=0; x< medicine.length; x++)
        prescription_name_DisplayConsultation.addItem(medicine[x].getName()+"("+medicine[x].getType()+")");
    }
    
    TableColumn name = prescription_tble_DisplayConsultation.getColumn("Name");
    name.setCellEditor(new DefaultCellEditor(prescription_name_DisplayConsultation));
    
    illness_DisplayConsultation.setRows(4);
    illness_DisplayConsultation.setLineWrap(true);
    illness_DisplayConsultation.setWrapStyleWord(true);
    illness_DisplayConsultation.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
    
    JScrollPane scrollPaneIllness = new JScrollPane(illness_DisplayConsultation);
    scrollPaneIllness.setBorder(BorderFactory.createLoweredBevelBorder());
    
    JScrollPane scrollPanePrescription = new JScrollPane(prescription_tble_DisplayConsultation);
    scrollPanePrescription.setBorder(BorderFactory.createLoweredBevelBorder());
    
   
    for(int x = 1; x <= 31; x++)
      day_DisplayConsultation.addItem(""+x);
    
    day_DisplayConsultation.setEditable(false);
    day_DisplayConsultation.setBackground(Color.WHITE);
   
    for(int x = 1; x <= 12; x++)
      month_DisplayConsultation.addItem(""+x);
    
    month_DisplayConsultation.setEditable(false);
    month_DisplayConsultation.setBackground(Color.WHITE);
    
    for(int x = 1900; x <= 2010; x++)
      year_DisplayConsultation.addItem(""+x);
    
    year_DisplayConsultation.setEditable(false);
    year_DisplayConsultation.setBackground(Color.WHITE);
    
    for(int x = 1; x <= 24; x++)
      hour_DisplayConsultation.addItem(""+x);
    
    hour_DisplayConsultation.setEditable(false);
    hour_DisplayConsultation.setBackground(Color.WHITE);
    
    for(int x = 0; x <= 59; x++)
      min_DisplayConsultation.addItem(""+x);
    
    min_DisplayConsultation.setEditable(false);
    min_DisplayConsultation.setBackground(Color.WHITE);
    
    
    //set content positions
    nric_label_DisplayConsultation.setBounds(20, 30, 200, 20);
    nric_DisplayConsultation.setBounds(20, 50, 300, 25);
    
    date_label_DisplayConsultation.setBounds(20, 90, 200, 20);
    day_DisplayConsultation.setBounds(20, 110, 100, 25);
    month_DisplayConsultation.setBounds(120, 110, 100, 25);
    year_DisplayConsultation.setBounds(220, 110, 100, 25);
    
    time_label_DisplayConsultation.setBounds(20, 150, 200, 20);
    hour_DisplayConsultation.setBounds(20, 170, 100, 25);
    min_DisplayConsultation.setBounds(120, 170, 100, 25);
    
    illness_label_DisplayConsultation.setBounds(20, 220, 200, 20);
    scrollPaneIllness.setPreferredSize(new Dimension(300,75));
    scrollPaneIllness.setBounds(20,240,300,75);
    
    prescription_label_DisplayConsultation.setBounds(20, 340, 200, 20);
    
    scrollPanePrescription.setPreferredSize(new Dimension(300,75));
    scrollPanePrescription.setBounds(20,360,300,75);
    
    prescription_bttn_DisplayConsultation.setBounds(20, 450, 200, 25);
    prescription_bttn_DisplayConsultation.addActionListener(this);
    
    prescription_delete_bttn_DisplayConsultation.setBounds(230, 450, 90, 25);
    prescription_delete_bttn_DisplayConsultation.addActionListener(this);
      
    prescribed_label_DisplayConsultation.setBounds(20, 490, 100, 25);
    prescribed_DisplayConsultation.setBounds(120, 490, 25, 25);
    
    
    //add to panel
    contentPane.add(nric_label_DisplayConsultation);
    contentPane.add(nric_DisplayConsultation);
    contentPane.add(date_label_DisplayConsultation);
    contentPane.add(day_DisplayConsultation);
    contentPane.add(month_DisplayConsultation);
    contentPane.add(year_DisplayConsultation);
    contentPane.add(time_label_DisplayConsultation);
    contentPane.add(hour_DisplayConsultation);
    contentPane.add(min_DisplayConsultation);
    contentPane.add(illness_label_DisplayConsultation);
    contentPane.add(scrollPaneIllness);
    contentPane.add(prescription_label_DisplayConsultation);
    contentPane.add(prescription_bttn_DisplayConsultation);
    contentPane.add(prescription_delete_bttn_DisplayConsultation);
    contentPane.add(prescribed_label_DisplayConsultation);
    contentPane.add(prescribed_DisplayConsultation);
    contentPane.add(scrollPanePrescription);
    windowPane.add(contentPane, BorderLayout.CENTER);
    
    if(consultation != null)
    {
      nric_DisplayConsultation.setText(consultation.getIC());
      day_DisplayConsultation.setSelectedItem(""+consultation.getDateTime().getDay());
      month_DisplayConsultation.setSelectedItem((""+consultation.getDateTime().getMonth()));
      year_DisplayConsultation.setSelectedItem((""+consultation.getDateTime().getYear()));
      hour_DisplayConsultation.setSelectedItem((""+consultation.getDateTime().getHour()));
      min_DisplayConsultation.setSelectedItem((""+consultation.getDateTime().getMin()));
      illness_DisplayConsultation.setText(consultation.getIllnessDesc());
      
      Prescription [] p = consultation.getPrescription();
      if(p != null){
        for(int x = 0; x < p.length; x++)
        {
          Object [] r = {p[x].medicineName+"("+p[x].medicineType+")",p[x].qty};
          ((DefaultTableModel)prescription_tble_DisplayConsultation.getModel()).addRow(r);
        }
      }
      prescribed_DisplayConsultation.setSelected(consultation.getDispensed());
    }
   
    validate();
  }
  
  
  private Object[] consultationToArray(Consultation consultation){
    
    Patient patient = cS.getPatient(consultation.getIC());
    
    Object[] c = {
      consultation.getIC(), 
      patient.getName(),
      consultation.getDateTime().getDate(), 
      consultation.getDateTime().getTime(), 
      consultation.getIllnessDesc(), 
      consultation.getPrescriptionDesc(),
      (boolean)consultation.getDispensed()
    };
    return c;
  }
  
  public void displayConsultations(Consultation [] consultation){
    
    
    if(contentPane != null) windowPane.remove(contentPane);
    contentPane = new JPanel();
      
    //create border with title
    contentPane.setBorder(BorderFactory.createTitledBorder("View Consultation Records"));
    if(consultation != null && consultation.length > 0 && consultation[0] instanceof Consultation){
    
      contentPane.setLayout(new FlowLayout());
    
      String [] colNames = {"NRIC","Name","Date","Time","Illness Description","Prescription","Medicine Dispensed"};
            
      //save displayed consultations
      selectedConsultations = consultation;
      
      DefaultTableModel model = new DefaultTableModel(colNames,0){
        public boolean isCellEditable(int rowIndex, int colIndex) {
          return false;
        }
      };
      consultationTable = new JTable(model);
      
      for(int i = 0; i < consultation.length && consultation[i] instanceof Consultation; i++)
        model.addRow(consultationToArray(consultation[i]));
      
      consultationTable.getColumnModel().getColumn(5).setCellRenderer(new TextAreaRenderer());


      consultationTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      consultationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      consultationTable.setRowSelectionAllowed(true);
      consultationTable.setColumnSelectionAllowed(false);
      
      
      JScrollPane scrollPane = new JScrollPane(consultationTable);
      scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
      scrollPane.setPreferredSize(new Dimension(580,500));
      
      selectButton_SelectConsultation = new JButton("Select Consultation");
      selectButton_SelectConsultation.addActionListener(this);
      
      contentPane.add(scrollPane);
      contentPane.add(selectButton_SelectConsultation);
      
      
    }
    else
    {
      contentPane.setLayout(new FlowLayout());
      JLabel error = new JLabel("No Consultation Records to Display");
      contentPane.add(error);
    }
    
    //add pane to window
    windowPane.add(contentPane, BorderLayout.CENTER);
                          
    //redraw
    validate();
    
  }

  public void addMedicine(){
    
    state = ADD_MEDICINE;
    
    displayMedicine(null);
    
    contentPane.setBorder(BorderFactory.createTitledBorder("Add Medicine"));
    submit_button_AddMedicine = new JButton("Add Medicine");
    cancel_button_AddMedicine = new JButton("Cancel");
    
    submit_button_AddMedicine.setBounds(20, 390, 120, 25);
    cancel_button_AddMedicine.setBounds(150, 390, 80, 25);
    
    submit_button_AddMedicine.addActionListener(this);
    cancel_button_AddMedicine.addActionListener(this);
    
    contentPane.add(submit_button_AddMedicine);
    contentPane.add(cancel_button_AddMedicine);
    
    //add pane to window
    windowPane.add(contentPane, BorderLayout.CENTER);
                          
    //redraw
    validate();    
  }
  
  public void displayMedicine(Medicine medicine){
    
     if(contentPane != null)windowPane.remove(contentPane);
    contentPane = new JPanel();
    contentPane.setLayout(null);
    
    //create border with title
    contentPane.setBorder(BorderFactory.createTitledBorder("Display Medicine"));
      
    //create contents
    name_label_DisplayMedicine = new JLabel("Medicine Name");
    name_DisplayMedicine = new JTextField("");
    
    supplier_label_DisplayMedicine = new JLabel("Supplier");
    supplier_DisplayMedicine = new JTextField("");
    
    type_label_DisplayMedicine = new JLabel("Type");
    type_DisplayMedicine = new JComboBox();
    
    quantity_label_DisplayMedicine = new JLabel("Quantity");
    quantity_DisplayMedicine = new JTextField();
    
    orderQuantity_label_DisplayMedicine = new JLabel("Bulk Order Quantity");
    orderQuantity_DisplayMedicine = new JTextField();
    
    thresholdValue_label_DisplayMedicine = new JLabel("Reorder Threshold Value");
    thresholdValue_DisplayMedicine = new JTextField();
    
    
    //set border
    name_DisplayMedicine.setBorder(BorderFactory.createLoweredBevelBorder());
    supplier_DisplayMedicine.setBorder(BorderFactory.createLoweredBevelBorder());
    type_DisplayMedicine.setBorder(BorderFactory.createLoweredBevelBorder());
    quantity_DisplayMedicine.setBorder(BorderFactory.createLoweredBevelBorder());
    orderQuantity_DisplayMedicine.setBorder(BorderFactory.createLoweredBevelBorder());
    thresholdValue_DisplayMedicine.setBorder(BorderFactory.createLoweredBevelBorder());
    
    //set other miscellaneous behaviour
    type_DisplayMedicine.addItem("Pill");
    type_DisplayMedicine.addItem("Liquid");
    type_DisplayMedicine.setEditable(false);
    type_DisplayMedicine.setBackground(Color.WHITE);
   
    //set content positions
    name_label_DisplayMedicine.setBounds(20, 30, 200, 20);
    name_DisplayMedicine.setBounds(20, 50, 300, 25);
    
    supplier_label_DisplayMedicine.setBounds(20, 90, 200, 20);
    supplier_DisplayMedicine.setBounds(20, 110, 300, 25);
    
    type_label_DisplayMedicine.setBounds(20, 150, 200, 20);
    type_DisplayMedicine.setBounds(20, 170, 300, 25);
    
    quantity_label_DisplayMedicine.setBounds(20, 210, 200, 20);
    quantity_DisplayMedicine.setBounds(20, 230, 300, 25);
    
    orderQuantity_label_DisplayMedicine.setBounds(20, 270, 200, 20);
    orderQuantity_DisplayMedicine.setBounds(20, 290, 300, 25);
    
    thresholdValue_label_DisplayMedicine.setBounds(20, 330, 200, 20);
    thresholdValue_DisplayMedicine.setBounds(20, 350, 300, 25);
    
    //add content to pane
    contentPane.add(name_label_DisplayMedicine);
    contentPane.add(name_DisplayMedicine);
    
    contentPane.add(supplier_label_DisplayMedicine);
    contentPane.add(supplier_DisplayMedicine);
    
    contentPane.add(type_label_DisplayMedicine);
    contentPane.add(type_DisplayMedicine);
    
    contentPane.add(quantity_label_DisplayMedicine);
    contentPane.add(quantity_DisplayMedicine);
    
    contentPane.add(orderQuantity_label_DisplayMedicine);
    contentPane.add(orderQuantity_DisplayMedicine);
    
    contentPane.add(thresholdValue_label_DisplayMedicine);
    contentPane.add(thresholdValue_DisplayMedicine);
    
    if(medicine != null)
    {
      name_DisplayMedicine.setText(medicine.getName());
      supplier_DisplayMedicine.setText(medicine.getSupplier());
      type_DisplayMedicine.setSelectedItem(medicine.getType());
      quantity_DisplayMedicine.setText(""+medicine.getQty());
      orderQuantity_DisplayMedicine.setText(""+medicine.getBulkOrder());
      thresholdValue_DisplayMedicine.setText(""+medicine.getThreshold());
    }
    //add pane to window
    windowPane.add(contentPane, BorderLayout.CENTER);
                          
    //redraw
    validate();
  }
  
  private Object[] medicineToArray(Medicine medicine){
    Object[] m = {
      medicine.getName(), 
      medicine.getSupplier(), 
      medicine.getType(), 
      medicine.getQty(), 
      medicine.getBulkOrder(),
      medicine.getThreshold()
    };
    return m;
  }

  public void displayMedicines(Medicine [] medicine){
    
    if(contentPane != null) windowPane.remove(contentPane);
    contentPane = new JPanel();
      
    //create border with title
    contentPane.setBorder(BorderFactory.createTitledBorder("View Medicine Records"));
    if(medicine != null && medicine.length > 0 && medicine[0] instanceof Medicine){
    
      contentPane.setLayout(new FlowLayout());

      String [] colNames = {"Name","Supplier","Type","Quantity","Bulk Order","Reorder Threshold"};
          
      //save displayed medicines
      selectedMedicines = medicine;
      
      DefaultTableModel model = new DefaultTableModel(colNames, 0){
        public boolean isCellEditable(int rowIndex, int colIndex) {
          return false;
        }
      };
      
      medicineTable = new JTable(model);
      
      for(int i = 0; i < medicine.length && medicine[i] instanceof Medicine; i++)
      {
        model.addRow(medicineToArray(medicine[i]));
      }
        
  
      medicineTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      medicineTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      medicineTable.setRowSelectionAllowed(true);
      medicineTable.setColumnSelectionAllowed(false);
      
      
      JScrollPane scrollPane = new JScrollPane(medicineTable);
      scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
      scrollPane.setPreferredSize(new Dimension(580,500));
      
      selectButton_SelectMedicine = new JButton("Select Medicine");
      selectButton_SelectMedicine.addActionListener(this);
      
      contentPane.add(scrollPane);
      contentPane.add(selectButton_SelectMedicine);
      
    }
    else
    {
      contentPane.setLayout(new FlowLayout());
      JLabel error = new JLabel("No Medicine Records to Display");
      contentPane.add(error);
    }
    
    //add pane to window
    windowPane.add(contentPane, BorderLayout.CENTER);
                          
    //redraw
    validate();
  }
  
  public void prescribeMedicine(){
    
    state = PRESCRIBE_MEDICINE;
    Consultation [] c = cS.getUnprescribed();
    displayConsultations(c);
  }
  
  public void reportMedicine(){
    state = REPORT_MEDICINE;
    cS.reportMedicine();
  }
  
  public void restockMedicine(){
    state = RESTOCK_MEDICINE;
    cS.reportMedicine();
  }
  
  public void searchPrescription(){
    state = SEARCH_PRESCRIPTION;
    search("Prescription");
  }
  
  public void searchIllness(){
    state = SEARCH_ILLNESS;
    search("Illness");
  }
  
  public void search(String searchType){
   
    if(contentPane != null)windowPane.remove(contentPane);
    contentPane = new JPanel();
    contentPane.setLayout(null);
    
    //create border with title
    contentPane.setBorder(BorderFactory.createTitledBorder("Search Consultation Records"));
      
    //create contents
    search_label_SearchConsultation = new JLabel("Search");
    search_SearchConsultation = new JTextField("");
    
    type_label_SearchConsultation = new JLabel("Search Type");
    type_SearchConsultation = new JComboBox();
    
    dateFrom_label_SearchConsultation = new JLabel("Search Records From");
    dayFrom_SearchConsultation = new JComboBox();
    monthFrom_SearchConsultation = new JComboBox();
    yearFrom_SearchConsultation = new JComboBox();
    
    dateTo_label_SearchConsultation = new JLabel("To");
    dayTo_SearchConsultation = new JComboBox();
    monthTo_SearchConsultation = new JComboBox();
    yearTo_SearchConsultation = new JComboBox();
    
    searchBtn_SearchConsultation = new JButton("Search");
    
    //set border
    search_SearchConsultation.setBorder(BorderFactory.createLoweredBevelBorder());
    dayFrom_SearchConsultation.setBorder(BorderFactory.createLoweredBevelBorder());
    monthFrom_SearchConsultation.setBorder(BorderFactory.createLoweredBevelBorder());
    yearFrom_SearchConsultation.setBorder(BorderFactory.createLoweredBevelBorder());
    dayTo_SearchConsultation.setBorder(BorderFactory.createLoweredBevelBorder());
    monthTo_SearchConsultation.setBorder(BorderFactory.createLoweredBevelBorder());
    yearTo_SearchConsultation.setBorder(BorderFactory.createLoweredBevelBorder());
    type_SearchConsultation.setBorder(BorderFactory.createLoweredBevelBorder());
    
    //set other miscellaneous behaviour
    dayFrom_SearchConsultation.addItem("");
    for(int x = 1; x <= 31; x++)
      dayFrom_SearchConsultation.addItem(""+x);
    
    dayFrom_SearchConsultation.setEditable(false);
    dayFrom_SearchConsultation.setBackground(Color.WHITE);
    
    monthFrom_SearchConsultation.addItem("");
    for(int x = 1; x <= 12; x++)
      monthFrom_SearchConsultation.addItem(""+x);
    
    monthFrom_SearchConsultation.setEditable(false);
    monthFrom_SearchConsultation.setBackground(Color.WHITE);
    
    yearFrom_SearchConsultation.addItem("");
    for(int x = 1900; x <= 2010; x++)
      yearFrom_SearchConsultation.addItem(""+x);
    
    yearFrom_SearchConsultation.setEditable(false);
    yearFrom_SearchConsultation.setBackground(Color.WHITE);
    
    dayTo_SearchConsultation.addItem("");
    for(int x = 1; x <= 31; x++)
      dayTo_SearchConsultation.addItem(""+x);
    
    dayTo_SearchConsultation.setEditable(false);
    dayTo_SearchConsultation.setBackground(Color.WHITE);
    
    monthTo_SearchConsultation.addItem("");
    for(int x = 1; x <= 12; x++)
      monthTo_SearchConsultation.addItem(""+x);
    
    monthTo_SearchConsultation.setEditable(false);
    monthTo_SearchConsultation.setBackground(Color.WHITE);
    
    yearTo_SearchConsultation.addItem("");
    for(int x = 1900; x <= 2010; x++)
      yearTo_SearchConsultation.addItem(""+x);
    
    yearTo_SearchConsultation.setEditable(false);
    yearTo_SearchConsultation.setBackground(Color.WHITE);
    
    type_SearchConsultation.addItem("Prescription");
    type_SearchConsultation.addItem("Illness");
    type_SearchConsultation.setEditable(false);
    type_SearchConsultation.setBackground(Color.WHITE);
    
    //set content positions
    search_label_SearchConsultation.setBounds(20, 30, 200, 20);
    search_SearchConsultation.setBounds(20, 50, 300, 25);
    
    type_label_SearchConsultation.setBounds(20,90, 200, 20);
    type_SearchConsultation.setBounds(20,110,100,25);
    
    dateFrom_label_SearchConsultation.setBounds(20, 150, 200, 20);
    dayFrom_SearchConsultation.setBounds(20, 170, 50, 25);
    monthFrom_SearchConsultation.setBounds(80, 170, 50, 25);
    yearFrom_SearchConsultation.setBounds(140, 170, 100, 25);
    
    dateTo_label_SearchConsultation.setBounds(20, 200, 200, 20);
    dayTo_SearchConsultation.setBounds(20, 220, 50, 25);
    monthTo_SearchConsultation.setBounds(80, 220, 50, 25);
    yearTo_SearchConsultation.setBounds(140, 220, 100, 25);
    
    searchBtn_SearchConsultation.setBounds(20, 260, 80, 25);
    searchBtn_SearchConsultation.addActionListener(this);
      
    //add content to pane
    contentPane.add(search_label_SearchConsultation);
    contentPane.add(search_SearchConsultation);
    contentPane.add(type_label_SearchConsultation);
    contentPane.add(type_SearchConsultation);
    contentPane.add(dateFrom_label_SearchConsultation);
    contentPane.add(dayFrom_SearchConsultation);
    contentPane.add(monthFrom_SearchConsultation);
    contentPane.add(yearFrom_SearchConsultation);
    contentPane.add(dateTo_label_SearchConsultation);
    contentPane.add(dayTo_SearchConsultation);
    contentPane.add(monthTo_SearchConsultation);
    contentPane.add(yearTo_SearchConsultation);
    contentPane.add(searchBtn_SearchConsultation);
    
    if(searchType != null)
    {
      type_SearchConsultation.setSelectedItem(searchType);
    }
    //add pane to window
    windowPane.add(contentPane, BorderLayout.CENTER);
                          
    //redraw
    validate(); 
  }
  public void displayError(String message){
    
    JOptionPane.showMessageDialog(null, message,"Error",JOptionPane.ERROR_MESSAGE);
  }

  public void displayInformation(String message){
    
    JOptionPane.showMessageDialog(null, message,"Information",JOptionPane.INFORMATION_MESSAGE);
  }
}