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

	// pos ��ü �ҷ�����
	public pos p;

	public MyButtonListener(pos po) {
		this.p = po;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// ������� ��ư�� ������ ������� ���� ���
		// �г��� �����ϰ� ���� �гο� ����

		//������� ����÷��
		ImageIcon Ired[] = new ImageIcon[6];
		JLabel Lred[] = new JLabel[6];
		String Sred[] = { "image/red.jpg", "image/red1.jpg", "image/red2.jpg", "image/red3.jpg", "image/red4.jpg",
				"image/red5.jpg" };
		
		//ȭ��Ʈ ����÷��
		ImageIcon Iwhite[] = new ImageIcon[6];
		JLabel Lwhite[] = new JLabel[6];
		String Swhite[] = { "image/white.jpg", "image/white1.jpg", "image/white2.jpg", "image/white3.jpg",
				"image/white4.jpg", "image/white5.jpg" };
		
		//����Ŭ�� ����÷��
		ImageIcon Ispa[] = new ImageIcon[6];
		JLabel Lspa[] = new JLabel[6];
		String Sspa[] = { "image/s.jpg", "image/s1.jpg", "image/s2.jpg", "image/s3.jpg", "image/s4.jpg",
				"image/s5.jpg" };

		//�׶��� ����÷��
		ImageIcon Ig[] = new ImageIcon[6];
		JLabel Lg[] = new JLabel[6];
		String Sg[] = { "image/g.jpg", "image/g1.jpg", "image/g2.jpg", "image/g3.jpg", "image/g4.jpg", "image/g5.jpg" };

		//�Ľ�Ÿ ����÷��
		ImageIcon Ipa[] = new ImageIcon[6];
		JLabel Lpa[] = new JLabel[6];
		String Spa[] = { "image/pa.jpg", "image/pa1.jpg", "image/pa2.jpg", "image/pa3.jpg", "image/pa4.jpg",
				"image/pa5.jpg" };

		//���� ����÷��
		ImageIcon Ipi[] = new ImageIcon[6];
		JLabel Lpi[] = new JLabel[6];
		String Spi[] = { "image/pi.jpg", "image/pi1.jpg", "image/pi2.jpg", "image/pi3.jpg", "image/pi4.jpg",
				"image/pi5.jpg" };

		//��Ÿ ����÷��
		ImageIcon Ik[] = new ImageIcon[6];
		JLabel Lk[] = new JLabel[6];
		String Sk[] = { "image/ms.jpg", "image/ms1.png", "image/ms2.jpg", "image/ms3.jpg", "image/ms4.jpg",
				"image/ms5.jpg" };

		// �޴����
		String mr[] = { "Ʈ����ü ����", "����", "��ũ���� ��", "���׽� ���� ��", "������", "�䷹�� ũ����" };
		String mw[] = { "����", "�ӽþӶ�", "�����ܵ�", "�Һ� ���", "�����", "��������" };
		String ms[] = { "�Ƹ��� �𽺱���", "����Ÿ", "�����", "�ƽ�Ƽ", "ī��", "���" };
		String mg[] = { "�丶�� �׶���", "ũ�� �׶���", "���� �׶���", "�߰�� �׶���", "�Ұ�� �׶���", "������� �׶���" };
		String mp[] = { "�丶�� �Ľ�Ÿ", "ũ�� �Ľ�Ÿ", "���� �Ľ�Ÿ", "�˸��� �ø���", "���� �Ľ�Ÿ", "���Ľ�Ÿ" };
		String mi[] = { "�����Ը���", "�񽺸���ũ", "�V������", "ũ������", "������������", "�����̽��ʸ���" };
		String mk[] = { "��������", "��������", "10000�� ����", "20000�� ����", "25000�� ����", "50000�� ����" };

		// ������� ��ư�� ������ ȭ��Ʈ���� ���� ���
		// �г��� �����ϰ� ���� �гο� ����
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

				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("�������"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// ȭ��Ʈ���� ��ư�� ������ ȭ��Ʈ���� ���� ���
		// �г��� �����ϰ� ���� �гο� ����
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
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("ȭ��Ʈ����"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// ����Ŭ������ ��ư�� ������ ����Ŭ������ ���� ���
		// �г��� �����ϰ� ���� �гο� ����
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
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("����Ŭ������"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// �׶��� ��ư�� ������ �׶��� ���� ���
		// �г��� �����ϰ� ���� �гο� ����
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
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("�׶���"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// �Ľ�Ÿ ��ư�� ������ �Ľ�Ÿ ������ ���
		// �г��� �����ϰ� ���� �гο� ����
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
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("�Ľ�Ÿ"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// ���ڹ�ư�� ������ ���� ������ ���
		// �г��� �����ϰ� ���� �гο� ����
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
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("����"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// ��Ÿ��ư�� ������ ��Ÿ ������ ���
		// �г��� �����ϰ� ���� �гο� ����
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
				p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("����"));
			}

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}
	}
}
