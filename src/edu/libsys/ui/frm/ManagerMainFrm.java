package edu.libsys.ui.frm;

import javax.swing.*;

import edu.libsys.ui.frm.bookInfo.BookInfoListFrm;
import edu.libsys.ui.frm.bookType.BookTypeListFrm;
import edu.libsys.ui.frm.borrrow.BorrowListFrm;
import edu.libsys.ui.frm.operator.OperatorListFrm;
import edu.libsys.ui.frm.operator.OperatorPwdFrm;
import edu.libsys.ui.frm.order.OrderListFrm;
import edu.libsys.ui.frm.teafer.TeaferListFrm;

import java.awt.*;
import java.awt.event.*;

public class ManagerMainFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 206158037318196360L;

	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;

	private JMenuBar menuBar;

	// 系统维护
	private JMenu windows;

	// 权限管理
	private JMenu jurisdiction;
	// 修改密码
	private JMenuItem updatePwd;

	// 注销
	private JMenuItem logout;
	// 退出
	private JMenuItem exit;

	// 图书类别管理
	private JMenuItem bookTypeManage;
	// 图书信息管理
	private JMenuItem bookInfoManage;
	// 读者信息管理
	private JMenuItem teaferManage;
	// 新书订购管理
	private JMenuItem orderManage;
	// 图书借阅管理
	private JMenuItem borrowManage;
	// 操作员信息管理
	private JMenuItem operatorManage;

	public LoginFrm loginFrm;
	private String loginName;

	private JPanel statusBar;// 状态条
	private JLabel status;// 状态栏

	private void ststusBarInitUI() {
		// 示例前置条件：需要将container的布局由绝对布局null改为BorderLAyout布局，代码如下：
		// container.setLayout(new BorderLayout());

		// 创建状态条面板，将设置为流式布局------从左到右
		statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// 创建状态栏
		status = new JLabel();
		status.setText("状态栏");
		// 将状态栏放到状态条
		statusBar.add(status);

		// 将状态栏放在最底部【BorderLayout的南方SOUTH】
		this.add(statusBar, BorderLayout.SOUTH);
	}

	/**
	 * 无参构造方法
	 */
	public ManagerMainFrm() {
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();

		ststusBarInitUI();
	}

	/*
	 * 加载背景图片
	 */
	ImageIcon background = new ImageIcon("picture/TWO.png");
	JLabel label = new JLabel(background);

	/*
	 * 1.初始化用户界面
	 */
	public void initUI() {
		// 定义当前窗体的宽高
		int width = 750;
		int height = 500;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("管理员");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);

		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		// container.setLayout(null);
		container.setLayout(new BorderLayout());
		// 将容器对象添加到当前窗体
		// this.add(container, BorderLayout.CENTER);
		this.add(container);

		// 创建菜单栏对象
		menuBar = new JMenuBar();
		// 将菜单栏对象添加到窗体的菜单栏中
		setJMenuBar(menuBar);

		// 创建系统管理菜单
		windows = new JMenu("系统管理(S)");
		// 设置快捷键Alt+S打开系统管理菜单
		windows.setMnemonic('S');
		// 将系统管理添加到菜单栏对象中
		menuBar.add(windows);

		// 创建修改密码栏
		updatePwd = new JMenuItem("修改密码    Ctrl+X");
		// 设置快捷键Ctrl+X打开修改密码栏
		updatePwd.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK));
		updatePwd.setMnemonic('X');
		// 将修改密码栏添加到系统管理菜单下
		windows.add(updatePwd);

		// 创建注销栏
		logout = new JMenuItem("注销   Ctrl+Q");
		// 设置快捷键Ctrl+Q打开注销栏
		logout.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_DOWN_MASK));
		logout.setMnemonic('Q');
		// 将注销栏添加到系统管理菜单下
		windows.add(logout);

		// 创建退出栏
		exit = new JMenuItem("退出  Ctrl+E");
		// 设置快捷键Ctrl+E打开退出栏
		exit.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));
		exit.setMnemonic('E');
		// 将退出栏添加到系统菜单下
		windows.add(exit);

		// 创建权限管理菜单
		jurisdiction = new JMenu("权限管理(B)");
		// 设置快捷键Alt+B打开权限管理菜单
		jurisdiction.setMnemonic('B');
		// 将权限管理菜单添加到菜单栏对象中
		menuBar.add(jurisdiction);

		// 创建图书类别管理栏
		bookTypeManage = new JMenuItem("图书类别管理    Ctrl+M");
		// 设置快捷键Ctrl+M打开图书类别管理栏
		bookTypeManage.setAccelerator(KeyStroke.getKeyStroke('M', InputEvent.CTRL_DOWN_MASK));
		bookTypeManage.setMnemonic('M');
		// 将图书类别管理栏添加到权限管理菜单下
		jurisdiction.add(bookTypeManage);

		// 创管图书信息管理栏
		bookInfoManage = new JMenuItem("图书信息管理    Ctrl+S");
		// 设置快捷键Ctrl+S打开图书信息管理栏
		bookInfoManage.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
		bookInfoManage.setMnemonic('S');
		// 将图书信息管理栏添加到权限管理菜单下
		jurisdiction.add(bookInfoManage);

		// 创建读者信息管理栏
		teaferManage = new JMenuItem("读者信息管理    Ctrl+J");
		// 设置快捷键Ctrl+J打开读者信息管理栏
		teaferManage.setAccelerator(KeyStroke.getKeyStroke('J', InputEvent.CTRL_DOWN_MASK));
		teaferManage.setMnemonic('J');
		// 将读者信息管理栏添加到权限管理菜单下
		jurisdiction.add(teaferManage);

		// 创建新书订购管理栏
		orderManage = new JMenuItem("新书订购管理    Ctrl+C");
		// 设置快捷键Ctrl+C打开新书订购管理栏
		orderManage.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
		orderManage.setMnemonic('C');
		// 将新书订购管理栏添加到权限管理菜单下
		jurisdiction.add(orderManage);

		// 创建图书借阅管理栏
		borrowManage = new JMenuItem("图书借阅管理    Ctrl+D");
		// 设置快捷键Ctrl+D打开图书借阅管理栏
		borrowManage.setAccelerator(KeyStroke.getKeyStroke('D', InputEvent.CTRL_DOWN_MASK));
		borrowManage.setMnemonic('D');
		// 将图书借阅管理栏添加到权限管理菜单下
		jurisdiction.add(borrowManage);

		// 创建操作员信息管理栏
		operatorManage = new JMenuItem("操作员信息管理    Ctrl+G");
		// 设置快捷键Ctrl+G打开年级管理栏
		operatorManage.setAccelerator(KeyStroke.getKeyStroke('G', InputEvent.CTRL_DOWN_MASK));
		operatorManage.setMnemonic('G');
		// 将年级管理栏添加到权限管理菜单下
		jurisdiction.add(operatorManage);

		
		/**
		 * 背景要放在最后添加，否则会覆盖之前的标签等
		 * 
		 * 设置标签大小
		 */
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		container.add(label);
	}

	/*
	 * 2.绑定事件
	 */
	public void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// 为当前的窗体添加监听器：目的是重写windowsClosing事件的处理方法
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// 这里写定制操作
				window_closing(e);

			}

		});
		// 为图书类别管理栏添加事件监听器
		bookTypeManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bookTypeManage_click(e);
			}

		});
		// 为图书信息管理栏添加事件监听器
		bookInfoManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bookInfoManage_click(e);
			}

		});

		// 为读者信息管理栏添加事件监听器
		teaferManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				teaferManage_click(e);
			}

		});

		// 为新书订购管理栏添加事件监听器
		orderManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				orderManage_click(e);
			}

		});
		// 为图书借阅管理栏添加事件监听器
		borrowManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				borrowManage_click(e);
			}

		});
		// 为操作员信息管理栏添加事件监听器
		operatorManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				operatorManage_click(e);
			}

		});
		// 为修改密码栏添加事件监听器
		updatePwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updatePwd_click(e);
			}

		});
		// 为注销栏添加事件监听器
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logout_click(e);
			}

		});
		// 为退出栏添加事件监听器
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exit_click(e);
			}

		});

	}

	// 当点击图书类别管理栏时,状态栏会提示:图书类别管理,并弹出图书类别管理窗体
	protected void bookTypeManage_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：图书类别管理");
		BookTypeListFrm frm = new BookTypeListFrm();
		frm.setVisible(true);
	}

//当点击图书信息管理栏时,状态栏会提示:图书信息管理,并弹出图书信息管理窗体
	protected void bookInfoManage_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：图书信息管理");
		BookInfoListFrm frm = new BookInfoListFrm();
		frm.setVisible(true);
	}

//当点击修改密码栏时,状态栏会提示:修改密码,并弹出修改密码窗体
	private void updatePwd_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("修改密码");
		OperatorPwdFrm managerPwdFrm = new OperatorPwdFrm();
		managerPwdFrm.loginName = loginName;
		managerPwdFrm.setVisible(true);

	}

	// 当点击读者信息管理栏时，状态栏会提示：读者信息管理,并弹出读者信息管理窗体
	public void teaferManage_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：读者信息管理");
		TeaferListFrm frm = new TeaferListFrm();
		frm.setVisible(true);
	}

	// 当点击新书订购管理栏时，状态栏会提示：新书订购管理,并弹出新书订购管理窗体
	public void orderManage_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：新书订购管理");
		OrderListFrm frm = new OrderListFrm();
		frm.setVisible(true);
	}

	// 当点击图书借阅管理栏时,状态栏会提示:图书借阅管理,并弹出图书借阅管理窗体
	public void borrowManage_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：图书借阅管理");
		BorrowListFrm frm = new BorrowListFrm();
		frm.setVisible(true);
	}

	// 当点击操作员信息管理栏时，状态栏会提示：操作员信息管理,并弹出操作员信息管理窗体
	public void operatorManage_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：操作员信息管理");
		OperatorListFrm frm = new OperatorListFrm();
		frm.setVisible(true);
	}

	// 当点击注销栏时，状态栏会提示：注销；并会弹出确认注销窗口
	public void logout_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：注销");
		int option = JOptionPane.showConfirmDialog(this, "确定注销系统吗？", "提示", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			if (loginFrm != null) {
				this.dispose();
				loginFrm.setVisible(true);

			}
		}
	}

	// 当点击退出栏时，状态栏会提示：退出；并会弹出确认退出窗口
	public void exit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：退出");

		int res = JOptionPane.showConfirmDialog(null, "是否继续？", "提示：即将退出", JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
			System.out.println("已退出！");
			System.exit(0);
		} else {
			return;
		}
	}

	// 当点击右上角的关闭按钮会弹出确认退出窗口
	public void window_closing(WindowEvent e) {
		int option = JOptionPane.showConfirmDialog(null, "是否继续？", "提示：即将退出", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			if (e.getWindow() == this) {
				System.exit(0);
			}
		}
	}

	/*
	 * 3.自定义加载
	 */
	public void customLoad() {
	}

	// 窗口标题显示
	public void refreshTitle(String loginName) {
		this.loginName = loginName;
		this.setTitle(this.getTitle() + "- 【" + loginName + "】");
	}

}
