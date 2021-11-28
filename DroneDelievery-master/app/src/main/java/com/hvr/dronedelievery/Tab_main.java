package com.hvr.dronedelievery;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Tab_main extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Home home;
    private Liist list;
    private Drone drone;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        menu=bottomNavigationView.getMenu();


        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.action_home:
                        menuItem.setIcon(R.drawable.home_blue);    // 선택한 이미지 변경
                        menu.findItem(R.id.action_drone).setIcon(R.drawable.drone_black);
                        menu.findItem(R.id.action_list).setIcon(R.drawable.order_list);
                        setFrag(0);
                        break;
                    case R.id.action_list:
                        menuItem.setIcon(R.drawable.order_list_blue);    // 선택한 이미지 변경
                        menu.findItem(R.id.action_drone).setIcon(R.drawable.drone_black);
                        menu.findItem(R.id.action_home).setIcon(R.drawable.home_black);
                        setFrag(1);
                        break;
                    case R.id.action_drone:
                        menuItem.setIcon(R.drawable.drone_blue);    // 선택한 이미지 변경
                        menu.findItem(R.id.action_home).setIcon(R.drawable.home_black);
                        menu.findItem(R.id.action_list).setIcon(R.drawable.order_list);
                        setFrag(2);
                        break;
                }
                return true;
            }
        });

        home=new Home();
        list=new Liist();
        drone=new Drone();
        setFrag(0); // 첫 프래그먼트 화면 지정
    }

    // 프레그먼트 교체
    private void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.Main_Frame,home);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.Main_Frame,list);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.Main_Frame,drone);
                ft.commit();
                break;


        }
    }

}