package com.example.fran.examenpmdm;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fran.examenpmdm.dummy.DummyContent;

/**
 * A fragment representing a single objeto detail screen.
 * This fragment is either contained in a {@link objetoListActivity}
 * in two-pane mode (on tablets) or a {@link objetoDetailActivity}
 * on handsets.
 */
public class objetoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public objetoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_objeto_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.objeto_detail)).setText(mItem.details);
        }

        /**
         * El btnBorrar elimina el texto que hay
         */
        Button btnBorrar = (Button) rootView.findViewById(R.id.btnBorrar);
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)//construye isInLayout para la versión 4.2
            @Override
            public void onClick(View v) {
                //SE REEMPLAZA EL ITEM POR UN TEXT SIN NADA
                ((TextView) v.findViewById(R.id.objeto_detail)).setText("");
                //EN CASO QUE LA APLICACION ESTÉ EN PORT SE CERRARA EL ACTIVITY, SI NO, NO HACE NADA
                if (rootView == null || !rootView.isInLayout()) {//En la versión 4.2 no me permite hacer isInLayout
                    getActivity().finish();
                } else {

                }
            }
        });

        return rootView;
    }
}
