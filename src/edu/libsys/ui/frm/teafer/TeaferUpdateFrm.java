package edu.libsys.ui.frm.teafer;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.libsys.bean.TeaferBean;
import edu.libsys.service.TeaferService;
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

public class TeaferUpdateFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5227887501116455134L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public TeaferListFrm teaferListFrm = null;

	private JLabel lblISBN;// 读者编号的标签
	private JTextField txtISBN;// 读者编号的文本框

	private JLabel lblName;// 读者姓名的标签
	private JTextField txtName;// 读者姓名的文本框

	private JLabel lblSex;// 读者性别的标签
	// private JTextField txtSex;//读者性别的文本框
	private ButtonGroup btngrpSex;// 性别单选按钮
	private JRadioButton rdoSexMale;// 性别男的单选按钮
	private JRadioButton rdoSexFemale;// 性别女的单选按钮

	private JLabel lblAge;// 读者年龄的标签
	private JTextField txtAge;// 读者年龄的文本框

	private JLabel lblIdentityCard;// 证件号码的标签
	private JTextField txtIdentityCard;// 证件号码的文本框

	private JLabel lblDate;// 会员有效日期的标签
	private JTextField txtDate;// 会员有效日期的文本框

	private JLabel lblMaxNum;// 最大借书量的标签
	private JTextField txtMaxNum;// 最大借书量的文本框

	private JLabel lblTel;// 电话号码的标签
	private JTextField txtTel;// 电话号码的文本框

	private JLabel lblKeepMoney;// 押金的标签
	private JTextField txtKeepMoney;// 押金的文本框

	private JLabel lblZj; // 证件类型的标签
	private JComboBox<IdText> cboZj; // 证件类型的控件

	private JLabel lblZy;// 职业的标签
	private JTextField txtZy;// 职业的文本框

	private JLabel lblBztime;// 办证日期的标签
	private JTextField txtBztime;// 办证日期的文本框

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public TeaferUpdateFrm() {
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
		int width = 800;
		int height = 630;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("读者管理");
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
		if (this.teaferListFrm != null) {
			teaferListFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblISBN = new JLabel();
		lblISBN.setText("读者编号：");
		lblISBN.setBounds(50, 80, 80, 30);
		lblISBN.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblISBN);

		txtISBN = new JTextField();
		txtISBN.getText();
		txtISBN.setBounds(140, 80, 200, 30);
		txtISBN.setEnabled(false);
		container.add(txtISBN);

		lblName = new JLabel();
		lblName.setText("读者姓名：");
		lblName.setBounds(400, 80, 80, 30);
		lblName.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblName);

		txtName = new JTextField();
		txtName.getText();
		txtName.setBounds(500, 80, 200, 30);
		container.add(txtName);

		lblSex = new JLabel();
		lblSex.setText("读者性别：");
		lblSex.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		lblSex.setBounds(50, 80, 120, 150);
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

		lblAge = new JLabel();
		lblAge.setText("读者年龄：");
		lblAge.setBounds(400, 140, 80, 30);
		lblAge.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblAge);

		txtAge = new JTextField();
		txtAge.getText();
		txtAge.setBounds(500, 140, 200, 30);
		container.add(txtAge);

		lblIdentityCard = new JLabel();
		lblIdentityCard.setText("证件号码：");
		lblIdentityCard.setBounds(50, 200, 80, 30);
		lblIdentityCard.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblIdentityCard);

		txtIdentityCard = new JTextField();
		txtIdentityCard.getText();
		txtIdentityCard.setBounds(140, 200, 200, 30);
		container.add(txtIdentityCard);

		lblDate = new JLabel();
		lblDate.setText("会员有效日期：");
		lblDate.setBounds(380, 200, 120, 30);
		lblDate.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblDate);

		txtDate = new JTextField();
		txtDate.getText();
		txtDate.setBounds(500, 200, 200, 30);
		container.add(txtDate);

		lblMaxNum = new JLabel();
		lblMaxNum.setText("最大借书量：");
		lblMaxNum.setBounds(40, 260, 100, 30);
		lblMaxNum.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblMaxNum);

		txtMaxNum = new JTextField();
		txtMaxNum.getText();
		txtMaxNum.setBounds(140, 260, 200, 30);
		container.add(txtMaxNum);

		lblTel = new JLabel();
		lblTel.setText("电话号码：");
		lblTel.setBounds(400, 260, 80, 30);
		lblTel.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblTel);

		txtTel = new JTextField();
		txtTel.getText();
		txtTel.setBounds(500, 260, 200, 30);
		container.add(txtTel);

		lblKeepMoney = new JLabel();
		lblKeepMoney.setText("押金：");
		lblKeepMoney.setBounds(65, 320, 100, 30);
		lblKeepMoney.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblKeepMoney);

		txtKeepMoney = new JTextField();
		txtKeepMoney.getText();
		txtKeepMoney.setBounds(140, 320, 200, 30);
		container.add(txtKeepMoney);

		lblZj = new JLabel();
		lblZj.setText("证件类型：");
		lblZj.setBounds(400, 320, 80, 30);
		lblZj.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblZj);

		cboZj = new JComboBox<IdText>();
		cboZj.setBounds(500, 320, 200, 30);
		container.add(cboZj);

		lblZy = new JLabel();
		lblZy.setText("职业：");
		lblZy.setBounds(65, 380, 100, 30);
		lblZy.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblZy);

		txtZy = new JTextField();
		txtZy.getText();
		txtZy.setBounds(140, 380, 200, 30);
		container.add(txtZy);

		lblBztime = new JLabel();
		lblBztime.setText("办证日期：");
		lblBztime.setBounds(400, 380, 80, 30);
		lblBztime.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblBztime);

		txtBztime = new JTextField();
		txtBztime.getText();
		txtBztime.setBounds(500, 380, 200, 30);
		container.add(txtBztime);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(250, 480, 90, 40);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(400, 480, 90, 40);
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setBounds(50, 540, 600, 30);
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

	private void bindComboBoxData() {
		// 绑定下拉菜单数据的过程
		// 1)加载列表
		// 2)将转为list<Idtext>
		List<IdText> idTextList = new ArrayList<IdText>();
		idTextList.add(new IdText(0L, "借阅证"));
		idTextList.add(new IdText(1L, "身份证"));

		// 3)传递给IdTextModel
		IdTextModel model = new IdTextModel(idTextList);
		// 4)再显示到cbo控件
		cboZj.setModel(model);

	}

	TeaferService teaferService = new TeaferServiceImpl();

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		// 1）获取输入数据
		String name = txtName.getText().trim();
		String identityCard = txtIdentityCard.getText().trim();
		String tel = txtTel.getText().trim();
		String zy = txtZy.getText().trim();
		String itemAge = txtAge.getText().trim();
		String itemMaxNum = txtMaxNum.getText().trim();
		String itemKeepMoney = txtKeepMoney.getText().trim();
		String itemDate = txtDate.getText().trim();
		String itemBztime = txtBztime.getText().trim();

		IdText itemZj = (IdText) cboZj.getSelectedItem();
		Long zj = itemZj.getId();
		// 2)为空性判断

		if (SysFun.isNullOrEmpty(name)) {
			lblMsg.setText("提示：读者姓名不能为空！");
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
		if (SysFun.isNullOrEmpty(itemAge)) {
			lblMsg.setText("提示：读者年龄不能为空！");
			return;
		}
		if (SysFun.isNullOrEmpty(identityCard)) {
			lblMsg.setText("提示：证件号码不能为空！");
			return;
		}
		if (SysFun.isNullOrEmpty(itemDate)) {
			lblMsg.setText("提示：会员有效日期不能为空！");
			return;
		}
		DateFormat format = new SimpleDateFormat("YYYY-mm-dd HH:MM:SS");
		try {
			format.parse(itemDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			lblMsg.setText("提示：会员有效日期格式有误！");
			return;
		}
		if (SysFun.isNullOrEmpty(itemMaxNum)) {
			lblMsg.setText("提示：最大借书量不能为空！");
			return;
		}
		if (SysFun.isNullOrEmpty(tel)) {
			lblMsg.setText("提示：电话号码不能为空！");
			return;
		}
		if (SysFun.isNullOrEmpty(itemKeepMoney)) {
			lblMsg.setText("提示：押金不能为空！");
			return;
		}
		if (cboZj.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择证件类型");
			return;
		}
		if (SysFun.isNullOrEmpty(zy)) {
			lblMsg.setText("提示：职业不能为空！");
			return;
		}
		if (SysFun.isNullOrEmpty(itemBztime)) {
			lblMsg.setText("提示：办证日期不能为空！");
			return;
		}
		try {
			format.parse(itemBztime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			lblMsg.setText("提示：办证日期格式有误！");
			return;
		}
		Long age = Long.valueOf(itemAge);
		Long maxNum = Long.valueOf(itemAge);
		Double keepMoney = Double.valueOf(itemKeepMoney);
		Timestamp date = Timestamp.valueOf(itemDate);
		Timestamp bztime = Timestamp.valueOf(itemBztime);

		// 8)真正的修改
		TeaferBean bean = new TeaferBean();
		bean.setISBN(pk);
		bean.setName(name);
		bean.setSex(sex);
		bean.setAge(age);
		bean.setIdentityCard(identityCard);
		bean.setDate(date);
		bean.setMaxNum(maxNum);
		bean.setTel(tel);
		bean.setKeepMoney(keepMoney);
		bean.setZj(zj);
		bean.setZy(zy);
		bean.setBztime(bztime);

		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;
		try {
			result = teaferService.update(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (teaferListFrm != null) {
				teaferListFrm.btnReset_click(null);
				teaferListFrm.setVisible(true);
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
		this.setTitle("修改读者信息");
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
			this.setTitle(this.getTitle() + "-->正在修改ISBN为【" + pk + "】的读者信息");
		}
		TeaferBean bean = teaferService.load(pk);

		if (bean != null) {
			txtISBN.setText(bean.getISBN());
			txtName.setText(bean.getName());
			txtAge.setText(bean.getAge().toString());
			txtIdentityCard.setText(bean.getIdentityCard());
			txtDate.setText(bean.getDate().toString());
			txtMaxNum.setText(bean.getMaxNum().toString());
			txtTel.setText(bean.getTel());
			txtKeepMoney.setText(bean.getKeepMoney().toString());
			txtZy.setText(bean.getZy());
			txtBztime.setText(bean.getBztime().toString());

			cboZj.getModel().setSelectedItem(new IdText(bean.getZj(), ""));

			if ("男".equals(bean.getSex())) {
				rdoSexMale.setSelected(true);
			} else {
				rdoSexFemale.setSelected(true);
			}
		}

	}
}