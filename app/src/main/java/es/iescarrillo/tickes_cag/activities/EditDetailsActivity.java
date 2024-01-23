package es.iescarrillo.tickes_cag.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.iescarrillo.tickes_cag.R;
import es.iescarrillo.tickes_cag.apiClient.TicketAPIClient;
import es.iescarrillo.tickes_cag.apiServices.TicketAPIService;
import es.iescarrillo.tickes_cag.models.DetailsTicket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDetailsActivity extends AppCompatActivity {

    private EditText etAmount, etDescription;

    private Button addDetails, btnBack;

    private DetailsTicket dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        Intent intent = getIntent();
        //Nos traemos el objeto en el intent
        dt = new DetailsTicket();
        if (intent != null) {
            dt = (DetailsTicket) intent.getSerializableExtra("detail");
        }


        etAmount=findViewById(R.id.etAmount);
        etDescription=findViewById(R.id.etDescription);
        addDetails=findViewById(R.id.btnAddDetails);
        btnBack=findViewById(R.id.btnBack);


        etAmount.setText(dt.getAmount().toString());
        etDescription.setText(dt.getDescription().toString());

        TicketAPIService apiService = TicketAPIClient.getClient().create(TicketAPIService.class);


        addDetails.setOnClickListener(v -> {
            dt.setAmount(Double.valueOf(etAmount.getText().toString()));
            dt.setDescription(etDescription.getText().toString());


            Call updateDetail = apiService.updateDetail(dt.getId(), dt);

            updateDetail.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Toast toast;
                    if(response.isSuccessful()){
                        toast = Toast.makeText(getApplicationContext(), "Detalle editado", Toast.LENGTH_SHORT);

                    }else {
                        toast = Toast.makeText(getApplicationContext(), "Fallo al editar", Toast.LENGTH_SHORT);

                    }
                    toast.show();
                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            });

            Intent goBack = new Intent(this, MainActivity.class);
            startActivity(goBack);


        });

        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}