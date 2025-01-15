package application;

public class specialistDatabase {

   private String cin;
   private String firstName;
   private String lastName;
   private String specialistNum;
   private String service;

   // Constructor to initialize the attributes
   public specialistDatabase(String cin, String firstName, String lastName, String specialistNum, String service) {
       this.cin = cin;
       this.firstName = firstName;
       this.lastName = lastName;
       this.specialistNum = specialistNum;
       this.service = service;
   }

   // Getters for each attribute
   public String getCin() {
       return cin;
   }

   public String getFirstName() {
       return firstName;
   }

   public String getLastName() {
       return lastName;
   }

   public String getSpecialistNum() {
       return specialistNum;
   }

   public String getService() {
       return service;
   }

   // Setters for each attribute (if needed)
   public void setCin(String cin) {
       this.cin = cin;
   }

   public void setFirstName(String firstName) {
       this.firstName = firstName;
   }

   public void setLastName(String lastName) {
       this.lastName = lastName;
   }

   public void setSpecialistNum(String specialistNum) {
       this.specialistNum = specialistNum;
   }

   public void setService(String service) {
       this.service = service;
   }
}
