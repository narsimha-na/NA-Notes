package com.maverick.nanotes.adapter;

import android.content.Context;
import android.icu.text.MessagePattern;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.DiffResult;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.maverick.nanotes.R;
import com.maverick.nanotes.model.Notes;

import java.util.List;

public class NoteAdapter extends ListAdapter<Notes,NoteAdapter.NoteViewHolder> {

    private OnItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Notes> DIFF_CALLBACK = new DiffUtil.ItemCallback<Notes>() {
        @Override
        public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                     oldItem.getDesc().equals(newItem.getDesc()) &&
                    oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.isImp() == newItem.isImp();
        }
    };



    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holders, int position) {
        NoteViewHolder holder = holders;
        Notes model = getItem(position);

        holder.titleV.setText(model.getTitle());
        holder.descV.setText(model.getDesc());
        holder.dateV.setText(model.getDate());
    }

    public Notes getNoteAt(int position){
    	return getItem(position);
    }

    public  class  NoteViewHolder extends RecyclerView.ViewHolder{

	    private TextView titleV,descV,dateV;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            titleV = itemView.findViewById(R.id.item_note_list_title);
            descV = itemView.findViewById(R.id.item_note_list_desc);
            dateV = itemView.findViewById(R.id.item_note_list_time);

            itemView.setOnClickListener(v->{
            	int position = getAdapterPosition();
            	if(listener != null && position != RecyclerView.NO_POSITION){
            		listener.onItemClick(getItem(position));
	            }
            });
        }
    }

    public interface OnItemClickListener{
    	void onItemClick(Notes note);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
    	this.listener = listener;
    }
}
