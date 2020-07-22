package babbarabhishek.notetakingapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Note_table")
public class Note {

    // these are the attributes for the table note_table
    @PrimaryKey(autoGenerate = true)  // sqlite will automatically increase the value
    private int id;
    private String title;
    private  String description;
    private  int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    // to access these values , we make getter and setters


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
