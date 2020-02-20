package com.example.todolist1.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist1.Activities.AddActionActivity;
import com.example.todolist1.Activities.MainActivity;
import com.example.todolist1.Entities.Action;
import com.example.todolist1.R;
import com.example.todolist1.RecyclerView.RecyclerViewAdapter;
import com.example.todolist1.ViewModel.ActionViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Inbox extends Fragment {
    public static final int REQUEST = 1;
    public static final int NO_REQUEST = 2;
    int var=-1;
    ActionViewModel actionViewModel ;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST && resultCode == RESULT_OK){
//
//            String title = data.getStringExtra("Insert title");
//            String desc = data.getStringExtra("Insert description");
//            int idstatus = data.getIntExtra("Choose the status",-1);
//
//            Action action = new Action(title,desc,idstatus);
//            actionViewModel.insert(action);
//
//           Toast.makeText(getContext(),"A task has been added to your agenda",Toast.LENGTH_SHORT).show();
//
//        }
         if (requestCode == NO_REQUEST && resultCode == RESULT_OK) {
//            int id = data.getIntExtra("ID", -1);
//
//         Toast.makeText(getContext(),""+id,Toast.LENGTH_SHORT).show();
            if (var == -1) {
                Toast.makeText(getContext(), "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra("Insert title");
            String desc = data.getStringExtra("Insert description");
            int idstatus = data.getIntExtra("IDS",-1);

            Action action = new Action(title,desc,idstatus);

            action.setId(var);
            actionViewModel.update(action);

            Toast.makeText(getContext() , "Note updated", Toast.LENGTH_SHORT).show();  } else{
            Toast.makeText(getContext(),"The creation of the task is canceled",Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inbox_fragment,container,false);


        RecyclerView recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
        actionViewModel = ViewModelProviders.of(this).get(ActionViewModel.class);
        actionViewModel.getAllAction().observe(this, new Observer<List<Action>>() {
            @Override
            public void onChanged(List<Action> actions) {
                // Update RecyclerView
                adapter.setList(actions);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                String t = adapter.getAction(viewHolder.getAdapterPosition()).getDescription();
                actionViewModel.delete(adapter.getAction(viewHolder.getAdapterPosition()));
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(getContext(),"The task "+t+" is done",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public synchronized void onItemClick(Action action) {
                Intent intent = new Intent(getActivity(),AddActionActivity.class);
                intent.putExtra("ID",action.getId());
                var=action.getId();
                Toast.makeText(getContext(),""+action.getId(),Toast.LENGTH_SHORT).show();
                intent.putExtra("title",action.getTitle());
                intent.putExtra("description",action.getDescription());
                intent.putExtra("IDID",action.getIdstatus());
                startActivityForResult(intent,NO_REQUEST);
            }
        });


      return root;
    }


}
