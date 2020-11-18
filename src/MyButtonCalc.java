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
			System.out.println("JDBC ���� ����...����");

			con = DriverManager.getConnection(url, id, password);
			System.out.println("DB ���� ����...����");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC ���� ����...����");
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select * from POS, RED,WHITE,SPA,GRATIN,PASTA,PIZZA where POS.id1 = RED.rid and POS.id1 = WHITE.wid and POS.id1 = SPA.spaid and POS.id1 = GRATIN.gid and POS.id1 = PASTA.paid and POS.id1 = PIZZA.piid");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Statement ���� ����...����");
		}

	}

	public int beforesum = 0; // �������ݾ�
	public int totalsum = 0; // �������Ű���
	public int totalnum = 0; // �������ż���
	public int snum = 0; // ����ȱ��
	public int stotal = 0; // ���� �ݾ�
	// public int C_id = 1; // ������ ID
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

		String mr[] = { "Ʈ����ü ����", "����", "��ũ���� ��", "���׽� ���� ��", "������", "�䷹�� ũ����" }; // ������θ�
		String mw[] = { "����", "�ӽþӶ�", "�����ܵ�", "�Һ� ���", "�����", "��������" }; // ȭ��Ʈ���θ�
		String ms[] = { "�Ƹ��� �𽺱���", "����Ÿ", "�����", "�ƽ�Ƽ", "ī��", "���" }; // ����Ŭ�����θ�
		String mg[] = { "�丶�� �׶���", "ũ�� �׶���", "���� �׶���", "�߰�� �׶���", "�Ұ�� �׶���", "������� �׶���" }; // �׶�����
		String mp[] = { "�丶�� �Ľ�Ÿ", "ũ�� �Ľ�Ÿ", "���� �Ľ�Ÿ", "�˸��� �ø���", "���� �Ľ�Ÿ", "���Ľ�Ÿ" }; // �Ľ�Ÿ��
		String mi[] = { "�����Ը���", "�񽺸���ũ", "�V������", "ũ������", "������������", "�����̽��ʸ���" }; // ���ڸ�
		String mk[] = { "��������", "��������", "10000�� ����", "20000�� ����", "25000�� ����", "50000�� ����" }; // ���θ�

		int costOfRedWine[] = { 30000, 40000, 43000, 75000, 60000, 45000 }; // ������� ����
		int costOfWhiteWine[] = { 40000, 20000, 33000, 55000, 40000, 35000 }; // ȭ��Ʈ���� ����
		int costOfSpaWine[] = { 34000, 90000, 13000, 45000, 50000, 35000 }; // ����Ŭ������ ����

		int costOfGratin[] = { 12000, 23000, 43000, 25000, 23000, 25000 }; // �׶��� ����

		int costOfPasta[] = { 17000, 20000, 13000, 35000, 40000, 15000 };
		// �Ľ�Ÿ ����
		int costOfPizza[] = { 17000, 20000, 13000, 55000, 30000, 35000 }; // ���� ����
		int costOfSome[] = { 30000, 25000, 10000, 20000, 25000, 50000 }; // ��Ÿ ����

		// ������� ������
		for (int i = 0; i < p.menu_RedWine.length; i++) {
			if (event.getSource() == p.menu_RedWine[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfRedWine[i];
				totalsum += costOfRedWine[i];
				totalnum += 1;
				Red[i] += 1;
				p.tex[0].append(mr[i] + " ��(��) ���õǾ����ϴ�. ---> ���� : " + costOfRedWine[i] + "\n");
				p.tex[0].append("���õ� �׸� ���� : " + totalnum + "  ����Ƚ�� : " + snum + "  ���ε� �ݾ� : " + stotal
						+ " ----> �����ݾ� : " + totalsum + "\n\n");

			}

		}

		// ȭ��Ʈ���� ������
		for (int i = 0; i < p.menu_WhiteWine.length; i++) {
			if (event.getSource() == p.menu_WhiteWine[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfWhiteWine[i];
				totalsum += costOfWhiteWine[i];
				totalnum += 1;
				White[i] += 1;

				p.tex[0].append(mw[i] + " ��(��) ���õǾ����ϴ�. ---> ���� : " + costOfWhiteWine[i] + "\n");
				p.tex[0].append("���õ� �׸� ���� : " + totalnum + "  ����Ƚ�� : " + snum + "  ���ε� �ݾ� : " + stotal
						+ " ----> �����ݾ� : " + totalsum + "\n\n");
			}
		}

		// ����ũ�� ��ư ������
		for (int i = 0; i < p.menu_SpaWine.length; i++) {
			if (event.getSource() == p.menu_SpaWine[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfSpaWine[i];
				totalsum += costOfSpaWine[i];
				totalnum += 1;
				Spa[i] += 1;

				p.tex[0].append(ms[i] + " ��(��) ���õǾ����ϴ�. ---> ���� : " + costOfSpaWine[i] + "\n");
				p.tex[0].append("���õ� �׸� ���� : " + totalnum + "  ����Ƚ�� : " + snum + "  ���ε� �ݾ� : " + stotal
						+ " ----> �����ݾ� : " + totalsum + "\n\n");
			}
		}

		// �׶��� ��ư ������
		for (int i = 0; i < p.menu_Gratin.length; i++) {
			if (event.getSource() == p.menu_Gratin[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfGratin[i];
				totalsum += costOfGratin[i];
				totalnum += 1;
				Gratin[i] += 1;

				p.tex[0].append(mg[i] + " ��(��) ���õǾ����ϴ�. ---> ���� : " + costOfGratin[i] + "\n");
				p.tex[0].append("���õ� �׸� ���� : " + totalnum + "  ����Ƚ�� : " + snum + "  ���ε� �ݾ� : " + stotal
						+ " ----> �����ݾ� : " + totalsum + "\n\n");
			}
		}

		// �Ľ�Ÿ��ư ������
		for (int i = 0; i < p.menu_Pasta.length; i++) {
			if (event.getSource() == p.menu_Pasta[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfPasta[i];
				totalsum += costOfPasta[i];
				totalnum += 1;
				Pasta[i] += 1;

				p.tex[0].append(mp[i] + " ��(��) ���õǾ����ϴ�. ---> ���� : " + costOfPasta[i] + "\n");
				p.tex[0].append("���õ� �׸� ���� : " + totalnum + "  ����Ƚ�� : " + snum + "  ���ε� �ݾ� : " + stotal
						+ " ----> �����ݾ� : " + totalsum + "\n\n");
			}
		}

		// ���ڹ�ư ������
		for (int i = 0; i < p.menu_Pizza.length; i++) {
			if (event.getSource() == p.menu_Pizza[i]) {
				p.tex[0].setForeground(Color.BLACK);
				beforesum += costOfPizza[i];
				totalsum += costOfPizza[i];
				totalnum += 1;
				Pizza[i] += 1;

				p.tex[0].append(mi[i] + " ��(��) ���õǾ����ϴ�. ---> ���� : " + costOfPizza[i] + "\n");
				p.tex[0].append("���õ� �׸� ���� : " + totalnum + "  ����Ƚ�� : " + snum + "  ���ε� �ݾ� : " + stotal
						+ " ----> �����ݾ� : " + totalsum + "\n\n");
			}
		}

		// ��Ÿ��ư ������
		for (int i = 0; i < p.menu_some.length; i++) {
			if (event.getSource() == p.menu_some[i]) {

				if (totalsum < 50000) {
					p.tex[0].setForeground(Color.RED);
					p.tex[0].setText("50000�� ���ϴ� ������ �Ұ����մϴ�. \n�ٽ� �ֹ� ��Ź�帮�ڽ��ϴ�. \n");
				} else if (totalsum > 50000) {
					p.tex[0].setForeground(Color.BLACK);
					totalsum -= costOfSome[i];
					snum += 1;
					before[i] += 1;

					stotal += costOfSome[i];
					p.tex[0].append(mk[i] + "��(��) ���õǾ����ϴ�. ---> ���ΰ��� : " + costOfSome[i] + "\n");
					p.tex[0].append(
							"���õ� �׸� ���� : " + totalnum + "  ����Ƚ�� : " + snum + " ----> �����ݾ� : " + totalsum + "\n\n");
				}
			}
		}

		// order��ư ������
		if (event.getSource() == p.order) {

			if (totalsum == 0) {
				p.tex[0].setForeground(Color.RED);
				p.tex[0].setText("���õ� �޴��� �����ϴ�. \n");

				// �˾�â ���� - �ֹ����� ������ ����
				JOptionPane.showMessageDialog(null, "�ٽ� �ֹ� ��Ź�帮�ڽ��ϴ�.", "����ģģ_�ֹ�����", JOptionPane.QUESTION_MESSAGE);

			} else {
				p.tex[0].setText("�Ǹ�����\n");
				p.tex[0].setForeground(Color.BLUE);
				p.tex[0].append("-----------------------------\n");

				p.tex[0].append("\n" + "���õ� �׸� ���� : " + totalnum + "\n");

				p.tex[0].append("-----------------------------\n���ż���\n");
				// ���õ� �׸���� ����
				for (int i = 0; i < 6; i++) {

					if (Red[i] != 0) {
						p.tex[0].append(mr[i] + " �� ���ż��� : " + Red[i] + "\n");
					}
					if (White[i] != 0) {
						p.tex[0].append(mw[i] + " �� ���ż��� : " + White[i] + "\n");
					}
					if (Spa[i] != 0) {
						p.tex[0].append(ms[i] + " �� ���ż��� : " + Spa[i] + "\n");
					}
					if (Gratin[i] != 0) {
						p.tex[0].append(mg[i] + " �� ���ż��� : " + Gratin[i] + "\n");
					}
					if (Pasta[i] != 0) {
						p.tex[0].append(mp[i] + " �� ���ż��� : " + Pasta[i] + "\n");
					}
					if (Pizza[i] != 0) {
						p.tex[0].append(mi[i] + " �� ���ż��� : " + Pizza[i] + "\n");
					}
				}
				//////////////////////////////////////////
				try {
					stmt = con.createStatement();

					int result = stmt.executeUpdate("insert into POS (����Ƚ��,���αݾ�,�����ݾ�,�������ݾ�) value( '" + snum + "', '"
							+ stotal + "' ,'" + totalsum + "'," + beforesum + ")");

					System.out.println("POS_table INSERT ����");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("POS_tableINSERT ����");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt.executeUpdate(
							"insert into RED (����, ����, ��ũ����, ���׽�, ������, �䷹��) value( '" + Red[0] + "', '" + Red[1]
									+ "' ,'" + Red[2] + "' ,'" + Red[3] + "' ,'" + Red[4] + "' ," + Red[5] + ")");

					System.out.println("RED_table INSERT ����");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("RED_table INSERT ����");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt.executeUpdate("insert into WHITE (����, �ӽþ�, �����ܵ�, �Һ�, �����, ��������) value( '"
							+ White[0] + "', '" + White[1] + "' ,'" + White[2] + "' ,'" + White[3] + "' ,'" + White[4]
							+ "' ," + White[5] + ")");

					System.out.println("WHITE_INSERT ����");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("WHITE_INSERT ����");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt.executeUpdate(
							"insert into SPA (�Ƹ���, ����Ÿ, �����, �ƽ�Ƽ, ī��, ���) value('" + Spa[0] + "', '" + Spa[1] + "' ,'"
									+ Spa[2] + "' ,'" + Spa[3] + "' ,'" + Spa[4] + "' ," + Spa[5] + ")");

					System.out.println("SPA_INSERT ����");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("SPA_INSERT ����");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt
							.executeUpdate("insert into GRATIN (�丶��׶���, ũ���׶���, �����׶���, �߰��׶���, �Ұ��׶���, �������׶���) value( '"
									+ Gratin[0] + "', '" + Gratin[1] + "' ,'" + Gratin[2] + "' ,'" + Gratin[3] + "' ,'"
									+ Gratin[4] + "' ," + Gratin[5] + ")");

					System.out.println("GRA_INSERT ����");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("GRA_INSERT ����");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt
							.executeUpdate("insert into PASTA (�丶���Ľ�Ÿ, ũ���Ľ�Ÿ, �����Ľ�Ÿ, �˸����ø���, �����Ľ�Ÿ, ���Ľ�Ÿ) value( '"
									+ Pasta[0] + "', '" + Pasta[1] + "' ,'" + Pasta[2] + "' ,'" + Pasta[3] + "' ,'"
									+ Pasta[4] + "' ," + Pasta[5] + ")");

					System.out.println("Pasta_INSERT ����");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Pasta_INSERT ����");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt
							.executeUpdate("insert into PIZZA (�丶������, �񽺸���ũ, ǳ������, ũ������, ������������, �����̽��ʸ���) value( '"
									+ Pizza[0] + "', '" + Pizza[1] + "' ,'" + Pizza[2] + "' ,'" + Pizza[3] + "' ,'"
									+ Pizza[4] + "' ," + Pizza[5] + ")");

					System.out.println("Pizza_INSERT ����");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Pizza_INSERT ����");
					e.printStackTrace();
				}

				try {
					stmt = con.createStatement();

					int result = stmt.executeUpdate("insert into BEFORESUM (��������, ��������, ����, �̸���, �̸���õ��, ������) value('"
							+ before[0] + "', '" + before[1] + "' ,'" + before[2] + "' ,'" + before[3] + "' ,'"
							+ before[4] + "' ," + before[5] + ")");

					System.out.println("Beforsum_INSERT ����");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Beforesum_INSERT ����");
					e.printStackTrace();
				}

				/////////////////////////////////////////
				p.tex[0].append("-----------------------------\n");

				p.tex[0].append("���� Ƚ�� : " + snum + "  ------>" + "  ���� �ݾ� : " + stotal
						+ "\n-----------------------------\n" + "������ �ݾ� : " + beforesum
						+ "\n-----------------------------\n" + "��������:" + totalsum + "��!!" + "\n");

				// �˾�â ���� - �ֹ�Ȯ�� ������ ����

				JOptionPane.showMessageDialog(null, "��������:" + totalsum + "��!!", "����ģģ_�ֹ�Ȯ��",
						JOptionPane.INFORMATION_MESSAGE);

				totalsum = 0; // �ֹ���ư�� ������ ���ڿ��� reset�� �ǵ��� totalsum�� 0���� �ٲ۴�
				totalnum = 0; // �ֹ���ư�� ������ ���ڿ��� reset�� �ǵ��� totalnum�� 0���� �ٲ۴�
				snum = 0; // �ֹ���ư�� ������ ���ڿ��� reset�� �ǵ��� snum�� 0���� �ٲ۴�
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

		// cancel ��ư ������
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

			p.tex[0].setText("�ٽ� �ֹ� ��Ź�帮�ڽ��ϴ�. \n");

			// �˾�â ���� - ��� ������ ���
			try {

				String sql = "select id1 from POS";
				rs = stmt.executeQuery(sql);
				rs.next();
				JOptionPane.showMessageDialog(null, "\n" + "�ֹ��� ��� �Ǿ����ϴ�.\n" + "�ٽ� �ֹ� ��Ź�帮�ڽ��ϴ�.", "����ģģ_�ֹ����",
						JOptionPane.WARNING_MESSAGE);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		// call ��ư ������
		if (event.getSource() == p.call) {
			p.tex[0].setForeground(Color.ORANGE);
			p.tex[0].setText("��ø� ��ٷ��ּ���....���� �����ڸ� ȣ���ϰڽ��ϴ�.\n����ģģ\nSINCE 2018\nHEAD CHEIF ������ \nPOS MADE BY �赿��");

			// �˾�â ���� - ��� ������ ���
			JOptionPane.showMessageDialog(null, "��ø� ��ٷ��ּ���.\n" + "���� �����ڸ� ȣ���ϰڽ��ϴ�.", "����ģģ_������ȣ��",
					JOptionPane.INFORMATION_MESSAGE);

		}

		// logout ��ư ������
		if (event.getSource() == p.logout) {
			p.tex[0].setForeground(Color.ORANGE);
			p.tex[0].setText("pos�� �����ϰڽ��ϴ�. �ٽ� �α������ּ���!");

			// �˾�â ���� - ��� ������ ���
			JOptionPane.showMessageDialog(null, "POS �α׾ƿ�!", "����ģģ_�α׾ƿ�", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
