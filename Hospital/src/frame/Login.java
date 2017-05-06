package frame;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import action.*;
import content.ImageContent;
import content.StatueContent;
import model.*;


public class Login {

	//各部分的控制器——Action
	DoctorAction doctorAction;
	NurseAction nurseAction;
	FinanceAction financeAction;
	HealthAction healthAction;
	StoreKeeperAction storeKeeperAction;
	SystemConAction systemConAction;


	private JFrame jFrame;
	
	//  存放图片用的控件
	private JLayeredPane layeredPane;
	private JPanel jp;
	private JLabel jl;
	private ImageIcon image;
	
	//登陆控件
	private Label identify_label;
	private JTextField identify;
	private Label name_label;
	private JTextField name;
	private Label password_label;
	private JPasswordField password;
	private Label kind_label;
	private JComboBox kind;
	private JButton submit;
	private JButton cancel;
	private JButton change;

	// 初始化登陆界面
	public void init() {

		//创建背景
		layeredPane = new JLayeredPane();
		image = new ImageIcon(ImageContent.login_img);
		// 创建背景的那些东西
		jp = new JPanel();
		jp.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		jl = new JLabel(image);
		jp.add(jl);
		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		
		
		//添加登陆控件
		identify_label = new Label("工号");
		name_label = new Label("姓名");
		password_label = new Label("口令");
		kind_label = new Label("职业");
		submit = new JButton("确          认");
		cancel = new JButton("取          消");
		change = new JButton("更改口令");
		identify = new JTextField();
		name = new JTextField();
		password = new JPasswordField();
		kind = new JComboBox<>();
		for(int i = 0;i < StatueContent.kind.length; i++) {
			kind.addItem(StatueContent.kind[i]);
		}
		identify_label.setBounds(360, 40, 30, 25);
		identify.setBounds(390, 40, 100, 25);
		name_label.setBounds(360, 80, 30, 25);
		name.setBounds(390, 80, 100, 25);
		password_label.setBounds(360, 120, 30, 25);
		password.setBounds(390, 120, 100, 25);
		kind_label.setBounds(360, 160, 30, 25);
		kind.setBounds(390, 160, 100, 25);
		submit.setBounds(510 , 40 , 90 , 25);
		cancel.setBounds(510 , 80 , 90 , 25);
		change.setBounds(510 , 120 , 90 , 25);
		layeredPane.add(identify, JLayeredPane.MODAL_LAYER);
		layeredPane.add(identify_label, JLayeredPane.MODAL_LAYER);
		layeredPane.add(name, JLayeredPane.MODAL_LAYER);
		layeredPane.add(name_label, JLayeredPane.MODAL_LAYER);
		layeredPane.add(password, JLayeredPane.MODAL_LAYER);
		layeredPane.add(password_label, JLayeredPane.MODAL_LAYER);
		layeredPane.add(kind_label, JLayeredPane.MODAL_LAYER);
		layeredPane.add(kind, JLayeredPane.MODAL_LAYER);
		layeredPane.add(submit, JLayeredPane.MODAL_LAYER);
		layeredPane.add(cancel, JLayeredPane.MODAL_LAYER);
		layeredPane.add(change, JLayeredPane.MODAL_LAYER);
		
		
		//创建页面基本属性
		jFrame = new JFrame(StatueContent.login);
		jFrame.setLayeredPane(layeredPane);
		jFrame.setSize(StatueContent.login_width, StatueContent.login_height);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		
		//为确认按钮添加单击事件
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//验证身份 进入不同操作界面
				String role = kind.getSelectedItem().toString();
				switch (role){
					case "医生工作":
						int docId;
						doctorAction = new DoctorAction();
						String idString1 = identify.getText();
						String docName = name.getText();
						String docPassword = password.getText();
						//判断为空，报异常窗口
						if (idString1.trim().isEmpty()&&docName.trim().isEmpty()&&docPassword.trim().isEmpty()){
							JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//如果工号为不是整数报异常窗口
						try {
							docId = Integer.parseInt(idString1);
						}catch (Exception e1){
							JOptionPane.showMessageDialog(null,"工号必须是整数","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//判断是否数据库中有这个系统管理员
						Doctor doctor = doctorAction.LoginDoctor(docId,docName,docPassword);
						//如果是空就显示提示窗口
						if (doctor==null){
							JOptionPane.showMessageDialog(null,"查无此人,请检查填写的信息","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						new DoctorFrame().init();
						break;
					case "护士工作":
						int nurId;
						nurseAction = new NurseAction();
						String idString2 = identify.getText();
						String nurName = name.getText();
						String nurPassword = password.getText();
						//判断为空，报异常窗口
						if (idString2.trim().isEmpty()&& nurName.trim().isEmpty()&&nurPassword.trim().isEmpty()){
							JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//如果工号为不是整数报异常窗口
						try {
							nurId = Integer.parseInt(idString2);
						}catch (Exception e1){
							JOptionPane.showMessageDialog(null,"工号必须是整数","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//判断是否数据库中有这个系统管理员
						Nurse nurse = nurseAction.LoginNurse(nurId,nurName,nurPassword);
						//如果是空就显示提示窗口
						if (nurse==null){
							JOptionPane.showMessageDialog(null,"查无此人,请检查填写的信息","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						new NurseFrame().init();
						break;
					case "财务工作":
						int finId;
						financeAction = new FinanceAction();
						String idString3 = identify.getText();
						String finName = name.getText();
						String finPassword = password.getText();
						//判断为空，报异常窗口
						if (idString3.trim().isEmpty()&&finName.trim().isEmpty()&&finPassword.trim().isEmpty()){
							JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//如果工号为不是整数报异常窗口
						try {
							finId = Integer.parseInt(idString3);
						}catch (Exception e1){
							JOptionPane.showMessageDialog(null,"工号必须是整数","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//判断是否数据库中有这个系统管理员
						Finance finance = financeAction.LoginFince(finId,finName,finPassword);
						//如果是空就显示提示窗口
						if (finance==null){
							JOptionPane.showMessageDialog(null,"查无此人,请检查填写的信息","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						new ReimbursementFrame().init();
						break;
					case "卫材管理":
						int stoId;
						storeKeeperAction = new StoreKeeperAction();
						String idString4 = identify.getText();
						String stoName = name.getText();
						String stoPassword = password.getText();
						//判断为空，报异常窗口
						if (idString4.trim().isEmpty()&&stoName.trim().isEmpty()&&stoPassword.trim().isEmpty()){
							JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//如果工号为不是整数报异常窗口
						try {
							stoId = Integer.parseInt(idString4);
						}catch (Exception e1){
							JOptionPane.showMessageDialog(null,"工号必须是整数","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//判断是否数据库中有这个系统管理员
						StoreKeeper storeKeeper = storeKeeperAction.LoginStoreKeeper(stoId,stoName,stoPassword);
						//如果是空就显示提示窗口
						if (storeKeeper==null){
							JOptionPane.showMessageDialog(null,"查无此人,请检查填写的信息","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						new EisaiManagementFrame().init();
						break;
					case "卫生安全":
						int heaId;
						healthAction = new HealthAction();
						String idString5 = identify.getText();
						String heaName = name.getText();
						String heaPassword = password.getText();
						//判断为空，报异常窗口
						if (idString5.trim().isEmpty()&&heaName.trim().isEmpty()&&heaPassword.trim().isEmpty()){
							JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//如果工号为不是整数报异常窗口
						try {
							heaId = Integer.parseInt(idString5);
						}catch (Exception e1){
							JOptionPane.showMessageDialog(null,"工号必须是整数","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//判断是否数据库中有这个系统管理员
						Health health = healthAction.LoginHealth(heaId,heaName,heaPassword);
						//如果是空就显示提示窗口
						if (health==null){
							JOptionPane.showMessageDialog(null,"查无此人,请检查填写的信息","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}

						new SafeFrame().init();
						break;
					case "系统维护":
						int sysId;
						systemConAction = new SystemConAction();
						String idString = identify.getText();
						String sysName = name.getText();
						String sysPassword = password.getText();
						//判断为空，报异常窗口
						if (idString.isEmpty()&&sysName.isEmpty()&&sysPassword.isEmpty()){
							JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//如果工号为不是整数报异常窗口
						try {
							sysId = Integer.parseInt(idString);
						}catch (Exception e1){
							JOptionPane.showMessageDialog(null,"工号必须是整数","错误窗口",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//判断是否数据库中有这个系统管理员
						SystemCon systemCon = systemConAction.LoginSystemCon(sysId,sysName,sysPassword);
						//如果是空就显示提示窗口
						if (systemCon==null){
							JOptionPane.showMessageDialog(null,"查无此人,请检查填写的信息","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						new SystemFrame().init();
						break;
				}
				jFrame.dispose();
			}
		});
		
		//为更改口令添加单击事件
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//更改口令
				new Change(kind.getSelectedItem().toString()).init();
			}
		});
		
		jFrame.setVisible(true);
//		Test test = new Test();
//		test.loadURLImage("https://www.baidu.com/img/bd_logo1.png",jFrame.getGraphics(),jFrame);
	}
	
	public static void main(String[] args) {
		new Login().init();
	}
}
