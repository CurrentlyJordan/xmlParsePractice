package nyc.c4q.jordansmith.practicexmlparser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.jordansmith.practicexmlparser.Model.Item;

/**
 * Created by jordansmith on 1/27/17.
 */

public class ParkEventAdapter extends RecyclerView.Adapter<ParkEventAdapter.ParkEventViewHolder> {
    List<Item> itemList = new ArrayList<>();


    @Override
    public ParkEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        ParkEventViewHolder vh = new ParkEventViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ParkEventViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.bind(item);


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setData(List<Item> itemList){
        this.itemList = itemList;

    }

    public static class ParkEventViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView dateTextView;


        public ParkEventViewHolder(View itemView) {
            super(itemView);
            dateTextView = (TextView) itemView.findViewById(R.id.date_of_event);
            textView = (TextView) itemView.findViewById(R.id.name_of_event_textview);
        }

        public void bind(Item item) {
            textView.setText(item.getTitle());
            dateTextView.setText(item.getStartdate());
        }
    }
}
