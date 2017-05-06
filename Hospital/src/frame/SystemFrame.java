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

public class SystemFrame {

	//各部分的控制器——Action
	SystemConAction systemConAction;
	DoctorAction doctorAction;
	NurseAction nurseAction;
	FinanceAction financeAction;
	StoreKeeperAction storeKeeperAction;
	HealthAction healthAction;

	private JFrame jFrame;
	
	//菜单栏组件
	private JPanel jPanel1;
    private JMenuBar jMenuBar;
    private JMenu[] jMenus = {new JMenu("字典维护"),new JMenu("病人情况"),new JMenu("财务工作"),new JMenu("仓库管理"),new JMenu("卫生安全"),new JMenu("操作授权"),new JMenu("退出")};
	private JMenuItem j1 = new JMenuItem("字典维护");
	private JMenuItem j2 = new JMenuItem("医生工作");
	private JMenuItem j22 = new JMenuItem("护士工作");
	private JMenuItem j3 = new JMenuItem("财务工作");
	private JMenuItem j4 = new JMenuItem("仓库管理");
	private JMenuItem j5 = new JMenuItem("卫生安全");
	private JMenuItem j6 = new JMenuItem("操作授权");
	private JMenuItem j7 = new JMenuItem("退出");

	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel changePath;
	private JPanel operation;
    private JPanel tablePanel = new JPanel();
    //表格组件
    private String[][] datas = {};
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane5;
    
    //动态组件
    private JTextField show;
    private JScrollPane showPane;

	private JPanel addDictron;
	private JPanel doctorWork;
	private JPanel nurseWork;
	private JPanel finance;
	private JPanel eisai;
	private JPanel safe;

	private int num = 5;

	public void init() {

		// 设置页面基本属性
		jFrame = new JFrame(StatueContent.kind[5]);
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
		addDictron = new JPanel();
		addDictron.setBounds(0, 0, StatueContent.main_width, 300);
		changePathLayout();

		operation = new JPanel();
		operation.setBounds(0, 0, StatueContent.main_width, 300);
		operationLayout();

		doctorWork = new JPanel();
		doctorWork.setBounds(0, 0, StatueContent.main_width, 100);
		doctorWorkLayout();

		nurseWork = new JPanel();
		nurseWork.setBounds(0, 0, StatueContent.main_width, 100);
		nurseWorkLayout();

		finance = new JPanel();
		finance.setBounds(0, 0, StatueContent.main_width, 100);
		financeLayout();

		eisai = new JPanel();
		eisai.setBounds(0, 0, StatueContent.main_width, 100);
		eisaiLayout();

		safe = new JPanel();
		safe.setBounds(0, 0, StatueContent.main_width, 100);
		safeLayout();

		// 字典维护
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addDictron.setVisible(true);
				operation.setVisible(false);
				doctorWork.setVisible(false);
				nurseWork.setVisible(false);
				finance.setVisible(false);
				eisai.setVisible(false);
				safe.setVisible(false);
				tablePanel.setVisible(false);
				tablePanel.setBounds(0, 300, StatueContent.main_width, StatueContent.main_height - 300);
			}
		});

		//医生工作
		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				jPanel3.setVisible(false);
				addDictron.setVisible(false);
				operation.setVisible(false);
				doctorWork.setVisible(true);
				nurseWork.setVisible(false);
				finance.setVisible(false);
				eisai.setVisible(false);
				safe.setVisible(false);

				tablePanel.setVisible(true);
				model.setColumnIdentifiers(StatueContent.doctorcolname1);
				tablePanel.setBounds(0, 100, StatueContent.main_width, StatueContent.main_height - 100);
			}
		});

		//护士工作
		j22.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				jPanel3.setVisible(false);
				addDictron.setVisible(false);
				operation.setVisible(false);
				doctorWork.setVisible(false);
				nurseWork.setVisible(true);
				finance.setVisible(false);
				eisai.setVisible(false);
				safe.setVisible(false);

				tablePanel.setVisible(true);
				model.setColumnIdentifiers(StatueContent.doctorcolname2);
				tablePanel.setBounds(0, 100, StatueContent.main_width, StatueContent.main_height - 100);
			}
		});

		//财务工作
		j3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				jPanel3.setVisible(false);
				addDictron.setVisible(false);
				operation.setVisible(false);
				doctorWork.setVisible(false);
				nurseWork.setVisible(false);
				finance.setVisible(true);
				eisai.setVisible(false);
				safe.setVisible(false);

				tablePanel.setVisible(true);
				model.setColumnIdentifiers(StatueContent.reimbursementColname);
				tablePanel.setBounds(0, 100, StatueContent.main_width, StatueContent.main_height - 100);
			}
		});

		//仓库管理
		j4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				jPanel3.setVisible(false);
				addDictron.setVisible(false);
				operation.setVisible(false);
				doctorWork.setVisible(false);
				nurseWork.setVisible(false);
				finance.setVisible(false);
				eisai.setVisible(true);
				safe.setVisible(false);

				tablePanel.setVisible(true);
				model.setColumnIdentifiers(StatueContent.eisaiManagementColname1);
				tablePanel.setBounds(0, 100, StatueContent.main_width, StatueContent.main_height - 100);
			}
		});

		//卫生安全
		j5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addDictron.setVisible(false);
				operation.setVisible(false);
				doctorWork.setVisible(false);
				nurseWork.setVisible(false);
				finance.setVisible(false);
				eisai.setVisible(false);
				safe.setVisible(true);

				tablePanel.setVisible(true);
				model.setColumnIdentifiers(StatueContent.safeColname1);
				tablePanel.setBounds(0, 100, StatueContent.main_width, StatueContent.main_height - 100);
			}
		});

		j6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jPanel3.setVisible(false);
				operation.setVisible(true);
				addDictron.setVisible(false);
				doctorWork.setVisible(false);
				nurseWork.setVisible(false);
				finance.setVisible(false);
				eisai.setVisible(false);
				safe.setVisible(false);

				tablePanel.setVisible(false);
				tablePanel.setBounds(0, 300, StatueContent.main_width, StatueContent.main_height - 300);
			}
		});

		j7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jFrame.dispose();
				new Login().init();
			}
		});

		jFrame.setVisible(true);
	}
	
	private void changePathLayout() {
		//声明控件
		JLabel addDoctorLabel = new JLabel("添加医生：");
		JTextField addDoctor = new JTextField();
		JButton addDoctorSubmit = new JButton("OK");

		JLabel addNurseLabel = new JLabel("添加护士：");
		JTextField addNurse = new JTextField();
		JButton addNurseSubmit = new JButton("OK");

		JLabel addKindLabel = new JLabel("添加药品种类：");
		JTextField addkind = new JTextField();
		JButton addKindSubmit = new JButton("OK");

		JLabel addHospitalLabel = new JLabel("添加就诊医院：");
		JTextField addHospital = new JTextField();
		JButton addHospitalSubmit = new JButton("OK");

		//添加控件
		addDictron.setLayout(null);
		addDoctorLabel.setBounds(100, 20, 80, 25);
		addDoctor.setBounds(200, 20, 100, 25);
		addDoctorSubmit.setBounds(400, 20, 90, 25);

		addNurseLabel.setBounds(100, 60, 80, 25);
		addNurse.setBounds(200, 60, 100, 25);
		addNurseSubmit.setBounds(400, 60, 90, 25);

		addKindLabel.setBounds(100, 100, 100, 25);
		addkind.setBounds(200, 100, 100, 25);
		addKindSubmit.setBounds(400, 100, 90, 25);

		addHospitalLabel.setBounds(100, 140, 100, 25);
		addHospital.setBounds(200, 140, 100, 25);
		addHospitalSubmit.setBounds(400, 140, 90, 25);
		//备份的OK
		addDoctorSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		addDictron.add(addDoctorLabel);
		addDictron.add(addDoctor);
		addDictron.add(addDoctorSubmit);
		addDictron.add(addHospitalLabel);
		addDictron.add(addHospitalSubmit);
		addDictron.add(addHospital);
		addDictron.add(addKindSubmit);
		addDictron.add(addKindLabel);
		addDictron.add(addkind);
		addDictron.add(addNurseLabel);
		addDictron.add(addNurse);
		addDictron.add(addNurseSubmit);
		addDictron.setVisible(false);
		jPanel2.add(addDictron);
	}

	//添加人员
	private void operationLayout() {
		//声明控件
		JLabel idLabel = new JLabel("工号：");
		JTextField id = new JTextField();
		JLabel nameLabel = new JLabel("姓名：");
		JTextField name = new JTextField();
		JLabel passwordLabel = new JLabel("口令：");
		JPasswordField password = new JPasswordField();
		JLabel passwordLabel2 = new JLabel("确认口令：");
		JPasswordField password2 = new JPasswordField();
		JLabel jurisdictionLabel1 = new JLabel("权限授予：");
		JComboBox jurisdiction = new JComboBox<>();
		for(int i = 0;i < StatueContent.jurisdictions.length; i++) {
			jurisdiction.addItem(StatueContent.jurisdictions[i]);
		}
		JButton addInfoSubmit = new JButton("OK");
		
		//添加控件
		operation.setLayout(null);
		idLabel.setBounds(100, 20, 80, 25);
		id.setBounds(180, 20, 100, 25);
		nameLabel.setBounds(100, 60, 80, 25);
		name.setBounds(180, 60, 100, 25);
		passwordLabel.setBounds(100, 100, 80, 25);
		password.setBounds(180, 100, 100, 25);
		passwordLabel2.setBounds(100, 140, 80, 25);
		password2.setBounds(180, 140, 100, 25);
		jurisdictionLabel1.setBounds(100, 180, 80, 25);
		jurisdiction.setBounds(180, 180, 100, 25);
		
		addInfoSubmit.setBounds(400, 220, 90, 25);

		//确认添加
		addInfoSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取填写的所有值
				int theId;
				String idString = id.getText();
				String nameString = name.getText();
				String passwordString = password.getText();
				String password2String = password2.getText();
				String role = jurisdiction.getSelectedItem().toString();
				//先排除空值情况
				//判断为空，报异常窗口
				if (idString.isEmpty()||nameString.isEmpty()||passwordString.isEmpty()||password2String.isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.WARNING_MESSAGE);
					return;
				}
				//如果工号为不是整数报异常窗口
				try {
					theId = Integer.parseInt(idString);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"工号必须是整数","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//排除两次密码不一致情况
				if (!passwordString.equals(password2String)){
					JOptionPane.showMessageDialog(null,"两次密码不一致","信息窗口",JOptionPane.WARNING_MESSAGE);
					return;
				}
				//通过前面的筛选再走下面的流程
				//确定添加的人的 权限 是医生还是护士还是仓库管理员......
				switch (role){
					case "doctor":
						//添加医生
						//步骤1：查重
						//查重主要 看工号
						doctorAction = new DoctorAction();
						Doctor doctor = doctorAction.loadDoctor(theId);
						if (doctor != null){
							JOptionPane.showMessageDialog(null,"此医生已经存在！请检查工号","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						doctor = new Doctor();
						//步骤2：添加
						doctor.setId(theId);
						doctor.setName(nameString);
						doctor.setPassword(passwordString);
						int i = doctorAction.addDoctor(doctor);
						if (i==1){
							JOptionPane.showMessageDialog(null,"医生添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null,"医生添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case "nurse":
						//添加护士
						//步骤1：查重
						//查重主要 看工号
						nurseAction = new NurseAction();
						Nurse nurse = nurseAction.loadNurse(theId);
						if (nurse != null){
							JOptionPane.showMessageDialog(null,"此护士已经存在！请检查工号","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						nurse = new Nurse();
						//步骤2：添加
						nurse.setId(theId);
						nurse.setName(nameString);
						nurse.setPassword(passwordString);
						int i1 = nurseAction.addNurse(nurse);
						if (i1==1){
							JOptionPane.showMessageDialog(null,"护士添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null,"护士添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case "storageKeeper":
						//添加护士
						//步骤1：查重
						//查重主要 看工号
						storeKeeperAction = new StoreKeeperAction();
						StoreKeeper storeKeeper = storeKeeperAction.loadStoreKeeper(theId);
						if (storeKeeper != null){
							JOptionPane.showMessageDialog(null,"此仓库管理已经存在！请检查工号","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						storeKeeper = new StoreKeeper();
						//步骤2：添加
						storeKeeper.setId(theId);
						storeKeeper.setName(nameString);
						storeKeeper.setPassword(passwordString);
						int i3 = storeKeeperAction.addStoreKeeper(storeKeeper);
						if (i3==1){
							JOptionPane.showMessageDialog(null,"仓管添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null,"仓管添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case "finance":
						//添加财务
						//步骤1：查重
						//查重主要 看工号
						financeAction = new FinanceAction();
						Finance finance = financeAction.loadFinance(theId);
						if (finance != null){
							JOptionPane.showMessageDialog(null,"此财务人员已经存在！请检查工号","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						finance = new Finance();
						//步骤2：添加
						finance.setId(theId);
						finance.setName(nameString);
						finance.setPassword(passwordString);
						int i2 = financeAction.addFinance(finance);
						if (i2 == 1){
							JOptionPane.showMessageDialog(null,"财务添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null,"财务添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case "health":
						//添加卫生
						//步骤1：查重
						//查重主要 看工号
						healthAction = new HealthAction();
						Health health = healthAction.loadHealth(theId);
						if (health != null){
							JOptionPane.showMessageDialog(null,"此卫生人员已经存在！请检查工号","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						health = new Health();
						//步骤2：添加
						health.setId(theId);
						health.setName(nameString);
						health.setPassword(passwordString);
						int i4 = healthAction.addHealth(health);
						if (i4 == 1){
							JOptionPane.showMessageDialog(null,"卫生添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null,"卫生添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case "systemCon":
						//添加卫生
						//步骤1：查重
						//查重主要 看工号
						systemConAction = new SystemConAction();
						SystemCon systemCon = systemConAction.loadSystemCon(theId);
						if (systemCon != null){
							JOptionPane.showMessageDialog(null,"管理员已经存在！请检查工号","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						systemCon = new SystemCon();
						//步骤2：添加
						systemCon.setId(theId);
						systemCon.setName(nameString);
						systemCon.setPassword(passwordString);
						int i5 = systemConAction.addSystemCon(systemCon);
						if (i5 == 1){
							JOptionPane.showMessageDialog(null,"管理员添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null,"管理员添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
						}
						break;
				}
			}
		});

		operation.add(idLabel);
		operation.add(id);
		operation.add(nameLabel);
		operation.add(name);
		operation.add(passwordLabel);
		operation.add(password);
		operation.add(passwordLabel2);
		operation.add(password2);
		operation.add(jurisdictionLabel1);
		operation.add(jurisdiction);
		operation.add(addInfoSubmit);		
		operation.setVisible(false);
		jPanel2.add(operation);
	}

	private void layoutPanel1() {
        jPanel1.setLayout(new BorderLayout());
        jMenuBar = new JMenuBar();
        jMenus[0].add(j1);
        jMenus[1].add(j2);
        jMenus[1].add(j22);
		jMenus[2].add(j3);
		jMenus[3].add(j4);
		jMenus[4].add(j5);
		jMenus[5].add(j6);
		jMenus[6].add(j7);
        for(int i = 0; i < jMenus.length; i ++) {
        	jMenuBar.add(jMenus[i]);
        }
        jPanel1.add(jMenuBar);
    }
	
	private void layoutPanel2() {
		jPanel2.setLayout(null);
		jPanel3 = new JPanel();
		jPanel3.setBounds(0, 0, StatueContent.main_width, 150);
		//设置展示框
		show = new JTextField("欢迎使用");
		show.setEnabled(false);
		show.setFont(new Font("黑体",Font.PLAIN,90));
		show.setHorizontalAlignment(JTextField.CENTER);  
		jPanel3.setLayout(new BorderLayout());
		showPane = new JScrollPane();
		showPane.setViewportView(show);
		jPanel3.add(showPane , BorderLayout.CENTER);

		tablePanel.setBounds(0, 100, StatueContent.main_width, StatueContent.main_height - 100);
		jPanel2.add(jPanel3);
		jPanel2.add(tablePanel);
		// 初始化表格
		tablePanel.setLayout(new BorderLayout());
		model = new DefaultTableModel(datas, StatueContent.eisaiManagementColname1);
		table = new JTable(model);
		scrollPane5 = new JScrollPane(table);
		table.getTableHeader().setResizingAllowed(false);
		tablePanel.add(scrollPane5, BorderLayout.CENTER);
		table.setRowHeight(35);
		table.setEnabled(false);
		tablePanel.setVisible(false);
	}

	private void safeLayout() {
		// 声明控件
		JLabel timeLabel = new JLabel("时间：");
		JTextField year = new JTextField();
		JLabel yearLabel = new JLabel("年");
		JTextField mouth = new JTextField();
		JLabel mouthLabel = new JLabel("月");
		JTextField day = new JTextField();
		JLabel dayLabel = new JLabel("日");
		JButton addMedicintSubmit = new JButton("查找");

		// 添加控件
		safe.setLayout(null);

		timeLabel.setBounds(100, 20, 80, 25);
		year.setBounds(180 ,20 , 80 , 25);
		yearLabel.setBounds(260, 20, 15, 25);
		mouth.setBounds(275 , 20,80,25);
		mouthLabel.setBounds(355, 20, 15, 25);
		day.setBounds(370,20,80,25);
		dayLabel.setBounds(450, 20, 15, 25);

		addMedicintSubmit.setBounds(440, 60, 90, 25);
		addMedicintSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"共计" + num + "人","统计人数",JOptionPane.WARNING_MESSAGE);
			}
		});
		safe.add(timeLabel);
		safe.add(year);
		safe.add(yearLabel);
		safe.add(mouth);
		safe.add(mouthLabel);
		safe.add(day);
		safe.add(dayLabel);
		safe.add(addMedicintSubmit);
		jPanel2.add(safe);

	}

	private void eisaiLayout() {
		// 声明控件
		JLabel timeLabel = new JLabel("时间：");
		JTextField year = new JTextField();
		JLabel yearLabel = new JLabel("年");
		JTextField mouth = new JTextField();
		JLabel mouthLabel = new JLabel("月");
		JTextField day = new JTextField();
		JLabel dayLabel = new JLabel("日");
		JButton addMedicintSubmit = new JButton("查找");

		// 添加控件
		eisai.setLayout(null);

		timeLabel.setBounds(100, 20, 80, 25);
		year.setBounds(180 ,20 , 80 , 25);
		yearLabel.setBounds(260, 20, 15, 25);
		mouth.setBounds(275 , 20,80,25);
		mouthLabel.setBounds(355, 20, 15, 25);
		day.setBounds(370,20,80,25);
		dayLabel.setBounds(450, 20, 15, 25);

		addMedicintSubmit.setBounds(440, 60, 90, 25);
		addMedicintSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"共计" + num + "人","统计人数",JOptionPane.WARNING_MESSAGE);
			}
		});

		eisai.add(timeLabel);
		eisai.add(year);
		eisai.add(yearLabel);
		eisai.add(mouth);
		eisai.add(mouthLabel);
		eisai.add(day);
		eisai.add(dayLabel);
		eisai.add(addMedicintSubmit);
		jPanel2.add(eisai);
	}

	private void financeLayout() {
		// 声明控件
		JLabel timeLabel = new JLabel("时间：");
		JTextField year = new JTextField();
		JLabel yearLabel = new JLabel("年");
		JTextField mouth = new JTextField();
		JLabel mouthLabel = new JLabel("月");
		JTextField day = new JTextField();
		JLabel dayLabel = new JLabel("日");
		JButton addMedicintSubmit = new JButton("查找");

		// 添加控件
		finance.setLayout(null);

		timeLabel.setBounds(100, 20, 80, 25);
		year.setBounds(180 ,20 , 80 , 25);
		yearLabel.setBounds(260, 20, 15, 25);
		mouth.setBounds(275 , 20,80,25);
		mouthLabel.setBounds(355, 20, 15, 25);
		day.setBounds(370,20,80,25);
		dayLabel.setBounds(450, 20, 15, 25);

		addMedicintSubmit.setBounds(440, 60, 90, 25);
		addMedicintSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"共计" + num + "人","统计人数",JOptionPane.WARNING_MESSAGE);
			}
		});
		finance.add(timeLabel);
		finance.add(year);
		finance.add(yearLabel);
		finance.add(mouth);
		finance.add(mouthLabel);
		finance.add(day);
		finance.add(dayLabel);
		finance.add(addMedicintSubmit);
		jPanel2.add(finance);

	}

	private void nurseWorkLayout() {
		// 声明控件
		JLabel timeLabel = new JLabel("时间：");
		JTextField year = new JTextField();
		JLabel yearLabel = new JLabel("年");
		JTextField mouth = new JTextField();
		JLabel mouthLabel = new JLabel("月");
		JTextField day = new JTextField();
		JLabel dayLabel = new JLabel("日");
		JButton addMedicintSubmit = new JButton("查找");

		// 添加控件
		nurseWork.setLayout(null);

		timeLabel.setBounds(100, 20, 80, 25);
		year.setBounds(180 ,20 , 80 , 25);
		yearLabel.setBounds(260, 20, 15, 25);
		mouth.setBounds(275 , 20,80,25);
		mouthLabel.setBounds(355, 20, 15, 25);
		day.setBounds(370,20,80,25);
		dayLabel.setBounds(450, 20, 15, 25);

		addMedicintSubmit.setBounds(440, 60, 90, 25);
		addMedicintSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"共计" + num + "人","统计人数",JOptionPane.WARNING_MESSAGE);
			}
		});
		nurseWork.add(timeLabel);
		nurseWork.add(year);
		nurseWork.add(yearLabel);
		nurseWork.add(mouth);
		nurseWork.add(mouthLabel);
		nurseWork.add(day);
		nurseWork.add(dayLabel);
		nurseWork.add(addMedicintSubmit);
		jPanel2.add(nurseWork);

	}

	private void doctorWorkLayout() {
		// 声明控件
		JLabel timeLabel = new JLabel("时间：");
		JTextField year = new JTextField();
		JLabel yearLabel = new JLabel("年");
		JTextField mouth = new JTextField();
		JLabel mouthLabel = new JLabel("月");
		JTextField day = new JTextField();
		JLabel dayLabel = new JLabel("日");
		JButton addMedicintSubmit = new JButton("查找");

		// 添加控件
		doctorWork.setLayout(null);

		timeLabel.setBounds(100, 20, 80, 25);
		year.setBounds(180 ,20 , 80 , 25);
		yearLabel.setBounds(260, 20, 15, 25);
		mouth.setBounds(275 , 20,80,25);
		mouthLabel.setBounds(355, 20, 15, 25);
		day.setBounds(370,20,80,25);
		dayLabel.setBounds(450, 20, 15, 25);

		addMedicintSubmit.setBounds(440, 60, 90, 25);

		addMedicintSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"共计" + num + "人","统计人数",JOptionPane.WARNING_MESSAGE);
			}
		});
		doctorWork.add(timeLabel);
		doctorWork.add(year);
		doctorWork.add(yearLabel);
		doctorWork.add(mouth);
		doctorWork.add(mouthLabel);
		doctorWork.add(day);
		doctorWork.add(dayLabel);
		doctorWork.add(addMedicintSubmit);
		jPanel2.add(doctorWork);


	}



	public static void main(String[] args) {
		new SystemFrame().init();
	}
}
