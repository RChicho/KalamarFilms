package com.example.invitaapp.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.invitaapp.R;
import com.example.invitaapp.Utils.ResultListener;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginFragment;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsuarioLogin;
    private EditText editTextPasswordLogin;
    private Button buttonRegistrarLogin;
    private Button buttonIngresarLogin;
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    private LoginButton loginButton;


    public static final String CLAVE = "CLAVE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getIntent();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


        mAuth = FirebaseAuth.getInstance();


        buttonRegistrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validacionRegister();
            }
        });

        buttonIngresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validacionDeLogin();
            }
        });

        callbackManager=CallbackManager.Factory.create();
        encontrarComponentes();
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email","user_birthday","user_friends"
        ));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            Toast.makeText(LoginActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "pasaron cosas", Toast.LENGTH_SHORT).show();

            }
        });



    }


    private void validacionRegister() {
        if (editTextUsuarioLogin != null && editTextPasswordLogin != null) {
            register();
        } else {
            Toast.makeText(LoginActivity.this, "MAIL O PASSWORD INVALIDO", Toast.LENGTH_SHORT).show();
        }
    }


    private void validacionDeLogin() {
        if (editTextUsuarioLogin != null && editTextPasswordLogin != null) {
            login();
        } else {
            Toast.makeText(LoginActivity.this, "MAIL O PASSWORD INVALIDO", Toast.LENGTH_SHORT).show();
        }
    }


    private void login() {

        String email = editTextUsuarioLogin.getText().toString();
        String password = editTextPasswordLogin.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            irAlHomeDeLaApp(user.getEmail());
                            Toast.makeText(LoginActivity.this, "INICIO OK", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "El usuario no se pudo loguear", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void register() {

        String email = editTextUsuarioLogin.getText().toString();
        String password = editTextPasswordLogin.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            irAlHomeDeLaApp(user.getEmail());
                            Toast.makeText(LoginActivity.this, "USUARIO REGISTRADO", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LoginActivity.this, "El usuario no pudo registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            irAlHomeDeLaApp(currentUser.getEmail());
        }
    }





    /*private void cambiarDeActivity(){

       // String username = null;
        Intent intent = new Intent(this,MainActivity.class);
        //Bundle bundle = new Bundle();
        //bundle.putString(MainActivity.CLAVE_LOGIN, username);
        //intent.putExtras(bundle);
        limpiarCampos();
        startActivity(intent);
    }*/


    private void irAlHomeDeLaApp(String userName) {

        Bundle bundle = new Bundle();
        bundle.putString("USER_NAME", userName);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);

        limpiarCampos();
        startActivity(intent);

    }

    private void limpiarCampos() {
        editTextUsuarioLogin.setText("");
        editTextPasswordLogin.setText("");
    }

    private void encontrarComponentes() {
        editTextUsuarioLogin = findViewById(R.id.editTextFragmentLoginUsuario);
        editTextPasswordLogin = findViewById(R.id.editTextFragmentLoginPassword);
        buttonIngresarLogin = findViewById(R.id.ButtonLoginFragmentIngresar);
        buttonRegistrarLogin = findViewById(R.id.ButtonLoginFragmentRegistrar);
        loginButton = findViewById(R.id.login_button);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }




}

