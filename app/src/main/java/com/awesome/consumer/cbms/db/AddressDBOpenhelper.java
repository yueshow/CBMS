package com.awesome.consumer.cbms.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Create: 02/03/18 , 下午3:56
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 02/03/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class AddressDBOpenhelper extends SQLiteOpenHelper {
    public AddressDBOpenhelper(Context context) {
        super(context, "address.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE address (" +
                "id integer primary key autoincrement, " +
                "parentId integer, " +
                "zh varchar(40), " +
                "tw varchar(40), " +
                "en varchar(40), " +
                "km varchar(40), " +
                "FOREIGN KEY(parentId) REFERENCES address(id))");

        insertCounrty(db);
        insertProvince(db);
        insertCity(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void insertCounrty(SQLiteDatabase db){
        db.execSQL("INSERT INTO address (parentId, zh, tw, en, km) values " +
                "('0', '中国', '中國', 'China', 'ប្រទេសចិន')," +
                "('0', '美国', '美國', 'The United States', 'អាមេរិច')," +
                "('0', '柬埔寨', '柬埔寨', 'Cambodia', 'កម្ពុជា')");
    }

    private void insertProvince(SQLiteDatabase db){
        db.execSQL("INSERT INTO address (parentId, zh, tw) values " +
                "(1, '北京市', '北京市')," +
                "(1, '上海市', '上海市')," +
                "(1, '天津市', '天津市')," +
                "(1, '重庆市', '重慶市')," +
                "(1, '台湾省', '臺灣省')," +
                "(1, '广东省', '廣東省')," +
                "(1, '浙江省', '浙江省')," +
                "(1, '江苏省', '江蘇省')," +
                "(1, '山东省', '山東省')," +
                "(1, '福建省', '福建省')," +
                "(1, '安徽省', '安徽省')," +
                "(1, '四川省', '四川省')," +
                "(1, '湖北省', '湖北省')," +
                "(1, '河北省', '河北省')," +
                "(1, '云南省', '雲南省')," +
                "(1, '黑龙江省', '黑龍江省')," +
                "(1, '吉林省', '吉林省')," +
                "(1, '辽宁省', '遼寧省')," +
                "(1, '海南省', '海南省')," +
                "(1, '湖南省', '湖南省')," +
                "(1, '河南省', '河南省')," +
                "(1, '贵州省', '貴州省')," +
                "(1, '江西省', '江西省')," +
                "(1, '陕西省', '陝西省')," +
                "(1, '山西省', '山西省')," +
                "(1, '青海省', '青海省')," +
                "(1, '甘肃省', '甘肅省')," +
                "(1, '广西壮族自治区', '廣西壯族自治區')," +
                "(1, '宁夏回族自治区', '寧夏回族自治區')," +
                "(1, '西藏自治区', '西藏自治區')," +
                "(1, '内蒙古自治区', '內蒙古自治區')," +
                "(1, '新疆维吾尔自治区', '新疆維吾爾自治區')," +
                "(1, '香港特别行政区', '香港特別行政區')," +
                "(1, '澳门特别行政区', '澳門特別行政區')");
    }

    private void insertCity(SQLiteDatabase db){
        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +
                "(4, '东城区'  , ''), " +
                "(4, '西城区'  , ''), " +
                "(4, '朝阳区'  , ''), " +
                "(4, '丰台区'  , ''), " +
                "(4, '石景山区', ''), " +
                "(4, '海淀区'  , ''), " +
                "(4, '门头沟区', ''), " +
                "(4, '房山区'  , ''), " +
                "(4, '通州区'  , ''), " +
                "(4, '顺义区'  , ''), " +
                "(4, '昌平区'  , ''), " +
                "(4, '大兴区'  , ''), " +
                "(4, '怀柔区'  , ''), " +
                "(4, '平谷区'  , ''), " +
                "(4, '密云县'  , ''), " +
                "(4, '延庆县'  , '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +
                "(5, '黄浦区', ''), " +
                "(5, '卢湾区', ''), " +
                "(5, '徐汇区', ''), " +
                "(5, '长宁区', ''), " +
                "(5, '静安区', ''), " +
                "(5, '普陀区', ''), " +
                "(5, '闸北区', ''), " +
                "(5, '虹口区', ''), " +
                "(5, '杨浦区', ''), " +
                "(5, '闵行区', ''), " +
                "(5, '宝山区', ''), " +
                "(5, '嘉定区', ''), " +
                "(5, '浦东新区', ''), " +
                "(5, '金山区', ''), " +
                "(5, '松江区', ''), " +
                "(5, '青浦区', ''), " +
                "(5, '南汇区', ''), " +
                "(5, '奉贤区', ''), " +
                "(5, '崇明县', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +
                "(6, '和平区', ''), " +
                "(6, '河东区', ''), " +
                "(6, '河西区', ''), " +
                "(6, '南开区', ''), " +
                "(6, '河北区', ''), " +
                "(6, '红桥区', ''), " +
                "(6, '塘沽区', ''), " +
                "(6, '汉沽区', ''), " +
                "(6, '大港区', ''), " +
                "(6, '东丽区', ''), " +
                "(6, '西青区', ''), " +
                "(6, '津南区', ''), " +
                "(6, '北辰区', ''), " +
                "(6, '武清区', ''), " +
                "(6, '宝坻区', ''), " +
                "(6, '宁河县', ''), " +
                "(6, '静海县', ''), " +
                "(6, '蓟县 ', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +
                "(7, '万州区', ''), " +
                "(7, '涪陵区', ''), " +
                "(7, '渝中区', ''), " +
                "(7, '大渡口区', ''), " +
                "(7, '江北区', ''), " +
                "(7, '沙坪坝区', ''), " +
                "(7, '九龙坡区', ''), " +
                "(7, '南岸区', ''), " +
                "(7, '北碚区', ''), " +
                "(7, '万盛区', ''), " +
                "(7, '双桥区', ''), " +
                "(7, '渝北区', ''), " +
                "(7, '巴南区', ''), " +
                "(7, '黔江区', ''), " +
                "(7, '长寿区', ''), " +
                "(7, '綦江县', ''), " +
                "(7, '潼南县', ''), " +
                "(7, '铜梁县', ''), " +
                "(7, '大足县', ''), " +
                "(7, '荣昌县', ''), " +
                "(7, '璧山县', ''), " +
                "(7, '梁平县', ''), " +
                "(7, '城口县', ''), " +
                "(7, '丰都县', ''), " +
                "(7, '垫江县', ''), " +
                "(7, '武隆县', ''), " +
                "(7, '忠县', ''), " +
                "(7, '开县', ''), " +
                "(7, '云阳县', ''), " +
                "(7, '奉节县', ''), " +
                "(7, '巫山县', ''), " +
                "(7, '巫溪县', ''), " +
                "(7, '石柱土家族自治县', ''), " +
                "(7, '秀山土家族苗族自治县', ''), " +
                "(7, '酉阳土家族苗族自治县', ''), " +
                "(7, '彭水苗族土家族自治县', ''), " +
                "(7, '江津区', ''), " +
                "(7, '合川区', ''), " +
                "(7, '永川区', ''), " +
                "(7, '南川区', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾
                "(8, '台北市', '臺北市', 'Taipei City'), " +
                "(8, '新北市', '新北市', 'New Taipei City'), " +
                "(8, '桃园市', '桃園市', 'Taoyuan City'), " +
                "(8, '台中市', '台中市', 'Taichung City'), " +
                "(8, '台南市', '台南市', 'Tainan City'), " +
                "(8, '高雄市', '高雄市', 'Kaohsiung City'), " +
                "(8, '基隆市', '基隆市', 'Keelung City'), " +
                "(8, '新竹市', '新竹市', 'Hsinchu City'), " +
                "(8, '嘉义市', '嘉義市', 'Chiayi City'), " +
                "(8, '新竹县', '新竹縣', 'Hsinchu County'), " +
                "(8, '苗栗县', '苗栗縣', 'Miaoli County'), " +
                "(8, '彰化县', '彰化縣', 'Changhua County'), " +
                "(8, '南投县', '南投縣', 'Nantou County'), " +
                "(8, '云林县', '雲林縣', 'Yunlin County'), " +
                "(8, '嘉义县', '嘉義縣', 'Chiayi County'), " +
                "(8, '屏东县', '屏東縣', 'Pingtung County'), " +
                "(8, '宜兰县', '宜蘭縣', 'Yilan County'), " +
                "(8, '花莲县', '花蓮縣', 'Hualien County'), " +
                "(8, '台东县', '台東縣', 'Taitung County'), " +
                "(8, '澎湖县', '澎湖縣', 'Penghu County'), " +
                "(8, '金门县', '金門縣', 'Kinmen County'), " +
                "(8, '连江县', '連江縣', 'Lienchiang County')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-台北
                "(131, '中正区', '中正區', 'Zhongzheng District'), " +
                "(131, '大同区', '大同區', 'Datong District'), " +
                "(131, '中山区', '中山區', 'Zhongshan District'), " +
                "(131, '万华区', '萬華區', 'Mankah'), " +
                "(131, '信义区', '信義區', ''), " +
                "(131, '松山区', '松山區', ''), " +
                "(131, '大安区', '大安區', ''), " +
                "(131, '南港区', '南港區', ''), " +
                "(131, '北投区', '北投區', ''), " +
                "(131, '内湖区', '內湖區', ''), " +
                "(131, '士林区', '士林區', ''), " +
                "(131, '文山区', '文山區', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-新北
                "(132, '板桥区', '板橋區', ''), " +
                "(132, '汐止区', '汐止區', ''), " +
                "(132, '新店区', '新店區', ''), " +
                "(132, '永和区', '永和區', ''), " +
                "(132, '中和区', '中和區', ''), " +
                "(132, '土城区', '土城區', ''), " +
                "(132, '树林区', '樹林區', ''), " +
                "(132, '三重区', '三重區', ''), " +
                "(132, '新庄区', '新莊區', ''), " +
                "(132, '芦洲区', '蘆洲區', ''), " +
                "(132, '瑞芳区', '瑞芳區', ''), " +
                "(132, '三峡区', '三峽區', ''), " +
                "(132, '莺歌区', '鶯歌區', ''), " +
                "(132, '淡水区', '淡水區', ''), " +
                "(132, '万里区', '萬裏區', ''), " +
                "(132, '金山区', '金山區', ''), " +
                "(132, '深坑区', '深坑區', ''), " +
                "(132, '石碇区', '石碇區', ''), " +
                "(132, '平溪区', '平溪區', ''), " +
                "(132, '双溪区', '雙溪區', ''), " +
                "(132, '贡寮区', '貢寮區', ''), " +
                "(132, '坪林区', '坪林區', ''), " +
                "(132, '乌来区', '烏來區', ''), " +
                "(132, '泰山区', '泰山區', ''), " +
                "(132, '林口区', '林口區', ''), " +
                "(132, '五股区', '五股區', ''), " +
                "(132, '八里区', '八裏區', ''), " +
                "(132, '三芝区', '三芝區', ''), " +
                "(132, '石门区', '石門區', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-桃园
                "(133, '桃园区', '桃園區', ''), " +
                "(133, '中坜区', '中壢區', ''), " +
                "(133, '平镇区', '平鎮區', ''), " +
                "(133, '八德区', '八德區', ''), " +
                "(133, '杨梅区', '楊梅區', ''), " +
                "(133, '芦竹区', '蘆竹區', ''), " +
                "(133, '大溪区', '大溪區', ''), " +
                "(133, '龙潭区', '龍潭區', ''), " +
                "(133, '龟山区', '龜山區', ''), " +
                "(133, '大园区', '大園區', ''), " +
                "(133, '观音区', '觀音區', ''), " +
                "(133, '新屋区', '新屋區', ''), " +
                "(133, '复兴区', '復興區', '')");


        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-台中
                "(134, '中区', '中區', ''), " +
                "(134, '东区', '東區', ''), " +
                "(134, '南区', '南區', ''), " +
                "(134, '西区', '西區', ''), " +
                "(134, '北区', '北區', ''), " +
                "(134, '北屯区', '北屯區', ''), " +
                "(134, '西屯区', '西屯區', ''), " +
                "(134, '南屯区', '南屯區', ''), " +
                "(134, '太平区', '太平區', ''), " +
                "(134, '大里区', '大裏區', ''), " +
                "(134, '雾峰区', '霧峰區', ''), " +
                "(134, '乌日区', '烏日區', ''), " +
                "(134, '丰原区', '豐原區', ''), " +
                "(134, '后里区', '後裏區', ''), " +
                "(134, '潭子区', '潭子區', ''), " +
                "(134, '大雅区', '大雅區', ''), " +
                "(134, '神冈区', '神岡區', ''), " +
                "(134, '石冈区', '石岡區', ''), " +
                "(134, '东势区', '東勢區', ''), " +
                "(134, '新社区', '新社區', ''), " +
                "(134, '和平区', '和平區', ''), " +
                "(134, '大肚区', '大肚區', ''), " +
                "(134, '沙鹿区', '沙鹿區', ''), " +
                "(134, '龙井区', '龍井區', ''), " +
                "(134, '梧栖区', '梧棲區', ''), " +
                "(134, '清水区', '清水區', ''), " +
                "(134, '大甲区', '大甲區', ''), " +
                "(134, '外埔区', '外埔區', ''), " +
                "(134, '大安区', '大安區', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-台南
                "(135, '', '', ''), " +
                "(135, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-高雄
                "(136, '', '', ''), " +
                "(136, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-基隆
                "(137, '', '', ''), " +
                "(137, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-新竹
                "(138, '', '', ''), " +
                "(138, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-嘉义
                "(139, '', '', ''), " +
                "(139, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-新竹
                "(140, '', '', ''), " +
                "(140, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-苗栗
                "(141, '', '', ''), " +
                "(141, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-彰化
                "(142, '', '', ''), " +
                "(142, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-南投
                "(143, '', '', ''), " +
                "(143, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-云林
                "(144, '', '', ''), " +
                "(144, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-嘉义
                "(145, '', '', ''), " +
                "(145, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-屏东
                "(146, '', '', ''), " +
                "(146, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-宜兰
                "(147, '', '', ''), " +
                "(147, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-花莲
                "(148, '', '', ''), " +
                "(148, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-台东
                "(149, '', '', ''), " +
                "(149, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-澎湖
                "(150, '', '', ''), " +
                "(150, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-金门
                "(151, '', '', ''), " +
                "(151, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw, en) values" +//台湾-连江
                "(152, '', '', ''), " +
                "(152, '', '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//广东
                "(9, '', ''), " +
                "(9, '', '')");


        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//浙江
                "(10, '', ''), " +
                "(10, '', '')");


        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//江苏
                "(11, '', ''), " +
                "(11, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//山东
                "(12, '', ''), " +
                "(12, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//福建
                "(13, '', ''), " +
                "(13, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//安徽
                "(14, '', ''), " +
                "(14, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//四川
                "(15, '', ''), " +
                "(15, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//湖北
                "(16, '', ''), " +
                "(16, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//河北
                "(17, '', ''), " +
                "(17, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//云南
                "(18, '', ''), " +
                "(18, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//黑龙江
                "(19, '', ''), " +
                "(19, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//吉林
                "(20, '', ''), " +
                "(20, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//辽宁
                "(21, '', ''), " +
                "(21, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//海南
                "(22, '', ''), " +
                "(22, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//湖南
                "(23, '', ''), " +
                "(23, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//河南
                "(24, '', ''), " +
                "(24, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//贵州
                "(25, '', ''), " +
                "(25, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//江西
                "(26, '', ''), " +
                "(26, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//陕西
                "(27, '', ''), " +
                "(27, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//山西
                "(28, '', ''), " +
                "(28, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//青海
                "(29, '', ''), " +
                "(29, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//甘肃
                "(30, '', ''), " +
                "(30, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//广西
                "(31, '', ''), " +
                "(31, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//宁夏
                "(32, '', ''), " +
                "(32, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//西藏
                "(33, '', ''), " +
                "(33, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//内蒙
                "(34, '', ''), " +
                "(34, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//新疆
                "(35, '', ''), " +
                "(35, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//香港
                "(36, '', ''), " +
                "(36, '', '')");

        db.execSQL("INSERT INTO address (parentId, zh, tw) values" +//澳门
                "(37, '', ''), " +
                "(37, '', '')");
    }
}
