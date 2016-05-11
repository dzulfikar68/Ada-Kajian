package com.digitcreativestudio.adakajian;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.digitcreativestudio.adakajian.adapter.DaftarKajianAdapter;
import com.digitcreativestudio.adakajian.entity.Kajian;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDaftarKajian extends Fragment {

    private DaftarKajianAdapter mAdapter;
    String judul_kajian, ustad_kajian;

    ArrayList<Kajian> daftar_kajian = new ArrayList<Kajian>();

    public FragmentDaftarKajian() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_daftar_kajian, container, false);

        RecyclerView myList = (RecyclerView) rootView.findViewById(R.id.rv_kajian);
        myList.setNestedScrollingEnabled(false);


        Bundle extras = getArguments();
        if (extras != null) {
            daftar_kajian = extras.getParcelableArrayList("arraylist");

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
            myList.setLayoutManager(mLayoutManager);
            myList.setItemAnimator(new DefaultItemAnimator());
            myList.setAdapter(mAdapter);

            myList.setAdapter(new DaftarKajianAdapter(getContext(),daftar_kajian,
                    new DaftarKajianAdapter.OnItemClickListener() {
                        @Override public void onItemClick(Kajian item) {
                            judul_kajian=item.getJudul_kajian();
                            ustad_kajian= item.getUstad_kajian();
                            //Toast.makeText(getContext(), "KLIKED "+ustad_kajian, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getContext(), DetailKajianActivity.class);
                            intent.putExtra("judul_kajian", judul_kajian);
                            intent.putExtra("ustad_kajian", ustad_kajian);
                            startActivity(intent);
                        }
                    })
            );
        }
        // Inflate the layout for this fragment
        return rootView;
    }

}
