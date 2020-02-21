package com.example.todolist1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist1.Entities.Action;
import com.example.todolist1.Entities.Projects;
import com.example.todolist1.Entities.Status;
import com.example.todolist1.Entities.Tags;
import com.example.todolist1.Fragments.Inbox;
import com.example.todolist1.Fragments.State;
import com.example.todolist1.R;
import com.example.todolist1.ViewModel.ActionViewModel;
import com.example.todolist1.ViewModel.ProjectViewModel;
import com.example.todolist1.ViewModel.StatusViewModel;
import com.example.todolist1.ViewModel.TagsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int REQUEST = 1;
    public static final int NO_REQUEST = 2;
    private ActionViewModel actionViewModel;
    private StatusViewModel statusViewModel;
    private ProjectViewModel projectViewModel ;
    private TagsViewModel tagsViewModel;
    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton floatbutton = (FloatingActionButton) findViewById(R.id.fab);

        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, AddActionActivity.class);
                startActivityForResult(intent,REQUEST);
            }
        });
        drawerLayout = findViewById(R.id.drawerlayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState==null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Inbox()).commit();
            getSupportActionBar().setTitle("Inbox");
                navigationView.setCheckedItem(R.id.inbox);
        }

        actionViewModel = ViewModelProviders.of(this).get(ActionViewModel.class);
        statusViewModel = ViewModelProviders.of(this).get(StatusViewModel.class);
        tagsViewModel = ViewModelProviders.of(this).get(TagsViewModel.class);
        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);

        navigationView.getMenu().getItem(3).setActionView(R.layout.add_status_nav);
        View view = navigationView.getMenu().getItem(3).getActionView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST && resultCode == RESULT_OK){

            String title = data.getStringExtra("Insert title");
            String desc = data.getStringExtra("Insert description");
            int idstatus = data.getIntExtra("IDS",-1);
            String Tag = data.getStringExtra("insert tag");
            String Project = data.getStringExtra("insert project");


            Action action = new Action(title,desc,idstatus);
            actionViewModel.insert(action);
            Tags tag = new Tags(Tag);
            tagsViewModel.insert(tag);
            Projects projects = new Projects(Project);
           projectViewModel.insert(projects);
            // Toast.makeText(this,"this message is "+Project+"",Toast.LENGTH_SHORT).show();

            Toast.makeText(this,"A task has been added to your agenda",Toast.LENGTH_SHORT).show();

        }
//        else if (requestCode == NO_REQUEST && resultCode == RESULT_OK) {
//            int id = data.getIntExtra("ID", -1);
//
//            if (id == -1) {
//                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            String title = data.getStringExtra("Insert title");
//            String desc = data.getStringExtra("Insert description");
//            int idstatus = data.getIntExtra("Choose the status",-1);
//
//            Action action = new Action(title,desc,idstatus);
//
//            action.setId(id);
//            actionViewModel.update(action);
//
//            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();  } else{
//            Toast.makeText(this,"The creation of the task is canceled",Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.inbox :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Inbox()).commit();
                getSupportActionBar().setTitle("Inbox");
                break;
            case R.id.state :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new State()).commit();
                getSupportActionBar().setTitle("State");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
