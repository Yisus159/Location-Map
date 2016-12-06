package example.jesus.appfirebase01;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button iniciar,registrar;
    EditText usuario,password;

    FirebaseAuth.AuthStateListener mAunthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = (EditText) findViewById(R.id.usertext);
        password = (EditText) findViewById(R.id.pwuser);
        iniciar = (Button) findViewById(R.id.boton0);
        registrar = (Button) findViewById(R.id.boton1);

        iniciar.setOnClickListener(this);
        registrar.setOnClickListener(this);

        mAunthListener = new FirebaseAuth.AuthStateListener(){


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser(); //Firebase.getinstance().getcurrentuser():
                if (user != null){
                        //start activy o lo que sea

                        Log.i("SESION","Sesion Iniciada con Usuario: "+user.getEmail());

                    Context context = getApplicationContext();
                    CharSequence text = "Sesion Iniciada con Usuario: "+user.getEmail();
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }else{
                        Log.i("SESION","Sesion Cerrada");

                    Context context = getApplicationContext();
                    CharSequence text = "Sesion Cerrada";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

            }
        };

    }

   private void registrar(String name, String pass){
       FirebaseAuth.getInstance() .createUserWithEmailAndPassword(name,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   Log.i("SESION" ,"Usuario Creado Correctamente");

                   Context context = getApplicationContext();
                   CharSequence text = "Usuario Creado Correctamente";
                   int duration = Toast.LENGTH_LONG;

                   Toast toast = Toast.makeText(context, text, duration);
                   toast.show();


               }else{
                   Log.e("SESION",task.getException().getMessage()+"");
                   Context context = getApplicationContext();
                   CharSequence text = "Error: "+task.getException().getMessage();
                   int duration = Toast.LENGTH_LONG;

                   Toast toast = Toast.makeText(context, text, duration);
                   toast.show();
               }
           }
       });
   }

    private void iniciar(String name, String pass){
        FirebaseAuth.getInstance() .signInWithEmailAndPassword(name,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("SESION" ,"Usuario logeado Correctamente");

                    Context context = getApplicationContext();
                    CharSequence text = "Iniciando Sesion";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                }else{
                    Log.e("SESION",task.getException().getMessage()+"");
                    Context context = getApplicationContext();
                    CharSequence text = "Error: "+task.getException().getMessage();
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.boton0:
                String namelogin = usuario.getText().toString();
                String passlogin = password.getText().toString();
                iniciar(namelogin,passlogin);
                break;
            case R.id.boton1:
                String namereg = usuario.getText().toString();
                String passreg = password.getText().toString();
                registrar(namereg,passreg);
                break;
         }


    }

    @Override
    protected void onStart(){
            super.onStart();
            FirebaseAuth.getInstance().addAuthStateListener(mAunthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAunthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAunthListener);
        }
    }
}
