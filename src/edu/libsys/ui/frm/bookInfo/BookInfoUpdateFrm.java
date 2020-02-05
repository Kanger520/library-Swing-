package edu.libsys.ui.frm.bookInfo;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.libsys.bean.BookInfoBean;
import edu.libsys.bean.BookTypeBean;
import edu.libsys.service.BookInfoService;
import edu.libsys.service.BookTypeService;
import edu.libsys.service.impl.BookInfoServiceImpl;
import edu.libsys.service.impl.BookTypeServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookInfoUpdateFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5227887501116455134L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public BookInfoListFrm bookInfoListFrm = null;

	private JLabel lblTypeName;// 图书类别的标签
	private JComboBox<IdText> cboTypeName;// 图书类别的文本框

	private JLabel lblBookName;// 图书名称的标签
	private JTextField txtBookName;// 图书名称的文本框

	private JLabel lblWriter;// 作者的标签
	private JTextField txtWriter;// 作者的文本框

	private JLabel lblTranslator;// 译者的标签
	private JTextField txtTranslator;// 译者的文本框

	private JLabel lblPublisher;// 出版社的标签
	private JTextField txtPublisher;// 出版社的文本框

	private JLabel lblDate;// 出版日期的标签
	private JTextField txtDate;// 出版日期的文本框

	private JLabel lblPrice;// 书籍价格的标签
	private JTextField txtPrice;// 书籍价格的文本框

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public BookInfoUpdateFrm() {
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
		int height = 580;
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
		if (this.bookInfoListFrm != null) {
			bookInfoListFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblTypeName = new JLabel();
		lblTypeName.setText("图书类别：");
		lblTypeName.setBounds(50, 60, 80, 30);
		lblTypeName.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblTypeName);

		cboTypeName = new JComboBox<IdText>();
		cboTypeName.setBounds(140, 60, 200, 30);
		container.add(cboTypeName);

		lblBookName = new JLabel();
		lblBookName.setText("图书名称：");
		lblBookName.setBounds(50, 110, 80, 30);
		lblBookName.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblBookName);

		txtBookName = new JTextField();
		txtBookName.getText();
		txtBookName.setBounds(140, 110, 200, 30);
		container.add(txtBookName);

		lblWriter = new JLabel();
		lblWriter.setText("作者：");
		lblWriter.setBounds(60, 160, 80, 30);
		lblWriter.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblWriter);

		txtWriter = new JTextField();
		txtWriter.getText();
		txtWriter.setBounds(140, 160, 200, 30);
		container.add(txtWriter);

		lblTranslator = new JLabel();
		lblTranslator.setText("译者：");
		lblTranslator.setBounds(60, 210, 80, 30);
		lblTranslator.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblTranslator);

		txtTranslator = new JTextField();
		txtTranslator.getText();
		txtTranslator.setBounds(140, 210, 200, 30);
		container.add(txtTranslator);

		lblPublisher = new JLabel();
		lblPublisher.setText("出版社：");
		lblPublisher.setBounds(60, 260, 80, 30);
		lblPublisher.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblPublisher);

		txtPublisher = new JTextField();
		txtPublisher.getText();
		txtPublisher.setBounds(140, 260, 200, 30);
		container.add(txtPublisher);

		lblDate = new JLabel();
		lblDate.setText("出版日期：");
		lblDate.setBounds(50, 310, 80, 30);
		lblDate.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblDate);

		txtDate = new JTextField();
		txtDate.getText();
		txtDate.setBounds(140, 310, 200, 30);
		container.add(txtDate);

		lblPrice = new JLabel();
		lblPrice.setText("书籍价格：");
		lblPrice.setBounds(50, 360, 80, 30);
		lblPrice.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.getText();
		txtPrice.setBounds(140, 360, 200, 30);
		container.add(txtPrice);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(70, 460, 90, 40);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(230, 460, 90, 40);
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setBounds(20, 520, 600, 30);
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

	BookInfoService bookInfoService = new BookInfoServiceImpl();
	BookTypeService bookTypeService = new BookTypeServiceImpl();

	private void bindComboBoxData() {
		// 绑定下拉菜单数据的过程
		// 1)从数据库加载列表
		List<BookTypeBean> list = bookTypeService.list();
		// 2)将转为list<Idtext>
		List<IdText> idTextList = new ArrayList<IdText>();
		for (BookTypeBean item : list) {
			idTextList.add(new IdText(item.getId(), item.getTypeName()));
		}
		// 3)传递给IdTextModel
		IdTextModel model = new IdTextModel(idTextList);
		// 4)再显示到cbo控件
		cboTypeName.setModel(model);
	}

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		// 1）获取输入数据
		String bookName = txtBookName.getText().trim();
		String writer = txtWriter.getText().trim();
		String translator = txtTranslator.getText().trim();
		String publisher = txtPublisher.getText().trim();

		IdText itemTypeName = (IdText) cboTypeName.getSelectedItem();
		Long typeName = itemTypeName.getId();

		String itemDate = txtDate.getText().trim();

		String itemPrice = txtPrice.getText().trim();

		// 2)为空性判断
		if (cboTypeName.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择图书类别");
			return;
		}

		if (SysFun.isNullOrEmpty(bookName)) {
			lblMsg.setText("提示：图书名称不能为空!");
			return;
		}
		if (SysFun.isNullOrEmpty(writer)) {
			lblMsg.setText("提示：作者不能为空!");
			return;
		}
		if (SysFun.isNullOrEmpty(translator)) {
			lblMsg.setText("提示：译者不能为空!");
			return;
		}
		if (SysFun.isNullOrEmpty(publisher)) {
			lblMsg.setText("提示：出版社不能为空!");
			return;
		}
		if (SysFun.isNullOrEmpty(itemDate)) {
			lblMsg.setText("提示：出版日期不能为空!");
			return;
		}
		DateFormat format = new SimpleDateFormat("YYYY-mm-dd HH:MM:SS");
		try {
			format.parse(itemDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			lblMsg.setText("提示：出版日期格式有误！");
			return;
		}
		Timestamp date = Timestamp.valueOf(itemDate);
		Double price = Double.valueOf(itemPrice);
		if (price == null) {
			lblMsg.setText("提示：书籍价格不能为空!");
			return;
		}

		// 8)真正的修改
		BookInfoBean bean = new BookInfoBean();
		bean.setTypeId(typeName);
		bean.setBookName(bookName);
		bean.setWriter(writer);
		bean.setTranslator(translator);
		bean.setPublisher(publisher);
		bean.setDate(date);
		bean.setPrice(price);
		bean.setISBN(pk);

		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;
		try {
			result = bookInfoService.update(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (bookInfoListFrm != null) {
				bookInfoListFrm.btnReset_click(null);
				bookInfoListFrm.setVisible(true);
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
		BookInfoBean bean = bookInfoService.load(pk);

		if (bean != null) {
			txtBookName.setText(bean.getBookName());
			txtWriter.setText(bean.getWriter());
			txtTranslator.setText(bean.getTranslator());
			txtPublisher.setText(bean.getPublisher());
			txtDate.setText(bean.getDate().toString());
			txtPrice.setText(bean.getPrice().toString());
			cboTypeName.getModel().setSelectedItem(new IdText(bean.getTypeId(), ""));
		}

	}
}