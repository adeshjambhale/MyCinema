package in.edu.vpt.mycinema;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class Feedback extends Activity {

    FirebaseAuth firebaseAuth;
 RatingBar ratingBar1;
  RatingBar ratingBar2;
  RatingBar ratingBar3;
    Button submit1;
    float rating1;
    float rating2;
    float rating3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_feedback);
        firebaseAuth=FirebaseAuth.getInstance();
        ratingBar1=findViewById(R.id.ratingbar1);
        ratingBar2=findViewById(R.id.ratingbar2);
        ratingBar3=findViewById(R.id.ratingbar3);
        submit1=findViewById(R.id.bt_submit);
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rating1=ratingBar1.getRating();
                rating2=ratingBar2.getRating();
                rating3=ratingBar3.getRating();
                sendata();
                startActivity(new Intent(Feedback.this,HomePage.class));
            }
        });
           }


    public void sendata(){
        String datafieldtext=Float.toString(rating1);
        String datafieldtext2=Float.toString(rating2);
        String datafieldtex2=Float.toString(rating3);
        FeedBacks plumberUser=new FeedBacks(datafieldtext,datafieldtext2,datafieldtex2);
        String user_id=firebaseAuth.getCurrentUser().getUid();
        DatabaseReference current_user= FirebaseDatabase.getInstance().getReference().child("FeedBack").child(user_id);
        current_user.setValue(plumberUser);
    }

}
