import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class main implements ActionListener {
	public pos p;

	public main(pos po) {
		this.p = po;
	}

	String fb[] = { "레드와인", "화이트와인", "스파클링와인", "그라탕", "파스타", "피자", "기타" };
	JButton food_btn[] = new JButton[7]; // --> 음식메뉴 버튼

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// 왼쪽 상단 판매 버튼 클릭시 액션
		if (e.getSource() == p.menu_btn[0]) {
			p.sub_panel[3].removeAll(); // p.sub_panel[3]에 대한 모든 정보 삭제

			// 이미지 삽입 --> 메인화면
			String image = "image/main_pic.jpg";
			ImageIcon mainc = new ImageIcon(image);
			JLabel main = new JLabel();
			main.setIcon(mainc);
			///////////////////////

			p.sub_panel[0].removeAll(); // 음식메뉴 p.sub_panel[0]을 전부삭제
			p.sub_panel[0] = new JPanel(new GridLayout(0, 1)); // 메뉴버튼 재 생성
			p.sub_panel[0].setBorder(BorderFactory.createTitledBorder("FOOD"));

			// 오른쪽 상단 메뉴버튼과 음식 매뉴 버튼 생성
			for (int i = 0; i < 7; i++) {
				food_btn[i] = new JButton(fb[i]); // 오른쪽 상단 메뉴버튼
			}

			for (int i = 0; i < 7; i++) {
				p.sub_panel[0].add(p.food_btn[i]);
			}
			p.tex[0].setForeground(Color.GRAY);
			p.tex[0].setText("판매화면\n\n");
			p.main_panel[1].add(p.sub_panel[0], BorderLayout.EAST);
			p.main_panel[1].add(main, BorderLayout.CENTER);
			p.setTitle("문래친친_판매");
			p.setVisible(true);

		}
	}
}