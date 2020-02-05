package edu.libsys.ui.frm;

import javax.swing.*;

import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.libsys.bean.OperatorBean;
import edu.libsys.service.OperatorService;
import edu.libsys.service.impl.OperatorServiceImpl;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class LoginFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5798300372149651967L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	private JLabel lblTitle;// 界面标题
	private JLabel Type; // 用户类型
	private JComboBox<IdText> cboType; // 账号文本框
	private JLabel LoginName; // 账号
	private JTextField txtLoginName; // 账号文本框
	private JLabel LoginPass; // 密码
	private JPasswordField txtLoginPass;// 密码文本框
	private JLabel lblMsg;// 提示
	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	

	/**
	 * 无参构造方法
	 */
	public LoginFrm() {
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();
	}

	/*
	 * 加载背景图片
	 */
	ImageIcon background = new ImageIcon("picture/ONE.jpg");
	JLabel label = new JLabel(background);
	/*
	 * 1.初始化用户界面
	 */
	public void initUI() {
		// 定义当前窗体的宽高
		int width = 600;
		int height = 500;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("图书管理系统");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);
		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		container.setLayout(null);
		// 将容器对象添加到当前窗体
		this.add(container);

		/* 添加界面标题 */
		lblTitle = new JLabel();
		lblTitle.setText("图书管理系统");
		lblTitle.setBounds(200, 20, 300, 80);
		lblTitle.setFont(new Font("楷体", Font.BOLD, 29));
		lblTitle.setForeground(Color.BLACK);
		container.add(lblTitle);

		// 添加用户类型标签
		Type = new JLabel();
		Type.setText("用户类型：");
		Type.setBounds(150, 140, 100, 30);
		Type.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		Type.setForeground(Color.BLACK);
		container.add(Type);
		// 添加用户类型的文本框
		cboType = new JComboBox<IdText>();
		cboType.setBounds(250, 140, 180, 30);
		container.add(cboType);
		// 添加账号标签
		LoginName = new JLabel();
		LoginName.setText("账号:");
		LoginName.setBounds(160, 210, 80, 30);
		LoginName.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		LoginName.setForeground(Color.BLACK);
		container.add(LoginName);
		// 添加账号的文本框
		txtLoginName = new JTextField();
		txtLoginName.getText();
		txtLoginName.setBounds(250, 210, 180, 30);
		container.add(txtLoginName);
		// 添加密码标签
		LoginPass = new JLabel();
		LoginPass.setText("密码：");
		LoginPass.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		LoginPass.setForeground(Color.BLACK);
		LoginPass.setBounds(160, 280, 80, 30);
		container.add(LoginPass);
		// 添加密码的文本框
		txtLoginPass = new JPasswordField();
		txtLoginPass.setText("");
		txtLoginPass.setBounds(250, 280, 180, 30);
		container.add(txtLoginPass);
		// 添加登录按钮
		btnSubmit = new JButton();
		btnSubmit.setText("登录");
		btnSubmit.setBounds(160, 350, 90, 30);
		container.add(btnSubmit);
		// 添加重置按钮
		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(320, 350, 90, 30);
		container.add(btnReset);
		// 添加提示信息标签
		lblMsg = new JLabel();
		lblMsg.setText("");
		lblMsg.setFont(new Font("宋体", Font.CENTER_BASELINE, 16));
		lblMsg.setForeground(Color.red);
		lblMsg.setBounds(140, 420, 500, 30);
		container.add(lblMsg);
		
		/**
		 * 背景要放在最后添加，否则会覆盖之前的标签等
		 * 
		 * 设置标签大小
		 */
		label.setBounds(-100, -100, background.getIconWidth(), background.getIconHeight());
		container.add(label);
	}

	/*
	 * 2.绑定事件
	 */
	public void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 提交按钮事件
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				btnSubmit_click(e);

			}
		});
		// 重置按钮事件
		btnReset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				btnReset_click(e);

			}
		});
		// 输入账号再输入密码后直接按回车键可触发登录事件
		txtLoginPass.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				if (k == e.VK_ENTER) {
					btnSubmit_click(null);
				}
			}
		});
	}

	/*
	 * 3.自定义加载
	 */
	public void customLoad() {
		bindComboBoxData();
	}

	private void bindComboBoxData() {
		// TODO Auto-generated method stub
		// 绑定下拉菜单数据的过程
		// list<Idtext>
		List<IdText> idTextListA = new ArrayList<IdText>();
		idTextListA.add(new IdText(0L, "----请选择用户类型----"));
		idTextListA.add(new IdText(1L, "管理员"));
		idTextListA.add(new IdText(2L, "操作员"));
		// 传递给IdTextModel
		IdTextModel modelA = new IdTextModel(idTextListA);
		// 显示到cbo控件
		cboType.setModel(modelA);
		// 默认显示"----请选择用户类型----"
		cboType.setSelectedIndex(0);
	}

	/**
	 * 登录功能
	 */
	public void btnSubmit_click(ActionEvent e) {
		// 读取文本框的内容,忽略空格
		String loginName = txtLoginName.getText().trim();
		@SuppressWarnings("deprecation")
		String loginPass = txtLoginPass.getText().trim();
		if(cboType.getSelectedIndex()==0) {
			lblMsg.setText("提示：请选择用户类型。");
			return;
		}
		if (loginName == null || loginName.isEmpty()) {
			lblMsg.setText("提示：请输入账号。");
			return;
		}
		if (loginPass == null || loginPass.isEmpty()) {
			lblMsg.setText("提示：请输入密码。");
			return;
		}

		// 获取下拉菜单的种类
		String person = cboType.getSelectedItem().toString();
		// 根据下拉菜单跳转相应的登录方式
		switch (person) {
		case "管理员":
			managerLogin(loginName, loginPass);
			break;
		case "操作员":
			operatorLogin(loginName, loginPass);
			break;
		
		}
	}
	OperatorService operatorService = new OperatorServiceImpl();
 
	/**
	 * 管理员的登录方式
	 */
	private void managerLogin(String loginName, String loginPass) {
		// TODO Auto-generated method stub
		boolean isOK = false;
		// 判断账号密码是否一致
		OperatorBean bean = operatorService.loadByName(loginName);
		if (bean == null) {
			lblMsg.setText("提示：账号输入有误，登陆失败！");
			return;
		}
		isOK = bean.getPassword().equals(loginPass);
		if (!isOK) {
			lblMsg.setText("提示：密码输入有误，登陆失败！");
			return;
		}
		if (bean.getAdmin() == 0) {
			lblMsg.setText("提示：抱歉，您没有此权限。请选择操作员类型！");
			return;
		}

		lblMsg.setText("登陆成功。");

		ManagerMainFrm managermainFrm = new ManagerMainFrm();
		managermainFrm.loginFrm = this;
		managermainFrm.refreshTitle(loginName);

		// 隐藏登录窗口
		this.setVisible(false);
		// 显示管理员管理窗口
		managermainFrm.setVisible(true);
		// 登陆成功清除账号密码
		btnReset_click(null);
	}

	/**
	 * 操作员的登录方式
	 */
	private void operatorLogin(String loginName, String loginPass) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		boolean isOK = false;
		// 判断账号密码是否一致
		OperatorBean bean = operatorService.loadByName(loginName);
		if (bean == null) {
			lblMsg.setText("提示：账号输入有误，登陆失败！");
			return;
		}
		isOK = bean.getPassword().equals(loginPass);
		if (!isOK) {
			lblMsg.setText("提示：密码输入有误，登陆失败！");
			return;
		}
		if (bean.getAdmin() == 1) {
			lblMsg.setText("提示：抱歉，您没有此权限。请选择管理员类型！");
			return;
		}
		lblMsg.setText("登陆成功。");

		OperatorMainFrm operatormainFrm = new OperatorMainFrm();

		operatormainFrm.loginFrm = this;

		operatormainFrm.refreshTitle(loginName);
		// 隐藏登录窗口
		this.setVisible(false);
		// 显示管理员管理窗口
		operatormainFrm.setVisible(true);
		// 登陆成功清除账号密码
		btnReset_click(null);
	}

	
	/**
	 * 重置功能
	 */
	public void btnReset_click(ActionEvent e) {
		// 清空账号密码以及提示信息
		txtLoginName.setText("");
		txtLoginPass.setText("");
		lblMsg.setText("");
		// 用户类型一栏返回"请选择用户类型"
		bindComboBoxData();
		cboType.setSelectedIndex(0);

	}

}
