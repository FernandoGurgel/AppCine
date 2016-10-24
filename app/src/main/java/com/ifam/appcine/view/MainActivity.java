package com.ifam.appcine.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ifam.appcine.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int posicao = 2;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String[] semana = {"Dom","Seg","Ter","Qua","Qui","Sex","Sab"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        onCreateTabNavegacao(viewPager);
        viewPager.setCurrentItem(diaSemana());

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private int diaSemana(){
        try {
            Date dia = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(dia);
            return calendar.get(Calendar.DAY_OF_WEEK)-1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void onCreateTabNavegacao(ViewPager viewPager) {
        /*
        * Inializando Abas de navegação
        */
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        /*
        *  Iniciando semana
        */
        for(int z=0; z < semana.length; z++){
            if( z==0 ){
                adapter.addFrag(new FimSemana(), semana[z]);
            }else if(z == semana.length-1){
                adapter.addFrag(new FimSemana(), semana[z]);
            }else{
                adapter.addFrag(new Semana(), semana[z]);
            }
            viewPager.setAdapter(adapter);
        }


    }
    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitulo = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFrag(Fragment fragment,String titulo){
            fragmentList.add(fragment);
            fragmentTitulo.add(titulo);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitulo.get(position);
        }
    }
}
