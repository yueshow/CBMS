package com.awesome.consumer.cbms.beans.test;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Create: 22/01/18 , 上午11:39
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 22/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class Member extends ResultData{
    @SerializedName("is_set_pay_password")
    public boolean is_set_pay_password;

    @SerializedName("id")
    public Integer id;

    @SerializedName("gender")
    public Integer gender;

    @SerializedName("username")
    public String username;

    @SerializedName("name")
    public String name;

    @SerializedName("code")
    public String code;

    @SerializedName("address")
    public String address;

    @SerializedName("member_info_id")
    public Integer member_info_id;

    @SerializedName("type")
    public Integer memberType;//类型，1=机构用户，2=个人用户

    @SerializedName("butt_type")
    public Integer butt_type;

    @SerializedName("company_type")
    public Integer company_type;

    @SerializedName("card_type")
    public Integer card_type;



    @SerializedName("bondsman1_id")
    public Integer bondsman1_id;

    @SerializedName("bondsman2_id")
    public Integer bondsman2_id;

    @SerializedName("is_enable_view_members")
    public Integer is_enable_view_members;

    @SerializedName("is_enable_view_tran_detail")
    public Integer is_enable_view_tran_detail;

    @SerializedName("contacts")
    public String contacts;

    @SerializedName("parentcode")
    public String parentcode;

    @SerializedName("parentid")
    public Integer parentid;

    @SerializedName("parentname")
    public String parentname;

    @SerializedName("card_name")
    public String card_name;

    @SerializedName("card_code")
    public String card_code;

    @SerializedName("reserve_information")
    public String reserve_information;

    @SerializedName("bondsman1_name")
    public String bondsman1_name;

    @SerializedName("bondsman2_name")
    public String bondsman2_name;

    @SerializedName("bondsman1_code")
    public String bondsman1_code;

    @SerializedName("bondsman2_code")
    public String bondsman2_code;

    @SerializedName("sub_count")
    public Integer sub_count;


    @SerializedName("view_permissions")
    public String view_permissions;

    @SerializedName("edit_permissions")
    public String edit_permissions;

    @SerializedName("contact_ways")
    public List<Contacts> listContacts;

    @SerializedName("accounts")
    public List<Account> listAccounts;

    public String language;

    private String accountsToString(){
        if (null == listAccounts) return null;
        StringBuilder sb = new StringBuilder();
        for (Account account : listAccounts) {
            sb.append("account [ ").append(account.toString()).append("]\n");
        }
        return sb.toString();
    }

    @SerializedName("card_pics")
    public List<String> listPics;

    @SerializedName("view_permission_names")
    public List<String> view_permission_names;

    @SerializedName("edit_permission_names")
    public List<String> edit_permission_names;

    public String toString(){
        return "Member : [is_set_pay_password : " + is_set_pay_password
                + ",\n id : " + id
                + ",\n member_info_id : " + member_info_id
                + ",\n memberType : " + memberType
                + ",\n butt_type : " + butt_type
                + "\n----------------"
                + ",\n code : " + code
                + ",\n username : " + username
                + ",\n name : " + name
                + ",\n gender : " + gender
                + ",\n address : " + address
                + ",\n contacts : " + contacts
                + ",\n sub_count : " + sub_count
                + "\n----------------"
                + ",\n company_type : " + company_type
                + ",\n card_type : " + card_type
                + ",\n card_name : " + card_name
                + ",\n card_code : " + card_code
                + "\n----------------"
                + ",\n parentid : " + parentid
                + ",\n parentcode : " + parentcode
                + ",\n parentname : " + parentname
                + "\n----------------"
                + ",\n bondsman1_id : " + bondsman1_id
                + ",\n bondsman1_code : " + bondsman1_code
                + ",\n bondsman1_name : " + bondsman1_name
                + "\n----------------"
                + ",\n bondsman2_id : " + bondsman2_id
                + ",\n bondsman2_code : " + bondsman2_code
                + ",\n bondsman2_name : " + bondsman2_name
                + "\n----------------"
                + ",\n reserve_information : " + reserve_information
                + ",\n is_enable_view_members : " + is_enable_view_members
                + ",\n is_enable_view_tran_detail : " + is_enable_view_tran_detail
                + ",\n view_permissions : " + view_permissions
                + ",\n edit_permissions : " + edit_permissions

                + "\n" + picToString()
                + "\n" + contactToString()
                + "\n" + accountsToString()
                + "\n]";
    }

    private String contactToString(){
        StringBuilder sb = new StringBuilder();
        if (null != listContacts){
            for (Contacts item : listContacts) {
                sb.append(item.toString() + "\n");
            }
        }
        return sb.toString();
    }

    private String picToString(){
        StringBuilder sb = new StringBuilder();
        if (null != listPics){
            for (String item : listPics) {
                sb.append(item + ",\t");
            }
        }
        return sb.toString();
    }

    public static class Contacts{
        @SerializedName("type")
        public String type;

        @SerializedName("values")
        public List<String> values;

        public String toString(){
            StringBuilder sb = new StringBuilder();
            for (String value : values) {
                sb.append(value);
            }
            return "Contacts : [Typp : " + type
                    + "values : [" + sb.toString() + "]]";
        }
    }

    public static class Account{
        @SerializedName("butt_type")
        public Integer butt_type;

        @SerializedName("members")
        public List<Member> members;

        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("\nbutt_type : ");
            sb.append(butt_type);
            sb.append("\nMembers : ");
            for (Member member : members) {
                sb.append(member.toString());
                sb.append("\n");
            }
            sb.append("\n");
            return sb.toString();
        }
    }

    private LinkedList<File> certifyPhotoFileList;
    public boolean addCertifyPhotoFile(File file){
        if (null == certifyPhotoFileList){
            certifyPhotoFileList = new LinkedList();
        }
        return certifyPhotoFileList.add(file);
    }
    public File removeCertifyPhotoFile(int index){
        if (null != certifyPhotoFileList && 0 <= index && index < certifyPhotoFileList.size()){
            return certifyPhotoFileList.remove(index);
        }
        return null;
    }
    public LinkedList<File> getCertifyPhotoFileList(){
        return certifyPhotoFileList;
    }
    public void destoryCertifyPhotoFileList(){
        if (null != certifyPhotoFileList){
            certifyPhotoFileList.clear();
            certifyPhotoFileList = null;
        }
    }
}
