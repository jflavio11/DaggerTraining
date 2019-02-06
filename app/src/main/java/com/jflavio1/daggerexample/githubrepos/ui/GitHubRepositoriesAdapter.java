package com.jflavio1.daggerexample.githubrepos.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.domain.model.GithubRepositoryEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GitHubRepositoriesAdapter extends RecyclerView.Adapter<GitHubRepositoriesAdapter.ViewHolder> {

    private List<GithubRepositoryEntity> list;

    public GitHubRepositoriesAdapter(List<GithubRepositoryEntity> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.repository_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtRepositoryName.setText(list.get(position).getfullName());
        holder.txtRepositoryDescription.setText(list.get(position).getdescription());
        holder.txtRepositoryUrl.setText(list.get(position).geturl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtRepositoryName;
        TextView txtRepositoryDescription;
        TextView txtRepositoryUrl;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRepositoryName = itemView.findViewById(R.id.txt_full_name_repo);
            txtRepositoryDescription = itemView.findViewById(R.id.txt_description_repo);
            txtRepositoryUrl = itemView.findViewById(R.id.txt_url_repo);
        }
    }

}
