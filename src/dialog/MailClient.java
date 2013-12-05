package dialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import action.MailAction;
import data.Mail;
import data.User;

/**
 * 
 * @author spacelis
 *
 */
public class MailClient extends javax.swing.JFrame {
	static User user = null;
	static Mail mail = null;
	String lastSelectedBox = "";
	List<Mail> mailList = new ArrayList<Mail>();
	static DefaultMutableTreeNode root = new DefaultMutableTreeNode("我的邮箱");
	static DefaultMutableTreeNode recvBox = new DefaultMutableTreeNode("收件箱");
	static DefaultMutableTreeNode postBox = new DefaultMutableTreeNode("发件箱");
	static DefaultMutableTreeNode draftBox = new DefaultMutableTreeNode("草稿箱");
	static DefaultTableModel table = null;

	/** 
	 * Creates new form MailClient
	 */
	public MailClient() {
		initComponents();

		root.add(recvBox);
		root.add(postBox);
		root.add(draftBox);
		user = new User();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				jMenuItem1ActionPerformed(null);
			}
		});
		this.setLocation(200, 150);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		menuBar1 = new java.awt.MenuBar();
		menu1 = new java.awt.Menu();
		menuBar2 = new java.awt.MenuBar();
		menu2 = new java.awt.Menu();
		treePopupMenu = new javax.swing.JPopupMenu();
		jMenuItem3 = new javax.swing.JMenuItem();
		jPanel1 = new javax.swing.JPanel();
		jToolBar1 = new javax.swing.JToolBar();
		newMailButton = new javax.swing.JButton();
		rcvMailButton = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jScrollPane4 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable(){
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		jScrollPane3 = new javax.swing.JScrollPane();
		mailTextArea = new javax.swing.JTextArea();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tree = new javax.swing.JTree(root);
		jScrollPane2 = new javax.swing.JScrollPane();
		stateTextArea = new javax.swing.JTextArea();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jSeparator1 = new javax.swing.JSeparator();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jMenuItem4 = new javax.swing.JMenuItem();

		menu1.setLabel("Menu");
		menuBar1.add(menu1);

		menu2.setLabel("Menu");
		menuBar2.add(menu2);

		jMenuItem3.setText("\u6e05\u7a7a");
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem3ActionPerformed(evt);
			}
		});
		treePopupMenu.add(jMenuItem3);

		setTitle("MailClient");
		setDefaultCloseOperation(3);

		jToolBar1.setRollover(true);

		newMailButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/icons/WriteNew.png"))); // NOI18N
		newMailButton.setFocusable(false);
		newMailButton.setHorizontalTextPosition(0);
		newMailButton.setText("\u65b0\u90ae\u4ef6");
		newMailButton.setVerticalTextPosition(3);
		newMailButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newMailButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(newMailButton);

		rcvMailButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/icons/download.png"))); // NOI18N
		rcvMailButton.setFocusable(false);
		rcvMailButton.setHorizontalTextPosition(0);
		rcvMailButton.setText("\u6536\u90ae\u4ef6");
		rcvMailButton.setVerticalTextPosition(3);
		rcvMailButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rcvMailButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(rcvMailButton);

		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/icons/icon_host.gif"))); // NOI18N
		jButton1.setFocusable(false);
		jButton1.setHorizontalTextPosition(0);
		jButton1.setMaximumSize(new java.awt.Dimension(65, 59));
		jButton1.setMinimumSize(new java.awt.Dimension(65, 59));
		jButton1.setPreferredSize(new java.awt.Dimension(55, 49));
		jButton1.setText("\u7528\u6237\u8bbe\u7f6e");
		jButton1.setVerticalTextPosition(3);
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jToolBar1.add(jButton1);

		jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/icons/transmit.gif"))); // NOI18N
		jButton3.setFocusable(false);
		jButton3.setHorizontalTextPosition(0);
		jButton3.setMaximumSize(new java.awt.Dimension(50, 59));
		jButton3.setMinimumSize(new java.awt.Dimension(50, 59));
		jButton3.setPreferredSize(new java.awt.Dimension(50, 49));
		jButton3.setText("\u8f6c\u53d1");
		jButton3.setVerticalTextPosition(3);
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});
		jToolBar1.add(jButton3);

		jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/icons/junkmail.gif"))); // NOI18N
		jButton2.setFocusable(false);
		jButton2.setHorizontalTextPosition(0);
		jButton2.setMaximumSize(new java.awt.Dimension(45, 59));
		jButton2.setMinimumSize(new java.awt.Dimension(45, 59));
		jButton2.setText("\u5220\u9664");
		jButton2.setVerticalTextPosition(3);
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		jToolBar1.add(jButton2);

		jTable1.setEditingColumn(0);
		jTable1.setEditingRow(0);
		jTable1.setFocusable(false);
		jTable1.setShowGrid(false);
		jTable1.setShowHorizontalLines(true);
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});
		jScrollPane4.setViewportView(jTable1);

		mailTextArea.setColumns(20);
		mailTextArea.setRows(5);
		jScrollPane3.setViewportView(mailTextArea);

		org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				org.jdesktop.layout.GroupLayout.TRAILING, jToolBar1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 764,
				Short.MAX_VALUE).add(jScrollPane3,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 764,
				Short.MAX_VALUE).add(jScrollPane4,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 764,
				Short.MAX_VALUE));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel1Layout
										.createSequentialGroup()
										.add(
												jToolBar1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												59,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jScrollPane4,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												296, Short.MAX_VALUE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jScrollPane3,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												199,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)));

		tree.setFocusable(false);
		tree.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				treeMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tree);

		stateTextArea.setColumns(15);
		stateTextArea.setEditable(false);
		stateTextArea.setRows(5);
		stateTextArea.setName("stateTextArea");
		jScrollPane2.setViewportView(stateTextArea);
		stateTextArea.getAccessibleContext().setAccessibleName("stateTextArea");

		org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane2,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 175,
				Short.MAX_VALUE).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 175,
				Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel2Layout.createSequentialGroup().add(jScrollPane2,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 217,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jScrollPane1,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								343, Short.MAX_VALUE)));

		jMenu1.setText("\u9009\u9879");

		jMenuItem1.setText("\u7528\u6237\u8bbe\u7f6e");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem1);
		jMenu1.add(jSeparator1);

		jMenuItem2.setText("\u9000\u51fa");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem2);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("\u5e2e\u52a9");

		jMenuItem4.setText("\u5e2e\u52a9");
		jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem4ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem4);

		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().add(jPanel2,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jPanel1,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jPanel2,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	/**
	 * 删除邮件
	 */
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		table.removeRow(mailList.indexOf(mail));
		mailList.remove(mail);
	}

	/**
	 * 转发
	 * @param evt
	 */
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		if ("".equals(user.getUsername())) {
			JOptionPane.showConfirmDialog(null, "请先设置用户", "提示",
					JOptionPane.OK_OPTION);
			return;
		}
		mail.setFrom("");
		mail.setTo("");
		mail.setCc("");
		mail.setBcc("");
		new WriteNewMail(user, mail);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		jMenuItem1ActionPerformed(null);
	}

	/**
	 * 帮助菜单
	 */
	private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
		JOptionPane.showConfirmDialog(null, "本软件由spacelis开发.\nJavaDoc文档 请打开doc/index.html",
				"提示", JOptionPane.OK_OPTION);
	}

	/**
	 * 右键弹出菜单”清空邮箱“的响应
	 */
	private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
		treePopupMenu.setVisible(false);
		String chosBox = null;
		String deleFilename = "";
		try {
			if (tree.getLastSelectedPathComponent().equals(recvBox)) {
				chosBox = "收件箱";
				deleFilename = "user/" + user.getUsername() + "/recvBox.dat";
			} else if (tree.getLastSelectedPathComponent().equals(postBox)) {
				chosBox = "发件箱";
				deleFilename = "user/" + user.getUsername() + "/postBox.dat";
			} else if (tree.getLastSelectedPathComponent().equals(draftBox)) {
				chosBox = "草稿箱";
				deleFilename = "user/" + user.getUsername() + "/draftBox.dat";
			} else {
				chosBox = "所有邮箱";
				deleFilename = "*.dat";
			}
			if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(null,
					"确认清空" + chosBox + "?", "确认", JOptionPane.OK_CANCEL_OPTION)) {
				if (deleFilename.equals("*.dat")) {
					File file1 = new File("user/" + user.getUsername()
							+ "/recvBox.dat");
					if (file1.exists())
						file1.delete();
					File file2 = new File("user/" + user.getUsername()
							+ "/postBox.dat");
					if (file2.exists())
						file2.delete();
					File file3 = new File("user/" + user.getUsername()
							+ "/draftBox.dat");
					if (file3.exists())
						file3.delete();
				} else {
					File file1 = new File(deleFilename);
					if (file1.exists())
						file1.delete();
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Tabel的鼠标响应
	 * @param evt
	 */
	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {

		int index = jTable1.getSelectedRow();
		mail = mailList.get(jTable1.getSelectedRow());
		if (evt.getClickCount() == 1) {

			mailTextArea.setText(mail.getContent());

		} else if (evt.getClickCount() == 2) {
			if (tree.getLastSelectedPathComponent().equals(recvBox))
				new ReadMail(user, mailList, index);
			else {
				mail = mailList.get(index);
				
				new WriteNewMail(user, mail);
			}
		}
		if ("".equals(mail.getExtraFilename()))
			mail.setStatus("+");
		else
			mail.setStatus("+ &");

		if (tree.getLastSelectedPathComponent().equals(recvBox)) {
			table.removeRow(index);
			table.insertRow(index, new String[] { mail.getStatus(),
					mail.getSubject(), mail.getTo(), mail.getTime() });
		}
	}

	/**
	 * Tree的鼠标响应
	 * @param evt
	 */
	private void treeMouseClicked(java.awt.event.MouseEvent evt) {
		treePopupMenu.setVisible(false);
		if (evt.getClickCount() >= 1 && evt.getButton() == 1) {
			if (!"".equals(lastSelectedBox)) {
				try {
					MailAction.save(mailList, "user/" + user.getUsername()
							+ "/" + lastSelectedBox);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			mailList.clear();
			table = new DefaultTableModel();
			jTable1.setModel(table);
			if (tree.getLastSelectedPathComponent().equals(recvBox)) {
				//双击收件箱的动作
				table.addColumn("状态");
				table.addColumn("主题");
				table.addColumn("发件人");
				table.addColumn("时间");

				jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
				jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
				jTable1.getColumnModel().getColumn(2).setPreferredWidth(130);
				jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);

				try {

					MailAction.regain(mailList, "user/" + user.getUsername()
							+ "/recvBox.dat");
					Mail aMail;
					Iterator<Mail> it = mailList.iterator();
					while (it.hasNext()) {
						aMail = it.next();
						table.addRow(new Object[] { aMail.getStatus(),
								aMail.getSubject(), aMail.getFrom(),
								aMail.getTime() });
					}
				} catch (IOException e) {
					System.out.println("无记录");
					e.printStackTrace();
				}
				lastSelectedBox = "recvBox.dat";
			} else if (tree.getLastSelectedPathComponent().equals(postBox)) {
				//双击发件箱的动作
				table.addColumn("主题");
				table.addColumn("收件人");
				table.addColumn("发送时间");

				jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
				jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
				jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);

				try {
					MailAction.regain(mailList, "user/" + user.getUsername()
							+ "/postBox.dat");
					Mail aMail;
					Iterator<Mail> it = mailList.iterator();
					while (it.hasNext()) {
						aMail = it.next();
						table.addRow(new String[] { aMail.getSubject(),
								aMail.getTo(), aMail.getTime() });
					}
				} catch (IOException e) {
					System.out.println("无记录");
					e.printStackTrace();
				}
				lastSelectedBox = "postBox.dat";
			} else if (tree.getLastSelectedPathComponent().equals(draftBox)) {
				//双击草稿箱的动作
				table.addColumn("主题");
				table.addColumn("收件人");
				table.addColumn("保存时间");

				jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
				jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
				jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);

				try {
					MailAction.regain(mailList, "user/" + user.getUsername()
							+ "/draftBox.dat");
					Mail aMail;
					Iterator<Mail> it = mailList.iterator();
					while (it.hasNext()) {
						aMail = it.next();
						table.addRow(new String[] { aMail.getSubject(),
								aMail.getTo(), aMail.getTime() });
					}
				} catch (IOException e) {
					System.out.println("无记录");
					e.printStackTrace();
				}
				lastSelectedBox = "draftBox.dat";
			}

		} else if (evt.getButton() == 3) {//右键单击的响应
			treePopupMenu.setLocation(
					evt.getX() + tree.getLocationOnScreen().x, evt.getY()
							+ tree.getLocationOnScreen().y);
			treePopupMenu.setVisible(true);
		}
	}

	/**
	 * 新邮件按钮响应
	 */
	private void newMailButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if ("".equals(user.getUsername())) {
			JOptionPane.showConfirmDialog(null, "请先设置用户", "提示",
					JOptionPane.OK_OPTION);
			return;
		}
		mail = new Mail();
		new WriteNewMail(user, mail);
	}

	/**
	 * 收邮件按钮的响应
	 * @param evt
	 */
	private void rcvMailButtonActionPerformed(java.awt.event.ActionEvent evt) {
		rcvMailButton.setEnabled(false);
		if ("".equals(user.getUsername())) {
			JOptionPane.showConfirmDialog(null, "请先设置用户", "提示",
					JOptionPane.OK_OPTION);
			return;
		}
		mailList.clear();

		new Thread(new Runnable() {
			public void run() {
				String oldStatus = stateTextArea.getText();
				try {
					String msg = MailAction.receive(user, mailList);
					JOptionPane.showConfirmDialog(null, msg, "提示",
							JOptionPane.OK_OPTION);
					rcvMailButton.setEnabled(true);
				} catch (IOException e) {
					JOptionPane.showConfirmDialog(null, "连接服务器错误", "提示",
							JOptionPane.OK_OPTION);
					e.printStackTrace();
				}
				stateTextArea.setText(oldStatus);
			}
		}).start();
		
	}

	/**
	 * 退出菜单响应
	 * @param evt
	 */
	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	/**
	 * 用户设置菜单响应
	 * @param evt
	 */
	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
		user = new Configuration(user).getUser();
		if (user.getUsername() == "") {
			stateTextArea.setText("用户未登录\n请您先进行用户设置");
		} else {
			stateTextArea.setText("用户:" + user.getUsername()
					+ "\n   欢迎使用本软件！\n" + "smtp服务器:" + user.getSmtpServer()
					+ "\n" + "pop3服务器:" + user.getPopServer());
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		new MailClient().setVisible(true);

	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JMenuItem jMenuItem4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTable jTable1;
	private javax.swing.JToolBar jToolBar1;
	private javax.swing.JTextArea mailTextArea;
	private java.awt.Menu menu1;
	private java.awt.Menu menu2;
	private java.awt.MenuBar menuBar1;
	private java.awt.MenuBar menuBar2;
	private javax.swing.JButton newMailButton;
	private javax.swing.JButton rcvMailButton;
	public static javax.swing.JTextArea stateTextArea;
	private javax.swing.JTree tree;
	private javax.swing.JPopupMenu treePopupMenu;
	// End of variables declaration//GEN-END:variables

}