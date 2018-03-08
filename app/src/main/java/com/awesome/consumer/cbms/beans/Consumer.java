package com.awesome.consumer.cbms.beans;

/**
 * Create: 26/12/17 , 上午10:38
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 26/12/17)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */

public class Consumer {
    public interface Language{
        int LANGUAGE_ZH_CN = 0x01;
        int LANGUAGE_ZH_TW = 0x02;
        int LANGUAGE_EN = 0x03;
        int LANGUAGE_KM = 0x04;
    }
    public static final int ACCOUNT_PERSON = 0x01;
    public static final int ACCOUNT_COMPANY = 0x00;
    public int id, accountType;
    public String name;//最多60字
    public int lang;//官方语言编号，zh-cn/en
    public String username;//登录账号，最多30字
    public String password;//登录密码，最多30字

    public int company_type;//机构类型，1=法人，2=事业单位，3=其他团体

    public String pics;//当cart_type=2时必填	json	证件照片，格式[“pic1.jpg”,”pic2.jpg”]
    public String card_code;//证件号，最多60字
    public String card_name;//当cart_type=3时必填	string	证件名称，最多30字
    public String bondsman1_name;//当cart_type=3时必填	string	证明人1名称，最多30字
    public String bondsman1_code;//当cart_type=3时必填	string	证明人1户口号，最多30字
    public String bondsman2_name;//当cart_type=3时必填	string	证明人2名称，最多30字
    public String bondsman2_code;//当cart_type=3时必填	string	证明人2户口号，最多30字
    public String contact;//联系人姓名

    public String contact_ways;//json	联系方式，格式{‘qq’: ‘123123’, ‘wechat’: ‘sfsf’, …}
    public int gender;//性别，1=小姐/女士，2=先生
    public String address;//地址
    public String reserve_information;//预留信息，最多30字
    public String parentcode;//上线户口号
    public String parentname;//上线户口名

    public int card_type;//【机构：证件类型，2=营业执照，3=其它】
                          //【个人：证件类型，1=身份证，2=护照，3=其它】

    /**
     * 两者共有信息：
     lang	是	string	常用语言编号，zh-cn/en
     username	是	string	登录账号，最多30字
     password	是	string	登录密码，最多30字
     name	是	string	姓名，最多60字
     contact_ways	是	json	联系方式，格式{‘qq’: ‘123123’, ‘wechat’: ‘sfsf’, …}
     card_type	是	int	证件类型，1=身份证，2=护照，3=其它
     pics	当cart_type=1/2时必填	json	证件照片，格式[“pic1.jpg”,”pic2.jpg”]
     card_code	是	string	证件号
     card_name	当cart_type=3时必填	string	证件名称
     bondsman1_name	当cart_type=3时必填	string	证明人1名称，最多30字
     bondsman1_code	当cart_type=3时必填	string	证明人1户口号，最多30字
     bondsman2_name	当cart_type=3时必填	string	证明人2名称，最多30字
     bondsman2_code	当cart_type=3时必填	string	证明人2户口号，最多30字
     gender	否	int	性别，1=小姐/女士，2=先生
     address	否	string	家庭地址
     reserve_information	是	string	预留信息，最多三十个字
     parentcode	否	string	上线户口号
     parentname	否	string	上线名称
     */

    public Consumer(){
        accountType = ACCOUNT_COMPANY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
