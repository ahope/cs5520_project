package edu.northeastern.cs5520.todo_adrienne;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import edu.northeastern.cs5520.todo_adrienne.databinding.FragmentToDoBinding;


public class ToDoFragment extends Fragment {

    private ToDoViewModel mViewModel;
    private FragmentToDoBinding binding;

    private MaterialDatePicker materialDatePicker;
    private MaterialTimePicker materialTimePicker;

    public static ToDoFragment newInstance() {
        return new ToDoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentToDoBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
        binding.setViewmodel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        buildDatePicker();
        buildTimePicker();

        // Wire up the button to ensure the task gets created.
        binding.buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mViewModel.createTodo();
                mViewModel.persistCurrentToDo();
            }
        });

        binding.buttonChooseDate.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View view) {
                // getSupportFragmentManager() to
                // interact with the fragments
                // associated with the material design
                // date picker tag is to get any error
                // in logcat
                materialDatePicker.show(ToDoFragment.this.getParentFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        binding.buttonChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialTimePicker.show(ToDoFragment.this.getParentFragmentManager(), "MATERIAL_TIME_PICKER");
            }
        });

    }

    private void buildTimePicker() {
        MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder();
        builder.setTitleText("Select a time");
        this.materialTimePicker = builder.build();

        materialTimePicker.addOnPositiveButtonClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {
                        LocalDateTime curDate = mViewModel.currentToDo.getValue().getDeadline();
                        mViewModel.currentToDo.getValue().setDeadline(curDate.withHour(materialTimePicker.getHour()).withMinute(materialTimePicker.getMinute()));
//                        mViewModel.setTime(materialTimePicker.getHour() + ":" + materialTimePicker.getMinute());
                    }
                }
        );
    }

    private void buildDatePicker() {
        // now create instance of the material date picker
        // builder make sure to add the "datePicker" which
        // is normal material date picker which is the first
        // type of the date picker in material design date
        // picker
        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();

        // now define the properties of the
        // materialDateBuilder that is title text as SELECT A DATE
        materialDateBuilder.setTitleText("SELECT A DATE");

        // now create the instance of the material date
        // picker
        this.materialDatePicker = materialDateBuilder.build();

        // handle select date button which opens the
        // material design date picker
//        mPickDateButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // getSupportFragmentManager() to
//                        // interact with the fragments
//                        // associated with the material design
//                        // date picker tag is to get any error
//                        // in logcat
//                        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
//                    }
//                });

        // now handle the positive button click from the
        // material design date picker
        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {

                        // if the user clicks on the positive
                        // button that is ok button update the
                        // selected date
                        LocalDateTime curDate = mViewModel.currentToDo.getValue().getDeadline();
                        Long newDate = ((Long)materialDatePicker.getSelection()).longValue();
                        // TODO (AHS): Bah-- timezones!!

                        LocalDateTime ldt = LocalDateTime.ofEpochSecond(newDate.longValue()/1000, 0, ZoneOffset.UTC);
                        LocalDateTime ldt2 = ldt.withHour(curDate.getHour());
                        LocalDateTime ldt3 = ldt2.withMinute(curDate.getMinute());
                        mViewModel.currentToDo.getValue().setDeadline(ldt3);


//                        mViewModel.setDate(((Long)materialDatePicker.getSelection()).longValue());//.setText("Selected Date is : " + materialDatePicker.getHeaderText());
                        // in the above statement, getHeaderText
                        // is the selected date preview from the
                        // dialog
                    }
                });
    }


}