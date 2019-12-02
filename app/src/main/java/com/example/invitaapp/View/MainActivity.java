package com.example.invitaapp.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonIOException;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements FragmentListaPeliculas.ListenerDelFragment, NavigationView.OnNavigationItemSelectedListener ,FragmentPeliculasSimilares.ListenerDelFragment{


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    public static final String CLAVE_LOGIN = "clave_login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(this.getApplicationContext());
        //AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);


        encontrarVistas();
//        AccessToken accessToken= AccessToken.getCurrentAccessToken();
      /*  GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try{
                    String email = object.getString("email");
                    String birthday = object.getString("birthday");
                } catch (JsonIOException e){
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();*/


        //pegarFragment(new FragmentListaPeliculas());


        pegarPrimerFragment(new FragmentListaPeliculas());

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.open_drawer,
                        R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setBackgroundColor(getResources().getColor(R.color.negro));


        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setIcon(R.drawable.toolbar_kalamar);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectedMenuItem(menuItem);
                return true;
            }
        });

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.blanco));

    }

    private void encontrarVistas() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView1);
        toolbar = findViewById(R.id.ToolBarActivityMain);
    }


    private void selectedMenuItem(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuPrincipal_inicio:
                pegarFragment(new FragmentListaPeliculas());
                drawerLayout.closeDrawers();
                break;

            case R.id.menuPrincipal_perfil:
                Toast.makeText(this, "PROXIMAMENTE PAG PERFIL", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuPrincipal_favoritos:
                Toast.makeText(this, "PROXIMAMENTE PAG FAVORITOS", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuPrincipal_settings:
                Toast.makeText(this, "PROXIMAMENTE PAG SETTINGS", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuPrincipal_juegos:
                Toast.makeText(this, "PROXIMAMENTE PAG JUEGOS", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuPrincipal_acerca_calamar:
                pegarFragment(new FragmentSomos());
                drawerLayout.closeDrawers();
                break;

            case R.id.menuPrincipal_logout:
                FirebaseAuth.getInstance().signOut();
                cambiarDeActivity();
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    private void pegarFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ContenedorDelMain, fragment)
                .replace(R.id.ContenedorDelMain, fragment)
                .addToBackStack(null)
                .commit();
    }
    private void pegarPrimerFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ContenedorDelMain, fragment)
                .commit();
    }


    @Override
    public void recibirPelicula(Pelicula pelicula) {

        FragmentDetallePelicula fragmentDetallePelicula = new FragmentDetallePelicula();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetallePelicula.CLAVE_PELICULA, pelicula);
        fragmentDetallePelicula.setArguments(bundle);
        pegarFragment(fragmentDetallePelicula);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    private void cambiarDeActivity() {
        String username = null;
        Intent intent = new Intent(this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(LoginActivity.CLAVE, username);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    public void obtenerKeyHash() {
        String keyHash = "";
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("MI KEY HASH: " + keyHash);}}






