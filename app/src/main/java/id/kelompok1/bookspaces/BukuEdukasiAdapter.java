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

public class BukuEdukasiAdapter extends RecyclerView.Adapter<BukuEdukasiAdapter.ViewHolder> {
    private List<BukuHandler> bukuHandlerList;
    private Context context;
    private RecyclerView recyclerView;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemJudul;
        TextView itemKategori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemJudul = itemView.findViewById(R.id.judul_edukasi);
            itemKategori = itemView.findViewById(R.id.value_kategori_edukasi);
        }
    }

    public BukuEdukasiAdapter(List<BukuHandler> bukuHandlerList, Context context, RecyclerView recyclerView) {
        this.bukuHandlerList = bukuHandlerList;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public BukuEdukasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_edukasi, parent, false);
        BukuEdukasiAdapter.ViewHolder viewHolder = new BukuEdukasiAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BukuEdukasiAdapter.ViewHolder holder, int position) {
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
