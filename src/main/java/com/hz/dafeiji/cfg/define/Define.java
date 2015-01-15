package com.hz.dafeiji.cfg.define;

import com.hz.dafeiji.cfg.manual.define.DefineTempletCfg;

/**
 * 常数定义，自动生成，勿改
 * @author liukun
 * 2015-1-15 14:14:16
 */
 @SuppressWarnings("UnusedDeclaration")
public class Define {
	
/** 装备升级所消耗的金币与能源所占比例 **/
public static float JIN_BI_EQUIPMENT_UP = 0.1F;/** 僚机升级所消耗的金币与经验所占比例 **/
public static float JIN_BI_WING_UP = 0.1F;/** 装备拆分时所获得的能源与升级所消耗能源的比例 **/
public static float ZHUANG_BEI_CHAI_FEN = 0.1F;/** 僚机被吞噬时所获得的经验与升级所消耗经验的比例 **/
public static float LIAO_JI_TUN_SHI = 0.1F;/** 装备升级时的总的参数。 **/
public static float ZHUANG_BEI_SHENG_JI_CHANG_LIANG = 10F;/** 经典模式核弹携带上限 **/
public static int HEI_DAN_XIE_DAI_SHANG_XIAN = 9;/** 开局后可以冲刺10屏的距离 **/
public static int XIN_SHENG_QU_DONG = 1;/** 死亡后可以冲刺5屏的距离 **/
public static int SI_WANG_QU_DONG = 5;/** 蓝色防护罩持续时间为5秒 **/
public static int LAN_SE_FANG_HU_TIME = 5;/** 战斗中冲刺3屏 **/
public static int KUANG_RE_QU_DONG = 3;/** 战斗中获得爱心可以恢复20%的气血 **/
public static float HUI_FU_QI_XUE = 0.2F;/** 进入狂暴状态攻速和子弹大小会提高50% **/
public static float KUANG_BAO_SHU_XING_JIA_CHENG = 0.5F;/** 狂暴持续时间为5秒，注狂暴时间结束相当于叠加层数减一 **/
public static int KUANG_BAO_CHI_XU_TIME = 5;/** 进入狂暴需要的普通子弹数量 **/
public static int KUANG_BAO_XU_YAO_PU_TONG = 4;/** 进入狂暴需要的狂暴子弹数量 **/
public static int KUANG_BAO_XU_YAO_KUANG_BAO = 1;/** 普通子弹一层的攻速和子弹大小提高是10% **/
public static float PU_TONG_ZI_DAN_WEI_LI = 0.1F;/** 受到攻击狂暴层数减一层 **/
public static int KUANG_BAO_WEI_LI_SUAI_JIAN = 1;/** 吃一个小水晶获得2积分 **/
public static int JI_FEN_XIAO = 2;/** 吃一个中水晶获得5积分 **/
public static int JI_FEN_ZHONG = 5;/** 吃一个大水晶获得10积分 **/
public static int JI_FEN_DA = 10;/** 单位是屏，可以吸收0.5屏范围内的道具 **/
public static float CI_TIE_FAN_WEI = 0.5F;/** 机甲生命最大值 **/
public static int MAX_JI_JIA_HP = 10000;/** 机甲攻击最大值 **/
public static int MAX_JI_JIA_ATTACK = 100;/** 机甲攻速最大值 **/
public static int MAX_JI_JIA_ASPD = 100;/** 机甲最小值 **/
public static int ID_JI_JIA_MIN = 100000;/** 机甲最大值 **/
public static int ID_JI_JIA_MAX = 199999;/** 僚机 **/
public static String ID_LIAO_JI = "200000-299999";/** 装备 **/
public static String ID_ZHUANG_BEI = "300000-399999";/** 小怪 **/
public static String ID_XIAO_GUAI = "400000-449999";/** boss **/
public static String ID_BOSS = "450000-499999";/** 商店道具 **/
public static String ID_SHOP_DAO_JU = "530000-559999";/** 战斗道具 **/
public static String ID_COMBAT_DAO_JU = "560000-599999";/** 消耗品 **/
public static String ID_XIAO_HAO_PIN = "600000-699999";/** 技能id **/
public static String ID_JI_NENG = "700000-799999";/** 基础属性 **/
public static String ID_JI_CHU_SHU_XING = "500000-529999";/** 最小材料id **/
public static int ID_STUFF_MIN = 530000;/** 最大材料id **/
public static int ID_STUFF_MAX = 699999;
		public static void reload() {
		DefineTempletCfg.init();
JIN_BI_EQUIPMENT_UP = getFloat("JIN_BI_EQUIPMENT_UP");JIN_BI_WING_UP = getFloat("JIN_BI_WING_UP");ZHUANG_BEI_CHAI_FEN = getFloat("ZHUANG_BEI_CHAI_FEN");LIAO_JI_TUN_SHI = getFloat("LIAO_JI_TUN_SHI");ZHUANG_BEI_SHENG_JI_CHANG_LIANG = getFloat("ZHUANG_BEI_SHENG_JI_CHANG_LIANG");HEI_DAN_XIE_DAI_SHANG_XIAN = getInt("HEI_DAN_XIE_DAI_SHANG_XIAN");XIN_SHENG_QU_DONG = getInt("XIN_SHENG_QU_DONG");SI_WANG_QU_DONG = getInt("SI_WANG_QU_DONG");LAN_SE_FANG_HU_TIME = getInt("LAN_SE_FANG_HU_TIME");KUANG_RE_QU_DONG = getInt("KUANG_RE_QU_DONG");HUI_FU_QI_XUE = getFloat("HUI_FU_QI_XUE");KUANG_BAO_SHU_XING_JIA_CHENG = getFloat("KUANG_BAO_SHU_XING_JIA_CHENG");KUANG_BAO_CHI_XU_TIME = getInt("KUANG_BAO_CHI_XU_TIME");KUANG_BAO_XU_YAO_PU_TONG = getInt("KUANG_BAO_XU_YAO_PU_TONG");KUANG_BAO_XU_YAO_KUANG_BAO = getInt("KUANG_BAO_XU_YAO_KUANG_BAO");PU_TONG_ZI_DAN_WEI_LI = getFloat("PU_TONG_ZI_DAN_WEI_LI");KUANG_BAO_WEI_LI_SUAI_JIAN = getInt("KUANG_BAO_WEI_LI_SUAI_JIAN");JI_FEN_XIAO = getInt("JI_FEN_XIAO");JI_FEN_ZHONG = getInt("JI_FEN_ZHONG");JI_FEN_DA = getInt("JI_FEN_DA");CI_TIE_FAN_WEI = getFloat("CI_TIE_FAN_WEI");MAX_JI_JIA_HP = getInt("MAX_JI_JIA_HP");MAX_JI_JIA_ATTACK = getInt("MAX_JI_JIA_ATTACK");MAX_JI_JIA_ASPD = getInt("MAX_JI_JIA_ASPD");ID_JI_JIA_MIN = getInt("ID_JI_JIA_MIN");ID_JI_JIA_MAX = getInt("ID_JI_JIA_MAX");ID_LIAO_JI = getString("ID_LIAO_JI");ID_ZHUANG_BEI = getString("ID_ZHUANG_BEI");ID_XIAO_GUAI = getString("ID_XIAO_GUAI");ID_BOSS = getString("ID_BOSS");ID_SHOP_DAO_JU = getString("ID_SHOP_DAO_JU");ID_COMBAT_DAO_JU = getString("ID_COMBAT_DAO_JU");ID_XIAO_HAO_PIN = getString("ID_XIAO_HAO_PIN");ID_JI_NENG = getString("ID_JI_NENG");ID_JI_CHU_SHU_XING = getString("ID_JI_CHU_SHU_XING");ID_STUFF_MIN = getInt("ID_STUFF_MIN");ID_STUFF_MAX = getInt("ID_STUFF_MAX");
		}
		
		private static int getInt(String s) {
			return new Integer(get(s));
		}

		private static float getFloat(String s) {
			return new Float(get(s));
		}

		private static Boolean getBoolean(String s) {
			return Boolean.valueOf( get( s ) );
		}

		private static String getString(String s) {
			String string = get(s);
			if(string.endsWith("\"") && string.startsWith("\"")) {
				string = string.substring(1, string.length() - 1);
			}
			return string;
		}

		private static String get(String s) {
			return DefineTempletCfg.get(s).getValue().trim();
		}

		private static double getDouble(String s) {
			return new Double(get(s));
		}
}