package com.awesome.consumer.cbms.ui.activity.loginandregister.company;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.ui.activity.BaseActivity;
import com.awesome.consumer.cbms.ui.widget.BottomMenuDialog;

import java.util.LinkedList;

public class InputSNSActivity extends BaseActivity {
    private LinkedList<View> linkedListView = new LinkedList();
    private View itemView = null;
    private LinearLayout llSNS = null;
    private BottomMenuDialog bottomMenuDialog = null;
    private int [] snsArray = {R.string.sms_line,
            R.string.sms_phone,
            R.string.sms_potato,
            R.string.sms_telegram,
            R.string.sms_sms,
            R.string.sms_wechat,
            R.string.sms_whatsapp};

//    private String[] smsStringArray = null;
    private int currentViewIndex = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_sns);
        findViewById(R.id.btn_next).setOnClickListener(this);
        llSNS = (LinearLayout) findViewById(R.id.ll_sns);
        mHandler.sendEmptyMessage(ADD_ITEM);
//        smsStringArray = getResources().getStringArray(R.array.sms);

        setPageTitle(R.string.input_sns_title);
        showBackButton(true);
        setRightMenu(R.string.save);
    }

    private static final int ADD_ITEM = 0x01;
    private static final int DELETE_ITEM = 0x02;
    private static final int UPDATE_SMS = 0x03;
    private Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            int index = -1;
            switch (msg.what) {
                case ADD_ITEM:
                    addItem();
                    break;

                case DELETE_ITEM:
                    index = formatToIntByObject(msg.obj);
                    if (0 <= index) {
                        deleteItem(index);
                    }
                    break;

                case UPDATE_SMS:
                    for (View view : linkedListView) {
                        if (formatToIntByObject(view.getTag()) == currentViewIndex) {
                            if(snsArray.length > msg.arg1){
                                TextView et = (TextView) view.findViewById(R.id.et_sns_type);
                                et.setText(snsArray[msg.arg1]);
                            }
                        }
                    }
                    break;
            }
        }
    };

    private void addItem() {
        itemView = LayoutInflater.from(InputSNSActivity.this).inflate(R.layout.item_sns, null);
        if (null != linkedListView){
            itemView.setTag(linkedListView.size());
            itemView.findViewById(R.id.img_delete).setTag(linkedListView.size());
            itemView.findViewById(R.id.et_sns_type).setTag(linkedListView.size());
            itemView.findViewById(R.id.et_sns_type).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    showSmsDialog();
                    currentViewIndex = formatToIntByObject(v.getTag());
                }
            });
            if (0 == linkedListView.size()) {
                itemView.findViewById(R.id.img_delete).setVisibility(View.INVISIBLE);
            }else{
                itemView.findViewById(R.id.img_delete).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Message msg = mHandler.obtainMessage();
                        msg.what = DELETE_ITEM;
                        msg.obj = v.getTag();
                        mHandler.sendMessage(msg);
                    }
                });
            }
            linkedListView.add(itemView);
        }
        if (null != llSNS) {
            llSNS.addView(itemView);
        }
    }

    private void deleteItem(int index){
        View view = null;
        for (View v : linkedListView) {
            if (formatToIntByObject(v.getTag()) == index){
                view = v;
                break;
            }
        }

        if (null != view && null != llSNS) {
            try {
                llSNS.removeView(view);
            } catch (Exception e) {
            }
        }
    }


    private int formatToIntByObject(Object obj){
        int index = -1;
        try {
            index = Integer.valueOf(obj.toString());
        } catch (Exception e) {
        }
        return index;
    }


    private void showSmsDialog(){
        if (bottomMenuDialog == null) {
            bottomMenuDialog = new BottomMenuDialog.Builder(InputSNSActivity.this)
                    .setTitle(null)
                    .addMenu(R.string.sms_line, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateSmsMessage(0);
                        }
                    }).addMenu(R.string.sms_phone, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateSmsMessage(1);    }
                    }).addMenu(R.string.sms_potato, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateSmsMessage(2);}
                        }).addMenu(R.string.sms_telegram, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateSmsMessage(3);}
                        }).addMenu(R.string.sms_sms, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateSmsMessage(4);}
                        }).addMenu(R.string.sms_wechat, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateSmsMessage(5);}
                        }).addMenu(R.string.sms_whatsapp, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomMenuDialog.dismiss();
                            sendUpdateSmsMessage(6);
                        }
                    }).create();

            bottomMenuDialog.show();
        }else{
            bottomMenuDialog.show();
        }
    }

    private void sendUpdateSmsMessage(int index){
        Message msg = mHandler.obtainMessage();
        msg.what = UPDATE_SMS;
        msg.arg1 = index;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                mHandler.sendEmptyMessage(ADD_ITEM);
                break;
            case R.id.btn_right:
                finish();
                break;
        }
    }
}
