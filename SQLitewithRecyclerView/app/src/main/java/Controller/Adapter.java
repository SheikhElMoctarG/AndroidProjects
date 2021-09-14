package Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitewithrecyclerview.MainActivity;
import com.example.sqlitewithrecyclerview.R;

import java.util.List;

import Model.Data;
import Views.EditActivity;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;
    private List<Data> list;
    private DatabaseHelper databaseHelper;

    public Adapter(Context context, List<Data> list) {
        this.context = context;
        this.list = list;
    }

    public Adapter(Context context, List<Data> list, DatabaseHelper databaseHelper) {
        this.context = context;
        this.list = list;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Data data = list.get(position);
        holder.name.setText(data.getName());
        holder.address.setText(data.getAddress());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(position);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("position",data.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name,address;
        public ImageView edit,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    private void deleteData(int position){
        databaseHelper.deleteData(list.get(position));
        list.remove(position);
        MainActivity.notifyAdapter();
    }
}
