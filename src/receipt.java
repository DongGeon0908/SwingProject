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
			System.out.println("JDBC ���� ����...����");

			con = DriverManager.getConnection(url, id, password);
			System.out.println("DB ���� ����...����");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC ���� ����...����d");
		}
		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(
					"select * from POS, RED,WHITE,SPA,GRATIN,PASTA,PIZZA,beforesum where POS.id1 = RED.rid and POS.id1 = WHITE.wid and POS.id1 = SPA.spaid and POS.id1 = GRATIN.gid and POS.id1 = PASTA.paid and POS.id1 = PIZZA.piid and POS.id1 = beforesum.beid");
			System.out.println("Statement ���� ����...����");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Statement ���� ����...����");
		}
	}

	ResultSet rs;
	// p.sub_panel[3]�� �� JTextArea ����
	JTextArea text = new JTextArea();
	JButton rr_btn[] = new JButton[7];

	String a[] = { "NEXT", "PREVIOUS", "REFUND" };

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// ���� ��� ��� ��ư Ŭ���� �׼�
		if (e.getSource() == p.menu_btn[1]) {
			p.sub_panel[3].removeAll(); // p.sub_panel[3]�� ���� ��� ���� ����
			// p.sub_panel[3] = new JPanel(); //p.sub_panel[3]�� �����

			p.sub_panel[0].removeAll(); // ���ĸ޴� p.sub_panel[0]�� ���λ���
			p.sub_panel[0] = new JPanel(new GridLayout(0, 1)); // �޴���ư �� ����
			p.sub_panel[0].setBorder(BorderFactory.createTitledBorder("������"));
			for (int i = 0; i < a.length; i++) {
				rr_btn[i] = new JButton(a[i]);

				rr_btn[i].addActionListener(this);

				p.sub_panel[0].add(rr_btn[i]);
			}

			text = new JTextArea(19, 81); // p.sub_panel[3]�� �� JTextArea ����
			p.sub_panel[3] = new JPanel();

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]�� text ����
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("������"));
			p.tex[0].setForeground(Color.GRAY);
			p.tex[0].setText("������ ���� ȭ��\n");

			p.main_panel[1].add(p.sub_panel[0], BorderLayout.EAST);
			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setTitle("����ģģ_������");
			p.setVisible(true);

		}

		if (e.getSource() == rr_btn[0]) {

			text.setFont(font);
			text.setText("������ ����\n");
			// p.sub_panel[3] = new JPanel();
			p.tex[0].setText("������ �ľ�");

			try {

				rs.next();
				p.tex[0].setText("������ Id : " + rs.getInt("id1"));
				text.append("������ ID     : " + rs.getInt("rid") + "\n");

				if (rs.getInt("����") != 0) {
					text.append("����              :   " + rs.getInt("����") + "��       ---------------->" + "���� �Ǹűݾ� : "
							+ rs.getInt("����") * 30000 + " \n");
				}
				if (rs.getInt("����") != 0) {
					text.append("����              :   " + rs.getInt("����") + "��       ---------------->" + "���� �Ǹűݾ�: "
							+ rs.getInt("����") * 40000 + "\n");
				}
				if (rs.getInt("��ũ����") != 0) {
					text.append("��ũ����     :   " + rs.getInt("��ũ����") + "��       ---------------->" + "��ũ�����Ǹűݾ� : "
							+ rs.getInt("��ũ����") * 43000 + "\n");
				}
				if (rs.getInt("���׽�") != 0) {
					text.append("���׽�           :   " + rs.getInt("���׽�") + "��       ---------------->" + "���׻� ���� �Ǹűݾ� : "
							+ rs.getInt("���׽�") * 75000 + "\n");
				}
				if (rs.getInt("������") != 0) {
					text.append("������           :   " + rs.getInt("������") + "��       ---------------->" + "���� �Ǹűݾ� : "
							+ rs.getInt("������") * 60000 + "\n");
				}
				if (rs.getInt("�䷹��") != 0) {
					text.append("�䷹��           :   " + rs.getInt("�䷹��") + "��       ---------------->" + "�䷹�� �Ǹűݾ� : "
							+ rs.getInt("�䷹��") * 45000 + "\n");
				}
				if (rs.getInt("����") != 0) {
					text.append("����          :   " + rs.getInt("����") + "��       ---------------->" + "���� �Ǹűݾ� : "
							+ rs.getInt("����") * 40000 + "\n");
				}
				if (rs.getInt("�ӽþ�") != 0) {
					text.append("�ӽþ�          :   " + rs.getInt("�ӽþ�") + "��       ---------------->" + "�ӽþ� �Ǹűݾ� : "
							+ rs.getInt("�ӽþ�") * 20000 + "\n");
				}
				if (rs.getInt("�����ܵ�") != 0) {
					text.append("�����ܵ�       :   " + rs.getInt("�����ܵ�") + "��       ---------------->" + "�����ܵ� �Ǹűݾ� : "
							+ rs.getInt("�����ܵ�") * 33000 + "\n");
				}
				if (rs.getInt("�Һ�") != 0) {
					text.append("�Һ�          :   " + rs.getInt("�Һ�") + "��       ---------------->" + "�Һ� �Ǹűݾ� : "
							+ rs.getInt("�Һ�") * 55000 + "\n");
				}
				if (rs.getInt("�����") != 0) {
					text.append("�����          :   " + rs.getInt("�����") + "��       ---------------->" + "����� �Ǹűݾ� : "
							+ rs.getInt("�����") * 40000 + "\n");
				}
				if (rs.getInt("��������") != 0) {
					text.append("��������       :   " + rs.getInt("��������") + "��       ---------------->" + "�������� �Ǹűݾ� : "
							+ rs.getInt("��������") * 35000 + "\n");
				}
				if (rs.getInt("�Ƹ���") != 0) {
					text.append("�Ƹ���          :   " + rs.getInt("�Ƹ���") + "��       ---------------->" + "�Ƹ��� �Ǹűݾ� : "
							+ rs.getInt("�Ƹ���") * 34000 + "\n");
				}
				if (rs.getInt("����Ÿ") != 0) {
					text.append("����Ÿ          :   " + rs.getInt("����Ÿ") + "��       ---------------->" + "����Ÿ �Ǹűݾ� : "
							+ rs.getInt("����Ÿ") * 90000 + "\n");
				}
				if (rs.getInt("�����") != 0) {
					text.append("�����          :   " + rs.getInt("�����") + "��       ---------------->" + "����� �Ǹűݾ� : "
							+ rs.getInt("�����") * 13000 + "\n");
				}
				if (rs.getInt("�ƽ�Ƽ") != 0) {
					text.append("�ƽ�Ƽ          :   " + rs.getInt("�ƽ�Ƽ") + "��       ---------------->" + "�ƽ�Ƽ �Ǹűݾ� : "
							+ rs.getInt("�ƽ�Ƽ") * 45000 + "\n");
				}
				if (rs.getInt("ī��") != 0) {
					text.append("ī��             :   " + rs.getInt("ī��") + "��       ---------------->" + "ī�� �Ǹűݾ� : "
							+ rs.getInt("ī��") * 50000 + "\n");
				}
				if (rs.getInt("���") != 0) {
					text.append("���             :   " + rs.getInt("���") + "��       ---------------->" + "��� �Ǹűݾ� : "
							+ rs.getInt("���") * 35000 + "\n");
				}
				if (rs.getInt("�丶��׶���") != 0) {
					text.append("�丶��׶���       :   " + rs.getInt("�丶��׶���") + "��       ---------------->"
							+ "�丶��׶��� �Ǹűݾ� : " + rs.getInt("�丶��׶���") * 12000 + "\n");
				}
				if (rs.getInt("ũ���׶���") != 0) {
					text.append("ũ���׶���          :   " + rs.getInt("ũ���׶���") + "��       ---------------->"
							+ "ũ���׶��� �Ǹűݾ� : " + rs.getInt("ũ���׶���") * 23000 + "\n");
				}
				if (rs.getInt("�����׶���") != 0) {
					text.append("�����׶���          :   " + rs.getInt("�����׶���") + "��       ---------------->"
							+ "�����׶��� �Ǹűݾ� : " + rs.getInt("�����׶���") * 43000 + "\n");
				}
				if (rs.getInt("�߰��׶���") != 0) {
					text.append("�߰��׶���       :   " + rs.getInt("�߰��׶���") + "��       ---------------->"
							+ "�߰��׶��� �Ǹűݾ� : " + rs.getInt("�߰��׶���") * 25000 + "\n");
				}
				if (rs.getInt("�Ұ��׶���") != 0) {
					text.append("�Ұ��׶���       :   " + rs.getInt("�Ұ��׶���") + "��       ---------------->"
							+ "�Ұ��׶��� �Ǹűݾ� : " + rs.getInt("�Ұ��׶���") * 23000 + "\n");
				}
				if (rs.getInt("�������׶���") != 0) {
					text.append("�������׶���    :   " + rs.getInt("�������׶���") + "��       ---------------->"
							+ "�������׶��� �Ǹűݾ� : " + rs.getInt("�������׶���") * 25000 + "\n");
				}
				if (rs.getInt("�丶���Ľ�Ÿ") != 0) {
					text.append("�丶���Ľ�Ÿ       :   " + rs.getInt("�丶���Ľ�Ÿ") + "��       ---------------->"
							+ "�丶���Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("�丶���Ľ�Ÿ") * 17000 + "\n");
				}
				if (rs.getInt("ũ���Ľ�Ÿ") != 0) {
					text.append("ũ���Ľ�Ÿ          :   " + rs.getInt("ũ���Ľ�Ÿ") + "��       ---------------->"
							+ "ũ���Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("ũ���Ľ�Ÿ") * 20000 + "\n");
				}
				if (rs.getInt("�����Ľ�Ÿ") != 0) {
					text.append("�����Ľ�Ÿ          :   " + rs.getInt("�����Ľ�Ÿ") + "��       ---------------->"
							+ "�����Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("�����Ľ�Ÿ") * 13000 + "\n");
				}
				if (rs.getInt("�˸����ø���") != 0) {
					text.append("�˸����ø���       :   " + rs.getInt("�˸����ø���") + "��       ---------------->"
							+ "�˸����ø��� �Ǹűݾ� : " + rs.getInt("�˸����ø���") * 35000 + "\n");
				}
				if (rs.getInt("�����Ľ�Ÿ") != 0) {
					text.append("�����Ľ�Ÿ          :   " + rs.getInt("�����Ľ�Ÿ") + "��       ---------------->"
							+ "�����Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("�����Ľ�Ÿ") * 40000 + "\n");
				}
				if (rs.getInt("���Ľ�Ÿ") != 0) {
					text.append("���Ľ�Ÿ             :   " + rs.getInt("���Ľ�Ÿ") + "��       ---------------->"
							+ "���Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("���Ľ�Ÿ") * 15000 + "\n");
				}
				if (rs.getInt("�丶������") != 0) {
					text.append("�丶������          :   " + rs.getInt("�丶������") + "��       ---------------->"
							+ "�丶������ �Ǹűݾ� : " + rs.getInt("�丶������") * 30000 + "\n");
				}
				if (rs.getInt("�񽺸���ũ") != 0) {
					text.append("�񽺸���ũ          :   " + rs.getInt("�񽺸���ũ") + "��       ---------------->"
							+ "�񽺸���ũ �Ǹűݾ� : " + rs.getInt("�񽺸���ũ") * 25000 + "\n");
				}
				if (rs.getInt("ǳ������") != 0) {
					text.append("ǳ������             :   " + rs.getInt("ǳ������") + "��       ---------------->"
							+ "ǳ������ �Ǹűݾ� : " + rs.getInt("ǳ������") * 10000 + "\n");
				}
				if (rs.getInt("ũ������") != 0) {
					text.append("ũ������             :   " + rs.getInt("ũ������") + "��       ---------------->"
							+ "ũ������ �Ǹűݾ� : " + rs.getInt("ũ������") * 20000 + "\n");
				}
				if (rs.getInt("������������") != 0) {
					text.append("������������       :   " + rs.getInt("������������") + "��       ---------------->"
							+ "������������ �Ǹűݾ� : " + rs.getInt("������������") * 25000 + "\n");
				}
				if (rs.getInt("�����̽��ʸ���") != 0) {
					text.append("�����̽��ʸ���    :   " + rs.getInt("�����̽��ʸ���") + "��       ---------------->"
							+ "�����̽��ʸ��� �Ǹűݾ� : " + rs.getInt("�����̽��ʸ���") * 50000 + "\n");
				}
				if (rs.getInt("��������") != 0) {
					text.append("��������     :   " + rs.getInt("��������") + "��     ---------------->" + "���� ���αݾ� : "
							+ rs.getInt("��������") * 30000 + "\n");
				}
				if (rs.getInt("��������") != 0) {
					text.append("��������    :   " + rs.getInt("��������") + "��     ---------------->" + "���� ���αݾ� : "
							+ rs.getInt("��������") * 25000 + "\n");
				}
				if (rs.getInt("����") != 0) {
					text.append("��������    :   " + rs.getInt("����") + "��     ---------------->" + "���� �����αݾ� : "
							+ rs.getInt("����") * 10000 + "\n");
				}
				if (rs.getInt("�̸���") != 0) {
					text.append("�̸�������    :   " + rs.getInt("�̸���") + "��     ---------------->" + "�̸��� �����αݾ� : "
							+ rs.getInt("�̸���") * 20000 + "\n");
				}
				if (rs.getInt("������") != 0) {
					text.append("����������    :   " + rs.getInt("������") + "��     ---------------->" + "������ �����αݾ� : "
							+ rs.getInt("������") * 50000 + "\n");
				}
				if (rs.getInt("�������ݾ�") != 0) {
					text.append("�������ݾ�    :   " + rs.getInt("�������ݾ�") + "\n");
				}
				if (rs.getInt("�����ݾ�") != 0) {
					text.append("�����ݾ�   :     " + rs.getInt("�����ݾ�"));
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.setVisible(true);
		}

		if (e.getSource() == rr_btn[1]) {
			text.setFont(font);
			text.setText("������ ����\n");

			try {

				rs.previous();
				p.tex[0].setText("������ Id : " + rs.getInt("id1"));
				text.append("������ ID     : " + rs.getInt("rid") + "\n");
				if (rs.getInt("����") != 0) {
					text.append("����              :   " + rs.getInt("����") + "��       ---------------->" + "���� �Ǹűݾ� : "
							+ rs.getInt("����") * 30000 + " \n");
				}
				if (rs.getInt("����") != 0) {
					text.append("����              :   " + rs.getInt("����") + "��       ---------------->" + "���� �Ǹűݾ�: "
							+ rs.getInt("����") * 40000 + "\n");
				}
				if (rs.getInt("��ũ����") != 0) {
					text.append("��ũ����     :   " + rs.getInt("��ũ����") + "��       ---------------->" + "��ũ�����Ǹűݾ� : "
							+ rs.getInt("��ũ����") * 43000 + "\n");
				}
				if (rs.getInt("���׽�") != 0) {
					text.append("���׽�           :   " + rs.getInt("���׽�") + "��       ---------------->" + "���׻� ���� �Ǹűݾ� : "
							+ rs.getInt("���׽�") * 75000 + "\n");
				}
				if (rs.getInt("������") != 0) {
					text.append("������           :   " + rs.getInt("������") + "��       ---------------->" + "���� �Ǹűݾ� : "
							+ rs.getInt("������") * 60000 + "\n");
				}
				if (rs.getInt("�䷹��") != 0) {
					text.append("�䷹��           :   " + rs.getInt("�䷹��") + "��       ---------------->" + "�䷹�� �Ǹűݾ� : "
							+ rs.getInt("�䷹��") * 45000 + "\n");
				}
				if (rs.getInt("����") != 0) {
					text.append("����          :   " + rs.getInt("����") + "��       ---------------->" + "���� �Ǹűݾ� : "
							+ rs.getInt("����") * 40000 + "\n");
				}
				if (rs.getInt("�ӽþ�") != 0) {
					text.append("�ӽþ�          :   " + rs.getInt("�ӽþ�") + "��       ---------------->" + "�ӽþ� �Ǹűݾ� : "
							+ rs.getInt("�ӽþ�") * 20000 + "\n");
				}
				if (rs.getInt("�����ܵ�") != 0) {
					text.append("�����ܵ�       :   " + rs.getInt("�����ܵ�") + "��       ---------------->" + "�����ܵ� �Ǹűݾ� : "
							+ rs.getInt("�����ܵ�") * 33000 + "\n");
				}
				if (rs.getInt("�Һ�") != 0) {
					text.append("�Һ�          :   " + rs.getInt("�Һ�") + "��       ---------------->" + "�Һ� �Ǹűݾ� : "
							+ rs.getInt("�Һ�") * 55000 + "\n");
				}
				if (rs.getInt("�����") != 0) {
					text.append("�����          :   " + rs.getInt("�����") + "��       ---------------->" + "����� �Ǹűݾ� : "
							+ rs.getInt("�����") * 40000 + "\n");
				}
				if (rs.getInt("��������") != 0) {
					text.append("��������       :   " + rs.getInt("��������") + "��       ---------------->" + "�������� �Ǹűݾ� : "
							+ rs.getInt("��������") * 35000 + "\n");
				}
				if (rs.getInt("�Ƹ���") != 0) {
					text.append("�Ƹ���          :   " + rs.getInt("�Ƹ���") + "��       ---------------->" + "�Ƹ��� �Ǹűݾ� : "
							+ rs.getInt("�Ƹ���") * 34000 + "\n");
				}
				if (rs.getInt("����Ÿ") != 0) {
					text.append("����Ÿ          :   " + rs.getInt("����Ÿ") + "��       ---------------->" + "����Ÿ �Ǹűݾ� : "
							+ rs.getInt("����Ÿ") * 90000 + "\n");
				}
				if (rs.getInt("�����") != 0) {
					text.append("�����          :   " + rs.getInt("�����") + "��       ---------------->" + "����� �Ǹűݾ� : "
							+ rs.getInt("�����") * 13000 + "\n");
				}
				if (rs.getInt("�ƽ�Ƽ") != 0) {
					text.append("�ƽ�Ƽ          :   " + rs.getInt("�ƽ�Ƽ") + "��       ---------------->" + "�ƽ�Ƽ �Ǹűݾ� : "
							+ rs.getInt("�ƽ�Ƽ") * 45000 + "\n");
				}
				if (rs.getInt("ī��") != 0) {
					text.append("ī��             :   " + rs.getInt("ī��") + "��       ---------------->" + "ī�� �Ǹűݾ� : "
							+ rs.getInt("ī��") * 50000 + "\n");
				}
				if (rs.getInt("���") != 0) {
					text.append("���             :   " + rs.getInt("���") + "��       ---------------->" + "��� �Ǹűݾ� : "
							+ rs.getInt("���") * 35000 + "\n");
				}
				if (rs.getInt("�丶��׶���") != 0) {
					text.append("�丶��׶���       :   " + rs.getInt("�丶��׶���") + "��       ---------------->"
							+ "�丶��׶��� �Ǹűݾ� : " + rs.getInt("�丶��׶���") * 12000 + "\n");
				}
				if (rs.getInt("ũ���׶���") != 0) {
					text.append("ũ���׶���          :   " + rs.getInt("ũ���׶���") + "��       ---------------->"
							+ "ũ���׶��� �Ǹűݾ� : " + rs.getInt("ũ���׶���") * 23000 + "\n");
				}
				if (rs.getInt("�����׶���") != 0) {
					text.append("�����׶���          :   " + rs.getInt("�����׶���") + "��       ---------------->"
							+ "�����׶��� �Ǹűݾ� : " + rs.getInt("�����׶���") * 43000 + "\n");
				}
				if (rs.getInt("�߰��׶���") != 0) {
					text.append("�߰��׶���       :   " + rs.getInt("�߰��׶���") + "��       ---------------->"
							+ "�߰��׶��� �Ǹűݾ� : " + rs.getInt("�߰��׶���") * 25000 + "\n");
				}
				if (rs.getInt("�Ұ��׶���") != 0) {
					text.append("�Ұ��׶���       :   " + rs.getInt("�Ұ��׶���") + "��       ---------------->"
							+ "�Ұ��׶��� �Ǹűݾ� : " + rs.getInt("�Ұ��׶���") * 23000 + "\n");
				}
				if (rs.getInt("�������׶���") != 0) {
					text.append("�������׶���    :   " + rs.getInt("�������׶���") + "��       ---------------->"
							+ "�������׶��� �Ǹűݾ� : " + rs.getInt("�������׶���") * 25000 + "\n");
				}
				if (rs.getInt("�丶���Ľ�Ÿ") != 0) {
					text.append("�丶���Ľ�Ÿ       :   " + rs.getInt("�丶���Ľ�Ÿ") + "��       ---------------->"
							+ "�丶���Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("�丶���Ľ�Ÿ") * 17000 + "\n");
				}
				if (rs.getInt("ũ���Ľ�Ÿ") != 0) {
					text.append("ũ���Ľ�Ÿ          :   " + rs.getInt("ũ���Ľ�Ÿ") + "��       ---------------->"
							+ "ũ���Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("ũ���Ľ�Ÿ") * 20000 + "\n");
				}
				if (rs.getInt("�����Ľ�Ÿ") != 0) {
					text.append("�����Ľ�Ÿ          :   " + rs.getInt("�����Ľ�Ÿ") + "��       ---------------->"
							+ "�����Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("�����Ľ�Ÿ") * 13000 + "\n");
				}
				if (rs.getInt("�˸����ø���") != 0) {
					text.append("�˸����ø���       :   " + rs.getInt("�˸����ø���") + "��       ---------------->"
							+ "�˸����ø��� �Ǹűݾ� : " + rs.getInt("�˸����ø���") * 35000 + "\n");
				}
				if (rs.getInt("�����Ľ�Ÿ") != 0) {
					text.append("�����Ľ�Ÿ          :   " + rs.getInt("�����Ľ�Ÿ") + "��       ---------------->"
							+ "�����Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("�����Ľ�Ÿ") * 40000 + "\n");
				}
				if (rs.getInt("���Ľ�Ÿ") != 0) {
					text.append("���Ľ�Ÿ             :   " + rs.getInt("���Ľ�Ÿ") + "��       ---------------->"
							+ "���Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("���Ľ�Ÿ") * 15000 + "\n");
				}
				if (rs.getInt("�丶������") != 0) {
					text.append("�丶������          :   " + rs.getInt("�丶������") + "��       ---------------->"
							+ "�丶������ �Ǹűݾ� : " + rs.getInt("�丶������") * 30000 + "\n");
				}
				if (rs.getInt("�񽺸���ũ") != 0) {
					text.append("�񽺸���ũ          :   " + rs.getInt("�񽺸���ũ") + "��       ---------------->"
							+ "�񽺸���ũ �Ǹűݾ� : " + rs.getInt("�񽺸���ũ") * 25000 + "\n");
				}
				if (rs.getInt("ǳ������") != 0) {
					text.append("ǳ������             :   " + rs.getInt("ǳ������") + "��       ---------------->"
							+ "ǳ������ �Ǹűݾ� : " + rs.getInt("ǳ������") * 10000 + "\n");
				}
				if (rs.getInt("ũ������") != 0) {
					text.append("ũ������             :   " + rs.getInt("ũ������") + "��       ---------------->"
							+ "ũ������ �Ǹűݾ� : " + rs.getInt("ũ������") * 20000 + "\n");
				}
				if (rs.getInt("������������") != 0) {
					text.append("������������       :   " + rs.getInt("������������") + "��       ---------------->"
							+ "������������ �Ǹűݾ� : " + rs.getInt("������������") * 25000 + "\n");
				}
				if (rs.getInt("�����̽��ʸ���") != 0) {
					text.append("�����̽��ʸ���    :   " + rs.getInt("�����̽��ʸ���") + "��       ---------------->"
							+ "�����̽��ʸ��� �Ǹűݾ� : " + rs.getInt("�����̽��ʸ���") * 50000 + "\n");
				}
				if (rs.getInt("��������") != 0) {
					text.append("��������     :   " + rs.getInt("��������") + "��     ---------------->" + "���� ���αݾ� : "
							+ rs.getInt("��������") * 30000 + "\n");
				}
				if (rs.getInt("��������") != 0) {
					text.append("��������    :   " + rs.getInt("��������") + "��     ---------------->" + "���� ���αݾ� : "
							+ rs.getInt("��������") * 25000 + "\n");
				}
				if (rs.getInt("����") != 0) {
					text.append("��������    :   " + rs.getInt("����") + "��     ---------------->" + "���� �����αݾ� : "
							+ rs.getInt("����") * 10000 + "\n");
				}
				if (rs.getInt("�̸���") != 0) {
					text.append("�̸�������    :   " + rs.getInt("�̸���") + "��     ---------------->" + "�̸��� �����αݾ� : "
							+ rs.getInt("�̸���") * 20000 + "\n");
				}
				if (rs.getInt("������") != 0) {
					text.append("����������    :   " + rs.getInt("������") + "��     ---------------->" + "������ �����αݾ� : "
							+ rs.getInt("������") * 50000 + "\n");
				}
				if (rs.getInt("�������ݾ�") != 0) {
					text.append("�������ݾ�    :   " + rs.getInt("�������ݾ�") + "\n");
				}
				if (rs.getInt("�����ݾ�") != 0) {
					text.append("�����ݾ�   :     " + rs.getInt("�����ݾ�"));
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.setVisible(true);
		}

		if (e.getSource() == rr_btn[2]) {
			text.setFont(font);
			text.setText("���� ���\n");
			p.tex[0].setText("���� ��� �ľ�");

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

				System.out.println("DELETE ����");
				try {
					rs.next();
					p.tex[0].setText("������ Id : " + rs.getInt("id1"));
					text.append("������ ID     : " + rs.getInt("rid") + "\n");
					if (rs.getInt("����") != 0) {
						text.append("����              :   " + rs.getInt("����") + "��       ---------------->"
								+ "���� �Ǹűݾ� : " + rs.getInt("����") * 30000 + " \n");
					}
					if (rs.getInt("����") != 0) {
						text.append("����              :   " + rs.getInt("����") + "��       ---------------->" + "���� �Ǹűݾ�: "
								+ rs.getInt("����") * 40000 + "\n");
					}
					if (rs.getInt("��ũ����") != 0) {
						text.append("��ũ����     :   " + rs.getInt("��ũ����") + "��       ---------------->" + "��ũ�����Ǹűݾ� : "
								+ rs.getInt("��ũ����") * 43000 + "\n");
					}
					if (rs.getInt("���׽�") != 0) {
						text.append("���׽�           :   " + rs.getInt("���׽�") + "��       ---------------->"
								+ "���׻� ���� �Ǹűݾ� : " + rs.getInt("���׽�") * 75000 + "\n");
					}
					if (rs.getInt("������") != 0) {
						text.append("������           :   " + rs.getInt("������") + "��       ---------------->" + "���� �Ǹűݾ� : "
								+ rs.getInt("������") * 60000 + "\n");
					}
					if (rs.getInt("�䷹��") != 0) {
						text.append("�䷹��           :   " + rs.getInt("�䷹��") + "��       ---------------->"
								+ "�䷹�� �Ǹűݾ� : " + rs.getInt("�䷹��") * 45000 + "\n");
					}
					if (rs.getInt("����") != 0) {
						text.append("����          :   " + rs.getInt("����") + "��       ---------------->" + "���� �Ǹűݾ� : "
								+ rs.getInt("����") * 40000 + "\n");
					}
					if (rs.getInt("�ӽþ�") != 0) {
						text.append("�ӽþ�          :   " + rs.getInt("�ӽþ�") + "��       ---------------->" + "�ӽþ� �Ǹűݾ� : "
								+ rs.getInt("�ӽþ�") * 20000 + "\n");
					}
					if (rs.getInt("�����ܵ�") != 0) {
						text.append("�����ܵ�       :   " + rs.getInt("�����ܵ�") + "��       ---------------->" + "�����ܵ� �Ǹűݾ� : "
								+ rs.getInt("�����ܵ�") * 33000 + "\n");
					}
					if (rs.getInt("�Һ�") != 0) {
						text.append("�Һ�          :   " + rs.getInt("�Һ�") + "��       ---------------->" + "�Һ� �Ǹűݾ� : "
								+ rs.getInt("�Һ�") * 55000 + "\n");
					}
					if (rs.getInt("�����") != 0) {
						text.append("�����          :   " + rs.getInt("�����") + "��       ---------------->" + "����� �Ǹűݾ� : "
								+ rs.getInt("�����") * 40000 + "\n");
					}
					if (rs.getInt("��������") != 0) {
						text.append("��������       :   " + rs.getInt("��������") + "��       ---------------->" + "�������� �Ǹűݾ� : "
								+ rs.getInt("��������") * 35000 + "\n");
					}
					if (rs.getInt("�Ƹ���") != 0) {
						text.append("�Ƹ���          :   " + rs.getInt("�Ƹ���") + "��       ---------------->" + "�Ƹ��� �Ǹűݾ� : "
								+ rs.getInt("�Ƹ���") * 34000 + "\n");
					}
					if (rs.getInt("����Ÿ") != 0) {
						text.append("����Ÿ          :   " + rs.getInt("����Ÿ") + "��       ---------------->" + "����Ÿ �Ǹűݾ� : "
								+ rs.getInt("����Ÿ") * 90000 + "\n");
					}
					if (rs.getInt("�����") != 0) {
						text.append("�����          :   " + rs.getInt("�����") + "��       ---------------->" + "����� �Ǹűݾ� : "
								+ rs.getInt("�����") * 13000 + "\n");
					}
					if (rs.getInt("�ƽ�Ƽ") != 0) {
						text.append("�ƽ�Ƽ          :   " + rs.getInt("�ƽ�Ƽ") + "��       ---------------->" + "�ƽ�Ƽ �Ǹűݾ� : "
								+ rs.getInt("�ƽ�Ƽ") * 45000 + "\n");
					}
					if (rs.getInt("ī��") != 0) {
						text.append("ī��             :   " + rs.getInt("ī��") + "��       ---------------->" + "ī�� �Ǹűݾ� : "
								+ rs.getInt("ī��") * 50000 + "\n");
					}
					if (rs.getInt("���") != 0) {
						text.append("���             :   " + rs.getInt("���") + "��       ---------------->" + "��� �Ǹűݾ� : "
								+ rs.getInt("���") * 35000 + "\n");
					}
					if (rs.getInt("�丶��׶���") != 0) {
						text.append("�丶��׶���       :   " + rs.getInt("�丶��׶���") + "��       ---------------->"
								+ "�丶��׶��� �Ǹűݾ� : " + rs.getInt("�丶��׶���") * 12000 + "\n");
					}
					if (rs.getInt("ũ���׶���") != 0) {
						text.append("ũ���׶���          :   " + rs.getInt("ũ���׶���") + "��       ---------------->"
								+ "ũ���׶��� �Ǹűݾ� : " + rs.getInt("ũ���׶���") * 23000 + "\n");
					}
					if (rs.getInt("�����׶���") != 0) {
						text.append("�����׶���          :   " + rs.getInt("�����׶���") + "��       ---------------->"
								+ "�����׶��� �Ǹűݾ� : " + rs.getInt("�����׶���") * 43000 + "\n");
					}
					if (rs.getInt("�߰��׶���") != 0) {
						text.append("�߰��׶���       :   " + rs.getInt("�߰��׶���") + "��       ---------------->"
								+ "�߰��׶��� �Ǹűݾ� : " + rs.getInt("�߰��׶���") * 25000 + "\n");
					}
					if (rs.getInt("�Ұ��׶���") != 0) {
						text.append("�Ұ��׶���       :   " + rs.getInt("�Ұ��׶���") + "��       ---------------->"
								+ "�Ұ��׶��� �Ǹűݾ� : " + rs.getInt("�Ұ��׶���") * 23000 + "\n");
					}
					if (rs.getInt("�������׶���") != 0) {
						text.append("�������׶���    :   " + rs.getInt("�������׶���") + "��       ---------------->"
								+ "�������׶��� �Ǹűݾ� : " + rs.getInt("�������׶���") * 25000 + "\n");
					}
					if (rs.getInt("�丶���Ľ�Ÿ") != 0) {
						text.append("�丶���Ľ�Ÿ       :   " + rs.getInt("�丶���Ľ�Ÿ") + "��       ---------------->"
								+ "�丶���Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("�丶���Ľ�Ÿ") * 17000 + "\n");
					}
					if (rs.getInt("ũ���Ľ�Ÿ") != 0) {
						text.append("ũ���Ľ�Ÿ          :   " + rs.getInt("ũ���Ľ�Ÿ") + "��       ---------------->"
								+ "ũ���Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("ũ���Ľ�Ÿ") * 20000 + "\n");
					}
					if (rs.getInt("�����Ľ�Ÿ") != 0) {
						text.append("�����Ľ�Ÿ          :   " + rs.getInt("�����Ľ�Ÿ") + "��       ---------------->"
								+ "�����Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("�����Ľ�Ÿ") * 13000 + "\n");
					}
					if (rs.getInt("�˸����ø���") != 0) {
						text.append("�˸����ø���       :   " + rs.getInt("�˸����ø���") + "��       ---------------->"
								+ "�˸����ø��� �Ǹűݾ� : " + rs.getInt("�˸����ø���") * 35000 + "\n");
					}
					if (rs.getInt("�����Ľ�Ÿ") != 0) {
						text.append("�����Ľ�Ÿ          :   " + rs.getInt("�����Ľ�Ÿ") + "��       ---------------->"
								+ "�����Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("�����Ľ�Ÿ") * 40000 + "\n");
					}
					if (rs.getInt("���Ľ�Ÿ") != 0) {
						text.append("���Ľ�Ÿ             :   " + rs.getInt("���Ľ�Ÿ") + "��       ---------------->"
								+ "���Ľ�Ÿ �Ǹűݾ� : " + rs.getInt("���Ľ�Ÿ") * 15000 + "\n");
					}
					if (rs.getInt("�丶������") != 0) {
						text.append("�丶������          :   " + rs.getInt("�丶������") + "��       ---------------->"
								+ "�丶������ �Ǹűݾ� : " + rs.getInt("�丶������") * 30000 + "\n");
					}
					if (rs.getInt("�񽺸���ũ") != 0) {
						text.append("�񽺸���ũ          :   " + rs.getInt("�񽺸���ũ") + "��       ---------------->"
								+ "�񽺸���ũ �Ǹűݾ� : " + rs.getInt("�񽺸���ũ") * 25000 + "\n");
					}
					if (rs.getInt("ǳ������") != 0) {
						text.append("ǳ������             :   " + rs.getInt("ǳ������") + "��       ---------------->"
								+ "ǳ������ �Ǹűݾ� : " + rs.getInt("ǳ������") * 10000 + "\n");
					}
					if (rs.getInt("ũ������") != 0) {
						text.append("ũ������             :   " + rs.getInt("ũ������") + "��       ---------------->"
								+ "ũ������ �Ǹűݾ� : " + rs.getInt("ũ������") * 20000 + "\n");
					}
					if (rs.getInt("������������") != 0) {
						text.append("������������       :   " + rs.getInt("������������") + "��       ---------------->"
								+ "������������ �Ǹűݾ� : " + rs.getInt("������������") * 25000 + "\n");
					}
					if (rs.getInt("�����̽��ʸ���") != 0) {
						text.append("�����̽��ʸ���    :   " + rs.getInt("�����̽��ʸ���") + "��       ---------------->"
								+ "�����̽��ʸ��� �Ǹűݾ� : " + rs.getInt("�����̽��ʸ���") * 50000 + "\n");
					}
					if (rs.getInt("��������") != 0) {
						text.append("��������     :   " + rs.getInt("��������") + "��     ---------------->" + "���� ���αݾ� : "
								+ rs.getInt("��������") * 30000 + "\n");
					}
					if (rs.getInt("��������") != 0) {
						text.append("��������    :   " + rs.getInt("��������") + "��     ---------------->" + "���� ���αݾ� : "
								+ rs.getInt("��������") * 25000 + "\n");
					}
					if (rs.getInt("����") != 0) {
						text.append("��������    :   " + rs.getInt("����") + "��     ---------------->" + "���� �����αݾ� : "
								+ rs.getInt("����") * 10000 + "\n");
					}
					if (rs.getInt("�̸���") != 0) {
						text.append("�̸�������    :   " + rs.getInt("�̸���") + "��     ---------------->" + "�̸��� �����αݾ� : "
								+ rs.getInt("�̸���") * 20000 + "\n");
					}
					if (rs.getInt("������") != 0) {
						text.append("����������    :   " + rs.getInt("������") + "��     ---------------->" + "������ �����αݾ� : "
								+ rs.getInt("������") * 50000 + "\n");
					}
					if (rs.getInt("�������ݾ�") != 0) {
						text.append("�������ݾ�    :   " + rs.getInt("�������ݾ�") + "\n");
					}
					if (rs.getInt("�����ݾ�") != 0) {
						text.append("�����ݾ�   :     " + rs.getInt("�����ݾ�"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("DELETE ����");
				e1.printStackTrace();
			}

			p.setVisible(true);
		}

	}
}
