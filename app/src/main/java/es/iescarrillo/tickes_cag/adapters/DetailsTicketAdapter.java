package es.iescarrillo.tickes_cag.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.iescarrillo.tickes_cag.R;
import es.iescarrillo.tickes_cag.models.DetailsTicket;
import es.iescarrillo.tickes_cag.models.Ticket;

public class DetailsTicketAdapter extends ArrayAdapter<DetailsTicket> {

    public DetailsTicketAdapter(Context context, List<DetailsTicket> detailsTickets){
        super(context, 0, detailsTickets);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtén el objeto Contact en la posición actual
        DetailsTicket dt  = getItem(position);

        // Reutiliza una vista existente o crea una nueva si es necesario
        if (convertView == null) {
            // Indicamos la vista plantilla
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_details, parent, false);
        }

        // Accede al TextView en el diseño de cada elemento del ListView
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);
        TextView tvAmount = convertView.findViewById(R.id.tvAmount);



        // Modificamos el texto a mostrar
        tvId.setText("ID: " +dt.getId());
        tvDescription.setText("Descrp.: " +dt.getDescription());
        tvAmount.setText("Precio: " + dt.getAmount().toString() + " €");

        return convertView;
    }
}
