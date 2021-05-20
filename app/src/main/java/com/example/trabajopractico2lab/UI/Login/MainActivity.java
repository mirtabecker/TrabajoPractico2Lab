package com.example.trabajopractico2lab.UI.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trabajopractico2lab.R;

public class MainActivity extends AppCompatActivity {

    private EditText email,password;
    private TextView mensaje;
    private Button login, registrar;
    private MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
    }
    public void inicializar(){
        email= findViewById(R.id.etCorreo);
        password=findViewById(R.id.etPassword);
        mensaje= findViewById(R.id.tvMensaje);
        login=findViewById(R.id.btLogin);
        registrar=findViewById(R.id.btRegistrar);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        vm.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mensaje.setText(s);
                mensaje.setVisibility(View.VISIBLE);
                email.setText("");
                password.setText("");
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.autenticar(email.getText().toString(),password.getText().toString());
                email.setText("");
                password.setText("");
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.activityRegistar();
            }
        });
    }
}