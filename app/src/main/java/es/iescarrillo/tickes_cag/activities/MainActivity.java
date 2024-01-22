package es.iescarrillo.tickes_cag.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.iescarrillo.tickes_cag.R;
import es.iescarrillo.tickes_cag.adapters.TicketAdapter;
import es.iescarrillo.tickes_cag.apiClient.TicketAPIClient;
import es.iescarrillo.tickes_cag.apiServices.TicketAPIService;
import es.iescarrillo.tickes_cag.models.Ticket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lvTickets;
    private TicketAdapter tAdapter;


    private Ticket t;

    private EditText etAmount;

    private Button btnAdd;

    private ArrayList<Ticket> tickets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTickets=findViewById(R.id.lvTickets);
        etAmount=findViewById(R.id.etAmount);
        btnAdd=findViewById(R.id.btnAdd);

        tickets=new ArrayList<>();
        tAdapter = new TicketAdapter(getApplicationContext(), tickets);

        TicketAPIService apiService = TicketAPIClient.getClient().create(TicketAPIService.class);

        Call callTickets = apiService.getTickets();

        callTickets.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.isSuccessful()){
                    ArrayList<Ticket> ticketList = (ArrayList<Ticket>) response.body();

                    for(Ticket t : ticketList){
                        tickets.add(t);
                    }

                    lvTickets.setAdapter(tAdapter);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });




    }
}