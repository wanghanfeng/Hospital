package frame;

import action.*;
import content.StatueContent;
import model.*;

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
	ImportDeviceAction importDeviceAction;
	ChangeDeviceAction changeDeviceAction;
	LeaderPayAction leaderPayAction;
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
	private JPanel changeDevice;
	private JPanel importDevice;
	private JPanel leaderPayPanel;
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

		changeDevice = new JPanel();
		changeDevice.setBounds(0, 0, StatueContent.main_width, 100);
		changeDeviceLayout();

		importDevice = new JPanel();
		importDevice.setBounds(0, 0, StatueContent.main_width, 100);
		importDeviceLayout();


		leaderPayPanel = new JPanel();
		leaderPayPanel.setBounds(0, 0, StatueContent.main_width, 100);
		leaderPayPanelLayout();

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
				importDevice.setVisible(false);
				changeDevice.setVisible(false);
			}
		});
		j3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddImport(new ReimbursementFrame(model));
			}
		});
		j4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addInfo.setVisible(false);
				importDevice.setVisible(true);
				changeDevice.setVisible(false);
				leaderPayPanel.setVisible(false);
			}
		});
		j5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddChange(new ReimbursementFrame(model));
			}
		});
		j6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addInfo.setVisible(false);
				importDevice.setVisible(false);
				changeDevice.setVisible(true);
				leaderPayPanel.setVisible(false);
			}
		});
		j7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddLeader(new ReimbursementFrame(model));
			}
		});
		j8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addInfo.setVisible(false);
				importDevice.setVisible(false);
				changeDevice.setVisible(false);
				leaderPayPanel.setVisible(true);
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

	private void leaderPayPanelLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("姓名：");
		JTextField name = new JTextField();
		JLabel sexLabel = new JLabel("性别：");
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton man = new JRadioButton("男");
		JRadioButton woman = new JRadioButton("女");
		buttonGroup.add(man);
		buttonGroup.add(woman);
		man.setSelected(true);
		woman.setSelected(false);
		JLabel unitLabel = new JLabel("单位：");
		JTextField unit = new JTextField();
		JButton addPatientSubmit = new JButton("查找");

		//添加控件
		leaderPayPanel.setLayout(null);
		nameLabel.setBounds(100, 20, 40, 25);
		name.setBounds(140, 20, 100, 25);
		sexLabel.setBounds(100, 60, 40, 25);
		man.setBounds(140, 60, 50, 25);
		woman.setBounds(190, 60, 50, 25);
		unitLabel.setBounds(360, 20, 40, 25);
		unit.setBounds(400, 20, 200, 25);
		addPatientSubmit.setBounds(400, 60, 90, 25);
		leaderPayPanel.add(nameLabel);
		leaderPayPanel.add(name);
		leaderPayPanel.add(sexLabel);
		leaderPayPanel.add(man);
		leaderPayPanel.add(woman);
		leaderPayPanel.add(unitLabel);
		leaderPayPanel.add(unit);
		leaderPayPanel.add(addPatientSubmit);
		leaderPayPanel.setVisible(false);
		jPanel2.add(leaderPayPanel);

		addPatientSubmit.addActionListener(new ActionListener() {
			/**
			 * 查找病人
			 *目的：1.有姓名 按照 姓名+性别 查找
			 *     2.有姓名和单位 按照 姓名+性别+单位 查找
			 *     3.有单位 按照 单位+性别 查找
			 * @param e
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				//实现查找病人 先获取填空信息
				String theName = name.getText();
				String theSex = "";
				if (man.isSelected()){
					theSex = man.getText();
				}else if (woman.isSelected()){
					theSex = woman.getText();
				}
				String theUnit = unit.getText();
				leaderPayAction = new LeaderPayAction();

				List<LeaderPay> list = leaderPayAction.getLeaderPayByInf(theName,theSex,theUnit);

				model.setColumnIdentifiers(StatueContent.leaderPay);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				//展示选择的病人
				for (LeaderPay l:list) {
					model.addRow(new String[]{
							l.getTime(),
							l.getName(),
							l.getSex(),
							l.getUnit(),
							l.getMilitary(),
							l.getPercentage(),
							l.getHospital(),
							l.getApprove(),
							l.getReason()
					});
				}
			}
		});
	}

	private void importDeviceLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("姓名：");
		JTextField name = new JTextField();
		JButton addInfoSubmit = new JButton("查找");

		//添加控件
		importDevice.setLayout(null);
		nameLabel.setBounds(100, 20, 40, 25);
		name.setBounds(140, 20, 100, 25);
		addInfoSubmit.setBounds(400, 60, 90, 25);
		importDevice.add(nameLabel);
		importDevice.add(name);
		importDevice.add(addInfoSubmit);
		importDevice.setVisible(false);
		addInfoSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = name.getText();

				importDeviceAction = new ImportDeviceAction();

				List<ImportDevice> importDevices = importDeviceAction.getImportDeviceByInf(theName);
				model.setColumnIdentifiers(StatueContent.importDevice);
				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				//查数据库，展示查到的财务信息
				for (ImportDevice im:importDevices) {
					model.addRow(new String[]{
							im.getTime(),
							im.getName(),
							im.getProducer(),
							im.getType(),
							im.getValue(),
							im.getPurchasing(),
							im.getApprove()
					});
				}
			}
		});
		jPanel2.add(importDevice);
	}

	private void changeDeviceLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("姓名：");
		JTextField name = new JTextField();
		JButton addInfoSubmit = new JButton("查找");

		//添加控件
		changeDevice.setLayout(null);
		nameLabel.setBounds(100, 20, 40, 25);
		name.setBounds(140, 20, 100, 25);
		addInfoSubmit.setBounds(400, 60, 90, 25);
		changeDevice.add(nameLabel);
		changeDevice.add(name);
		changeDevice.add(addInfoSubmit);
		changeDevice.setVisible(false);
		addInfoSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = name.getText();

				changeDeviceAction = new ChangeDeviceAction();

				List<ChangeDevice> changeDevices = changeDeviceAction.getChangeDeviceByInf(theName);
				model.setColumnIdentifiers(StatueContent.changeDevice);
				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				//查数据库，展示查到的财务信息
				for (ChangeDevice c:changeDevices) {
					model.addRow(new String[]{
							c.getTime(),
							c.getName(),
							c.getPurchasing(),
							c.getApprove()
					});
				}
			}
		});
		jPanel2.add(changeDevice);

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
		tablePanel.setBounds(0, 100, StatueContent.main_width, StatueContent.main_height - 82);
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
	DictionaryAction dictionaryAction;
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
	private JComboBox doctor = new JComboBox<>();

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

		dictionaryAction = new DictionaryAction();
		List<Dictionary> dictionaries = dictionaryAction.getDictionaryByInf("hospital");
		//就诊医院的下拉表
		for(Dictionary d:dictionaries) {
			hospital.addItem(d.getName());
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
		List<Dictionary> doctors = dictionaryAction.getDictionaryByInf("doctor");
		//就诊医院的下拉表
		for(Dictionary d:dictionaries) {
			doctor.addItem(d.getName());
		}
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
				String theDoctor = doctor.getSelectedItem().toString();

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
				//报销不能大于总价格
				if (totalPriceDouble<reducePriceDouble) {
					JOptionPane.showMessageDialog(null, "报销不能大于总价格，请检查", "错误窗口", JOptionPane.ERROR_MESSAGE);
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

	private ImportDeviceAction importDeviceAction = new ImportDeviceAction();
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

	public AddImport(ReimbursementFrame reimbursementFrame) {
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
				//添加引进设备
				//日期是当前系统时间 月 日 年
				String theDate = nowTime;
				//设备名称
				String theName = name.getText();
				//生产商
				String theProducer = producer.getText();
				//类型
				String theType = type.getText();
				//市场价格
				String theValue = value.getText();
				//进价
				String thePurchasing = purchasing.getText();
				//审批人
				String theApprove = approve.getText();
				double value;
				double pur;
				//验证空值
				if (theName.trim().isEmpty() ||
						theProducer.trim().isEmpty() ||
						theApprove.trim().isEmpty() || theType.trim().isEmpty()&&
						theValue.trim().isEmpty() || thePurchasing.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				try{
					value = Double.parseDouble(theValue);
					pur = Double.parseDouble(thePurchasing);
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null,"价格应该是数字","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (value<pur){
					JOptionPane.showMessageDialog(null,"进价不能大于市场价格","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				ImportDevice importDevice = new ImportDevice();
				importDevice.setName(theName);
				importDevice.setTime(theDate);
				importDevice.setApprove(theApprove);
				importDevice.setProducer(theProducer);
				importDevice.setType(theType);
				importDevice.setValue(theValue);
				importDevice.setPurchasing(thePurchasing);

				importDeviceAction = new ImportDeviceAction();
				int i = importDeviceAction.addImportDevice(importDevice);
				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(reimbursementFrame.model.getRowCount()>0){
					reimbursementFrame.model.removeRow(reimbursementFrame.model.getRowCount()-1);
				}

				reimbursementFrame.model.setColumnIdentifiers(StatueContent.importDevice);

				//查数据库，展示所有外诊报销
				List<ImportDevice> importDevices = importDeviceAction.getAllImportDevice();
				for (ImportDevice im:importDevices) {
					reimbursementFrame.model.addRow(new String[]{
							im.getTime(),
							im.getName(),
							im.getProducer(),
							im.getType(),
							im.getValue(),
							im.getPurchasing(),
							im.getApprove()
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
	private ChangeDeviceAction changeDeviceAction;
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

	public AddChange(ReimbursementFrame reimbursementFrame) {
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
				//添加引进设备
				//日期是当前系统时间 月 日 年
				String theDate = nowTime;
				//物资名称
				String theName = name.getText();
				//进价
				String thePurchasing = purchasing.getText();
				//审批人
				String theApprove = approve.getText();

				double pur;

				//验证空值
				if (theName.trim().isEmpty() ||
						thePurchasing.trim().isEmpty() ||
						theApprove.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					pur = Double.parseDouble(thePurchasing);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"进价必须为数字","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				ChangeDevice changeDevice = new ChangeDevice();
				changeDevice.setTime(theDate);
				changeDevice.setName(theName);
				changeDevice.setApprove(theApprove);
				changeDevice.setPurchasing(thePurchasing);

				changeDeviceAction = new ChangeDeviceAction();
				int i = changeDeviceAction.addChangeDevice(changeDevice);
				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(reimbursementFrame.model.getRowCount()>0){
					reimbursementFrame.model.removeRow(reimbursementFrame.model.getRowCount()-1);
				}

				reimbursementFrame.model.setColumnIdentifiers(StatueContent.changeDevice);

				//查数据库，展示所有外诊报销
				List<ChangeDevice> changeDevices = changeDeviceAction.getAllChangeDevice();
				for (ChangeDevice c:changeDevices) {
					reimbursementFrame.model.addRow(new String[]{
							c.getTime(),
							c.getName(),
							c.getPurchasing(),
							c.getApprove()
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
	private LeaderPayAction leaderPayAction;
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

	public AddLeader(ReimbursementFrame reimbursementFrame) {
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
				//添加领导报销
				//日期是当前系统时间 月 日 年
				String theDate = nowTime;
				//患者名
				String theName = name.getText();
				//单位
				String theUnit = unit.getText();
				//性别
				String theSex = "";
				if (man.isSelected()){
					theSex = man.getText();
				}else if (woman.isSelected()){
					theSex = woman.getText();
				}
				//军衔
				String theMilitary = military.getText();
				//百分比
				String thePercentage = percentage.getText();
				//就诊医院
				String theHospial = hosptial.getText();
				//审批人
				String theApprove = approve.getText();
				//报销原因
				String theReason = reason.getText();

				//验证空值
				if (theName.trim().isEmpty() &&
						theUnit.trim().isEmpty() &&
						theApprove.trim().isEmpty() && theSex.trim().isEmpty()&&
						theMilitary.trim().isEmpty() && thePercentage.trim().isEmpty()&&
						theHospial.trim().isEmpty() && theReason.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				LeaderPay leaderPay = new LeaderPay();
				leaderPay.setName(theName);
				leaderPay.setApprove(theApprove);
				leaderPay.setTime(theDate);
				leaderPay.setMilitary(theMilitary);
				leaderPay.setPercentage(thePercentage);
				leaderPay.setSex(theSex);
				leaderPay.setUnit(theUnit);
				leaderPay.setReason(theReason);
				leaderPay.setHospital(theHospial);

				leaderPayAction = new LeaderPayAction();
				int i = leaderPayAction.addLeaderPay(leaderPay);
				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(reimbursementFrame.model.getRowCount()>0){
					reimbursementFrame.model.removeRow(reimbursementFrame.model.getRowCount()-1);
				}

				reimbursementFrame.model.setColumnIdentifiers(StatueContent.leaderPay);

				//查数据库，展示所有外诊报销
				List<LeaderPay> leaderPays = leaderPayAction.getAllLeaderPay();
				for (LeaderPay l:leaderPays) {
					reimbursementFrame.model.addRow(new String[]{
							l.getTime(),
							l.getName(),
							l.getSex(),
							l.getUnit(),
							l.getMilitary(),
							l.getPercentage(),
							l.getHospital(),
							l.getApprove(),
							l.getReason()
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