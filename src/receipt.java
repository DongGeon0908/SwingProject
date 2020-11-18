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

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class receipt implements ActionListener {
	public pos p;
	Statement stmt;
	Connection con = null;
	PreparedStatement pstmt;
	Font font = new Font("arian", Font.BOLD, 12);

	public receipt(pos po) throws SQLException {
		this.p = po;

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
					"select * from POS, RED,WHITE,SPA,GRATIN,PASTA,PIZZA,beforesum where POS.id1 = RED.rid and POS.id1 = WHITE.wid and POS.id1 = SPA.spaid and POS.id1 = GRATIN.gid and POS.id1 = PASTA.paid and POS.id1 = PIZZA.piid and POS.id1 = beforesum.beid");
			System.out.println("Statement 생성 성공...동건");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Statement 생성 오류...동건");
		}
	}

	ResultSet rs;
	// p.sub_panel[3]에 들어갈 JTextArea 생성
	JTextArea text = new JTextArea();
	JButton rr_btn[] = new JButton[7];

	String a[] = { "NEXT", "PREVIOUS", "REFUND" };

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// 왼쪽 상단 통계 버튼 클릭시 액션
		if (e.getSource() == p.menu_btn[1]) {
			p.sub_panel[3].removeAll(); // p.sub_panel[3]에 대한 모든 정보 삭제
			// p.sub_panel[3] = new JPanel(); //p.sub_panel[3]을 재생성

			p.sub_panel[0].removeAll(); // 음식메뉴 p.sub_panel[0]을 전부삭제
			p.sub_panel[0] = new JPanel(new GridLayout(0, 1)); // 메뉴버튼 재 생성
			p.sub_panel[0].setBorder(BorderFactory.createTitledBorder("영수증"));
			for (int i = 0; i < a.length; i++) {
				rr_btn[i] = new JButton(a[i]);

				rr_btn[i].addActionListener(this);

				p.sub_panel[0].add(rr_btn[i]);
			}

			text = new JTextArea(19, 81); // p.sub_panel[3]에 들어갈 JTextArea 생성
			p.sub_panel[3] = new JPanel();

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]에 text 삽입
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("영수증"));
			p.tex[0].setForeground(Color.GRAY);
			p.tex[0].setText("영수증 업무 화면\n");

			p.main_panel[1].add(p.sub_panel[0], BorderLayout.EAST);
			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setTitle("문래친친_영수증");
			p.setVisible(true);

		}

		if (e.getSource() == rr_btn[0]) {

			text.setFont(font);
			text.setText("영수증 업무\n");
			// p.sub_panel[3] = new JPanel();
			p.tex[0].setText("영수증 파악");

			try {

				rs.next();
				p.tex[0].setText("구매자 Id : " + rs.getInt("id1"));
				text.append("구매자 ID     : " + rs.getInt("rid") + "\n");

				if (rs.getInt("말벡") != 0) {
					text.append("말벡              :   " + rs.getInt("말벡") + "개       ---------------->" + "말벡 판매금액 : "
							+ rs.getInt("말벡") * 30000 + " \n");
				}
				if (rs.getInt("앙케") != 0) {
					text.append("앙케              :   " + rs.getInt("앙케") + "개       ---------------->" + "앙케 판매금액: "
							+ rs.getInt("앙케") * 40000 + "\n");
				}
				if (rs.getInt("라크라사드") != 0) {
					text.append("라크라사드     :   " + rs.getInt("라크라사드") + "개       ---------------->" + "라크라사드판매금액 : "
							+ rs.getInt("라크라사드") * 43000 + "\n");
				}
				if (rs.getInt("몬테스") != 0) {
					text.append("몬테스           :   " + rs.getInt("몬테스") + "개       ---------------->" + "몬테사 알파 판매금액 : "
							+ rs.getInt("몬테스") * 75000 + "\n");
				}
				if (rs.getInt("엘레베") != 0) {
					text.append("엘레베           :   " + rs.getInt("엘레베") + "개       ---------------->" + "말벡 판매금액 : "
							+ rs.getInt("엘레베") * 60000 + "\n");
				}
				if (rs.getInt("페레제") != 0) {
					text.append("페레제           :   " + rs.getInt("페레제") + "개       ---------------->" + "페레제 판매금액 : "
							+ rs.getInt("페레제") * 45000 + "\n");
				}
				if (rs.getInt("리즐링") != 0) {
					text.append("리즐링          :   " + rs.getInt("리즐링") + "개       ---------------->" + "리즐링 판매금액 : "
							+ rs.getInt("리즐링") * 40000 + "\n");
				}
				if (rs.getInt("앙시앙") != 0) {
					text.append("앙시앙          :   " + rs.getInt("앙시앙") + "개       ---------------->" + "앙시앙 판매금액 : "
							+ rs.getInt("앙시앙") * 20000 + "\n");
				}
				if (rs.getInt("지아콘디") != 0) {
					text.append("지아콘디       :   " + rs.getInt("지아콘디") + "개       ---------------->" + "지아콘디 판매금액 : "
							+ rs.getInt("지아콘디") * 33000 + "\n");
				}
				if (rs.getInt("소비뇽") != 0) {
					text.append("소비뇽          :   " + rs.getInt("소비뇽") + "개       ---------------->" + "소비뇽 판매금액 : "
							+ rs.getInt("소비뇽") * 55000 + "\n");
				}
				if (rs.getInt("비안코") != 0) {
					text.append("비안코          :   " + rs.getInt("비안코") + "개       ---------------->" + "비안코 판매금액 : "
							+ rs.getInt("비안코") * 40000 + "\n");
				}
				if (rs.getInt("샤르도네") != 0) {
					text.append("샤르도네       :   " + rs.getInt("샤르도네") + "개       ---------------->" + "샤르도네 판매금액 : "
							+ rs.getInt("샤르도네") * 35000 + "\n");
				}
				if (rs.getInt("아리온") != 0) {
					text.append("아리온          :   " + rs.getInt("아리온") + "개       ---------------->" + "아리온 판매금액 : "
							+ rs.getInt("아리온") * 34000 + "\n");
				}
				if (rs.getInt("로제타") != 0) {
					text.append("로제타          :   " + rs.getInt("로제타") + "개       ---------------->" + "로제타 판매금액 : "
							+ rs.getInt("로제타") * 90000 + "\n");
				}
				if (rs.getInt("비안코") != 0) {
					text.append("비안코          :   " + rs.getInt("비안코") + "개       ---------------->" + "비안코 판매금액 : "
							+ rs.getInt("비안코") * 13000 + "\n");
				}
				if (rs.getInt("아스티") != 0) {
					text.append("아스티          :   " + rs.getInt("아스티") + "개       ---------------->" + "아스티 판매금액 : "
							+ rs.getInt("아스티") * 45000 + "\n");
				}
				if (rs.getInt("카바") != 0) {
					text.append("카바             :   " + rs.getInt("카바") + "개       ---------------->" + "카바 판매금액 : "
							+ rs.getInt("카바") * 50000 + "\n");
				}
				if (rs.getInt("브룻") != 0) {
					text.append("브룻             :   " + rs.getInt("브룻") + "개       ---------------->" + "브룻 판매금액 : "
							+ rs.getInt("브룻") * 35000 + "\n");
				}
				if (rs.getInt("토마토그라탕") != 0) {
					text.append("토마토그라탕       :   " + rs.getInt("토마토그라탕") + "개       ---------------->"
							+ "토마토그라탕 판매금액 : " + rs.getInt("토마토그라탕") * 12000 + "\n");
				}
				if (rs.getInt("크림그라탕") != 0) {
					text.append("크림그라탕          :   " + rs.getInt("크림그라탕") + "개       ---------------->"
							+ "크림그라탕 판매금액 : " + rs.getInt("크림그라탕") * 23000 + "\n");
				}
				if (rs.getInt("로제그라탕") != 0) {
					text.append("로제그라탕          :   " + rs.getInt("로제그라탕") + "개       ---------------->"
							+ "로제그라탕 판매금액 : " + rs.getInt("로제그라탕") * 43000 + "\n");
				}
				if (rs.getInt("닭고기그라탕") != 0) {
					text.append("닭고기그라탕       :   " + rs.getInt("닭고기그라탕") + "개       ---------------->"
							+ "닭고기그라탕 판매금액 : " + rs.getInt("닭고기그라탕") * 25000 + "\n");
				}
				if (rs.getInt("소고기그라탕") != 0) {
					text.append("소고기그라탕       :   " + rs.getInt("소고기그라탕") + "개       ---------------->"
							+ "소고기그라탕 판매금액 : " + rs.getInt("소고기그라탕") * 23000 + "\n");
				}
				if (rs.getInt("돼지고기그라탕") != 0) {
					text.append("돼지고기그라탕    :   " + rs.getInt("돼지고기그라탕") + "개       ---------------->"
							+ "돼지고기그라탕 판매금액 : " + rs.getInt("돼지고기그라탕") * 25000 + "\n");
				}
				if (rs.getInt("토마토파스타") != 0) {
					text.append("토마토파스타       :   " + rs.getInt("토마토파스타") + "개       ---------------->"
							+ "토마토파스타 판매금액 : " + rs.getInt("토마토파스타") * 17000 + "\n");
				}
				if (rs.getInt("크림파스타") != 0) {
					text.append("크림파스타          :   " + rs.getInt("크림파스타") + "개       ---------------->"
							+ "크림파스타 판매금액 : " + rs.getInt("크림파스타") * 20000 + "\n");
				}
				if (rs.getInt("로제파스타") != 0) {
					text.append("로제파스타          :   " + rs.getInt("로제파스타") + "개       ---------------->"
							+ "로제파스타 판매금액 : " + rs.getInt("로제파스타") * 13000 + "\n");
				}
				if (rs.getInt("알리오올리오") != 0) {
					text.append("알리오올리오       :   " + rs.getInt("알리오올리오") + "개       ---------------->"
							+ "알리오올리오 판매금액 : " + rs.getInt("알리오올리오") * 35000 + "\n");
				}
				if (rs.getInt("연어파스타") != 0) {
					text.append("연어파스타          :   " + rs.getInt("연어파스타") + "개       ---------------->"
							+ "연어파스타 판매금액 : " + rs.getInt("연어파스타") * 40000 + "\n");
				}
				if (rs.getInt("냉파스타") != 0) {
					text.append("냉파스타             :   " + rs.getInt("냉파스타") + "개       ---------------->"
							+ "냉파스타 판매금액 : " + rs.getInt("냉파스타") * 15000 + "\n");
				}
				if (rs.getInt("토마토피자") != 0) {
					text.append("토마토피자          :   " + rs.getInt("토마토피자") + "개       ---------------->"
							+ "토마토피자 판매금액 : " + rs.getInt("토마토피자") * 30000 + "\n");
				}
				if (rs.getInt("비스마르크") != 0) {
					text.append("비스마르크          :   " + rs.getInt("비스마르크") + "개       ---------------->"
							+ "비스마르크 판매금액 : " + rs.getInt("비스마르크") * 25000 + "\n");
				}
				if (rs.getInt("풍기피자") != 0) {
					text.append("풍기피자             :   " + rs.getInt("풍기피자") + "개       ---------------->"
							+ "풍기피자 판매금액 : " + rs.getInt("풍기피자") * 10000 + "\n");
				}
				if (rs.getInt("크림피자") != 0) {
					text.append("크림피자             :   " + rs.getInt("크림피자") + "개       ---------------->"
							+ "크림피자 판매금액 : " + rs.getInt("크림피자") * 20000 + "\n");
				}
				if (rs.getInt("포테이토피자") != 0) {
					text.append("포테이토피자       :   " + rs.getInt("포테이토피자") + "개       ---------------->"
							+ "포테이토피자 판매금액 : " + rs.getInt("포테이토피자") * 25000 + "\n");
				}
				if (rs.getInt("스파이스초리죠") != 0) {
					text.append("스파이스초리죠    :   " + rs.getInt("스파이스초리죠") + "개       ---------------->"
							+ "스파이스초리죠 판매금액 : " + rs.getInt("스파이스초리죠") * 50000 + "\n");
				}
				if (rs.getInt("직원할인") != 0) {
					text.append("직원할인     :   " + rs.getInt("직원할인") + "번     ---------------->" + "직원 헐인금액 : "
							+ rs.getInt("직원할인") * 30000 + "\n");
				}
				if (rs.getInt("지인할인") != 0) {
					text.append("지인할인    :   " + rs.getInt("지인할인") + "번     ---------------->" + "지인 헐인금액 : "
							+ rs.getInt("지인할인") * 25000 + "\n");
				}
				if (rs.getInt("만원") != 0) {
					text.append("만원할인    :   " + rs.getInt("만원") + "번     ---------------->" + "만원 총헐인금액 : "
							+ rs.getInt("만원") * 10000 + "\n");
				}
				if (rs.getInt("이만원") != 0) {
					text.append("이만원할인    :   " + rs.getInt("이만원") + "번     ---------------->" + "이만원 총헐인금액 : "
							+ rs.getInt("이만원") * 20000 + "\n");
				}
				if (rs.getInt("오만원") != 0) {
					text.append("오만원할인    :   " + rs.getInt("오만원") + "번     ---------------->" + "오만원 총헐인금액 : "
							+ rs.getInt("오만원") * 50000 + "\n");
				}
				if (rs.getInt("할인전금액") != 0) {
					text.append("할인전금액    :   " + rs.getInt("할인전금액") + "\n");
				}
				if (rs.getInt("최종금액") != 0) {
					text.append("최종금액   :     " + rs.getInt("최종금액"));
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.setVisible(true);
		}

		if (e.getSource() == rr_btn[1]) {
			text.setFont(font);
			text.setText("영수증 업무\n");

			try {

				rs.previous();
				p.tex[0].setText("구매자 Id : " + rs.getInt("id1"));
				text.append("구매자 ID     : " + rs.getInt("rid") + "\n");
				if (rs.getInt("말벡") != 0) {
					text.append("말벡              :   " + rs.getInt("말벡") + "개       ---------------->" + "말벡 판매금액 : "
							+ rs.getInt("말벡") * 30000 + " \n");
				}
				if (rs.getInt("앙케") != 0) {
					text.append("앙케              :   " + rs.getInt("앙케") + "개       ---------------->" + "앙케 판매금액: "
							+ rs.getInt("앙케") * 40000 + "\n");
				}
				if (rs.getInt("라크라사드") != 0) {
					text.append("라크라사드     :   " + rs.getInt("라크라사드") + "개       ---------------->" + "라크라사드판매금액 : "
							+ rs.getInt("라크라사드") * 43000 + "\n");
				}
				if (rs.getInt("몬테스") != 0) {
					text.append("몬테스           :   " + rs.getInt("몬테스") + "개       ---------------->" + "몬테사 알파 판매금액 : "
							+ rs.getInt("몬테스") * 75000 + "\n");
				}
				if (rs.getInt("엘레베") != 0) {
					text.append("엘레베           :   " + rs.getInt("엘레베") + "개       ---------------->" + "말벡 판매금액 : "
							+ rs.getInt("엘레베") * 60000 + "\n");
				}
				if (rs.getInt("페레제") != 0) {
					text.append("페레제           :   " + rs.getInt("페레제") + "개       ---------------->" + "페레제 판매금액 : "
							+ rs.getInt("페레제") * 45000 + "\n");
				}
				if (rs.getInt("리즐링") != 0) {
					text.append("리즐링          :   " + rs.getInt("리즐링") + "개       ---------------->" + "리즐링 판매금액 : "
							+ rs.getInt("리즐링") * 40000 + "\n");
				}
				if (rs.getInt("앙시앙") != 0) {
					text.append("앙시앙          :   " + rs.getInt("앙시앙") + "개       ---------------->" + "앙시앙 판매금액 : "
							+ rs.getInt("앙시앙") * 20000 + "\n");
				}
				if (rs.getInt("지아콘디") != 0) {
					text.append("지아콘디       :   " + rs.getInt("지아콘디") + "개       ---------------->" + "지아콘디 판매금액 : "
							+ rs.getInt("지아콘디") * 33000 + "\n");
				}
				if (rs.getInt("소비뇽") != 0) {
					text.append("소비뇽          :   " + rs.getInt("소비뇽") + "개       ---------------->" + "소비뇽 판매금액 : "
							+ rs.getInt("소비뇽") * 55000 + "\n");
				}
				if (rs.getInt("비안코") != 0) {
					text.append("비안코          :   " + rs.getInt("비안코") + "개       ---------------->" + "비안코 판매금액 : "
							+ rs.getInt("비안코") * 40000 + "\n");
				}
				if (rs.getInt("샤르도네") != 0) {
					text.append("샤르도네       :   " + rs.getInt("샤르도네") + "개       ---------------->" + "샤르도네 판매금액 : "
							+ rs.getInt("샤르도네") * 35000 + "\n");
				}
				if (rs.getInt("아리온") != 0) {
					text.append("아리온          :   " + rs.getInt("아리온") + "개       ---------------->" + "아리온 판매금액 : "
							+ rs.getInt("아리온") * 34000 + "\n");
				}
				if (rs.getInt("로제타") != 0) {
					text.append("로제타          :   " + rs.getInt("로제타") + "개       ---------------->" + "로제타 판매금액 : "
							+ rs.getInt("로제타") * 90000 + "\n");
				}
				if (rs.getInt("비안코") != 0) {
					text.append("비안코          :   " + rs.getInt("비안코") + "개       ---------------->" + "비안코 판매금액 : "
							+ rs.getInt("비안코") * 13000 + "\n");
				}
				if (rs.getInt("아스티") != 0) {
					text.append("아스티          :   " + rs.getInt("아스티") + "개       ---------------->" + "아스티 판매금액 : "
							+ rs.getInt("아스티") * 45000 + "\n");
				}
				if (rs.getInt("카바") != 0) {
					text.append("카바             :   " + rs.getInt("카바") + "개       ---------------->" + "카바 판매금액 : "
							+ rs.getInt("카바") * 50000 + "\n");
				}
				if (rs.getInt("브룻") != 0) {
					text.append("브룻             :   " + rs.getInt("브룻") + "개       ---------------->" + "브룻 판매금액 : "
							+ rs.getInt("브룻") * 35000 + "\n");
				}
				if (rs.getInt("토마토그라탕") != 0) {
					text.append("토마토그라탕       :   " + rs.getInt("토마토그라탕") + "개       ---------------->"
							+ "토마토그라탕 판매금액 : " + rs.getInt("토마토그라탕") * 12000 + "\n");
				}
				if (rs.getInt("크림그라탕") != 0) {
					text.append("크림그라탕          :   " + rs.getInt("크림그라탕") + "개       ---------------->"
							+ "크림그라탕 판매금액 : " + rs.getInt("크림그라탕") * 23000 + "\n");
				}
				if (rs.getInt("로제그라탕") != 0) {
					text.append("로제그라탕          :   " + rs.getInt("로제그라탕") + "개       ---------------->"
							+ "로제그라탕 판매금액 : " + rs.getInt("로제그라탕") * 43000 + "\n");
				}
				if (rs.getInt("닭고기그라탕") != 0) {
					text.append("닭고기그라탕       :   " + rs.getInt("닭고기그라탕") + "개       ---------------->"
							+ "닭고기그라탕 판매금액 : " + rs.getInt("닭고기그라탕") * 25000 + "\n");
				}
				if (rs.getInt("소고기그라탕") != 0) {
					text.append("소고기그라탕       :   " + rs.getInt("소고기그라탕") + "개       ---------------->"
							+ "소고기그라탕 판매금액 : " + rs.getInt("소고기그라탕") * 23000 + "\n");
				}
				if (rs.getInt("돼지고기그라탕") != 0) {
					text.append("돼지고기그라탕    :   " + rs.getInt("돼지고기그라탕") + "개       ---------------->"
							+ "돼지고기그라탕 판매금액 : " + rs.getInt("돼지고기그라탕") * 25000 + "\n");
				}
				if (rs.getInt("토마토파스타") != 0) {
					text.append("토마토파스타       :   " + rs.getInt("토마토파스타") + "개       ---------------->"
							+ "토마토파스타 판매금액 : " + rs.getInt("토마토파스타") * 17000 + "\n");
				}
				if (rs.getInt("크림파스타") != 0) {
					text.append("크림파스타          :   " + rs.getInt("크림파스타") + "개       ---------------->"
							+ "크림파스타 판매금액 : " + rs.getInt("크림파스타") * 20000 + "\n");
				}
				if (rs.getInt("로제파스타") != 0) {
					text.append("로제파스타          :   " + rs.getInt("로제파스타") + "개       ---------------->"
							+ "로제파스타 판매금액 : " + rs.getInt("로제파스타") * 13000 + "\n");
				}
				if (rs.getInt("알리오올리오") != 0) {
					text.append("알리오올리오       :   " + rs.getInt("알리오올리오") + "개       ---------------->"
							+ "알리오올리오 판매금액 : " + rs.getInt("알리오올리오") * 35000 + "\n");
				}
				if (rs.getInt("연어파스타") != 0) {
					text.append("연어파스타          :   " + rs.getInt("연어파스타") + "개       ---------------->"
							+ "연어파스타 판매금액 : " + rs.getInt("연어파스타") * 40000 + "\n");
				}
				if (rs.getInt("냉파스타") != 0) {
					text.append("냉파스타             :   " + rs.getInt("냉파스타") + "개       ---------------->"
							+ "냉파스타 판매금액 : " + rs.getInt("냉파스타") * 15000 + "\n");
				}
				if (rs.getInt("토마토피자") != 0) {
					text.append("토마토피자          :   " + rs.getInt("토마토피자") + "개       ---------------->"
							+ "토마토피자 판매금액 : " + rs.getInt("토마토피자") * 30000 + "\n");
				}
				if (rs.getInt("비스마르크") != 0) {
					text.append("비스마르크          :   " + rs.getInt("비스마르크") + "개       ---------------->"
							+ "비스마르크 판매금액 : " + rs.getInt("비스마르크") * 25000 + "\n");
				}
				if (rs.getInt("풍기피자") != 0) {
					text.append("풍기피자             :   " + rs.getInt("풍기피자") + "개       ---------------->"
							+ "풍기피자 판매금액 : " + rs.getInt("풍기피자") * 10000 + "\n");
				}
				if (rs.getInt("크림피자") != 0) {
					text.append("크림피자             :   " + rs.getInt("크림피자") + "개       ---------------->"
							+ "크림피자 판매금액 : " + rs.getInt("크림피자") * 20000 + "\n");
				}
				if (rs.getInt("포테이토피자") != 0) {
					text.append("포테이토피자       :   " + rs.getInt("포테이토피자") + "개       ---------------->"
							+ "포테이토피자 판매금액 : " + rs.getInt("포테이토피자") * 25000 + "\n");
				}
				if (rs.getInt("스파이스초리죠") != 0) {
					text.append("스파이스초리죠    :   " + rs.getInt("스파이스초리죠") + "개       ---------------->"
							+ "스파이스초리죠 판매금액 : " + rs.getInt("스파이스초리죠") * 50000 + "\n");
				}
				if (rs.getInt("직원할인") != 0) {
					text.append("직원할인     :   " + rs.getInt("직원할인") + "번     ---------------->" + "직원 헐인금액 : "
							+ rs.getInt("직원할인") * 30000 + "\n");
				}
				if (rs.getInt("지인할인") != 0) {
					text.append("지인할인    :   " + rs.getInt("지인할인") + "번     ---------------->" + "지인 헐인금액 : "
							+ rs.getInt("지인할인") * 25000 + "\n");
				}
				if (rs.getInt("만원") != 0) {
					text.append("만원할인    :   " + rs.getInt("만원") + "번     ---------------->" + "만원 총헐인금액 : "
							+ rs.getInt("만원") * 10000 + "\n");
				}
				if (rs.getInt("이만원") != 0) {
					text.append("이만원할인    :   " + rs.getInt("이만원") + "번     ---------------->" + "이만원 총헐인금액 : "
							+ rs.getInt("이만원") * 20000 + "\n");
				}
				if (rs.getInt("오만원") != 0) {
					text.append("오만원할인    :   " + rs.getInt("오만원") + "번     ---------------->" + "오만원 총헐인금액 : "
							+ rs.getInt("오만원") * 50000 + "\n");
				}
				if (rs.getInt("할인전금액") != 0) {
					text.append("할인전금액    :   " + rs.getInt("할인전금액") + "\n");
				}
				if (rs.getInt("최종금액") != 0) {
					text.append("최종금액   :     " + rs.getInt("최종금액"));
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.setVisible(true);
		}

		if (e.getSource() == rr_btn[2]) {
			text.setFont(font);
			text.setText("구매 취소\n");
			p.tex[0].setText("구매 취소 파악");

			try {

				// rs = stmt.executeQuery(sql);
				stmt = con.createStatement();
				int result = stmt.executeUpdate("delete from BEFORESUM where beid=" + rs.getInt("id1"));
				int result1 = stmt.executeUpdate("delete from PIZZA where piid =" + rs.getInt("id1"));
				int result2 = stmt.executeUpdate("delete from PASTA where paid = " + rs.getInt("id1"));
				int result3 = stmt.executeUpdate("delete from GRATIN where gid = " + rs.getInt("id1"));
				int result4 = stmt.executeUpdate("delete from SPA where spaid = " + rs.getInt("id1"));
				int result5 = stmt.executeUpdate("delete from WHITE where wid  = " + rs.getInt("id1"));
				int result6 = stmt.executeUpdate("delete from RED where rid  = " + rs.getInt("id1"));
				int result7 = stmt.executeUpdate("delete from POS where id1  = " + rs.getInt("id1"));

				System.out.println("DELETE 성공");
				try {
					rs.next();
					p.tex[0].setText("구매자 Id : " + rs.getInt("id1"));
					text.append("구매자 ID     : " + rs.getInt("rid") + "\n");
					if (rs.getInt("말벡") != 0) {
						text.append("말벡              :   " + rs.getInt("말벡") + "개       ---------------->"
								+ "말벡 판매금액 : " + rs.getInt("말벡") * 30000 + " \n");
					}
					if (rs.getInt("앙케") != 0) {
						text.append("앙케              :   " + rs.getInt("앙케") + "개       ---------------->" + "앙케 판매금액: "
								+ rs.getInt("앙케") * 40000 + "\n");
					}
					if (rs.getInt("라크라사드") != 0) {
						text.append("라크라사드     :   " + rs.getInt("라크라사드") + "개       ---------------->" + "라크라사드판매금액 : "
								+ rs.getInt("라크라사드") * 43000 + "\n");
					}
					if (rs.getInt("몬테스") != 0) {
						text.append("몬테스           :   " + rs.getInt("몬테스") + "개       ---------------->"
								+ "몬테사 알파 판매금액 : " + rs.getInt("몬테스") * 75000 + "\n");
					}
					if (rs.getInt("엘레베") != 0) {
						text.append("엘레베           :   " + rs.getInt("엘레베") + "개       ---------------->" + "말벡 판매금액 : "
								+ rs.getInt("엘레베") * 60000 + "\n");
					}
					if (rs.getInt("페레제") != 0) {
						text.append("페레제           :   " + rs.getInt("페레제") + "개       ---------------->"
								+ "페레제 판매금액 : " + rs.getInt("페레제") * 45000 + "\n");
					}
					if (rs.getInt("리즐링") != 0) {
						text.append("리즐링          :   " + rs.getInt("리즐링") + "개       ---------------->" + "리즐링 판매금액 : "
								+ rs.getInt("리즐링") * 40000 + "\n");
					}
					if (rs.getInt("앙시앙") != 0) {
						text.append("앙시앙          :   " + rs.getInt("앙시앙") + "개       ---------------->" + "앙시앙 판매금액 : "
								+ rs.getInt("앙시앙") * 20000 + "\n");
					}
					if (rs.getInt("지아콘디") != 0) {
						text.append("지아콘디       :   " + rs.getInt("지아콘디") + "개       ---------------->" + "지아콘디 판매금액 : "
								+ rs.getInt("지아콘디") * 33000 + "\n");
					}
					if (rs.getInt("소비뇽") != 0) {
						text.append("소비뇽          :   " + rs.getInt("소비뇽") + "개       ---------------->" + "소비뇽 판매금액 : "
								+ rs.getInt("소비뇽") * 55000 + "\n");
					}
					if (rs.getInt("비안코") != 0) {
						text.append("비안코          :   " + rs.getInt("비안코") + "개       ---------------->" + "비안코 판매금액 : "
								+ rs.getInt("비안코") * 40000 + "\n");
					}
					if (rs.getInt("샤르도네") != 0) {
						text.append("샤르도네       :   " + rs.getInt("샤르도네") + "개       ---------------->" + "샤르도네 판매금액 : "
								+ rs.getInt("샤르도네") * 35000 + "\n");
					}
					if (rs.getInt("아리온") != 0) {
						text.append("아리온          :   " + rs.getInt("아리온") + "개       ---------------->" + "아리온 판매금액 : "
								+ rs.getInt("아리온") * 34000 + "\n");
					}
					if (rs.getInt("로제타") != 0) {
						text.append("로제타          :   " + rs.getInt("로제타") + "개       ---------------->" + "로제타 판매금액 : "
								+ rs.getInt("로제타") * 90000 + "\n");
					}
					if (rs.getInt("비안코") != 0) {
						text.append("비안코          :   " + rs.getInt("비안코") + "개       ---------------->" + "비안코 판매금액 : "
								+ rs.getInt("비안코") * 13000 + "\n");
					}
					if (rs.getInt("아스티") != 0) {
						text.append("아스티          :   " + rs.getInt("아스티") + "개       ---------------->" + "아스티 판매금액 : "
								+ rs.getInt("아스티") * 45000 + "\n");
					}
					if (rs.getInt("카바") != 0) {
						text.append("카바             :   " + rs.getInt("카바") + "개       ---------------->" + "카바 판매금액 : "
								+ rs.getInt("카바") * 50000 + "\n");
					}
					if (rs.getInt("브룻") != 0) {
						text.append("브룻             :   " + rs.getInt("브룻") + "개       ---------------->" + "브룻 판매금액 : "
								+ rs.getInt("브룻") * 35000 + "\n");
					}
					if (rs.getInt("토마토그라탕") != 0) {
						text.append("토마토그라탕       :   " + rs.getInt("토마토그라탕") + "개       ---------------->"
								+ "토마토그라탕 판매금액 : " + rs.getInt("토마토그라탕") * 12000 + "\n");
					}
					if (rs.getInt("크림그라탕") != 0) {
						text.append("크림그라탕          :   " + rs.getInt("크림그라탕") + "개       ---------------->"
								+ "크림그라탕 판매금액 : " + rs.getInt("크림그라탕") * 23000 + "\n");
					}
					if (rs.getInt("로제그라탕") != 0) {
						text.append("로제그라탕          :   " + rs.getInt("로제그라탕") + "개       ---------------->"
								+ "로제그라탕 판매금액 : " + rs.getInt("로제그라탕") * 43000 + "\n");
					}
					if (rs.getInt("닭고기그라탕") != 0) {
						text.append("닭고기그라탕       :   " + rs.getInt("닭고기그라탕") + "개       ---------------->"
								+ "닭고기그라탕 판매금액 : " + rs.getInt("닭고기그라탕") * 25000 + "\n");
					}
					if (rs.getInt("소고기그라탕") != 0) {
						text.append("소고기그라탕       :   " + rs.getInt("소고기그라탕") + "개       ---------------->"
								+ "소고기그라탕 판매금액 : " + rs.getInt("소고기그라탕") * 23000 + "\n");
					}
					if (rs.getInt("돼지고기그라탕") != 0) {
						text.append("돼지고기그라탕    :   " + rs.getInt("돼지고기그라탕") + "개       ---------------->"
								+ "돼지고기그라탕 판매금액 : " + rs.getInt("돼지고기그라탕") * 25000 + "\n");
					}
					if (rs.getInt("토마토파스타") != 0) {
						text.append("토마토파스타       :   " + rs.getInt("토마토파스타") + "개       ---------------->"
								+ "토마토파스타 판매금액 : " + rs.getInt("토마토파스타") * 17000 + "\n");
					}
					if (rs.getInt("크림파스타") != 0) {
						text.append("크림파스타          :   " + rs.getInt("크림파스타") + "개       ---------------->"
								+ "크림파스타 판매금액 : " + rs.getInt("크림파스타") * 20000 + "\n");
					}
					if (rs.getInt("로제파스타") != 0) {
						text.append("로제파스타          :   " + rs.getInt("로제파스타") + "개       ---------------->"
								+ "로제파스타 판매금액 : " + rs.getInt("로제파스타") * 13000 + "\n");
					}
					if (rs.getInt("알리오올리오") != 0) {
						text.append("알리오올리오       :   " + rs.getInt("알리오올리오") + "개       ---------------->"
								+ "알리오올리오 판매금액 : " + rs.getInt("알리오올리오") * 35000 + "\n");
					}
					if (rs.getInt("연어파스타") != 0) {
						text.append("연어파스타          :   " + rs.getInt("연어파스타") + "개       ---------------->"
								+ "연어파스타 판매금액 : " + rs.getInt("연어파스타") * 40000 + "\n");
					}
					if (rs.getInt("냉파스타") != 0) {
						text.append("냉파스타             :   " + rs.getInt("냉파스타") + "개       ---------------->"
								+ "냉파스타 판매금액 : " + rs.getInt("냉파스타") * 15000 + "\n");
					}
					if (rs.getInt("토마토피자") != 0) {
						text.append("토마토피자          :   " + rs.getInt("토마토피자") + "개       ---------------->"
								+ "토마토피자 판매금액 : " + rs.getInt("토마토피자") * 30000 + "\n");
					}
					if (rs.getInt("비스마르크") != 0) {
						text.append("비스마르크          :   " + rs.getInt("비스마르크") + "개       ---------------->"
								+ "비스마르크 판매금액 : " + rs.getInt("비스마르크") * 25000 + "\n");
					}
					if (rs.getInt("풍기피자") != 0) {
						text.append("풍기피자             :   " + rs.getInt("풍기피자") + "개       ---------------->"
								+ "풍기피자 판매금액 : " + rs.getInt("풍기피자") * 10000 + "\n");
					}
					if (rs.getInt("크림피자") != 0) {
						text.append("크림피자             :   " + rs.getInt("크림피자") + "개       ---------------->"
								+ "크림피자 판매금액 : " + rs.getInt("크림피자") * 20000 + "\n");
					}
					if (rs.getInt("포테이토피자") != 0) {
						text.append("포테이토피자       :   " + rs.getInt("포테이토피자") + "개       ---------------->"
								+ "포테이토피자 판매금액 : " + rs.getInt("포테이토피자") * 25000 + "\n");
					}
					if (rs.getInt("스파이스초리죠") != 0) {
						text.append("스파이스초리죠    :   " + rs.getInt("스파이스초리죠") + "개       ---------------->"
								+ "스파이스초리죠 판매금액 : " + rs.getInt("스파이스초리죠") * 50000 + "\n");
					}
					if (rs.getInt("직원할인") != 0) {
						text.append("직원할인     :   " + rs.getInt("직원할인") + "번     ---------------->" + "직원 헐인금액 : "
								+ rs.getInt("직원할인") * 30000 + "\n");
					}
					if (rs.getInt("지인할인") != 0) {
						text.append("지인할인    :   " + rs.getInt("지인할인") + "번     ---------------->" + "지인 헐인금액 : "
								+ rs.getInt("지인할인") * 25000 + "\n");
					}
					if (rs.getInt("만원") != 0) {
						text.append("만원할인    :   " + rs.getInt("만원") + "번     ---------------->" + "만원 총헐인금액 : "
								+ rs.getInt("만원") * 10000 + "\n");
					}
					if (rs.getInt("이만원") != 0) {
						text.append("이만원할인    :   " + rs.getInt("이만원") + "번     ---------------->" + "이만원 총헐인금액 : "
								+ rs.getInt("이만원") * 20000 + "\n");
					}
					if (rs.getInt("오만원") != 0) {
						text.append("오만원할인    :   " + rs.getInt("오만원") + "번     ---------------->" + "오만원 총헐인금액 : "
								+ rs.getInt("오만원") * 50000 + "\n");
					}
					if (rs.getInt("할인전금액") != 0) {
						text.append("할인전금액    :   " + rs.getInt("할인전금액") + "\n");
					}
					if (rs.getInt("최종금액") != 0) {
						text.append("최종금액   :     " + rs.getInt("최종금액"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("DELETE 실패");
				e1.printStackTrace();
			}

			p.setVisible(true);
		}

	}
}
