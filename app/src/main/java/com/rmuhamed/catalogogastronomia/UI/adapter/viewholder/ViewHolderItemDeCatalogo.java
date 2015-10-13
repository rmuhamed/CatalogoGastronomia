package com.rmuhamed.catalogogastronomia.UI.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rmuhamed.catalogogastronomia.MODEL.Branch;
import com.rmuhamed.catalogogastronomia.R;
import com.rmuhamed.catalogogastronomia.CatalogoApplication;

/**
 * Created by rmuhamed on 08/10/2015.
 */
public class ViewHolderItemDeCatalogo extends RecyclerView.ViewHolder {
    private final Context contexto;

    public ViewHolderItemDeCatalogo(View catalogoItemView, Context contexto) {
        super(catalogoItemView);
        this.contexto = contexto;
    }

    public void mostrarItemDelCatalogo(Branch unBranch) {
        TextView establishmentNameLabel = (TextView) this.itemView.findViewById(R.id.listing_item_establishment_name_label);
        establishmentNameLabel.setText(unBranch.getName());

        TextView establishmentTownLabel = (TextView) this.itemView.findViewById(R.id.listing_item_establishment_town_label);
        establishmentTownLabel.setText(unBranch.getLocation());

        TextView establishmentAddressLabel = (TextView) this.itemView.findViewById(R.id.listing_item_establishment_address_label);
        establishmentAddressLabel.setText(String.format("%s %s", unBranch.getStreet(), unBranch.getStreetNumber()));

        ImageView imagen = (ImageView) this.itemView.findViewById(R.id.listing_item_image);

        if (unBranch.getImagesFather()!=null && !unBranch.getImagesFather().isEmpty()) {
            int height = ((WindowManager) this.contexto.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();

            String imageRemoteUrl = String.format("%s?height=%d", unBranch.getImagesFather().get(0).getURL(), height/3) ;

            CatalogoApplication.class.cast(this.contexto.getApplicationContext()).showImage(imageRemoteUrl, imagen);
        }
    }
}
