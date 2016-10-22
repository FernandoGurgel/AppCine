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
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String[] semana = {"Seg","Ter","Qua","Qui","Sex"};
    private String[] fimSemana = {"Dom","Sab"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        onCreateTabNavegacao(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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
                adapter.addFrag(new FimSemana(), fimSemana[z]);
                adapter.addFrag(new Semana(), semana[z]);
            }else if(z != semana.length-1){
                adapter.addFrag(new Semana(), semana[z]);
            }else{
                adapter.addFrag(new Semana(), semana[z]);
                adapter.addFrag(new FimSemana(), fimSemana[1]);
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
