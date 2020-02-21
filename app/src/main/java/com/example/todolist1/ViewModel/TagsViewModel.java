package com.example.todolist1.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.todolist1.Entities.Tags;
import com.example.todolist1.Repository.TagsRepository;

import java.util.List;


public class TagsViewModel  extends ActionViewModel{
    private TagsRepository tagsRepository;
    private LiveData<List<Tags>> allTags;
    private LiveData<List<Tags>> allTagsS;

    public TagsViewModel(@NonNull Application application) {
     super(application);
        tagsRepository = new TagsRepository(application);
        allTags = tagsRepository.getalltags();
    }

    public void update(Tags tags) { tagsRepository.update(tags); }

    public void insert(Tags tags) { tagsRepository.insert(tags); }

    public void delete(Tags tags) { tagsRepository.delete(tags); }

    public LiveData<List<Tags>> getAllTags() { return allTags;  }

//    public LiveData<List<Tags>> getAllActionS(int p) {
//        return tagsRepository.getalltags(p);
//    }
}