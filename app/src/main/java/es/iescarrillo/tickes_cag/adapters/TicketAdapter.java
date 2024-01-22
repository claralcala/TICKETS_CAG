package es.iescarrillo.tickes_cag.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.iescarrillo.tickes_cag.R;
import es.iescarrillo.tickes_cag.models.Ticket;

public class TicketAdapter extends ArrayAdapter<Ticket> {

    public TicketAdapter(Context context, List<Ticket> tickets){
        super(context, 0, tickets);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtén el objeto Contact en la posición actual
        Ticket t  = getItem(position);

        // Reutiliza una vista existente o crea una nueva si es necesario
        if (convertView == null) {
            // Indicamos la vista plantilla
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ticket, parent, false);
        }

        // Accede al TextView en el diseño de cada elemento del ListView
        TextView tvId = convertView.findViewById(R.id.tvid);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvAmount = convertView.findViewById(R.id.tvAmount);



        // Modificamos el texto a mostrar
        tvId.setText("ID: " +t.getId());
        tvDate.setText("Fecha creación: " +t.getCreationDate());
        tvAmount.setText("Total: " + t.getTotalAmount().toString() + " €");

        return convertView;
    }
}
