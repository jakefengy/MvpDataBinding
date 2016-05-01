package com.xm.sample.binding.itemactionhandle;

import com.xm.sample.binding.contract.TaskListContract;
import com.xm.sample.binding.entity.Task;

/**
 * 任务列表Item事件处理
 */
public class TaskItemActionHandle {

    private TaskListContract.Presenter presenter;

    public TaskItemActionHandle(TaskListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void deleteTask(Task task) {
        presenter.deleteTask(task);
    }

    public void toDetail(Task task) {
        presenter.toDetail(task);
    }

}
