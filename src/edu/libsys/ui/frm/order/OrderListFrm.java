package edu.libsys.ui.frm.order;

import com.liuvei.common.win.ATableModel;

import edu.libsys.bean.OrderBean;
import edu.libsys.service.OrderService;
import edu.libsys.service.impl.OrderServiceImpl;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class OrderListFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 无参构造方法
	 */
	public OrderListFrm() {
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();
	}

	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	private JLabel lblTitle;// 管理界面的标题
	private JLabel lblBookISBN;// 书籍编号搜索标签
	private JTextField txtBookISBN;// 书籍编号搜索文本框
	private JLabel lblOperator;// 操作员搜索标签
	private JTextField txtOperator;// 操作员搜索文本框

	private JButton btnSearch;// 查询按钮

	private JButton btnReset;// 重置按钮
	private JButton btnInsert;// 添加按钮

	/*
	 * 1.初始化用户界面
	 */

	private void initUI() {
		// 定义当前窗体的宽高
		int width = 800;
		int height = 620;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("新书订购管理");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);
		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		container.setLayout(null);
		// 将容器对象添加到当前窗体
		this.add(container);
		/* 添加管理界面标题 */
		lblTitle = new JLabel();
		lblTitle.setText("新书订购列表");
		lblTitle.setBounds(285, 5, 300, 80);
		lblTitle.setFont(new Font("宋体", Font.BOLD, 35));
		container.add(lblTitle);

		// 添加书籍编号搜索的标签
		lblBookISBN = new JLabel();
		lblBookISBN.setText("书籍编号：");
		lblBookISBN.setFont(new Font("宋体", Font.BOLD, 14));
		lblBookISBN.setBounds(260, 430, 80, 30);
		container.add(lblBookISBN);

		// 添加书籍编号搜索的文本框
		txtBookISBN = new JTextField();
		txtBookISBN.setBounds(350, 430, 180, 30);
		container.add(txtBookISBN);

		// 添加操作员搜索的标签
		lblOperator = new JLabel();
		lblOperator.setText("操作员名称：");
		lblOperator.setFont(new Font("宋体", Font.BOLD, 14));
		lblOperator.setBounds(250, 480, 100, 30);
		container.add(lblOperator);

		// 添加操作员搜索的文本框
		txtOperator = new JTextField();
		txtOperator.setBounds(350, 480, 180, 30);
		container.add(txtOperator);

		// 添加搜索按钮
		btnSearch = new JButton();
		btnSearch.setText("查询");
		btnSearch.setBounds(270, 530, 70, 30);
		container.add(btnSearch);

		// 添加重置按钮
		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(360, 530, 70, 30);
		container.add(btnReset);

		// 添加添加按钮
		btnInsert = new JButton();
		btnInsert.setText("添加");
		btnInsert.setBounds(450, 530, 70, 30);
		container.add(btnInsert);

		// 【Swing表格步骤4】：initUI()中初始化表格的方法
		initTableUI();

		// 【右键菜单--步骤5 】initUI()中初始化表格对象的右键菜单
		createTblObjMenu();
	}

	private void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 查询按钮事件处理
		btnSearch.addActionListener(e -> {
			btnSearch_click(e);
		});

		// 重置按钮事件处理
		btnReset.addActionListener(e -> {
			btnReset_click(e);

		});

		// 添加按钮事件处理
		btnInsert.addActionListener(e -> {
			btnInsert_click(e);
		});
		// 【右键菜单--步骤6】bindEvent(),在表格对象tblObj的鼠标事件中,右击时,显示右键菜单
		tblObj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 判定击中的是鼠标的右键
				if (e.getButton() == MouseEvent.BUTTON3) {
					// 获取鼠标所在的位置对应的表格的行号
					int focusedRowIndex = tblObj.rowAtPoint(e.getPoint());
					// 如果行号为-1，则说明鼠标位置不在数据行
					if (focusedRowIndex == -1) {
						return;
					}
					// 如果鼠标位置在数据行上。则选中该行
					tblObj.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
					// 调用相关方法，显示右键菜单
					tblObjMenu.show(tblObj, e.getX(), e.getY());
				}
			}

		});
	}

	// 添加查询功能
	private void btnInsert_click(ActionEvent e) {
		// TODO Auto-generated method stub
		OrderInsertFrm orderInsertFrm = new OrderInsertFrm();
		orderInsertFrm.listFrm = this;
		orderInsertFrm.setVisible(true);
		this.setVisible(false);

	}

	// 重置查询功能
	public void btnReset_click(ActionEvent e) {
		// TODO Auto-generated method stub
		txtBookISBN.setText("");
		txtOperator.setText("");
		showListData();

	}

	// 根据文本框的内容搜索对应的数据
	public void btnSearch_click(ActionEvent e) {
		// TODO Auto-generated method stub
		showListData();
	}

	// 定义表格对象的右键菜单
	private JPopupMenu tblObjMenu = null;

	// 创建表格对象的右键菜单，并绑定事件
	public void createTblObjMenu() {
		tblObjMenu = new JPopupMenu();
		// 菜单项删除
		JMenuItem deleteMenuItem = new JMenuItem("删除");
		tblObjMenu.add(deleteMenuItem);

		// 菜单项删除添加点击事件
		deleteMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteMenuItem_click(e);
			}

		});

		// 菜单项修改
		JMenuItem updateMenuItem = new JMenuItem("修改");
		tblObjMenu.add(updateMenuItem);

		// 菜单项修改添加点击事件
		updateMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateMenuItem_click(e);
			}

		});
		// 菜单项验收
		JMenuItem returnMenuItem = new JMenuItem("验收");
		tblObjMenu.add(returnMenuItem);

		// 菜单项还书添加点击事件
		returnMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				returnMenuItem_click(e);
			}
		});

	}

	// 删除的事件处理
	private void deleteMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		// 获取当前选中行的下标
		int index = tblObj.getSelectedRow();
		// 如果下标不为-1，则选中行为数据行
		if (index != -1) {
			// 取得表格对象的数据模型
			TableModel model = tblObj.getModel();
			// 根据选中的行和列，获取对应的数据值
			Object obj = model.getValueAt(index, 0);
			String pk = "" + obj;

			// 创建确定删除的弹窗
			String titel = "系统提示";
			String message = "请确定是否删除选中的数据";
			int option = JOptionPane.YES_NO_OPTION;

			// 保存用户点击的按钮类型的值
			int buttonValue = JOptionPane.showConfirmDialog(null, message, titel, option);

			// 如果点击的按钮为YES，进行删除数据
			if (buttonValue == JOptionPane.YES_OPTION) {
				OrderService orderService = new OrderServiceImpl();
				Long result = orderService.delete(pk);
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "删除成功");
					showListData();
				} else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}
		}

	}

	// 修改的事件处理
	private void updateMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		// 获取当前选中行的下标
		int index = tblObj.getSelectedRow();
		// 如果下标不为-1,则选中行为数据行
		if (index != -1) {
			TableModel model = tblObj.getModel();
			// 取得表格对象的数据模型
			Object obj = model.getValueAt(index, 0);
			String pk = "" + obj;
			// 如果主键合法,则在修改的窗体中显示要修改的数据
		
				OrderUpdateFrm orderUpdateFrm = new OrderUpdateFrm();
				// 将当前要修改的主键的值,传递到修改窗体
				orderUpdateFrm.pk = pk;
				// 修改窗体,根据传入的主键值,加载数据到各个组件中
				orderUpdateFrm.loadData();
				// 当前列表窗体的引用,传递给修改窗体,以便修改窗体关闭时,可以显示列表窗体
				orderUpdateFrm.orderListFrm= this;
				// 当前列表窗体隐藏
				this.setVisible(false);

				orderUpdateFrm.setVisible(true);

			
		}

	}

	// 验收的事件处理
	private void returnMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		// 获取当前选中行的下标
		int index = tblObj.getSelectedRow();
		if (index != -1) {
			// 取得表格对象的数据模型
			TableModel model = tblObj.getModel();
			// 根据选中的行和列，获取对应的数据值
			Object obj = model.getValueAt(index, 0);
			String pk = "" + obj;

			// 创建确定删除的弹窗
			String titel = "系统提示";
			String message = "是否验收此书？";
			int option = JOptionPane.YES_NO_OPTION;

			// 保存用户点击的按钮类型的值
			int buttonValue = JOptionPane.showConfirmDialog(null, message, titel, option);

			// 如果点击的按钮为YES，进行删除数据
			if (buttonValue == JOptionPane.YES_OPTION) {
				OrderService orderService = new OrderServiceImpl();
				OrderBean bean = new OrderBean();
				bean = orderService.load(pk);
				if (bean.getCheakAndAccept() == 1) {
					JOptionPane.showMessageDialog(null, "该书已经验收，请勿重新验收！");
					return;
				}
				Long result = orderService.acceptBook(pk);
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "验收成功.！");
					showListData();
				} else {
					JOptionPane.showMessageDialog(null, "验收失败！");
				}
			}
		}

	}

	/**
	 * 自定义加载：在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {

	}

	/**
	 * 【Swing表格步骤1】： pnlTablePane表格面板，用来放真正的表格对象
	 */

	private JScrollPane pnlTablePane;

	/**
	 * 【Swing表格步骤2】：tblObj是表格对象
	 */
	private JTable tblObj;

	/**
	 * 【Swing表格步骤3】：初始化表格相关对象，要调用获取表格数据的方法showListDate().
	 * <p>
	 * * 【Swing表格步骤4】：initUI()中初始化表格的方法
	 */
	private void initTableUI() {
		// 初始化表格对象
		tblObj = new JTable();
		// 初始化表格面板，并放入表格对象
		pnlTablePane = new JScrollPane(tblObj);
		// 设置表格面板的位置
		pnlTablePane.setBounds(10, 100, 780, 280);
		// 整个窗体的容器中添加表格面板
		container.add(pnlTablePane);
		// 调用方法在表格对象中显示List集合的数据
		showListData();
	}

	/**
	 * 【Swing表格步骤5】：调用方法在表格对象中显示List集合的数据
	 */
	private void showListData() {
		// 5.1初始化服务对象（可以作为属性）
		OrderService orderService = new OrderServiceImpl();
		// 5.2从DB中提取List集合数据
		List<OrderBean> list = null;

		String ISBN = txtBookISBN.getText();
		String operator = txtOperator.getText();
		if ((ISBN != null && !ISBN.isEmpty()) || (operator != null && !operator.isEmpty())) {
			list = orderService.listBySearch(ISBN, operator);
		} else {
			list = orderService.list();
		}
		// 5.4将List集合数据传给【所需表格模型】【新方案】
		// 5.4.1 声明表格模型
		ATableModel<OrderBean> tableModel = null;
		// 5.4.2 实例化表格模型，传入List集合对象和表格对象要显示的数据
		tableModel = new ATableModel<OrderBean>(list, 6) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 24903144265467267L;

			/**
			 * 5.4.3 重写getTitle()--返回【界面表格】各列的表头的名称，传入列的下标，返回对应的下表列的名称
			 */
			@Override
			public String getTitle(int columnIndex) {
				// TODO Auto-generated method stub
				if (columnIndex == 0) {
					return "书籍编号";
				} else if (columnIndex == 1) {
					return "订购日期";
				} else if (columnIndex == 2) {
					return "订购数量";
				} else if (columnIndex == 3) {
					return "操作员";
				} else if (columnIndex == 4) {
					return "是否验收";
				} else if (columnIndex == 5) {
					return "书籍折扣";
				}
				return "无";
			}

			/**
			 * 5.4.4 重写getCellValue()--返回【界面表格】各列的值，传入实体和列号，从【实体类集合】获取对应的值并返回
			 */
			@Override
			public Object getPropValue(OrderBean bean, int columnIndex) {
				// TODO Auto-generated method stub
				// 具体显示那个字段用户自定义,如果不存在返回null
				if (columnIndex == 0) {
					return bean.getISBN();
				} else if (columnIndex == 1) {
					return bean.getDate();
				} else if (columnIndex == 2) {
					return bean.getNumber();
				} else if (columnIndex == 3) {
					return bean.getOperator();
				} else if (columnIndex == 4) {
					return bean.getCheakAndAccept() == 1 ? "是" : "否";
				} else if (columnIndex == 5) {
					return bean.getZk();
				}
				return null;
			}

		};
		// 5.5设置表格对象的数据类型
		// 相当于，将表格模型tableModel 封装的列表数据list，显示到表格对象tblObj
		tblObj.setModel(tableModel);
	}
}
