package frame;

import action.HealthScreenAction;
import action.ImportantPersonAction;
import action.PreventionAction;
import action.SpecialListAction;
import content.StatueContent;
import model.HealthScreen;
import model.ImportantPerson;
import model.Prevention;
import model.SpecialList;

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

public class SafeFrame {

	ImportantPersonAction importantPersonAction;
	HealthScreenAction healthScreenAction;

	private JFrame jFrame;

	//菜单栏组件
	private JPanel jPanel1;
	private JMenuBar jMenuBar;
	private JMenu[] jMenus = {new JMenu("重点人员") , new JMenu("卫生排查") ,new JMenu("预防工作"),new JMenu("专家讲座"), new JMenu("退出")};
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
	private JPanel addPeople;
	private JPanel addInfo;
	private JPanel addPrevention;
	private JPanel addSpecialList;

	//表格组件
	private String[][] datas = {};
	public DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane5;

	//动态组件
	private JTextField show;
	private JScrollPane showPane;

	public SafeFrame() {
	}

	public SafeFrame(DefaultTableModel model) {
		this.model = model;
	}

	public void init() {

		// 设置页面基本属性
		jFrame = new JFrame(StatueContent.kind[4]);
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
		addPeople = new JPanel();
		addPeople.setBounds(0, 0, StatueContent.main_width, 100);
		addPeopleLayout();

		addInfo = new JPanel();
		addInfo.setBounds(0, 0, StatueContent.main_width, 100);
		addInfoLayout();

		addSpecialList = new JPanel();
		addSpecialList.setBounds(0, 0, StatueContent.main_width, 100);
		addSpecialListLayout();

		addPrevention = new JPanel();
		addPrevention.setBounds(0, 0, StatueContent.main_width, 100);
		addPreventionLayout();

		table.setEnabled(false);
		// 添加单击事件
		j1.addActionListener(new ActionListener() {
			//重点人员信息录入窗口
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPeopleFrame(new SafeFrame(model));
			}
		});
		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addPeople.setVisible(true);
				addInfo.setVisible(false);
			}
		});
		j3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddInfoFrame(new SafeFrame(model));
			}
		});
		j4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addPeople.setVisible(false);
				addPrevention.setVisible(false);
				addSpecialList.setVisible(false);
				addInfo.setVisible(true);
			}
		});
		j5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPrevention(new SafeFrame(model));
			}
		});
		j6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addPeople.setVisible(false);
				addPrevention.setVisible(true);
				addSpecialList.setVisible(false);
				addInfo.setVisible(false);
			}
		});
		j7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddSpecialist(new SafeFrame(model));
			}
		});
		j8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addPeople.setVisible(false);
				addPrevention.setVisible(false);
				addSpecialList.setVisible(true);
				addInfo.setVisible(false);
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

	private void addSpecialListLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("姓名：");
		JTextField name = new JTextField();
		JLabel unitLabel = new JLabel("单位：");
		JTextField unit = new JTextField();
		JButton addPatientSubmit = new JButton("查找");

		//添加控件
		addSpecialList.setLayout(null);
		nameLabel.setBounds(100, 20, 40, 25);
		name.setBounds(140, 20, 100, 25);
		unitLabel.setBounds(360, 20, 40, 25);
		unit.setBounds(400, 20, 200, 25);
		addPatientSubmit.setBounds(400, 60, 90, 25);
		addSpecialList.add(nameLabel);
		addSpecialList.add(name);
		addSpecialList.add(unitLabel);
		addSpecialList.add(unit);
		addSpecialList.add(addPatientSubmit);
		addSpecialList.setVisible(false);
		jPanel2.add(addSpecialList);
		addPatientSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = name.getText();
				String theUnit = unit.getText();

				SpecialListAction specialListAction = new SpecialListAction();

				List<SpecialList> specialLists = specialListAction.getSpecialListByInf(theName , theUnit);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}
				model.setColumnIdentifiers(StatueContent.specialList);

				//查数据库，展示所有病人
				for (SpecialList s:specialLists) {
					model.addRow(new String[]{
							s.getTime(),
							s.getName(),
							s.getUnit(),
							s.getMajor(),
							s.getEndtime()
					});
				}
			}
		});
	}

	private void addPreventionLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("姓名：");
		JTextField name = new JTextField();
		JButton addPatientSubmit = new JButton("查找");

		//添加控件
		addPrevention.setLayout(null);
		nameLabel.setBounds(100, 20, 40, 25);
		name.setBounds(140, 20, 100, 25);
		addPatientSubmit.setBounds(400, 60, 90, 25);
		addPrevention.add(nameLabel);
		addPrevention.add(name);
		addPrevention.add(addPatientSubmit);
		addPrevention.setVisible(false);
		jPanel2.add(addPrevention);
		addPatientSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = name.getText();

				PreventionAction preventAtion = new PreventionAction();

				List<Prevention> preventions = preventAtion.getPreventionByInf(theName);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}
				model.setColumnIdentifiers(StatueContent.prevention);

				//查数据库，展示所有病人
				for (Prevention p:preventions) {
					model.addRow(new String[]{
							p.getTime(),
							p.getLeader(),
							p.getPlace(),
							p.getMaterialName(),
							String.valueOf(p.getMaterialNum()),
							p.getWork()
					});
				}
			}
		});
	}
	// 病人信息录用模块
	private void addPeopleLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("姓名：");
		JTextField name = new JTextField();
		JLabel unitLabel = new JLabel("单位：");
		JTextField unit = new JTextField();
		JButton addPatientSubmit = new JButton("查找");

		//添加控件
		addPeople.setLayout(null);
		nameLabel.setBounds(100, 20, 40, 25);
		name.setBounds(140, 20, 100, 25);
		unitLabel.setBounds(360, 20, 40, 25);
		unit.setBounds(400, 20, 200, 25);
		addPatientSubmit.setBounds(400, 60, 90, 25);
		addPeople.add(nameLabel);
		addPeople.add(name);
		addPeople.add(unitLabel);
		addPeople.add(unit);
		addPeople.add(addPatientSubmit);
		addPeople.setVisible(false);
		jPanel2.add(addPeople);

		addPatientSubmit.addActionListener(new ActionListener() {
			//查找重点病人
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = name.getText();
				String theUnit = unit.getText();

				importantPersonAction = new ImportantPersonAction();
				List<ImportantPerson> list = importantPersonAction.getImportantPersonByInf(theName,theUnit);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				model.setColumnIdentifiers(StatueContent.safeColname1);

				//查数据库，展示所有病人
				for (ImportantPerson importantPerson1 : list) {
					model.addRow(new String[]{
							importantPerson1.getThetime().toString(),
							importantPerson1.getName(),
							importantPerson1.getSex(),
							importantPerson1.getCompany(),
							importantPerson1.getAge()+"",
							importantPerson1.getTemp()+"",
							importantPerson1.getSituation(),
							importantPerson1.getDrug(),
							importantPerson1.getDrugAmount(),
							importantPerson1.getIso(),
							importantPerson1.getDoctor()
					});
				}

			}
		});

	}

	// 录用模块
	private void addInfoLayout() {
		// 声明控件
		JLabel unitLabel = new JLabel("单位：");
		JTextField unit = new JTextField();
		JButton addMedicintSubmit = new JButton("查找");

		// 添加控件
		addInfo.setLayout(null);

		unitLabel.setBounds(100, 20, 80, 25);
		unit.setBounds(180 ,20 , 200 , 25);

		addMedicintSubmit.setBounds(440, 60, 90, 25);
		addInfo.add(unitLabel);
		addInfo.add(unit);
		addInfo.add(addMedicintSubmit);
		addInfo.setVisible(false);
		jPanel2.add(addInfo);

		addMedicintSubmit.addActionListener(new ActionListener() {
			//查找卫生排查信息
			@Override
			public void actionPerformed(ActionEvent e) {
				/*//添加重点
				String theYear = year.getText();
				String theMonth = mouth.getText();
				String theDay = day.getText();
				int yearInt;
				int monthInt;
				int dayInt;
				String theDate = theYear+"-"+theMonth+"-"+theDay;


				if (theDay.trim().isEmpty()&&theMonth.trim().isEmpty()){
					//只有年
					theDate = theYear;
				}else if (theYear.trim().isEmpty()&&theDay.trim().isEmpty()){
					//只有月
					theDate = theMonth;
				}else if (theYear.trim().isEmpty()&&theMonth.trim().isEmpty()){
					//只有日
					theDate = theDay;
				}else if (!theYear.trim().isEmpty()&&!theMonth.trim().isEmpty()&&theDay.trim().isEmpty()){
					//有年、月
					theDate = theYear + "-" + theMonth;
				}else if (!theYear.trim().isEmpty()&&theMonth.trim().isEmpty()&&!theDay.trim().isEmpty()){
					//有年、日
					theDate = theYear + "-" + theDay;
				}else if (theYear.trim().isEmpty()&&!theMonth.trim().isEmpty()&&!theDay.trim().isEmpty()){
					//有月、日
					theDate = theMonth+ "-" + theDay;
				}else {
					theDate = theYear + "-" + theMonth + "-" + theDay;
				}

				healthScreenAction = new HealthScreenAction();
				List<HealthScreen> list= healthScreenAction.getHealthScreenByInf(theDate);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				model.setColumnIdentifiers(StatueContent.safeColname2);

				//查数据库，信息
				for (HealthScreen healthScreen1 : list) {
					model.addRow(new String[]{
							healthScreen1.getTime().toString(),
							healthScreen1.getCompany(),
							healthScreen1.getType(),
							healthScreen1.getDie()+"",
							healthScreen1.getEpidemic(),
							healthScreen1.getName()
					});
				}*/

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
		model = new DefaultTableModel(datas, StatueContent.safeColname1);
		table = new JTable(model);
		scrollPane5 = new JScrollPane(table);
		table.getTableHeader().setResizingAllowed(false);
		tablePanel.add(scrollPane5, BorderLayout.CENTER);
		table.setRowHeight(35);

		importantPersonAction = new ImportantPersonAction();

		//清空
		while(model.getRowCount()>0){
			model.removeRow(model.getRowCount()-1);
		}

		model.setColumnIdentifiers(StatueContent.safeColname1);

		//查数据库，展示所有病人
		List<ImportantPerson> importantPersonList = importantPersonAction.getAllImportantPerson();
		for (ImportantPerson importantPerson1 : importantPersonList) {
			model.addRow(new String[]{
					importantPerson1.getThetime().toString(),
					importantPerson1.getName(),
					importantPerson1.getSex(),
					importantPerson1.getCompany(),
					importantPerson1.getAge()+"",
					importantPerson1.getTemp()+"",
					importantPerson1.getSituation(),
					importantPerson1.getDrug(),
					importantPerson1.getDrugAmount(),
					importantPerson1.getIso(),
					importantPerson1.getDoctor()
			});
		}


	}

	public static void main(String[] args) {
		new SafeFrame().init();
	}
}

class AddPeopleFrame {
	ImportantPersonAction importantPersonAction;

	private JFrame jFrame = new JFrame("信息录入");
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);
	private JLabel unitLabel = new JLabel("单位：");
	private JLabel ageLabel = new JLabel("年龄：");
	private JLabel contentLabel = new JLabel("病情：");
	private JLabel doctorLabel = new JLabel("值班医生：");

	private JLabel nameLabel = new JLabel("姓名：");
	private JTextField name = new JTextField();
	private JLabel sexLabel = new JLabel("性别：");
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JRadioButton man = new JRadioButton("男");
	private JRadioButton woman = new JRadioButton("女");
	private JTextField unit = new JTextField();
	private JTextField age = new JTextField();
	private JTextArea content = new JTextArea();
	private JTextField doctor = new JTextField();

	private JLabel temperatureLabel = new JLabel("体温");
	private JTextField tempterature = new JTextField();
	private JLabel geliLabel = new JLabel("隔离时间");
	private JTextField geli = new JTextField();

	private JLabel medicineNameLabel = new JLabel("用药名称");
	private JTextField medicineName = new JTextField();
	private JLabel medNumLabel = new JLabel("用药数量");
	private JTextField medNum = new JTextField();

	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddPeopleFrame(SafeFrame safeFrame) {
		jFrame.setTitle("重点人员信息录入");
		jFrame.setSize(450, 500);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		// 第一行

		nameLabel.setBounds(30, 60, 80, 25);
		name.setBounds(100, 60, 150, 25);

		sexLabel.setBounds(30, 90, 80, 25);
		man.setBounds(100, 90, 50, 25);
		woman.setBounds(150, 90, 50, 25);
		buttonGroup1.add(man);
		buttonGroup1.add(woman);

		unitLabel.setBounds(30, 130, 80, 25);
		unit.setBounds(100, 130, 150, 25);

		ageLabel.setBounds(30, 170, 80, 25);
		age.setBounds(100, 170, 80, 25);

		doctorLabel.setBounds(30, 210, 80, 25);
		doctor.setBounds(100, 210, 150, 25);

		temperatureLabel.setBounds(30, 250, 80, 25);
		tempterature.setBounds(100 , 250 , 80 , 25);
		geliLabel.setBounds(220, 250, 80, 25);
		geli.setBounds(300 , 250 , 80 , 25);

		medicineNameLabel.setBounds(30, 290, 80, 25);
		medicineName.setBounds(100 , 290 , 80 , 25);
		medNumLabel.setBounds(220, 290, 80, 25);
		medNum.setBounds(300 , 290 , 80 , 25);

		contentLabel.setBounds(30, 330, 80, 25);
		content.setBounds(100, 330, 310, 70);

		submit.setBounds(100, 415, 90, 25);
		cancel.setBounds(200, 415, 90, 25);


		jFrame.add(unitLabel);
		jFrame.add(ageLabel);
		jFrame.add(contentLabel);
		jFrame.add(doctorLabel);
		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(sexLabel);
		jFrame.add(man);
		jFrame.add(woman);
		jFrame.add(age);
		jFrame.add(unit);
		jFrame.add(content);
		jFrame.add(doctor);
		jFrame.add(medNum);
		jFrame.add(medNumLabel);
		jFrame.add(medicineName);
		jFrame.add(medicineNameLabel);
		jFrame.add(geliLabel);
		jFrame.add(geli);
		jFrame.add(temperatureLabel);
		jFrame.add(tempterature);
		jFrame.add(submit);
		jFrame.add(cancel);

		man.doClick();

		submit.addActionListener(new ActionListener() {
			//重点人员信息录入
			@Override
			public void actionPerformed(ActionEvent e) {
				importantPersonAction = new ImportantPersonAction();
				//添加重点

				String theDate = nowTime;

				String theName = name.getText();
				String theUnit = unit.getText();

				String theSex = "";
				if (man.isSelected()){
					theSex = man.getText();
				}else if (woman.isSelected()){
					theSex = woman.getText();
				}
				// int
				String theAge = age.getText();
				String theDoctor = doctor.getText();
				//double
				String theTemp = tempterature.getText();
				String theIos = geli.getText();
				String theDrug = medicineName.getText();
				String theDrugAmount = medNum.getText();

				String theSituation = content.getText();

				int ageInt;
				//验证空值
				if (theName.isEmpty() &&
						theAge.isEmpty() && theUnit.isEmpty() &&
						theSex.isEmpty() && theDoctor.isEmpty()&&
						theTemp.isEmpty() && theIos.trim().isEmpty()&&
						theDrug.isEmpty() && theDrugAmount.trim().isEmpty()&&
						theSituation.isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}


				//年龄 必须 是整数
				try {
					ageInt = Integer.parseInt(theAge);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"年龄必须是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}
				double tempDouble;
				//体温是数字
				try {
					tempDouble = Double.parseDouble(theTemp);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"体温必须是数字，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				ImportantPerson importantPerson = new ImportantPerson();
				importantPerson.setThetime(theDate);
				importantPerson.setName(theName);
				importantPerson.setSex(theSex);
				importantPerson.setCompany(theUnit);
				importantPerson.setAge(ageInt);
				importantPerson.setDoctor(theDoctor);
				importantPerson.setTemp(tempDouble);
				importantPerson.setIso(theIos);
				importantPerson.setDrug(theDrug);
				importantPerson.setDrugAmount(theDrugAmount);
				importantPerson.setSituation(theSituation);

				int i = importantPersonAction.addImportantPerson(importantPerson);

				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(safeFrame.model.getRowCount()>0){
					safeFrame.model.removeRow(safeFrame.model.getRowCount()-1);
				}

				safeFrame.model.setColumnIdentifiers(StatueContent.safeColname1);

				//查数据库，展示所有重点病人
				List<ImportantPerson> importantPersonList = importantPersonAction.getAllImportantPerson();
				for (ImportantPerson importantPerson1 : importantPersonList) {
					safeFrame.model.addRow(new String[]{
							importantPerson1.getThetime().toString(),
							importantPerson1.getName(),
							importantPerson1.getSex(),
							importantPerson1.getCompany(),
							importantPerson1.getAge()+"",
							importantPerson1.getTemp()+"",
							importantPerson1.getSituation(),
							importantPerson1.getDrug(),
							importantPerson1.getDrugAmount(),
							importantPerson1.getIso(),
							importantPerson1.getDoctor()
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


//卫生排查
class AddInfoFrame {
	HealthScreenAction healthScreenAction;

	private JFrame jFrame = new JFrame("卫生排查信息录入");
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);
	private JLabel unitLabel = new JLabel("单位：");

	private JTextField unit = new JTextField();

	private JLabel kindLabel = new JLabel("病情种类");
	private JTextField kind = new JTextField();
	private JLabel peopleLabel = new JLabel("调查人");
	private JTextField people = new JTextField();

	private JLabel yiqingLabel = new JLabel("有无重大疫情");
	private JTextField yiqing = new JTextField();

	private JLabel peopleNumLabel = new JLabel("非战斗减员人数");
	private JTextField peopleNum = new JTextField();


	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddInfoFrame(SafeFrame safeFrame) {
		jFrame.setTitle("卫生排查信息录入");
		jFrame.setSize(450, 300);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		// 第一行

		unitLabel.setBounds(30, 60, 80, 25);
		unit.setBounds(100, 60, 150, 25);

		kindLabel.setBounds(30, 100, 80, 25);
		kind.setBounds(100 , 100 , 80 , 25);
		peopleLabel.setBounds(220, 100, 80, 25);
		people.setBounds(300 , 100 , 80 , 25);

		yiqingLabel.setBounds(30, 140, 100, 25);
		yiqing.setBounds(130, 140, 150, 25);

		peopleNumLabel.setBounds(30, 180, 100, 25);
		peopleNum.setBounds(130, 180, 150, 25);

		submit.setBounds(100, 220, 90, 25);
		cancel.setBounds(200, 220, 90, 25);


		jFrame.add(unitLabel);
		jFrame.add(unit);
		jFrame.add(kindLabel);
		jFrame.add(kind);
		jFrame.add(peopleLabel);
		jFrame.add(people);
		jFrame.add(yiqingLabel);
		jFrame.add(yiqing);
		jFrame.add(peopleNumLabel);
		jFrame.add(peopleNum);


		jFrame.add(submit);
		jFrame.add(cancel);

		submit.addActionListener(new ActionListener() {
			//添加排查
			@Override
			public void actionPerformed(ActionEvent e) {
				//添加重点
				String theDate = nowTime;

				String theUnit = unit.getText();
				String theKind = kind.getText();
				//调查人
				String theName = people.getText();
				String theType = yiqing.getText();

				String theDie = peopleNum.getText();
				int dieInt;

				//验证空值
				if (theName.trim().isEmpty() &&
						theKind.trim().isEmpty() && theType.trim().isEmpty() &&
						theDie.trim().isEmpty() && theUnit.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				//死亡人数 必须 是整数
				try {
					dieInt = Integer.parseInt(theDie);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"死亡人数必须是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				HealthScreen healthScreen = new HealthScreen();
				healthScreen.setTime(theDate);
				healthScreen.setCompany(theUnit);
				healthScreen.setName(theName);
				healthScreen.setEpidemic(theKind);
				healthScreen.setType(theType);
				healthScreen.setDie(dieInt);
				healthScreenAction = new HealthScreenAction();

				int i = healthScreenAction.addHealthScreen(healthScreen);

				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(safeFrame.model.getRowCount()>0){
					safeFrame.model.removeRow(safeFrame.model.getRowCount()-1);
				}

				safeFrame.model.setColumnIdentifiers(StatueContent.safeColname2);

				//查数据库，展示所有病人
				List<HealthScreen> healthScreens = healthScreenAction.getAllhealthScreens();
				for (HealthScreen healthScreen1 : healthScreens) {
					safeFrame.model.addRow(new String[]{
							healthScreen1.getTime().toString(),
							healthScreen1.getCompany(),
							healthScreen1.getType(),
							healthScreen1.getDie()+"",
							healthScreen1.getEpidemic(),
							healthScreen1.getName()
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

//预防工作
class AddPrevention {
	private PreventionAction preventionAction;
	private JFrame jFrame = new JFrame("预防工作信息录入");
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);

	private JLabel leaderLabel = new JLabel("值班领导");
	private JTextField leader = new JTextField();

	private JLabel placeLabel = new JLabel("地点");
	private JTextField place = new JTextField();

	private JLabel materialsNameLabel = new JLabel("发放物资名称");
	private JTextField materialName = new JTextField();

	private JLabel materialsNumLabel = new JLabel("发放物资数量");
	private JTextField materialNum = new JTextField();

	private JLabel workLabel = new JLabel("具体工作");
	private JTextField work = new JTextField();

	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddPrevention(SafeFrame safeFrame) {
		jFrame.setTitle("预防工作信息录入");
		jFrame.setSize(450, 250);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		leaderLabel.setBounds(30, 20, 80, 25);
		leader.setBounds(110, 20, 90, 25);

		placeLabel.setBounds(30, 60, 80, 25);
		place.setBounds(110,60,90,25);

		materialsNameLabel.setBounds(30, 100, 80, 25);
		materialName.setBounds(110, 100, 90, 25);
		materialsNumLabel.setBounds(200, 100, 80, 25);
		materialNum.setBounds(280, 100, 90, 25);

		workLabel.setBounds(30, 140, 80, 25);
		work.setBounds(110,140,90,25);

		submit.setBounds(100, 180, 90, 25);
		cancel.setBounds(200, 180, 90, 25);

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//添加预防工作
				String theDate = nowTime;
				//地点
				String thePlace = place.getText();
				//值班领导
				String theLeader = leader.getText();
				//发放物资名称
				String theMaterialName = materialName.getText();
				//发放物资数量
				String theMaterialNum = materialNum.getText();
				//具体工作
				String theWork = work.getText();

				//验证空值
				if (thePlace.trim().isEmpty() &&
						theLeader.trim().isEmpty() &&
						theMaterialName.trim().isEmpty() && theMaterialNum.trim().isEmpty()&&
						theWork.trim().isEmpty() ){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				Prevention prevention = new Prevention();
				prevention.setTime(theDate);
				prevention.setLeader(theLeader);
				prevention.setMaterialName(theMaterialName);
				prevention.setMaterialNum(Integer.parseInt(theMaterialNum));
				prevention.setPlace(thePlace);
				prevention.setWork(theWork);

				preventionAction = new PreventionAction();
				int i = preventionAction.addPrevention(prevention);
				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(safeFrame.model.getRowCount()>0){
					safeFrame.model.removeRow(safeFrame.model.getRowCount()-1);
				}

				safeFrame.model.setColumnIdentifiers(StatueContent.prevention);

				//查数据库，展示所有外诊报销
				List<Prevention> preventions = preventionAction.getAllPrevention();
				for (Prevention p:preventions) {
					safeFrame.model.addRow(new String[]{
							p.getTime(),
							p.getLeader(),
							p.getPlace(),
							p.getMaterialName(),
							String.valueOf(p.getMaterialNum()),
							p.getWork()
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

		jFrame.add(leaderLabel);
		jFrame.add(leader);
		jFrame.add(placeLabel);
		jFrame.add(place);
		jFrame.add(materialsNameLabel);
		jFrame.add(materialName);
		jFrame.add(materialsNumLabel);
		jFrame.add(materialNum);
		jFrame.add(workLabel);
		jFrame.add(work);
		jFrame.add(submit);
		jFrame.add(cancel);
		jFrame.setVisible(true);
	}
}

//专家讲座
class AddSpecialist {
	private SpecialListAction specialListAction;
	private JFrame jFrame = new JFrame("专家讲座信息录入");
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);

	private JLabel nameLabel = new JLabel("姓名：");
	private JTextField name = new JTextField();

	private JLabel unitLabel = new JLabel("单位");
	private JTextField unit = new JTextField();

	private JLabel majorLabel = new JLabel("专业");
	private JTextField major = new JTextField();

	private JLabel endTimeLabel = new JLabel("截止时间");
	private JLabel yearLabel = new JLabel("年");
	private JLabel mouthLabel = new JLabel("月");
	private JLabel dayLabel = new JLabel("日");
	private JTextField year = new JTextField();
	private JTextField mouth = new JTextField();
	private JTextField day = new JTextField();

	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddSpecialist(SafeFrame safeFrame) {
		jFrame.setTitle("专家讲座信息录入");
		jFrame.setSize(450, 200);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		nameLabel.setBounds(30, 20, 80, 25);
		name.setBounds(110, 20, 90, 25);
		majorLabel.setBounds(220, 20, 80, 25);
		major.setBounds(280, 20, 90, 25);

		unitLabel.setBounds(30, 60, 80, 25);
		unit.setBounds(110,60,90,25);

		endTimeLabel.setBounds(30, 100, 80, 25);
		year.setBounds(110 ,100 , 80 , 25);
		yearLabel.setBounds(190, 100, 15, 25);
		mouth.setBounds(205 , 100,80,25);
		mouthLabel.setBounds(285, 100, 15, 25);
		day.setBounds(300,100,80,25);
		dayLabel.setBounds(380, 100, 15, 25);


		submit.setBounds(100, 140, 90, 25);
		cancel.setBounds(200, 140, 90, 25);

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//添加专家讲座
				String theName = name.getText();
				String theMajor = major.getText();
				String theUnit = unit.getText();
				String endTime = year.getText() + "-" + mouth.getText() + "-" + day.getText();
				String theDate = nowTime;

				//验证空值
				if (theName.trim().isEmpty() &&
						theMajor.trim().isEmpty() && theUnit.trim().isEmpty() &&
						endTime.trim().isEmpty() && theUnit.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				SpecialList specialList = new SpecialList();
				specialList.setName(theName);
				specialList.setMajor(theMajor);
				specialList.setEndtime(endTime);
				specialList.setUnit(theUnit);
				specialList.setTime(theDate);


				specialListAction = new SpecialListAction();
				int i = specialListAction.addSpecialList(specialList);

				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(safeFrame.model.getRowCount()>0){
					safeFrame.model.removeRow(safeFrame.model.getRowCount()-1);
				}

				safeFrame.model.setColumnIdentifiers(StatueContent.specialList);

				//查数据库，展示所有病人
				List<SpecialList> specialLists = specialListAction.getAllSpecialList();
				for (SpecialList s:specialLists) {
					safeFrame.model.addRow(new String[]{
						s.getTime(),
						s.getName(),
						s.getUnit(),
						s.getMajor(),
						s.getEndtime()
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
		jFrame.add(majorLabel);
		jFrame.add(major);
		jFrame.add(unitLabel);
		jFrame.add(unit);
		jFrame.add(endTimeLabel);
		jFrame.add(yearLabel);
		jFrame.add(year);
		jFrame.add(mouthLabel);
		jFrame.add(mouth);
		jFrame.add(dayLabel);
		jFrame.add(day);
		jFrame.add(submit);
		jFrame.add(cancel);

		jFrame.setVisible(true);
	}
}

