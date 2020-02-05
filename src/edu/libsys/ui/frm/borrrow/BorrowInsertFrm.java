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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BorrowInsertFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5054412665800201507L;
	public BorrowListFrm listFrm = null;
	private JPanel container;
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

	public BorrowInsertFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	private void initUI() {
		int width = 400;
		int height = 600;
		this.setSize(width, height);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(x, y);

		this.setTitle("系统登录");// 标题
		this.setResizable(false);// 设置窗体不允许最大化

		container = new JPanel();
		container.setLayout(null);// 绝对布局
		this.add(container);
		customInitUI();
	}

	private void bindEvent() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}
		});

		customBindEvent();
	}

	private void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (listFrm != null) {
			listFrm.setVisible(true);// 显示列表窗体
		}
		this.dispose();// 关闭当前窗体
	}

	BookInfoService bookInfoService = new BookInfoServiceImpl();
	TeaferService teaferService = new TeaferServiceImpl();
	OperatorService operatorService = new OperatorServiceImpl();

	private void bindComboBoxData() {
		Long num = 1L;
		// 绑定下拉菜单数据的过程
		// 1)从数据库加载列表
		List<BookInfoBean> listA = bookInfoService.list();
		// 2)将转为list<Idtext>
		List<IdText> idTextListA = new ArrayList<IdText>();
		idTextListA.add(new IdText(0L, "请选择"));
		for (BookInfoBean item : listA) {
			idTextListA.add(new IdText(num, item.getBookName()));
			num++;
		}
		// 3)传递给IdTextModel
		IdTextModel modelA = new IdTextModel(idTextListA);
		// 4)再显示到cbo控件
		cboBookISBN.setModel(modelA);
		cboBookISBN.setSelectedIndex(0);// 默认选中的下标

		List<OperatorBean> listB = operatorService.list();
		List<IdText> idTextListB = new ArrayList<IdText>();
		idTextListB.add(new IdText(0L, "请选择"));
		for (OperatorBean item : listB) {
			idTextListB.add(new IdText(item.getId(), item.getName()));
		}
		IdTextModel modelB = new IdTextModel(idTextListB);
		cboOperatorId.setModel(modelB);
		cboOperatorId.setSelectedIndex(0);// 默认选中的下标

		List<TeaferBean> listC = teaferService.list();
		List<IdText> idTextListC = new ArrayList<IdText>();

		idTextListC.add(new IdText(0L, "请选择"));
		for (TeaferBean item : listC) {
			idTextListC.add(new IdText(num, item.getName()));
			num++;
		}
		IdTextModel modelC = new IdTextModel(idTextListC);
		cboReaderISBN.setModel(modelC);
		cboReaderISBN.setSelectedIndex(0);// 默认选中的下标

		List<IdText> idTextListD = new ArrayList<IdText>();
		idTextListD.add(new IdText(0L, "请选择"));
		idTextListD.add(new IdText(1L, "否"));
		idTextListD.add(new IdText(2L, "是"));
		IdTextModel modelD = new IdTextModel(idTextListD);
		cboIsBack.setModel(modelD);

		cboIsBack.setSelectedIndex(0);// 默认选中的下标
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

	// 提交按钮事件处理
	private void customBindEvent() {

		btnSubmit.addActionListener(e -> {
			btnSubmit_click(e);
		});

		// 重置按钮事件处理
		btnReset.addActionListener(e -> {
			btnReset_click();
		});
	}

	protected void btnReset_click() {
		// TODO Auto-generated method stub
		lblMsg.setText(cboReaderISBN.getSelectedItem().toString());
		bindComboBoxData();

	}

	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub

		// 1) 获取各控件的值
		String book = cboBookISBN.getSelectedItem().toString();
		IdText itemOperator = (IdText) cboOperatorId.getSelectedItem();
		String reader = cboReaderISBN.getSelectedItem().toString();
		IdText itemIsBack = (IdText) cboIsBack.getSelectedItem();

		String itemBorrowDate = txtBorrowDate.getText().trim();
		String itemBackDate = txtBackDate.getText().trim();
		// String reader=cboReaderISBN.getSelectedItem().toString();

		// 选择空值开始提示
		if (cboBookISBN.getSelectedIndex() == 0) {
			lblMsg.setText("提示：请选择图书！");
			return;
		}
		if (cboOperatorId.getSelectedIndex() == 0) {
			lblMsg.setText("提示：请选择操作员！");
			return;
		}
		if (cboReaderISBN.getSelectedIndex() == 0) {
			lblMsg.setText("提示：请选择读者！");
			return;
		}
		if (cboIsBack.getSelectedIndex() == 0) {
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

		// 8真正的添加
		BorrowBean bean = new BorrowBean();
		BookInfoService bookInfoService = new BookInfoServiceImpl();
		TeaferService teaferService = new TeaferServiceImpl();
		bean.setBookISBN(bookInfoService.loadByName(book).getISBN());
		bean.setOperatorId(operatorId);
		bean.setReaderISBN(teaferService.loadByName(reader).getISBN());
		bean.setIsback(isBack - 1);
		bean.setBorrowDate(borrowDate);
		bean.setBackDate(backDate);

		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;
		BorrowService borrowService = new BorrowServiceImpl();

		try {

			result = borrowService.insert(bean);

		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9处理结果
		if (result > 0) {
			JOptionPane.showMessageDialog(this, "操作成功！");
			// 成功时显示父窗体，之后，关闭当前窗体
			if (listFrm != null) {
				listFrm.btnReset_click(null);
				listFrm.setVisible(true);
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "操作失败！");
		}

	}

	/**
	 * 自定义加载
	 */
	public void customLoad() {
		this.setTitle("添加借阅记录");
		bindComboBoxData();

	}
}
