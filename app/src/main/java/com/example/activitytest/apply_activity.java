package com.example.activitytest;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.adapter.CustomSpinnerArrayAdapter;
import com.example.club.control.example.ActivityManager;
import com.example.club.control.example.PlaceManager;
import com.example.club.model.Beanactivity;
import com.example.club.model.Beanplace;
import com.example.club.util.BaseException;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import any.CustomDialog;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class apply_activity extends AppCompatActivity {
    public static  final int CHOOSE_PHOTO=2;
    private TextView btnbegin;
    private TextView btnover;
    private Calendar cal;
    private int year,month,day;
    private ImageView picture;
    private byte[] datas=null;
    List<Beanplace> place1=new ArrayList<Beanplace>();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            vChangeFontStyle();
        }
    };
    private Handler mhandler=new Handler(){
        public void handleMessage(Message msg){
            CustomDialog customDialog = new CustomDialog(apply_activity.this);
            customDialog.setTitle("提醒");
            if(msg.what==1){
            customDialog.setMessage("活动标题未填写");}
            if(msg.what==2){
                customDialog.setMessage("活动地点未填写");}
            if(msg.what==3){
                customDialog.setMessage("活动开始时间未填写");}
            if(msg.what==4){
                customDialog.setMessage("活动结束时间未填写");}
            if(msg.what==5){
                customDialog.setMessage("活动内容未填写");}
            if(msg.what==6){
                customDialog.setMessage("活动图片未填写");}
            if(msg.what==7){
                customDialog.setMessage("活动开始时间要早于结束时间");}
            customDialog.setConfirm("confirm", new CustomDialog.IOnConfirmListener(){
                @Override
                public void onConfirm(CustomDialog dialog) {
                }
            });
            customDialog.show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_activity);
        Intent it = getIntent();
        final int ID = it.getIntExtra("id",0);
        picture=(ImageView)findViewById(R.id.TextView_apply_activity_picture1);
        final EditText editText1 = (EditText) findViewById(R.id.activity_title);
        final Spinner editText2 = (Spinner) findViewById(R.id.Button_apply_activity_palce);
        final TextView editText3 = (TextView) findViewById(R.id.TextView_apply_activity_starttime1);
        final TextView editText4 = (TextView) findViewById(R.id.TextView_apply_activity_finishtime1);
        final EditText editText5 = (EditText) findViewById(R.id.editText6);
        Button btnrel2=(Button)findViewById(R.id.TextView_apply_activity_release2);
        Button btnrel1=(Button)findViewById(R.id.button_apply_activity_exit);
        btnrel1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        getbeginDate();
        new Thread(){
            @Override
            public void run() {
                try {
                    PlaceManager place=new PlaceManager();
                    place1=new ArrayList<Beanplace>();
                    place1=place.getPlaceall();
                    Message message=new Message();
                    message.obj=place1;
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        picture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(apply_activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(apply_activity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }
            }
        });
        btnrel2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                            Date date1 = null;
                            Date date2 = null;
                            try {
                            date1 = format1.parse(editText3.getText().toString());
                            date2 = format1.parse(editText4.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if(editText1.getText().toString().equals("")){
                                Message message=new Message();
                                message.what=1;
                                mhandler.sendMessage(message);
                            }
                            else if(editText2.getSelectedItem()==null){
                                Message message=new Message();
                                message.what=2;
                                mhandler.sendMessage(message);
                            }
                            else if(editText3.getText().toString().equals("")){
                                Message message=new Message();
                                message.what=3;
                                mhandler.sendMessage(message);
                            }
                            else if(editText4.getText().toString().equals("")){
                                Message message=new Message();
                                message.what=4;
                                mhandler.sendMessage(message);
                            }
                            else if(date1.after(date2)){
                                Message message=new Message();
                                message.what=7;
                                mhandler.sendMessage(message);
                            }
                            else if(editText5.getText().toString().equals("")){
                                Message message=new Message();
                                message.what=5;
                                mhandler.sendMessage(message);
                            }
                            else if(datas==null){
                                Message message=new Message();
                                message.what=6;
                                mhandler.sendMessage(message);
                            }
                            else{
                            ActivityManager activity = new ActivityManager();
                            Beanactivity activity1 = new Beanactivity();
                            int place_id=0;
                            String pl=(String)editText2.getSelectedItem().toString();
                            for (int i=0;i<place1.size();i++){
                                if(pl.equals((place1.get(i).getPlace_name()))){
                                    place_id=place1.get(i).getPlace_ID();
                                    break;
                                }
                            }
                            activity.addActivity(editText1.getText().toString(),ID,place_id,editText3.getText().toString(),editText4.getText().toString(),editText5.getText().toString(),datas);
                            finish();}
                            shutdown();
                        } catch (BaseException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            });


        btnbegin=(TextView) findViewById(R.id.TextView_apply_activity_starttime1);
        btnbegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        btnbegin.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(apply_activity.this, DatePickerDialog.THEME_HOLO_LIGHT,listener,year,month,day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
            }
        });
        getoverDate();
        btnover = (TextView) findViewById(R.id.TextView_apply_activity_finishtime1);
        btnover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        btnover.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(apply_activity.this, DatePickerDialog.THEME_HOLO_LIGHT,listener,year,month,day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
            }
        });
    }
    private void getbeginDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }
    //获取当前日期  结束
    private void getoverDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }
    private void vChangeFontStyle(){

        Spinner styleSpinner =(Spinner)findViewById(R.id.Button_apply_activity_palce);
        String[] mStringsItem =new String[place1.size()];
        for(int i=0;i<place1.size();i++)
        mStringsItem[i]=place1.get(i).getPlace_name();
        ArrayAdapter<String> adapter1 = new CustomSpinnerArrayAdapter(apply_activity.this, android.R.layout.simple_dropdown_item_1line , mStringsItem, 20f);
        styleSpinner.setAdapter(adapter1);
    }
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    openAlbum();}
                else{
                    Toast.makeText(this,"You denide the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                Log.e("jip",""+ Build.VERSION.SDK_INT );
                if (Build.VERSION.SDK_INT >= 19) {
                    //4.4及以上系统使用该方法处理图片
                    handleImageOnKitKat(data);
                } else {
                    //4.4以下系统使用该方法处理图片
                    handleImageBeforeKitKat(data);
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this, uri)){
            //如果是document类型的Uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection);
            }else if("com.android.providers.downloads.documents".equals
                    (uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse(
                        "content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的Uri，则使用普通方法处理
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection){
        String path = null;
        //通过uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.
                        Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if(imagePath !=null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            datas = baos.toByteArray();
            picture.setBackground(null);
            picture.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
}
