package com.xm.sample.binding.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xm.sample.binding.contract.TaskListContract;
import com.xm.sample.binding.databinding.TaskItemBinding;
import com.xm.sample.binding.entity.Task;
import com.xm.sample.binding.itemactionhandle.TaskItemActionHandle;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context context;
    private List<Task> mDataSource;
    private LayoutInflater inflater;

    private TaskListContract.Presenter presenter;

    public TaskAdapter(Context ctx, List<Task> datas, TaskListContract.Presenter presenter) {
        this.context = ctx;
        this.mDataSource = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        this.presenter = presenter;

        if (datas != null) {
            mDataSource.addAll(datas);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TaskItemBinding binding = TaskItemBinding.inflate(inflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task entity = mDataSource.get(position);

        TaskItemBinding binding = holder.binding;
        binding.setTask(entity);
        binding.setActionHandle(new TaskItemActionHandle(presenter));
        binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TaskItemBinding binding;

        ViewHolder(TaskItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    public void updateSource(List<Task> data) {
        if (data == null) {
            return;
        }

        mDataSource.clear();
        mDataSource.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<Task> data) {
        if (data == null) {
            return;
        }

        mDataSource.addAll(data);
        notifyDataSetChanged();

    }

    public void addData(Task task) {
        mDataSource.add(task);
        notifyDataSetChanged();
    }

    public void deleteData(Task task) {
        int index = mDataSource.indexOf(task);
        mDataSource.remove(task);
        notifyItemRemoved(index);
    }

}
