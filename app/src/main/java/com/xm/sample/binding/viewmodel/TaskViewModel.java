package com.xm.sample.binding.viewmodel;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.xm.sample.binding.BR;
import com.xm.sample.binding.databinding.ActivityTaskListBinding;

public class TaskViewModel extends BaseObservable {

    private int taskCount = 0;

    private ActivityTaskListBinding binding;

    public TaskViewModel(ActivityTaskListBinding binding) {
        this.binding = binding;
    }

    @Bindable
    public boolean isEmptyCount() {
        return taskCount == 0;
    }

    @Bindable
    public String getTaskLabel() {
        return "空啊啊啊啊";
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
        binding.taskRefreshLayout.setRefreshing(false);

        notifyPropertyChanged(BR.taskLabel);
        notifyPropertyChanged(BR.emptyCount);

    }
}
