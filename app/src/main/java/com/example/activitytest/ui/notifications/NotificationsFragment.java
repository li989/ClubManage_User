package com.example.activitytest.ui.notifications;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.activitytest.AboutSystem;
import com.example.activitytest.ChangePwd;
import com.example.activitytest.MyClub;
import com.example.activitytest.PersonalInform;
import com.example.activitytest.R;
import com.example.activitytest.add_club;
import com.example.activitytest.club_notice;
import com.example.club.model.Beanuser;

import any.BitmapToRound_Util;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);


        Button btn1=(Button)root.findViewById(R.id.person_bt1);
        Button btn2=(Button)root.findViewById(R.id.person_bt2);
        Button btn3=(Button)root.findViewById(R.id.person_bt3);
        Button btn4=(Button)root.findViewById(R.id.person_bt4);
        Button btn5=(Button)root.findViewById(R.id.person_bt5);
        Button btn6=(Button)root.findViewById(R.id.person_bt6);
        Button btn1r=(Button)root.findViewById(R.id.per_btnr1);
        Button btn2r=(Button)root.findViewById(R.id.per_btnr2);
        Button btn3r=(Button)root.findViewById(R.id.per_btnr3);
        Button btn4r=(Button)root.findViewById(R.id.per_btnr4);
        Button btn5r=(Button)root.findViewById(R.id.per_btnr5);
        Button btn6r=(Button)root.findViewById(R.id.per_btnr6);
        ImageView pic=(ImageView)root.findViewById(R.id.h_head);
        try{
            Bitmap bitmap = BitmapFactory.decodeByteArray(Beanuser.currentLoginUser.getC_picture(), 0, Beanuser.currentLoginUser.getC_picture().length);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(Beanuser.currentLoginUser.getC_picture(), 0, Beanuser.currentLoginUser.getC_picture().length);
            bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
            pic.setImageBitmap(bitmap1);
        }catch (Exception e){
            pic.setImageResource(R.drawable.club_pro);
        }
        TextView name=(TextView)root.findViewById(R.id.user_name);
        if (Beanuser.currentLoginUser.getC_name()!=null)
            name.setText(""+Beanuser.currentLoginUser.getC_name());
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), PersonalInform.class);
                startActivityForResult(intent,1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyClub.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AboutSystem.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), add_club.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ChangePwd.class);
                startActivity(intent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().finish();

            }
        });
        btn1r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), PersonalInform.class);
                startActivityForResult(intent,1);
            }
        });
        btn2r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyClub.class);
                startActivity(intent);
            }
        });
        btn3r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AboutSystem.class);
                startActivity(intent);
            }
        });
        btn4r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), add_club.class);
                startActivity(intent);
            }
        });
        btn5r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ChangePwd.class);
                startActivity(intent);
            }
        });
        btn6r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return root;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        View root=getView();
        ImageView pic=(ImageView)root.findViewById(R.id.h_head);
        try{
            Bitmap bitmap = BitmapFactory.decodeByteArray(Beanuser.currentLoginUser.getC_picture(), 0, Beanuser.currentLoginUser.getC_picture().length);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(Beanuser.currentLoginUser.getC_picture(), 0, Beanuser.currentLoginUser.getC_picture().length);
            bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
            pic.setImageBitmap(bitmap1);
        }catch (Exception e){
            pic.setImageResource(R.drawable.club_pro);
        }
        TextView name=(TextView)root.findViewById(R.id.user_name);
        if (Beanuser.currentLoginUser.getC_name()!=null)
            name.setText(""+Beanuser.currentLoginUser.getC_name());
    }
}