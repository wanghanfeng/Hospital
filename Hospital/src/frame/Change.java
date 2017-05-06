package frame;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import action.*;
import content.ImageContent;
import content.StatueContent;
import model.*;

public class Change {
	//各部分的控制器——Action
	DoctorAction doctorAction;
	NurseAction nurseAction;
	FinanceAction financeAction;
	HealthAction healthAction;
	StoreKeeperAction storeKeeperAction;
	SystemConAction systemConAction;
	
	private String kind;
	private JFrame jFrame;
	
	//  存放图片用的控件
	private JLayeredPane layeredPane;
	private JPanel jp;
	private JLabel jl;
	private ImageIcon image;
	
	//修改密码的控件
	private Label identify_label;
	private JTextField identify;
	private Label name_label;
	private JTextField name;
	private Label password_label1;
	private JTextField password1;
	private Label password_label2;
	private JTextField password2;
	private Label password_label3;
	private JTextField password3;
	
	//提交取消按钮
	private JButton submit;
	private JButton cancel;
	
	public Change(String kind) {
		//修改密码的种类
		this.kind = kind;
	}

	public static void ifSuccess(int result){
		if (result==0){
			JOptionPane.showMessageDialog(null,"用户名或密码输入错误,请检查","信息窗口",JOptionPane.WARNING_MESSAGE);
			return;
		}else if (result==1){
			JOptionPane.showMessageDialog(null,"更改成功!","信息窗口",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void init() {
		//创建背景
		layeredPane = new JLayeredPane();
		image = new ImageIcon(ImageContent.change_img);
		// 创建背景的那些东西
		jp = new JPanel();
		jp.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		jl = new JLabel(image);
		jp.add(jl);
		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		
		//添加控件
		identify_label = new Label("工     号");
		name_label = new Label("姓     名");
		password_label1 = new Label("原口令");
		password_label2 = new Label("新  口  令");
		password_label3 = new Label("确认口令");
		identify = new JTextField();
		name = new JTextField();
		password1 = new JTextField();
		password2 = new JTextField();
		password3 = new JTextField();
		identify_label.setBounds(30, 50, 45, 25);
		identify.setBounds(75, 50, 90, 25);
		name_label.setBounds(30, 90, 45, 25);
		name.setBounds(75, 90, 90, 25);
		password_label1.setBounds(30, 130, 45, 25);
		password1.setBounds(75, 130, 90, 25);
		password_label2.setBounds(180, 50, 50, 25);
		password2.setBounds(230, 50, 90, 25);
		password_label3.setBounds(180, 90, 50, 25);
		password3.setBounds(230, 90, 90, 25);
		layeredPane.add(identify_label, JLayeredPane.MODAL_LAYER);
		layeredPane.add(identify, JLayeredPane.MODAL_LAYER);
		layeredPane.add(name_label, JLayeredPane.MODAL_LAYER);
		layeredPane.add(name, JLayeredPane.MODAL_LAYER);
		layeredPane.add(password_label1, JLayeredPane.MODAL_LAYER);
		layeredPane.add(password1, JLayeredPane.MODAL_LAYER);
		layeredPane.add(password_label2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(password2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(password_label3, JLayeredPane.MODAL_LAYER);
		layeredPane.add(password3, JLayeredPane.MODAL_LAYER);
		
		submit = new JButton("确认");
		cancel = new JButton("取消");
		submit.setBounds(75, 180, 90, 25);
		layeredPane.add(submit,  JLayeredPane.MODAL_LAYER);
		cancel.setBounds(170, 180, 90, 25);
		layeredPane.add(cancel,  JLayeredPane.MODAL_LAYER);
		
		//确认按钮单机事件
		submit.addActionListener(new ActionListener() {
			//确定更改信息
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(kind+"------");
				int result;
				//工号
				String idString = identify.getText();
				//姓名
				String nameString = name.getText();
				//原口令
				String oldPwd = password1.getText();
				//新口令
				String newPwd = password2.getText().trim();
				//确认新口令
				String newPwdConfirm = password3.getText().trim();

				//判断为空，报异常窗口
				if (idString.trim().isEmpty()&&nameString.trim().isEmpty()&&
						oldPwd.trim().isEmpty()&& newPwd.trim().isEmpty()&&
							newPwdConfirm.trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"不能有空值","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}

				//密码输入不一致
				if (!newPwd.equals(newPwdConfirm)){
					JOptionPane.showMessageDialog(null,"两次输入的密码不一致","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}
				int theId;
				//如果工号为不是整数报异常窗口
				try {
					theId = Integer.parseInt(idString);
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null,"工号必须是整数","错误窗口",JOptionPane.ERROR_MESSAGE);
					return;
				}
				switch (kind){
					case "医生工作":
						doctorAction = new DoctorAction();
						//检查原密码对不对
						Doctor doctor = doctorAction.LoginDoctor(theId,nameString,oldPwd);
						if (doctor==null){
							JOptionPane.showMessageDialog(null,"用户名或密码输入错误,请检查","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						//更新
						doctor.setId(theId);
						doctor.setName(nameString);
						doctor.setPassword(newPwd);
						result = doctorAction.updateDoctor(doctor);
						//判断是否成功更新
						ifSuccess(result);
						break;
					case "护士工作":
						nurseAction = new NurseAction();
						Nurse nurse = nurseAction.LoginNurse(theId,nameString,oldPwd);
						if (nurse==null){
							JOptionPane.showMessageDialog(null,"用户名或密码输入错误,请检查","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						nurse.setId(theId);
						nurse.setName(nameString);
						nurse.setPassword(newPwd);
						result = nurseAction.updateNurse(nurse);
						ifSuccess(result);
						break;
					case "财务工作":
						financeAction = new FinanceAction();
						Finance finance = financeAction.LoginFince(theId,nameString,oldPwd);
						if (finance == null){
							JOptionPane.showMessageDialog(null,"用户名或密码输入错误,请检查","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						finance.setId(theId);
						finance.setName(nameString);
						finance.setPassword(newPwd);
						result = financeAction.updateFinance(finance);
						ifSuccess(result);
						break;
					case "卫材管理":
						storeKeeperAction = new StoreKeeperAction();
						StoreKeeper storeKeeper = storeKeeperAction.LoginStoreKeeper(theId,nameString,oldPwd);
						if (storeKeeper == null){
							JOptionPane.showMessageDialog(null,"用户名或密码输入错误,请检查","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						storeKeeper.setId(theId);
						storeKeeper.setName(nameString);
						storeKeeper.setPassword(newPwd);
						result = storeKeeperAction.updateStoreKeeper(storeKeeper);
						ifSuccess(result);
						break;
					case "卫生安全":
						healthAction = new HealthAction();
						Health health = healthAction.LoginHealth(theId,nameString,oldPwd);
						if (health == null){
							JOptionPane.showMessageDialog(null,"用户名或密码输入错误,请检查","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						health.setId(theId);
						health.setName(nameString);
						health.setPassword(newPwd);
						result = healthAction.updateHealth(health);
						ifSuccess(result);
						break;
					case "系统维护":
						systemConAction = new SystemConAction();
						SystemCon systemCon = systemConAction.LoginSystemCon(theId,nameString,oldPwd);
						if (systemCon == null){
							JOptionPane.showMessageDialog(null,"用户名或密码输入错误,请检查","信息窗口",JOptionPane.WARNING_MESSAGE);
							return;
						}
						systemCon.setId(theId);
						systemCon.setName(nameString);
						systemCon.setPassword(newPwd);
						result = systemConAction.updateSystemCon(systemCon);
						ifSuccess(result);
						break;
				}
				jFrame.dispose();
			}
		});
		
		//取消按钮单机事件
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		
		//创建页面基本属性
		jFrame = new JFrame(StatueContent.change_title);
		jFrame.setLayeredPane(layeredPane);
		jFrame.setSize(StatueContent.change_width, StatueContent.change_height);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		
		
	}

	
}
