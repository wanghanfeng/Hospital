package frame;

import action.ConsumablesAction;
import content.StatueContent;
import model.Consumables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReimbursementFrame {
	ConsumablesAction consumablesAction;
	private JFrame jFrame;
	//菜单栏组件
	private JPanel jPanel1;
	private JMenuBar jMenuBar;
	private JMenu[] jMenus = {new JMenu("外诊报销"),new JMenu("引进设备"),new JMenu("物资更换"),new JMenu("干部报销"), new JMenu("退出")};
	private JMenuItem j1 = new JMenuItem("信息录入");
	private JMenuItem j2 = new JMenuItem("信息查询");
	private JMenuItem j3 = new JMenuItem("信息录入");
	private JMenuItem j4 = new JMenuItem("信息查询");
	private JMenuItem j5 = new JMenuItem("信息录入");
	private JMenuItem j6 = new JMenuItem("信息查询");
	private JMenuItem j7 = new JMenuItem("信息录入");
	private JMenuItem j8 = new JMenuItem("信息查询");
	private JMenuItem j9 = new JMenuItem("注销登陆");


	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel addInfo;

	//表格组件
	private String[][] datas = {};
	public DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane5;

	//动态组件
	private JTextField show;
	private JScrollPane showPane;

	public ReimbursementFrame() {
	}

	public ReimbursementFrame(DefaultTableModel model) {
		this.model = model;
	}

	public void init() {

		// 设置页面基本属性
		jFrame = new JFrame(StatueContent.kind[2]);
		jFrame.setSize(StatueContent.main_width, StatueContent.main_height);
		jFrame.setLayout(new BorderLayout());
		jFrame.setLocationRelativeTo(null);
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new Login().init();
			}
		});
		jFrame.setResizable(false);

		// 初始化菜单栏
		jPanel1 = new JPanel();
		jFrame.add(jPanel1, BorderLayout.NORTH);
		layoutPanel1();

		// 初始化内容
		jPanel2 = new JPanel();
		jFrame.add(jPanel2, BorderLayout.CENTER);
		layoutPanel2();

		// 初始化动态组件
		addInfo = new JPanel();
		addInfo.setBounds(0, 0, StatueContent.main_width, 100);
		selectLayout();
		table.setEnabled(false);


		// 添加单击事件
		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddReimbursementFrame(new ReimbursementFrame(model));
			}
		});
		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addInfo.setVisible(true);
			}
		});
		j3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddImport();
			}
		});
		j4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		j5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddChange();
			}
		});
		j6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		j7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddLeader();
			}
		});
		j8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		j9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
				new Login().init();
			}
		});

		jFrame.setVisible(true);
	}

	// 病人信息录用模块
	private void selectLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("姓名：");
		JTextField name = new JTextField();
		JLabel queueLabel = new JLabel("队别：");
		JTextField queue = new JTextField();
		JButton addInfoSubmit = new JButton("查找");

		//添加控件
		addInfo.setLayout(null);
		nameLabel.setBounds(100, 20, 40, 25);
		name.setBounds(140, 20, 100, 25);
		queueLabel.setBounds(360, 20, 40, 25);
		queue.setBounds(400, 20, 200, 25);
		addInfoSubmit.setBounds(400, 60, 90, 25);
		addInfo.add(nameLabel);
		addInfo.add(name);
		addInfo.add(queueLabel);
		addInfo.add(queue);
		addInfo.add(addInfoSubmit);
		addInfo.setVisible(false);
		jPanel2.add(addInfo);

		addInfoSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = name.getText();
				String theUnit = queue.getText();

				consumablesAction = new ConsumablesAction();

				List<Consumables> consumablesList = consumablesAction.getConsumablesByInf(theName,theUnit);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}
				//查数据库，展示查到的财务信息
				for (Consumables consumables1 : consumablesList) {
					model.addRow(new String[]{
							consumables1.getThetime().toString(),
							consumables1.getThename(),
							consumables1.getUnit(),
							consumables1.getHos(),
							consumables1.getReason(),
							consumables1.getAuditor(),
							consumables1.getTotalPrice()+"",
							consumables1.getDays()+"",
							consumables1.getReducePrice()+"",
							consumables1.getDoctor()
					});
				}



			}
		});

	}

	private void layoutPanel1() {
		jPanel1.setLayout(new BorderLayout());
		jMenuBar = new JMenuBar();
		jMenus[0].add(j1);
		jMenus[0].add(j2);
		jMenus[1].add(j3);
		jMenus[1].add(j4);
		jMenus[2].add(j5);
		jMenus[2].add(j6);
		jMenus[3].add(j7);
		jMenus[3].add(j8);
		jMenus[4].add(j9);
		for(int i = 0; i < jMenus.length; i ++) {
			jMenuBar.add(jMenus[i]);
		}
		jPanel1.add(jMenuBar);
	}

	private void layoutPanel2() {
		jPanel2.setLayout(null);
		jPanel3 = new JPanel();
		jPanel3.setBounds(0, 0, StatueContent.main_width, 100);
		//设置展示框
		show = new JTextField("欢迎使用");
		show.setEnabled(false);
		show.setFont(new Font("黑体",Font.PLAIN,90));
		show.setHorizontalAlignment(JTextField.CENTER);
		jPanel3.setLayout(new BorderLayout());
		showPane = new JScrollPane();
		showPane.setViewportView(show);
		jPanel3.add(showPane , BorderLayout.CENTER);
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(0, 100, StatueContent.main_width, StatueContent.main_height - 100);
		jPanel2.add(jPanel3);
		jPanel2.add(tablePanel);

		// 初始化表格
		tablePanel.setLayout(new BorderLayout());
		model = new DefaultTableModel(datas, StatueContent.reimbursementColname);
		table = new JTable(model);
		scrollPane5 = new JScrollPane(table);
		table.getTableHeader().setResizingAllowed(false);
		tablePanel.add(scrollPane5, BorderLayout.CENTER);
		table.setRowHeight(35);

		table.setEnabled(false);
		consumablesAction = new ConsumablesAction();
		//清空
		while(model.getRowCount()>0){
			model.removeRow(model.getRowCount()-1);
		}

		//查数据库，展示所有财务信息
		List<Consumables> consumablesList = consumablesAction.getAllConsumables();
		for (Consumables consumables1 : consumablesList) {
			model.addRow(new String[]{
					consumables1.getThetime().toString(),
					consumables1.getThename(),
					consumables1.getUnit(),
					consumables1.getHos(),
					consumables1.getReason(),
					consumables1.getAuditor(),
					consumables1.getTotalPrice()+"",
					consumables1.getDays()+"",
					consumables1.getReducePrice()+"",
					consumables1.getDoctor()
			});
		}

	}

	public static void main(String[] args) {
		new ReimbursementFrame().init();
	}
}

class AddReimbursementFrame {
	ConsumablesAction consumablesAction;
	private JFrame jFrame = new JFrame("外诊报销录入");

	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);

	private JLabel nameLabel = new JLabel("姓名：");
	private JLabel reasonLabel = new JLabel("外诊原因：");
	private JLabel peopleLabel = new JLabel("审核人：");
	private JLabel sumLabel = new JLabel("总费用：");

	private JLabel usedayLabel = new JLabel("住院天数：");
	private JLabel ableLabel = new JLabel("可报销费用：");

	private JTextField useday = new JTextField();
	private JTextField able = new JTextField();

	private JLabel hospitalLabel = new JLabel("就诊医院：");
	private JComboBox hospital = new JComboBox<>();

	private JLabel idLabel = new JLabel("队别：");

	private JTextField year = new JTextField();
	private JTextField mouth = new JTextField();
	private JTextField day = new JTextField();
	private JTextField name = new JTextField();
	private JTextField people = new JTextField();
	private JTextField sum = new JTextField();
	private JTextField reason = new JTextField();

	private JLabel doctorLabel = new JLabel("审批医生：");
	private JTextField doctor = new JTextField();

	private JTextField id = new JTextField();

	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");
	public AddReimbursementFrame(ReimbursementFrame reimbursementFrame) {

		jFrame.setTitle("财务信息录入");
		jFrame.setSize(450, 400);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		//第一行

		nameLabel.setBounds(30, 60, 70, 25);
		name.setBounds(100, 60, 90, 25);
		idLabel.setBounds(220, 60, 80, 25);
		id.setBounds(300 , 60 , 80 , 25);

		hospitalLabel.setBounds(30, 100, 80, 25);
		hospital.setBounds(100 , 100 , 150 , 25);

		//就诊医院的下拉表
		for(int i = 0;i < 5; i++) {
			hospital.addItem(5);
		}


		reasonLabel.setBounds(30, 140, 80, 25);
		reason.setBounds(100,140,150,25);

		peopleLabel.setBounds(30, 180, 80, 25);
		people.setBounds(100,180,80,25);
		sumLabel.setBounds(190, 180, 100, 25);
		sum.setBounds(290 , 180 , 80 , 25);

		usedayLabel.setBounds(30, 220, 80, 25);
		useday.setBounds(100,220,80,25);
		ableLabel.setBounds(190, 220, 100, 25);
		able.setBounds(290 , 220 , 80 , 25);

		doctorLabel.setBounds(30, 260, 80, 25);
		doctor.setBounds(100,260,80,25);

		submit.setBounds(70 , 300 , 90 , 25);
		cancel.setBounds(170 , 300 , 90 , 25);

		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(reasonLabel);
		jFrame.add(peopleLabel);
		jFrame.add(year);
		jFrame.add(mouth);
		jFrame.add(day);
		jFrame.add(people);
		jFrame.add(reason);
		jFrame.add(submit);
		jFrame.add(cancel);
		jFrame.add(hospital);
		jFrame.add(sumLabel);
		jFrame.add(sum);
		jFrame.add(hospitalLabel);
		jFrame.add(idLabel);
		jFrame.add(able);
		jFrame.add(useday);
		jFrame.add(usedayLabel);
		jFrame.add(ableLabel);
		jFrame.add(id);
		jFrame.add(doctorLabel);
		jFrame.add(doctor);

		submit.addActionListener(new ActionListener() {
			//添加财务信息
			@Override
			public void actionPerformed(ActionEvent e) {
				consumablesAction = new ConsumablesAction();
				//添加处方
				int daysInt;
				double totalPriceDouble;
				double reducePriceDouble;
				//时间
				String theDate = nowTime;
				//姓名
				String theName = name.getText();
				//队别
				String theUnit = id.getText();
				//就诊医院
				String theHos = hospital.getSelectedItem().toString();
				//原因
				String theReason = reason.getText();
				//审核人
				String theAuditor = people.getText();
				//总费用 double
				String theTotalPrice = sum.getText();
				//报销费用 double
				String reducePrice = able.getText();
				//天数 int
				String theDays = useday.getText();
				//医生
				String theDoctor = doctor.getText();

				//验证空值
				if (theName.trim().isEmpty() &&
						theUnit.trim().isEmpty() && theHos.trim().isEmpty() &&
						theReason.trim().isEmpty() && theAuditor.trim().isEmpty()&&
						theTotalPrice.trim().isEmpty()&&reducePrice.trim().isEmpty()&&
						theDays.trim().isEmpty()&& theDoctor.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				//验证 年 月 日 天数 必须 为 整数
				try {
					daysInt = Integer.parseInt(theDays);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"日期相关必须是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				//验证 费用相关 必须是double类型
				try {
					totalPriceDouble = Double.parseDouble(theTotalPrice);
					reducePriceDouble = Double.parseDouble(reducePrice);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"费用必须是数字，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				Consumables consumables = new Consumables();
				consumables.setThetime(theDate);
				consumables.setThename(theName);
				consumables.setUnit(theUnit);
				consumables.setHos(theHos);
				consumables.setReason(theReason);
				consumables.setAuditor(theAuditor);
				consumables.setTotalPrice(totalPriceDouble);
				consumables.setDays(daysInt);
				consumables.setReducePrice(reducePriceDouble);
				consumables.setDoctor(theDoctor);

				int i = consumablesAction.addConsumables(consumables);

				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(reimbursementFrame.model.getRowCount()>0){
					reimbursementFrame.model.removeRow(reimbursementFrame.model.getRowCount()-1);
				}

				//查数据库，展示所有财务信息
				List<Consumables> consumablesList = consumablesAction.getAllConsumables();
				for (Consumables consumables1 : consumablesList) {
					reimbursementFrame.model.addRow(new String[]{
							consumables1.getThetime().toString(),
							consumables1.getThename(),
							consumables1.getUnit(),
							consumables1.getHos(),
							consumables1.getReason(),
							consumables1.getAuditor(),
							consumables1.getTotalPrice()+"",
							consumables1.getDays()+"",
							consumables1.getReducePrice()+"",
							consumables1.getDoctor()
					});
				}

				jFrame.dispose();
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setVisible(true);
	}
}

//引进设备信息录入
class AddImport {

	private JFrame jFrame = new JFrame();

	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);

	private JLabel nameLabel = new JLabel("设备名称：");
	private JTextField name = new JTextField();

	private JLabel producerLabel = new JLabel("生产商：");
	private JTextField producer = new JTextField();

	private JLabel typeLabel = new JLabel("类型：");
	private JTextField type = new JTextField();

	private JLabel valueLabel = new JLabel("市场价格：");
	private JTextField value = new JTextField();

	private JLabel purchasingLabel = new JLabel("进价：");
	private JTextField purchasing = new JTextField();

	private JLabel approveLabel = new JLabel("审批人：");
	private JTextField approve = new JTextField();

	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddImport() {
		jFrame.setTitle("引进设备录入");
		jFrame.setSize(450, 200);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		nameLabel.setBounds(30, 20, 80, 25);
		name.setBounds(110, 20, 90, 25);
		producerLabel.setBounds(220, 20, 80, 25);
		producer.setBounds(280, 20, 90, 25);

		typeLabel.setBounds(30, 60, 80, 25);
		type.setBounds(110, 60, 90, 25);
		valueLabel.setBounds(220, 60, 80, 25);
		value.setBounds(280, 60, 90, 25);

		purchasingLabel.setBounds(30, 100, 80, 25);
		purchasing.setBounds(110, 100, 90, 25);
		approveLabel.setBounds(220, 100, 80, 25);
		approve.setBounds(280, 100, 90, 25);

		submit.setBounds(100, 140, 90, 25);
		cancel.setBounds(200, 140, 90, 25);

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});


		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(producerLabel);
		jFrame.add(producer);
		jFrame.add(typeLabel);
		jFrame.add(type);
		jFrame.add(valueLabel);
		jFrame.add(value);
		jFrame.add(purchasingLabel);
		jFrame.add(purchasing);
		jFrame.add(approveLabel);
		jFrame.add(approve);
		jFrame.add(submit);
		jFrame.add(cancel);
		jFrame.setVisible(true);
	}

}

//物资更换信息录入
class AddChange{
	private JFrame jFrame = new JFrame();
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);

	private JLabel nameLabel = new JLabel("物资名称：");
	private JTextField name = new JTextField();

	private JLabel purchasingLabel = new JLabel("进价：");
	private JTextField purchasing = new JTextField();

	private JLabel approveLabel = new JLabel("审批人：");
	private JTextField approve = new JTextField();

	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddChange() {
		jFrame.setTitle("物资更换录入");
		jFrame.setSize(450, 180);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		nameLabel.setBounds(30, 20, 80, 25);
		name.setBounds(110, 20, 90, 25);

		purchasingLabel.setBounds(30, 60, 80, 25);
		purchasing.setBounds(110, 60, 90, 25);
		approveLabel.setBounds(220, 60, 80, 25);
		approve.setBounds(280, 60, 90, 25);

		submit.setBounds(100, 100, 90, 25);
		cancel.setBounds(200, 100, 90, 25);

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(purchasingLabel);
		jFrame.add(purchasing);
		jFrame.add(approveLabel);
		jFrame.add(approve);
		jFrame.add(submit);
		jFrame.add(cancel);

		jFrame.setVisible(true);
	}
}

//干部报销信息录入
class AddLeader {
	private JFrame jFrame = new JFrame();
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);

	private JLabel nameLabel = new JLabel("姓名：");
	private JTextField name = new JTextField();

	private JLabel sexLabel = new JLabel("性别：");
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JRadioButton man = new JRadioButton("男");
	private JRadioButton woman = new JRadioButton("女");

	private JLabel unitLabel = new JLabel("单位：");
	private JTextField unit = new JTextField();

	private JLabel percentageLabel = new JLabel("百分比");
	private JTextField percentage = new JTextField();

	private JLabel hospitalLabel = new JLabel("就诊医院");
	private JTextField hosptial = new JTextField();

	private JLabel approveLabel = new JLabel("审批人");
	private JTextField approve = new JTextField();

	private JLabel reasonLabel = new JLabel("报销原因");
	private JTextField reason = new JTextField();

	private JLabel militaryLabel = new JLabel("军衔：");
	private JTextField military = new JTextField();
	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddLeader() {
		jFrame.setTitle("引进设备录入");
		jFrame.setSize(450, 320);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		nameLabel.setBounds(30, 20, 80, 25);
		name.setBounds(110, 20, 90, 25);
		unitLabel.setBounds(220, 20, 80, 25);
		unit.setBounds(280, 20, 90, 25);

		sexLabel.setBounds(30, 60, 80, 25);
		man.setBounds(100, 60, 50, 25);
		woman.setBounds(150, 60, 50, 25);
		buttonGroup1.add(man);
		buttonGroup1.add(woman);

		militaryLabel.setBounds(30, 100, 80, 25);
		military.setBounds(110, 100, 90, 25);
		percentageLabel.setBounds(220, 100, 80, 25);
		percentage.setBounds(280, 100, 90, 25);

		hospitalLabel.setBounds(30, 140, 80, 25);
		hosptial.setBounds(110, 140, 90, 25);
		approveLabel.setBounds(220, 140, 80, 25);
		approve.setBounds(280, 140, 90, 25);

		reasonLabel.setBounds(30, 180, 80, 25);
		reason.setBounds(110, 180, 150, 25);

		submit.setBounds(100, 220, 90, 25);
		cancel.setBounds(200, 220, 90, 25);

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(militaryLabel);
		jFrame.add(military);
		jFrame.add(unitLabel);
		jFrame.add(unit);
		jFrame.add(woman);
		jFrame.add(man);
		jFrame.add(percentageLabel);
		jFrame.add(percentage);
		jFrame.add(approveLabel);
		jFrame.add(approve);
		jFrame.add(reasonLabel);
		jFrame.add(reason);
		jFrame.add(sexLabel);
		jFrame.add(hospitalLabel);
		jFrame.add(hosptial);
		jFrame.add(submit);
		jFrame.add(cancel);
		jFrame.setVisible(true);

	}
}