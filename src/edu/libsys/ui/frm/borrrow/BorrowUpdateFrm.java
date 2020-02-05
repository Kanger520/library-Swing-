package edu.libsys.ui.frm.borrrow;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.libsys.bean.BookInfoBean;
import edu.libsys.bean.BorrowBean;
import edu.libsys.bean.OperatorBean;
import edu.libsys.bean.TeaferBean;
import edu.libsys.service.BookInfoService;
import edu.libsys.service.BorrowService;
import edu.libsys.service.OperatorService;
import edu.libsys.service.TeaferService;
import edu.libsys.service.impl.BookInfoServiceImpl;
import edu.libsys.service.impl.BorrowServiceImpl;
import edu.libsys.service.impl.OperatorServiceImpl;
import edu.libsys.service.impl.TeaferServiceImpl;

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

public class BorrowUpdateFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1439178593925529769L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public BorrowListFrm borrowListFrm = null;
	
	private JLabel lblBookISBN; // 书籍编号的标签
	private JComboBox<IdText> cboBookISBN; // 书籍编号的控件

	private JLabel lblOperatorId; // 操作员编号的标签
	private JComboBox<IdText> cboOperatorId; // 操作员编号的控件

	private JLabel lblReaderISBN; // 读者编号的标签
	private JComboBox<IdText> cboReaderISBN; // 读者编号的控件

	private JLabel lblIsBack; // 是否归还的标签
	private JComboBox<IdText> cboIsBack; // 是否归还的控件

	private JLabel lblBorrowDate;// 借书日期的标签
	private JTextField txtBorrowDate;// 借书日期的文本框

	private JLabel lblBackDate;// 还书日期的标签
	private JTextField txtBackDate;// 还书日期的文本框

	private JButton btnSubmit; // 提交按钮
	private JButton btnReset; // 重置按钮
	private JLabel lblMsg; // 提示信息标签
	/**
	 * 无参构造方法
	 */
	public BorrowUpdateFrm() {
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
		int height = 600;
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
		if (this.borrowListFrm != null) {
			borrowListFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblBookISBN = new JLabel();
		lblBookISBN.setText("图书名称：");
		lblBookISBN.setBounds(60, 60, 80, 30);
		lblBookISBN.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblBookISBN);

		cboBookISBN = new JComboBox<IdText>();
		cboBookISBN.setBounds(150, 60, 160, 30);
		container.add(cboBookISBN);

		lblOperatorId = new JLabel();
		lblOperatorId.setText("操作员名称：");
		lblOperatorId.setBounds(50, 130, 120, 30);
		lblOperatorId.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblOperatorId);

		cboOperatorId = new JComboBox<IdText>();
		cboOperatorId.setBounds(150, 130, 160, 30);
		container.add(cboOperatorId);

		lblReaderISBN = new JLabel();
		lblReaderISBN.setText("读者名称：");
		lblReaderISBN.setBounds(60, 200, 80, 30);
		lblReaderISBN.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblReaderISBN);

		cboReaderISBN = new JComboBox<IdText>();
		cboReaderISBN.setBounds(150, 200, 160, 30);
		container.add(cboReaderISBN);

		lblIsBack = new JLabel();
		lblIsBack.setText("是否归还：");
		lblIsBack.setBounds(50, 270, 80, 30);
		lblIsBack.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblIsBack);

		cboIsBack = new JComboBox<IdText>();
		cboIsBack.setBounds(150, 270, 160, 30);
		container.add(cboIsBack);

		lblBorrowDate = new JLabel();
		lblBorrowDate.setText("借书日期：");
		lblBorrowDate.setBounds(50, 340, 80, 30);
		lblBorrowDate.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblBorrowDate);

		txtBorrowDate = new JTextField();
		txtBorrowDate.getText();
		txtBorrowDate.setBounds(150, 340, 160, 30);
		container.add(txtBorrowDate);

		lblBackDate = new JLabel();
		lblBackDate.setText("应还日期：");
		lblBackDate.setBounds(50, 410, 80, 30);
		lblBackDate.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblBackDate);

		txtBackDate = new JTextField();
		txtBackDate.getText();
		txtBackDate.setBounds(150, 410, 160, 30);
		container.add(txtBackDate);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(100, 490, 70, 30);
		btnSubmit.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(210, 490, 70, 30);
		btnReset.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setBounds(50, 525, 300, 30);
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

	BorrowService borrowService = new BorrowServiceImpl();
	BookInfoService bookInfoService =new BookInfoServiceImpl();
	TeaferService teaferService=new TeaferServiceImpl();
	OperatorService operatorService =new OperatorServiceImpl();

	// 提交功能
	@SuppressWarnings("unused")
	private void btnSubmit_click(ActionEvent e) {
		// 1）获取输入数据
		// trim忽略前后空格
		String book = cboBookISBN.getSelectedItem().toString();
		IdText itemOperator = (IdText) cboOperatorId.getSelectedItem();
		String reader = cboReaderISBN.getSelectedItem().toString();
		IdText itemIsBack = (IdText) cboIsBack.getSelectedItem();

		String itemBorrowDate = txtBorrowDate.getText().trim();
		String itemBackDate = txtBackDate.getText().trim();

		
		// 选择空值开始提示
		if (cboBookISBN.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择图书！");
			return;
		}
		if (cboOperatorId.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择操作员！");
			return;
		}
		if (cboReaderISBN.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择读者！");
			return;
		}
		if (cboIsBack.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择是否归还！");
			return;
		}
		if (SysFun.isNullOrEmpty(itemBorrowDate)) {
			lblMsg.setText("提示：借书日期不能为空!");
			return;
		}
		if (SysFun.isNullOrEmpty(itemBackDate)) {
			lblMsg.setText("提示：应还日期不能为空!");
			return;
		}
		DateFormat format = new SimpleDateFormat("YYYY-mm-dd HH:MM:SS");
		try {
			format.parse(itemBorrowDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			lblMsg.setText("提示：借书日期格式有误！");
			return;
		}
		try {
			format.parse(itemBackDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			lblMsg.setText("提示：应还日期格式有误！");
			return;
		}
		Long operatorId = itemOperator.getId();// 获取操作者编号
		Long isBack = itemIsBack.getId();
		
		Timestamp borrowDate = Timestamp.valueOf(itemBorrowDate);
		Timestamp backDate = Timestamp.valueOf(itemBackDate);

		// 修改时,根据名称取得对象
		// a)对象的主键如果与当前修改的主键一致,说明取得的是当前修改对象,允许修改
		// b)对象的主键如果与当前修改的主键不一致,说明存在另一个对象的名称跟当前修改内容一致,说明名称重复
		// 5)真正的修改
		BorrowBean bean = new BorrowBean();
		
		bean.setId(pk);
		bean.setBookISBN(bookInfoService.loadByName(book).getISBN());
		bean.setOperatorId(operatorId);
		bean.setReaderISBN(teaferService.loadByName(reader).getISBN());
		bean.setIsback(isBack);
		bean.setBorrowDate(borrowDate);
		bean.setBackDate(backDate);

		// 6)处理结果
		Long result = 0L;
		String errMsg = null;

		try {
			result = borrowService.update(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果修改结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (borrowListFrm != null) {
				borrowListFrm.btnReset_click(null);
				borrowListFrm.setVisible(true);
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
		this.setTitle("修改图书借阅信息");
		bindComboBoxData();
	}

	private void bindComboBoxData() {
		// TODO Auto-generated method stub
		Long num=1L;
		// 绑定下拉菜单数据的过程
		// 1)从数据库加载列表
		List<BookInfoBean> listA = bookInfoService.list();
		// 2)将转为list<Idtext>
		List<IdText> idTextListA = new ArrayList<IdText>();	
		for (BookInfoBean item : listA) {
			idTextListA.add(new IdText(num, item.getBookName()));
			num++;
		}
		// 3)传递给IdTextModel
		IdTextModel modelA = new IdTextModel(idTextListA);
		// 4)再显示到cbo控件
		cboBookISBN.setModel(modelA);
	

		List<OperatorBean> listB = operatorService.list();
		List<IdText> idTextListB = new ArrayList<IdText>();
		for (OperatorBean item : listB) {
			idTextListB.add(new IdText(item.getId(), item.getName()));
		}
		IdTextModel modelB = new IdTextModel(idTextListB);
		cboOperatorId.setModel(modelB);
		
		Long ite=1L;
		List<TeaferBean> listC = teaferService.list();
		List<IdText> idTextListC = new ArrayList<IdText>();	
		for (TeaferBean item : listC) {
			idTextListC.add(new IdText(ite, item.getName()));
			ite++;
		}
		IdTextModel modelC = new IdTextModel(idTextListC);
		cboReaderISBN.setModel(modelC);
		


		List<IdText> idTextListD = new ArrayList<IdText>();
		idTextListD.add(new IdText(0L, "否"));
		idTextListD.add(new IdText(1L, "是"));
		IdTextModel modelD = new IdTextModel(idTextListD);
		cboIsBack.setModel(modelD);


	}

	/**
	 * 【修改窗体之加载数据--步骤1】声明当前修改的数据的主键值。从列表数据中传递过来
	 */
	Long pk = null;

	/**
	 * 【修改窗体之加载数据--步骤2】根据pk加载要修改的主键对应的数据，并显示窗体的标题
	 */
	public void loadData() {
		if (!this.getTitle().contains("主键")) {
			this.setTitle(this.getTitle() + "-->正在修改id为【" + pk + "】的借阅记录信息");
		} 
		BorrowBean bean = borrowService.load(pk);
		// 读取选中行的图书名称,并直接显示出来
		Long num = 0L;
		List<BookInfoBean> listA = bookInfoService.list();
		List<IdText> idTextListA = new ArrayList<IdText>();
		for (BookInfoBean item : listA) {
			idTextListA.add(new IdText(num, item.getBookName()));
			num++;
			if ((bean.getBookISBN()).equals(bookInfoService.loadByName(item.getBookName()).getISBN())) {
				cboBookISBN.getModel().setSelectedItem(new IdText(num, ""));
				break;
			}
		}
		// 读取选中行的操作员名称,并直接显示出来
		cboOperatorId.getModel().setSelectedItem(new IdText(bean.getOperatorId(), ""));
		
		// 读取选中行的读者名称,并直接显示出来
		Long ite=1L;
		List<TeaferBean> listC= teaferService.list();
		List<IdText> idTextListC = new ArrayList<IdText>();
		for (TeaferBean item : listC) {
			idTextListC.add(new IdText(ite, item.getName()));
			if ((bean.getReaderISBN().equals(teaferService.loadByName(item.getName()).getISBN()))) {
				cboReaderISBN.getModel().setSelectedItem(new IdText(ite, teaferService.loadByName(item.getName()).getISBN()));
				break;
			}
			ite++;
		}
		// 读取选中行中的是否归还,并直接显示出来
		if (bean.getIsback() == 1L) {
			cboIsBack.getModel().setSelectedItem(new IdText(1L, ""));
		} else {
			cboIsBack.getModel().setSelectedItem(new IdText(0L, ""));
		}
		// 读取选中行的借书日期和应还日期,并直接显示出来
		txtBorrowDate.setText(bean.getBorrowDate().toString());
		txtBackDate.setText(bean.getBackDate().toString());

	
	}
}