package com.digitcreativestudio.adakajian;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.digitcreativestudio.adakajian.entity.Kajian;

import java.util.ArrayList;
import java.util.List;

public class DaftarKajianActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ArrayList<Kajian> daftar_kajian = new ArrayList<Kajian>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar__kajian);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        for (int i=0;i<=10;i++){
//            daftar_kajian.add(new Kajian("Menumbuhkan ukhuwah Islam di lingkungan kampus "+i,"lokasi kajian"+i,"tanggal kajian "+i,"jam kajian "+i,"ustadz Fajar Ainul Bashri "+i,"pamflet "+i,"keterangan "+i));
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentDaftarKajian(), "Fiqih");
        adapter.addFragment(new FragmentDaftarKajian(), "Tauhid");
        adapter.addFragment(new FragmentDaftarKajian(), "Tahsin");
        adapter.addFragment(new FragmentDaftarKajian(), "Akhlaq");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            Bundle extras1 = new Bundle();
            if(title.equalsIgnoreCase("Fiqih")){
                extras1.putParcelableArrayList("arraylist", daftar_kajian);
            }else if(title.equalsIgnoreCase("Tauhid")){
                extras1.putParcelableArrayList("arraylist", daftar_kajian);
            }else if(title.equalsIgnoreCase("Tahsin")){
                extras1.putParcelableArrayList("arraylist", daftar_kajian);
            }else if(title.equalsIgnoreCase("Akhlaq")){
                extras1.putParcelableArrayList("arraylist", daftar_kajian);
            }
            fragment.setArguments(extras1);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.daftar_kajian, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }else if (id == R.id.action_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_kajian_terdekat) {
            Intent i = new Intent(DaftarKajianActivity.this,PetaKajianActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_tambah_kajian) {

        } else if (id == R.id.nav_akun) {
            Intent i = new Intent(DaftarKajianActivity.this,LoginActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_pengaturan) {
            Intent i = new Intent(DaftarKajianActivity.this,PengaturanActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_tentang) {
            Intent i = new Intent(DaftarKajianActivity.this,TentangActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_feedback) {
            Toast.makeText(DaftarKajianActivity.this, "Go to feedback page", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
