package frame;

import action.DrugsAction;
import action.PatientInformationAction;
import action.PrescriptionAction;
import content.StatueContent;
import model.Drugs;
import model.PatientInformation;
import model.Prescription;
import utils.FileUploader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.tools.ant.taskdefs.GenerateKey.DnameParam;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DoctorFrame {

	private JFrame jFrame;
	PatientInformationAction patientInformationAction;
	PrescriptionAction prescriptionAction;

	//菜单栏组件
	private JPanel jPanel1;
    private JMenuBar jMenuBar;
    private JMenu[] jMenus = {new JMenu("病人信息") , new JMenu("药品处方") , new JMenu("退出")};
    private JMenuItem j1 = new JMenuItem("病人信息录入");
    private JMenuItem j2 = new JMenuItem("病人信息查询");
    private JMenuItem j3 = new JMenuItem("处方信息录入");
    private JMenuItem j4 = new JMenuItem("处方信息查询");
    private JMenuItem j5 = new JMenuItem("注销登陆");
    
    
    private JPanel jPanel2;
    private JPanel jPanel3; 
    private JPanel addPatient;
    private JPanel addMedicint;
    
    //表格组件
    public String[][] datas = {};
    public DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane5;
    
    //动态组件
    private JTextField show;
    private JScrollPane showPane;

	public DoctorFrame() {
	}

	public DoctorFrame(DefaultTableModel model) {
		this.model = model;
	}

	//初始化界面
	public void init() {

		// 设置页面基本属性
		jFrame = new JFrame(StatueContent.kind[0]);
		jFrame.setSize(StatueContent.main_width, StatueContent.main_height);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jFrame.setLayout(new BorderLayout());
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new Login().init();
			}
		});

		// 初始化菜单栏
		jPanel1 = new JPanel();
		jFrame.add(jPanel1, BorderLayout.NORTH);
		layoutPanel1();

		// 初始化内容
		jPanel2 = new JPanel();
		jFrame.add(jPanel2, BorderLayout.CENTER);
		layoutPanel2();

		// 初始化动态组件
		addPatient = new JPanel();
		addPatient.setBounds(0, 0, StatueContent.main_width, 100);
		addPatientLayout(new DoctorFrame(model));
		
		addMedicint = new JPanel();
		addMedicint.setBounds(0, 0, StatueContent.main_width, 100);
		addMedicintLayout(new DoctorFrame(model));



		// 添加单击事件
		j1.addActionListener(new ActionListener() {
			//添加病人
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPatient(new DoctorFrame(model));
			}
		});
		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addPatient.setVisible(true);
				addMedicint.setVisible(false);
			}
		});

		j3.addActionListener(new ActionListener() {
			//添加处方
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddMedicine(new DoctorFrame(model));
			}
		});

		j4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel3.setVisible(false);
				addPatient.setVisible(false);
				addMedicint.setVisible(true);
			}
		});
		j5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
				new Login().init();
			}
		});

		jFrame.setVisible(true);
	}



	
	// 病人信息录用模块
	private void addPatientLayout(DoctorFrame doctorFrame) {
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
		addPatient.setLayout(null);
		nameLabel.setBounds(100, 20, 40, 25);
		name.setBounds(140, 20, 100, 25);
		sexLabel.setBounds(100, 60, 40, 25);
		man.setBounds(140, 60, 50, 25);
		woman.setBounds(190, 60, 50, 25);
		unitLabel.setBounds(360, 20, 40, 25);
		unit.setBounds(400, 20, 200, 25);
		addPatientSubmit.setBounds(400, 60, 90, 25);
		addPatient.add(nameLabel);
		addPatient.add(name);
		addPatient.add(sexLabel);
		addPatient.add(man);		
		addPatient.add(woman);		
		addPatient.add(unitLabel);		
		addPatient.add(unit);		
		addPatient.add(addPatientSubmit);
		addPatient.setVisible(false);
		jPanel2.add(addPatient);

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

				List<PatientInformation> list = patientInformationAction.getPatientsByInf(theName,theSex,theUnit);

				doctorFrame.model.setColumnIdentifiers(StatueContent.doctorcolname1);

				//清空
				while(doctorFrame.model.getRowCount()>0){
					doctorFrame.model.removeRow(doctorFrame.model.getRowCount()-1);
				}

				//展示选择的病人
				for (PatientInformation patient : list) {
					doctorFrame.model.addRow(new String[]{
							patient.getTime().toString(),
							patient.getName(),
							patient.getSex(),
							patient.getInstitutions(),
							patient.getSituation(),
							patient.getDoctor(),
							patient.getDetailUrl()
					});
				}



			}
		});

	}

	// 药品处方录用模块
	private void addMedicintLayout(DoctorFrame doctorFrame) {
		// 声明控件
		JLabel nameLabel = new JLabel("患者名称：");
		JTextField name = new JTextField();
		JLabel unitLabel = new JLabel("患者单位：");
		JTextField unit = new JTextField();
		JButton addMedicintSubmit = new JButton("查找");

		// 添加控件
		addMedicint.setLayout(null);
		nameLabel.setBounds(100, 20, 80, 25);
		name.setBounds(180, 20, 100, 25);
		unitLabel.setBounds(440, 20, 80, 25);
		unit.setBounds(520, 20, 200, 25);
		addMedicintSubmit.setBounds(440, 60, 90, 25);
		addMedicint.add(nameLabel);
		addMedicint.add(name);
		addMedicint.add(unitLabel);
		addMedicint.add(unit);
		addMedicint.add(addMedicintSubmit);
		addMedicint.setVisible(false);
		jPanel2.add(addMedicint);

		addMedicintSubmit.addActionListener(new ActionListener() {
			//查找处方
			@Override
			public void actionPerformed(ActionEvent e) {
				prescriptionAction = new PrescriptionAction();
				String theName = name.getText().trim();
				String theUnit = unit.getText().trim();

				List<Prescription> list = prescriptionAction.getPrescriptionByInf(theName,theUnit);

				//清空
				while(doctorFrame.model.getRowCount()>0){
					doctorFrame.model.removeRow(doctorFrame.model.getRowCount()-1);
				}

				//换标题
				doctorFrame.model.setColumnIdentifiers(StatueContent.doctorcolname2);

				//显示查到的处方
				for (Prescription prescriptions : list) {
					doctorFrame.model.addRow(new String[]{
							prescriptions.getTime().toString(),
							prescriptions.getDoctor(),
							prescriptions.getName(),
							prescriptions.getInstitutions(),
							prescriptions.getDrug(),
							prescriptions.getCode(),
							prescriptions.getAmount()+"",
							prescriptions.getHz(),
							prescriptions.getDay()+"",
							prescriptions.getNote(),
							prescriptions.getChronicDisease()
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
		model = new DefaultTableModel(datas, StatueContent.doctorcolname1);

		table = new JTable(model);
		scrollPane5 = new JScrollPane(table);
		table.getTableHeader().setResizingAllowed(false);
		tablePanel.add(scrollPane5, BorderLayout.CENTER);
		table.setRowHeight(35);

		//表单不可更改！
		table.setEnabled(false);

		patientInformationAction = new PatientInformationAction();

		//清空
		while(model.getRowCount()>0){
			model.removeRow(model.getRowCount()-1);
		}

		//查数据库，展示所有病人
		List<PatientInformation> patientInformationList = patientInformationAction.getAllPatient();
		for (PatientInformation patient : patientInformationList) {
			model.addRow(new String[]{
					patient.getTime().toString(),
					patient.getName(),
					patient.getSex(),
					patient.getInstitutions(),
					patient.getSituation(),
					patient.getDoctor(),
					patient.getDetailUrl()
			});
		}
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = ((JTable) e.getSource()).rowAtPoint(e  
                            .getPoint());  
                    int col = ((JTable) e.getSource())  
                            .columnAtPoint(e.getPoint());  
                    String cellValue = (String) (table  
                            .getValueAt(row, col));
                    System.out.println(cellValue);
                    //展示图片 默认大小 可拖拽放大或缩小
                    if (col == 6) {
                    	PictureFrame r = new PictureFrame(500, 500);
                        r.loadURLImage(cellValue,r.getGraphics(),((JFrame)r));
					}
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

	}
}

class AddPatient{
	PatientInformationAction patientInformationAction;
	private JFrame jFrame = new JFrame("添加病人");
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);
	
	private String pictureURl;
	
	private JLabel time = new JLabel("时间：");
	private JLabel unitLabel = new JLabel("单位：");
	private JLabel ageLabel = new JLabel("年龄：");
	private JLabel contentLabel = new JLabel("病情：");
	private JLabel doctorLabel = new JLabel("医生：");
	private JLabel pictureLabel = new JLabel("图影资料:");
	
	private JTextField year = new JTextField();
	private JTextField mouth = new JTextField();
	private JTextField day = new JTextField();
	private JLabel nameLabel = new JLabel("姓名：");
	private JTextField name = new JTextField();
	private JLabel sexLabel = new JLabel("性别：");
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JRadioButton man = new JRadioButton("男");
	private JRadioButton woman = new JRadioButton("女");
	private JTextField unit = new JTextField();
	private JTextField age = new JTextField();
	private ButtonGroup buttonGroup2 = new ButtonGroup();
	private JRadioButton chuzhen = new JRadioButton("初诊");
	private JRadioButton fuzhen = new JRadioButton("复诊");
	private JTextArea content = new JTextArea();
	private JComboBox doctor = new JComboBox<>();
	private JTextField pictureTF = new JTextField();
	private JButton selectBtn = new JButton("选择图片");
	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddPatient(DoctorFrame doctorFrame) {
		jFrame.setTitle("病人信息录入");
		jFrame.setSize(550, 540);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		//第一行

		nameLabel.setBounds(30,60,40,25);
		name.setBounds(70,60,150,25);
		
		sexLabel.setBounds(30, 90, 40, 25);
		man.setBounds(70,90,50,25);
		woman.setBounds(120,90,50,25);
		buttonGroup1.add(man);
		buttonGroup1.add(woman);
		
		unitLabel.setBounds(30, 130, 40, 25);
		unit.setBounds(70,130,150,25);
		
		ageLabel.setBounds(30, 170, 40, 25);
		age.setBounds(70,170,80,25);
		chuzhen.setBounds(180,170,80,25);
		fuzhen.setBounds(260,170,80,25);
		buttonGroup2.add(chuzhen);
		buttonGroup2.add(fuzhen);
		
		doctorLabel.setBounds(30, 210, 40, 25);
		for(int i = 0;i < 5; i++) {
			doctor.addItem(i);
		}
		doctor.setBounds(70 , 210 , 150,25);
		
		contentLabel.setBounds(30, 250, 40, 25);
		content.setBounds(70,250,310,70);
		
		pictureLabel.setBounds(30, 360, 80, 25);
		pictureTF.setBounds(110, 360, 310, 25 );
		selectBtn.setBounds(440, 360, 80, 25);
		pictureTF.setText("请将图片拖拽至此窗口上传");
		
		submit.setBounds(70 , 475 , 90 , 25);
		cancel.setBounds(170 , 475 , 90 , 25);
		
//		jFrame.add(time);
//		jFrame.add(yearLabel);
//		jFrame.add(mouthLabel);
//		jFrame.add(dayLabel);
		jFrame.add(unitLabel);
		jFrame.add(ageLabel);
		jFrame.add(contentLabel);
		jFrame.add(doctorLabel);
		jFrame.add(year);
		jFrame.add(mouth);
		jFrame.add(day);
		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(sexLabel);
		jFrame.add(man);
		jFrame.add(woman);
		jFrame.add(age);
		jFrame.add(unit);
		jFrame.add(chuzhen);
		jFrame.add(fuzhen);
		jFrame.add(content);
		jFrame.add(doctor);
		jFrame.add(pictureLabel);
		jFrame.add(pictureTF);
//		jFrame.add(selectBtn);
		jFrame.add(submit);
		jFrame.add(cancel);

		man.doClick();
		chuzhen.doClick();
		
		new DropTarget(jFrame, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter()
        {
            @Override
            public void drop(DropTargetDropEvent dtde)//重写适配器的drop方法
            {
                try
                {
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))//如果拖入的文件格式受支持
                    {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//接收拖拽来的数据
                        List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                        pictureURl = list.get(0).getAbsolutePath();
                        
//                        JOptionPane.showMessageDialog(null, pictureURl);
                        pictureTF.setText(pictureURl);
                        dtde.dropComplete(true);//指示拖拽操作已完成
                        new FileUploader(pictureURl);
                    }
                    else
                    {
                        dtde.rejectDrop();//否则拒绝拖拽来的数据
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

		submit.addActionListener(new ActionListener() {
			//添加病人
			@Override
			public void actionPerformed(ActionEvent e) {

//				String theYear = year.getText();
//				String theMonth = mouth.getText();
//				String theDay = day.getText();

				String theName = name.getText();
				String theSex = "";
				if (man.isSelected()){
					theSex = man.getText();
				}else if (woman.isSelected()){
					theSex = woman.getText();
				}

				String theInstitutions = unit.getText();
				String theAge = age.getText();
				String theFlag="";
				int ageInt;
//				int yearInt;
//				int monthInt;
//				int dayInt;

				if (chuzhen.isSelected()){
					theFlag = chuzhen.getText();
				}else if (fuzhen.isSelected()){
					theFlag = fuzhen.getText();
				}

				String theDoctor = doctor.getSelectedItem().toString();
				String theContent = content.getText();

				String theDate = nowTime;
				//验证空值
				if (theName.trim().isEmpty() && theInstitutions.trim().isEmpty() && theAge.trim().isEmpty() && theDoctor.trim().isEmpty() && theContent.trim().isEmpty()){
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
				
				//获取图片在网络上的url地址
				String detail_url = parseToURL(pictureTF.getText());
				System.out.println(detail_url);

				PatientInformation patientInformation = new PatientInformation();
				patientInformation.setName(theName);
				patientInformation.setAge(ageInt);
				patientInformation.setSex(theSex);
				patientInformation.setInstitutions(theInstitutions);
				patientInformation.setTime(theDate);
				patientInformation.setFlag(theFlag);
				patientInformation.setDoctor(theDoctor);
				patientInformation.setSituation(theContent);
				patientInformation.setDetailUrl(detail_url);

				patientInformationAction = new PatientInformationAction();
				int i = patientInformationAction.addPatientInformation(patientInformation);
				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(doctorFrame.model.getRowCount()>0){
					doctorFrame.model.removeRow(doctorFrame.model.getRowCount()-1);
				}

				doctorFrame.model.setColumnIdentifiers(StatueContent.doctorcolname1);

				//查数据库，展示所有病人
				List<PatientInformation> patientInformationList = patientInformationAction.getAllPatient();
				for (PatientInformation patient : patientInformationList) {
					doctorFrame.model.addRow(new String[]{
							patient.getTime().toString(),
							patient.getName(),
							patient.getSex(),
							patient.getInstitutions(),
							patient.getSituation(),
							patient.getDoctor(),
							patient.getDetailUrl()
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
	public String parseToURL(String filePath) {
		if (filePath.length() == 0) {
			return "";
		}
		String[] paraArr = filePath.split("/");
		if (paraArr.length == 1) {
			paraArr = filePath.split("\\");
		}
		String url = StatueContent.server_schem + paraArr[paraArr.length - 1];
		return url;
	}
}

class AddMedicine{
	PrescriptionAction prescriptionAction;
	DrugsAction drugsAction = new DrugsAction();
	PatientInformationAction patientInformationAction = new PatientInformationAction();

	private JFrame jFrame = new JFrame("处方信息录入");

	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);
	
	private JLabel nameLabel = new JLabel("患者姓名：");
	private JComboBox name = new JComboBox<>();

	private JLabel medicineLabel = new JLabel("药品名称：");
	private JComboBox medicine = new JComboBox<>();

	private JLabel unitLabel = new JLabel("患者单位：");
	private JLabel numLabel = new JLabel("数量：");
	private JLabel usenumLabel = new JLabel("用量：");
	
	private JLabel usedayLabel = new JLabel("用药天数：");
	private JLabel pinlvLabel = new JLabel("用药频率：");
	
	private JTextField useday = new JTextField();
	private JTextField pinlv = new JTextField();

	private JLabel idLabel = new JLabel("音位码：");
	
	private JTextField year = new JTextField();
	private JTextField mouth = new JTextField();
	private JTextField day = new JTextField();
	private JTextField num = new JTextField();
	private JTextField usenum = new JTextField();
	private JTextField unit = new JTextField();
	
	private JLabel disease = new JLabel("慢性病：");
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JRadioButton yesDisease = new JRadioButton("是");
	private JRadioButton noDisesse = new JRadioButton("否");

	private JLabel noteLabel = new JLabel("用法说明：");
	private JTextField note = new JTextField();

	private JLabel doctorLabel = new JLabel("开药医生：");
	private JTextField doctor = new JTextField();

	private JTextField id = new JTextField();
	
	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");
	public AddMedicine(DoctorFrame doctorFrame) {
		jFrame.setTitle("处方信息录入");
		jFrame.setSize(450, 480);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		
		//第一行
		nameLabel.setBounds(30, 60, 70, 25);
		name.setBounds(100, 60, 90, 25);

		List<PatientInformation> patientInformations = patientInformationAction.getAllPatient();
		for(PatientInformation p:patientInformations) {
			name.addItem(p.getName());
		}

		idLabel.setBounds(220, 60, 80, 25);
		id.setBounds(300 , 60 , 80 , 25);
		
		medicineLabel.setBounds(30, 100, 80, 25);
		medicine.setBounds(100 , 100 , 150 , 25);
		List<Drugs> drugses = drugsAction.getAllDrugs();
		for(Drugs d:drugses) {
			medicine.addItem(d.getDrug());
		}

		unitLabel.setBounds(30, 140, 80, 25);
		unit.setBounds(100,140,150,25);
		numLabel.setBounds(30, 180, 80, 25);
		num.setBounds(100,180,80,25);
		usenumLabel.setBounds(190, 180, 70, 25);
		usenum.setBounds(260 , 180 , 80 , 25);
		
		usedayLabel.setBounds(30, 220, 80, 25);
		useday.setBounds(100,220,80,25);
		pinlvLabel.setBounds(190, 220, 70, 25);
		pinlv.setBounds(260 , 220 , 80 , 25);
		
		disease.setBounds(30, 260, 80, 25);
		yesDisease.setBounds(100,260,50,25);
		noDisesse.setBounds(150,260,50,25);
		buttonGroup1.add(yesDisease);
		buttonGroup1.add(noDisesse);
		yesDisease.doClick();

		noteLabel.setBounds(30, 300, 80, 25);
		note.setBounds(100,300,150,25);
		
		doctorLabel.setBounds(30, 340, 80, 25);
		doctor.setBounds(100,340,80,25);
		
		submit.setBounds(70 , 380 , 90 , 25);
		cancel.setBounds(170 , 380 , 90 , 25);
		
//		jFrame.add(time);
//		jFrame.add(yearLabel);
//		jFrame.add(mouthLabel);
//		jFrame.add(dayLabel);
		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(unitLabel);
		jFrame.add(numLabel);
		jFrame.add(year);
		jFrame.add(mouth);
		jFrame.add(day);
		jFrame.add(num);
		jFrame.add(unit);
		jFrame.add(submit);
		jFrame.add(cancel);
		jFrame.add(medicine);
		jFrame.add(usenumLabel);
		jFrame.add(usenum);
		jFrame.add(medicineLabel);
		jFrame.add(idLabel);
		jFrame.add(pinlv);
		jFrame.add(useday);
		jFrame.add(usedayLabel);
		jFrame.add(pinlvLabel);
		jFrame.add(disease);
		jFrame.add(yesDisease);
		jFrame.add(noDisesse);
		jFrame.add(id);
		jFrame.add(noteLabel);
		jFrame.add(note);
		jFrame.add(doctorLabel);
		jFrame.add(doctor);
		
		submit.addActionListener(new ActionListener() {

			//添加处方!
			@Override
			public void actionPerformed(ActionEvent e) {
				//添加处方
				//日期是当前系统时间 月 日 年
				String theDate = nowTime;
				//患者名
				String theName = name.getSelectedItem().toString();
				//单位
				String theInstitutions = unit.getText();
				//药名
				String theDrug = medicine.getSelectedItem().toString();
				//音位码
				String theCode = id.getText();
				//数量
				String theAmount = num.getText();
				//用量
				String theDosage = usenum.getText();
				//用药天数
				String theUseDay = useday.getText();
				//用药频率
				String theHz = pinlv.getText();

				String chronicDisease = "";
				//慢性病
				if (yesDisease.isSelected()){
					chronicDisease = yesDisease.getText();
				}else if (noDisesse.isSelected()){
					chronicDisease = noDisesse.getText();
				}
				//用法说明
				String theNote = note.getText();
				String theDoctor = doctor.getText();

				//验证空值
				if (theName.trim().isEmpty() &&
						theInstitutions.trim().isEmpty() && theDosage.trim().isEmpty() &&
						theDoctor.trim().isEmpty() && theName.trim().isEmpty()&&
						theDrug.trim().isEmpty()&& theUseDay.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}
				int amountInt;
				//数量 必须 为整数
				try {
					amountInt = Integer.parseInt(theAmount);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"数量必须是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}
				int useDayInt;
				//用药天数 必须 为整数
				try {
					useDayInt = Integer.parseInt(theUseDay);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"天数必须是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}
				int amount;
				amount = drugsAction.getDrugByName(theDrug).getAmount();
				if (amountInt >= amount) {
					JOptionPane.showMessageDialog(null,"药品不足，请修改","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				System.out.println(chronicDisease);

				Prescription prescription = new Prescription();

				prescription.setTime(theDate);
				prescription.setDoctor(theDoctor);
				prescription.setName(theName);
				prescription.setCode(theCode);
				prescription.setDrug(theDrug);
				prescription.setInstitutions(theInstitutions);
				prescription.setAmount(amountInt);
				prescription.setDosage(theDosage);
				prescription.setDay(useDayInt);
				prescription.setHz(theHz);
				prescription.setChronicDisease(chronicDisease);
				prescription.setNote(theNote);

				prescriptionAction = new PrescriptionAction();
				int i = prescriptionAction.addPrescription(prescription);

				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				//清空
				while(doctorFrame.model.getRowCount()>0){
					doctorFrame.model.removeRow(doctorFrame.model.getRowCount()-1);
				}

				doctorFrame.model.setColumnIdentifiers(StatueContent.doctorcolname2);

				//查数据库，展示所有处方
				List<Prescription> prescriptionList = prescriptionAction.getAllPrescription();
				for (Prescription prescriptions : prescriptionList) {
					doctorFrame.model.addRow(new String[]{
							prescriptions.getTime().toString(),
							prescriptions.getDoctor(),
							prescriptions.getName(),
							prescriptions.getInstitutions(),
							prescriptions.getDrug(),
							prescriptions.getCode(),
							prescriptions.getAmount()+"",
							prescriptions.getHz(),
							prescriptions.getDay()+"",
							prescriptions.getNote(),
							prescriptions.getChronicDisease()
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

