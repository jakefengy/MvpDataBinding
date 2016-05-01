package com.xm.sample.binding.presenter;

import com.xm.sample.binding.contract.TaskListContract;
import com.xm.sample.binding.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskListPresenter implements TaskListContract.Presenter {

    //
    private List<Task> taskList = new ArrayList<>();

    private TaskListContract.View view;

    public TaskListPresenter(TaskListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadTasks() {
        if (view == null) {
            return;
        }

        view.onLoadTasks(taskList);
    }

    @Override
    public void addTask() {
        Task task = new Task();
        task.setId(String.valueOf(System.currentTimeMillis()));
        task.setName("Task Name " + task.getId());
        task.setComplete(false);
        taskList.add(task);

        if (view != null) {
            view.onAddTask(task);
        }

    }

    @Override
    public void deleteTask(Task task) {
        taskList.remove(task);
        if (view != null) {
            view.onDeleteTask(task);
        }

    }

    @Override
    public void toDetail(Task task) {
        if (view != null) {
            view.toDetail(task);
        }
    }

    @Override
    public void destroy() {
        view = null;
    }
}
