package edu.libsys.ui.frm.bookType;

import com.liuvei.common.SysFun;

import edu.libsys.bean.BookTypeBean;
import edu.libsys.service.BookTypeService;
import edu.libsys.service.impl.BookTypeServiceImpl;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BookTypeUpdateFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1439178593925529769L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public BookTypeListFrm bookTypeListFrm = null;

	private JLabel lblTypeName;// 图书类别名称的标签
	private JTextField txtTypeName;// 图书类别名称的文本框

	private JLabel lbldays; // 可借天数的标签
	private JTextField txtdays;// 可借天数的文本框

	private JLabel lblFk;// 迟还一天的罚款的标签
	private JTextField txtFk;// 迟还一天的罚款的文本框

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public BookTypeUpdateFrm() {
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
		int height = 300;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("系统登录");
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
		if (this.bookTypeListFrm != null) {
			bookTypeListFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblTypeName = new JLabel();
		lblTypeName.setText("图书类别名称：");

		lblTypeName.setBounds(70, 60, 120, 30);
		container.add(lblTypeName);

		txtTypeName = new JTextField();
		txtTypeName.getText();

		txtTypeName.setBounds(180, 60, 160, 30);
		container.add(txtTypeName);

		lbldays = new JLabel();
		lbldays.setText("可借天数：");

		lbldays.setBounds(90, 65, 100, 100);
		container.add(lbldays);

		txtdays = new JTextField();
		txtdays.getText();

		txtdays.setBounds(180, 100, 160, 30);
		container.add(txtdays);

		lblFk = new JLabel();
		lblFk.setText("迟还一天的罚款：");

		lblFk.setBounds(60, 80, 120, 150);
		container.add(lblFk);

		txtFk = new JTextField();
		txtFk.getText();

		txtFk.setBounds(180, 140, 160, 30);
		container.add(txtFk);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(130, 210, 70, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(230, 210, 70, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
		// lblMsg.setText("提示信息");
		lblMsg.setBounds(70, 280, 180, 30);
		lblMsg.setForeground(Color.RED);
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

	BookTypeService bookTypeService = new BookTypeServiceImpl();

	// 提交功能
	@SuppressWarnings("unused")
	private void btnSubmit_click(ActionEvent e) {
		// 1）获取输入数据
		// trim忽略前后空格
		String TypeName = txtTypeName.getText().trim();// trim忽略前后空格
		String itemDays = txtdays.getText().trim();
		Long Days = Long.parseLong(itemDays);
		String itemFk = txtFk.getText().trim();
		Double Fk = Double.valueOf(itemFk);

		// 2)为空性判断
		if (SysFun.isNullOrEmpty(TypeName)) {
			lblMsg.setText("提示：图书类别名称不能为空!");
			return;
		}
		if (Days == null) {
			lblMsg.setText("提示：可借天数不能为空!");
			return;
		}
		if (Fk == null) {
			lblMsg.setText("提示：迟还一天的罚款不能为空!");
			return;

		}

		// 修改时,根据名称取得对象
		// a)对象的主键如果与当前修改的主键一致,说明取得的是当前修改对象,允许修改
		// b)对象的主键如果与当前修改的主键不一致,说明存在另一个对象的名称跟当前修改内容一致,说明名称重复
		// 5)真正的修改
		BookTypeBean bean = new BookTypeBean();
		bean.setId(pk);
		bean.setTypeName(TypeName);
		bean.setDays(Days);
		bean.setFk(Fk);

		// 6)处理结果
		Long result = 0L;
		String errMsg = null;

		try {
			result = bookTypeService.update(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果修改结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (bookTypeListFrm != null) {
				bookTypeListFrm.btnReset_click(null);
				bookTypeListFrm.setVisible(true);
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "操作失败!");
		}
	}

	// 重置功能
	private void btnReset_click(ActionEvent e) {
		loadData();
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {
		this.setTitle("修改图书类别信息");
	}

	/**
	 * 【修改窗体之加载数据--步骤1】声明当前修改的数据的主键值。从列表数据中传递过来
	 */
	Long pk = null;

	/**
	 * 【修改窗体之加载数据--步骤2】根据pk加载要修改的主键对应的数据，并显示窗体的标题
	 */
	public void loadData() {
		if (pk == null) {
			JOptionPane.showMessageDialog(null, "主键值为空,加载数据失败");
			return;
		}
		BookTypeBean bean = bookTypeService.load(pk);
		if (bean == null) {
			JOptionPane.showMessageDialog(null, "主键值对应的数据不存在,加载数据失败");
			return;
		}
		this.setTitle(this.getTitle() + "-->正在修改id为【" + pk + "】的管理员信息");
		if (bean != null) {
			txtTypeName.setText(bean.getTypeName());
			txtdays.setText(bean.getDays().toString());
			txtFk.setText(bean.getFk().toString());

		}
	}
}