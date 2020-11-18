import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class pos extends JFrame {

	// public ImageIcon image = new ImageIcon(); // 메인 이미지 삽입

	JPanel main_panel[] = new JPanel[10]; // 메인패널
	JPanel sub_panel[] = new JPanel[10]; // 매인패널에 삽입하는 패널
	// JPanel x_panel[] = new JPanel[6];

	JPanel oc = new JPanel(new GridLayout(1, 3)); // 버튼패널 --> 구매,취소,호출, 로그아웃 버튼이 들어가는 패널
	JButton order = new JButton("주문"); // 주문 버튼 생성
	JButton cancel = new JButton("취소"); // 취소 버튼 생성
	JButton call = new JButton("호출"); // 직원호출 버튼 생성
	JButton logout = new JButton("로그아웃"); // 로그아웃 버튼 생성

	JButton food_btn[] = new JButton[7]; // --> 음식메뉴 버튼
	JButton menu_btn[] = new JButton[3]; // 왼쪽 상단 버튼

	JButton menu_RedWine[] = new JButton[6]; // 레드와인 종류
	JButton menu_WhiteWine[] = new JButton[6]; // 화이트 와인 종류
	JButton menu_SpaWine[] = new JButton[6]; // 스파클링 와인 종류
	JButton menu_Gratin[] = new JButton[6]; // 그라탕 종류
	JButton menu_Pasta[] = new JButton[6]; // 파스타 종류
	JButton menu_Pizza[] = new JButton[6]; // 피자 종류
	JButton menu_some[] = new JButton[6]; // 기타 할인 종류

	// tex[] 구역 설정
	JTextArea tex[] = new JTextArea[10]; // 왼쪽 하단의 텍스트 출력화면
	Font font = new Font("arian", Font.BOLD, 12);

	// 오른쪽 하단 버튼 이미지 첨부
	String oc_btn[] = { "image/order.jpg", "image/cancel.jpg", "image/call.png", "image/logout.jpg" };
	ImageIcon a[] = new ImageIcon[4];
	JLabel b[] = new JLabel[4];

	public MyButtonListener MyB;// 버튼리스너 생성
	public MyButtonCalc MyC; // 버튼리스너 생성
	public analys ana; // 통계 버튼 리스너 생성
	public main ma; // 판매 버튼 리스너 생성
	public receipt RE; // 영수증 버튼 리스너 생성

	public pos() throws SQLException {

		// 이미지 삽입 --> 메인화면
		String image = "image/main_pic.jpg";
		ImageIcon mainc = new ImageIcon(image);
		JLabel main = new JLabel();
		main.setIcon(mainc);
		///////////////////////
		// 버튼 이미지 삽입 --> 주문, 취소, 호출, 로그아웃
		for (int i = 0; i < 4; i++) {
			a[i] = new ImageIcon(oc_btn[i]);
			b[i] = new JLabel();
			b[i].setIcon(a[i]);
		}
		order.add(b[0]);
		cancel.add(b[1]);
		call.add(b[2]);
		logout.add(b[3]);
		//////////////////////////
		main_panel[0] = new JPanel(new BorderLayout()); // 메인패널
		main_panel[1] = new JPanel(new BorderLayout()); // 음식 버튼과 메뉴 확인, pos메뉴
		main_panel[2] = new JPanel(new BorderLayout()); // 계산 결과 확인, 주문버튼, 취소버튼

		sub_panel[0] = new JPanel(new GridLayout(0, 1)); // 음식메뉴
		sub_panel[1] = new JPanel(new BorderLayout()); // 주문버튼, 취소버튼
		sub_panel[2] = new JPanel(new GridLayout(0, 1)); // POS메뉴 버튼
		sub_panel[3] = new JPanel(new GridLayout(0, 3, 10, 15)); // 음식 메뉴 종류 패널

		// 왼쪽 상단 메뉴명
		String mb[] = { "판매", "영수증 업무", "통계" };

		// 오른쪽 상단 메뉴명
		String fb[] = { "레드와인", "화이트와인", "스파클링와인", "그라탕", "파스타", "피자", "기타" };

		// 왼쪽 상단 메뉴명 버튼 생성
		for (int i = 0; i < mb.length; i++) {
			menu_btn[i] = new JButton(mb[i]);
		}

		// 왼쪽 상단 메뉴명 사진 첨부
		menu_btn[0].setBorder(BorderFactory.createTitledBorder("판매"));
		menu_btn[1].setBorder(BorderFactory.createTitledBorder("영수증"));
		menu_btn[2].setBorder(BorderFactory.createTitledBorder("통계"));

		ImageIcon Imb[] = new ImageIcon[3];
		JLabel Lmb[] = new JLabel[3];
		String Smb[] = { "image/m.png", "image/m1.jpg", "image/m2.gif" };

		for (int i = 0; i < 3; i++) {
			Imb[i] = new ImageIcon(Smb[i]);
			Lmb[i] = new JLabel();
			Lmb[i].setIcon(Imb[i]);

			menu_btn[i].add(Lmb[i]);
		}

		// 오른쪽 상단 메뉴버튼과 음식 매뉴 버튼 생성
		for (int i = 0; i < 7; i++) {
			food_btn[i] = new JButton(fb[i]); // 오른쪽 상단 메뉴버튼
		}

		// 오른쪽 상단음식 버튼 생성
		for (int i = 0; i < 6; i++) {
			menu_RedWine[i] = new JButton(); // 레드와인
			menu_WhiteWine[i] = new JButton(); // 화이트 와인
			menu_SpaWine[i] = new JButton(); // 스파클링 와인
			menu_Gratin[i] = new JButton(); // 그라탕
			menu_Pasta[i] = new JButton(); // 파스타
			menu_Pizza[i] = new JButton(); // 피자
			menu_some[i] = new JButton(); // 기타 할인
		}

		// 주문 정보 출력
		tex[0] = new JTextArea(10, 51); // 주문창
		tex[0].setFont(font);

		// sub_panel[0]에 음식메뉴버튼 사입
		for (int i = 0; i < 7; i++) {
			sub_panel[0].add(food_btn[i]);
		}

		// 버튼 명 삽입
		order.setBorder(BorderFactory.createTitledBorder("주문"));
		cancel.setBorder(BorderFactory.createTitledBorder("취소"));
		call.setBorder(BorderFactory.createTitledBorder("호출"));
		logout.setBorder(BorderFactory.createTitledBorder("로그아웃"));

		// 주문,취소,호출 버튼의 크기 조절
		oc.setPreferredSize(new Dimension(580, 60));

		// oc에 버튼 추가
		oc.add(order); // 주문 버튼
		oc.add(cancel); // 취소 버튼
		oc.add(call); // 호출 버튼
		oc.add(logout); // 로그아웃 버튼

		// sub_panel[1]에 주문, 취소, 호출, 로그아웃 버튼 삽입
		sub_panel[1].add(oc);

		// sub_panel[2]에 왼쪽 상단에 있는 메뉴 버튼 삽입
		for (int i = 0; i < mb.length; i++) {
			sub_panel[2].add(menu_btn[i]);
		}

		sub_panel[0].setBorder(BorderFactory.createTitledBorder("FOOD"));
		sub_panel[2].setBorder(BorderFactory.createTitledBorder("MENU"));

		// main_panel[1]에 각 삽입 패널들을 추가 (초기화면)
		main_panel[1].add(sub_panel[2], BorderLayout.WEST);
		main_panel[1].add(main, BorderLayout.CENTER);
		main_panel[1].add(sub_panel[0], BorderLayout.EAST);

		// 주문 확인 text AREA삽입
		main_panel[2].add(new JScrollPane(tex[0]), BorderLayout.WEST);

		// 주문과 취소버튼을 오른쪽 하단에 삽입
		main_panel[2].add(sub_panel[1], BorderLayout.EAST);

		// main_panel[0]은 첫화면의 가장 안에 안에있는 메인패널 // main_panel[0]에 main_panel[1]과
		// main_panel[2]를 삽입
		main_panel[0].add(main_panel[1], BorderLayout.CENTER);
		main_panel[0].add(main_panel[2], BorderLayout.SOUTH);

		// 버튼 리스너 생성
		MyB = new MyButtonListener(this);
		MyC = new MyButtonCalc(this);
		ana = new analys(this);
		ma = new main(this);
		RE = new receipt(this);

		// order버튼과 cancel버튼, call버튼에 리스너 추가\
		order.addActionListener(MyC);
		cancel.addActionListener(MyC);
		call.addActionListener(MyC);
		logout.addActionListener(MyC);

		// menu_btn[]에 리스너 추가
		menu_btn[0].addActionListener(ma);
		menu_btn[1].addActionListener(RE);
		menu_btn[2].addActionListener(ana);

		// 음식 메뉴 버튼에 리스너 달기
		for (int i = 0; i < 7; i++) {
			food_btn[i].addActionListener(MyB);
		}

		// 음식 버튼에 리스너 추가
		for (int i = 0; i < 6; i++) {
			menu_RedWine[i].addActionListener(MyC);
			menu_WhiteWine[i].addActionListener(MyC);
			menu_SpaWine[i].addActionListener(MyC);
			menu_Gratin[i].addActionListener(MyC);
			menu_Pasta[i].addActionListener(MyC);
			menu_Pizza[i].addActionListener(MyC);
			menu_some[i].addActionListener(MyC);
		}

		// main_panel에 색상 설정
		// main_panel[0].setBackground(Color.lightGray);
		// main_panel[1].setBackground(Color.lightGray);
		main_panel[2].setBackground(Color.lightGray);

		// JFrame 설정
		this.setBackground(Color.BLACK);
		this.add(main_panel[0]); // 메인 프레임에 패널 추가
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("문래친친 Mullae Chin Chin");
		this.setSize(1100, 600);
		this.setVisible(true);
		this.setResizable(false);
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		new pos();
	}

}
