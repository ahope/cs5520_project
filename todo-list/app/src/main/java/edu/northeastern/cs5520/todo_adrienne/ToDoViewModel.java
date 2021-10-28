package edu.northeastern.cs5520.todo_adrienne;

import android.app.Application;
import android.mtp.MtpConstants;
import android.os.Build;

import androidx.annotation.RequiresApi;
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
        if (id < 0){
            MutableLiveData<ToDo> result = new MutableLiveData<>();
            result.setValue(new ToDo());
            return result;
        }
        return repository.getToDoById(id);
    });

    private MutableLiveData<String> _todoTitle = new MutableLiveData<>();

    private LiveData<String> todoTitle = Transformations.map(todo, (todo) -> {
        _todoTitle.setValue(todo.getTitle());
                return todo.getTitle();
            });


    private MutableLiveData<String> _todoDescription = new MutableLiveData<>();
    public LiveData<String> todoDescription = Transformations.map(todo, (todo) -> {
        return todo.getDescription();
    });


    private MutableLiveData<LocalDateTime> _date = new MutableLiveData<>();
    public LiveData<String> todoDateAsString = Transformations.map(todo, (todo) -> {
        _date.setValue(todo.getDeadline());
        return todo.getDeadlineDayAsString();
    });

    private MutableLiveData<LocalDateTime> _time = new MutableLiveData<>();
    public LiveData<String> todoTimeAsString = Transformations.map(todo, (todo) -> {
        _time.setValue(todo.getDeadline());
        return todo.getDeadlineTimeAsString();
    });

    private MutableLiveData<Boolean> todoCreated = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(Boolean.FALSE);
    private MutableLiveData<Boolean> isDataLoaded = new MutableLiveData<>(Boolean.FALSE);


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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void persistCurrentToDo() {
        ToDo updatedToDo = todo.getValue();
        updatedToDo.setTitle(_todoTitle.getValue());
        updatedToDo.setDescription(_todoDescription.getValue());
        updatedToDo.setDeadline(_date.getValue());

        updatedToDo.setReminderDateTime(_date.getValue().minusHours(1l));

        if (todoId.getValue() < 0) {
            repository.addToDo(updatedToDo);
        } else {
            int result = repository.update(updatedToDo);
        }
        todoCreated.setValue(Boolean.TRUE);
    }

    public void createTodo() {
        repository.addToDo(ToDo.createTodo(todoTitle.getValue(), todoDescription.getValue()));
        todoCreated.setValue(Boolean.TRUE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateTime(int newHour, int newMin) {
        _time.setValue(_date.getValue().withHour(newHour).withMinute(newMin));
        _date.setValue(_time.getValue());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateDate(long epochTime) {
        LocalDateTime ldt = LocalDateTime.ofEpochSecond(epochTime/1000, 0, ZoneOffset.UTC);
        LocalDateTime ldt2 = ldt.withHour(_date.getValue().getHour());
        LocalDateTime ldt3 = ldt2.withMinute(ldt2.getMinute());
        _date.setValue(ldt3);
        _time.setValue(_date.getValue());
    }

    public LiveData<String> getAdrienneTodoTitle() {
        return todoTitle;
    }

    public void setAdrienneTodoTitle(String newTitle) {
        _todoTitle.setValue(newTitle);
    }

    public void updateDescription(String description) {
        _todoDescription.setValue(description);
    }

}