package com.xm.sample.binding.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.xm.sample.binding.R;
import com.xm.sample.binding.adapter.TaskAdapter;
import com.xm.sample.binding.contract.TaskListContract;
import com.xm.sample.binding.databinding.ActivityTaskListBinding;
import com.xm.sample.binding.entity.Task;
import com.xm.sample.binding.presenter.TaskListPresenter;
import com.xm.sample.binding.viewmodel.TaskViewModel;

import java.util.List;

public class Activity_Task_List extends AppCompatActivity implements TaskListContract.View {

    private TaskListContract.Presenter presenter;
    private ActivityTaskListBinding dataBinding;
    private TaskViewModel viewModel;

    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        initData();

    }

    private void initViews() {

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_task_list);
        dataBinding.taskList.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.taskRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadTasks();
            }
        });

        viewModel = new TaskViewModel(dataBinding);
    }

    private void initData() {
        new TaskListPresenter(this);
    }

    @Override
    public void onLoadTasks(List<Task> tasks) {
        adapter.updateSource(tasks);
        viewModel.setTaskCount(tasks.size());
    }

    @Override
    public void onDeleteTask(Task task) {
        adapter.deleteData(task);
        viewModel.setTaskCount(adapter.getItemCount());
    }

    @Override
    public void onAddTask(Task task) {
        adapter.addData(task);
        viewModel.setTaskCount(adapter.getItemCount());
    }

    @Override
    public void toDetail(Task task) {
        Toast.makeText(this, task.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(TaskListContract.Presenter presenter) {
        this.presenter = presenter;
        dataBinding.setPresenter(this.presenter);
        adapter = new TaskAdapter(Activity_Task_List.this, null, presenter);
        dataBinding.taskList.setAdapter(adapter);
        dataBinding.setViewmodel(viewModel);
        presenter.loadTasks();
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}
