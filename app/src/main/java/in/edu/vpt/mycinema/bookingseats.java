package in.edu.vpt.mycinema;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bookingseats extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText seats;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingseats);

        firebaseAuth=FirebaseAuth.getInstance();
        Button button = (Button)findViewById(R.id.btnproceed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendata();
                startActivity(new Intent(bookingseats.this,HomePage.class));
            }
        });

        seats=(EditText) findViewById(R.id.edseats);

        spinner = (Spinner) findViewById(R.id.timeslot);
        adapter = ArrayAdapter.createFromResource(this, R.array.Select, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city=parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),city,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void sendata(){
        String datafieldtext=seats.getText().toString();
        String datafieldtext2=city.toString();

        in.edu.vpt.mycinema.Movie plumberUser=new in.edu.vpt.mycinema.Movie(datafieldtext,datafieldtext2);
          String user_id=firebaseAuth.getCurrentUser().getUid();
        DatabaseReference current_user= FirebaseDatabase.getInstance().getReference().child("MovieTicket").child(user_id);
        current_user.setValue(plumberUser);
    }
}
