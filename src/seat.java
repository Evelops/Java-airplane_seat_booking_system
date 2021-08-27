import java.util.Scanner;
public class seat {
	String seatID;
	int onoff;
	String seatname;
	
	boolean reservation(String reserve) {
		if (reserve.equals(seatname))
			return true;
		return false;
	}
	void read(Scanner scan) {
		onoff=scan.nextInt();
		seatID = scan.next();
	}


}

