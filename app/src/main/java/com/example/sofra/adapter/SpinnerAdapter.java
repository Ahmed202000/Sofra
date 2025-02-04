package com.example.sofra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.sofra.R;
import com.example.sofra.data.model.listOfCity.ListOfCityData;
import com.example.sofra.data.model.listOfTown.ListOfTown;
import com.example.sofra.data.model.listOfTown.ListOfTownData;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflter;
    private List<ListOfCityData> CityDataList = new ArrayList<>();
    private List<ListOfTownData> listOfTownData= new ArrayList<>();
    public int selectedId = 0;

    public SpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<ListOfCityData> CityDataList, String hint) {
        this.CityDataList = new ArrayList<>();
        this.CityDataList.add(new ListOfCityData(0, hint));
        this.CityDataList.addAll(CityDataList);
    }
//
//    public void setDataTown(List<ListOfTownData> listOfTownData, String hint) {
//        this.listOfTownData= new ArrayList<>();
//        this.listOfTownData.add(new ListOfTownData(0, hint));
//        this.listOfTownData.addAll(listOfTownData);
//    }
    @Override
    public int getCount() {
        return CityDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_spinner, null);//da el item ely byzhr gowa el spinner nfso

        TextView names = (TextView) view.findViewById(R.id.item_spinner_tv_text);

        names.setText(CityDataList.get(i).getName());
        selectedId = CityDataList.get(i).getId();//da el id ely ana wa2f 3aleh now

        return view;
    }
}