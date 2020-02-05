package edu.libsys.ui.frm.borrrow;

import com.liuvei.common.win.ATableModel;

import edu.libsys.bean.BorrowBean;
import edu.libsys.service.BorrowService;
import edu.libsys.service.impl.BorrowServiceImpl;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@SuppressWarnings("serial")
public class BorrowListFrm extends JFrame {
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	private JLabel lblTitle;// 管理界面的标题

	private JLabel lblBookName;// 图书名称搜索标签
	private JTextField txtBookName;// 图书名称搜索文本框

	private JLabel lblOperatorName;// 操作员名称搜索标签
	private JTextField txtOperatorName;// 操作员名称搜索文本框

	private JLabel lblReaderName;// 读者姓名搜索标签
	private JTextField txtReaderName;// 读者姓名搜索文本框

	private JButton btnSearch;// 查询按钮
	private JButton btnReset;// 重置按钮
	private JButton btnInsert;// 添加按钮

	/**
	 * 无参构造方法
	 */
	public BorrowListFrm() {
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
		int height = 650;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("图书类别管理");
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
		lblTitle.setText("借阅记录列表");
		lblTitle.setBounds(295, 5, 300, 80);
		lblTitle.setFont(new Font("宋体", Font.BOLD, 25));
		container.add(lblTitle);

		// 添加图书名称搜索的标签
		lblBookName = new JLabel();
		lblBookName.setText("图书名称：");
		lblBookName.setBounds(200, 400, 80, 30);
		container.add(lblBookName);

		// 添加图书名称搜索的文本框
		txtBookName = new JTextField();
		txtBookName.setBounds(270, 400, 180, 30);
		container.add(txtBookName);

		// 添加操作员名称搜索的标签
		lblOperatorName = new JLabel();
		lblOperatorName.setText("操作员名称：");
		lblOperatorName.setBounds(190, 460, 80, 30);
		container.add(lblOperatorName);

		// 添加操作员名称搜索的文本框
		txtOperatorName = new JTextField();
		txtOperatorName.setBounds(270, 460, 180, 30);
		container.add(txtOperatorName);

		// 添加读者名称搜索的标签
		lblReaderName = new JLabel();
		lblReaderName.setText("读者姓名：");
		lblReaderName.setBounds(190, 520, 80, 30);
		container.add(lblReaderName);

		// 添加读者名称搜索的文本框
		txtReaderName = new JTextField();
		txtReaderName.setBounds(270, 520, 180, 30);
		container.add(txtReaderName);

		// 添加搜索按钮
		btnSearch = new JButton();
		btnSearch.setText("查询");
		btnSearch.setBounds(540, 400, 70, 30);
		container.add(btnSearch);

		// 添加重置按钮
		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(540, 460, 70, 30);
		container.add(btnReset);

		// 添加添加按钮
		btnInsert = new JButton();
		btnInsert.setText("添加");
		btnInsert.setBounds(540, 520, 70, 30);
		container.add(btnInsert);

		// 【Swing表格步骤4】：initUI()中初始化表格的方法
		initTableUI();

		// 【右键菜单--步骤5 】initUI()中初始化表格对象的右键菜单
		createTblObjMenu();
	}

	/*
	 * 2.绑定事件
	 */
	public void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 查询按钮事件
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
				// 判定击中的是鼠标右键
				if (e.getButton() == MouseEvent.BUTTON3) {
					// 获取鼠标所在位置对应的表格的行号
					int rowIndex = tblObj.rowAtPoint(e.getPoint());
					// 如果行号为-1,则说明鼠标位置不在数据行
					if (rowIndex == -1) {
						return;
					}
					// 如果鼠标位置在数据行上,则选中该行
					tblObj.setRowSelectionInterval(rowIndex, rowIndex);

					// 调用相关方法,显示右键菜单
					tblObjMenu.show(tblObj, e.getX(), e.getY());
				}
			}
		});
	}

	// 根据文本框的内容搜索对应的数据
	public void btnSearch_click(ActionEvent e) {
		// TODO Auto-generated method stub
		showListData();// 搜索后，再显示新数据
	}

	// 重置查询功能
	public void btnReset_click(ActionEvent e) {
		// TODO Auto-generated method stub
		txtBookName.setText("");
		txtOperatorName.setText("");
		txtReaderName.setText("");
		showListData();// 清空后，再显示新数据
	}

	// 添加查询功能
	private void btnInsert_click(ActionEvent e) {
		// TODO Auto-generated method stub
		BorrowInsertFrm borrowInsertFrm = new BorrowInsertFrm();
		borrowInsertFrm.listFrm = this;

		borrowInsertFrm.setVisible(true);
		this.setVisible(false);
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
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
		pnlTablePane.setBounds(10, 100, 780, 260);
		// 整个窗体的容器中添加表格面板
		container.add(pnlTablePane);
		// 调用方法在表格对象中显示List集合的数据
		showListData();

	}

	/**
	 * 【Swing表格步骤5】：调用方法在表格对象中显示List集合的数据
	 */
	public void showListData() {
		// 5.1初始化服务对象（可以作为属性）
		BorrowService borrowService = new BorrowServiceImpl();
		// 5.2从DB中提取List集合数据
		List<BorrowBean> list = null;

		String bookName = txtBookName.getText();
		String operatorName = txtOperatorName.getText();
		String readerName = txtReaderName.getText();
		if ((bookName != null && !bookName.isEmpty()) || (operatorName != null && !operatorName.isEmpty())
				|| (readerName != null && !readerName.isEmpty())) {
			list = borrowService.listBySearch(bookName, operatorName, readerName);
		} else {
			list = borrowService.list();
		}

		// 5.4将List集合数据传给【所需表格模型】【新方案】
		// 5.4.1 声明表格模型
		ATableModel<BorrowBean> tableModel = null;

		// 5.4.2 实例化表格模型，传入List集合对象和表格对象要显示的数据
		tableModel = new ATableModel<BorrowBean>(list, 7) {

			/**
			 * 5.4.3 重写getTitle()--返回【界面表格】各列的表头的名称，传入列的下标，返回对应的下表列的名称
			 */
			@Override
			public String getTitle(int columnIndex) {
				// TODO Auto-generated method stub
				if (columnIndex == 0) {
					return "借阅编号";// 用户自定义
				} else if (columnIndex == 1) {
					return "图书名称";// 用户自定义
				} else if (columnIndex == 2) {
					return "操作员名称";// 用户自定义
				} else if (columnIndex == 3) {
					return "读者名称";// 用户自定义
				}else if (columnIndex == 4) {
					return "是否归还";// 用户自定义
				}else if (columnIndex == 5) {
					return "借书日期";// 用户自定义
				}else if (columnIndex == 6) {
					return "还书日期";// 用户自定义
				}
				return "无";
			}

			/**
			 * 5.4.4 重写getCellValue()--返回【界面表格】各列的值，传入实体和列号，从【实体类集合】获取对应的值并返回
			 */
			@Override
			public Object getPropValue(BorrowBean bean, int columnIndex) {
				// TODO Auto-generated method stub

				if (columnIndex == 0) {
					return bean.getId();// 具体显示哪个字段，则用户自定义
				} else if (columnIndex == 1) {
					return bean.getBookName();// 具体显示哪个字段，则用户自定义
				} else if (columnIndex == 2) {
					return bean.getOperatorName();// 具体显示哪个字段，则用户自定义
				} else if (columnIndex == 3) {
					return bean.getReaderName();// 具体显示哪个字段，则用户自定义
				}else if (columnIndex == 4) {
					return bean.getIsback() == 1L ? "是" : "否";// 具体显示哪个字段，则用户自定义
				}else if (columnIndex == 5) {
					return bean.getBorrowDate();// 具体显示哪个字段，则用户自定义
				}else if (columnIndex == 6) {
					return bean.getBackDate();// 具体显示哪个字段，则用户自定义
				}

				return null;// 不存在，则返回null
			}

		};

		// 5.5设置表格对象的数据类型
		// 相当于，将表格模型tableModel 封装的列表数据list，显示到表格对象tblObj

		tblObj.setModel(tableModel);

		// 显示表格数据小结：
		// 第五步A:【列表数据】list(5.1和5.2)== 封装到==>【表格模型】tableModel(5.3和5.4)
		// 第五步B:将【表格模型】tableModel（已经封装了列表数据list）,显示到表格对象tblObj（5.5）

	}

	/**
	 * 【右键菜单--步骤1】定义表格对象的右键菜单
	 */
	private JPopupMenu tblObjMenu = null;

	/**
	 * 【右键菜单--步骤2】创建表格对象的右键菜单,并绑定事件
	 */
	public void createTblObjMenu() {
		tblObjMenu = new JPopupMenu();
		// 1)菜单项【删除】，添加到右键菜单
		JMenuItem deleteMenuItem = new JMenuItem("删除");
		tblObjMenu.add(deleteMenuItem);
		// 菜单项【删除】添加点击事件
		deleteMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteMenuItem_click(e);
			}
		});
		// 2）菜单项【修改】，添加到右键菜单
		JMenuItem updateMenuItem = new JMenuItem("修改");
		tblObjMenu.add(updateMenuItem);
		// 菜单项【修改】添加点击事件
		updateMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateMenuItem_click(e);
			}
		});
		   // 菜单项还书
        JMenuItem returnMenuItem = new JMenuItem("还书");
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

	/**
	 * 【右键菜单--步骤3】菜单项【删除】的事件处理
	 */
	private void deleteMenuItem_click(ActionEvent e) {
		// 获取当前选中行的下标
		int index = tblObj.getSelectedRow();
		// 如果下标不为-1,则选中行为数据行
		if (index != -1) {
			// 取得表格对象的数据模型
			TableModel model = tblObj.getModel();
			// 在表格对象模型中.根据选中的行和列,获取对应的数据值
			Object obj = model.getValueAt(index, 0);
			Long pk = Long.valueOf("" + obj);
			// 创建确认删除的弹窗
			String title = "系统提示";
			String message = "请确认是否删除选中的数据";
			int option = JOptionPane.YES_NO_OPTION;// 对话框按钮类型
			// 保存用户点击的按钮类型的值
			int buttonValue = JOptionPane.showConfirmDialog(null, message, title, option);

			// 如果点击的按钮为YES,进行删除数据
			if (buttonValue == JOptionPane.YES_OPTION) {
				BorrowService borrowService = new BorrowServiceImpl();
				Long result = borrowService.delete(pk);
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "删除成功!");
					// btnReset_click(null);
					showListData();
				} else {
					JOptionPane.showMessageDialog(null, "删除失败!");
				}
			}
		}
	}
	

	/**
	 * 【右键菜单--步骤4】菜单项【修改】的事件处理
	 */
	private void updateMenuItem_click(ActionEvent e) {
		// 获取当前选中行的下标
		int index = tblObj.getSelectedRow();
		// 如果下标不为-1,则选中行为数据行
		if (index != -1) {
			// 取得表格对象的数据模型
			TableModel model = tblObj.getModel();
			// 在表格对象模型中.根据选中的行和列,获取对应的数据值
			Object obj = model.getValueAt(index, 0);
			Long pk = Long.valueOf("" + obj);
			// 如果主键合法,则在修改的窗体中显示要修改的数据
			if (pk > 0) {
				BorrowUpdateFrm borrowUpdateFrm = new BorrowUpdateFrm();
				// 将当前要修改的主键的值,传递到修改窗体
				borrowUpdateFrm.pk = pk;
				// 修改窗体,根据传入的主键值,加载数据到各个组件中
				borrowUpdateFrm.loadData();
				// 当前列表窗体的引用,传递给修改窗体,以便修改窗体关闭时,可以显示列表窗体
				borrowUpdateFrm.borrowListFrm= this;
				// 当前列表窗体隐藏
				this.setVisible(false);

				borrowUpdateFrm.setVisible(true);
			}
		}
	}
	private void returnMenuItem_click(ActionEvent e) {
        // TODO Auto-generated method stub
        // 获取当前选中行的下标
        int index = tblObj.getSelectedRow();
        if (index != -1) {
            // 取得表格对象的数据模型
            TableModel model = tblObj.getModel();
            // 根据选中的行和列，获取对应的数据值
            Object obj = model.getValueAt(index, 0);
            Long pk = Long.valueOf("" + obj);

            // 创建确定删除的弹窗
            String titel = "系统提示";
            String message = "是否归还该书";
            int option = JOptionPane.YES_NO_OPTION;

            // 保存用户点击的按钮类型的值
            int buttonValue = JOptionPane.showConfirmDialog(null, message, titel, option);

            // 如果点击的按钮为YES，进行删除数据
            if (buttonValue == JOptionPane.YES_OPTION) {
                BorrowService borrowService = new BorrowServiceImpl();
                BorrowBean bean = new BorrowBean();
                bean = borrowService.load(pk);
                if (bean.getIsback() == 1) {
                    JOptionPane.showMessageDialog(null, "该书已经归还，请勿重新归还");
                    return;
                }
                Long result = borrowService.returnBook(pk);
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "还书成功");
                    showListData();
                } else {
                    JOptionPane.showMessageDialog(null, "还书失败");
                }
            }
        }
}}
