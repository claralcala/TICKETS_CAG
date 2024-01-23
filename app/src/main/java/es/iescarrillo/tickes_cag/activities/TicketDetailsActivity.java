package es.iescarrillo.tickes_cag.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import es.iescarrillo.tickes_cag.R;
import es.iescarrillo.tickes_cag.adapters.DetailsTicketAdapter;
import es.iescarrillo.tickes_cag.apiClient.TicketAPIClient;
import es.iescarrillo.tickes_cag.apiServices.TicketAPIService;
import es.iescarrillo.tickes_cag.models.DetailsTicket;
import es.iescarrillo.tickes_cag.models.Ticket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketDetailsActivity extends AppCompatActivity {

    private Ticket t;

    private ListView lvDetails;

    private TextView tvID, tvAmount, tvDate;

    private Button btnAdd, btnEdit, btnDelete, btnBack;

    private ArrayList<DetailsTicket> details;

    private DetailsTicketAdapter detailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);

        Intent intent = getIntent();
        //Nos traemos el objeto en el intent
        t = new Ticket();
        if (intent != null) {
            t = (Ticket) intent.getSerializableExtra("ticket");
        }



        details=new ArrayList<>();

        tvID=findViewById(R.id.tvID);
        tvAmount=findViewById(R.id.tvTotalAmount);
        tvDate=findViewById(R.id.tvDate);
        btnAdd=findViewById(R.id.btnAddDetail);
        btnEdit=findViewById(R.id.btnEdit);
        btnDelete=findViewById(R.id.btnDelete);
        btnBack=findViewById(R.id.btnBack);



        lvDetails=findViewById(R.id.lvDetails);

        tvID.setText("ID: " + t.getId().toString());
        tvAmount.setText("Precio total: " +t.getTotalAmount().toString());
        tvDate.setText("Fecha de creación: " + t.getCreationDate().toString());

        detailsAdapter=new DetailsTicketAdapter(getApplicationContext(), details);


        TicketAPIService apiService = TicketAPIClient.getClient().create(TicketAPIService.class);

        Call callDetails = apiService.getDetails(t.getId());

        callDetails.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()){
                    ArrayList<DetailsTicket> detailList = (ArrayList<DetailsTicket>) response.body();

                    for(DetailsTicket dt : detailList){
                        details.add(dt);
                    }

                    lvDetails.setAdapter(detailsAdapter);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });


    }
}