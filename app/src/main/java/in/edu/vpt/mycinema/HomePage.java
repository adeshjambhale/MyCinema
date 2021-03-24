package in.edu.vpt.mycinema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomePage extends AppCompatActivity {


    FirebaseAuth firebaseAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:

                    break;
                case R.id.nav_orders:
                    startActivity(new Intent(HomePage.this, Feedback.class));

                    break;
                case R.id.nav_logout:
                    startActivity(new Intent(HomePage.this, LoginActivity.class));
                    firebaseAuth.signOut();
                    finish();
                    break;

                default:
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        firebaseAuth=FirebaseAuth.getInstance();
        LinearLayout linearLayout=findViewById(R.id.dham);
        LinearLayout linearLayout1=findViewById(R.id.movie);
        LinearLayout linearLayout2=findViewById(R.id.movie1);
        LinearLayout linearLayout3=findViewById(R.id.movie2);

        BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.main_nav);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        Menu menu=navigation.getMenu();
        MenuItem menuItem=menu.getItem(0);
        menuItem.setChecked(true);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,bookingseats.class));
            }
        });
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,bookingseats.class));
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,bookingseats.class));
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,bookingseats.class));
            }
        });
        ImageView image = findViewById(R.id.image);
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image3 = findViewById(R.id.image3);

        String ur1 ="https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/URI_-_New_poster.jpg/220px-URI_-_New_poster.jpg";
        String url11 = "https://images.financialexpress.com/2019/01/total-dhamaal-poster-660-620x413.jpg";
        String url111 = "https://in.bmscdn.com/iedb/movies/images/mobile/listing/xxlarge/anandi-gopal-et00093208-02-01-2019-02-39-35.jpg" ;
        String url2 = "http://www.collegers.net/attachments/f46/744d1519305083-upcoming-bollywood-movies-2018-list-latest-bollywood-hindi-movies-release-dates-tumblr_p4jwuikun01wv9xft_og_1280.jpg";
        Picasso.with(this).load(ur1).into(image);
        Picasso.with(this).load(url11).into(image1);
        Picasso.with(this).load(url111).into(image2);
        Picasso.with(this).load(url2).into(image3);



    }

}
