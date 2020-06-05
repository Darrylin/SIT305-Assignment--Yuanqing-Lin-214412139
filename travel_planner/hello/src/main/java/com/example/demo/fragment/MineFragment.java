package com.example.demo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.demo.R;
import com.example.demo.activity.EditPwdActivity;
import com.example.demo.activity.LoginActivity;
import com.example.demo.util.SPHelper;


public class MineFragment extends Fragment implements View.OnClickListener {

    private View view;

    public static MineFragment newInstance(String text) {
        MineFragment fragmentCommon = new MineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mine, container, false);
        initView();
        return view;
    }

    private void initView() {
        view.findViewById(R.id.layout_editpwd).setOnClickListener(this);
        view.findViewById(R.id.layout_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_editpwd:
                startActivity(new Intent(getActivity(), EditPwdActivity.class));
                break;
            case R.id.layout_logout:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                SPHelper.getInstance(getActivity()).setRemeber(0);
                getActivity().finish();
                break;
            default:
                break;
        }
    }

}
