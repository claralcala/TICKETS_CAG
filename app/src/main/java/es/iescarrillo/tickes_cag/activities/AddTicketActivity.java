package es.iescarrillo.tickes_cag.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.iescarrillo.tickes_cag.R;
import es.iescarrillo.tickes_cag.apiClient.TicketAPIClient;
import es.iescarrillo.tickes_cag.apiServices.TicketAPIService;
import es.iescarrillo.tickes_cag.models.Ticket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTicketActivity extends AppCompatActivity {

    private EditText etAmount;
    private Button btnBack, btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);

        etAmount=findViewById(R.id.etAmount);
        btnAdd=findViewById(R.id.btnAddTicket);
        btnBack=findViewById(R.id.btnBack);

        TicketAPIService apiService = TicketAPIClient.getClient().create(TicketAPIService.class);

        btnAdd.setOnClickListener(v -> {
            Ticket ticket = new Ticket();
            ticket.setId(0);
            ticket.setTotalAmount(Double.valueOf(etAmount.getText().toString()));

            LocalDateTime now = LocalDateTime.now();
            String pattern = "dd/MM/yyyy HH:mm:ss";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

            String formatedDate = now.format(formatter);

            ticket.setCreationDate(formatedDate);

            Call postTicket= apiService.postTicket(ticket);

            postTicket.enqueue(new Callback() {
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

        btnBack.setOnClickListener(v -> {
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        });
    }
}