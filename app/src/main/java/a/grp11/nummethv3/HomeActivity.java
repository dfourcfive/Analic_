package a.grp11.nummethv3;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;



import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import a.grp11.nummethv3.DataStructure.Parts;

import a.grp11.nummethv3.PartData.PartsConfig;
import a.grp11.nummethv3.PartsMenu.PartsMenuPagerAdapter;
import a.grp11.nummethv3.Menus.Animation.ToggleLogo;
import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity {

    MaterialViewPager mMaterialViewPager;
    CircleImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        mMaterialViewPager = findViewById(R.id.materialViewPager);
        logo = findViewById(R.id.top_menu_logo);

        if(savedInstanceState==null){
            PartsConfig.init(this);
        }

        final Toolbar toolbar = mMaterialViewPager.getToolbar();

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
        mMaterialViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {

                Drawable drawableImg = getResources().getDrawable(PartsConfig.getParts().getPartData(page).getPartImageRes());
                Drawable drawableIcon = getResources().getDrawable(PartsConfig.getParts().getPartData(page).getPartIconRes());
                int pageColor =  getResources().getColor(PartsConfig.getParts().getPartData(page).getPartColorRes());

                mMaterialViewPager.getPagerTitleStrip().setIndicatorColor(pageColor);
                ToggleLogo.toggle(logo,drawableIcon,pageColor);
                return HeaderDesign.fromColorResAndDrawable(PartsConfig.getParts().getPartData(page).getPartColorRes(),drawableImg);
            }
        });

        mMaterialViewPager.getViewPager().setAdapter(new PartsMenuPagerAdapter(getSupportFragmentManager()));
        mMaterialViewPager.getViewPager().setOffscreenPageLimit(mMaterialViewPager.getViewPager().getAdapter().getCount());
        mMaterialViewPager.getPagerTitleStrip().setViewPager(mMaterialViewPager.getViewPager());



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
