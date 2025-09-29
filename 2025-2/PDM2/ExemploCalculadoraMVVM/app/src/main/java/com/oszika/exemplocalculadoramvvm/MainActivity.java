package com.oszika.exemplocalculadoramvvm;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.oszika.exemplocalculadoramvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private CalculadoraViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(CalculadoraViewModel.class);

        viewModel.getResultado().observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.textViewResultado.setText(s);
            }
        });

        binding.buttonCalculadora.setOnClickListener(view -> {
            String s1 = binding.editTextN1.getText().toString();
            String s2 = binding.editTextN2.getText().toString();
            String op = binding.editTextOp.getText().toString();

            double n1 = Double.parseDouble(s1);
            double n2 = Double.parseDouble(s2);
            viewModel.calcularVM(n1, n2, op);
        });

    }
}