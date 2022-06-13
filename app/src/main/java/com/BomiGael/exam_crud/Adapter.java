package com.BomiGael.exam_crud;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    private final List<Users> modelList;

    public static String strShowName, strShowPhone, strShowMail, strShowPass;


    public Adapter(List<Users> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_items,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        try{
            String name = modelList.get(position).getNom();
            String phone = modelList.get(position).getContact();
            String mail = modelList.get(position).getMail();
            String pass = modelList.get(position).getMot_de_passe();
            holder.setData(name);

            holder.btn.setOnClickListener(view -> {
                view.getContext().startActivity(new Intent(view.getContext(), UserP.class));

                strShowName = name;
                strShowPhone = phone;
                strShowMail = mail;
                strShowPass = pass;
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{

        private final TextView name;

        final CardView cardView;
        final Button btn;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Nom);
            cardView = itemView.findViewById(R.id.cardV);
            btn = itemView.findViewById(R.id.btnShowProfil);
        }

        public void setData(String txtNom){
            name.setText(txtNom);
        }
    }
}
