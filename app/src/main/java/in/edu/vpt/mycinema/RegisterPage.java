package in.edu.vpt.mycinema;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends Activity implements View.OnClickListener {

    private Button register_user;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextMobile_no;
    private EditText editTextAge;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_page);

        progressDialog =new ProgressDialog(this);

        register_user = (Button) findViewById(R.id.btnRegister);
        editTextName =(EditText)  findViewById(R.id.etname);
        editTextEmail =(EditText)  findViewById(R.id.etUserEmail);
        editTextPassword =(EditText) findViewById(R.id.etUserPassword);
        editTextMobile_no =(EditText) findViewById(R.id.etMoibleno);
        editTextAge =(EditText)  findViewById(R.id.etPincode);


        register_user.setOnClickListener(this);

        //initializing firebase auth object
        firebaseAuth=FirebaseAuth.getInstance();

    }


    private void registerUser(){
        final String Name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim();
        final String mobile_no = editTextMobile_no.getText().toString().trim();
        final String age = editTextAge.getText().toString().trim();

        if (Name.isEmpty()) {
            //Firstname is empty
            Toast.makeText(this,"Please Enter First Name",Toast.LENGTH_SHORT).show();
            return;
        }

        if (mobile_no.isEmpty()) {
            //Lastname is empty
            Toast.makeText(this,"Please Enter Mobile Number",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email) ){
            //email is empty
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(Password) ){
            //password is empty
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        if (age.isEmpty()) {
            //Lastname is empty
            Toast.makeText(this,"Please Enter Age",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(
                                    Name,
                                    email,
                                    mobile_no,
                                    age
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterPage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    } else {
                                        Toast.makeText(RegisterPage.this, "Could Not Register..Please Try Again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(RegisterPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });

    }

    @Override
    public void onClick(View v) {
        if (v == register_user) {
            registerUser();
        }

    }
}