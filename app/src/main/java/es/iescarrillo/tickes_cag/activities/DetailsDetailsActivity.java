package es.iescarrillo.tickes_cag.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.iescarrillo.tickes_cag.R;
import es.iescarrillo.tickes_cag.apiClient.TicketAPIClient;
import es.iescarrillo.tickes_cag.apiServices.TicketAPIService;
import es.iescarrillo.tickes_cag.models.DetailsTicket;
import es.iescarrillo.tickes_cag.models.Ticket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsDetailsActivity extends AppCompatActivity {

    private TextView tvID, tvAmount, tvDescription;

    private Button btnEdit, btnDelete, btnBack;

    private DetailsTicket dt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_details);

        Intent intent = getIntent();
        //Nos traemos el objeto en el intent
        dt = new DetailsTicket();
        if (intent != null) {
            dt = (DetailsTicket) intent.getSerializableExtra("detail");
        }

        tvID=findViewById(R.id.tvID);
        tvAmount=findViewById(R.id.tvAmount);
        tvDescription=findViewById(R.id.tvDescription);
        btnEdit=findViewById(R.id.btnEdit);
        btnDelete=findViewById(R.id.btnDelete);
        btnBack=findViewById(R.id.btnBack);

        tvAmount.setText("Precio: " +dt.getAmount().toString());
        tvDescription.setText("Descripción: " +dt.getDescription().toString());
        tvID.setText("ID: " + dt.getId().toString());

        TicketAPIService apiService = TicketAPIClient.getClient().create(TicketAPIService.class);



        btnDelete.setOnClickListener(v -> {
            Call deleteDetail = apiService.deleteDetail(dt.getId());

            deleteDetail.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()){
                        Toast toast = Toast.makeText(getApplicationContext(), "Detalle eliminado", Toast.LENGTH_SHORT);

                        toast.show();
                    }
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

        btnEdit.setOnClickListener(v -> {

            Intent edit = new Intent(this, EditDetailsActivity.class);

            edit.putExtra("detail", dt);
            startActivity(edit);
        });
    }
}