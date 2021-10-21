package edu.northeastern.cs5520.todo_adrienne;

import android.app.Application;

import androidx.databinding.Bindable;
import androidx.databinding.InverseMethod;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;

public class ToDoViewModel extends AndroidViewModel {
    private ToDoItemRepository repository;

    private final MutableLiveData<Integer> todoId = new MutableLiveData<>();

    private final LiveData<ToDo> todo = Transformations.switchMap(todoId, (id) -> {
//        MutableLiveData<ToDo> result = new MutableLiveData<>();
//        result.setValue(repository.asList().get(id));
        return repository.getToDoById(id);
    });

    private MutableLiveData<String> _todoTitle = new MutableLiveData<>();

    private LiveData<String> todoTitle = Transformations.map(todo, (todo) -> {
        _todoTitle.setValue(todo.getTitle());
                return todo.getTitle();
            });


    public LiveData<String> todoDescription = Transformations.map(todo, (todo) -> {
        return todo.getDescription();
    });

    public LiveData<String> todoDateAsString = Transformations.map(todo, (todo) -> {
        return todo.getDeadlineDayAsString();
    });

    public LiveData<String> todoTimeAsString = Transformations.map(todo, (todo) -> {
        return todo.getDeadlineTimeAsString();
    });

    private MutableLiveData<Boolean> todoCreated = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(Boolean.FALSE);
    private MutableLiveData<Boolean> isDataLoaded = new MutableLiveData<>(Boolean.FALSE);
//    private MutableLiveData<Integer> toDoId = new MutableLiveData<>();



    // TODO(AHS): get rid of this
//    public MutableLiveData<ToDo> currentToDo = new MutableLiveData<>();

    // TODO(ahs): Review/include the SavedStateHandle stuff
    public ToDoViewModel(Application application) {
        super(application);
        repository = ToDoItemRepository.getSingleton(application);

    }

    public void loadToDo(int todoId) {
        this.todoId.setValue(Integer.valueOf(todoId));
    }

    public LiveData<Boolean> getTodoCreated() {
        return todoCreated;
    }

    public void persistCurrentToDo() {
//        repository.addToDo(currentToDo.getValue());
        ToDo updatedToDo = todo.getValue();
        updatedToDo.setTitle(_todoTitle.getValue());
        int result = repository.update(updatedToDo);

        todoCreated.setValue(Boolean.TRUE);
    }

//    public void loadToDo(int id) {
//        // TODO(ahs): Make sure that this mutable/not mutable is what we really want.
//        LiveData<ToDo> todo = repository.getToDoById(id);
//        TODO(ahs): this is where
//        currentToDo.setValue(todo.getValue());
//        currentToDo.postValue(todo.getValue());
//    }


    public void createTodo() {
        repository.addToDo(ToDo.createTodo(todoTitle.getValue(), todoDescription.getValue()));
        todoCreated.setValue(Boolean.TRUE);
    }

    public void updateTime(int newHour, int newMin) {

    }

    public void updateDate(long epochTime) {
//        LocalDateTime curDate = mViewModel.currentToDo.getValue().getDeadline();
//        Long newDate = ((Long)materialDatePicker.getSelection()).longValue();
//        // TODO (AHS): Bah-- timezones!!
//
//        LocalDateTime ldt = LocalDateTime.ofEpochSecond(newDate.longValue()/1000, 0, ZoneOffset.UTC);
//        LocalDateTime ldt2 = ldt.withHour(curDate.getHour());
//        LocalDateTime ldt3 = ldt2.withMinute(curDate.getMinute());
//        mViewModel.currentToDo.getValue().setDeadline(ldt3);
    }

    public LiveData<String> getAdrienneTodoTitle() {
        return todoTitle;
    }


    public void setAdrienneTodoTitle(String newTitle) {
        _todoTitle.setValue(newTitle);
    }

}