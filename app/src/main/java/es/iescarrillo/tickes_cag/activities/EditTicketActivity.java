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
import es.iescarrillo.tickes_cag.models.Ticket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTicketActivity extends AppCompatActivity {

    private EditText etAmount;
    private Button btnBack, btnEdit;

    private Ticket t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ticket);

        etAmount=findViewById(R.id.etAmount);
        btnEdit=findViewById(R.id.btnEditTicket);
        btnBack=findViewById(R.id.btnBack);

        Intent intent = getIntent();
        //Nos traemos el objeto en el intent
        t = new Ticket();
        if (intent != null) {
            t = (Ticket) intent.getSerializableExtra("ticket");
        }


        TicketAPIService apiService = TicketAPIClient.getClient().create(TicketAPIService.class);


        btnEdit.setOnClickListener(v -> {

            t.setTotalAmount(Double.valueOf(etAmount.getText().toString()));

          Call editTicket=   apiService.updateTicket(t.getId(), t);


          editTicket.enqueue(new Callback() {
              @Override
              public void onResponse(Call call, Response response) {
                  if(response.isSuccessful()){
                      Toast toast = Toast.makeText(getApplicationContext(), "Ticket editado", Toast.LENGTH_SHORT);

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
    }
}