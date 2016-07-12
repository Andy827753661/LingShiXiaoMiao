package com.lingshimall.lingshixiaomiao.db;

/**
 * 数据库
 * 
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseHellper extends SQLiteOpenHelper {
	private static DataBaseHellper mDataBaseHelper;

	public synchronized static DataBaseHellper getInstance(Context context) {
		if (mDataBaseHelper == null) {
			mDataBaseHelper = new DataBaseHellper(context);
		}

		return mDataBaseHelper;
	}

	public DataBaseHellper(Context context) {
		super(context, "weifang.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
//		int image[] = { R.drawable.yandan, R.drawable.bailianou,
//				R.drawable.qincai, R.drawable.zhalianhua, R.drawable.liyu,
//				R.drawable.shanyao };
		// 用户表
//		db.execSQL("create table UserList (_id integer primary key autoincrement,userName ,sex ,name ,image integer,PassWord,money double)");
//		// 商品菜单表
//		db.execSQL("create table ProductList(name ,price double ,image integer ,count integer ,introduce)");
//		db.execSQL("insert into  ProductList(name ,price ,image ,count ,introduce)values('桓台金丝鸭蛋','20.5','"
//				+ image[0]
//				+ "','200','桓台特产金丝鸭蛋：自古以来，马踏胡上鹅鸭成群。金丝鸭蛋已负盛名。鹅鸭平素以湖中的蛤蜊为食，所产鸭蛋品质好，色香味独具特色。腌制蒸熟后，可见蛋青蛋黄相交处，有蛋黄油圈隔，似缕缕金丝绞缠，其味佳美，蛋油香而不腻，肉质砂不噎喉，金丝鸭蛋由此而得名。过去曾是皇家贡品。 金丝鸭蛋民间又叫缠丝鸭蛋。产于临淄大路家庄一带。古代临淄泉水众多，河汊交错，近水人家多养鸭。大路家庄是安次水注入?水（今乌河 ）之处，河内多产螺丝以及鱼、虾等。鸭食之，所产之蛋“卵黄层层， 紫赤相间”。剖之，如金丝缠绕，其味异常。如盐渍近月时间，煮熟刀切时，黄油顺刃流出，入口有奇香。金丝鸭蛋过去少为外人所知。１９５７年中央卫生检查团到临淄检查工作 ，席间奉送每人一枚品尝，交口称善。此后金丝鸭蛋之名逐渐扬播于省内外。')");
//		db.execSQL("insert into  ProductList(name ,price ,image ,count ,introduce)values('桓台白莲藕','30.4','"
//				+ image[1]
//				+ "','300','马踏湖白莲藕是山东省淄博市桓台县的特产。马踏湖白莲藕洁白如玉，鲜嫩甜脆，藕内含淀粉，蛋白高，还有糖、脂肪以及维生素等多种成分，是上等蔬菜。白莲藕是马踏湖中特产之冠，含有丰富的蛋白质、脂肪、碳水化合物、钙、磷、铁，以及维生素B和维生素C。有明显的补益气血，增强人体免疫力作用。白莲藕还可以消暑消热，是良好的祛暑食物。')");
//		db.execSQL("insert into  ProductList(name ,price ,image ,count ,introduce)values('桓台芹菜','12.5','"
//				+ image[2]
//				+ "','400','桓台县特产。集中产区在荆家、田庄二镇。荆家实秆芹菜是荆家镇的传统特色蔬菜，经过优中选优，提纯复壮培育而成，具有脆嫩、清香、适口性好、营养丰富、风味独特、食用价值高的特点，兼有清热利水、降压、消肿等药用功效。')");
//		db.execSQL("insert into  ProductList(name ,price ,image ,count ,introduce)values('桓台炸莲花','50','"
//				+ image[3]
//				+ "','500','炸莲花是将鲜嫩的莲花总投资洗净挂浆后入油锅炸之。此菜火候以酥脆为宜。捞出沥油放入白糖即可。食之酥脆清香。另有炸薄荷叶一菜，主料为嫩薄荷叶，做法与炸莲花相同，其味芬芳清辛。')");
//		db.execSQL("insert into  ProductList(name ,price ,image ,count ,introduce)values('桓台鲤鱼抱蛋','100','"
//				+ image[4]
//				+ "','600','1684年（清康熙二十三年），王士祯在京任詹事府少詹事兼翰林院待讲学士，举荐陈某到京在宫廷御膳房供职。此技艺经两代传给鲍士彬后，境内鲈鱼绝迹，遂以鲤代鲈，菜名也随之易为“鲤鱼抱蛋”。通过精心制作，仍不失原有风味。当鲍士彬授艺于县饮食服务公司紫影楼特三级烹调厨师张月华（女）后，经其多次实践，根据鲁菜特点，改鲤鱼的油炸为清蒸，较原来易于保持营养，且增加其鲜嫩清香的特色。1986年10月，张月华参加山东省第一届鲁菜大奖赛（淄博赛区），表演此菜的制作技艺获奖。')");
//		db.execSQL("insert into  ProductList(name ,price ,image ,count ,introduce)values('桓台细毛山药','30','"
//				+ image[5]
//				+ "','300','桓台新城细毛山药是山东省桓台县新城镇著名的地方特产，栽培历史悠久，早在明清年间就享有盛誉。因其特殊的水土生长条件，成为山药家族中的上乘之品，并成为宫廷贡品。新城细毛山药具有健脾、补肺、固肾、益精等多种功效，集菜品、药品和补品三者于一体，获得了“国家地理标志保护产品”和“中国有机产品”认证。')");
//		// 购物车表
//		db.execSQL("create table ShoppingList (_id integer primary key autoincrement,userName,ProName,shopPrice double, proImg integer,proCount integer)");
//		// 留言表
//		db.execSQL("create table MessageList (_id integer primary key autoincrement,userName,imageUrl,name ,date ,content,image integer)");
//		// 收藏表
//		db.execSQL("create table CollectList (_id integer primary key autoincrement,userName,collectName ,collectPrice double,collectImg integer)");
//		// 管理员表
//		db.execSQL("create table ManagerList (_id integer primary key autoincrement,userName ,passWord)");
//		db.execSQL("insert into  ManagerList(_id,userName,passWord)values('1','admin','1111')");
//		db.execSQL("insert into  ManagerList(_id,userName,passWord)values('2','1111','2222')");
//		// 已经付款订单表
//		// 搜索历史记录表
//		db.execSQL("create table SouSuoList (_id integer primary key autoincrement,userName,content)");
//		// 待付款订单表
//		db.execSQL("create table IndentList (_id integer primary key autoincrement,userName,indentName,indentPrice double,indentImage integer,indentCount integer,indentSum double)");
//		db.execSQL("create table WaitIndentList (_id integer primary key autoincrement,userName,indentName,indentPrice double,indentImage integer,indentCount integer,indentSum double)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
