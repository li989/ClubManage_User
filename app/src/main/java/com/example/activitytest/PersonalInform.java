package com.example.activitytest;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.club.control.example.UserManager;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.io.ByteArrayOutputStream;

import any.BitmapToRound_Util;

public class PersonalInform extends AppCompatActivity {
    public static  final int CHOOSE_PHOTO=2;
    private ImageView picture;
    private ImageView picture1;
    byte[] img;
    private int requestCode;
    private int resultCode;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_inform);
        Button btnexit=(Button)findViewById(R.id.perinf_exit);
        final TextView t1=(TextView)findViewById(R.id.per_t1);
        final TextView t2=(TextView)findViewById(R.id.per_t2);
        final TextView t3=(TextView)findViewById(R.id.per_t3);
        final TextView t4=(TextView)findViewById(R.id.per_t4);
        final TextView t5=(TextView)findViewById(R.id.per_t5);
        final TextView t6=(TextView)findViewById(R.id.per_t6);
        Button btn1=(Button)findViewById(R.id.per_btn1);
        Button btn2=(Button)findViewById(R.id.per_btn2);
        Button btn3=(Button)findViewById(R.id.per_btn3);
        Button btn4=(Button)findViewById(R.id.per_btn4);
        Button btn5=(Button)findViewById(R.id.per_btn5);
        Button btn6=(Button)findViewById(R.id.per_btn6);
        Button btn7=(Button)findViewById(R.id.per_btn7);
        Button btn8=(Button)findViewById(R.id.per_btn8);
        Button btn9=(Button)findViewById(R.id.per_btn9);
        Button btn10=(Button)findViewById(R.id.per_btn10);
        Button btn11=(Button)findViewById(R.id.per_btn11);
        Button btn12=(Button)findViewById(R.id.per_btn12);
        picture=(ImageView)findViewById(R.id.info_h_head);
        picture1=(ImageView)findViewById(R.id.info_h_back);
        try{
            Bitmap bitmap = BitmapFactory.decodeByteArray(Beanuser.currentLoginUser.getC_picture(), 0, Beanuser.currentLoginUser.getC_picture().length);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(Beanuser.currentLoginUser.getC_picture(), 0, Beanuser.currentLoginUser.getC_picture().length);
            bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
            picture.setImageBitmap(bitmap1);
            picture1.setImageBitmap(bitmap);
        }catch (Exception e){
            picture.setImageResource(R.drawable.club_pro);
        }
        if (Beanuser.currentLoginUser.getC_name()!=null)
            t1.setText(Beanuser.currentLoginUser.getC_name());
        if (Beanuser.currentLoginUser.getC_sno()!=null)
            t2.setText(Beanuser.currentLoginUser.getC_sno());
        if (Beanuser.currentLoginUser.getC_sex()!=null)
            t3.setText(Beanuser.currentLoginUser.getC_sex());
        if (Beanuser.currentLoginUser.getC_mobile()!=null)
            t4.setText(Beanuser.currentLoginUser.getC_mobile());
        if (Beanuser.currentLoginUser.getC_brithday()!=null)
            t5.setText(Beanuser.currentLoginUser.getC_brithday().toString().substring(0,10));
        if (Beanuser.currentLoginUser.getC_major()!=null)
            t6.setText(Beanuser.currentLoginUser.getC_major());
        picture.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ContextCompat.checkSelfPermission(PersonalInform.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PersonalInform.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }

            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("default","exit");
                setResult(0,intent);
                finish();
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonalInform.this, ChangeName.class);
                intent.putExtra("default",t1.getText().toString());
                startActivityForResult(intent,0);

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonalInform.this, ChangeSno.class);
                intent.putExtra("default",t2.getText().toString());
                startActivityForResult(intent,0);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonalInform.this, ChangeSex.class);
                intent.putExtra("default",t3.getText().toString());
                startActivityForResult(intent,0);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonalInform.this, Changemobile.class);
                intent.putExtra("default",t4.getText().toString());
                startActivityForResult(intent,0);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonalInform.this, birthday.class);
                intent.putExtra("default",t5.getText().toString());
                startActivityForResult(intent,0);
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonalInform.this, ChangeMajor.class);
                intent.putExtra("default",t6.getText().toString());
                startActivityForResult(intent,0);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t1.callOnClick();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.callOnClick();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t3.callOnClick();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t4.callOnClick();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t5.callOnClick();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t6.callOnClick();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t1.callOnClick();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.callOnClick();
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t3.callOnClick();
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t4.callOnClick();
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t5.callOnClick();
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t6.callOnClick();
            }
        });
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
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
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
        String s=null;
        try {
            s=data.getStringExtra("default");
        }catch (Exception e){
            e.printStackTrace();
        }

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
        final TextView t1=(TextView)findViewById(R.id.per_t1);
        final TextView t2=(TextView)findViewById(R.id.per_t2);
        final TextView t3=(TextView)findViewById(R.id.per_t3);
        final TextView t4=(TextView)findViewById(R.id.per_t4);
        final TextView t5=(TextView)findViewById(R.id.per_t5);
        final TextView t6=(TextView)findViewById(R.id.per_t6);

        if(s!=null){
            if(s.equals("name")){
                if (Beanuser.currentLoginUser.getC_name()!=null)
                    t1.setText(Beanuser.currentLoginUser.getC_name());
                else
                    t1.setText("");
            }else if(s.equals("sno")){
                if (Beanuser.currentLoginUser.getC_sno()!=null)
                    t2.setText(Beanuser.currentLoginUser.getC_sno());
                else
                    t2.setText("");
            }else if(s.equals("sex")){
                if (Beanuser.currentLoginUser.getC_sex()!=null)
                    t3.setText(Beanuser.currentLoginUser.getC_sex());
                else
                    t3.setText("");
            }else if(s.equals("mobile")){
                if (Beanuser.currentLoginUser.getC_mobile()!=null)
                    t4.setText(Beanuser.currentLoginUser.getC_mobile());
                else
                    t4.setText("");
            }else if(s.equals("birthday")){
                if (Beanuser.currentLoginUser.getC_brithday()!=null)
                    t5.setText(Beanuser.currentLoginUser.getC_brithday().toString().substring(0,10));
                else
                    t5.setText("");
            }else if(s.equals("major")){
                if (Beanuser.currentLoginUser.getC_major()!=null)
                    t6.setText(Beanuser.currentLoginUser.getC_major());
                else
                    t6.setText("");
            }else if(data.getStringExtra("default").equals("error")){
                AlertDialog alertDialog2 = new AlertDialog.Builder(PersonalInform.this)
                        .setMessage("修改错误")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).create();
            }
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
            byte[] datas = baos.toByteArray();
            img=datas;
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(datas, 0, datas.length);
            picture.setBackground(null);
            picture.setImageBitmap(bitmap1);
            picture1.setImageBitmap(bitmap1);
            new Thread(){
                @Override
                public void run() {
                    try {
                        UserManager a=new UserManager();
                        System.out.println(1);
                        Beanuser.currentLoginUser.setC_picture(img);
                        Beanuser.currentLoginUser=a.modifyuser(Beanuser.currentLoginUser);
                        if (Beanuser.currentLoginUser==null){
                            Intent intent=new Intent();
                            intent.putExtra("default","error");
                            setResult(0,intent);
                            finish();
                        }
                    } catch (BaseException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }else{
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
}
