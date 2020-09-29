package com.nerdlabs.yogaarchive;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YogaListFragment extends Fragment implements CategoryListAdapter.ListItemClickListener {
    RecyclerView.Adapter listAdapter;
    RecyclerView catposelist;
    RecyclerView.LayoutManager layoutManager;
    String[] beginPoseSubnames, beginPoseNames, intPoseNames, intPoseSubnames, expPoseNames, expPoseSubnames;
    int[] poseimg_res = {R.drawable.circ1, R.drawable.circ2, R.drawable.circ3, R.drawable.circ1, R.drawable.circ2, R.drawable.circ2};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String strtext = getArguments().getString("category");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(strtext);

        View view = inflater.inflate(R.layout.fragment_yoga_list, container, false);

        ArrayList<PosesModel> myArrayList = new ArrayList<PosesModel>();
        catposelist = (RecyclerView) view.findViewById(R.id.rv_gen_list);
        beginPoseNames = getResources().getStringArray(R.array.beginner_poses_names);
        beginPoseSubnames = getResources().getStringArray(R.array.beginner_poses_subnames);

        if (strtext.equals("Beginner Poses")){
            int i = 0;
            for (String name : beginPoseNames) {
                PosesModel myModel = new PosesModel(name, beginPoseSubnames[i], poseimg_res[i]);
                myArrayList.add(myModel);
                i++;
            }
            listAdapter = new CategoryListAdapter(myArrayList, this.getActivity(), this);
        }
        else if(strtext.equals("Intermediate Essentials")){
            int i = 0;
            for (String name : beginPoseNames) {
                PosesModel myModel = new PosesModel(name, beginPoseSubnames[i], poseimg_res[i]);
                myArrayList.add(myModel);
                i++;
            }
            listAdapter = new CategoryListAdapter(myArrayList, this.getActivity(), this);
        }
        else if(strtext.equals("Expert Yogas")){
            int i = 0;
            for (String name : beginPoseNames) {
                PosesModel myModel = new PosesModel(name, beginPoseSubnames[i], poseimg_res[i]);
                myArrayList.add(myModel);
                i++;
            }
            listAdapter = new CategoryListAdapter(myArrayList, this.getActivity(), this);
        }
        catposelist.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        catposelist.setLayoutManager(layoutManager);
        catposelist.setAdapter(listAdapter);


        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        String toolbartext;

        toolbartext = beginPoseNames[clickedItemIndex];

        Fragment fragmentdet = new YogaDetailsFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.exit_to_left, R.anim.slide_in_left, R.anim.exit_to_right);
        ft.replace(R.id.frag_cn, fragmentdet)
                .addToBackStack(null)
                .commit();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(toolbartext);


    }
}
