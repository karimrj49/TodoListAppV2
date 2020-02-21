package com.example.todolist1.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist1.Entities.Action;
import com.example.todolist1.Entities.Status;
import com.example.todolist1.R;
import com.example.todolist1.RecyclerView.StatusRecyclerViewAdapter;
import com.example.todolist1.ViewModel.StatusViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddActionActivity extends AppCompatActivity implements StatusRecyclerViewAdapter.OnStatusListener {

    private EditText edititle , editdesc,idtag,idproject;
    private Button adds;
    private StatusViewModel statusViewModel;
    private RecyclerView recyclerView;
    private StatusRecyclerViewAdapter adapter;
    private RecyclerView.ViewHolder viewHolder;

    int rpos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_action);
        //AddActionActivity.this.setTitle(R.string.add_task);
        getSupportActionBar().setTitle(R.string.add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adds = findViewById(R.id.addS);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        adapter = new StatusRecyclerViewAdapter(this,this);
        recyclerView.setAdapter(adapter);
        statusViewModel = ViewModelProviders.of(this).get(StatusViewModel.class);

        statusViewModel.getAllStatus().observe(this, new Observer<List<Status>>() {
            @Override
            public void onChanged(List<Status> status) {
                // Update RecyclerView
                adapter.setList(status);
            }
        });
        edititle = (EditText) findViewById(R.id.action_title);
        editdesc = (EditText) findViewById(R.id.action_description);
        Intent intent = getIntent();

        if (intent.hasExtra("ID")) {
            getSupportActionBar().setTitle("update task");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            edititle.setText(intent.getStringExtra("title"));
            editdesc.setText(intent.getStringExtra("description"));
            int sn = intent.getIntExtra("IDID",-1);
            String s = null;
            try {
                 s = statusViewModel.getStatusById(sn);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             Toast.makeText(getApplicationContext(),"The selected state previously is "+s,Toast.LENGTH_SHORT).show();
        } else {
            getSupportActionBar().setTitle(R.string.add_task);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddActionActivity.this);
                View mview = getLayoutInflater().inflate(R.layout.addstatusdialog,null);
                EditText sedit = mview.findViewById(R.id.edit_status);
                Button Ssave = mview.findViewById(R.id.savestatus);
                builder.setView(mview);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Ssave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = sedit.getText().toString();
                        if(s.trim().isEmpty()){
                            Toast.makeText(getApplicationContext(),"You have to write the title of the new status",Toast.LENGTH_SHORT).show();
                        }else {
                            Status S = new Status(s);
                            statusViewModel.inserts(S);
                            alertDialog.dismiss();
                        }
                    }
                });


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addaction,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add :
                add();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void add (){
        edititle = (EditText) findViewById(R.id.action_title);
        editdesc = (EditText) findViewById(R.id.action_description);
        idtag =(EditText) findViewById(R.id.idtag);
        idproject = (EditText) findViewById(R.id.idproject);

        String title = edititle.getText().toString();
        String desc = editdesc.getText().toString();
        String Tage = idtag.getText().toString();
        String project = idproject.getText().toString();
        Intent data = new Intent();
        if(title.trim().isEmpty() || desc.trim().isEmpty() || Tage.trim().isEmpty() || project.trim().isEmpty()){
            setResult(RESULT_CANCELED,data);
            Toast.makeText(getApplicationContext(),"Please insert a title and description",Toast.LENGTH_SHORT).show();
        }else if (rpos==-1){
            setResult(RESULT_CANCELED,data);
            Toast.makeText(getApplicationContext(),"Please choose a status",Toast.LENGTH_SHORT).show();
        }
        else {
            data.putExtra("Insert title", title);
            data.putExtra("Insert description", desc);
            data.putExtra("IDS",rpos);
            data.putExtra("insert tag",Tage);
            data.putExtra("insert project",project);
            setResult(RESULT_OK, data);
            finish();
        }
    }
    @Override
    public int onStatusClick(int position) {
        //viewHolder.itemView.setBackgroundColor(000);
        String title = adapter.getStatusTitle(position);
        rpos = position+1;
        Toast.makeText(this,"You have selected the status"+title+" ",Toast.LENGTH_SHORT).show();
        return rpos;

    }
}
