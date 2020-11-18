import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class analys implements ActionListener {
	public pos p;
	Statement stmt;
	Connection con = null;
	PreparedStatement pstmt;
	Font font = new Font("arian", Font.BOLD, 12);

	public analys(pos po) throws SQLException {

		this.p = po;

		// 데이터베이스 연동
		String url = "jdbc:mysql://localhost:3306/pos_db?characterEncoding=UTF-8&serverTimezone=UTC";
		String id = "root";
		String password = "3805";

		stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC 적재 성공...동건");

			con = DriverManager.getConnection(url, id, password);
			System.out.println("DB 연결 성공...동건");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC 적재 오류...동건d");
		}
		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(
					"select * from POS, RED,WHITE,SPA,GRATIN,PASTA,PIZZA where POS.id1 = RED.rid and POS.id1 = WHITE.wid and POS.id1 = SPA.spaid and POS.id1 = GRATIN.gid and POS.id1 = PASTA.paid and POS.id1 = PIZZA.piid");
			System.out.println("Statement 생성 성공...동건");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Statement 생성 오류...동건");
		}
	}

	ResultSet rs;
	// p.sub_panel[3]에 들어갈 JTextArea 생성
	JTextArea text = new JTextArea(); // 새로운 텍스에리어 생성
	JButton a_btn[] = new JButton[7]; // 오른쪽 상단 버튼 재생성

	String a[] = { "레드 판매량", "화이트 판매량", "스파클링 판매량", "그라탕 판매량", "파스타 판매량", "피자 판매량", "판매량" };

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		p.tex[0].setFont(font);
		// 왼쪽 상단 통계 버튼 클릭시 액션
		if (e.getSource() == p.menu_btn[2]) {
			p.sub_panel[3].removeAll(); // p.sub_panel[3]에 대한 모든 정보 삭제
			// p.sub_panel[3] = new JPanel(); //p.sub_panel[3]을 재생성

			p.sub_panel[0].removeAll(); // 음식메뉴 p.sub_panel[0]을 전부삭제
			p.sub_panel[0] = new JPanel(new GridLayout(0, 1)); // 메뉴버튼 재 생성
			p.sub_panel[0].setBorder(BorderFactory.createTitledBorder("판매량"));
			for (int i = 0; i < a.length; i++) {
				a_btn[i] = new JButton(a[i]);

				a_btn[i].addActionListener(this);

				p.sub_panel[0].add(a_btn[i]);
			}

			text = new JTextArea(19, 81); // p.sub_panel[3]에 들어갈 JTextArea 생성
			p.sub_panel[3] = new JPanel();

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]에 text 삽입
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("통계 화면"));

			p.tex[0].setForeground(Color.GRAY);
			p.tex[0].setText("통계와 분석 화면\n");

			// 메인패널에 재 삽입
			p.main_panel[1].add(p.sub_panel[0], BorderLayout.EAST);
			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setTitle("문래친친_통계");
			p.setVisible(true);

		}

		// 통계버튼 클릭스 오른쪽 상단의 메뉴가 바뀝

		// 레드와인 판매량 버튼
		if (e.getSource() == a_btn[0]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setForeground(Color.BLACK);
			text.setFont(font);
			text.setText("레드와인 판매량\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("레드와인 구매 개수 파악");

			try {

				String sql = "select sum(말벡),sum(앙케),sum(라크라사드),sum(몬테스) ,sum(엘레베), sum(페레제) from RED";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("총 판매 개수" + "\n");
				text.append("말벡 개수              :   " + rs.getInt("sum(말벡)") + "\n" + "말벡 판매금액 : "
						+ rs.getInt("sum(말벡)") * 30000 + " \n");
				text.append("앙케 개수              :   " + rs.getInt("sum(앙케)") + "\n" + "앙케 판매금액: "
						+ rs.getInt("sum(앙케)") * 40000 + "\n");
				text.append("라크라사드 개수     :   " + rs.getInt("sum(라크라사드)") + "\n" + "라크라사드판매금액 : "
						+ rs.getInt("sum(라크라사드)") * 43000 + "\n");
				text.append("몬테스 개수           :   " + rs.getInt("sum(몬테스)") + "\n" + "몬테사 알파 판매금액 : "
						+ rs.getInt("sum(몬테스)") * 75000 + "\n");
				text.append("엘레베 개수           :   " + rs.getInt("sum(엘레베)") + "\n" + "말벡 판매금액 : "
						+ rs.getInt("sum(엘레베)") * 60000 + "\n");
				text.append("페레제 개수           :   " + rs.getInt("sum(페레제)") + "\n" + "페레제 판매금액 : "
						+ rs.getInt("sum(페레제)") * 45000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]에 text 삽입
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("통계 화면"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 화이트 와인 판매량 버튼
		if (e.getSource() == a_btn[1]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("화이트와인 판매량\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("화이트와인 구매 개수 파악");

			try {

				String sql = "select sum(리즐링),sum(앙시앙),sum(지아콘디),sum(소비뇽),sum(비안코),sum(샤르도네)  from WHITE";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("총 판매 개수" + "\n");
				text.append("리즐링 개수          :   " + rs.getInt("sum(리즐링)") + "\n" + "리즐링 판매금액 : "
						+ rs.getInt("sum(리즐링)") * 40000 + "\n");
				text.append("앙시앙 개수          :   " + rs.getInt("sum(앙시앙)") + "\n" + "앙시앙 판매금액 : "
						+ rs.getInt("sum(앙시앙)") * 20000 + "\n");
				text.append("지아콘디 개수       :   " + rs.getInt("sum(지아콘디)") + "\n" + "지아콘디 판매금액 : "
						+ rs.getInt("sum(지아콘디)") * 33000 + "\n");
				text.append("소비뇽 개수          :   " + rs.getInt("sum(소비뇽)") + "\n" + "소비뇽 판매금액 : "
						+ rs.getInt("sum(소비뇽)") * 55000 + "\n");
				text.append("비안코 개수          :   " + rs.getInt("sum(비안코)") + "\n" + "비안코 판매금액 : "
						+ rs.getInt("sum(비안코)") * 40000 + "\n");
				text.append("샤르도네 개수       :   " + rs.getInt("sum(샤르도네)") + "\n" + "샤르도네 판매금액 : "
						+ rs.getInt("sum(샤르도네)") * 35000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]에 text 삽입
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("통계 화면"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 스파클링 와인 판매량 버튼
		if (e.getSource() == a_btn[2]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("스파클링와인 판매량\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("스파클링와인 구매 개수 파악");

			try {

				String sql = "select sum(아리온),sum(로제타),sum(비안코),sum(아스티),sum(카바),sum(브룻)  from SPA";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("총 판매 개수" + "\n");
				text.append("아리온 개수          :   " + rs.getInt("sum(아리온)") + "\n" + "아리온 판매금액 : "
						+ rs.getInt("sum(아리온)") * 34000 + "\n");
				text.append("로제타 개수          :   " + rs.getInt("sum(로제타)") + "\n" + "로제타 판매금액 : "
						+ rs.getInt("sum(로제타)") * 90000 + "\n");
				text.append("비안코 개수          :   " + rs.getInt("sum(비안코)") + "\n" + "비안코 판매금액 : "
						+ rs.getInt("sum(비안코)") * 13000 + "\n");
				text.append("아스티 개수          :   " + rs.getInt("sum(아스티)") + "\n" + "아스티 판매금액 : "
						+ rs.getInt("sum(아스티)") * 45000 + "\n");
				text.append("카바 개수             :   " + rs.getInt("sum(카바)") + "\n" + "카바 판매금액 : "
						+ rs.getInt("sum(카바)") * 50000 + "\n");
				text.append("브룻 개수             :   " + rs.getInt("sum(브룻)") + "\n" + "브룻 판매금액 : "
						+ rs.getInt("sum(브룻)") * 35000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]에 text 삽입
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("통계 화면"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 그라탕 판매량 버튼
		if (e.getSource() == a_btn[3]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("그라탕 판매량\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("그라탕 구매 개수 파악");

			try {

				String sql = "select sum(토마토그라탕),sum(크림그라탕),sum(로제그라탕),sum(닭고기그라탕),sum(소고기그라탕),sum(돼지고기그라탕)  from GRATIN";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("총 판매 개수" + "\n");
				text.append("토마토그라탕 개수       :   " + rs.getInt("sum(토마토그라탕)") + "\n" + "토마토그라탕 판매금액 : "
						+ rs.getInt("sum(토마토그라탕)") * 12000 + "\n");
				text.append("크림그라탕 개수          :   " + rs.getInt("sum(크림그라탕)") + "\n" + "크림그라탕 판매금액 : "
						+ rs.getInt("sum(크림그라탕)") * 23000 + "\n");
				text.append("로제그라탕 개수          :   " + rs.getInt("sum(로제그라탕)") + "\n" + "로제그라탕 판매금액 : "
						+ rs.getInt("sum(로제그라탕)") * 43000 + "\n");
				text.append("닭고기그라탕 개수       :   " + rs.getInt("sum(닭고기그라탕)") + "\n" + "닭고기그라탕 판매금액 : "
						+ rs.getInt("sum(닭고기그라탕)") * 25000 + "\n");
				text.append("소고기그라탕 개수       :   " + rs.getInt("sum(소고기그라탕)") + "\n" + "소고기그라탕 판매금액 : "
						+ rs.getInt("sum(소고기그라탕)") * 23000 + "\n");
				text.append("돼지고기그라탕 개수    :   " + rs.getInt("sum(돼지고기그라탕)") + "\n" + "돼지고기그라탕 판매금액 : "
						+ rs.getInt("sum(돼지고기그라탕)") * 25000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]에 text 삽입
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("통계 화면"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 파스타 판매량 버튼
		if (e.getSource() == a_btn[4]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("파스타 판매량\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("파스타 구매 개수 파악");

			try {

				String sql = "select sum(토마토파스타),sum(크림파스타),sum(로제파스타),sum(알리오올리오),sum(연어파스타),sum(냉파스타)  from PASTA";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("총 판매 개수" + "\n");
				text.append("토마토파스타 개수       :   " + rs.getInt("sum(토마토파스타)") + "\n" + "토마토파스타 판매금액 : "
						+ rs.getInt("sum(토마토파스타)") * 17000 + "\n");
				text.append("크림파스타 개수          :   " + rs.getInt("sum(크림파스타)") + "\n" + "크림파스타 판매금액 : "
						+ rs.getInt("sum(크림파스타)") * 20000 + "\n");
				text.append("로제파스타 개수          :   " + rs.getInt("sum(로제파스타)") + "\n" + "로제파스타 판매금액 : "
						+ rs.getInt("sum(로제파스타)") * 13000 + "\n");
				text.append("알리오올리오 개수       :   " + rs.getInt("sum(알리오올리오)") + "\n" + "알리오올리오 판매금액 : "
						+ rs.getInt("sum(알리오올리오)") * 35000 + "\n");
				text.append("연어파스타 개수          :   " + rs.getInt("sum(연어파스타)") + "\n" + "연어파스타 판매금액 : "
						+ rs.getInt("sum(연어파스타)") * 40000 + "\n");
				text.append("냉파스타 개수             :   " + rs.getInt("sum(냉파스타)") + "\n" + "냉파스타 판매금액 : "
						+ rs.getInt("sum(냉파스타)") * 15000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]에 text 삽입
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("통계 화면"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 피자 판매량 버튼
		if (e.getSource() == a_btn[5]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("피자 판매량\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("피자 구매 개수 파악");

			try {

				String sql = "select sum(토마토피자),sum(비스마르크),sum(풍기피자),sum(크림피자),sum(포테이토피자),sum(스파이스초리죠)  from PIZZA";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("총 판매 개수" + "\n");
				text.append("토마토피자 개수          :   " + rs.getInt("sum(토마토피자)") + "\n" + "토마토피자 판매금액 : "
						+ rs.getInt("sum(토마토피자)") * 30000 + "\n");
				text.append("비스마르크 개수          :   " + rs.getInt("sum(비스마르크)") + "\n" + "비스마르크 판매금액 : "
						+ rs.getInt("sum(비스마르크)") * 25000 + "\n");
				text.append("풍기피자 개수             :   " + rs.getInt("sum(풍기피자)") + "\n" + "풍기피자 판매금액 : "
						+ rs.getInt("sum(풍기피자)") * 10000 + "\n");
				text.append("크림피자 개수             :   " + rs.getInt("sum(크림피자)") + "\n" + "크림피자 판매금액 : "
						+ rs.getInt("sum(크림피자)") * 20000 + "\n");
				text.append("포테이토피자 개수       :   " + rs.getInt("sum(포테이토피자)") + "\n" + "포테이토피자 판매금액 : "
						+ rs.getInt("sum(포테이토피자)") * 25000 + "\n");
				text.append("스파이스초리죠 개수    :   " + rs.getInt("sum(스파이스초리죠)") + "\n" + "스파이스초리죠 판매금액 : "
						+ rs.getInt("sum(스파이스초리죠)") * 35000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]에 text 삽입
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("통계 화면"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 총판매량 버튼
		if (e.getSource() == a_btn[6]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("판매량\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("총 판매량 확인");

			try {

				String sql = "select sum(할인횟수),sum(할인금액),sum(할인전금액),sum(최종금액) from POS";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("총 판매 " + "\n");
				text.append("할인횟수               :   " + rs.getInt("sum(할인횟수)") + "\n");
				text.append("할인금액                :   " + rs.getInt("sum(할인금액)") + "\n");
				text.append("할인전금액              :   " + rs.getInt("sum(할인전금액)") + "\n");
				text.append("최종금액                :   " + rs.getInt("sum(최종금액)") + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]에 text 삽입
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("통계 화면"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

	}
}
