package babbarabhishek.notetakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE=
            "babbarabhishek.notetakingapp.EXTRA_TITLE";
    public static final String EXTRA_ID=
            "babbarabhishek.notetakingapp.EXTRA_ID";
    public static final String EXTRA_DESCRIPTION=
            "babbarabhishek.notetakingapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY=
            "babbarabhishek.notetakingapp.EXTRA_PRIORITY";



    EditText ettitle;
    EditText etdescription;
    NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ettitle = findViewById(R.id.etTitle);
        etdescription = findViewById(R.id.etDescription);
        numberPicker = findViewById(R.id.number_picker_priority);
//        String[] nums = new String[10];
//        for(int i=0; i<nums.length; i++)
//            nums[i] = Integer.toString(i);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
//        numberPicker.setWrapSelectorWheel(false);
//        numberPicker.setDisplayedValues(nums);
      // numberPicker.setValue(1);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_24);

        // get the intent who started this activity
        Intent intent= getIntent();
        if(intent.hasExtra(EXTRA_ID)) // if the request code was 2, then it was edit request so extra id will be passed
        {
            ettitle.setText(intent.getStringExtra(EXTRA_TITLE));
            etdescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));

            setTitle("Edit Note");
        }
        else
        setTitle("Add Note");
    }

    public void saveNote()
    {
        String title=ettitle.getText().toString();
        String description=etdescription.getText().toString();
        int priority= numberPicker.getValue();

        if(title.trim().isEmpty()||description.trim().isEmpty())
        {
            Toast.makeText(this,"enter both title and description",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);
        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1)
        {
            data.putExtra(EXTRA_ID,id);  // now change on activity result method in main activity
        }
        setResult(RESULT_OK,data);
        finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}