package com.example.dashika.fuelbuddy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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

import java.util.Collections;
import java.util.Comparator;

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
    @BindView(R.id.btnDistance)
    Button btnDistance;
    @BindView(R.id.btnCost)
    Button btnCost;
    @BindView(R.id.imgTriangleDistance)
    ImageView imgTriangleDistance;
    @BindView(R.id.imgTriangleCost)
    ImageView imgTriangleCost;

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
                        btnCost.setTextColor(getResources().getColor(R.color.grey));
                        btnDistance.setTextColor(getResources().getColor(R.color.white));
                        imgTriangleDistance.setVisibility(View.VISIBLE);
                        imgTriangleCost.setVisibility(View.GONE);
                        Collections.sort(AppFuelBuddy.getElements(), new Comparator<Element>() {
                            @Override
                            public int compare(Element lhs, Element rhs) {
                                return lhs.getDistance() < rhs.getDistance() ? -1 : (lhs.getDistance() > rhs.getDistance()) ? 1 : 0;
                            }
                        });
                        break;
                    }
                    case 1: {
                        btnCost.setTextColor(getResources().getColor(R.color.white));
                        btnDistance.setTextColor(getResources().getColor(R.color.grey));
                        imgTriangleDistance.setVisibility(View.GONE);
                        imgTriangleCost.setVisibility(View.VISIBLE);
                        Collections.sort(AppFuelBuddy.getElements(), new Comparator<Element>() {
                            @Override
                            public int compare(Element lhs, Element rhs) {
                                return lhs.getCoast() < rhs.getCoast() ? -1 : (lhs.getCoast() > rhs.getCoast()) ? 1 : 0;
                            }
                        });
                        break;
                    }
                }
                PageFragment.change();
                pagerAdapter.notifyDataSetChanged();
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

        final LatLng Moscow = Constant.latLngMoscowCenter;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Moscow, 12.0f));

        mMap.setInfoWindowAdapter(new InfoWindowAdapterMarker());
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (containMoscow(latLng)) {
                    Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(AppFuelBuddy.getElements().get(0).getAddress())
                            .snippet(AppFuelBuddy.getElements().get(0).getCoast() + " \u20BD")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.transparent)));

                    marker.showInfoWindow();
                }
            }
        });

    }

    private boolean containMoscow(LatLng latLng) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(Constant.latLngMoscowNortheast);
        builder.include(Constant.latLngMoscowSouthwest);
        LatLngBounds bound = builder.build();

        return bound.contains(latLng);
    }

    public class InfoWindowAdapterMarker implements GoogleMap.InfoWindowAdapter {

        private View popUp;

        @Override
        public View getInfoWindow(Marker marker) {
            popUp = getLayoutInflater().inflate(R.layout.layout_popup, null);
            popUp.setBackgroundColor(getResources().getColor(R.color.transparent));
            TextView twAddress = (TextView) popUp.findViewById(R.id.twAddress);
            TextView twCost = (TextView) popUp.findViewById(R.id.twCost);
            ImageView imgLogo = (ImageView) popUp.findViewById(R.id.imgLogo);

            twAddress.setText(marker.getTitle());
            twCost.setText(marker.getSnippet());

            return popUp;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return popUp;
        }


    }

}
