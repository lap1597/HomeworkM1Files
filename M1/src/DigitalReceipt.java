
public class DigitalReceipt extends Receipt {
    Store store;
    private  int numberOfItems;
    private double total;
    private int tv;
    private String receiptID;
    private String email;
    
   
    public DigitalReceipt(Store store, int numberOfItems, double total, String receiptID, String email) {
    	super(store,numberOfItems,total,receiptID);
        this.store=store;
        this.numberOfItems=numberOfItems;
        this.total=total;
        this.receiptID=receiptID;
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString(){
    
       return super.toString() + String.format("\n"+"%s" + "%7s","Digital copy sent to: ", email) ;
 
    }
    public static boolean validateEmail(String email) {
    
        if(email.contains("@")&& email.contains(".") ){ // check if email has "@" and "."
        // create  first half string to check if there have aleast 1 letter
            String strBefore=email.substring(0,email.indexOf("@")); 
            int letterBefore =0;
            for(Character ch:strBefore.toCharArray()){
                if(Character.isLetter(ch)){
                    letterBefore+=1;
                }
            }  
            if(strBefore.length()>=1 && letterBefore>=1){
                // to count how many letter appear before "@"
                  
        // check if last half after "@" qualify.
                String strAfter=email.substring(email.indexOf("@"));//get the new string from "@""
                if(strAfter.contains(".")){// check condition
                    String temp=strAfter.substring(strAfter.indexOf("."));// get the string from "."
                    if(temp.length()>=4 &&Character.isLetter(temp.charAt(3) ))    {// 3 character include "." so the length must >=4 
                        int count=0;
                        for(Character ch:temp.toCharArray()){                    //and the character at position 3 is suppose to be a Letter
                                if(Character.isLetter(ch)){
                                    count +=1;
                                }
                            }
                            if (count==3){
                                return true;
                            }
                        }
                    }
                }
        }
        
        return false;
}
    
}
