package edu.libsys.ui.frm.order;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.libsys.bean.OperatorBean;
import edu.libsys.bean.OrderBean;
import edu.libsys.service.OperatorService;
import edu.libsys.service.OrderService;
import edu.libsys.service.impl.OperatorServiceImpl;
import edu.libsys.service.impl.OrderServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderUpdateFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5227887501116455134L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public OrderListFrm orderListFrm = null;

	private JLabel lblISBN;// 图书编号的标签
	private JTextField txtISBN;// 图书编号的文本框

	private JLabel lblDate;// 订购日期的标签
	private JTextField txtDate;// 订购日期的文本框

	private JLabel lblNumber;// 订购数量的标签
	private JTextField txtNumber;// 订购数量的文本框

	private JLabel lblOperator;// 操作员的标签
	private JComboBox<IdText> cboOperator;// 操作员的文本框

	private JLabel lblCheakAndAccept;// 是否验收的标签
	private JComboBox<IdText> cboCheakAndAccept;// 是否验收的文本框

	private JLabel lblZk;// 书籍折扣的标签
	private JTextField txtZk;// 书籍折扣的文本框

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public OrderUpdateFrm() {
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
		int width = 400;
		int height = 650;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("题库管理");
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
		if (this.orderListFrm != null) {
			orderListFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblISBN = new JLabel();
		lblISBN.setText("图书编号：");
		lblISBN.setBounds(50, 60, 80, 30);
		lblISBN.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblISBN);

		txtISBN = new JTextField();
		txtISBN.getText();
		txtISBN.setBounds(140, 60, 200, 30);
		txtISBN.setEnabled(false);
		container.add(txtISBN);

		lblDate = new JLabel();
		lblDate.setText("订购日期：");
		lblDate.setBounds(50, 130, 80, 30);
		lblDate.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblDate);

		txtDate = new JTextField();
		txtDate.getText();
		txtDate.setBounds(140, 130, 200, 30);
		container.add(txtDate);

		lblNumber = new JLabel();
		lblNumber.setText("订购数量：");
		lblNumber.setBounds(50, 200, 80, 30);
		lblNumber.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblNumber);

		txtNumber = new JTextField();
		txtNumber.getText();
		txtNumber.setBounds(140, 200, 200, 30);
		container.add(txtNumber);

		lblOperator = new JLabel();
		lblOperator.setText("操作员名称：");
		lblOperator.setBounds(40, 270, 120, 30);
		lblOperator.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblOperator);

		cboOperator = new JComboBox<IdText>();
		cboOperator.setBounds(140, 270, 200, 30);
		container.add(cboOperator);

		lblCheakAndAccept = new JLabel();
		lblCheakAndAccept.setText("是否验收：");
		lblCheakAndAccept.setBounds(50, 340, 120, 30);
		lblCheakAndAccept.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblCheakAndAccept);

		cboCheakAndAccept = new JComboBox<IdText>();
		cboCheakAndAccept.setBounds(140, 340, 200, 30);
		container.add(cboCheakAndAccept);

		lblZk = new JLabel();
		lblZk.setText("书籍折扣：");
		lblZk.setBounds(50, 410, 80, 30);
		lblZk.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblZk);

		txtZk = new JTextField();
		txtZk.getText();
		txtZk.setBounds(140, 410, 200, 30);
		container.add(txtZk);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(70, 470, 90, 40);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(230, 470, 90, 40);
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setBounds(50, 500, 600, 30);
		lblMsg.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		lblMsg.setForeground(Color.red);
		container.add(lblMsg);
	}

	private void custBindEvent() {
		// 提交按钮事件处理
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSubmit_click(e);
			}

		});
		// 重置按钮事件处理
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnReset_click(e);
			}

		});
	}

	OperatorService operatorService = new OperatorServiceImpl();
	OrderService orderService = new OrderServiceImpl();

	private void bindComboBoxData() {
		// 绑定下拉菜单数据的过程
		// 1)从数据库加载列表
		List<OperatorBean> list = operatorService.list();
		// 2)将转为list<Idtext>
		List<IdText> idTextList = new ArrayList<IdText>();
		for (OperatorBean item : list) {
			idTextList.add(new IdText(item.getId(), item.getName()));
		}
		// 3)传递给IdTextModel
		IdTextModel model = new IdTextModel(idTextList);
		// 4)再显示到cbo控件
		cboOperator.setModel(model);

		List<IdText> idTextListD = new ArrayList<IdText>();
		idTextListD.add(new IdText(0L, "否"));
		idTextListD.add(new IdText(1L, "是"));
		IdTextModel modelD = new IdTextModel(idTextListD);
		cboCheakAndAccept.setModel(modelD);

	}

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		lblMsg.setText("");// 每次提交,先清空上次的提示信息
		// 1）获取输入数据
		String ISBN = txtISBN.getText().trim();// trim忽略前后空格
		String itemnumber = txtNumber.getText().trim();
		String itemZk = txtZk.getText().trim();
		String itemDate = txtDate.getText().trim();

		String operator = cboOperator.getSelectedItem().toString();
		IdText itemCheakAndAccept = (IdText) cboCheakAndAccept.getSelectedItem();

		// 2)为空性判断
		if (SysFun.isNullOrEmpty(ISBN)) {
			lblMsg.setText("提示：图书编号不能为空!");
			return;
		}
		if (SysFun.isNullOrEmpty(itemDate)) {
			lblMsg.setText("提示：订购日期不能为空!");
			return;
		}
		if (SysFun.isNullOrEmpty(itemnumber)) {
			lblMsg.setText("提示：订购数量不能为空!");
			return;
		}
		if (cboOperator.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择图书类别");
			return;
		}
		if (cboCheakAndAccept.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择操作员");
			return;
		}
		if (SysFun.isNullOrEmpty(itemZk)) {
			lblMsg.setText("提示：书籍折扣不能为空!");
			return;
		}

		Long cheakAndAccept = itemCheakAndAccept.getId();
		Timestamp date = Timestamp.valueOf(itemDate);
		Double zk = Double.valueOf(itemZk);
		Long number = Long.valueOf(itemnumber);
		if (zk < 0 || zk > 10) {
			lblMsg.setText("提示：书籍折扣输入有误！");
			return;
		}

		// 8)真正的修改
		OrderBean bean = new OrderBean();
		bean.setDate(date);
		bean.setOperator(operator);
		bean.setNumber(number);
		bean.setCheakAndAccept(cheakAndAccept);
		bean.setZk(zk);
		bean.setISBN(ISBN);

		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;
		try {
			result = orderService.update(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (orderListFrm != null) {
				orderListFrm.btnReset_click(null);
				orderListFrm.setVisible(true);
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "操作失败!");
		}
	}

	// 重置功能
	private void btnReset_click(ActionEvent e) {
		bindComboBoxData();
		loadData();
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {
		bindComboBoxData();
		this.setTitle("修改题库");
	}

	/**
	 * 【修改窗体之加载数据--步骤1】声明当前修改的数据的主键值。从列表数据中传递过来
	 */
	String pk = null;

	/**
	 * 【修改窗体之加载数据--步骤2】根据pk加载要修改的主键对应的数据，并显示窗体的标题
	 */
	public void loadData() {

		if (!this.getTitle().contains("主键")) {
			this.setTitle(this.getTitle() + "-->正在修改ISBN为【" + pk + "】的学生信息");
		}
		OrderBean bean = orderService.load(pk);

		if (bean != null) {
			txtISBN.setText(bean.getISBN());
			txtDate.setText(bean.getDate().toString());
			txtNumber.setText(bean.getNumber().toString());
			txtZk.setText(bean.getZk().toString());
			if (bean.getCheakAndAccept() == 0L) {
				cboCheakAndAccept.getModel().setSelectedItem(new IdText(0L, ""));
			} else {
				cboCheakAndAccept.getModel().setSelectedItem(new IdText(1L, ""));
			}
			cboOperator.getModel()
					.setSelectedItem(new IdText(operatorService.loadByName(bean.getOperator()).getId(), ""));
		}

	}
}