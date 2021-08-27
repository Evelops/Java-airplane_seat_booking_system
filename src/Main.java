import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	ArrayList<Member> MemList = new ArrayList<>();
	seat[] seatList = null;
	Scanner scan = new Scanner(System.in);
	String saveID;

	void FirstMenu() {
		namemaker();
		readmember();
		while (true) {
			System.out.println("1.�α���\n2.ȸ������\n3.����");
			int n = scan.nextInt();
			switch (n) {
			case 1:
				Login();
				break;
			case 2:
				Membership();
				break;
			}
			if (n == 3)
				break;
		}
	}

	void Menu() {
		System.out.println("=========�����=========");
		System.out.println("1. �¼�����");
		System.out.println("2. �¼����");
		System.out.println("3. ����");
		System.out.println("======================\n");
		System.out.println("� ������ �Ͻðڽ��ϱ�?");
		int n = scan.nextInt();
		switch (n) {
		case 1:
			mymain();
			writeFile();
			break;
		case 2:
			cancel();
			writeFile();
			break;
		case 3:
			break;
		}

	}

	void Login() {
		int a = 0;
		int b = 0;
		while (true) {
			System.out.print("���̵� �Է��ϼ���: ");
			String InputID = scan.next();
			for (Member s : MemList) {
				if (s.IDmatches(InputID)) {
					b = -1;
					while (true) {
						System.out.print("��й�ȣ�� �Է��ϼ���: ");
						String Inputpassword = scan.next();
						for (Member r : MemList) {
							if (r.PWmatches(Inputpassword)) {
								saveID = InputID;// save id ������ inputId �α��� ������ �ӽ÷� �����س��´�.
								Menu();
								a = -1;
								break;
							}
						}
						if (a == -1)
							break;
						System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ��Է����ּ���.");
					}
				}
			}
			if (b == -1)
				break;
			System.out.println("���̵� Ʋ�Ƚ��ϴ�. �ٽ��Է����ּ���.");
		}

	}

	void namemaker() {
		String seatnum;
		seatList = new seat[25];
		readNumber();
		for (int i = 0; i < 25; i++) {
			seatList[i] = new seat();
			if (i / 4 == 0) {
				seatnum = Integer.toString(i + 1);
				seatList[i].seatname = "A" + seatnum;
			} else if (i / 4 == 1) {
				seatnum = Integer.toString(i % 4 + 1);
				seatList[i].seatname = "B" + seatnum;
			} else if (i / 4 == 2) {
				seatnum = Integer.toString(i % 4 + 1);
				seatList[i].seatname = "C" + seatnum;
			} else if (i / 4 == 3) {
				seatnum = Integer.toString(i % 4 + 1);
				seatList[i].seatname = "D" + seatnum;
			} else if (i / 4 == 4) {
				seatnum = Integer.toString(i % 4 + 1);
				seatList[i].seatname = "E" + seatnum;
			} else if (i / 4 == 5) {
				seatnum = Integer.toString(i % 4 + 1);
				seatList[i].seatname = "F" + seatnum;
			}
		}

	}

	void readNumber() {
		Scanner filein = openFile("seat.txt");
		filein.nextLine();
		int i = 0;
		while (filein.hasNext()) {
			seatList[i] = new seat();
			seatList[i].read(filein);
			i++;
		}

		filein.close();
	}

	void writeFile() {// text ���� ���� �Ҷ� ����Լ�
		File f = new File("seat.txt");
		PrintWriter pw;
		try {
			pw = new PrintWriter(f);
			for (int i = 0; i < 24; i++) {
				pw.printf("\n%d\t%s", seatList[i].onoff, seatList[i].seatID);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	void mymain() {
		System.out.println("�¼������� �����ϼ̽��ϴ�.");
		System.out.println("���ϴ� �¼��� �Է��ϼ���:");
		String seatnum = scan.next();
		for (int i = 0; i < 24; i++) {
			if (seatList[i].reservation(seatnum)) {
				if (seatList[i].onoff == 0) {
					System.out.println("�¼��� ����Ǿ����ϴ�.");
					seatList[i].onoff = 1;
					seatList[i].seatID = saveID;//
					break;
				} else if (seatList[i].onoff == 1) {
					System.out.println("�̹� ����� �¼��Դϴ�.�ٸ� �¼��� �������ּ���");
					break;
				}

			}
		}
	}

	void cancel() {
		System.out.println("�¼� ��Ҹ� �����߽��ϴ�.");
		System.out.println("����� �¼��� �������ּ���.");
		String seatnum = scan.next();
		for (int i = 0; i < 24; i++) {
			if (seatList[i].reservation(seatnum)) {
				if (seatList[i].onoff == 1) {
					System.out.println("�¼��� ��ҵǾ����ϴ�.");
					seatList[i].onoff = 0;
					seatList[i].seatID = null;
					break;
				} else if (seatList[i].onoff == 0) {
					System.out.println("������� ���� �¼��Դϴ�. �ѹ��� Ȯ�����ּ���.");
					break;
				}
			}
		}
	}

	void Membership() {
		// �Ʒ��� ���� �Է�
		File f = new File("member.txt");
		PrintWriter pw;
		try {
			pw = new PrintWriter(f);
			Member s = new Member();
			for (Member ss : MemList) {
				pw.printf("%s", ss.name);
				pw.printf("\t%d", ss.age);
				pw.printf("\t%s", ss.country);
				pw.printf("\t%d", ss.phonenumber);
				pw.printf("\t%s", ss.ID);
				pw.printf("\t%s\n", ss.password);
			}
			System.out.print("ȸ�������� �Է��Ͻÿ�.\n�̸�:");
			s.name = scan.next();
			MemList.add(s);
			pw.printf("%s", s.name);
			System.out.print("����:");
			s.age = scan.nextInt();
			MemList.add(s);
			pw.printf("\t%d", s.age);
			System.out.print("����:");
			s.country = scan.next();
			MemList.add(s);
			pw.printf("\t%s", s.country);
			System.out.print("��ȭ��ȣ:");
			s.phonenumber = scan.nextInt();
			MemList.add(s);
			pw.printf("\t%d", s.phonenumber);
			System.out.print("ID:");
			s.ID = scan.next();
			MemList.add(s);
			pw.printf("\t%s", s.ID);
			System.out.print("password:");
			s.password = scan.next();
			MemList.add(s);
			pw.printf("\t%s\n", s.password);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	void readmember() {
		Scanner filein = openFile("member.txt"); // �������� �ٷιٷ� �����ִ°� �߿��ϴ�!!
		while (filein.hasNext()) {
			Member s = new Member();
			s.read(filein);
			MemList.add(s);
		}
		filein.close();
	}

	Scanner openFile(String filename) {
		File f = new File(filename);
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return s;
	}

	public static void main(String[] args) {
		Main a = new Main();
		a.FirstMenu();
	}
}