package com.gaviota.carre.gaviota007;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreadosFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_creados, container, false);
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
}
