public class Receipt {
    Store store;
    private  int numberOfItems;
    private double total;
    private String receiptID;
    public static final int DEFAULT_ITEM=1;
    //fix this
    public Receipt(){
       
    }
    public Receipt(Store store, int numberOfItems, double total, String receiptID) {
        this.store = store;
        this.numberOfItems = numberOfItems;
        this.total = total;
        this.receiptID = receiptID;
    }
    public Receipt(Store store, double total, String receiptID){
        this(store,DEFAULT_ITEM,total,receiptID);
    }
    public Store getStore() {
        return store;
    }
    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Receipt) {
			Receipt other = (Receipt) obj;
			return this.store.equals(other.store) 
                && this.numberOfItems==other.numberOfItems 
                && this.total==other.total
                && this.receiptID.equalsIgnoreCase(other.receiptID);
		} else {
			return false;
		}
    }
    @Override
    public String toString(){
        if(numberOfItems>1){
            return String.format("%s"+ "%7s "+"%7s" + "%5d "+ "%2s" + "%10s" +"%5.2f ", "Receipt ",receiptID,store, numberOfItems,"Items","Total: ", total);
        }else{
            return String.format("%s"+ "%7s "+"%7s" + "%5d "+ "%2s" + "%10s" +"%5.2f ", "Receipt ",receiptID,store, numberOfItems,"Item","Total: ", total);
        }
    }
    public static boolean idMeetsCriteria(String receiptID, char firstChar, char secondChar, int timesAfter) {
        int count=0;
        if(receiptID.indexOf(firstChar) !=-1){ // check if the first appear in receiptID
            String sub=receiptID.substring(receiptID.indexOf(firstChar)+1); //get the string after the firstChar, not include the firstChar
            for(int i=0;i<sub.length();i++){ // run loop to check the how many the secondChar appear.
                if(sub.charAt(i)==secondChar){                  
                    count +=1;
            }
            }
            if(count==timesAfter){
                return true;
            }
        }
        return false;
    }

}
