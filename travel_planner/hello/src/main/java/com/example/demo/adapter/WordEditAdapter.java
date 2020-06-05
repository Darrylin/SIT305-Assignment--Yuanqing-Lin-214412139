package com.example.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.listener.WordEditClickLinstener;
import com.example.demo.model.Note;

import java.util.List;

public class WordEditAdapter extends RecyclerView.Adapter<WordEditAdapter.ViewHolder> {

    private List<Note> wordModelList;
    private WordEditClickLinstener linstener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_english, tv_edit, tv_delete;

        public ViewHolder(View view) {
            super(view);
            tv_english = view.findViewById(R.id.tv_english);
            tv_edit = view.findViewById(R.id.tv_edit);
            tv_delete = view.findViewById(R.id.tv_delete);
        }
    }

    public WordEditAdapter(List<Note> wordModelList, WordEditClickLinstener linstener) {
        this.wordModelList = wordModelList;
        this.linstener = linstener;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_edit_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Note wordModel = wordModelList.get(position);
//        holder.tv_english.setText(wordModel.getEnglish());
        holder.tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linstener != null) {
                    linstener.edit(position);
                }
            }
        });
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linstener != null) {
                    linstener.delete(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordModelList.size();
    }


}
