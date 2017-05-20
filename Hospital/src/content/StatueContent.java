package content;

public class StatueContent {
	//资源服务器属性
	public final static String server_ip = "123.206.13.45";
	public final static int server_port = 8800;
	public final static String server_schem ="http://" + server_ip + "/dashboard/img/";
	
	//登陆界面所用属性
	public final static String login = "用户登陆";
	public final static int login_width = 625;
	public final static int login_height = 285;
	public final static String[] kind = {"医生工作" , "护士工作" , "财务工作" , "卫材管理","卫生安全","系统维护"};

	//修改密码页面属性
	public final static String change_title = "更改口令";
	public final static int change_width = 352;
	public final static int change_height = 285;
	
	//主页面属性
	public final static int main_width = 800;
	public final static int main_height = 630;
	public final static String[] jurisdictions = {"doctor" , "nurse" , "storageKeeper","finance","health","systemCon"};
	public final static String[] doctorcolname1 = {"时间", "姓名", "性别", "单位", "病情", "医生", "详情"};
	public final static String[] doctorcolname2 = {"时间", "开药医生", "患者姓名", "患者单位", "药品名称", "音位码", "数量", "用药频率","用药天数","用法说明","慢性病"};
	public final static String[] doctorcolname22 = {"id","时间", "开药医生", "患者姓名", "患者单位", "药品名称", "音位码", "数量", "用药频率","用药天数","用法说明","慢性病"};
	public final static String[] consxamne = {"时间" ,"姓名", "性别" ,"单位", "就诊医院" ,"审批人", "建议门诊" ,"病情说明"};
	public final static String[] nurseColname = {"时间", "姓名", "性别", "单位", "年龄", "用药名称", "音位码","不良反应","值班护士"};
	public final static String[] nurseColname11 = {"id","时间", "姓名", "性别", "单位", "年龄", "用药名称", "音位码","不良反应","值班护士"};
	public final static String[] nurseColname1 = {"时间","姓名","单位","性别","过敏药物","过敏史","值班护士"};
	public final static String[] quarantine = {"时间","姓名","单位","性别","隔离天数","隔离原因","审批人"};
	public final static String[] allergyPatient = {"时间","姓名","单位","性别","过敏药物","过敏史","监护人","病情说明"};
	public final static String[] reimbursementColname = {"时间", "姓名", "队别", "外诊原因", "审批医生", "就诊医院", "住院天数", "审核人","总费用","可报销费用"};
	public final static String[] importDevice = {"时间","设备名称","生产商","类型","市场价格","进价","审批人"};
	public final static String[] importDevice1 = {"id","时间","设备名称","生产商","类型","市场价格","进价","审批人"};
	public final static String[] changeDevice = {"时间","物资名称","进价","审批人"};
	public final static String[] leaderPay = {"时间","姓名","性别","单位","军衔","百分比","就诊医院","审批人","报销原因"};
	public final static String[] eisaiManagementColname1 = {"音位码" , "时间", "种类", "药品名称",  "化学名", "包装单位", "规格", "产地","进价","零售价","供应商","失效期","入库数量","操作人"};
	public final static String[] eisaiManagementColname2 = {"时间", "物资名称", "规格型号", "库存量", "进货单位", "零售价", "进价", "失效日期","批号","产地","音位码","物资代码","操作人"};
	public final static String[] safeColname1 = {"时间", "姓名", "性别", "单位", "年龄", "体温", "病情", "用药名称","用药数量","隔离时间","值班医生"};
	public final static String[] safeColname2 = {"时间", "单位", "有无重大疫情", "非战斗减亡人员", "病情种类", "调查人"};
	public final static String[] safeColname3 = {"id" , "时间", "单位", "有无重大疫情", "非战斗减亡人员", "病情种类", "调查人"};
	public final static String[] prevention = {"时间" , "值班领导","地点","发放物资名称","发放物资数量","具体工作"};
	public final static String[] specialList = {"时间","姓名","单位","专业","截止时间"};
	
	public final static String about = "SDT Windows [版本 10.0.10586] (c) 2016 Peng。保留所有权利。";
}
