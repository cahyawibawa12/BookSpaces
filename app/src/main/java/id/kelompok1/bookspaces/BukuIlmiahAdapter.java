package id.kelompok1.bookspaces;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.kelompok1.bookspaces.R;

public class BukuIlmiahAdapter extends RecyclerView.Adapter<BukuIlmiahAdapter.ViewHolder> {
    private List<BukuHandler> bukuHandlerList;
    private Context context;
    private RecyclerView recyclerView;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemJudul;
        TextView itemKategori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemJudul = itemView.findViewById(R.id.judul_ilmiah);
            itemKategori = itemView.findViewById(R.id.value_kategori_ilmiah);
        }
    }

    public BukuIlmiahAdapter(List<BukuHandler> bukuHandlerList, Context context, RecyclerView recyclerView) {
        this.bukuHandlerList = bukuHandlerList;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public BukuIlmiahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_ilmiah, parent, false);
        BukuIlmiahAdapter.ViewHolder viewHolder = new BukuIlmiahAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BukuIlmiahAdapter.ViewHolder holder, int position) {
        BukuHandler bukuHandler = bukuHandlerList.get(position);
        holder.itemJudul.setText(String.valueOf(bukuHandler.getJudul()));
        holder.itemKategori.setText(String.valueOf(bukuHandler.getKategori()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer itemId = Integer.valueOf(bukuHandler.getId());
                Intent gotoDetail = new Intent(holder.itemView.getContext(), DetailBukuActivity.class);
                gotoDetail.putExtra("id", itemId);
                holder.itemView.getContext().startActivity(gotoDetail);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return bukuHandlerList.size();
    }
}
