package com.example.chong.cs408bikesafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.chong.cs408bikesafe.Adapter.MyAdapter;
import com.example.chong.cs408bikesafe.Models.TitleChild;
import com.example.chong.cs408bikesafe.Models.TitleCreator;
import com.example.chong.cs408bikesafe.Models.TitleParent;

import java.util.ArrayList;
import java.util.List;

public class BikeSessions extends AppCompatActivity{

    RecyclerView recyclerView;

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        ((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bike_session);

        RecyclerView recyclerView;

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(this, initData());
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(adapter);

    }

    private List<ParentObject> initData() {
        TitleCreator titleCreator = TitleCreator.get(this);
        List<TitleParent> titles = titleCreator.getAll();
        List<ParentObject> parentObject = new ArrayList<>();
        for(TitleParent title: titles){
            List<Object> childList = new ArrayList<>();
            childList.add(new TitleChild("Yolo", "Yolo2"));
            title.setChildObjectList(childList);
            parentObject.add(title);
        }
        return parentObject;
    }

}
