package babbarabhishek.notetakingapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private  NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    // constructor
    public  NoteRepository(Application application)
    {
        NoteDataBase dataBase=NoteDataBase.getInstance(application);
        noteDao =dataBase.noteDao();
        allNotes=noteDao.getAllNotes();

    }

    public void insert(Note note)
    {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public void  update(Note note)
    {
        new updateNoteAsyncTask(noteDao).execute(note);
    }
    public  void delete(Note note)
    {
        new deleteNoteAsyncTask(noteDao).execute(note);
    }
    public  void deleteAllNotes(Note note)
    {
        new DeleteAllNotesAsyncTask(noteDao).execute(note);
    }
    public LiveData<List<Note>> getAllNotes()
    {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {

        private NoteDao noteDao;
        private InsertNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class updateNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {

        private NoteDao noteDao;
        private updateNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class deleteNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {

        private NoteDao noteDao;
        private deleteNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Note,Void,Void>
    {

        private NoteDao noteDao;
        private DeleteAllNotesAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete_all_notes();
            return null;
        }
    }
}
