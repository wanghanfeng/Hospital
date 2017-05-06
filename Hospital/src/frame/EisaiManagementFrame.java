package frame;

import action.DrugsAction;
import action.StuffAction;
import content.StatueContent;
import model.Drugs;
import model.Stuff;

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

public class EisaiManagementFrame {

	DrugsAction drugsAction;
	StuffAction stuffAction;

	private JFrame jFrame;
	
	//菜单栏组件
	private JPanel jPanel1;
    private JMenuBar jMenuBar;
    private JMenu[] jMenus = {new JMenu("药品出入库") , new JMenu("耗材出入库") , new JMenu("退出")};
    private JMenuItem j1 = new JMenuItem("药品入库");
    private JMenuItem j2 = new JMenuItem("药品出库");
    private JMenuItem j3 = new JMenuItem("药品查询");
    private JMenuItem j4 = new JMenuItem("耗材入库");
    private JMenuItem j5 = new JMenuItem("耗材出库");
    private JMenuItem j6 = new JMenuItem("耗材查询");
    private JMenuItem j7 = new JMenuItem("注销登陆");
    
    
    private JPanel jPanel2;
    private JPanel jPanel3; 
    private JPanel addMedicine;
    private JPanel addStuff;
    private JPanel selectMedicine;
    private JPanel selectStuff;
    
    //表格组件
    private String[][] datas = {};
    public DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane5;
    
    //动态组件
    private JTextField show;
    private JScrollPane showPane;

	public EisaiManagementFrame() {
	}

	public EisaiManagementFrame(DefaultTableModel model) {
		this.model = model;
	}

	public void init() {

		// 设置页面基本属性
		jFrame = new JFrame(StatueContent.kind[3]);
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
		addMedicine = new JPanel();
		addMedicine.setBounds(0, 0, StatueContent.main_width, 100);
		addMedicineLayout();
		
		addStuff = new JPanel();
		addStuff.setBounds(0, 0, StatueContent.main_width, 100);
		addStuffLayout();
		
		selectMedicine = new JPanel();
		selectMedicine.setBounds(0, 0, StatueContent.main_width, 100);
		selectMedicineLayout();
		
		selectStuff = new JPanel();
		selectStuff.setBounds(0, 0, StatueContent.main_width, 100);
		selectStuffLayout();

		// 添加单击事件
		j1.addActionListener(new ActionListener() {
			//药品入库窗口
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddMedicineFrame(new EisaiManagementFrame(model));
			}
		});
		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMedicine.setVisible(true);
				addStuff.setVisible(false);
				jPanel3.setVisible(false);
				selectMedicine.setVisible(false);
				selectStuff.setVisible(false);
			}
		});
		j3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMedicine.setVisible(false);
				addStuff.setVisible(false);
				jPanel3.setVisible(false);
				selectMedicine.setVisible(true);
				selectStuff.setVisible(false);
			}
		});
		j4.addActionListener(new ActionListener() {
			//打开添加耗材的窗口
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddStuffFrame(new EisaiManagementFrame(model));
			}
		});
		j5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMedicine.setVisible(false);
				addStuff.setVisible(true);
				jPanel3.setVisible(false);
				selectMedicine.setVisible(true);
				selectStuff.setVisible(false);
			}
		});
		j6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMedicine.setVisible(false);
				addStuff.setVisible(false);
				jPanel3.setVisible(false);
				selectMedicine.setVisible(false);
				selectStuff.setVisible(true);
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
	
	// 药品出库模块
	private void addMedicineLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("音位码：");
		JTextField name = new JTextField();
		JLabel unitLabel = new JLabel("出库数量：");
		JTextField unit = new JTextField();
		JButton addMedicineSubmit = new JButton("OK");
		
		//添加控件
		addMedicine.setLayout(null);
		nameLabel.setBounds(100, 20, 80, 25);
		name.setBounds(180, 20, 100, 25);
		unitLabel.setBounds(360, 20, 80, 25);
		unit.setBounds(440, 20, 200, 25);
		addMedicineSubmit.setBounds(440, 60, 90, 25);
		addMedicine.add(nameLabel);
		addMedicine.add(name);
		addMedicine.add(unitLabel);		
		addMedicine.add(unit);		
		addMedicine.add(addMedicineSubmit);
		addMedicine.setVisible(false);
		jPanel2.add(addMedicine);

		//出库
		addMedicineSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drugsAction = new DrugsAction();
				//药品出库
				/**
				 * 有几点要说明：
				 * 1.出库首先要查找 音位码 看是否有药品
				 * 2.有药品 要获取 库存数 和 出库数比较
				 * 3.如果可以取出，则直接改变那条库存即可。
				 */
				String theDrugCode = name.getText();
				String theNeed = unit.getText();
				int needInt;

				if (theDrugCode.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"音位码必须输入，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					needInt = Integer.parseInt(theNeed);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"出库必须是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				Drugs drugs = drugsAction.loadDrug(theDrugCode);
				if (drugs==null){
					JOptionPane.showMessageDialog(null,"未查到，请检查音位码","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				int theAmount = drugs.getAmount();
				if (theAmount<needInt){
					JOptionPane.showMessageDialog(null,"库存不足，请检查出库数","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				//剩余数量
				int result = theAmount - needInt;

				drugs.setAmount(result);

				//动用update语句 更新信息
				int j = drugsAction.updateDrugs(drugs);

				if (j==1){
					JOptionPane.showMessageDialog(null,"出库成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"出库失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				model.setColumnIdentifiers(StatueContent.eisaiManagementColname1);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				//查数据库，展示所有财务信息
				List<Drugs> drugsList = drugsAction.getAllDrugs();
				for (Drugs drugs1 : drugsList) {
					model.addRow(new String[]{
							drugs1.getThetime().toString(),
							drugs1.getSort(),
							drugs1.getDrug(),
							drugs1.getDrugcode(),
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
			}
		});

	}
	//耗材出库模块
	public void addStuffLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("音位码：");
		JTextField name = new JTextField();
		JLabel unitLabel = new JLabel("出库数量：");
		JTextField unit = new JTextField();
		JButton addStuffSubmit = new JButton("OK");
		
		//添加控件
		addStuff.setLayout(null);
		nameLabel.setBounds(100, 20, 80, 25);
		name.setBounds(180, 20, 100, 25);
		unitLabel.setBounds(360, 20, 80, 25);
		unit.setBounds(440, 20, 200, 25);
		addStuffSubmit.setBounds(440, 60, 90, 25);
		addStuff.add(nameLabel);
		addStuff.add(name);
		addStuff.add(unitLabel);		
		addStuff.add(unit);		
		addStuff.add(addStuffSubmit);
		addStuff.setVisible(false);
		jPanel2.add(addStuff);

		addStuffSubmit.addActionListener(new ActionListener() {
			//耗材出库
			@Override
			public void actionPerformed(ActionEvent e) {
				stuffAction = new StuffAction();
				//耗材出库
				/**
				 * 有几点要说明：
				 * 1.出库首先要查找 音位码 看是否有
				 * 2.有 要获取 库存数 和 出库数比较
				 * 3.如果可以取出，则直接改变那条库存即可。
				 */
				String theStuffcode = name.getText();
				String theNeed = unit.getText();
				int needInt;

				if (theStuffcode.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"音位码必须输入，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					needInt = Integer.parseInt(theNeed);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"出库必须是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				Stuff stuff = stuffAction.loadStuff(theStuffcode);
				if (stuff==null){
					JOptionPane.showMessageDialog(null,"未查到，请检查音位码","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				int theAmount = stuff.getAmount();
				if (theAmount<needInt){
					JOptionPane.showMessageDialog(null,"库存不足，请检查出库数","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				//剩余数量
				int result = theAmount - needInt;

				stuff.setAmount(result);

				//动用update语句 更新信息
				int j = stuffAction.updateStuff(stuff);

				if (j==1){
					JOptionPane.showMessageDialog(null,"出库成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"出库失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				model.setColumnIdentifiers(StatueContent.eisaiManagementColname2);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				//查数据库，展示所有财务信息
				List<Stuff> stuffList = stuffAction.getAllStuff();
				for (Stuff stuff1 : stuffList) {
					model.addRow(new String[]{
							stuff1.getThetime().toString(),
							stuff1.getStuff(),
							stuff1.getStandard(),
							stuff1.getAmount()+"",
							stuff1.getCompany(),
							stuff1.getBuysale()+"",
							stuff1.getThesale()+"",
							stuff1.getLose(),
							stuff1.getPicode(),
							stuff1.getPlace(),
							stuff1.getStuffcode(),
							stuff1.getSomecode(),
							stuff1.getUser()
					});
				}
			}
		});

	}
	
	//查找药品模块
	private void selectMedicineLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("药品名称：");
		JTextField name = new JTextField();
		JButton Submit = new JButton("OK");
		
		//添加控件
		selectMedicine.setLayout(null);
		nameLabel.setBounds(100, 20, 80, 25);
		name.setBounds(180, 20, 100, 25);
		Submit.setBounds(440, 60, 90, 25);
		selectMedicine.add(nameLabel);
		selectMedicine.add(name);
		selectMedicine.add(Submit);
		selectMedicine.setVisible(false);
		jPanel2.add(selectMedicine);

		//查找药品
		Submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = name.getText();
				drugsAction = new DrugsAction();
				List<Drugs> drugsList = drugsAction.getDrugsByInf(theName);

				model.setColumnIdentifiers(StatueContent.eisaiManagementColname1);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				//查数据库，展示所有财务信息
				for (Drugs drugs : drugsList) {
					model.addRow(new String[]{
							drugs.getThetime().toString(),
							drugs.getSort(),
							drugs.getDrug(),
							drugs.getDrugcode(),
							drugs.getChe(),
							drugs.getPackages(),
							drugs.getStandard(),
							drugs.getPlace(),
							drugs.getBuysale()+"",
							drugs.getThesale()+"",
							drugs.getCompany(),
							drugs.getLose(),
							drugs.getAmount()+"",
							drugs.getUser()
					});
				}
			}
		});

	}
	
	
	//查找耗材模块
	private void selectStuffLayout() {
		//声明控件
		JLabel nameLabel = new JLabel("物资名称：");
		JTextField name = new JTextField();
		JButton addMedicineSubmit = new JButton("OK");
		
		//添加控件
		selectStuff.setLayout(null);
		nameLabel.setBounds(100, 20, 80, 25);
		name.setBounds(180, 20, 100, 25);
		addMedicineSubmit.setBounds(440, 60, 90, 25);
		selectStuff.add(nameLabel);
		selectStuff.add(name);
		selectStuff.add(addMedicineSubmit);
		selectStuff.setVisible(false);
		jPanel2.add(selectStuff);

		addMedicineSubmit.addActionListener(new ActionListener() {
			//查找耗材
			@Override
			public void actionPerformed(ActionEvent e) {
				String theName = name.getText();

				stuffAction = new StuffAction();
				List<Stuff> stuffList = stuffAction.getStuffByInf(theName);
				model.setColumnIdentifiers(StatueContent.eisaiManagementColname2);

				//清空
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

				//查数据库，展示所有耗材信息
				System.out.println(stuffList.size());

				for (Stuff stuff1 : stuffList) {
					System.out.println("---");
					model.addRow(new String[]{
							stuff1.getThetime().toString(),
							stuff1.getStuff(),
							stuff1.getStandard(),
							stuff1.getAmount()+"",
							stuff1.getCompany(),
							stuff1.getBuysale()+"",
							stuff1.getThesale()+"",
							stuff1.getLose(),
							stuff1.getPicode(),
							stuff1.getPlace(),
							stuff1.getStuffcode(),
							stuff1.getSomecode(),
							stuff1.getUser()
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
        jMenus[0].add(j3);
        jMenus[1].add(j4);
        jMenus[1].add(j5);
        jMenus[1].add(j6);
        jMenus[2].add(j7);
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
		model = new DefaultTableModel(datas, StatueContent.eisaiManagementColname1);
		table = new JTable(model);
		scrollPane5 = new JScrollPane(table);
		table.getTableHeader().setResizingAllowed(false);
		tablePanel.add(scrollPane5, BorderLayout.CENTER);
		table.setRowHeight(35);
		table.setEnabled(false);


		//初始化
		drugsAction = new DrugsAction();

		model.setColumnIdentifiers(StatueContent.eisaiManagementColname1);

		//清空
		while(model.getRowCount()>0){
			model.removeRow(model.getRowCount()-1);
		}

		//查数据库，展示所有信息
		List<Drugs> drugsList = drugsAction.getAllDrugs();
		for (Drugs drugs : drugsList) {
			model.addRow(new String[]{
					drugs.getThetime().toString(),
					drugs.getSort(),
					drugs.getDrug(),
					drugs.getDrugcode(),
					drugs.getChe(),
					drugs.getPackages(),
					drugs.getStandard(),
					drugs.getPlace(),
					drugs.getBuysale()+"",
					drugs.getThesale()+"",
					drugs.getCompany(),
					drugs.getLose(),
					drugs.getAmount()+"",
					drugs.getUser()
			});
		}

	
	}
	
	public static void main(String[] args) {
		new EisaiManagementFrame().init();
	}
}


class AddMedicineFrame {
	DrugsAction drugsAction;

	private JFrame jFrame = new JFrame("药品入库录入");
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);
	
	private JLabel nameLabel = new JLabel("药品名称：");
	private JLabel name1Label = new JLabel("化学名称：");
	
	private JLabel usedayLabel = new JLabel("住院天数：");
	private JLabel ableLabel = new JLabel("可报销费用：");
	
	private JTextField useday = new JTextField();
	private JTextField able = new JTextField();
	
	private JLabel kindLabel = new JLabel("种类：");
	private JComboBox kind = new JComboBox<>();

	private JLabel idLabel = new JLabel("音位码：");
	
	private JLabel guigeLabel = new JLabel("规格：");
	private JTextField guige = new JTextField();
	
	private JTextField name = new JTextField();
	private JTextField sum = new JTextField();
	private JTextField name1 = new JTextField();

	private JLabel doctorLabel = new JLabel("审批医生：");
	private JTextField doctor = new JTextField();
	

	private JTextField id = new JTextField();
	
	private JLabel unitJLabel = new JLabel("包装单位");
	private JTextField unit = new JTextField();
	private JLabel placeJLabel = new JLabel("产地");
	private JTextField place = new JTextField();
	
	private JLabel jinjiaJLabel = new JLabel("进价");
	private JTextField jinjia = new JTextField();
	private JLabel lingshouJLabel = new JLabel("零售价");
	private JTextField lingshou = new JTextField();
	
	private JLabel endTime = new JLabel("失效期：");
	private JLabel endTimeYearLabel = new JLabel("年");
	private JLabel endTimeMouthLabel = new JLabel("月");
	private JLabel endTimeDayLabel = new JLabel("日");
	private JTextField endTimeyearYear = new JTextField();
	private JTextField endTimemouthMouth = new JTextField();
	private JTextField endTimedayDay = new JTextField();
	
	private JLabel moneyLabel = new JLabel("供应商");
	private JTextField money = new JTextField();
	
	private JLabel numJLabel = new JLabel("入库数量");
	private JTextField num = new JTextField();
	private JLabel peopleJLabel = new JLabel("操作人");
	private JTextField people = new JTextField();
	
	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public AddMedicineFrame(EisaiManagementFrame eisaiManagementFrame) {
		jFrame.setSize(450, 500);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		
		//第一行

		nameLabel.setBounds(30, 60, 70, 25);
		name.setBounds(100, 60, 90, 25);
		idLabel.setBounds(220, 60, 80, 25);
		id.setBounds(300 , 60 , 80 , 25);
		
		kindLabel.setBounds(30, 100, 80, 25);
		kind.setBounds(100 , 100 , 80 , 25);
		//种类的下拉表
		for(int i = 0;i < 5; i++) {
			kind.addItem(5);
		}


		guigeLabel.setBounds(220, 100, 80, 25);
		guige.setBounds(300 , 100 , 80 , 25);
		
		name1Label.setBounds(30, 140, 80, 25);
		name1.setBounds(100,140,150,25);
		
		unitJLabel.setBounds(30, 180, 80, 25);
		unit.setBounds(100 , 180 , 80 , 25);
		placeJLabel.setBounds(220, 180, 80, 25);
		place.setBounds(300 , 180 , 80 , 25);
		
		jinjiaJLabel.setBounds(30, 220, 80, 25);
		jinjia.setBounds(100 , 220 , 80 , 25);
		lingshouJLabel.setBounds(220, 220, 80, 25);
		lingshou.setBounds(300 , 220 , 80 , 25);
		
		endTime.setBounds(30, 260, 80, 25);
		endTimeyearYear.setBounds(100 ,260 , 80 , 25);
		endTimeYearLabel.setBounds(180, 260, 15, 25);
		endTimemouthMouth.setBounds(195 , 260,80,25);
		endTimeMouthLabel.setBounds(275, 260, 15, 25);
		endTimedayDay.setBounds(290,260,80,25);
		endTimeDayLabel.setBounds(370, 260, 15, 25);
		
		moneyLabel.setBounds(30, 340, 80, 25);
		money.setBounds(100,340,150,25);
		
		numJLabel.setBounds(30, 380, 80, 25);
		num.setBounds(100 , 380 , 80 , 25);
		peopleJLabel.setBounds(220, 380, 80, 25);
		people.setBounds(300 , 380 , 80 , 25);
		
		submit.setBounds(70 , 420 , 90 , 25);
		cancel.setBounds(170 , 420 , 90 , 25);
		
		
		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(name1Label);
		jFrame.add(people);
		jFrame.add(name1);
		jFrame.add(submit);
		jFrame.add(cancel);
		jFrame.add(kind);
		jFrame.add(sum);
		jFrame.add(kindLabel);
		jFrame.add(idLabel);
		jFrame.add(able);
		jFrame.add(useday);
		jFrame.add(usedayLabel);
		jFrame.add(ableLabel);
		jFrame.add(id);
		jFrame.add(doctorLabel);
		jFrame.add(doctor);
		jFrame.add(guigeLabel);
		jFrame.add(guige);
		jFrame.add(unit);
		jFrame.add(unitJLabel);
		jFrame.add(placeJLabel);
		jFrame.add(place);
		jFrame.add(jinjiaJLabel);
		jFrame.add(jinjia);
		jFrame.add(lingshouJLabel);
		jFrame.add(lingshou);
		jFrame.add(endTime);
		jFrame.add(endTimeDayLabel);
		jFrame.add(endTimedayDay);
		jFrame.add(endTimeYearLabel);
		jFrame.add(endTimeyearYear);
		jFrame.add(endTimeMouthLabel);
		jFrame.add(endTimemouthMouth);
		jFrame.add(moneyLabel);
		jFrame.add(money);
		jFrame.add(numJLabel);
		jFrame.add(num);
		jFrame.add(numJLabel);
		jFrame.add(people);
		jFrame.add(peopleJLabel);

		submit.addActionListener(new ActionListener() {
			//药品入库
			@Override
			public void actionPerformed(ActionEvent e) {
				//添加药品
				int endYear;
				int endMounth;
				int endDay;
				double buySaleDouble;
				double theSaleDouble;
				//入库时间
				String theDate = nowTime;

				String theEndYear = endTimeyearYear.getText();
				String theEndMounth = endTimemouthMouth.getText();
				String theEndDay = endTimedayDay.getText();
				//过期时间
				String theEndDate = theEndYear+"-"+theEndMounth+"-"+theEndDay;

				String theDrug = name.getText();
				String theDrugCode = id.getText();

				String theKind = kind.getSelectedItem().toString();
				String theStandard = guige.getText();
				String thePackages = unit.getText();
				String thePlace = place.getText();
				//进价和零售价
				String theBuySale = jinjia.getText();
				String theSale = lingshou.getText();
				String theCompany = money.getText();
				//入库数量
				String theAmount = num.getText();
				String theUser = people.getText();

				String theChe = name1.getText();

				int amountInt;

				//验证空值
				if (theEndYear.trim().isEmpty() &&
						theEndMounth.trim().isEmpty() && theEndDay.trim().isEmpty() &&
						theDrug.trim().isEmpty() && theKind.trim().isEmpty() &&
						theStandard.trim().isEmpty() && thePackages.trim().isEmpty()&&
						thePlace.trim().isEmpty() && theBuySale.trim().isEmpty() &&
						theSale.trim().isEmpty() && theCompany.trim().isEmpty() &&
						theAmount.trim().isEmpty() && theUser.trim().isEmpty() && theChe.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				//验证 年 月 日 天数 必须 为 整数
				try {
					endYear = Integer.parseInt(theEndYear);
					endMounth = Integer.parseInt(theEndYear);
					endDay = Integer.parseInt(theEndYear);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"日期相关必须是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				//验证价格
				try {
					buySaleDouble = Double.parseDouble(theBuySale);
					theSaleDouble = Double.parseDouble(theSale);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"价格需要是数字，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					amountInt = Integer.parseInt(theAmount);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"数量应该是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				Drugs drugs = new Drugs();
				drugs.setThetime(theDate);
				drugs.setSort(theKind);
				drugs.setDrug(theDrug);
				drugs.setDrugcode(theDrugCode);
				drugs.setChe(theChe);
				drugs.setPackages(thePackages);
				drugs.setStandard(theStandard);
				drugs.setPlace(thePlace);
				drugs.setBuysale(buySaleDouble);
				drugs.setThesale(theSaleDouble);
				drugs.setCompany(theCompany);
				drugs.setLose(theEndDate);
				drugs.setAmount(amountInt);
				drugs.setUser(theUser);

				drugsAction = new DrugsAction();

				Drugs tempDrug = drugsAction.loadDrug(drugs.getDrugcode());

				if (tempDrug!=null){
					JOptionPane.showMessageDialog(null,"该药品音位码已存在,数量已经被叠加","信息窗口",JOptionPane.WARNING_MESSAGE);
					int tempAmount = tempDrug.getAmount();
					//更新数量
					int resultAmount = tempAmount + drugs.getAmount();
					tempDrug.setAmount(resultAmount);
					//更改表单
					drugsAction.updateDrugs(tempDrug);

					eisaiManagementFrame.model.setColumnIdentifiers(StatueContent.eisaiManagementColname1);

					//清空
					while(eisaiManagementFrame.model.getRowCount()>0){
						eisaiManagementFrame.model.removeRow(eisaiManagementFrame.model.getRowCount()-1);
					}

					//查数据库，展示所有信息
					List<Drugs> drugsList = drugsAction.getAllDrugs();
					for (Drugs drugs1 : drugsList) {
						eisaiManagementFrame.model.addRow(new String[]{
								drugs1.getThetime().toString(),
								drugs1.getSort(),
								drugs1.getDrug(),
								drugs1.getDrugcode(),
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
					jFrame.dispose();
					return;
				}

				int i = drugsAction.addDrugs(drugs);

				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				eisaiManagementFrame.model.setColumnIdentifiers(StatueContent.eisaiManagementColname1);

				//清空
				while(eisaiManagementFrame.model.getRowCount()>0){
					eisaiManagementFrame.model.removeRow(eisaiManagementFrame.model.getRowCount()-1);
				}

				//查数据库，展示所有药品信息
				List<Drugs> drugsList = drugsAction.getAllDrugs();
				for (Drugs drugs1 : drugsList) {
					eisaiManagementFrame.model.addRow(new String[]{
							drugs1.getThetime().toString(),
							drugs1.getSort(),
							drugs1.getDrug(),
							drugs1.getDrugcode(),
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

class AddStuffFrame {
	StuffAction stuffAction;
	private JFrame jFrame = new JFrame("耗材入库录入");
	Date theDate = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = df.format(theDate);

	private JLabel nameLabel = new JLabel("物资名称：");
	private JLabel name1Label = new JLabel("进货单位：");
	
	private JLabel usedayLabel = new JLabel("住院天数：");
	private JLabel ableLabel = new JLabel("可报销费用：");
	
	private JTextField useday = new JTextField();
	private JTextField able = new JTextField();
	
	private JLabel kindLabel = new JLabel("库存量：");
	private JLabel idLabel = new JLabel("音位码：");
	
	private JLabel guigeLabel = new JLabel("规格：");
	private JTextField guige = new JTextField();
	
	private JTextField name = new JTextField();
	private JTextField sum = new JTextField();
	private JTextField name1 = new JTextField();

	private JLabel doctorLabel = new JLabel("审批医生：");
	private JTextField doctor = new JTextField();
	
	private JTextField kind = new JTextField();
	private JTextField id = new JTextField();
	
	private JLabel pihaoJLabel = new JLabel("批号");
	private JTextField pihao = new JTextField();
	private JLabel placeJLabel = new JLabel("产地");
	private JTextField place = new JTextField();
	
	private JLabel jinjiaJLabel = new JLabel("进价");
	private JTextField jinjia = new JTextField();
	private JLabel lingshouJLabel = new JLabel("零售价");
	private JTextField lingshou = new JTextField();
	
	private JLabel endTime = new JLabel("失效期：");
	private JLabel endTimeYearLabel = new JLabel("年");
	private JLabel endTimeMouthLabel = new JLabel("月");
	private JLabel endTimeDayLabel = new JLabel("日");
	private JTextField endTimeyearYear = new JTextField();
	private JTextField endTimemouthMouth = new JTextField();
	private JTextField endTimedayDay = new JTextField();
	
	private JLabel codeLabel = new JLabel("物资代码");
	private JTextField code = new JTextField();
	
	private JLabel peopleJLabel = new JLabel("操作人");
	private JTextField people = new JTextField();
	
	private JButton submit = new JButton("确定");
	private JButton cancel = new JButton("取消");
	public AddStuffFrame(EisaiManagementFrame eisaiManagementFrame) {
		jFrame.setSize(450, 500);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		
		//第一行

		nameLabel.setBounds(30, 60, 70, 25);
		name.setBounds(100, 60, 90, 25);
		idLabel.setBounds(220, 60, 80, 25);
		id.setBounds(300 , 60 , 80 , 25);
		
		kindLabel.setBounds(30, 100, 80, 25);
		kind.setBounds(100 , 100 , 80 , 25);
		guigeLabel.setBounds(220, 100, 80, 25);
		guige.setBounds(300 , 100 , 80 , 25);
		
		name1Label.setBounds(30, 140, 80, 25);
		name1.setBounds(100,140,150,25);
		
		pihaoJLabel.setBounds(30, 180, 80, 25);
		pihao.setBounds(100 , 180 , 80 , 25);
		placeJLabel.setBounds(220, 180, 80, 25);
		place.setBounds(300 , 180 , 80 , 25);
		
		jinjiaJLabel.setBounds(30, 220, 80, 25);
		jinjia.setBounds(100 , 220 , 80 , 25);
		lingshouJLabel.setBounds(220, 220, 80, 25);
		lingshou.setBounds(300 , 220 , 80 , 25);
		
		endTime.setBounds(30, 260, 80, 25);
		endTimeyearYear.setBounds(100 ,260 , 80 , 25);
		endTimeYearLabel.setBounds(180, 260, 15, 25);
		endTimemouthMouth.setBounds(195 , 260,80,25);
		endTimeMouthLabel.setBounds(275, 260, 15, 25);
		endTimedayDay.setBounds(290,260,80,25);
		endTimeDayLabel.setBounds(370, 260, 15, 25);
		
		codeLabel.setBounds(30, 340, 80, 25);
		code.setBounds(100,340,150,25);
		
		peopleJLabel.setBounds(220, 380, 80, 25);
		people.setBounds(300 , 380 , 80 , 25);
		
		submit.setBounds(70 , 420 , 90 , 25);
		cancel.setBounds(170 , 420 , 90 , 25);
		
		
		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(name1Label);
		jFrame.add(people);
		jFrame.add(name1);
		jFrame.add(submit);
		jFrame.add(cancel);
		jFrame.add(kind);
		jFrame.add(sum);
		jFrame.add(kindLabel);
		jFrame.add(idLabel);
		jFrame.add(able);
		jFrame.add(useday);
		jFrame.add(usedayLabel);
		jFrame.add(ableLabel);
		jFrame.add(id);
		jFrame.add(doctorLabel);
		jFrame.add(doctor);
		jFrame.add(guigeLabel);
		jFrame.add(guige);
		jFrame.add(pihao);
		jFrame.add(pihaoJLabel);
		jFrame.add(placeJLabel);
		jFrame.add(place);
		jFrame.add(jinjiaJLabel);
		jFrame.add(jinjia);
		jFrame.add(lingshouJLabel);
		jFrame.add(lingshou);
		jFrame.add(endTime);
		jFrame.add(endTimeDayLabel);
		jFrame.add(endTimedayDay);
		jFrame.add(endTimeYearLabel);
		jFrame.add(endTimeyearYear);
		jFrame.add(endTimeMouthLabel);
		jFrame.add(endTimemouthMouth);
		jFrame.add(codeLabel);
		jFrame.add(code);
		jFrame.add(people);
		jFrame.add(peopleJLabel);
		
		
		submit.addActionListener(new ActionListener() {
			//添加耗材
			@Override
			public void actionPerformed(ActionEvent e) {
				int endYear;
				int endMounth;
				int endDay;
				double buySaleDouble;
				double theSaleDouble;
				//入库时间
				String theDate = nowTime;

				String theEndYear = endTimeyearYear.getText();
				String theEndMounth = endTimemouthMouth.getText();
				String theEndDay = endTimedayDay.getText();
				//过期时间
				String theEndDate = theEndYear+"-"+theEndMounth+"-"+theEndDay;

				String theStuff = name.getText();
				String theStuffCode = id.getText();
				//库存量 int
				String theAmount = kind.getText();
				String theStandard = guige.getText();
				String theCompany = name1.getText();
				String thePiCode = pihao.getText();
				String thePalce = place.getText();
				//进价和零售价 double
				String theBuySale = jinjia.getText();
				String theSale = lingshou.getText();
				String theSomeCode = code.getText();
				String theUser = people.getText();

				//验证空值
				if (theEndYear.trim().isEmpty() &&
						theEndMounth.trim().isEmpty() && theEndDay.trim().isEmpty() &&
						theStuff.trim().isEmpty() && theStuffCode.trim().isEmpty()&&
						theStandard.trim().isEmpty() && theBuySale.trim().isEmpty()&&
						theSale.trim().isEmpty() && theCompany.trim().isEmpty()&&
						theAmount.trim().isEmpty()&&theUser.trim().isEmpty()&&
						thePiCode.trim().isEmpty()&&thePalce.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				int amountInt;

				//验证 年 月 日 天数 必须 为 整数
				try {
					endYear = Integer.parseInt(theEndYear);
					endMounth = Integer.parseInt(theEndYear);
					endDay = Integer.parseInt(theEndYear);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"日期相关必须是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				//验证价格
				try {
					buySaleDouble = Double.parseDouble(theBuySale);
					theSaleDouble = Double.parseDouble(theSale);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"价格需要是数字，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					amountInt = Integer.parseInt(theAmount);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"数量应该是整数，请检查","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				Stuff stuff = new Stuff();
				stuff.setStuffcode(theStuffCode);
				stuff.setThetime(theDate);
				stuff.setLose(theEndDate);
				stuff.setStuff(theStuff);
				stuff.setAmount(amountInt);
				stuff.setStandard(theStandard);
				stuff.setCompany(theCompany);
				stuff.setPicode(thePiCode);
				stuff.setPlace(thePalce);
				stuff.setBuysale(buySaleDouble);
				stuff.setThesale(theSaleDouble);
				stuff.setSomecode(theSomeCode);
				stuff.setUser(theUser);

				stuffAction = new StuffAction();

				//查重
				Stuff tempStuff = stuffAction.loadStuff(stuff.getStuffcode());

				if (tempStuff!=null){
					int tempAmount = tempStuff.getAmount();
					//更新数量
					int resultAmount = tempAmount + stuff.getAmount();
					tempStuff.setAmount(resultAmount);
					//更改表单
					int j = stuffAction.updateStuff(tempStuff);

					eisaiManagementFrame.model.setColumnIdentifiers(StatueContent.eisaiManagementColname2);

					//清空
					while(eisaiManagementFrame.model.getRowCount()>0){
						eisaiManagementFrame.model.removeRow(eisaiManagementFrame.model.getRowCount()-1);
					}

					//查数据库，展示所有信息
					List<Stuff> stuffList = stuffAction.getAllStuff();
					for (Stuff stuff1 : stuffList) {
						eisaiManagementFrame.model.addRow(new String[]{
								stuff1.getThetime().toString(),
								stuff1.getStuff(),
								stuff1.getStandard(),
								stuff1.getAmount()+"",
								stuff1.getCompany(),
								stuff1.getBuysale()+"",
								stuff1.getThesale()+"",
								stuff1.getLose(),
								stuff1.getPicode(),
								stuff1.getPlace(),
								stuff1.getStuffcode(),
								stuff1.getSomecode(),
								stuff1.getUser()
						});
					}
					JOptionPane.showMessageDialog(null,"该耗材音位码已存在,数量已经被叠加","信息窗口",JOptionPane.WARNING_MESSAGE);
					jFrame.dispose();
					return;
				}

				int i = stuffAction.addStuff(stuff);

				if (i==1){
					JOptionPane.showMessageDialog(null,"添加成功","消息窗口",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"添加失败","错误窗口",JOptionPane.ERROR_MESSAGE);
				}

				eisaiManagementFrame.model.setColumnIdentifiers(StatueContent.eisaiManagementColname2);

				//清空
				while(eisaiManagementFrame.model.getRowCount()>0){
					eisaiManagementFrame.model.removeRow(eisaiManagementFrame.model.getRowCount()-1);
				}

				//查数据库，展示所有耗材信息
				List<Stuff> stuffList = stuffAction.getAllStuff();
				for (Stuff stuff1 : stuffList) {
					eisaiManagementFrame.model.addRow(new String[]{
							stuff1.getThetime().toString(),
							stuff1.getStuff(),
							stuff1.getStandard(),
							stuff1.getAmount()+"",
							stuff1.getCompany(),
							stuff1.getBuysale()+"",
							stuff1.getThesale()+"",
							stuff1.getLose(),
							stuff1.getPicode(),
							stuff1.getPlace(),
							stuff1.getStuffcode(),
							stuff1.getSomecode(),
							stuff1.getUser()
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

