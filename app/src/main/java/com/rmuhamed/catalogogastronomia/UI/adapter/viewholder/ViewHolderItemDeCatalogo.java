package com.rmuhamed.catalogogastronomia.UI.adapter.viewholder;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rmuhamed.catalogogastronomia.MODEL.Branch;
import com.rmuhamed.catalogogastronomia.R;
import com.rmuhamed.catalogogastronomia.UI.CatalogoApplication;

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
        RelativeLayout establishmentContainer = (RelativeLayout) this.itemView.findViewById(R.id.listing_item_establishment_info_container);

        TextView establishmentNameLabel = (TextView) this.itemView.findViewById(R.id.listing_item_establishment_name_label);
        establishmentNameLabel.setText(unBranch.getName());

        TextView establishmentTownLabel = (TextView) this.itemView.findViewById(R.id.listing_item_establishment_town_label);
        establishmentTownLabel.setText(unBranch.getLocation());

        TextView establishmentAddressLabel = (TextView) this.itemView.findViewById(R.id.listing_item_establishment_address_label);
        establishmentAddressLabel.setText(String.format("%s %s", unBranch.getStreet(), unBranch.getStreetNumber()));

        //ProgressBar progress = (ProgressBar) this.itemView.findViewById(R.id.listing_item_image_progress);

        ImageView imagen = (ImageView) this.itemView.findViewById(R.id.listing_item_image);

        if (unBranch.getImagesFather()!=null && !unBranch.getImagesFather().isEmpty()) {
            CatalogoApplication.class.cast(this.contexto.getApplicationContext()).showImage(unBranch.getImagesFather().get(0).getURL(), imagen);
        }

    }
}
