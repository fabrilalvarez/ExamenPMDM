package com.example.fran.examenpmdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * An activity representing a list of objetos. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link objetoDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link objetoListFragment} and the item details
 * (if present) is a {@link objetoDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link objetoListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class objetoListActivity extends AppCompatActivity
        implements objetoListFragment.Callbacks, objetoDetailFragment.OnItemSelectedListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objeto_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.objeto_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((objetoListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.objeto_list))
                    .setActivateOnItemClick(true);
        }
        //lanzamos el metodo lanzarGiro
        lanzarGiro();
        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Si la variable de values config.xml es true
     */
    public void lanzarGiro() {
        if (getResources().getBoolean(R.bool.pantallaEsLand)) {
            Toast.makeText(getApplicationContext(), "Tumbado", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Callback method from {@link objetoListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(objetoDetailFragment.ARG_ITEM_ID, id);
            objetoDetailFragment fragment = new objetoDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.objeto_detail_container, fragment)
                    .commit();
        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, objetoDetailActivity.class);
            detailIntent.putExtra(objetoDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    /**
     * Implementamos  objetoDetailFragment.OnItemSelectedListener
     *
     * @param link
     */
    @Override
    public void enviarOK(String link) {
        Toast.makeText(getApplicationContext(), link, Toast.LENGTH_SHORT).show();
    }
}
