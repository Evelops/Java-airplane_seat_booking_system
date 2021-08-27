import java.util.Scanner;

public class Member {
	int range; //µî±Þ
	String name;
	int age;
	String country;
	int phonenumber;
	String ID;
	String password;
	
	void read(Scanner scan) {
		name = scan.next();
		age = scan.nextInt();
		country = scan.next();
		phonenumber = scan.nextInt();
	    ID = scan.next();
		password = scan.next();
	}
	
	boolean IDmatches(String kwd) {
		if (kwd.equals(ID))
			return true;
		return false;
	}
	boolean PWmatches(String kwd) {
		if (kwd.equals(password))
			return true;
		return false;
	}

}
