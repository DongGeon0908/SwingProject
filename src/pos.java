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

	// public ImageIcon image = new ImageIcon(); // ���� �̹��� ����

	JPanel main_panel[] = new JPanel[10]; // �����г�
	JPanel sub_panel[] = new JPanel[10]; // �����гο� �����ϴ� �г�
	// JPanel x_panel[] = new JPanel[6];

	JPanel oc = new JPanel(new GridLayout(1, 3)); // ��ư�г� --> ����,���,ȣ��, �α׾ƿ� ��ư�� ���� �г�
	JButton order = new JButton("�ֹ�"); // �ֹ� ��ư ����
	JButton cancel = new JButton("���"); // ��� ��ư ����
	JButton call = new JButton("ȣ��"); // ����ȣ�� ��ư ����
	JButton logout = new JButton("�α׾ƿ�"); // �α׾ƿ� ��ư ����

	JButton food_btn[] = new JButton[7]; // --> ���ĸ޴� ��ư
	JButton menu_btn[] = new JButton[3]; // ���� ��� ��ư

	JButton menu_RedWine[] = new JButton[6]; // ������� ����
	JButton menu_WhiteWine[] = new JButton[6]; // ȭ��Ʈ ���� ����
	JButton menu_SpaWine[] = new JButton[6]; // ����Ŭ�� ���� ����
	JButton menu_Gratin[] = new JButton[6]; // �׶��� ����
	JButton menu_Pasta[] = new JButton[6]; // �Ľ�Ÿ ����
	JButton menu_Pizza[] = new JButton[6]; // ���� ����
	JButton menu_some[] = new JButton[6]; // ��Ÿ ���� ����

	// tex[] ���� ����
	JTextArea tex[] = new JTextArea[10]; // ���� �ϴ��� �ؽ�Ʈ ���ȭ��
	Font font = new Font("arian", Font.BOLD, 12);

	// ������ �ϴ� ��ư �̹��� ÷��
	String oc_btn[] = { "image/order.jpg", "image/cancel.jpg", "image/call.png", "image/logout.jpg" };
	ImageIcon a[] = new ImageIcon[4];
	JLabel b[] = new JLabel[4];

	public MyButtonListener MyB;// ��ư������ ����
	public MyButtonCalc MyC; // ��ư������ ����
	public analys ana; // ��� ��ư ������ ����
	public main ma; // �Ǹ� ��ư ������ ����
	public receipt RE; // ������ ��ư ������ ����

	public pos() throws SQLException {

		// �̹��� ���� --> ����ȭ��
		String image = "image/main_pic.jpg";
		ImageIcon mainc = new ImageIcon(image);
		JLabel main = new JLabel();
		main.setIcon(mainc);
		///////////////////////
		// ��ư �̹��� ���� --> �ֹ�, ���, ȣ��, �α׾ƿ�
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
		main_panel[0] = new JPanel(new BorderLayout()); // �����г�
		main_panel[1] = new JPanel(new BorderLayout()); // ���� ��ư�� �޴� Ȯ��, pos�޴�
		main_panel[2] = new JPanel(new BorderLayout()); // ��� ��� Ȯ��, �ֹ���ư, ��ҹ�ư

		sub_panel[0] = new JPanel(new GridLayout(0, 1)); // ���ĸ޴�
		sub_panel[1] = new JPanel(new BorderLayout()); // �ֹ���ư, ��ҹ�ư
		sub_panel[2] = new JPanel(new GridLayout(0, 1)); // POS�޴� ��ư
		sub_panel[3] = new JPanel(new GridLayout(0, 3, 10, 15)); // ���� �޴� ���� �г�

		// ���� ��� �޴���
		String mb[] = { "�Ǹ�", "������ ����", "���" };

		// ������ ��� �޴���
		String fb[] = { "�������", "ȭ��Ʈ����", "����Ŭ������", "�׶���", "�Ľ�Ÿ", "����", "��Ÿ" };

		// ���� ��� �޴��� ��ư ����
		for (int i = 0; i < mb.length; i++) {
			menu_btn[i] = new JButton(mb[i]);
		}

		// ���� ��� �޴��� ���� ÷��
		menu_btn[0].setBorder(BorderFactory.createTitledBorder("�Ǹ�"));
		menu_btn[1].setBorder(BorderFactory.createTitledBorder("������"));
		menu_btn[2].setBorder(BorderFactory.createTitledBorder("���"));

		ImageIcon Imb[] = new ImageIcon[3];
		JLabel Lmb[] = new JLabel[3];
		String Smb[] = { "image/m.png", "image/m1.jpg", "image/m2.gif" };

		for (int i = 0; i < 3; i++) {
			Imb[i] = new ImageIcon(Smb[i]);
			Lmb[i] = new JLabel();
			Lmb[i].setIcon(Imb[i]);

			menu_btn[i].add(Lmb[i]);
		}

		// ������ ��� �޴���ư�� ���� �Ŵ� ��ư ����
		for (int i = 0; i < 7; i++) {
			food_btn[i] = new JButton(fb[i]); // ������ ��� �޴���ư
		}

		// ������ ������� ��ư ����
		for (int i = 0; i < 6; i++) {
			menu_RedWine[i] = new JButton(); // �������
			menu_WhiteWine[i] = new JButton(); // ȭ��Ʈ ����
			menu_SpaWine[i] = new JButton(); // ����Ŭ�� ����
			menu_Gratin[i] = new JButton(); // �׶���
			menu_Pasta[i] = new JButton(); // �Ľ�Ÿ
			menu_Pizza[i] = new JButton(); // ����
			menu_some[i] = new JButton(); // ��Ÿ ����
		}

		// �ֹ� ���� ���
		tex[0] = new JTextArea(10, 51); // �ֹ�â
		tex[0].setFont(font);

		// sub_panel[0]�� ���ĸ޴���ư ����
		for (int i = 0; i < 7; i++) {
			sub_panel[0].add(food_btn[i]);
		}

		// ��ư �� ����
		order.setBorder(BorderFactory.createTitledBorder("�ֹ�"));
		cancel.setBorder(BorderFactory.createTitledBorder("���"));
		call.setBorder(BorderFactory.createTitledBorder("ȣ��"));
		logout.setBorder(BorderFactory.createTitledBorder("�α׾ƿ�"));

		// �ֹ�,���,ȣ�� ��ư�� ũ�� ����
		oc.setPreferredSize(new Dimension(580, 60));

		// oc�� ��ư �߰�
		oc.add(order); // �ֹ� ��ư
		oc.add(cancel); // ��� ��ư
		oc.add(call); // ȣ�� ��ư
		oc.add(logout); // �α׾ƿ� ��ư

		// sub_panel[1]�� �ֹ�, ���, ȣ��, �α׾ƿ� ��ư ����
		sub_panel[1].add(oc);

		// sub_panel[2]�� ���� ��ܿ� �ִ� �޴� ��ư ����
		for (int i = 0; i < mb.length; i++) {
			sub_panel[2].add(menu_btn[i]);
		}

		sub_panel[0].setBorder(BorderFactory.createTitledBorder("FOOD"));
		sub_panel[2].setBorder(BorderFactory.createTitledBorder("MENU"));

		// main_panel[1]�� �� ���� �гε��� �߰� (�ʱ�ȭ��)
		main_panel[1].add(sub_panel[2], BorderLayout.WEST);
		main_panel[1].add(main, BorderLayout.CENTER);
		main_panel[1].add(sub_panel[0], BorderLayout.EAST);

		// �ֹ� Ȯ�� text AREA����
		main_panel[2].add(new JScrollPane(tex[0]), BorderLayout.WEST);

		// �ֹ��� ��ҹ�ư�� ������ �ϴܿ� ����
		main_panel[2].add(sub_panel[1], BorderLayout.EAST);

		// main_panel[0]�� ùȭ���� ���� �ȿ� �ȿ��ִ� �����г� // main_panel[0]�� main_panel[1]��
		// main_panel[2]�� ����
		main_panel[0].add(main_panel[1], BorderLayout.CENTER);
		main_panel[0].add(main_panel[2], BorderLayout.SOUTH);

		// ��ư ������ ����
		MyB = new MyButtonListener(this);
		MyC = new MyButtonCalc(this);
		ana = new analys(this);
		ma = new main(this);
		RE = new receipt(this);

		// order��ư�� cancel��ư, call��ư�� ������ �߰�\
		order.addActionListener(MyC);
		cancel.addActionListener(MyC);
		call.addActionListener(MyC);
		logout.addActionListener(MyC);

		// menu_btn[]�� ������ �߰�
		menu_btn[0].addActionListener(ma);
		menu_btn[1].addActionListener(RE);
		menu_btn[2].addActionListener(ana);

		// ���� �޴� ��ư�� ������ �ޱ�
		for (int i = 0; i < 7; i++) {
			food_btn[i].addActionListener(MyB);
		}

		// ���� ��ư�� ������ �߰�
		for (int i = 0; i < 6; i++) {
			menu_RedWine[i].addActionListener(MyC);
			menu_WhiteWine[i].addActionListener(MyC);
			menu_SpaWine[i].addActionListener(MyC);
			menu_Gratin[i].addActionListener(MyC);
			menu_Pasta[i].addActionListener(MyC);
			menu_Pizza[i].addActionListener(MyC);
			menu_some[i].addActionListener(MyC);
		}

		// main_panel�� ���� ����
		// main_panel[0].setBackground(Color.lightGray);
		// main_panel[1].setBackground(Color.lightGray);
		main_panel[2].setBackground(Color.lightGray);

		// JFrame ����
		this.setBackground(Color.BLACK);
		this.add(main_panel[0]); // ���� �����ӿ� �г� �߰�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("����ģģ Mullae Chin Chin");
		this.setSize(1100, 600);
		this.setVisible(true);
		this.setResizable(false);
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		new pos();
	}

}
