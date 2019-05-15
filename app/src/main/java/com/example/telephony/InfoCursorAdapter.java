package com.example.telephony;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoCursorAdapter extends RecyclerView.Adapter<InfoCursorAdapter.ViewHolder> {

    private Cursor mCursor;
    private ClickSimInfo clickSimInfo;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_sim_info, viewGroup, false);

        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = vh.getAdapterPosition();
                mCursor.moveToPosition(position);
                if (clickSimInfo != null) clickSimInfo.itemClickSimInfo(mCursor);
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        mCursor.moveToPosition(position);
        int idx_display_name = mCursor.getColumnIndex(SimInfoContract.DISPLAY_NAME);
        int idx_mcc = mCursor.getColumnIndex(SimInfoContract.MCC);
        int idx_mnc = mCursor.getColumnIndex(SimInfoContract.MNC);
        int idx_icc_id = mCursor.getColumnIndex(SimInfoContract.ICC_ID);
        String name = mCursor.getString(idx_display_name);
        String mcc = mCursor.getString(idx_mcc);
        String mnc = mCursor.getString(idx_mnc);
        String icc_id = mCursor.getString(idx_icc_id);
        viewHolder.tvName.setText(name);
        viewHolder.tvMccMnc.setText("MCC:"+mcc+" MNC:"+mnc);
        viewHolder.tvIccId.setText("ICC_ID: "+icc_id);
    }

    @Override
    public int getItemCount() {
        return (mCursor != null) ? mCursor.getCount() : 0;
    }

    @Override
    public long getItemId(int position) {
        if (mCursor != null) {
            if (mCursor.moveToPosition(position)) {
                int idx_id = mCursor.getColumnIndex(SimInfoContract._ID);
                return mCursor.getLong(idx_id);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public Cursor getCursor(){
        return mCursor;
    }

    public void setCursor(Cursor newCursor){
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    public void setClickSimInfo(ClickSimInfo clickSimInfo) {
        this.clickSimInfo = clickSimInfo;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvMccMnc;
        private TextView tvIccId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvMccMnc = itemView.findViewById(R.id.tv_mcc_mnc);
            tvIccId = itemView.findViewById(R.id.tv_icc_id);
        }
    }

    public interface ClickSimInfo {
        void itemClickSimInfo(Cursor cursor);
    }

}
