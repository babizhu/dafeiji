package com.hz.dafeiji.cfg.define;

import com.hz.dafeiji.cfg.manual.define.DefineTempletCfg;

/**
 * 常数定义，自动生成，勿改
 * @author liukun
 * 2015-1-13 14:35:25
 */
 @SuppressWarnings("UnusedDeclaration")
public class PropIdDefine {
	
/** 金币:游戏币，用于游戏日常消耗等。 **/
public static int CASH_JIN_BI = 500001;/** 钻石:充值获得货币. **/
public static int CASH_ZUAN_SHI = 500002;/** 总积分:总的积分数，只记录最高的 **/
public static int ZONG_JI_FEN = 500003;/** 周积分:战斗过程中获得的积分，用于排名（每周会清零） **/
public static int ZHOU_JI_FEN = 500004;/** 活动积分:用于兑换某些机甲或者是其他的道具 **/
public static int HUO_DONG_JI_FEN = 500005;/** 战斗力:玩家的整体实力体现 **/
public static int ZHAN_DOU_LI = 500006;/** 行动力:参与战斗的时候所消耗 **/
public static int XING_DONG_LI = 500007;/** 距离(以屏为单位) **/
public static int JU_LI = 500008;/** 能源 **/
public static int NENG_YUAN = 500009;/** 经验 **/
public static int JING_YAN = 500010;/** 天神祝福 **/
public static int ZQ_TIAN_SHEN_ZHU_FU = 531001;/** 跳关道具 **/
public static int ZQ_TIAO_GUAN_DAO_JU = 531002;/** 核弹 **/
public static int ZQ_HE_DAN = 531003;/** 死亡接力 **/
public static int ZQ_SI_WANG_JIE_LI = 531004;/** 开局护盾 **/
public static int ZQ_KAI_JU_HU_DUN = 531005;/** 新生驱动 **/
public static int ZQ_XIN_SHENG_QU_DONG = 531006;/** 死亡驱动 **/
public static int ZQ_SI_WANG_QU_DONG = 531007;/** 防护罩（黄） **/
public static int ZZ_HUANG_SE_BAO_HU_ZAO = 532001;/** 防护罩（蓝） **/
public static int ZZ_LAN_SE_BAO_HU_ZAO = 532002;/** 狂热驱动 **/
public static int ZZ_KUANG_RE_QU_DONG = 532003;/** 爱心 **/
public static int ZZ_AI_XIN = 532004;/** 普通子弹 **/
public static int ZZ_PU_TONG_ZI_DAN = 532005;/** 狂暴子弹 **/
public static int ZZ_KUANG_BAO_ZI_DAN = 532006;/** 磁铁 **/
public static int ZZ_CI_TIE = 532007;/** 小水晶 **/
public static int ZZ_XIAO_SHUI_JING = 532008;/** 中水晶 **/
public static int ZZ_ZHONG_SHUI_JING = 532009;/** 大水晶 **/
public static int ZZ_DA_SHUI_JING = 532010;/** 进阶卡 **/
public static int ZH_JIN_JIE_KA = 533001;/** 初级能源包 **/
public static int ZH_CHU_JI_NENG_YUAN_BAO = 533002;/** 高级能源包 **/
public static int ZH_GAO_JI_NENG_YUAN_BAO = 533003;/** 1级经验卡 **/
public static int ZH_JING_YAN_KA_1 = 533004;/** 2级经验卡 **/
public static int ZH_JING_YAN_KA_2 = 533005;/** 3级经验卡 **/
public static int ZH_JING_YAN_KA_3 = 533006;/** 4级经验卡 **/
public static int ZH_JING_YAN_KA_4 = 533007;/** 5级经验卡 **/
public static int ZH_JING_YAN_KA_5 = 533008;/** 万能碎片 **/
public static int ZH_WAN_NENG_SUI_PIAN = 533009;
		public static void reload() {
		DefineTempletCfg.init();
CASH_JIN_BI = getInt("CASH_JIN_BI");CASH_ZUAN_SHI = getInt("CASH_ZUAN_SHI");ZONG_JI_FEN = getInt("ZONG_JI_FEN");ZHOU_JI_FEN = getInt("ZHOU_JI_FEN");HUO_DONG_JI_FEN = getInt("HUO_DONG_JI_FEN");ZHAN_DOU_LI = getInt("ZHAN_DOU_LI");XING_DONG_LI = getInt("XING_DONG_LI");JU_LI = getInt("JU_LI");NENG_YUAN = getInt("NENG_YUAN");JING_YAN = getInt("JING_YAN");ZQ_TIAN_SHEN_ZHU_FU = getInt("ZQ_TIAN_SHEN_ZHU_FU");ZQ_TIAO_GUAN_DAO_JU = getInt("ZQ_TIAO_GUAN_DAO_JU");ZQ_HE_DAN = getInt("ZQ_HE_DAN");ZQ_SI_WANG_JIE_LI = getInt("ZQ_SI_WANG_JIE_LI");ZQ_KAI_JU_HU_DUN = getInt("ZQ_KAI_JU_HU_DUN");ZQ_XIN_SHENG_QU_DONG = getInt("ZQ_XIN_SHENG_QU_DONG");ZQ_SI_WANG_QU_DONG = getInt("ZQ_SI_WANG_QU_DONG");ZZ_HUANG_SE_BAO_HU_ZAO = getInt("ZZ_HUANG_SE_BAO_HU_ZAO");ZZ_LAN_SE_BAO_HU_ZAO = getInt("ZZ_LAN_SE_BAO_HU_ZAO");ZZ_KUANG_RE_QU_DONG = getInt("ZZ_KUANG_RE_QU_DONG");ZZ_AI_XIN = getInt("ZZ_AI_XIN");ZZ_PU_TONG_ZI_DAN = getInt("ZZ_PU_TONG_ZI_DAN");ZZ_KUANG_BAO_ZI_DAN = getInt("ZZ_KUANG_BAO_ZI_DAN");ZZ_CI_TIE = getInt("ZZ_CI_TIE");ZZ_XIAO_SHUI_JING = getInt("ZZ_XIAO_SHUI_JING");ZZ_ZHONG_SHUI_JING = getInt("ZZ_ZHONG_SHUI_JING");ZZ_DA_SHUI_JING = getInt("ZZ_DA_SHUI_JING");ZH_JIN_JIE_KA = getInt("ZH_JIN_JIE_KA");ZH_CHU_JI_NENG_YUAN_BAO = getInt("ZH_CHU_JI_NENG_YUAN_BAO");ZH_GAO_JI_NENG_YUAN_BAO = getInt("ZH_GAO_JI_NENG_YUAN_BAO");ZH_JING_YAN_KA_1 = getInt("ZH_JING_YAN_KA_1");ZH_JING_YAN_KA_2 = getInt("ZH_JING_YAN_KA_2");ZH_JING_YAN_KA_3 = getInt("ZH_JING_YAN_KA_3");ZH_JING_YAN_KA_4 = getInt("ZH_JING_YAN_KA_4");ZH_JING_YAN_KA_5 = getInt("ZH_JING_YAN_KA_5");ZH_WAN_NENG_SUI_PIAN = getInt("ZH_WAN_NENG_SUI_PIAN");
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