import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MyButtonCalc implements ActionListener {
	public pos p;

	ResultSet rs;
	Statement stmt;
	Connection con = null;
	PreparedStatement pstmt;

	public MyButtonCalc(pos po) throws SQLException {

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
			System.out.println("JDBC 적재 오류...동건");
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select * from POS, RED,WHITE,SPA,GRATIN,PASTA,PIZZA where POS.id1 = RED.rid and POS.id1 = WHITE.wid and POS.id1 = SPA.spaid and POS.id1 = GRATIN.gid and POS.id1 = PASTA.paid and POS.id1 = PIZZA.piid");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Statement 생성 오류...동건");
		}

	}

	public int beforesum = 0; // 할인전금액
	public int totalsum = 0; // 최종구매가격
	public int totalnum = 0; // 최종구매수량
	public int snum = 0; // 할인홧수
	public int stotal = 0; // 할인 금액
	// public int C_id = 1; // 구매자 ID
	public int Red[] = new int[6];
	public int White[] = new int[6];
	public int Spa[] = new int[6];
	public int Gratin[] = new int[6];
	public int Pasta[] = new int[6];
	public int Pizza[] = new int[6];
	public int before[] = new int[6];

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		String mr[] = { "트라피체 말백", "앙케", "라크라사드 블랙", "몬테스 알파 블랙", "엘레베", "페레제 크루즈" }; // 레드와인명
		String mw[] = { "리즐링", "앙시앙땅", "지아콘디", "소비뇽 블랑", "비안코", "샤르도네" }; // 화이트와인명
		String ms[] = { "아리온 모스까토", "로제타", "비안코", "아스티", "카바", "브룻" }; // 스파클링와인명
		String mg[] = { "토마토 그라탕", "크림 그라탕", "로제 그라탕", "닭고기 그라탕", "소고기 그라탕", "돼지고기 그라탕" }; // 그라탕명
		String mp[] = { "토마토 파스타", "크림 파스타", "로제 파스타", "알리오 올리오", "연어 파스타", "냉파스타" }; // 파스타명
		String mi[] = { "마르게리따", "비스마르크", "픙기피자", "크림피자", "포테이토피자", "스파이스초리죠" }; // 피자명
		String mk[] = { "직원할인", "지인할인", "10000원 할인", "20000원 할인", "25000원 할인", "50000원 할인" }; // 할인명

		int costOfRedWine[] = { 30000, 40000, 43000, 75000, 60000, 45000 }; // 레드와인 가격
		int costOfWhiteWine[] = { 40000, 20000, 33000, 55000, 40000, 35000 }; // 화이트와인 가격
		int costOfSpaWine[] = { 34000, 90000, 13000, 45000, 50000, 35000 }; // 스파클링와인 가격

		int costOfGratin[] = { 12000, 23000, 43000, 25000, 23000, 25000 }; // 그라탕 가격

		int costOfPasta[] = { 17000, 20000, 13000, 35000, 40000, 15000 };
		// 파스타 가격
		int costOfPizza[] = { 17000, 20000, 13000, 55000, 30000, 35000 }; // 피자 가격
		int costOfSome[] = { 30000, 25000, 10000, 20000, 25000, 50000 }; // 기타 할인

		// 레드와인 리스너
		for (int i = 0; i < p.menu_RedWine.length; i++) {
			if (event.getSource() == p.menu_RedWine[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfRedWine[i];
				totalsum += costOfRedWine[i];
				totalnum += 1;
				Red[i] += 1;
				p.tex[0].append(mr[i] + " 이(가) 선택되었습니다. ---> 가격 : " + costOfRedWine[i] + "\n");
				p.tex[0].append("선택된 항목 개수 : " + totalnum + "  할인횟수 : " + snum + "  할인된 금액 : " + stotal
						+ " ----> 최종금액 : " + totalsum + "\n\n");

			}

		}

		// 화이트와인 리스너
		for (int i = 0; i < p.menu_WhiteWine.length; i++) {
			if (event.getSource() == p.menu_WhiteWine[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfWhiteWine[i];
				totalsum += costOfWhiteWine[i];
				totalnum += 1;
				White[i] += 1;

				p.tex[0].append(mw[i] + " 이(가) 선택되었습니다. ---> 가격 : " + costOfWhiteWine[i] + "\n");
				p.tex[0].append("선택된 항목 개수 : " + totalnum + "  할인횟수 : " + snum + "  할인된 금액 : " + stotal
						+ " ----> 최종금액 : " + totalsum + "\n\n");
			}
		}

		// 스파크링 버튼 리스너
		for (int i = 0; i < p.menu_SpaWine.length; i++) {
			if (event.getSource() == p.menu_SpaWine[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfSpaWine[i];
				totalsum += costOfSpaWine[i];
				totalnum += 1;
				Spa[i] += 1;

				p.tex[0].append(ms[i] + " 이(가) 선택되었습니다. ---> 가격 : " + costOfSpaWine[i] + "\n");
				p.tex[0].append("선택된 항목 개수 : " + totalnum + "  할인횟수 : " + snum + "  할인된 금액 : " + stotal
						+ " ----> 최종금액 : " + totalsum + "\n\n");
			}
		}

		// 그라탕 버튼 리스너
		for (int i = 0; i < p.menu_Gratin.length; i++) {
			if (event.getSource() == p.menu_Gratin[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfGratin[i];
				totalsum += costOfGratin[i];
				totalnum += 1;
				Gratin[i] += 1;

				p.tex[0].append(mg[i] + " 이(가) 선택되었습니다. ---> 가격 : " + costOfGratin[i] + "\n");
				p.tex[0].append("선택된 항목 개수 : " + totalnum + "  할인횟수 : " + snum + "  할인된 금액 : " + stotal
						+ " ----> 최종금액 : " + totalsum + "\n\n");
			}
		}

		// 파스타버튼 리스너
		for (int i = 0; i < p.menu_Pasta.length; i++) {
			if (event.getSource() == p.menu_Pasta[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfPasta[i];
				totalsum += costOfPasta[i];
				totalnum += 1;
				Pasta[i] += 1;

				p.tex[0].append(mp[i] + " 이(가) 선택되었습니다. ---> 가격 : " + costOfPasta[i] + "\n");
				p.tex[0].append("선택된 항목 개수 : " + totalnum + "  할인횟수 : " + snum + "  할인된 금액 : " + stotal
						+ " ----> 최종금액 : " + totalsum + "\n\n");
			}
		}

		// 피자버튼 리스너
		for (int i = 0; i < p.menu_Pizza.length; i++) {
			if (event.getSource() == p.menu_Pizza[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfPizza[i];
				totalsum += costOfPizza[i];
				totalnum += 1;
				Pizza[i] += 1;

				p.tex[0].append(mi[i] + " 이(가) 선택되었습니다. ---> 가격 : " + costOfPizza[i] + "\n");
				p.tex[0].append("선택된 항목 개수 : " + totalnum + "  할인횟수 : " + snum + "  할인된 금액 : " + stotal
						+ " ----> 최종금액 : " + totalsum + "\n\n");
			}
		}

		// 기타버튼 리스너
		for (int i = 0; i < p.menu_some.length; i++) {
			if (event.getSource() == p.menu_some[i]) {

				if (totalsum < 50000) {
					p.tex[0].setForeground(Color.RED);
					p.tex[0].setText("50000원 이하는 할인이 불가능합니다. \n다시 주문 부탁드리겠습니다. \n");
				} else if (totalsum > 50000) {
					p.tex[0].setForeground(Color.BLACK);
					totalsum -= costOfSome[i];
					snum += 1;
					before[i] += 1;

					stotal += costOfSome[i];
					p.tex[0].append(mk[i] + "이(가) 선택되었습니다. ---> 할인가격 : " + costOfSome[i] + "\n");
					p.tex[0].append(
							"선택된 항목 개수 : " + totalnum + "  할인횟수 : " + snum + " ----> 최종금액 : " + totalsum + "\n\n");
				}
			}
		}

		// order버튼 리스너
		if (event.getSource() == p.order) {

			if (totalsum == 0) {
				p.tex[0].setForeground(Color.RED);
				p.tex[0].setText("선택된 메뉴가 없습니다. \n");

				// 팝업창 생성 - 주문오류 아이콘 생성
				JOptionPane.showMessageDialog(null, "다시 주문 부탁드리겠습니다.", "문래친친_주문오류", JOptionPane.QUESTION_MESSAGE);

			} else {
				p.tex[0].setText("판매정보\n");
				p.tex[0].setForeground(Color.BLUE);
				p.tex[0].append("-----------------------------\n");

				p.tex[0].append("\n" + "선택된 항목 개수 : " + totalnum + "\n");

				p.tex[0].append("-----------------------------\n구매수량\n");
				// 선택된 항목들의 개수
				for (int i = 0; i < 6; i++) {

					if (Red[i] != 0) {
						p.tex[0].append(mr[i] + " 의 구매수량 : " + Red[i] + "\n");
					}
					if (White[i] != 0) {
						p.tex[0].append(mw[i] + " 의 구매수량 : " + White[i] + "\n");
					}
					if (Spa[i] != 0) {
						p.tex[0].append(ms[i] + " 의 구매수량 : " + Spa[i] + "\n");
					}
					if (Gratin[i] != 0) {
						p.tex[0].append(mg[i] + " 의 구매수량 : " + Gratin[i] + "\n");
					}
					if (Pasta[i] != 0) {
						p.tex[0].append(mp[i] + " 의 구매수량 : " + Pasta[i] + "\n");
					}
					if (Pizza[i] != 0) {
						p.tex[0].append(mi[i] + " 의 구매수량 : " + Pizza[i] + "\n");
					}
				}
				//////////////////////////////////////////
				try {
					stmt = con.createStatement();

					int result = stmt.executeUpdate("insert into POS (할인횟수,할인금액,최종금액,할인전금액) value( '" + snum + "', '"
							+ stotal + "' ,'" + totalsum + "'," + beforesum + ")");

					System.out.println("POS_table INSERT 성공");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("POS_tableINSERT 실패");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt.executeUpdate(
							"insert into RED (말벡, 앙케, 라크라사드, 몬테스, 엘레베, 페레제) value( '" + Red[0] + "', '" + Red[1]
									+ "' ,'" + Red[2] + "' ,'" + Red[3] + "' ,'" + Red[4] + "' ," + Red[5] + ")");

					System.out.println("RED_table INSERT 성공");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("RED_table INSERT 실패");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt.executeUpdate("insert into WHITE (리즐링, 앙시앙, 지아콘디, 소비뇽, 비안코, 샤르도네) value( '"
							+ White[0] + "', '" + White[1] + "' ,'" + White[2] + "' ,'" + White[3] + "' ,'" + White[4]
							+ "' ," + White[5] + ")");

					System.out.println("WHITE_INSERT 성공");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("WHITE_INSERT 실패");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt.executeUpdate(
							"insert into SPA (아리온, 로제타, 비안코, 아스티, 카바, 브룻) value('" + Spa[0] + "', '" + Spa[1] + "' ,'"
									+ Spa[2] + "' ,'" + Spa[3] + "' ,'" + Spa[4] + "' ," + Spa[5] + ")");

					System.out.println("SPA_INSERT 성공");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("SPA_INSERT 실패");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt
							.executeUpdate("insert into GRATIN (토마토그라탕, 크림그라탕, 로제그라탕, 닭고기그라탕, 소고기그라탕, 돼지고기그라탕) value( '"
									+ Gratin[0] + "', '" + Gratin[1] + "' ,'" + Gratin[2] + "' ,'" + Gratin[3] + "' ,'"
									+ Gratin[4] + "' ," + Gratin[5] + ")");

					System.out.println("GRA_INSERT 성공");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("GRA_INSERT 실패");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt
							.executeUpdate("insert into PASTA (토마토파스타, 크림파스타, 로제파스타, 알리오올리오, 연어파스타, 냉파스타) value( '"
									+ Pasta[0] + "', '" + Pasta[1] + "' ,'" + Pasta[2] + "' ,'" + Pasta[3] + "' ,'"
									+ Pasta[4] + "' ," + Pasta[5] + ")");

					System.out.println("Pasta_INSERT 성공");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Pasta_INSERT 실패");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt
							.executeUpdate("insert into PIZZA (토마토피자, 비스마르크, 풍기피자, 크림피자, 포테이토피자, 스파이스초리죠) value( '"
									+ Pizza[0] + "', '" + Pizza[1] + "' ,'" + Pizza[2] + "' ,'" + Pizza[3] + "' ,'"
									+ Pizza[4] + "' ," + Pizza[5] + ")");

					System.out.println("Pizza_INSERT 성공");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Pizza_INSERT 실패");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt.executeUpdate("insert into BEFORESUM (직원할인, 지인할인, 만원, 이만원, 이만오천원, 오만원) value('"
							+ before[0] + "', '" + before[1] + "' ,'" + before[2] + "' ,'" + before[3] + "' ,'"
							+ before[4] + "' ," + before[5] + ")");

					System.out.println("Beforsum_INSERT 성공");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Beforesum_INSERT 실패");
					e.printStackTrace();
				}

				/////////////////////////////////////////
				p.tex[0].append("-----------------------------\n");

				p.tex[0].append("할인 횟수 : " + snum + "  ------>" + "  할인 금액 : " + stotal
						+ "\n-----------------------------\n" + "할인전 금액 : " + beforesum
						+ "\n-----------------------------\n" + "최종가격:" + totalsum + "원!!" + "\n");

				// 팝업창 생성 - 주문확인 아이콘 생성

				JOptionPane.showMessageDialog(null, "최종가격:" + totalsum + "원!!", "문래친친_주문확인",
						JOptionPane.INFORMATION_MESSAGE);

				totalsum = 0; // 주문버튼을 누르고 난뒤에는 reset이 되도록 totalsum을 0으로 바꾼다
				totalnum = 0; // 주문버튼을 누르고 난뒤에는 reset이 되도록 totalnum을 0으로 바꾼다
				snum = 0; // 주문버튼을 누르고 난뒤에는 reset이 되도록 snum을 0으로 바꾼다
				beforesum = 0;
				stotal = 0;

				for (int i = 0; i < 6; i++) {
					Red[i] = 0;
					White[i] = 0;
					Spa[i] = 0;
					Gratin[i] = 0;
					Pasta[i] = 0;
					Pizza[i] = 0;
				}

			}
		}

		// cancel 버튼 리스너
		if (event.getSource() == p.cancel) {
			p.tex[0].setForeground(Color.RED);
			totalsum = 0;
			totalnum = 0;
			snum = 0;
			beforesum = 0;
			stotal = 0;

			for (int i = 0; i < 6; i++) {
				Red[i] = 0;
				White[i] = 0;
				Spa[i] = 0;
				Gratin[i] = 0;
				Pasta[i] = 0;
				Pizza[i] = 0;
			}

			p.tex[0].setText("다시 주문 부탁드리겠습니다. \n");

			// 팝업창 생성 - 취소 아이콘 등록
			try {

				String sql = "select id1 from POS";
				rs = stmt.executeQuery(sql);
				rs.next();
				JOptionPane.showMessageDialog(null, "\n" + "주문이 취소 되었습니다.\n" + "다시 주문 부탁드리겠습니다.", "문래친친_주문취소",
						JOptionPane.WARNING_MESSAGE);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		// call 버튼 리스너
		if (event.getSource() == p.call) {
			p.tex[0].setForeground(Color.ORANGE);
			p.tex[0].setText("잠시만 기다려주세요....서비스 관리자를 호출하겠습니다.\n문래친친\nSINCE 2018\nHEAD CHEIF 인정현 \nPOS MADE BY 김동건");

			// 팝업창 생성 - 취소 아이콘 등록
			JOptionPane.showMessageDialog(null, "잠시만 기다려주세요.\n" + "서비스 관리자를 호출하겠습니다.", "문래친친_관리자호출",
					JOptionPane.INFORMATION_MESSAGE);

		}

		// logout 버튼 리스너
		if (event.getSource() == p.logout) {
			p.tex[0].setForeground(Color.ORANGE);
			p.tex[0].setText("pos를 종료하겠습니다. 다시 로그인해주세요!");

			// 팝업창 생성 - 취소 아이콘 등록
			JOptionPane.showMessageDialog(null, "POS 로그아웃!", "문래친친_로그아웃", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
