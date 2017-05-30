package com.example.dashika.fuelbuddy;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int PAGE_COUNT = 2;
    private GoogleMap mMap;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.btnPlus)
    ImageButton btnPlus;
    @BindView(R.id.tabPage)
    ViewPager tabPage;

    MyFragmentPagerAdapter pagerAdapter;
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Title " + position;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        tabPage.setAdapter(pagerAdapter);
        tabPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        break;
                    }
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        tabPage.setCurrentItem(0);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Moscow = Constant.latLngMoscowCenter;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Moscow));
        mMap.setInfoWindowAdapter(new InfoWindowAdapterMarker(this));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(containMoscow(latLng))
                {
                    Toast.makeText(getBaseContext(),"Contain",Toast.LENGTH_LONG).show();
                }
            }
        });

      Marker marker =  mMap.addMarker(new MarkerOptions()
                .position(Moscow)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.transparent)));

                marker.showInfoWindow();
    }

    private boolean containMoscow(LatLng latLng)
    {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(Constant.latLngMoscowNortheast);
        builder.include(Constant.latLngMoscowSouthwest);
        LatLngBounds bound = builder.build();

        return bound.contains(latLng);
    }

    public class InfoWindowAdapterMarker implements GoogleMap.InfoWindowAdapter {

        private Marker markerShowingInfoWindow;
        private Context mContext;
        public InfoWindowAdapterMarker(Context context) {
            mContext = context;
        }

        @Override
        public View getInfoContents(Marker marker) {

            markerShowingInfoWindow = marker;

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            View popUp = inflater.inflate(R.layout.layout_popup, null);

            TextView twAddress = (TextView) popUp.findViewById(R.id.twAddress);
            TextView twCost = (TextView) popUp.findViewById(R.id.twCost);
            ImageView imgLogo = (ImageView) popUp.findViewById(R.id.imgLogo);

            twAddress.setText(marker.getTitle());
            twCost.setText(marker.getSnippet());

            return popUp;
        }

        @Override
        public View getInfoWindow(Marker marker) {

            return null;
        }

    }

}
