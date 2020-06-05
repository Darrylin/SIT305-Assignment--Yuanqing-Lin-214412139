package com.example.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demo.R;
import com.example.demo.activity.AddActivity;
import com.example.demo.model.Note;
import com.example.demo.util.AppDBHelp;
import com.example.demo.util.SPHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Notepad view, table control displays data list
public class HomeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Note> noteList;
    private List<Map<String, String>> dataList;

    public static HomeFragment newInstance(String text) {
        HomeFragment fragmentCommon = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main, container, false);
        initView();
        return view;
    }

    private void initView() {
        gridView = view.findViewById(R.id.gridview);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                intent.putExtra("note", noteList.get(position));
                startActivity(intent);
            }
        });
        view.findViewById(R.id.img).setOnClickListener(this);
        ((EditText) view.findViewById(R.id.edittext)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                noteList = AppDBHelp.getInstance(getActivity()).findNote(SPHelper.getInstance(getActivity()).getUserId(), s.toString());
                dataList.clear();
                for (Note note : noteList) {
                    Map<String, String> hash = new HashMap<>();
                    hash.put("id", note.getId() + "");
                    hash.put("content", note.getContent());
                    hash.put("date", note.getDate());
                    dataList.add(hash);
                }
                adapter.notifyDataSetChanged();
            }
        });
        refreshData();
    }

    public void refreshData() {
        noteList = AppDBHelp.getInstance(getActivity()).findNote(SPHelper.getInstance(getActivity()).getUserId());
        dataList = new ArrayList<>();
        for (Note note : noteList) {
            Map<String, String> hash = new HashMap<>();
            hash.put("id", note.getId() + "");
            hash.put("content", note.getContent());
            hash.put("date", note.getDate());
            dataList.add(hash);
        }
        adapter = new SimpleAdapter(getActivity(), dataList, R.layout.act_note_item, new String[]{"content", "date"}, new int[]{R.id.tv_content, R.id.tv_date});
        gridView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img:
                startActivity(new Intent(getActivity(), AddActivity.class));
                break;
            default:
                break;
        }
    }

}
