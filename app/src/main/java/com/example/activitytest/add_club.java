package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adapter.CustomSpinnerArrayAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.control.example.UserManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class add_club extends AppCompatActivity{
    public static  final int CHOOSE_PHOTO=2;
    private Bitmap bitmap;
    private ArrayAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_club);
        vChangeFontStyle();
        Button btnexit=(Button)findViewById(R.id.add_club_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void vChangeFontStyle(){

        final Spinner styleSpinner =(Spinner)findViewById(R.id.sp_club_type);

        String[] mStringsItem = getResources().getStringArray(R.array.professionals);
        ArrayAdapter<String> adapter1 = new CustomSpinnerArrayAdapter(add_club.this, android.R.layout.simple_dropdown_item_1line , mStringsItem, 20f);
        styleSpinner.setAdapter(adapter1);

        final EditText edtname=(EditText)findViewById(R.id.edt_club_name);
        final EditText edtremark=(EditText)findViewById(R.id.add_club_page);
        ImageView btnpic=(ImageView)findViewById(R.id.add_club_picture1);
        btnpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(add_club.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(add_club.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }
            }
        });
        final Beanclub club=new Beanclub();

        Button btnsave=(Button)findViewById(R.id.add_club_release2) ;
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            ClubManager a=new ClubManager();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] datas = baos.toByteArray();
                            a.addClub(edtname.getText().toString(),edtremark.getText().toString(),Beanuser.currentLoginUser.getC_userID(),styleSpinner.getSelectedItem().toString(),datas);
                        } catch (BaseException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                finish();
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
            byte[] datas = baos.toByteArray();
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(datas, 0, datas.length);
            ImageView btnpic=(ImageView)findViewById(R.id.add_club_picture1);
            this.bitmap=bitmap1;
            btnpic.setBackground(null);
            btnpic.setImageBitmap(bitmap1);

        }else{
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
}