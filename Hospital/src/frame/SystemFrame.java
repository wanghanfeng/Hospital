package frame;

import action.*;
import content.StatueContent;
import model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

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

	private DictionaryAction dictionaryAction;

	private String oldValue = "";

	private String kind;

	List<Prescription> prescriptions;

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

		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getColumn() < 0)
					return;
				String nVal = table.getValueAt(e.getLastRow(), e.getColumn())
						.toString();
				// 如果旧的值 和新的值一样，直接 返回
				if (nVal.equals(oldValue)) {
					return;
				}
				System.out.println(oldValue);
				// 判断当前编辑的单元格是否是主键列
				if (e.getColumn() == 0) {
					// 还原旧的值
					table.setValueAt(oldValue, e.getLastRow(), e.getColumn());
					return;
				}

				switch (kind) {
					case "doctor":updateDoctor(e);break;
					case "nurse":updateNurse(e);break;
					case "finance":updateFinance(e);break;
					case "eisai":updateEisai(e); break;
					case "safe": updateSafe(e);break;
				}
			}
		});

		jFrame.setVisible(true);
	}

	public void del() {
		if (table.getSelectedRowCount() <= 0) {
			JOptionPane.showMessageDialog(null, "请选择要删除的数据行");
			return;
		}
		int result = JOptionPane.showConfirmDialog(null, "是否确定要删除");
		// 判断用户是否点击
		if (result == JOptionPane.OK_OPTION) {
			int userid = Integer.valueOf(table.getValueAt(
					table.getSelectedRow(), 0).toString());
			//userDao.delete(userid);
			//loadData();
		}
	}

	private void updateDoctor(TableModelEvent e) {
		Prescription prescription = new Prescription();
		prescription.setPre_id(Integer.parseInt(String.valueOf(model.getValueAt( e.getLastRow(),0))));
		prescription.setTime(String.valueOf(model.getValueAt( e.getLastRow(),1)));
		prescription.setDoctor(String.valueOf(model.getValueAt( e.getLastRow(),2)));
		prescription.setName(String.valueOf(model.getValueAt( e.getLastRow(),3)));
		prescription.setInstitutions(String.valueOf(model.getValueAt( e.getLastRow(),4)));
		prescription.setDrug(String.valueOf(model.getValueAt( e.getLastRow(),5)));
		prescription.setCode(String.valueOf(model.getValueAt( e.getLastRow(),6)));
		prescription.setAmount(Integer.parseInt(String.valueOf(model.getValueAt( e.getLastRow(),7))));
		prescription.setHz(String.valueOf(model.getValueAt( e.getLastRow(),8)));
		prescription.setDay(Integer.parseInt(String.valueOf(model.getValueAt( e.getLastRow(),9))));
		prescription.setNote(String.valueOf(model.getValueAt( e.getLastRow(),10)));
		prescription.setChronicDisease(String.valueOf(model.getValueAt( e.getLastRow(),11)));

		PrescriptionAction prescriptionAction = new PrescriptionAction();
		int i = prescriptionAction.updatePrescription(prescription);

		if (i==1){
			JOptionPane.showMessageDialog(null,"修改成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null,"修改失败","错误窗口",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateNurse(TableModelEvent e) {
		NurseRecords nurseRecords = new NurseRecords();
		nurseRecords.setN_id(Integer.parseInt(String.valueOf(model.getValueAt( e.getLastRow(),0))));
		nurseRecords.setThetime(String.valueOf(model.getValueAt( e.getLastRow(),1)));
		nurseRecords.setPatient(String.valueOf(model.getValueAt( e.getLastRow(),2)));
		nurseRecords.setThecode(String.valueOf(model.getValueAt( e.getLastRow(),3)));
		nurseRecords.setSex(String.valueOf(model.getValueAt( e.getLastRow(),4)));
		nurseRecords.setAge(Integer.parseInt(String.valueOf(model.getValueAt( e.getLastRow(),5))));
		nurseRecords.setIns(String.valueOf(model.getValueAt( e.getLastRow(),6)));
		nurseRecords.setDrug(String.valueOf(model.getValueAt( e.getLastRow(),7)));
		nurseRecords.setNote(String.valueOf(model.getValueAt( e.getLastRow(),8)));
		nurseRecords.setNurse(String.valueOf(model.getValueAt( e.getLastRow(),9)));

		NurseRecordsAction nurseRecordsAction = new NurseRecordsAction();

		int i = nurseRecordsAction.updatePrescription(nurseRecords);

		if (i==1){
			JOptionPane.showMessageDialog(null,"修改成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null,"修改失败","错误窗口",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateFinance(TableModelEvent e) {
		ImportDevice importDevice = new ImportDevice();
		importDevice.setI_id(Integer.parseInt(String.valueOf(model.getValueAt( e.getLastRow(),0))));
		importDevice.setName(String.valueOf(model.getValueAt( e.getLastRow(),1)));
		importDevice.setApprove(String.valueOf(model.getValueAt( e.getLastRow(),2)));
		importDevice.setProducer(String.valueOf(model.getValueAt( e.getLastRow(),3)));
		importDevice.setType(String.valueOf(model.getValueAt( e.getLastRow(),4)));
		importDevice.setValue(String.valueOf(model.getValueAt( e.getLastRow(),5)));
		importDevice.setPurchasing(String.valueOf(model.getValueAt( e.getLastRow(),6)));

		ImportDeviceAction importDeviceAction = new ImportDeviceAction();
		int i = importDeviceAction.updateImportDevice(importDevice);
		if (i==1){
			JOptionPane.showMessageDialog(null,"修改成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null,"修改失败","错误窗口",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateEisai(TableModelEvent e) {
		Drugs drugs = new Drugs();
		drugs.setDrugcode(String.valueOf(model.getValueAt( e.getLastRow(),0)));
		drugs.setThetime(String.valueOf(model.getValueAt( e.getLastRow(),1)));
		drugs.setSort(String.valueOf(model.getValueAt( e.getLastRow(),2)));
		drugs.setDrug(String.valueOf(model.getValueAt( e.getLastRow(),3)));
		drugs.setChe(String.valueOf(model.getValueAt( e.getLastRow(),4)));
		drugs.setPackages(String.valueOf(model.getValueAt( e.getLastRow(),5)));
		drugs.setStandard(String.valueOf(model.getValueAt( e.getLastRow(),6)));
		drugs.setPlace(String.valueOf(model.getValueAt( e.getLastRow(),7)));
		drugs.setBuysale(Double.parseDouble(String.valueOf(model.getValueAt( e.getLastRow(),8))));
		drugs.setThesale(Double.parseDouble(String.valueOf(model.getValueAt( e.getLastRow(),9))));
		drugs.setCompany(String.valueOf(model.getValueAt( e.getLastRow(),10)));
		drugs.setLose(String.valueOf(model.getValueAt( e.getLastRow(),11)));
		drugs.setAmount(Integer.parseInt(String.valueOf(model.getValueAt( e.getLastRow(),12))));
		drugs.setUser(String.valueOf(model.getValueAt( e.getLastRow(),13)));

		DrugsAction drugsAction = new DrugsAction();
		int i = drugsAction.updateALLDrugs(drugs);
		if (i==1){
			JOptionPane.showMessageDialog(null,"修改成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null,"修改失败","错误窗口",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateSafe(TableModelEvent e) {
		HealthScreen healthScreen = new HealthScreen();
		healthScreen.setHs_id(Integer.parseInt(String.valueOf(model.getValueAt( e.getLastRow(),0))));
		healthScreen.setTime(String.valueOf(model.getValueAt( e.getLastRow(),1)));
		healthScreen.setCompany(String.valueOf(model.getValueAt( e.getLastRow(),2)));
		healthScreen.setType(String.valueOf(model.getValueAt( e.getLastRow(),3)));
		healthScreen.setDie(Integer.parseInt(String.valueOf(model.getValueAt( e.getLastRow(),4))));
		healthScreen.setEpidemic(String.valueOf(model.getValueAt( e.getLastRow(),5)));
		healthScreen.setName(String.valueOf(model.getValueAt( e.getLastRow(),6)));

		HealthScreenAction healthScreenAction = new HealthScreenAction();
		int i = healthScreenAction.updateHealthScreen(healthScreen);
		if (i==1){
			JOptionPane.showMessageDialog(null,"修改成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null,"修改失败","错误窗口",JOptionPane.ERROR_MESSAGE);
		}
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

		addDoctorSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = addDoctor.getText();
				String theType = "doctor";
				if (theName.isEmpty()||theType.isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.WARNING_MESSAGE);
					return;
				}
				dictionaryAction = new DictionaryAction();
				List<Dictionary> dictionaryList = dictionaryAction.getDictionaryByInf(theType);
				if(dictionaryList != null) {
					for(Dictionary d:dictionaryList) {
						if(theName.equals(d.getName())) {
							JOptionPane.showMessageDialog(null,"该医生已存在","错误窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}
				Dictionary dictionary = new Dictionary();
				dictionary.setName(theName);
				dictionary.setType(theType);
				int i = dictionaryAction.addDictionary(dictionary);
				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}
				addDoctor.setText("");
			}
		});

		addNurseSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = addNurse.getText();
				String theType = "nurse";
				if (theName.isEmpty()||theType.isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.WARNING_MESSAGE);
					return;
				}
				dictionaryAction = new DictionaryAction();
				List<Dictionary> dictionaryList = dictionaryAction.getDictionaryByInf(theType);
				for(Dictionary d:dictionaryList) {
					if(theName.equals(d.getName())) {
						JOptionPane.showMessageDialog(null,"该护士已存在","错误窗口",JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				Dictionary dictionary = new Dictionary();
				dictionary.setName(theName);
				dictionary.setType(theType);
				int i = dictionaryAction.addDictionary(dictionary);
				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}
				addNurse.setText("");
			}
		});

		addHospitalSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = addHospital.getText();
				String theType = "hospital";
				if (theName.isEmpty()||theType.isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.WARNING_MESSAGE);
					return;
				}
				dictionaryAction = new DictionaryAction();
				List<Dictionary> dictionaryList = dictionaryAction.getDictionaryByInf(theType);
				for(Dictionary d:dictionaryList) {
					if(theName.equals(d.getName())) {
						JOptionPane.showMessageDialog(null,"该医院已存在","错误窗口",JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				Dictionary dictionary = new Dictionary();
				dictionary.setName(theName);
				dictionary.setType(theType);
				int i = dictionaryAction.addDictionary(dictionary);
				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}
				addHospital.setText("");
			}
		});

		addKindSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = addkind.getText();
				String theType = "kind";
				if (theName.isEmpty()||theType.isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.WARNING_MESSAGE);
					return;
				}
				dictionaryAction = new DictionaryAction();
				List<Dictionary> dictionaryList = dictionaryAction.getDictionaryByInf(theType);
				for(Dictionary d:dictionaryList) {
					if(theName.equals(d.getName())) {
						JOptionPane.showMessageDialog(null,"该药品种类已存在","错误窗口",JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				Dictionary dictionary = new Dictionary();
				dictionary.setName(theName);
				dictionary.setType(theType);
				int i = dictionaryAction.addDictionary(dictionary);
				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}
				addkind.setText("");
			}
		});

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

	private void safeLayout() {
		// 声明控件
		JLabel startTimeLabel = new JLabel("开始时间：");
		JTextField startYear = new JTextField();
		JLabel startYearLabel = new JLabel("年");
		JTextField startMouth = new JTextField();
		JLabel startMouthLabel = new JLabel("月");
		JTextField startDay = new JTextField();
		JLabel startDayLabel = new JLabel("日");

		JLabel endTimeLabel = new JLabel("结束时间：");
		JTextField endYear = new JTextField();
		JLabel endYearLabel = new JLabel("年");
		JTextField endMouth = new JTextField();
		JLabel endMouthLabel = new JLabel("月");
		JTextField endDay = new JTextField();
		JLabel endDayLabel = new JLabel("日");
		JButton addMedicintSubmit = new JButton("查找");
		JButton updateSafeSubmit = new JButton("修改");
		JButton deleteSafeSubmit = new JButton("删除");
		// 添加控件
		safe.setLayout(null);

		startTimeLabel.setBounds(100, 20, 80, 25);
		startYear.setBounds(180 ,20 , 80 , 25);
		startYearLabel.setBounds(260, 20, 15, 25);
		startMouth.setBounds(275 , 20,80,25);
		startMouthLabel.setBounds(355, 20, 15, 25);
		startDay.setBounds(370,20,80,25);
		startDayLabel.setBounds(450, 20, 15, 25);

		endTimeLabel.setBounds(100, 60, 80, 25);
		endYear.setBounds(180 ,60 , 80 , 25);
		endYearLabel.setBounds(260, 60, 15, 25);
		endMouth.setBounds(275 , 60,80,25);
		endMouthLabel.setBounds(355, 60, 15, 25);
		endDay.setBounds(370,60,80,25);
		endDayLabel.setBounds(450, 60, 15, 25);

		addMedicintSubmit.setBounds(500, 60, 90, 25);
		addMedicintSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String startTime = startYear.getText() + "-" + startMouth.getText() + "-" + startDay.getText();
				String endTime = endYear.getText() + "-" + endMouth.getText() + "-" + endDay.getText();

				HealthScreenAction healthScreenAction = new HealthScreenAction();
				List<HealthScreen> healthScreens = healthScreenAction.getHealthScreenByTime(startTime , endTime);

				model.setColumnIdentifiers(StatueContent.safeColname3);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				for (HealthScreen healthScreen1 : healthScreens) {
					model.addRow(new String[]{
							String.valueOf(healthScreen1.getHs_id()),
							healthScreen1.getTime().toString(),
							healthScreen1.getCompany(),
							healthScreen1.getType(),
							healthScreen1.getDie()+"",
							healthScreen1.getEpidemic(),
							healthScreen1.getName()
					});
				}
				updateSafeSubmit.setVisible(true);
				deleteSafeSubmit.setVisible(true);
				JOptionPane.showMessageDialog(null,"共计" + healthScreens.size() + "条","统计",JOptionPane.WARNING_MESSAGE);
			}
		});
		updateSafeSubmit.setBounds(600 , 60 , 90 ,25);
		updateSafeSubmit.setVisible(false);
		deleteSafeSubmit.setBounds(600 , 20 , 90 ,25);
		deleteSafeSubmit.setVisible(false);
		updateSafeSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(updateSafeSubmit.getText().equals("修改")) {
					table.setEnabled(true);
					kind = "safe";
					updateSafeSubmit.setText("关闭修改");
				}else if(updateSafeSubmit.getText().equals("关闭修改")) {
					table.setEnabled(false);
					updateSafeSubmit.setText("修改");

				}
			}
		});
		deleteSafeSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() <= 0) {
					JOptionPane.showMessageDialog(null, "请选择要删除的数据行");
					return;
				}
				int result = JOptionPane.showConfirmDialog(null, "是否确定要删除");
				// 判断用户是否点击
				if (result == JOptionPane.OK_OPTION) {
					int user_id = Integer.parseInt(table.getValueAt(
							table.getSelectedRow(), 0).toString());
					//userDao.delete(userid);
					//loadData();
					String startTime = startYear.getText() + "-" + startMouth.getText() + "-" + startDay.getText();
					String endTime = endYear.getText() + "-" + endMouth.getText() + "-" + endDay.getText();
					HealthScreenAction healthScreenAction = new HealthScreenAction();

					HealthScreen healthScreen = new HealthScreen();
					healthScreen.setHs_id(user_id);
					healthScreenAction.deleteHealthScreen(healthScreen);
					List<HealthScreen> healthScreens = healthScreenAction.getHealthScreenByTime(startTime , endTime);

					model.setColumnIdentifiers(StatueContent.safeColname3);

					//清空
					while(model.getRowCount()>0){
						model.removeRow(model.getRowCount()-1);
					}

					for (HealthScreen healthScreen1 : healthScreens) {
						model.addRow(new String[]{
								String.valueOf(healthScreen1.getHs_id()),
								healthScreen1.getTime().toString(),
								healthScreen1.getCompany(),
								healthScreen1.getType(),
								healthScreen1.getDie()+"",
								healthScreen1.getEpidemic(),
								healthScreen1.getName()
						});
					}
					JOptionPane.showMessageDialog(null,"共计" + healthScreens.size() + "条","统计",JOptionPane.WARNING_MESSAGE);

				}
			}
		});
		safe.add(startTimeLabel);
		safe.add(startYear);
		safe.add(startYearLabel);
		safe.add(startMouth);
		safe.add(startMouthLabel);
		safe.add(startDay);
		safe.add(startDayLabel);
		safe.add(endTimeLabel);
		safe.add(endYear);
		safe.add(endYearLabel);
		safe.add(endMouthLabel);
		safe.add(endMouth);
		safe.add(endDayLabel);
		safe.add(endDay);
		safe.add(addMedicintSubmit);
		safe.add(updateSafeSubmit);
		safe.add(deleteSafeSubmit);
		safe.setVisible(false);
		jPanel2.add(safe);

	}

	private void eisaiLayout() {
		// 声明控件
		JLabel startTimeLabel = new JLabel("开始时间：");
		JTextField startYear = new JTextField();
		JLabel startYearLabel = new JLabel("年");
		JTextField startMouth = new JTextField();
		JLabel startMouthLabel = new JLabel("月");
		JTextField startDay = new JTextField();
		JLabel startDayLabel = new JLabel("日");

		JLabel endTimeLabel = new JLabel("结束时间：");
		JTextField endYear = new JTextField();
		JLabel endYearLabel = new JLabel("年");
		JTextField endMouth = new JTextField();
		JLabel endMouthLabel = new JLabel("月");
		JTextField endDay = new JTextField();
		JLabel endDayLabel = new JLabel("日");
		JButton addMedicintSubmit = new JButton("查找");
		JButton updateEisaiSubmit = new JButton("修改");
		JButton deleteEisaiSubmit = new JButton("删除");
		// 添加控件
		eisai.setLayout(null);

		startTimeLabel.setBounds(100, 20, 80, 25);
		startYear.setBounds(180 ,20 , 80 , 25);
		startYearLabel.setBounds(260, 20, 15, 25);
		startMouth.setBounds(275 , 20,80,25);
		startMouthLabel.setBounds(355, 20, 15, 25);
		startDay.setBounds(370,20,80,25);
		startDayLabel.setBounds(450, 20, 15, 25);

		endTimeLabel.setBounds(100, 60, 80, 25);
		endYear.setBounds(180 ,60 , 80 , 25);
		endYearLabel.setBounds(260, 60, 15, 25);
		endMouth.setBounds(275 , 60,80,25);
		endMouthLabel.setBounds(355, 60, 15, 25);
		endDay.setBounds(370,60,80,25);
		endDayLabel.setBounds(450, 60, 15, 25);

		addMedicintSubmit.setBounds(500, 60, 90, 25);
		addMedicintSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String startTime = startYear.getText() + "-" + startMouth.getText() + "-" + startDay.getText();
				String endTime = endYear.getText() + "-" + endMouth.getText() + "-" + endDay.getText();

				DrugsAction drugsAction = new DrugsAction();
				List<Drugs> drugses = drugsAction.getDrugsByTime(startTime , endTime);

				model.setColumnIdentifiers(StatueContent.eisaiManagementColname1);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				for (Drugs drugs1 : drugses) {
					model.addRow(new String[]{
							drugs1.getDrugcode(),
							drugs1.getThetime().toString(),
							drugs1.getSort(),
							drugs1.getDrug(),
							drugs1.getChe(),
							drugs1.getPackages(),
							drugs1.getStandard(),
							drugs1.getPlace(),
							drugs1.getBuysale()+"",
							drugs1.getThesale()+"",
							drugs1.getCompany(),
							drugs1.getLose(),
							drugs1.getAmount()+"",
							drugs1.getUser()
					});
				}
				updateEisaiSubmit.setVisible(true);
				deleteEisaiSubmit.setVisible(true);
				JOptionPane.showMessageDialog(null,"共计" + drugses.size() + "条","统计",JOptionPane.WARNING_MESSAGE);
			}
		});

		updateEisaiSubmit.setBounds(600 , 60 , 90 ,25);
		updateEisaiSubmit.setVisible(false);
		deleteEisaiSubmit.setBounds(600 , 20 , 90 ,25);
		deleteEisaiSubmit.setVisible(false);
		updateEisaiSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(updateEisaiSubmit.getText().equals("修改")) {
					table.setEnabled(true);
					kind = "eisai";
					updateEisaiSubmit.setText("关闭修改");
				}else if(updateEisaiSubmit.getText().equals("关闭修改")) {
					table.setEnabled(false);
					updateEisaiSubmit.setText("修改");
				}
			}
		});
		deleteEisaiSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() <= 0) {
					JOptionPane.showMessageDialog(null, "请选择要删除的数据行");
					return;
				}
				int result = JOptionPane.showConfirmDialog(null, "是否确定要删除");
				// 判断用户是否点击
				if (result == JOptionPane.OK_OPTION) {
					String code = table.getValueAt(
							table.getSelectedRow(), 0).toString();
					//userDao.delete(userid);
					//loadData();
					String startTime = startYear.getText() + "-" + startMouth.getText() + "-" + startDay.getText();
					String endTime = endYear.getText() + "-" + endMouth.getText() + "-" + endDay.getText();

					DrugsAction drugsAction = new DrugsAction();
					Drugs drugs = new Drugs();
					drugs.setDrugcode(code);
					drugsAction.deleteDrugs(drugs);
					List<Drugs> drugses = drugsAction.getDrugsByTime(startTime , endTime);

					model.setColumnIdentifiers(StatueContent.eisaiManagementColname1);

					//清空
					while(model.getRowCount()>0){
						model.removeRow(model.getRowCount()-1);
					}

					for (Drugs drugs1 : drugses) {
						model.addRow(new String[]{
								drugs1.getDrugcode(),
								drugs1.getThetime().toString(),
								drugs1.getSort(),
								drugs1.getDrug(),
								drugs1.getChe(),
								drugs1.getPackages(),
								drugs1.getStandard(),
								drugs1.getPlace(),
								drugs1.getBuysale()+"",
								drugs1.getThesale()+"",
								drugs1.getCompany(),
								drugs1.getLose(),
								drugs1.getAmount()+"",
								drugs1.getUser()
						});
					}
					JOptionPane.showMessageDialog(null,"共计" + drugses.size() + "条","统计",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		eisai.add(startTimeLabel);
		eisai.add(startYear);
		eisai.add(startYearLabel);
		eisai.add(startMouth);
		eisai.add(startMouthLabel);
		eisai.add(startDay);
		eisai.add(startDayLabel);
		eisai.add(endTimeLabel);
		eisai.add(endYear);
		eisai.add(endYearLabel);
		eisai.add(endMouthLabel);
		eisai.add(endMouth);
		eisai.add(endDayLabel);
		eisai.add(endDay);
		eisai.add(addMedicintSubmit);
		eisai.add(updateEisaiSubmit);
		eisai.add(deleteEisaiSubmit);
		eisai.setVisible(false);
		jPanel2.add(eisai);
	}

	private void financeLayout() {
		// 声明控件
		JLabel startTimeLabel = new JLabel("开始时间：");
		JTextField startYear = new JTextField();
		JLabel startYearLabel = new JLabel("年");
		JTextField startMouth = new JTextField();
		JLabel startMouthLabel = new JLabel("月");
		JTextField startDay = new JTextField();
		JLabel startDayLabel = new JLabel("日");

		JLabel endTimeLabel = new JLabel("结束时间：");
		JTextField endYear = new JTextField();
		JLabel endYearLabel = new JLabel("年");
		JTextField endMouth = new JTextField();
		JLabel endMouthLabel = new JLabel("月");
		JTextField endDay = new JTextField();
		JLabel endDayLabel = new JLabel("日");
		JButton addMedicintSubmit = new JButton("查找");
		JButton updateFinanceSubmit = new JButton("修改");
		JButton deleteFinanceSubmit = new JButton("删除");
		// 添加控件
		finance.setLayout(null);

		startTimeLabel.setBounds(100, 20, 80, 25);
		startYear.setBounds(180 ,20 , 80 , 25);
		startYearLabel.setBounds(260, 20, 15, 25);
		startMouth.setBounds(275 , 20,80,25);
		startMouthLabel.setBounds(355, 20, 15, 25);
		startDay.setBounds(370,20,80,25);
		startDayLabel.setBounds(450, 20, 15, 25);

		endTimeLabel.setBounds(100, 60, 80, 25);
		endYear.setBounds(180 ,60 , 80 , 25);
		endYearLabel.setBounds(260, 60, 15, 25);
		endMouth.setBounds(275 , 60,80,25);
		endMouthLabel.setBounds(355, 60, 15, 25);
		endDay.setBounds(370,60,80,25);
		endDayLabel.setBounds(450, 60, 15, 25);

		addMedicintSubmit.setBounds(500, 60, 90, 25);
		addMedicintSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String startTime = startYear.getText() + "-" + startMouth.getText() + "-" + startDay.getText();
				String endTime = endYear.getText() + "-" + endMouth.getText() + "-" + endDay.getText();

				ImportDeviceAction importDeviceAction = new ImportDeviceAction();
				List<ImportDevice> importDevices = importDeviceAction.getImportDeviceByTime(startTime , endTime);

				model.setColumnIdentifiers(StatueContent.importDevice1);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				for (ImportDevice im:importDevices) {
					model.addRow(new String[]{
							String.valueOf(im.getI_id()),
							im.getTime(),
							im.getName(),
							im.getProducer(),
							im.getType(),
							im.getValue(),
							im.getPurchasing(),
							im.getApprove()
					});
				}

				updateFinanceSubmit.setVisible(true);
				deleteFinanceSubmit.setVisible(true);
				JOptionPane.showMessageDialog(null,"共计" + importDevices.size() + "条","统计",JOptionPane.WARNING_MESSAGE);
			}
		});
		updateFinanceSubmit.setBounds(600 , 60 , 90 ,25);
		updateFinanceSubmit.setVisible(false);
		deleteFinanceSubmit.setBounds(600 , 20 , 90 ,25);
		deleteFinanceSubmit.setVisible(false);
		updateFinanceSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(updateFinanceSubmit.getText().equals("修改")) {
					table.setEnabled(true);
					kind = "finance";
					updateFinanceSubmit.setText("关闭修改");
				}else if(updateFinanceSubmit.getText().equals("关闭修改")) {
					table.setEnabled(false);
					updateFinanceSubmit.setText("修改");
				}
			}
		});
		deleteFinanceSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() <= 0) {
					JOptionPane.showMessageDialog(null, "请选择要删除的数据行");
					return;
				}
				int result = JOptionPane.showConfirmDialog(null, "是否确定要删除");
				// 判断用户是否点击
				if (result == JOptionPane.OK_OPTION) {
					int userid = Integer.valueOf(table.getValueAt(
							table.getSelectedRow(), 0).toString());
					//userDao.delete(userid);
					//loadData();
					String startTime = startYear.getText() + "-" + startMouth.getText() + "-" + startDay.getText();
					String endTime = endYear.getText() + "-" + endMouth.getText() + "-" + endDay.getText();

					ImportDeviceAction importDeviceAction = new ImportDeviceAction();
					ImportDevice importDevice = new ImportDevice();
					importDevice.setI_id(userid);
					importDeviceAction.deleteImportDevice(importDevice);
					List<ImportDevice> importDevices = importDeviceAction.getImportDeviceByTime(startTime, endTime);

					model.setColumnIdentifiers(StatueContent.importDevice1);

					//清空
					while (model.getRowCount() > 0) {
						model.removeRow(model.getRowCount() - 1);
					}

					for (ImportDevice im : importDevices) {
						model.addRow(new String[]{
								String.valueOf(im.getI_id()),
								im.getTime(),
								im.getName(),
								im.getProducer(),
								im.getType(),
								im.getValue(),
								im.getPurchasing(),
								im.getApprove()
						});
					}

					JOptionPane.showMessageDialog(null, "共计" + importDevices.size() + "条", "统计", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		finance.add(startTimeLabel);
		finance.add(startYear);
		finance.add(startYearLabel);
		finance.add(startMouth);
		finance.add(startMouthLabel);
		finance.add(startDay);
		finance.add(startDayLabel);
		finance.add(endTimeLabel);
		finance.add(endYear);
		finance.add(endYearLabel);
		finance.add(endMouthLabel);
		finance.add(endMouth);
		finance.add(endDayLabel);
		finance.add(endDay);
		finance.add(addMedicintSubmit);
		finance.add(updateFinanceSubmit);
		finance.add(deleteFinanceSubmit);
		finance.setVisible(false);
		jPanel2.add(finance);

	}

	private void nurseWorkLayout() {
		// 声明控件
		JLabel startTimeLabel = new JLabel("开始时间：");
		JTextField startYear = new JTextField();
		JLabel startYearLabel = new JLabel("年");
		JTextField startMouth = new JTextField();
		JLabel startMouthLabel = new JLabel("月");
		JTextField startDay = new JTextField();
		JLabel startDayLabel = new JLabel("日");

		JLabel endTimeLabel = new JLabel("结束时间：");
		JTextField endYear = new JTextField();
		JLabel endYearLabel = new JLabel("年");
		JTextField endMouth = new JTextField();
		JLabel endMouthLabel = new JLabel("月");
		JTextField endDay = new JTextField();
		JLabel endDayLabel = new JLabel("日");
		JButton addMedicintSubmit = new JButton("查找");
		JButton updateNurseSubmit = new JButton("修改");
		JButton deleteNurseSubmit = new JButton("删除");
		// 添加控件
		nurseWork.setLayout(null);

		startTimeLabel.setBounds(100, 20, 80, 25);
		startYear.setBounds(180 ,20 , 80 , 25);
		startYearLabel.setBounds(260, 20, 15, 25);
		startMouth.setBounds(275 , 20,80,25);
		startMouthLabel.setBounds(355, 20, 15, 25);
		startDay.setBounds(370,20,80,25);
		startDayLabel.setBounds(450, 20, 15, 25);

		endTimeLabel.setBounds(100, 60, 80, 25);
		endYear.setBounds(180 ,60 , 80 , 25);
		endYearLabel.setBounds(260, 60, 15, 25);
		endMouth.setBounds(275 , 60,80,25);
		endMouthLabel.setBounds(355, 60, 15, 25);
		endDay.setBounds(370,60,80,25);
		endDayLabel.setBounds(450, 60, 15, 25);

		addMedicintSubmit.setBounds(500, 60, 90, 25);
		addMedicintSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String startTime = startYear.getText() + "-" + startMouth.getText() + "-" + startDay.getText();
				String endTime = endYear.getText() + "-" + endMouth.getText() + "-" + endDay.getText();

				NurseRecordsAction nurseRecordsAction = new NurseRecordsAction();
				List<NurseRecords> nurseRecordses = nurseRecordsAction.getNurseRecordsByTime(startTime , endTime);

				model.setColumnIdentifiers(StatueContent.nurseColname11);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				for (NurseRecords nurseRecord : nurseRecordses) {
					model.addRow(new String[]{
							String.valueOf(nurseRecord.getN_id()),
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
				deleteNurseSubmit.setVisible(true);
				updateNurseSubmit.setVisible(true);
				JOptionPane.showMessageDialog(null,"共计" + nurseRecordses.size() + "条","统计",JOptionPane.WARNING_MESSAGE);
			}
		});
		updateNurseSubmit.setBounds(600 , 60 , 90 ,25);
		updateNurseSubmit.setVisible(false);
		deleteNurseSubmit.setBounds(600 , 20 , 90 , 25);
		deleteNurseSubmit.setVisible(false);
		updateNurseSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(updateNurseSubmit.getText().equals("修改")) {
					table.setEnabled(true);
					kind = "nurse";
					updateNurseSubmit.setText("关闭修改");
				}else if(updateNurseSubmit.getText().equals("关闭修改")) {
					table.setEnabled(false);
					updateNurseSubmit.setText("修改");

				}


			}
		});

		deleteNurseSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() <= 0) {
					JOptionPane.showMessageDialog(null, "请选择要删除的数据行");
					return;
				}
				int result = JOptionPane.showConfirmDialog(null, "是否确定要删除");
				// 判断用户是否点击
				if (result == JOptionPane.OK_OPTION) {
					int userid = Integer.valueOf(table.getValueAt(
							table.getSelectedRow(), 0).toString());
					//userDao.delete(userid);
					//loadData();
					String startTime = startYear.getText() + "-" + startMouth.getText() + "-" + startDay.getText();
					String endTime = endYear.getText() + "-" + endMouth.getText() + "-" + endDay.getText();

					NurseRecordsAction nurseRecordsAction = new NurseRecordsAction();
					NurseRecords nurseRecords = new NurseRecords();
					nurseRecords.setN_id(userid);
					nurseRecordsAction.deleteNurseRecords(nurseRecords);
					List<NurseRecords> nurseRecordses = nurseRecordsAction.getNurseRecordsByTime(startTime , endTime);

					model.setColumnIdentifiers(StatueContent.nurseColname11);

					//清空
					while(model.getRowCount()>0){
						model.removeRow(model.getRowCount()-1);
					}

					for (NurseRecords nurseRecord : nurseRecordses) {
						model.addRow(new String[]{
								String.valueOf(nurseRecord.getN_id()),
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

					updateNurseSubmit.setVisible(true);
					JOptionPane.showMessageDialog(null,"共计" + nurseRecordses.size() + "条","统计",JOptionPane.WARNING_MESSAGE);
				}
			}
		});


		nurseWork.add(startTimeLabel);
		nurseWork.add(startYear);
		nurseWork.add(startYearLabel);
		nurseWork.add(startMouth);
		nurseWork.add(startMouthLabel);
		nurseWork.add(startDay);
		nurseWork.add(startDayLabel);
		nurseWork.add(endTimeLabel);
		nurseWork.add(endYear);
		nurseWork.add(endYearLabel);
		nurseWork.add(endMouthLabel);
		nurseWork.add(endMouth);
		nurseWork.add(endDayLabel);
		nurseWork.add(endDay);
		nurseWork.add(addMedicintSubmit);
		nurseWork.add(updateNurseSubmit);
		nurseWork.add(deleteNurseSubmit);
		nurseWork.setVisible(false);
		jPanel2.add(nurseWork);

	}

	private void doctorWorkLayout() {
		// 声明控件
		JLabel startTimeLabel = new JLabel("开始时间：");
		JTextField startYear = new JTextField();
		JLabel startYearLabel = new JLabel("年");
		JTextField startMouth = new JTextField();
		JLabel startMouthLabel = new JLabel("月");
		JTextField startDay = new JTextField();
		JLabel startDayLabel = new JLabel("日");

		JLabel endTimeLabel = new JLabel("结束时间：");
		JTextField endYear = new JTextField();
		JLabel endYearLabel = new JLabel("年");
		JTextField endMouth = new JTextField();
		JLabel endMouthLabel = new JLabel("月");
		JTextField endDay = new JTextField();
		JLabel endDayLabel = new JLabel("日");
		JButton addMedicintSubmit = new JButton("查找");
		JButton updateDoctorSubmit = new JButton("修改");
		JButton deleteDoctorSubmit = new JButton("删除");

		// 添加控件
		doctorWork.setLayout(null);

		startTimeLabel.setBounds(100, 20, 80, 25);
		startYear.setBounds(180 ,20 , 80 , 25);
		startYearLabel.setBounds(260, 20, 15, 25);
		startMouth.setBounds(275 , 20,80,25);
		startMouthLabel.setBounds(355, 20, 15, 25);
		startDay.setBounds(370,20,80,25);
		startDayLabel.setBounds(450, 20, 15, 25);

		endTimeLabel.setBounds(100, 60, 80, 25);
		endYear.setBounds(180 ,60 , 80 , 25);
		endYearLabel.setBounds(260, 60, 15, 25);
		endMouth.setBounds(275 , 60,80,25);
		endMouthLabel.setBounds(355, 60, 15, 25);
		endDay.setBounds(370,60,80,25);
		endDayLabel.setBounds(450, 60, 15, 25);

		addMedicintSubmit.setBounds(500, 60, 90, 25);
		addMedicintSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String startTime = startYear.getText() + "-" + startMouth.getText() + "-" + startDay.getText();
				String endTime = endYear.getText() + "-" + endMouth.getText() + "-" + endDay.getText();

				PrescriptionAction prescriptionAction = new PrescriptionAction();
				prescriptions = prescriptionAction.getPrescriptionByTime(startTime , endTime);

				model.setColumnIdentifiers(StatueContent.doctorcolname22);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				for (Prescription prescription : prescriptions) {
					model.addRow(new String[]{
							String.valueOf(prescription.getPre_id()),
							prescription.getTime().toString(),
							prescription.getDoctor(),
							prescription.getName(),
							prescription.getInstitutions(),
							prescription.getDrug(),
							prescription.getCode(),
							prescription.getAmount()+"",
							prescription.getHz(),
							prescription.getDay()+"",
							prescription.getNote(),
							prescription.getChronicDisease()
					});
				}


				JOptionPane.showMessageDialog(null,"共计" + prescriptions.size() + "条","统计",JOptionPane.WARNING_MESSAGE);
				updateDoctorSubmit.setVisible(true);
				deleteDoctorSubmit.setVisible(true);
			}
		});

		updateDoctorSubmit.setBounds(600 , 60 , 90 ,25);
		updateDoctorSubmit.setVisible(false);
		deleteDoctorSubmit.setBounds(600 , 20 , 90 ,25);
		deleteDoctorSubmit.setVisible(false);
		updateDoctorSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(updateDoctorSubmit.getText().equals("修改")) {
					kind = "doctor";
					table.setEnabled(true);
					updateDoctorSubmit.setText("关闭修改");
				}else if(updateDoctorSubmit.getText().equals("关闭修改")) {
					table.setEnabled(false);
					updateDoctorSubmit.setText("修改");
				}
			}
		});

		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int row = ((JTable) e.getSource()).rowAtPoint(e
							.getPoint());
					int col = ((JTable) e.getSource())
							.columnAtPoint(e.getPoint());
					String cellValue = (String) (table
							.getValueAt(row, col));
					oldValue = cellValue;

				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		deleteDoctorSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() <= 0) {
					JOptionPane.showMessageDialog(null, "请选择要删除的数据行");
					return;
				}
				int result = JOptionPane.showConfirmDialog(null, "是否确定要删除");
				// 判断用户是否点击
				if (result == JOptionPane.OK_OPTION) {
					int userid = Integer.valueOf(table.getValueAt(
							table.getSelectedRow(), 0).toString());
					//userDao.delete(userid);
					//loadData();
					PrescriptionAction prescriptionAction = new PrescriptionAction();
					Prescription prescription = new Prescription();
					prescription.setPre_id(userid);
					prescriptionAction.deletePrescription(prescription);

					String startTime = startYear.getText() + "-" + startMouth.getText() + "-" + startDay.getText();
					String endTime = endYear.getText() + "-" + endMouth.getText() + "-" + endDay.getText();

					prescriptions = prescriptionAction.getPrescriptionByTime(startTime , endTime);

					model.setColumnIdentifiers(StatueContent.doctorcolname22);

					//清空
					while(model.getRowCount()>0){
						model.removeRow(model.getRowCount()-1);
					}

					for (Prescription p : prescriptions) {
						model.addRow(new String[]{
								String.valueOf(prescription.getPre_id()),
								p.getTime().toString(),
								p.getDoctor(),
								p.getName(),
								p.getInstitutions(),
								p.getDrug(),
								p.getCode(),
								p.getAmount()+"",
								p.getHz(),
								p.getDay()+"",
								p.getNote(),
								p.getChronicDisease()
						});
					}

					JOptionPane.showMessageDialog(null,"共计" + prescriptions.size() + "条","统计",JOptionPane.WARNING_MESSAGE);
				}
			}
		});



		doctorWork.add(startTimeLabel);
		doctorWork.add(startYear);
		doctorWork.add(startYearLabel);
		doctorWork.add(startMouth);
		doctorWork.add(startMouthLabel);
		doctorWork.add(startDay);
		doctorWork.add(startDayLabel);
		doctorWork.add(endTimeLabel);
		doctorWork.add(endYear);
		doctorWork.add(endYearLabel);
		doctorWork.add(endMouthLabel);
		doctorWork.add(endMouth);
		doctorWork.add(endDayLabel);
		doctorWork.add(endDay);
		doctorWork.add(addMedicintSubmit);
		doctorWork.add(updateDoctorSubmit);
		doctorWork.add(deleteDoctorSubmit);
		doctorWork.setVisible(false);
		jPanel2.add(doctorWork);


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
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(35);
		table.setEnabled(false);
		tablePanel.setVisible(false);
	}

	public static void main(String[] args) {
		new SystemFrame().init();
	}
}
