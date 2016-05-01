package com.xm.sample.binding.contract;

import com.xm.sample.binding.entity.Task;

import java.util.List;

public interface TaskListContract {

    interface View extends BaseView<Presenter> {
        void onLoadTasks(List<Task> tasks);

        void onDeleteTask(Task task);

        void onAddTask(Task task);

        void toDetail(Task task);
    }

    interface Presenter extends BasePresenter {

        // xml control event

        void addTask();

        void loadTasks();

        void deleteTask(Task task);

        void toDetail(Task task);

    }

}
