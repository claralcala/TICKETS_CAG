package es.iescarrillo.tickes_cag.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    private TicketAPIService apiService;

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


         apiService = TicketAPIClient.getClient().create(TicketAPIService.class);

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
                    calculateTotalAmount();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });


        lvDetails.setOnItemClickListener((parent, view, position, id) -> {
            DetailsTicket detail = (DetailsTicket) parent.getItemAtPosition(position);
            Intent details = new Intent(this, DetailsDetailsActivity.class);

            detail.setTicket(t);
            details.putExtra("detail", detail);
            startActivity(details);


        });


        btnDelete.setOnClickListener(v -> {
            Call deleteTicket = apiService.deleteTicket(t.getId());

            deleteTicket.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()){
                        Toast toast = Toast.makeText(getApplicationContext(), "Ticket borrado", Toast.LENGTH_SHORT);

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


        btnEdit.setOnClickListener(v -> {
            Intent edit = new Intent(this, EditTicketActivity.class);
            edit.putExtra("ticket", t);
            startActivity(edit);
        });


        btnAdd.setOnClickListener(v -> {
            Intent edit = new Intent(this, AddTicketDetails.class);
            edit.putExtra("ticket", t);
            startActivity(edit);
        });




    }

    private void calculateTotalAmount() {
        double newAmount = 0.0;
        if (!details.isEmpty()) {


        for (DetailsTicket dt : details) {
            newAmount += dt.getAmount();
        }
        t.setTotalAmount(newAmount);
        tvAmount.setText("Precio total: " + t.getTotalAmount().toString());

        Call updateAmount = apiService.updateTicket(t.getId(), t);

        updateAmount.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {

                    Toast toast = Toast.makeText(getApplicationContext(), "Precio total actualizado", Toast.LENGTH_SHORT);

                    toast.show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }
    }
}