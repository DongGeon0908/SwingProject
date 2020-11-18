import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyButtonListener implements ActionListener {

	// pos 객체 불러오기
	public pos p;

	public MyButtonListener(pos po) {
		this.p = po;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// 레드와인 버튼을 누를시 레드와인 종류 출력
		// 패널을 생성하고 메인 패널에 삽입

		//레드와인 사진첨부
		ImageIcon Ired[] = new ImageIcon[6];
		JLabel Lred[] = new JLabel[6];
		String Sred[] = { "image/red.jpg", "image/red1.jpg", "image/red2.jpg", "image/red3.jpg", "image/red4.jpg",
				"image/red5.jpg" };
		
		//화이트 사진첨부
		ImageIcon Iwhite[] = new ImageIcon[6];
		JLabel Lwhite[] = new JLabel[6];
		String Swhite[] = { "image/white.jpg", "image/white1.jpg", "image/white2.jpg", "image/white3.jpg",
				"image/white4.jpg", "image/white5.jpg" };
		
		//스파클링 사진첨부
		ImageIcon Ispa[] = new ImageIcon[6];
		JLabel Lspa[] = new JLabel[6];
		String Sspa[] = { "image/s.jpg", "image/s1.jpg", "image/s2.jpg", "image/s3.jpg", "image/s4.jpg",
				"image/s5.jpg" };

		//그라탕 사진첨부
		ImageIcon Ig[] = new ImageIcon[6];
		JLabel Lg[] = new JLabel[6];
		String Sg[] = { "image/g.jpg", "image/g1.jpg", "image/g2.jpg", "image/g3.jpg", "image/g4.jpg", "image/g5.jpg" };

		//파스타 사진첨부
		ImageIcon Ipa[] = new ImageIcon[6];
		JLabel Lpa[] = new JLabel[6];
		String Spa[] = { "image/pa.jpg", "image/pa1.jpg", "image/pa2.jpg", "image/pa3.jpg", "image/pa4.jpg",
				"image/pa5.jpg" };

		//피자 사진첨부
		ImageIcon Ipi[] = new ImageIcon[6];
		JLabel Lpi[] = new JLabel[6];
		String Spi[] = { "image/pi.jpg", "image/pi1.jpg", "image/pi2.jpg", "image/pi3.jpg", "image/pi4.jpg",
				"image/pi5.jpg" };

		//기타 사진첨부
		ImageIcon Ik[] = new ImageIcon[6];
		JLabel Lk[] = new JLabel[6];
		String Sk[] = { "image/ms.jpg", "image/ms1.png", "image/ms2.jpg", "image/ms3.jpg", "image/ms4.jpg",
				"image/ms5.jpg" };

		// 메뉴명들
		String mr[] = { "트라피체 말백", "앙케", "라크라사드 블랙", "몬테스 알파 블랙", "엘레베", "페레제 크루즈" };
		String mw[] = { "리즐링", "앙시앙땅", "지아콘디", "소비뇽 블랑", "비안코", "샤르도네" };
		String ms[] = { "아리온 모스까토", "로제타", "비안코", "아스티", "카바", "브룻" };
		String mg[] = { "토마토 그라탕", "크림 그라탕", "로제 그라탕", "닭고기 그라탕", "소고기 그라탕", "돼지고기 그라탕" };
		String mp[] = { "토마토 파스타", "크림 파스타", "로제 파스타", "알리오 올리오", "연어 파스타", "냉파스타" };
		String mi[] = { "마르게리따", "비스마르크", "픙기피자", "크림피자", "포테이토피자", "스파이스초리죠" };
		String mk[] = { "직원할인", "지인할인", "10000원 할인", "20000원 할인", "25000원 할인", "50000원 할인" };

		// 레드와인 버튼을 누를시 화이트와인 종류 출력
		// 패널을 생성하고 메인 패널에 삽입
		if (e.getSource() == p.food_btn[0]) {

			p.sub_panel[3].removeAll();
			p.sub_panel[3] = new JPanel(new GridLayout(0, 3, 10, 15));

			for (int i = 0; i < 6; i++) {
				p.sub_panel[3].add(p.menu_RedWine[i]);

				Ired[i] = new ImageIcon(Sred[i]);
				Lred[i] = new JLabel();
				Lred[i].setIcon(Ired[i]);

				p.menu_RedWine[i].add(Lred[i]);
				p.menu_RedWine[i].setBorder(BorderFactory.createTitledBorder(mr[i]));

				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("레드와인"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 화이트와인 버튼을 누를시 화이트와인 종류 출력
		// 패널을 생성하고 메인 패널에 삽입
		if (e.getSource() == p.food_btn[1]) {
			p.sub_panel[3].removeAll();
			p.sub_panel[3] = new JPanel(new GridLayout(0, 3, 10, 15));

			for (int i = 0; i < 6; i++) {
				p.sub_panel[3].add(p.menu_WhiteWine[i]);

				Iwhite[i] = new ImageIcon(Swhite[i]);
				Lwhite[i] = new JLabel();
				Lwhite[i].setIcon(Iwhite[i]);
				p.menu_WhiteWine[i].add(Lwhite[i]);
				p.menu_WhiteWine[i].setBorder(BorderFactory.createTitledBorder(mw[i]));
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("화이트와인"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 스파클링와인 버튼을 누를시 스파클링와인 종류 출력
		// 패널을 생성하고 메인 패널에 삽입
		if (e.getSource() == p.food_btn[2]) {
			p.sub_panel[3].removeAll();
			p.sub_panel[3] = new JPanel(new GridLayout(0, 3, 10, 15));

			for (int i = 0; i < 6; i++) {
				p.sub_panel[3].add(p.menu_SpaWine[i]);

				Ispa[i] = new ImageIcon(Sspa[i]);
				Lspa[i] = new JLabel();
				Lspa[i].setIcon(Ispa[i]);
				p.menu_SpaWine[i].add(Lspa[i]);
				p.menu_SpaWine[i].setBorder(BorderFactory.createTitledBorder(ms[i]));
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("스파클링와인"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 그라탕 버튼을 누를시 그라탕 종류 출력
		// 패널을 생성하고 메인 패널에 삽입
		if (e.getSource() == p.food_btn[3]) {
			p.sub_panel[3].removeAll();
			p.sub_panel[3] = new JPanel(new GridLayout(0, 3, 10, 15));

			for (int i = 0; i < 6; i++) {
				p.sub_panel[3].add(p.menu_Gratin[i]);

				Ig[i] = new ImageIcon(Sg[i]);
				Lg[i] = new JLabel();
				Lg[i].setIcon(Ig[i]);
				p.menu_Gratin[i].add(Lg[i]);
				p.menu_Gratin[i].setBorder(BorderFactory.createTitledBorder(mg[i]));
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("그라탕"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 파스타 버튼을 누를시 파스타 종류를 출력
		// 패널을 생성하고 메인 패널에 삽입
		if (e.getSource() == p.food_btn[4]) {
			p.sub_panel[3].removeAll();
			p.sub_panel[3] = new JPanel(new GridLayout(0, 3, 10, 15));

			for (int i = 0; i < 6; i++) {

				p.sub_panel[3].add(p.menu_Pasta[i]);

				Ipa[i] = new ImageIcon(Spa[i]);
				Lpa[i] = new JLabel();
				Lpa[i].setIcon(Ipa[i]);
				p.menu_Pasta[i].add(Lpa[i]);
				p.menu_Pasta[i].setBorder(BorderFactory.createTitledBorder(mp[i]));
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("파스타"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 피자버튼을 누를시 피자 종류를 출력
		// 패널을 생성하고 메인 패널에 삽입
		if (e.getSource() == p.food_btn[5]) {
			p.sub_panel[3].removeAll();
			p.sub_panel[3] = new JPanel(new GridLayout(0, 3, 10, 15));

			for (int i = 0; i < 6; i++) {

				p.sub_panel[3].add(p.menu_Pizza[i]);

				Ipi[i] = new ImageIcon(Spi[i]);
				Lpi[i] = new JLabel();
				Lpi[i].setIcon(Ipi[i]);
				p.menu_Pizza[i].add(Lpi[i]);
				p.menu_Pizza[i].setBorder(BorderFactory.createTitledBorder(mi[i]));
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("피자"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// 기타버튼을 누를시 기타 종류를 출력
		// 패널을 생성하고 메인 패널에 삽입
		if (e.getSource() == p.food_btn[6]) {
			p.sub_panel[3].removeAll();
			p.sub_panel[3] = new JPanel(new GridLayout(0, 3, 10, 15));

			for (int i = 0; i < 6; i++) {

				p.sub_panel[3].add(p.menu_some[i]);

				Ik[i] = new ImageIcon(Sk[i]);
				Lk[i] = new JLabel();
				Lk[i].setIcon(Ik[i]);
				p.menu_some[i].add(Lk[i]);
				p.menu_some[i].setBorder(BorderFactory.createTitledBorder(mk[i]));
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("할인"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}
	}
}
