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

	String fb[] = { "�������", "ȭ��Ʈ����", "����Ŭ������", "�׶���", "�Ľ�Ÿ", "����", "��Ÿ" };
	JButton food_btn[] = new JButton[7]; // --> ���ĸ޴� ��ư

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// ���� ��� �Ǹ� ��ư Ŭ���� �׼�
		if (e.getSource() == p.menu_btn[0]) {
			p.sub_panel[3].removeAll(); // p.sub_panel[3]�� ���� ��� ���� ����

			// �̹��� ���� --> ����ȭ��
			String image = "image/main_pic.jpg";
			ImageIcon mainc = new ImageIcon(image);
			JLabel main = new JLabel();
			main.setIcon(mainc);
			///////////////////////

			p.sub_panel[0].removeAll(); // ���ĸ޴� p.sub_panel[0]�� ���λ���
			p.sub_panel[0] = new JPanel(new GridLayout(0, 1)); // �޴���ư �� ����
			p.sub_panel[0].setBorder(BorderFactory.createTitledBorder("FOOD"));

			// ������ ��� �޴���ư�� ���� �Ŵ� ��ư ����
			for (int i = 0; i < 7; i++) {
				food_btn[i] = new JButton(fb[i]); // ������ ��� �޴���ư
			}

			for (int i = 0; i < 7; i++) {
				p.sub_panel[0].add(p.food_btn[i]);
			}
			p.tex[0].setForeground(Color.GRAY);
			p.tex[0].setText("�Ǹ�ȭ��\n\n");
			p.main_panel[1].add(p.sub_panel[0], BorderLayout.EAST);
			p.main_panel[1].add(main, BorderLayout.CENTER);
			p.setTitle("����ģģ_�Ǹ�");
			p.setVisible(true);

		}
	}
}