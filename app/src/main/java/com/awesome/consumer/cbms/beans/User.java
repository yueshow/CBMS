package com.awesome.consumer.cbms.beans;

import com.awesome.consumer.cbms.beans.test.Member;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Create: 18/01/18 , 下午9:45
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 18/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class User {
//    public boolean is_set_pay_password;//中否设置了交易密码
//    public Integer id,          //
//            gender,             //
//            member_info_id,     //
//            type,               //
//            company_type,       //
//            card_type,          //
//            bondsman1_id,       //
//            bondsman2_id,       //
//            parentid,       //
//            sub_count,       //
//            is_enable_view_members,       //
//            is_enable_view_tran_detail,       //
//            butt_type;          //
//    public String username,     //
//            name,               //
//            code, //
//            contacts,           //
//            address,            //
//            parentcode,            //
//            parentname,            //
//            card_name,            //
//            card_code,            //
//            reserve_information,            //
//            bondsman1_name,            //
//            bondsman2_name,            //
//            bondsman1_code,            //
//            bondsman2_code,            //
//            view_permissions,   //
//            edit_permissions;   //
//    public List<String> card_pics, view_permission_names, edit_permission_names;
//    public Contacts contact_ways;//
//
//    class Accounts{
//        public Integer butt_type;
//        public List<User> list;
//    }
//
//    public void test(){
//        List<Contacts> persons = new ArrayList<Contacts>();
//        Gson gson = new Gson();
//        String str = gson.toJson(persons);
//    }
//    class Contacts{//联系方式
//        String type;
//        String[] values;
//    }
//
//    public String toString(){
//        return "id=" + id +
//                "gender =             " +gender +
//                "member_info_id =     " +member_info_id +
//                "type =              " +type +
//                "company_type =      " +company_type +
//                "card_type =         " +card_type +
//                "bondsman1_id =        " +bondsman1_id +
//                "bondsman2_id =      " +bondsman2_id +
//                "parentid =        " +parentid +
//                "sub_count =        " +sub_count +
//                "is_enable_view_members =        " +is_enable_view_members +
//                "is_enable_view_tran_detail =        " +is_enable_view_tran_detail +
//                "butt_type =        " + butt_type +
//                "username =        " + username +
//                "name =        " + name +
//                "code =        " + code +
//                "contacts =        " + contacts +
//                "address =        " + address +
//                "parentcode =        " + parentcode +
//                "parentname =        " + parentname +
//                "card_name =        " + card_name +
//                "card_code =         " + card_code +
//                "reserve_information =        " + reserve_information +
//                "bondsman1_name =        " + bondsman1_name +
//                "bondsman2_name =        " + bondsman2_name +
//                "bondsman1_code =        " + bondsman1_code +
//                "bondsman2_code =        " + bondsman2_code +
//                "view_permissions =        " + view_permissions +
//                "edit_permissions =        " + edit_permissions;
//    }

    public String token;
    public Member member, memberInfo;
}