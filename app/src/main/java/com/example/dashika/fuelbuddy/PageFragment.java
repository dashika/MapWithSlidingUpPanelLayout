package com.example.dashika.fuelbuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by dashika on 30/05/17.
 */

public class PageFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;



    public static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.list, null);
        switch (pageNumber) {
            case 0: {
                Collections.sort(AppFuelBuddy.getElements(), new Comparator<Element>() {
                    @Override
                    public int compare(Element lhs, Element rhs) {
                        return lhs.getDistance() > rhs.getDistance() ? -1 : (lhs.getDistance() < rhs.getDistance()) ? 1 : 0;
                    }
                });
                break;
            }
            case 1: {
                Collections.sort(AppFuelBuddy.getElements(), new Comparator<Element>() {
                    @Override
                    public int compare(Element lhs, Element rhs) {
                        return lhs.getCoast() > rhs.getCoast() ? -1 : (lhs.getCoast() < rhs.getCoast()) ? 1 : 0;
                    }
                });
                break;
            }
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemAdapter itemAdapter = new ItemAdapter(AppFuelBuddy.getElements(), getActivity());
        recyclerView.setAdapter(itemAdapter);
        return view;
    }
}
