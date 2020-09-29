package com.nerdlabs.yogaarchive;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;


public class YogaFragment extends Fragment implements CategoriesItemAdapter.ListItemClickListener, FeaturedYogaAdapter.ListItemClickListener {

    CarouselView carouselView;
    int[] yogaImages = {R.drawable.firefly, R.drawable.lotus, R.drawable.dancer, R.drawable.bow,
            R.drawable.cobra, R.drawable.plank, R.drawable.seatedtwist};
    RecyclerView.Adapter mAdapter, mAdapter2;
    RecyclerView mPoseList, mPoseList2;
    RecyclerView.LayoutManager layoutManager, layoutManager2;

    String[] poseCategorySubName, poseCategory, poseNames, poseSubNames;
    int[] poseimg_res = {R.drawable.circ1, R.drawable.circ2, R.drawable.circ3, R.drawable.circ1, R.drawable.circ2};
    boolean mAlreadyLoaded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Yoga Hacks");
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View view = inflater.inflate(R.layout.fragment_yoga, container, false);
        ArrayList<PosesModel> myArrayList = new ArrayList<PosesModel>();
        ArrayList<PosesModel> categorylist = new ArrayList<PosesModel>();


        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(yogaImages.length);
        carouselView.setImageListener(imageListener);


        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));

        mPoseList = (RecyclerView) view.findViewById(R.id.rv_categories);
        mPoseList2 = (RecyclerView) view.findViewById(R.id.rv_fyogas);
        ViewCompat.setNestedScrollingEnabled(mPoseList, false);
        ViewCompat.setNestedScrollingEnabled(mPoseList2, false);
        poseCategory = getResources().getStringArray(R.array.pose_category);
        poseCategorySubName = getResources().getStringArray(R.array.pose_category_subname);
        poseNames = getResources().getStringArray(R.array.pose_name);
        poseSubNames = getResources().getStringArray(R.array.pose_subname);
        int i = 0;
        for (String name : poseNames) {
            PosesModel myModel = new PosesModel(name, poseSubNames[i], poseimg_res[i]);
            myArrayList.add(myModel);
            i++;
        }
        i = 0;
        for (String name : poseCategory) {
            PosesModel myModel = new PosesModel(name, poseCategorySubName[i], poseimg_res[i]);
            categorylist.add(myModel);
            i++;
        }

        mAdapter = new CategoriesItemAdapter(categorylist, this.getActivity(), this);
        mAdapter2 = new FeaturedYogaAdapter(myArrayList, this.getActivity(), this);
        mPoseList.setHasFixedSize(true);
        mPoseList2.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        mPoseList.setLayoutManager(layoutManager);
        layoutManager2 = new LinearLayoutManager(this.getActivity());
        mPoseList2.setLayoutManager(layoutManager2);
        mPoseList.setAdapter(mAdapter);
        mPoseList2.setAdapter(mAdapter2);

        return view;

    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(yogaImages[position]);
        }
    };

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Bundle bundle = new Bundle();
        bundle.putString("category",poseCategory[clickedItemIndex]);

        YogaListFragment fraglist = new YogaListFragment();
        fraglist.setArguments(bundle);
        String toolbartext;

        //Setting Toolbar text
        toolbartext = poseCategory[clickedItemIndex];


        //Loading fragment
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_up, R.anim.fade_out, R.anim.fade_in, R.anim.slide_down);
        ft.replace(R.id.frag_cn, fraglist)
                .addToBackStack(null)
                .commit();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(toolbartext);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null && !mAlreadyLoaded) {
            mAlreadyLoaded = true;

        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onfyListItemClick(int clickedItemIndex) {

        String toolbartext;

        toolbartext = poseNames[clickedItemIndex];

        //Loading new Fragment
        Fragment fragmentdet = new YogaDetailsFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.exit_to_left, R.anim.slide_in_left, R.anim.exit_to_right);
        ft.replace(R.id.frag_cn, fragmentdet)
                .addToBackStack(null)
                .commit();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(toolbartext);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}