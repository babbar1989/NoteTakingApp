package babbarabhishek.notetakingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnItemClickListner listner;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currnote = notes.get(position);
        holder.TextViewTitle.setText(currnote.getTitle());
        holder.TextViewDescription.setText(currnote.getDescription());
        holder.TextViewPriority.setText(String.valueOf(currnote.getPriority()));


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int pos) {
        return notes.get(pos);
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        private TextView TextViewTitle;
        private TextView TextViewDescription;
        private TextView TextViewPriority;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            TextViewTitle = itemView.findViewById(R.id.text_view_title);
            TextViewDescription = itemView.findViewById(R.id.text_view_description);
            TextViewPriority = itemView.findViewById(R.id.text_viewPriority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (listner != null && pos != RecyclerView.NO_POSITION) {
                        listner.onItemClick(notes.get(pos));
                    }
                }
            });


        }
    }

    public interface OnItemClickListner {
        void onItemClick(Note note);
    }

    public void setOnItemClickListner(OnItemClickListner listner) {
        this.listner = listner;
    }
}
