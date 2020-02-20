package com.example.todolist1.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist1.Activities.MainActivity;
import com.example.todolist1.Entities.Action;
import com.example.todolist1.R;
import com.example.todolist1.RecyclerView.RecyclerViewAdapter;
import com.example.todolist1.ViewModel.ActionViewModel;

import java.util.List;

public class ActionWithState extends Fragment {

    ActionViewModel actionViewModel ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inbox_fragment,container,false);
        Bundle bundle = getArguments();
        int p = bundle.getInt("Position");
        String title = bundle.getString("Title");
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.getSupportActionBar().setTitle(title);
        RecyclerView recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
        actionViewModel = ViewModelProviders.of(this).get(ActionViewModel.class);

        actionViewModel.getAllActionS(p).observe(this, new Observer<List<Action>>() {
            @Override
            public void onChanged(List<Action> actions) {
                adapter.setList(actions);
            }
        });
        return root ;
    }
}
