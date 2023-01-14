package thKaguyaMod.data;

/** 弾幕に関する定数定義 */
public class DanmakuData {

    /**
     * 難易度（弾幕難易度）
     */
    /**	弾幕難易度　EASY	*/
    public static final int EASY		= 1;
    /**	弾幕難易度　NORMAL	*/
    public static final int NORMAL		= 2;
    /**	弾幕難易度　HARD	*/
    public static final int HARD		= 3;
    /**	弾幕難易度　LUNATIC	*/
    public static final int LUNATIC		= 4;

    /**
     * 弾の色		Shot Colors
     * 弾形状データ使用ビット	oooo xxxx xxxx xxxx
     */
	/*public static final int RED			= 0x0000;
	public static final int BLUE		= 0x1000;
	public static final int GREEN		= 0x2000;
	public static final int YELLOW		= 0x3000;
	public static final int PURPLE		= 0x4000;
	public static final int AQUA		= 0x5000;
	public static final int ORANGE		= 0x6000;
	public static final int WHITE		= 0x7000;

	public static final int RANDOM		= 0x8000;
	public static final int RAINBOW		= 0x9000;*/

    public static final int RED			= 0x0000;
    public static final int BLUE		= 0x0001;
    public static final int GREEN		= 0x0002;
    public static final int YELLOW		= 0x0003;
    public static final int PURPLE		= 0x0004;
    public static final int AQUA		= 0x0005;
    public static final int ORANGE		= 0x0006;
    public static final int WHITE		= 0x0007;

    public static final int RANDOM		= 0x0008;
    public static final int RAINBOW		= 0x0009;

    public static final int MASK_COLOR	= 0xF000;

    public static final int 赤弾			= RED;
    public static final int 青弾			= BLUE;
    public static final int 緑弾			= GREEN;
    public static final int 黄弾			= YELLOW;
    public static final int 紫弾			= PURPLE;
    public static final int 水弾			= AQUA;
    public static final int 橙弾			= ORANGE;
    public static final int 白弾			= WHITE;

    public static final int 乱弾			= RANDOM;
    public static final int 虹弾			= RAINBOW;

    /**
     * 弾の形状		Shot Forms
     * 弾形状データ使用ビット	xxxx oooo ooxx xxxx
     */
	/*public static final int FORM_SMALL		= 0x0000;
	public static final int FORM_TINY		= 0x0040;
	public static final int FORM_MEDIUM	= 0x0080;
	public static final int FORM_PEARL		= 0x00C0;
	public static final int FORM_CIRCLE	= 0x0100;
	public static final int FORM_LIGHT		= 0x0140;
	public static final int FORM_SCALE		= 0x0180;
	public static final int FORM_BUTTERFLY	= 0x01C0;
	public static final int FORM_SMALLSTAR	= 0x0200;
	public static final int FORM_STAR		= 0x0240;
	public static final int FORM_RICE		= 0x0280;
	public static final int FORM_CRYSTAL	= 0x02C0;
	public static final int FORM_HEART		= 0x0300;
	public static final int FORM_KUNAI		= 0x0340;
	public static final int FORM_TALISMAN	= 0x0380;
	public static final int FORM_BIGLIGHT	= 0x03C0;
	public static final int FORM_OVAL		= 0x0400;
	public static final int FORM_FAMILIAR	= 0x0440;
	public static final int FORM_ARROW		= 0x0480;
	public static final int FORM_PHANTOM	= 0x0680;
	public static final int FORM_AMULET	= 0x06C0;
	public static final int FORM_KNIFE		= 0x0700;
	public static final int FORM_WIND		= 0x0740;
	public static final int FORM_BIG		= 0x0780;
	public static final int FORM_KISHITU	= 0x07C0;*/

    public static final int MASK_FORM		= 0x0FC0;

    public static final int FORM_SMALL		=  0;
    public static final int FORM_TINY		=  1;
    public static final int FORM_MEDIUM	=  2;
    public static final int FORM_PEARL		=  3;
    public static final int FORM_CIRCLE	=  4;
    public static final int FORM_LIGHT		=  5;
    public static final int FORM_SCALE		=  6;
    public static final int FORM_BUTTERFLY	=  7;
    public static final int FORM_SMALLSTAR	=  8;
    public static final int FORM_STAR		=  9;
    public static final int FORM_RICE		= 10;
    public static final int FORM_CRYSTAL	= 11;
    public static final int FORM_HEART		= 12;
    public static final int FORM_KUNAI		= 13;
    public static final int FORM_TALISMAN	= 14;
    public static final int FORM_BIGLIGHT	= 15;
    public static final int FORM_OVAL		= 16;
    public static final int FORM_FAMILIAR	= 17;
    public static final int FORM_ARROW		= 18;
    public static final int FORM_PHANTOM	= 26;
    public static final int FORM_AMULET	= 27;
    public static final int FORM_KNIFE		= 28;
    public static final int FORM_WIND		= 29;
    public static final int FORM_BIG		= 30;
    public static final int FORM_KISHITU	= 31;


    public static final int 小弾				= FORM_SMALL;
    public static final int 粒弾				= FORM_TINY;
    public static final int 中弾				= FORM_MEDIUM;
    public static final int 真珠弾			= FORM_PEARL;
    public static final int 輪弾				= FORM_CIRCLE;
    public static final int 光弾				= FORM_LIGHT;
    public static final int 鱗弾				= FORM_SCALE;
    public static final int 蝶弾				= FORM_BUTTERFLY;
    public static final int 小星弾			= FORM_SMALLSTAR;
    public static final int 星弾				= FORM_STAR;
    public static final int 米弾				= FORM_RICE;
    public static final int 結晶弾			= FORM_CRYSTAL;
    public static final int ハート弾			= FORM_HEART;
    public static final int クナイ弾			= FORM_KUNAI;
    public static final int 札弾				= FORM_TALISMAN;
    public static final int 大光弾			= FORM_BIGLIGHT;
    public static final int 楕円弾			= FORM_OVAL;
    public static final int 使い魔			= FORM_FAMILIAR;
    public static final int 矢弾				= FORM_ARROW;
    public static final int 幽霊弾			= FORM_PHANTOM;
    public static final int アミュレット弾	= FORM_AMULET;
    public static final int ナイフ弾			= FORM_KNIFE;
    public static final int 風弾				= FORM_WIND;
    public static final int 大弾				= FORM_BIG;
    public static final int 気質弾			= FORM_KISHITU;

    /**
     * レーザーの形状
     */
    public static final int LASER_NORMAL = 0;
    public static final int LASER_GUNGNIR = 1;
    public static final int LASER_LAEVATAIN = 2;

    /**
     * 特殊弾の定義		Special Shot Definition
     */
    public static final int HOMING01		=  10;//ホーミング弾。ホーミングの数値が上がるほど追尾性能が高い
    public static final int HOMING02		=  11;
    public static final int DIFFUSION01	=  20;//拡散アミュレット
    public static final int FALL01			=  30;
    public static final int BOUND01			=  40;//一回だけ跳ね返る
    public static final int BOUND02			=  41;//二回だけ跳ね返る
    public static final int BOUND03			=  42;//三回だけ跳ね返る
    public static final int BOUND04			=  43;//無限に跳ね返る
    public static final int BOUNDFALL01	=  50;
    public static final int FIRE			=  60;//着火効果付与
    public static final int EXPLOSION01	=  70;//地形を破壊する爆発
    public static final int EXPLOSION02	=  71;//地形を破壊しない爆発
    public static final int BRAKE01			= 150;

    public static final int WIND01			= 180;

    public static final int GUNGNIR			= 285;

    /**
     * 標準の重力値		Default Gravity
     */
    public static final double GRAVITY_DEFAULT = -0.03D;

    /**
     * 弾形状		Shot Forms
     */
    /** 小弾 */
    public static final int[] SMALL = {  0,  1,  2,  3,  4,  5,  6,  7};
    /** 粒弾 */
    public static final int[] TINY = {  8,  9, 10, 11, 12, 13, 14, 15};
    /** 中弾 */
    public static final int[] MEDIUM = { 16, 17, 18, 19, 20, 21, 22, 23};
    /** 真珠弾 */
    public static final int[] PEARL = { 24, 25, 26, 27, 28, 29, 30, 31};
    /** 輪弾 */
    public static final int[] CIRCLE = { 32, 33, 34, 35, 36, 37, 38, 39};
    /** 光弾 */
    public static final int[] LIGHT = { 40, 41, 42, 43, 44, 45, 46, 47};
    /** 鱗弾 */
    public static final int[] SCALE = { 48, 49, 50, 51, 52, 53, 54, 55};
    /** 蝶弾 */
    public static final int[] BUTTERFLY = { 56, 57, 58, 59, 60, 61, 62, 63};
    /** 小星弾 */
    public static final int[] SMALLSTAR = { 64, 65, 66, 67, 68, 69, 70, 71};
    /** 星弾 */
    public static final int[] STAR = { 72, 73, 74, 75, 76, 77, 78, 79};
    /** 米弾 */
    public static final int[] RICE = { 80, 81, 82, 83, 84, 85, 86, 87};
    /** 結晶弾 */
    public static final int[] CRYSTAL = { 88, 89, 90, 91, 92, 93, 94, 95};
    /** ハート弾 */
    public static final int[] HEART = { 96, 97, 98, 99,100,101,102,103};
    /** クナイ弾 */
    public static final int[] KUNAI = {104,105,106,107,108,109,110,111};
    /** 札弾 */
    public static final int[] TALISMAN = {112,113,114,115,116,117,118,119};
    /** 大光弾 */
    public static final int[] BIGLIGHT = {120,121,122,123,124,125,126,127};
    /** 楕円弾 */
    public static final int[] OVAL = {128,129,130,131,132,133,134,135};
    /** 使い魔 */
    public static final int[] FAMILIAR = {136,137,138,139,140,141,142,143};
    /** 矢弾 */
    public static final int[] ARROW = {144,145,146,147,148,149,150,151};
    /** アミュレット */
    public static final int[] AMULET = {216,217,218,219,220,221,222,223};
    /** ナイフ弾 */
    public static final int[] KNIFE = {224,225,226,227,228,229,230,231};
    /** 風弾 */
    public static final int[] WIND = {232,233,234,235,236,237,238,239};
    /** 大弾 */
    public static final int[] BIG = {240,241,242,243,244,245,246,247};
    /** 気質弾 */
    public static final int[] KISHITU = {248,249,250,251,252,253,254,255};
    /** レーザー */
    public static final int[] LASER = {512,513,514,515,516,517,518,519};

    /**
     * 弾の色の名前		Shot Color Names(JP)
     */
    public static final String[] COLOR_NAME_JP ={
            "赤", "青", "緑", "黄", "紫", "水色", "橙", "白", "虹色", "ランダム", "暖色", "寒色", "", "", "", ""};

    /**
     * 弾の色の名前		Shot Color Names(EN)
     */
    public static final String[] COLOR_NAME ={
            "Red", "Blue", "Green", "Yellow", "Purple", "Aqua", "Orange", "White", "Rainbow", "Random", "Hot", "Cold", "", "", "", ""};

    /**
     * 弾の名前		Shot Form Names
     */
    public static final String[] SHOT_NAME_JP ={
            "小弾",  "粒弾",  "中弾","真珠弾",  "輪弾",  "光弾",  "鱗弾",  "蝶弾",
            "小星弾",  "星弾",  "米弾","結晶弾","ハート","クナイ",  "札弾","大光弾",
            "楕円弾","使い魔","　矢弾","未定義","未定義","未定義","未定義","未定義",
            "未定義","未定義","未定義","未定義","ナイフ",  "風弾",  "大弾","気質弾"};

    /**
     * 形状ごとの弾のダメージを定義		Shot Damage by Form
     */
    public static final float[] DAMAGE = {	2.4F, 1.0F, 2.8F, 5.0F, 2.0F, 3.4F, 1.6F, 2.8F,
            1.6F, 3.0F, 1.2F, 1.8F, 3.2F, 2.6F, 1.0F, 6.8F,
            3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
            0.0F, 0.0F, 0.0F, 5.0F, 2.6F, 4.0F, 5.0F, 5.0F};

    /**
     * 形状ごとの弾の大きさを定義		Shot Size by Form
     */
    public static final float[] SIZE = {0.30F, 0.15F, 0.50F, 0.30F, 0.30F, 0.30F, 0.15F, 0.30F,
            0.25F, 0.50F, 0.15F, 0.15F, 0.50F, 0.15F, 0.15F, 0.60F,
            0.50F, 1.00F, 0.15F, 0.00F, 0.00F, 0.00F, 0.00F, 0.00F,
            0.00F, 0.00F, 0.00F, 0.40F, 0.30F, 1.00F, 0.90F, 0.90F};

    private static final int[][] ways ={	{ 0},

            { 1},
            { 1, 1},
            { 1, 0, 2, 0},
            { 1, 0, 3, 0},
            { 1, 3, 1},
            { 1, 4, 1},
            { 1, 5, 1},
            { 1, 3, 3, 1},
            { 1, 5, 3, 0},
            { 1, 4, 4, 1},

            { 1, 6, 4, 0},
            { 1, 5, 5, 1},
            { 1, 3, 5, 3, 1},
            { 1, 3, 6, 3, 1},
            { 1, 4, 5, 4, 1},
            { 1, 4, 6, 4, 1},
            { 1, 4, 7, 4, 1},
            { 1, 3, 5, 5, 3, 1},
            { 1, 3, 6, 6, 3},
            { 1, 3, 6, 6, 3, 1},

            { 1, 5, 9, 5, 1},
            { 1, 3, 7, 7, 3, 1},
            { 1, 6, 9, 6, 1},
            { 1, 4, 7, 7, 4, 1},
            { 1, 3, 5, 7, 5, 3, 1},
            { 1, 3, 5, 8, 5, 3, 1},
            { 1, 3, 5, 9, 5, 3, 1},
            { 1, 3, 6, 8, 6, 3, 1},
            { 1, 3, 6, 9, 6, 3, 1},
            { 1, 4, 6, 8, 6, 4, 1},

            { 1, 3, 6, 11, 6, 3, 1},
            { 1, 3, 6, 12, 6, 3, 1},
            { 0},
            { 0},
            { 0},
            { 0},
            { 0},
            { 0},
            { 0},
            { 0},

            { 0},
            { 0},
            { 0},
            { 1, 3, 6, 12, 12, 6, 3, 1}

    };
}
