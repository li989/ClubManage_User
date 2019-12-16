package com.example.activitytest;

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
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.club.control.example.ClubNoticeManager;
import com.example.club.model.Beanclubnotice;
import com.example.club.util.BaseException;

import java.io.ByteArrayOutputStream;

import any.CustomDialog;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class add_clubnotice extends AppCompatActivity {
    public static  final int CHOOSE_PHOTO=2;
    private ImageView picture;
    private byte[] datas=null;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            CustomDialog customDialog = new CustomDialog(add_clubnotice.this);
            customDialog.setTitle("提醒");
            customDialog.setMessage("社内通知标题未填写");
            customDialog.setConfirm("confirm", new CustomDialog.IOnConfirmListener(){
                @Override
                public void onConfirm(CustomDialog dialog) {

                }
            });
            customDialog.show();
        }
    };
    private Handler mhandler=new Handler(){
        public void handleMessage(Message msg){
            CustomDialog customDialog = new CustomDialog(add_clubnotice.this);
            customDialog.setTitle("提醒");
            customDialog.setMessage("社内通知内容未填写");
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
        setContentView(R.layout.add_club_notice);
        Intent it = getIntent();
        final int ID = it.getIntExtra("club_ID",0);
        Button btnexit=(Button)findViewById(R.id.button_club_notice_exit);
        Button btnrel2=(Button)findViewById(R.id.TextView_club_notice_release2);
        picture=(ImageView)findViewById(R.id.TextView_club_notice_release3);
        final EditText editText1 = (EditText) findViewById(R.id.EditText_club_notice_theme);
        final EditText editText2 = (EditText) findViewById(R.id.EditText_club_notice_page);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        picture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(add_clubnotice.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(add_clubnotice.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }
            }
        });
        btnrel2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            if (editText1.getText().toString().equals("")){
                                Message message=new Message();
                                handler.sendMessage(message);
                            }
                            else if (editText2.getText().toString().equals("")){
                                Message message=new Message();
                                mhandler.sendMessage(message);
                            }
                            else{
                            ClubNoticeManager notice=new ClubNoticeManager();
                            Beanclubnotice clubnotice=new Beanclubnotice();
                            notice.addClubnotice(ID,editText1.getText().toString(),editText2.getText().toString(),datas);
                            finish();}
                            shutdown();
                        } catch (BaseException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
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
