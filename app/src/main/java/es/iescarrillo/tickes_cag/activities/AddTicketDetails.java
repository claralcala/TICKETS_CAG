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
import es.iescarrillo.tickes_cag.models.Ticket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTicketDetails extends AppCompatActivity {

    private EditText etAmount, etDescription;

    private Button addDetails, btnBack;

    private Ticket t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket_details);

        Intent intent = getIntent();
        //Nos traemos el objeto en el intent
        t = new Ticket();
        if (intent != null) {
            t = (Ticket) intent.getSerializableExtra("ticket");
        }



        etAmount=findViewById(R.id.etAmount);
        etDescription=findViewById(R.id.etDescription);
        addDetails=findViewById(R.id.btnAddDetails);
        btnBack=findViewById(R.id.btnBack);



        TicketAPIService apiService = TicketAPIClient.getClient().create(TicketAPIService.class);


        addDetails.setOnClickListener(v -> {

            DetailsTicket dt = new DetailsTicket();

            dt.setTicket(t);
            dt.setAmount(Double.valueOf(etAmount.getText().toString()));
            dt.setDescription(etDescription.getText().toString());
            dt.setId(0);

            Call addDetail= apiService.postDetail(dt);

            addDetail.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if(response.isSuccessful()){
                        Toast toast = Toast.makeText(getApplicationContext(), "Insercion realizada", Toast.LENGTH_SHORT);

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


    }
}