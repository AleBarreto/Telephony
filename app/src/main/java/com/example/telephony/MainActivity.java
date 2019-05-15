package com.example.telephony;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, InfoCursorAdapter.ClickSimInfo {


    private RecyclerView recyclerView;
    private InfoCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);

        adapter = new InfoCursorAdapter();
        adapter.setHasStableIds(true);
        adapter.setClickSimInfo(this);
        recyclerView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                SimInfoContract.CONTENT_URI,
                null, null, null,
                SimInfoContract.DISPLAY_NAME);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.setCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.setCursor(null);
    }

    @Override
    public void itemClickSimInfo(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(SimInfoContract._ID));

    }
}
