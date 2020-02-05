package edu.libsys.ui.frm.operator;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.libsys.bean.OperatorBean;
import edu.libsys.service.OperatorService;
import edu.libsys.service.impl.OperatorServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class OperatorInsertFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5107973246077245408L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public OperatorListFrm listFrm = null;

	private JLabel lblName;// 用户名的标签
	private JTextField txtName;// 用户名的文本框

	private JLabel lblAge;// 年龄的标签
	private JTextField txtAge;// 年龄的文本框

	private JLabel lblSex;// 性别的标签
	// private JTextField txtSex;//性别的文本框
	private ButtonGroup btngrpSex;// 性别单选按钮
	private JRadioButton rdoSexMale;// 性别男的单选按钮
	private JRadioButton rdoSexFemale;// 性别女的单选按钮

	private JLabel lblIdentityCard;// 证件号码的标签
	private JTextField txtIdentityCard;// 证件号码的文本框

	private JLabel lblWorkdate;// 工作时间的标签
	private JTextField txtWorkdate;// 工作时间的文本框

	private JLabel lblTel;// 电话号码的标签
	private JTextField txtTel;// 电话号码的文本框

	private JLabel lblAdmin; // 证件类型的标签
	private JComboBox<IdText> cboAdmin; // 证件类型的控件

	private JLabel lblPassword;// 职业的标签
	private JTextField txtPassword;// 职业的文本框

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public OperatorInsertFrm() {
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();
	}

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
		this.setTitle("添加读者");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);
		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		container.setLayout(null);
		// 将容器对象添加到当前窗体
		this.add(container);
		customInitUI();
	}

	/*
	 * 2.绑定事件
	 */
	public void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// 这里写定制操作
				window_closing(e);
			}
		});
		custBindEvent();
	}

	public void window_closing(WindowEvent e) {
		// 关闭主窗体，显示登陆窗体
		if (listFrm != null) {
			listFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblName = new JLabel();
		lblName.setText("用户名：");
		lblName.setBounds(50, 80, 80, 30);
		lblName.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblName);

		txtName = new JTextField();
		txtName.getText();
		txtName.setBounds(140, 80, 200, 30);
		container.add(txtName);

		lblAge = new JLabel();
		lblAge.setText("年龄：");
		lblAge.setBounds(410, 80, 80, 30);
		lblAge.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblAge);

		txtAge = new JTextField();
		txtAge.getText();
		txtAge.setBounds(500, 80, 200, 30);
		container.add(txtAge);

		lblSex = new JLabel();
		lblSex.setText("性别：");
		lblSex.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		lblSex.setBounds(60, 80, 120, 150);
		container.add(lblSex);

		rdoSexMale = new JRadioButton("男");
		rdoSexMale.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		rdoSexMale.setBounds(140, 140, 50, 30);
		container.add(rdoSexMale);
		rdoSexFemale = new JRadioButton("女");
		rdoSexFemale.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		rdoSexFemale.setBounds(190, 140, 50, 30);
		container.add(rdoSexFemale);
		// 将男女的单选按钮,放到ButtonGroup.作为一组,则只能选中一个
		btngrpSex = new ButtonGroup();
		btngrpSex.add(rdoSexMale);
		btngrpSex.add(rdoSexFemale);

		lblIdentityCard = new JLabel();
		lblIdentityCard.setText("证件号码：");
		lblIdentityCard.setBounds(400, 140, 80, 30);
		lblIdentityCard.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblIdentityCard);

		txtIdentityCard = new JTextField();
		txtIdentityCard.getText();
		txtIdentityCard.setBounds(500, 140, 200, 30);
		container.add(txtIdentityCard);

		lblWorkdate = new JLabel();
		lblWorkdate.setText("工作时间：");
		lblWorkdate.setBounds(40, 200, 120, 30);
		lblWorkdate.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblWorkdate);

		txtWorkdate = new JTextField();
		txtWorkdate.getText();
		txtWorkdate.setBounds(140, 200, 200, 30);
		container.add(txtWorkdate);

		lblTel = new JLabel();
		lblTel.setText("电话号码：");
		lblTel.setBounds(400, 200, 80, 30);
		lblTel.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblTel);

		txtTel = new JTextField();
		txtTel.getText();
		txtTel.setBounds(500, 200, 200, 30);
		container.add(txtTel);

		lblAdmin = new JLabel();
		lblAdmin.setText("是否为管理员：");
		lblAdmin.setBounds(30, 260, 120, 30);
		lblAdmin.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblAdmin);

		cboAdmin = new JComboBox<IdText>();
		cboAdmin.setBounds(140, 260, 200, 30);
		container.add(cboAdmin);

		lblPassword = new JLabel();
		lblPassword.setText("密码：");
		lblPassword.setBounds(410, 260, 80, 30);
		lblPassword.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.getText();
		txtPassword.setBounds(500, 260, 200, 30);
		container.add(txtPassword);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(250, 350, 90, 40);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(400, 350, 90, 40);
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setBounds(50, 390, 600, 30);
		lblMsg.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		lblMsg.setForeground(Color.red);
		container.add(lblMsg);
	}

	private void custBindEvent() {
		// 提交按钮事件处理
		btnSubmit.addActionListener(e -> {
			btnSubmit_click(e);
		});
		// 重置按钮事件处理
		btnReset.addActionListener(e -> {
			btnReset_click(e);
		});
	}

	private void bindComboBoxData() {
		// 绑定下拉菜单数据的过程
		// 1)加载列表
		// 2)将转为list<Idtext>
		List<IdText> idTextList = new ArrayList<IdText>();
		idTextList.add(new IdText(0L, "------------------请选择----------------"));
		idTextList.add(new IdText(1L, "否"));
		idTextList.add(new IdText(2L, "是"));

		// 3)传递给IdTextModel
		IdTextModel model = new IdTextModel(idTextList);
		// 4)再显示到cbo控件
		cboAdmin.setModel(model);
		cboAdmin.setSelectedIndex(0);// 默认选中的下标
	}

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		lblMsg.setText("");// 每次提交,先清空上次的提示信息
		// 1）获取输入数据

		String name = txtName.getText().trim();
		String itemAge = txtAge.getText().trim();
		String identityCard = txtIdentityCard.getText().trim();
		String tel = txtTel.getText().trim();
		String workdate = txtWorkdate.getText().trim();
		String password = txtPassword.getText().trim();

		IdText itemAdmin = (IdText) cboAdmin.getSelectedItem();
		Long admin = itemAdmin.getId();
		// 2)为空性判断

		if (SysFun.isNullOrEmpty(name)) {
			lblMsg.setText("提示：姓名不能为空！");
			return;
		}
		if (SysFun.isNullOrEmpty(itemAge)) {
			lblMsg.setText("提示：年龄不能为空！");
			return;
		}
		if (rdoSexMale.isSelected() == false && rdoSexFemale.isSelected() == false) {
			lblMsg.setText("提示:请选择性别");
			return;
		}
		String sex = "";
		if (rdoSexMale.isSelected()) {
			sex = "男";
		} else if (rdoSexFemale.isSelected()) {
			sex = "女";
		}

		if (SysFun.isNullOrEmpty(identityCard)) {
			lblMsg.setText("提示：证件号码不能为空！");
			return;
		}
		if (SysFun.isNullOrEmpty(workdate)) {
			lblMsg.setText("提示：工作时间不能为空！");
			return;
		}
		if (SysFun.isNullOrEmpty(tel)) {
			lblMsg.setText("提示：电话号码不能为空！");
			return;
		}

		if (cboAdmin.getSelectedIndex() == 0) {
			lblMsg.setText("提示：请选择是否为管理员");
			return;
		}
		if (SysFun.isNullOrEmpty(password)) {
			lblMsg.setText("提示：密码不能为空！");
			return;
		}
		Long age = Long.valueOf(itemAge);

		// 8)真正的添加
		OperatorBean bean = new OperatorBean();
		bean.setName(name);
		bean.setSex(sex);
		bean.setAge(age);
		bean.setIdentityCard(identityCard);
		bean.setWorkdate(workdate);
		bean.setTel(tel);
		bean.setAdmin(admin-1);
		bean.setPassword(password);

		OperatorService operatorService = new OperatorServiceImpl();
		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;
		try {
			result = operatorService.insert(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (listFrm != null) {
				listFrm.btnReset_click(null);
				listFrm.setVisible(true);
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "操作失败!");
		}
	}

	// 重置功能
	private void btnReset_click(ActionEvent e) {
		bindComboBoxData() ;
		txtName.setText("");
		rdoSexMale.setSelected(true);// 重置时默认选择男
		txtAge.setText("");
		txtIdentityCard.setText("");
		txtWorkdate.setText("");
		txtTel.setText("");
		cboAdmin.setSelectedIndex(0);
		txtPassword.setText("");
		lblMsg.setText("");
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {
		this.setTitle("添加操作者");
		bindComboBoxData();

	}

}
