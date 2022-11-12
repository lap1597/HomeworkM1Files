import java.util.*;

public class HomeworkM1Driver {

	private static boolean allTestsPassed = true;
	
	public static void main(String[] args) {
		
		// Store objects to use in testing
		Store groceryStore1 = new Store("Foods R Us", "Smallerville");
		Store groceryStore2 = new Store("Foods R Us", "Union City");
		Store electronicStore = new Store("ElectroFun", "Union City");
		Store bookStore = new Store("The Bookworm", "Eastchester");
		
		// receipt objects to use in testing
		Receipt receipt1 = new Receipt(groceryStore1, 10, 99.47, "a1x1m1m1");
		Receipt receipt2 = new Receipt(groceryStore1, .99, "vbevb0vv");
		Receipt receipt3 = new Receipt(groceryStore2, 21, 219.42, "h2g2b2gb");
		Receipt receipt4 = new Receipt(electronicStore, 599.99, "avvz0avv");
		DigitalReceipt digitalReceipt1 = new DigitalReceipt(bookStore, 50, 525.25, "bxxbxx3x", "sir.reads.a.lot@books.com");
		DigitalReceipt digitalReceipt2 = new DigitalReceipt(bookStore, 1, 4.87, "uuu3fqqq", "mark.darcy@gmail.abc");
		DigitalReceipt digitalReceipt3 = new DigitalReceipt(electronicStore, 2, 139.21, "xxx2m2xx", "dr.frankenstein@monster.com");
		DigitalReceipt digitalReceipt4 = new DigitalReceipt(groceryStore1, 18, 78.41, "ybbby99y", "t@iced.com");
	
		Receipt[] receipts = {receipt1, receipt2, receipt3, receipt4, digitalReceipt1, digitalReceipt2, digitalReceipt3, digitalReceipt4};	
		
		System.out.println("------------------------------Testing toString------------------------------");
		System.out.println("-----Make sure that the digital receipts show the email address.");
		for(Receipt receipt : receipts) {
			System.out.println(receipt);
		}
		
		System.out.println("\n\n------------------------------Testing use of Store object as an instance data variable.------------------------------");
		Store storeA = receipt3.getStore();
		Store storeB = new Store("Foods R Us", "Union City");
		System.out.println("Store A: " + storeA);
		System.out.println("Store B: " + storeB);
		boolean result = storeA.equals(storeB);
		System.out.println("Equal? Expected=true");
		System.out.println("Equal?   Actual=" + result);
		if(result==false) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: check your Store instance data variable.");
		}
		
		
		System.out.println("\n\n------------------------------Testing equals method------------------------------");
		// the testEquals method compares one of the receipts to the testReceipt to check for logical equivalence
		Receipt testReceipt = receipt1;
		// parameter 1: the invoking object that equals is invoked on
		// parameter 2: the parameter object passed into the equals method
		// parameter 3: the expected result
		// parameter 4: a description of the test
		testEquals(receipt1, testReceipt, true, "alias of the receipt");	
		
		testReceipt = new Receipt(receipt2.getStore(), receipt2.getNumberOfItems(), receipt2.getTotal(), receipt2.getReceiptID());
		testEquals(receipt2, testReceipt, true, "logically equivalent receipt");

		Store testStore = new Store(receipt3.getStore().getName().toUpperCase(), receipt3.getStore().getLocation().toLowerCase());
		testReceipt = new Receipt(testStore, receipt3.getNumberOfItems(), receipt3.getTotal(), receipt3.getReceiptID());
		testEquals(receipt3, testReceipt, true, "receipt with different capitalization of Store name");

		testReceipt = new Receipt(receipt4.getStore(), receipt4.getNumberOfItems(), receipt4.getTotal(), receipt4.getReceiptID().toUpperCase());
		testEquals(receipt4, testReceipt, true, "receipt with different capitalization of the receiptID");

		testReceipt = new Receipt(testStore, receipt1.getNumberOfItems(), receipt1.getTotal(), receipt1.getReceiptID());
		testEquals(receipt1, testReceipt, false, "receipt with a different store");

		testReceipt = new Receipt(receipt2.getStore(), receipt2.getNumberOfItems()+1, receipt2.getTotal(), receipt2.getReceiptID());
		testEquals(receipt2, testReceipt, false, "receipt with a different number of items");

		testReceipt = new Receipt(receipt3.getStore(), receipt3.getNumberOfItems(), receipt3.getTotal()*2, receipt3.getReceiptID());
		testEquals(receipt3, testReceipt, false, "receipt with a different total spent");

		testReceipt = new Receipt(receipt4.getStore(), receipt4.getNumberOfItems(), receipt4.getTotal(), receipt4.getReceiptID().substring(1));
		testEquals(receipt4, testReceipt, false, "receipt with a different receiptID");

		// this method creates a copy of each receipt and puts the copy in the list
		// the method then calls ArrayList.contains to test if the receipt is equal to one on the list
		// if equals works correctly, the call to contains will return true
		testEqualsMethod(receipts);
		
		
		System.out.println("\n\n------------------------------Testing Receipt Criteria------------------------------");
		// parameter 1: the receipt id
		// parameters 2-4: the ones passed into the idMeetsCriteria method (firstTarget, secondTarget, and countOfSecondAfterFirst)
		// parameter 5: the expected result (true if the receipt meets the criteria, false otherwise)
		// parameter 6: a description of the test
		testReceiptCriteria("ooXoMoMo", 'X', 'M', 2, true, "exactly 2 M's (secondTarget) after X (firstTarget)");
		testReceiptCriteria("VoEVooVV", 'E', 'V', 3, true, "exactly 3 V's (secondTarget) after E (firstTarget); note: also 1 extra V before E that is irrelevant");
		testReceiptCriteria("00g0p0gp", 'g', 'p', 2, true, "exactly 2 p's (secondTarget) after the first g (firstTarget); note: also 1 extra g that is irrelevant");
		testReceiptCriteria("y1q111q1", 'q', 'y', 0, true, "exactly 0 y's (secondTarget) after the first q (firstTarget); note: also 1 y before the q that is irrelevant");
		testReceiptCriteria("a11a1111", 'a', 'z', 0, true, "exactly 0 z's (secondTarget) after the first a (firstTarget)");

		testReceiptCriteria("ooFoDDoo", 'F', 'D', 3, false, "only 2 D's (secondTarget) after the F (firstChar) instead of 3");
		testReceiptCriteria("AooZoAoo", 'Z', 'A', 2, false, "only 1 A (secondTarget) after the Z (firstChar); note: there is a second A but it comes before the Z so it is irrelevant");
		testReceiptCriteria("1111pqqq", 'p', 'q', 2, false, "3 q's (secondTarget) after the p (firstChar) instead of 2");
		testReceiptCriteria("1111m111", 'c', 'm', 1, false, "no c (firstTarget)");
		testReceiptCriteria("obbboooo", 'b', 'b', 3, false, "the first b is the firstTarget; there are then only 2 *other b's* (that are in the count of secondTarget) after that instead of 3");

		System.out.println("\n\n------------------------------Testing e-Mail Validator (Extra Credit)------------------------------");
		System.out.println("-----Un-comment the driver file to test the extra credit.");
		// parameter 1: the email to test
		// parameter 2: the expected result (true if the email is valid, false otherwise)
		// parameter 3: a description of the test
		
		String validEmailDescription = "at least one letter somewhere before the @ and exactly three characters after the last period";
		testEmailValidator("sir.reads.a.lot@books.com", 	true, validEmailDescription);
		testEmailValidator("mark.darcy@gmail.abc", 			true, validEmailDescription);
		testEmailValidator("dr.frankenstein@monster.com", 	true, validEmailDescription);
		testEmailValidator("t@iced.gov", 					true, validEmailDescription);
		testEmailValidator("ina@hurry.edu", 				true, validEmailDescription);
		testEmailValidator("morris?@better.org", 			true, validEmailDescription);
		
		testEmailValidator("@abc.xyz", 						false, "no characters before @");
		testEmailValidator("abc.com", 						false, "no @");
		testEmailValidator("j@com", 						false, "no period");
		testEmailValidator("jessica@com", 					false, "no period");
		testEmailValidator("jessica.masters@com",			false, "no period after the @");
		testEmailValidator("j.m@gmail.comm", 				false, "4 chars after the last period");
		testEmailValidator("j.m@gmail.hi", 					false, "2 chars after the last period");
		testEmailValidator("j.m@gmail.z", 					false, "1 char after period");
		testEmailValidator("abcdef", 						false, "no @ or period");
		testEmailValidator("!@abc.def", 					false, "there are no letters before the @");
		

		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed) {
			System.out.println("----------Summary---------- All automated tests have passed. Be sure to manually review your code for style.");
		} else {
			System.out.flush();
			System.err.println("**********Summary********** ERROR: There is failure in at least one automated test. Review the output above for details.");
		}
	}
	
	
	
	/*----------------------------------------------------------------------------------------------------------*/
	/* TESTER METHODS */
	/*----------------------------------------------------------------------------------------------------------*/
	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 */
	public static void testEquals(Receipt receipt, Receipt testReceipt, boolean expectedResult, String testDescription) {
		System.out.println("\nInvoking object:  " + receipt);
		System.out.println("Parameter object: " + testReceipt);
		boolean actualResult = receipt.equals(testReceipt);
		System.out.println("Expected result=" + expectedResult);
		System.out.println("  Actual result=" + actualResult);
		if(actualResult!=expectedResult) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: " + testDescription);
		}
	}
	public static void testEqualsMethod(Receipt[] receipts) {
		System.out.println("\nTesting equality using the contains method...");
		List<Receipt> receiptList = new ArrayList<Receipt>();
		for(Receipt receipt : receipts) {
			Receipt copyReceipt;
			if(receipt instanceof DigitalReceipt) {
				copyReceipt = new DigitalReceipt(receipt.getStore(), receipt.getNumberOfItems(), receipt.getTotal(), new String(receipt.getReceiptID()), ( (DigitalReceipt) receipt).getEmail());
			} else {
				copyReceipt = new Receipt(receipt.getStore(), receipt.getNumberOfItems(), receipt.getTotal(), new String(receipt.getReceiptID()));
			}
			receiptList.add(copyReceipt);
		}
		boolean passContainsTests = true;
		for(Receipt receipt : receipts) {
			boolean contains = receiptList.contains(receipt);
			if(contains!=true) {
				allTestsPassed = false;
				System.out.println("**********TEST FAILED: equals method failed when testing equality of receipt: " + receipt);
				passContainsTests = false;
			}
		}
		if(passContainsTests) {
			System.out.println("All contains tests passed.");
		}
	}	
	public static void testReceiptCriteria(String receiptID, char firstChar, char secondChar, int timesAfter, boolean expectedResult, String testDescription) {
		boolean actualResult = Receipt.idMeetsCriteria(receiptID, firstChar,  secondChar,  timesAfter);
		System.out.println("\nID=" + receiptID + "  firstTarget=" + firstChar + "  secondTarget=" + secondChar + "  timesAfter=" + timesAfter);
		System.out.println("Expected=" + expectedResult);
		System.out.println("  Actual=" + actualResult);
		if(actualResult!=expectedResult) {
			allTestsPassed = false;
			System.out.println("*******************TEST FAILED: " + testDescription + "\n");
		}
	}
	public static void testEmailValidator(String email, boolean expectedResult, String testDescription) {
		boolean actualResult = DigitalReceipt.validateEmail(email);
		System.out.println("\nemail=" + email);
		System.out.println("Expected=" + expectedResult);
		System.out.println("  Actual=" + actualResult);
		if(actualResult!=expectedResult) {
			allTestsPassed = false;
			System.out.println("*******************TEST FAILED: " + testDescription);
		}
	}
}
