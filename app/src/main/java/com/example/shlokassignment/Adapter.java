package com.example.shlokassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {

Context context;

int count;

    List<String> al;
    String[] data ;


    public Adapter(List<String> al, int count ) {
        this.al = al;
        this.count = count;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Details details=new Details();


for (int i=7;i<al.size();i=i+27)
{
    details.setLTE_INSP_Label1(al.get(i));
    details.setLTE_INSP_Label2(al.get(i+1));
    details.setLTE_INSP_Label3(al.get(i+2));
    details.setLTE_INSP_Label4(al.get(i+4));
    System.out.println(al.get(i+1));
}



int len=details.getLTE_INSP_Label1().length();
String head=details.getLTE_INSP_Label1().substring(19,len-1);
holder.heading.setText(head);
        int poleng=details.getLTE_INSP_Label2().length();
        String pos=details.getLTE_INSP_Label2().substring(19,poleng-1);
        holder.id.setText(pos);
     int subhea=details.getLTE_INSP_Label3().length();
        String suhe=details.getLTE_INSP_Label3().substring(19,subhea-1);
        holder.subheading.setText(suhe);
        int subt=details.getLTE_INSP_Label4().length();
        String subtit=details.getLTE_INSP_Label4().substring(19,subt-1);
        holder.subtitle.setText(subtit);

    }

    @Override
    public int getItemCount() {
      return count;

    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView heading,subheading,subtitle,id;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            heading=itemView.findViewById(R.id.title);
            subheading=itemView.findViewById(R.id.sub_title);
            subtitle=itemView.findViewById(R.id.sub_data);
            id=itemView.findViewById(R.id.no);
        }
    }
}
