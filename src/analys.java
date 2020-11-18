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

		// �����ͺ��̽� ����
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
					"select * from POS, RED,WHITE,SPA,GRATIN,PASTA,PIZZA where POS.id1 = RED.rid and POS.id1 = WHITE.wid and POS.id1 = SPA.spaid and POS.id1 = GRATIN.gid and POS.id1 = PASTA.paid and POS.id1 = PIZZA.piid");
			System.out.println("Statement ���� ����...����");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Statement ���� ����...����");
		}
	}

	ResultSet rs;
	// p.sub_panel[3]�� �� JTextArea ����
	JTextArea text = new JTextArea(); // ���ο� �ؽ������� ����
	JButton a_btn[] = new JButton[7]; // ������ ��� ��ư �����

	String a[] = { "���� �Ǹŷ�", "ȭ��Ʈ �Ǹŷ�", "����Ŭ�� �Ǹŷ�", "�׶��� �Ǹŷ�", "�Ľ�Ÿ �Ǹŷ�", "���� �Ǹŷ�", "�Ǹŷ�" };

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		p.tex[0].setFont(font);
		// ���� ��� ��� ��ư Ŭ���� �׼�
		if (e.getSource() == p.menu_btn[2]) {
			p.sub_panel[3].removeAll(); // p.sub_panel[3]�� ���� ��� ���� ����
			// p.sub_panel[3] = new JPanel(); //p.sub_panel[3]�� �����

			p.sub_panel[0].removeAll(); // ���ĸ޴� p.sub_panel[0]�� ���λ���
			p.sub_panel[0] = new JPanel(new GridLayout(0, 1)); // �޴���ư �� ����
			p.sub_panel[0].setBorder(BorderFactory.createTitledBorder("�Ǹŷ�"));
			for (int i = 0; i < a.length; i++) {
				a_btn[i] = new JButton(a[i]);

				a_btn[i].addActionListener(this);

				p.sub_panel[0].add(a_btn[i]);
			}

			text = new JTextArea(19, 81); // p.sub_panel[3]�� �� JTextArea ����
			p.sub_panel[3] = new JPanel();

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]�� text ����
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("��� ȭ��"));

			p.tex[0].setForeground(Color.GRAY);
			p.tex[0].setText("���� �м� ȭ��\n");

			// �����гο� �� ����
			p.main_panel[1].add(p.sub_panel[0], BorderLayout.EAST);
			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setTitle("����ģģ_���");
			p.setVisible(true);

		}

		// ����ư Ŭ���� ������ ����� �޴��� �ٲ�

		// ������� �Ǹŷ� ��ư
		if (e.getSource() == a_btn[0]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setForeground(Color.BLACK);
			text.setFont(font);
			text.setText("������� �Ǹŷ�\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("������� ���� ���� �ľ�");

			try {

				String sql = "select sum(����),sum(����),sum(��ũ����),sum(���׽�) ,sum(������), sum(�䷹��) from RED";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("�� �Ǹ� ����" + "\n");
				text.append("���� ����              :   " + rs.getInt("sum(����)") + "\n" + "���� �Ǹűݾ� : "
						+ rs.getInt("sum(����)") * 30000 + " \n");
				text.append("���� ����              :   " + rs.getInt("sum(����)") + "\n" + "���� �Ǹűݾ�: "
						+ rs.getInt("sum(����)") * 40000 + "\n");
				text.append("��ũ���� ����     :   " + rs.getInt("sum(��ũ����)") + "\n" + "��ũ�����Ǹűݾ� : "
						+ rs.getInt("sum(��ũ����)") * 43000 + "\n");
				text.append("���׽� ����           :   " + rs.getInt("sum(���׽�)") + "\n" + "���׻� ���� �Ǹűݾ� : "
						+ rs.getInt("sum(���׽�)") * 75000 + "\n");
				text.append("������ ����           :   " + rs.getInt("sum(������)") + "\n" + "���� �Ǹűݾ� : "
						+ rs.getInt("sum(������)") * 60000 + "\n");
				text.append("�䷹�� ����           :   " + rs.getInt("sum(�䷹��)") + "\n" + "�䷹�� �Ǹűݾ� : "
						+ rs.getInt("sum(�䷹��)") * 45000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]�� text ����
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("��� ȭ��"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// ȭ��Ʈ ���� �Ǹŷ� ��ư
		if (e.getSource() == a_btn[1]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("ȭ��Ʈ���� �Ǹŷ�\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("ȭ��Ʈ���� ���� ���� �ľ�");

			try {

				String sql = "select sum(����),sum(�ӽþ�),sum(�����ܵ�),sum(�Һ�),sum(�����),sum(��������)  from WHITE";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("�� �Ǹ� ����" + "\n");
				text.append("���� ����          :   " + rs.getInt("sum(����)") + "\n" + "���� �Ǹűݾ� : "
						+ rs.getInt("sum(����)") * 40000 + "\n");
				text.append("�ӽþ� ����          :   " + rs.getInt("sum(�ӽþ�)") + "\n" + "�ӽþ� �Ǹűݾ� : "
						+ rs.getInt("sum(�ӽþ�)") * 20000 + "\n");
				text.append("�����ܵ� ����       :   " + rs.getInt("sum(�����ܵ�)") + "\n" + "�����ܵ� �Ǹűݾ� : "
						+ rs.getInt("sum(�����ܵ�)") * 33000 + "\n");
				text.append("�Һ� ����          :   " + rs.getInt("sum(�Һ�)") + "\n" + "�Һ� �Ǹűݾ� : "
						+ rs.getInt("sum(�Һ�)") * 55000 + "\n");
				text.append("����� ����          :   " + rs.getInt("sum(�����)") + "\n" + "����� �Ǹűݾ� : "
						+ rs.getInt("sum(�����)") * 40000 + "\n");
				text.append("�������� ����       :   " + rs.getInt("sum(��������)") + "\n" + "�������� �Ǹűݾ� : "
						+ rs.getInt("sum(��������)") * 35000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]�� text ����
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("��� ȭ��"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// ����Ŭ�� ���� �Ǹŷ� ��ư
		if (e.getSource() == a_btn[2]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("����Ŭ������ �Ǹŷ�\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("����Ŭ������ ���� ���� �ľ�");

			try {

				String sql = "select sum(�Ƹ���),sum(����Ÿ),sum(�����),sum(�ƽ�Ƽ),sum(ī��),sum(���)  from SPA";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("�� �Ǹ� ����" + "\n");
				text.append("�Ƹ��� ����          :   " + rs.getInt("sum(�Ƹ���)") + "\n" + "�Ƹ��� �Ǹűݾ� : "
						+ rs.getInt("sum(�Ƹ���)") * 34000 + "\n");
				text.append("����Ÿ ����          :   " + rs.getInt("sum(����Ÿ)") + "\n" + "����Ÿ �Ǹűݾ� : "
						+ rs.getInt("sum(����Ÿ)") * 90000 + "\n");
				text.append("����� ����          :   " + rs.getInt("sum(�����)") + "\n" + "����� �Ǹűݾ� : "
						+ rs.getInt("sum(�����)") * 13000 + "\n");
				text.append("�ƽ�Ƽ ����          :   " + rs.getInt("sum(�ƽ�Ƽ)") + "\n" + "�ƽ�Ƽ �Ǹűݾ� : "
						+ rs.getInt("sum(�ƽ�Ƽ)") * 45000 + "\n");
				text.append("ī�� ����             :   " + rs.getInt("sum(ī��)") + "\n" + "ī�� �Ǹűݾ� : "
						+ rs.getInt("sum(ī��)") * 50000 + "\n");
				text.append("��� ����             :   " + rs.getInt("sum(���)") + "\n" + "��� �Ǹűݾ� : "
						+ rs.getInt("sum(���)") * 35000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]�� text ����
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("��� ȭ��"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// �׶��� �Ǹŷ� ��ư
		if (e.getSource() == a_btn[3]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("�׶��� �Ǹŷ�\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("�׶��� ���� ���� �ľ�");

			try {

				String sql = "select sum(�丶��׶���),sum(ũ���׶���),sum(�����׶���),sum(�߰��׶���),sum(�Ұ��׶���),sum(�������׶���)  from GRATIN";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("�� �Ǹ� ����" + "\n");
				text.append("�丶��׶��� ����       :   " + rs.getInt("sum(�丶��׶���)") + "\n" + "�丶��׶��� �Ǹűݾ� : "
						+ rs.getInt("sum(�丶��׶���)") * 12000 + "\n");
				text.append("ũ���׶��� ����          :   " + rs.getInt("sum(ũ���׶���)") + "\n" + "ũ���׶��� �Ǹűݾ� : "
						+ rs.getInt("sum(ũ���׶���)") * 23000 + "\n");
				text.append("�����׶��� ����          :   " + rs.getInt("sum(�����׶���)") + "\n" + "�����׶��� �Ǹűݾ� : "
						+ rs.getInt("sum(�����׶���)") * 43000 + "\n");
				text.append("�߰��׶��� ����       :   " + rs.getInt("sum(�߰��׶���)") + "\n" + "�߰��׶��� �Ǹűݾ� : "
						+ rs.getInt("sum(�߰��׶���)") * 25000 + "\n");
				text.append("�Ұ��׶��� ����       :   " + rs.getInt("sum(�Ұ��׶���)") + "\n" + "�Ұ��׶��� �Ǹűݾ� : "
						+ rs.getInt("sum(�Ұ��׶���)") * 23000 + "\n");
				text.append("�������׶��� ����    :   " + rs.getInt("sum(�������׶���)") + "\n" + "�������׶��� �Ǹűݾ� : "
						+ rs.getInt("sum(�������׶���)") * 25000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]�� text ����
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("��� ȭ��"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// �Ľ�Ÿ �Ǹŷ� ��ư
		if (e.getSource() == a_btn[4]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("�Ľ�Ÿ �Ǹŷ�\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("�Ľ�Ÿ ���� ���� �ľ�");

			try {

				String sql = "select sum(�丶���Ľ�Ÿ),sum(ũ���Ľ�Ÿ),sum(�����Ľ�Ÿ),sum(�˸����ø���),sum(�����Ľ�Ÿ),sum(���Ľ�Ÿ)  from PASTA";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("�� �Ǹ� ����" + "\n");
				text.append("�丶���Ľ�Ÿ ����       :   " + rs.getInt("sum(�丶���Ľ�Ÿ)") + "\n" + "�丶���Ľ�Ÿ �Ǹűݾ� : "
						+ rs.getInt("sum(�丶���Ľ�Ÿ)") * 17000 + "\n");
				text.append("ũ���Ľ�Ÿ ����          :   " + rs.getInt("sum(ũ���Ľ�Ÿ)") + "\n" + "ũ���Ľ�Ÿ �Ǹűݾ� : "
						+ rs.getInt("sum(ũ���Ľ�Ÿ)") * 20000 + "\n");
				text.append("�����Ľ�Ÿ ����          :   " + rs.getInt("sum(�����Ľ�Ÿ)") + "\n" + "�����Ľ�Ÿ �Ǹűݾ� : "
						+ rs.getInt("sum(�����Ľ�Ÿ)") * 13000 + "\n");
				text.append("�˸����ø��� ����       :   " + rs.getInt("sum(�˸����ø���)") + "\n" + "�˸����ø��� �Ǹűݾ� : "
						+ rs.getInt("sum(�˸����ø���)") * 35000 + "\n");
				text.append("�����Ľ�Ÿ ����          :   " + rs.getInt("sum(�����Ľ�Ÿ)") + "\n" + "�����Ľ�Ÿ �Ǹűݾ� : "
						+ rs.getInt("sum(�����Ľ�Ÿ)") * 40000 + "\n");
				text.append("���Ľ�Ÿ ����             :   " + rs.getInt("sum(���Ľ�Ÿ)") + "\n" + "���Ľ�Ÿ �Ǹűݾ� : "
						+ rs.getInt("sum(���Ľ�Ÿ)") * 15000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]�� text ����
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("��� ȭ��"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// ���� �Ǹŷ� ��ư
		if (e.getSource() == a_btn[5]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("���� �Ǹŷ�\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("���� ���� ���� �ľ�");

			try {

				String sql = "select sum(�丶������),sum(�񽺸���ũ),sum(ǳ������),sum(ũ������),sum(������������),sum(�����̽��ʸ���)  from PIZZA";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("�� �Ǹ� ����" + "\n");
				text.append("�丶������ ����          :   " + rs.getInt("sum(�丶������)") + "\n" + "�丶������ �Ǹűݾ� : "
						+ rs.getInt("sum(�丶������)") * 30000 + "\n");
				text.append("�񽺸���ũ ����          :   " + rs.getInt("sum(�񽺸���ũ)") + "\n" + "�񽺸���ũ �Ǹűݾ� : "
						+ rs.getInt("sum(�񽺸���ũ)") * 25000 + "\n");
				text.append("ǳ������ ����             :   " + rs.getInt("sum(ǳ������)") + "\n" + "ǳ������ �Ǹűݾ� : "
						+ rs.getInt("sum(ǳ������)") * 10000 + "\n");
				text.append("ũ������ ����             :   " + rs.getInt("sum(ũ������)") + "\n" + "ũ������ �Ǹűݾ� : "
						+ rs.getInt("sum(ũ������)") * 20000 + "\n");
				text.append("������������ ����       :   " + rs.getInt("sum(������������)") + "\n" + "������������ �Ǹűݾ� : "
						+ rs.getInt("sum(������������)") * 25000 + "\n");
				text.append("�����̽��ʸ��� ����    :   " + rs.getInt("sum(�����̽��ʸ���)") + "\n" + "�����̽��ʸ��� �Ǹűݾ� : "
						+ rs.getInt("sum(�����̽��ʸ���)") * 35000 + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]�� text ����
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("��� ȭ��"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

		// ���Ǹŷ� ��ư
		if (e.getSource() == a_btn[6]) {
			p.sub_panel[3].removeAll();
			text = new JTextArea(19, 81);
			text.setFont(font);
			text.setForeground(Color.BLACK);
			text.setText("�Ǹŷ�\n");
			p.sub_panel[3] = new JPanel();
			p.tex[0].setText("�� �Ǹŷ� Ȯ��");

			try {

				String sql = "select sum(����Ƚ��),sum(���αݾ�),sum(�������ݾ�),sum(�����ݾ�) from POS";
				rs = stmt.executeQuery(sql);
				rs.next();
				text.append("�� �Ǹ� " + "\n");
				text.append("����Ƚ��               :   " + rs.getInt("sum(����Ƚ��)") + "\n");
				text.append("���αݾ�                :   " + rs.getInt("sum(���αݾ�)") + "\n");
				text.append("�������ݾ�              :   " + rs.getInt("sum(�������ݾ�)") + "\n");
				text.append("�����ݾ�                :   " + rs.getInt("sum(�����ݾ�)") + "\n");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			p.sub_panel[3].add(new JScrollPane(text));// sub_panel[3]�� text ����
			p.sub_panel[3].setBorder(BorderFactory.createTitledBorder("��� ȭ��"));

			p.main_panel[1].add(p.sub_panel[3], BorderLayout.CENTER);
			p.setVisible(true);
		}

	}
}
