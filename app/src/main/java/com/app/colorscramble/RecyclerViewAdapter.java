package com.app.colorscramble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.colorscramble.Model.RecyclerData;

import java.util.ArrayList;
import java.util.Collections;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<RecyclerData> recyclerDataArrayList;
    private Context mcontext;
    ArrayList<Integer> arl = new ArrayList<Integer>();

    public RecyclerViewAdapter(ArrayList<RecyclerData> recyclerDataArrayList, Context mcontext) {
        this.recyclerDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        RecyclerData recyclerData = recyclerDataArrayList.get(position);

        holder.ll_root.setBackgroundColor(recyclerData.getColor());

        holder.cv_root.setOnClickListener(view -> {

            if(recyclerDataArrayList.get(position).isValue()) {

                recyclerDataArrayList.get(position).setValue(false);

                holder.img_select.setVisibility(View.GONE);

                if(arl!=null&&arl.size()>0){

                    arl.remove(position);

                }

                notifyDataSetChanged();

            }
            else {

                recyclerDataArrayList.get(position).setValue(true);

                arl.add(position);

                if(arl.size()==2){

                    Collections.swap(recyclerDataArrayList, arl.get(0), arl.get(1));

                    notifyDataSetChanged();

                    for(int i=0;i<arl.size();i++){

                        recyclerDataArrayList.get(arl.get(i)).setValue(false);
                    }

                    arl.clear();
                }

                else {

                    holder.img_select.setVisibility(View.VISIBLE);
                }

            }



        });

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return recyclerDataArrayList.size();
    }

    //View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private CardView cv_root;
        private LinearLayout ll_root;
        private ImageView img_select;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_root = itemView.findViewById(R.id.ll_root);
            img_select = itemView.findViewById(R.id.img_select);
            cv_root = itemView.findViewById(R.id.cv_root);
        }

    }
}
