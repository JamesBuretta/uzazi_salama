package org.ei.opensrp.mcare.pageradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ei.opensrp.mcare.R;
import org.ei.opensrp.mcare.datamodels.ChwFollowUpMother;
import org.ei.opensrp.mcare.datamodels.PreRegisteredMother;

import java.util.List;


/**
 * Created by martha on 8/22/17.
 */

public class CHWFollowUpPagerAdapter extends
        RecyclerView.Adapter<CHWFollowUpPagerAdapter.ViewHolder> {

    private List<ChwFollowUpMother> fMother;
    private Context mContext;

    public CHWFollowUpPagerAdapter(Context context, List<ChwFollowUpMother> mothers) {
        fMother = mothers;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contactView = inflater.inflate(R.layout.card_chw_followup, parent, false);


        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        ChwFollowUpMother mother = fMother.get(position);


        viewHolder.nameTextView.setText(mother.getName());
        viewHolder.ageTextView.setText(mother.getAge());
        viewHolder.riskTextView.setText(mother.getRisk());
        viewHolder.villageTextView.setText(mother.getVillage());
        viewHolder.uniqueIDTextView.setText(mother.getUniqueID());
        viewHolder.facilityTextView.setText(mother.getFacility());
        viewHolder.uniqueIDTextView.setText(mother.getUniqueID());
        viewHolder.anc1TextView.setText(mother.getAnc1());
        viewHolder.anc2TextView.setText(mother.getAnc2());
        viewHolder.anc3TextView.setText(mother.getAnc3());
        viewHolder.anc4TextView.setText(mother.getAnc4());

    }

    @Override
    public int getItemCount() {
        return fMother.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView,riskTextView, uniqueIDTextView, villageTextView, ageTextView, numberTextView, facilityTextView, anc1TextView,anc2TextView,anc3TextView,anc4TextView;

        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.name);
            riskTextView = (TextView) itemView.findViewById(R.id.risk);
            uniqueIDTextView = (TextView) itemView.findViewById(R.id.unique);
            villageTextView = (TextView) itemView.findViewById(R.id.location);
            ageTextView = (TextView) itemView.findViewById(R.id.age);
            numberTextView = (TextView) itemView.findViewById(R.id.communication);
            facilityTextView = (TextView) itemView.findViewById(R.id.facility);
            anc1TextView = (TextView) itemView.findViewById(R.id.date_one);
            anc2TextView = (TextView) itemView.findViewById(R.id.date_two);
            anc3TextView = (TextView) itemView.findViewById(R.id.date_three);
            anc4TextView = (TextView) itemView.findViewById(R.id.date_four);
        }


    }
}