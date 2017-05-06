package frame;

import action.NurseRecordsAction;
import action.PatientInformationAction;
import content.StatueContent;
import model.NurseRecords;
import model.PatientInformation;

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

public class NurseFrame {
	NurseRecordsAction nurseRecordsAction;
	private JFrame jFrame;
	
	//菜单栏组件
	private JPanel jPanel1;
    private JMenuBar jMenuBar;
    private JMenu[] jMenus = {new JMenu("护理记录"),new JMenu("过敏病人"),new JMenu("隔离记录"), new JMenu("退出")};
    private JMenuItem j1 = new JMenuItem("信息录入");
    private JMenuItem j2 = new JMenuItem("信息查询");
	private JMenuItem j3 = new JMenuItem("信息录入");
	private JMenuItem j4 = new JMenuItem("信息查询");
	private JMenuItem j5 = new JMenuItem("信息录入");
	private JMenuItem j6 = new JMenuItem("信息查询");
    private JMenuItem j7 = new JMenuItem("注销登陆");
    
    
    private JPanel jPanel2;
    private JPanel jPanel3; 
    private JPanel addInfo;
    private JPanel selectInfo;

    //表格组件
    private String[][] datas = {};
    public DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane5;
    
    //动态组件
    private JTextField show;
    private JScrollPane showPane;

	public NurseFrame() {
	}

	public NurseFrame(DefaultTableModel model) {
		this.model = model;
	}

	public void init() {

		// 设置页面基本属性
		jFrame = new JFrame(StatueContent.kind[1]);
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
		addPatientLayout();

		// 添加单击事件
		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddNurseInfo(new NurseFrame(model));
			}
		});
		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addInfo.setVisible(true);
				selectInfo.setVisible(false);
			}
		});

		j3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddAllergyPatient();
			}
		});

		j4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				selectInfo.setVisible(true);
				addInfo.setVisible(false);
			}
		});

		j5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddQuarantine();
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
				jFrame.dispose();
				new Login().init();
			}
		});

		jFrame.setVisible(true);
	}
	
	// 病人信息录用模块
	private void addPatientLayout() {
		nurseRecordsAction = new NurseRecordsAction();
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
		JButton addInfoSubmit = new JButton("查找");
		
		//添加控件
		addInfo.setLayout(null);
		nameLabel.setBounds(100, 20, 40, 25);
		name.setBounds(140, 20, 100, 25);
		sexLabel.setBounds(100, 60, 40, 25);
		man.setBounds(140, 60, 50, 25);
		woman.setBounds(190, 60, 50, 25);
		unitLabel.setBounds(360, 20, 40, 25);
		unit.setBounds(400, 20, 200, 25);
		addInfoSubmit.setBounds(400, 60, 90, 25);
		addInfo.add(nameLabel);
		addInfo.add(name);
		addInfo.add(sexLabel);
		addInfo.add(man);		
		addInfo.add(woman);		
		addInfo.add(unitLabel);		
		addInfo.add(unit);		
		addInfo.add(addInfoSubmit);
		addInfo.setVisible(false);
		jPanel2.add(addInfo);

		addInfoSubmit.addActionListener(new ActionListener() {
			//查询 三个 性别必有的 患者姓名 单位
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = name.getText();

				String theSex = "";
				if (man.isSelected()){
					theSex = man.getText();
				}else if (woman.isSelected()){
					theSex = woman.getText();
				}

				String theUnit = unit.getText();


				List<NurseRecords> list = nurseRecordsAction.getNurseRecordsByInf(theName,theSex,theUnit);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				//查数据库，展示所有病人
				for (NurseRecords nurseRecord : list) {
					model.addRow(new String[]{
							nurseRecord.getThetime().toString(),
							nurseRecord.getPatient(),
							nurseRecord.getSex(),
							nurseRecord.getIns(),
							nurseRecord.getAge()+"",
							nurseRecord.getDrug(),
							nurseRecord.getThecode(),
							nurseRecord.getNote(),
							nurseRecord.getNurse()
					});
				}


			}

		});

	}

	//过敏病人查询模块
	private void addAllergyPatient() {
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
		JButton addInfoSubmit = new JButton("查找");

		//添加控件
		selectInfo.setLayout(null);
		nameLabel.setBounds(100, 20, 40, 25);
		name.setBounds(140, 20, 100, 25);
		sexLabel.setBounds(100, 60, 40, 25);
		man.setBounds(140, 60, 50, 25);
		woman.setBounds(190, 60, 50, 25);
		unitLabel.setBounds(360, 20, 40, 25);
		unit.setBounds(400, 20, 200, 25);
		addInfoSubmit.setBounds(400, 60, 90, 25);
		selectInfo.add(nameLabel);
		selectInfo.add(name);
		selectInfo.add(sexLabel);
		selectInfo.add(man);
		selectInfo.add(woman);
		selectInfo.add(unitLabel);
		selectInfo.add(unit);
		selectInfo.add(addInfoSubmit);
		selectInfo.setVisible(false);
		jPanel2.add(selectInfo);

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
		model = new DefaultTableModel(datas, StatueContent.nurseColname);
		table = new JTable(model);
		scrollPane5 = new JScrollPane(table);
		table.getTableHeader().setResizingAllowed(false);
		tablePanel.add(scrollPane5, BorderLayout.CENTER);
		table.setRowHeight(35);
		table.setEnabled(false);

		nurseRecordsAction = new NurseRecordsAction();

		//清空
		while(model.getRowCount()>0){
			model.removeRow(model.getRowCount()-1);
		}

		//查数据库，展示所有病人
		List<NurseRecords> nurseRecordsList = nurseRecordsAction.getAllNurseRecords();
		for (NurseRecords nurseRecord : nurseRecordsList) {
			model.addRow(new String[]{
					nurseRecord.getThetime().toString(),
					nurseRecord.getPatient(),
					nurseRecord.getSex(),
					nurseRecord.getIns(),
					nurseRecord.getAge()+"",
					nurseRecord.getDrug(),
					nurseRecord.getThecode(),
					nurseRecord.getNote(),
					nurseRecord.getNurse()
			});
		}

	}

	public static void main(String[] args) {
		new NurseFrame().init();
	}
}

class AddNurseInfo {
	NurseRecordsAction nurseRecordsAction;
	private JFrame jFrame = new JFrame("护理信息录入");
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);

	private JLabel nameLabel = new JLabel("患者姓名：");
	private JLabel unitLabel = new JLabel("患者单位：");
	
	private JTextField useday = new JTextField();
	private JTextField pinlv = new JTextField();
	
	private JLabel medicineLabel = new JLabel("用药名称：");
	private JTextField medicine = new JTextField();

	private JLabel idLabel = new JLabel("音位码：");
	private JTextField id = new JTextField();
	
	private JLabel ageLabel = new JLabel("年龄：");
	private JTextField age = new JTextField();

	private JTextField year = new JTextField();
	private JTextField mouth = new JTextField();
	private JTextField day = new JTextField();
	private JTextField name = new JTextField();
	private JTextField usenum = new JTextField();
	private JTextField unit = new JTextField();
	
	private JLabel noteLabel = new JLabel("不良反应：");
	private JTextField note = new JTextField();

	private JLabel nurseLabel = new JLabel("值班护士：");
	private JComboBox nurse = new JComboBox<>();


	
	private JLabel sexLabel = new JLabel("性别：");
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JRadioButton man = new JRadioButton("男");
	private JRadioButton woman = new JRadioButton("女");
	
	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddNurseInfo(NurseFrame nurseFrame) {
		jFrame.setSize(450, 400);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		// 第一行
//		time.setBounds(30, 20, 80, 25);
//		year.setBounds(100, 20, 80, 25);
//		yearLabel.setBounds(180, 20, 15, 25);
//		mouth.setBounds(195, 20, 80, 25);
//		mouthLabel.setBounds(275, 20, 15, 25);
//		day.setBounds(290, 20, 80, 25);
//		dayLabel.setBounds(370, 20, 15, 25);

		nameLabel.setBounds(30, 60, 70, 25);
		name.setBounds(100, 60, 90, 25);
		idLabel.setBounds(220, 60, 80, 25);
		id.setBounds(300, 60, 80, 25);

		sexLabel.setBounds(30, 100, 80, 25);
		man.setBounds(100, 100, 50, 25);
		woman.setBounds(150, 100, 50, 25);
		buttonGroup1.add(man);
		buttonGroup1.add(woman);
		ageLabel.setBounds(210, 100, 50, 25);
		age.setBounds(260, 100, 80, 25);

		unitLabel.setBounds(30, 140, 80, 25);
		unit.setBounds(100, 140, 150, 25);

		medicineLabel.setBounds(30, 180, 80, 25);
		medicine.setBounds(100, 180, 150, 25);

		noteLabel.setBounds(30, 220, 80, 25);
		note.setBounds(100, 220, 150, 25);

		nurseLabel.setBounds(30, 260, 80, 25);
		for(int i = 0;i < 5; i++) {
			nurse.addItem(i);
		}
		nurse.setBounds(100, 260, 80, 25);

		submit.setBounds(70, 300, 90, 25);
		cancel.setBounds(170, 300, 90, 25);

//		jFrame.add(time);
//		jFrame.add(yearLabel);
//		jFrame.add(mouthLabel);
//		jFrame.add(dayLabel);
		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(unitLabel);
		jFrame.add(year);
		jFrame.add(mouth);
		jFrame.add(day);
		jFrame.add(unit);
		jFrame.add(submit);
		jFrame.add(cancel);
		jFrame.add(medicine);
		jFrame.add(usenum);
		jFrame.add(medicineLabel);
		jFrame.add(idLabel);
		jFrame.add(pinlv);
		jFrame.add(useday);
		jFrame.add(sexLabel);
		jFrame.add(man);
		jFrame.add(woman);
		jFrame.add(id);
		jFrame.add(noteLabel);
		jFrame.add(note);
		jFrame.add(nurseLabel);
		jFrame.add(ageLabel);
		jFrame.add(age);
		jFrame.add(nurse);

		man.doClick();

		submit.addActionListener(new ActionListener() {
			//添加护理信息
			@Override
			public void actionPerformed(ActionEvent e) {
				nurseRecordsAction = new NurseRecordsAction();
				//添加处方
//				String theYear = year.getText();
//				String theMonth = mouth.getText();
//				String theDay = day.getText();
//				int yearInt;
//				int monthInt;
//				int dayInt;

				String theDate = nowTime;
				String theName = name.getText();
				String theCode = id.getText();

				String theSex = "";
				if (man.isSelected()){
					theSex = man.getText();
				}else if (woman.isSelected()){
					theSex = woman.getText();
				}

				String theAge = age.getText();
				int ageInt;
				String theIns = unit.getText();
				String theDrug = medicine.getText();
				String theNote = note.getText();
				String theNurse = nurse.getSelectedItem().toString();

				//验证空值
				if (theName.trim().isEmpty() &&
						theAge.trim().isEmpty() && theIns.trim().isEmpty() &&
						theNote.trim().isEmpty() && theNurse.trim().isEmpty()&&
						theCode.trim().isEmpty()){
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

				NurseRecords nurseRecords = new NurseRecords();
				nurseRecords.setThetime(theDate);
				nurseRecords.setPatient(theName);
				nurseRecords.setThecode(theCode);
				nurseRecords.setSex(theSex);
				nurseRecords.setAge(ageInt);
				nurseRecords.setIns(theIns);
				nurseRecords.setDrug(theDrug);
				nurseRecords.setNote(theNote);
				nurseRecords.setNurse(theNurse);

				int i = nurseRecordsAction.addNurseRecords(nurseRecords);

				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(nurseFrame.model.getRowCount()>0){
					nurseFrame.model.removeRow(nurseFrame.model.getRowCount()-1);
				}

				//查数据库，展示所有病人
				List<NurseRecords> nurseRecordsList = nurseRecordsAction.getAllNurseRecords();
				for (NurseRecords nurseRecord : nurseRecordsList) {
					nurseFrame.model.addRow(new String[]{
							nurseRecord.getThetime().toString(),
							nurseRecord.getPatient(),
							nurseRecord.getSex(),
							nurseRecord.getIns(),
							nurseRecord.getAge()+"",
							nurseRecord.getDrug(),
							nurseRecord.getThecode(),
							nurseRecord.getNote(),
							nurseRecord.getNurse()
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


class AddAllergyPatient {
	private JFrame jFrame = new JFrame("过敏病人录入");



	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);

	private JLabel nameLabel = new JLabel("姓名：");
	private JTextField name = new JTextField();

	private JLabel unitLabel = new JLabel("单位：");
	private JTextField unit = new JTextField();

	private JLabel sexLabel = new JLabel("性别：");
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JRadioButton man = new JRadioButton("男");
	private JRadioButton woman = new JRadioButton("女");

	private JLabel medicineLabel = new JLabel("过敏药物：");
	private JTextField medicine = new JTextField();

	private JLabel historyLabel = new JLabel("过敏史：");
	private JTextField history = new JTextField();

	private JLabel guardianLabel = new JLabel("监护人：");
	private JTextField guardian = new JTextField();

	private JLabel stateLabel = new JLabel("病情说明");
	private JTextArea state = new JTextArea();

	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddAllergyPatient() {
		jFrame.setSize(380, 375);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		nameLabel.setBounds(30, 20, 80, 25);
		name.setBounds(100, 20, 90, 25);
		unitLabel.setBounds(200, 20, 40, 25);
		unit.setBounds(240, 20, 90, 25);

		sexLabel.setBounds(30, 60, 40, 25);
		man.setBounds(100,60,50,25);
		woman.setBounds(150,60,50,25);
		buttonGroup1.add(man);
		buttonGroup1.add(woman);

		medicineLabel.setBounds(30, 100, 80, 25);
		medicine.setBounds(100,100,90,25);

		historyLabel.setBounds(30, 140, 80, 25);
		history.setBounds(100,140,150,25);

		guardianLabel.setBounds(30, 180, 80, 25);
		guardian.setBounds(100,180,90,25);

		stateLabel.setBounds(30 , 220 , 80 , 25);
		state.setBounds(100 , 220 , 220 , 70);
		state.setLineWrap(true);

		submit.setBounds(100, 305, 90, 25);
		cancel.setBounds(200, 305, 90, 25);

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jFrame.dispose();
			}
		});

		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(unitLabel);
		jFrame.add(unit);
		jFrame.add(sexLabel);
		jFrame.add(man);
		jFrame.add(woman);
		jFrame.add(medicineLabel);
		jFrame.add(medicine);
		jFrame.add(historyLabel);
		jFrame.add(history);
		jFrame.add(guardianLabel);
		jFrame.add(guardian);
		jFrame.add(stateLabel);
		jFrame.add(state);
		jFrame.add(submit);
		jFrame.add(cancel);

		jFrame.setVisible(true);
	}

}

class AddQuarantine {
	private JFrame jFrame = new JFrame("隔离记录录入");
	private PatientInformationAction patientInformationAction;
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);

	private JLabel nameLabel = new JLabel("姓名：");
	private JComboBox name = new JComboBox<>();

	private JLabel unitLabel = new JLabel("单位：");
	private JTextField unit = new JTextField();

	private JLabel sexLabel = new JLabel("性别：");
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JRadioButton man = new JRadioButton("男");
	private JRadioButton woman = new JRadioButton("女");

	private JLabel daysLabel = new JLabel("隔离天数：");
	private JTextField days = new JTextField();

	private JLabel reasonLabel = new JLabel("隔离原因：");
	private JTextArea reason = new JTextArea();

	private JLabel approveLabel = new JLabel("审批人");
	private JTextField approve = new JTextField();

	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddQuarantine() {
		jFrame.setSize(380, 320);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		nameLabel.setBounds(30, 20, 80, 25);
		name.setBounds(100, 20, 90, 25);
		patientInformationAction = new PatientInformationAction();
		List<PatientInformation> patientInformations = patientInformationAction.getAllPatient();
		for(PatientInformation p:patientInformations) {
			name.addItem(p.getName());
		}

		unitLabel.setBounds(200, 20, 40, 25);
		unit.setBounds(240, 20, 90, 25);

		sexLabel.setBounds(30, 60, 40, 25);
		man.setBounds(100,60,50,25);
		woman.setBounds(150,60,50,25);
		buttonGroup1.add(man);
		buttonGroup1.add(woman);

		daysLabel.setBounds(30 , 100 , 80 , 25);
		days.setBounds(100 , 100 , 90 , 25);
		approveLabel.setBounds(200, 100, 40, 25);
		approve.setBounds(240 , 100 , 80 , 25);

		reasonLabel.setBounds(30 , 140 , 80 , 25);
		reason.setBounds(100 , 140 , 220 , 70);
		reason.setLineWrap(true);

		submit.setBounds(100, 225, 90, 25);
		cancel.setBounds(200, 225, 90, 25);

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jFrame.dispose();
			}
		});

		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(unitLabel);
		jFrame.add(unit);
		jFrame.add(sexLabel);
		jFrame.add(man);
		jFrame.add(woman);
		jFrame.add(daysLabel);
		jFrame.add(days);
		jFrame.add(approveLabel);
		jFrame.add(approve);
		jFrame.add(reasonLabel);
		jFrame.add(reason);
		jFrame.add(submit);
		jFrame.add(cancel);
		jFrame.setVisible(true);
	}
}






