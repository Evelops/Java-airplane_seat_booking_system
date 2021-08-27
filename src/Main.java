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
			System.out.println("1.로그인\n2.회원가입\n3.종료");
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
		System.out.println("=========비행기=========");
		System.out.println("1. 좌석예약");
		System.out.println("2. 좌석취소");
		System.out.println("3. 종료");
		System.out.println("======================\n");
		System.out.println("어떤 업무를 하시겠습니까?");
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
			System.out.print("아이디를 입력하세요: ");
			String InputID = scan.next();
			for (Member s : MemList) {
				if (s.IDmatches(InputID)) {
					b = -1;
					while (true) {
						System.out.print("비밀번호를 입력하세요: ");
						String Inputpassword = scan.next();
						for (Member r : MemList) {
							if (r.PWmatches(Inputpassword)) {
								saveID = InputID;// save id 변수에 inputId 로그인 정보를 임시로 저장해놓는다.
								Menu();
								a = -1;
								break;
							}
						}
						if (a == -1)
							break;
						System.out.println("비밀번호가 틀렸습니다. 다시입력해주세요.");
					}
				}
			}
			if (b == -1)
				break;
			System.out.println("아이디가 틀렸습니다. 다시입력해주세요.");
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

	void writeFile() {// text 파일 수정 할때 사용함수
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
		System.out.println("좌석예약을 선택하셨습니다.");
		System.out.println("원하는 좌석을 입력하세요:");
		String seatnum = scan.next();
		for (int i = 0; i < 24; i++) {
			if (seatList[i].reservation(seatnum)) {
				if (seatList[i].onoff == 0) {
					System.out.println("좌석이 예약되었습니다.");
					seatList[i].onoff = 1;
					seatList[i].seatID = saveID;//
					break;
				} else if (seatList[i].onoff == 1) {
					System.out.println("이미 예약된 좌석입니다.다른 좌석을 선택해주세요");
					break;
				}

			}
		}
	}

	void cancel() {
		System.out.println("좌석 취소를 선택했습니다.");
		System.out.println("취소할 좌석을 선택해주세요.");
		String seatnum = scan.next();
		for (int i = 0; i < 24; i++) {
			if (seatList[i].reservation(seatnum)) {
				if (seatList[i].onoff == 1) {
					System.out.println("좌석이 취소되었습니다.");
					seatList[i].onoff = 0;
					seatList[i].seatID = null;
					break;
				} else if (seatList[i].onoff == 0) {
					System.out.println("예약되지 않은 좌석입니다. 한번더 확인해주세요.");
					break;
				}
			}
		}
	}

	void Membership() {
		// 아래는 파일 입력
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
			System.out.print("회원정보를 입력하시오.\n이름:");
			s.name = scan.next();
			MemList.add(s);
			pw.printf("%s", s.name);
			System.out.print("나이:");
			s.age = scan.nextInt();
			MemList.add(s);
			pw.printf("\t%d", s.age);
			System.out.print("지역:");
			s.country = scan.next();
			MemList.add(s);
			pw.printf("\t%s", s.country);
			System.out.print("전화번호:");
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
		Scanner filein = openFile("member.txt"); // 빨간줄을 바로바로 고쳐주는게 중요하다!!
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