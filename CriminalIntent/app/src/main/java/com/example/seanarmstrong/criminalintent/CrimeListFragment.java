package com.example.seanarmstrong.criminalintent;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sean.armstrong on 26/02/2017.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Crime mCrime;

        private TextView mTitleTextView;
        private TextView mDateTextView;

        // This could be split into a BasicCrimeHolder and a SeriousCrimeHolder which extend from
        // Crime holder. They would know what layout they wanted to inflate rather than the adapter choosing
        // But may be over the top for this exercise
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent, int layout) {
            super(inflater.inflate(layout, parent, false));
            setupCrimeView();
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mCrime.getTitle() + " Clicked!", Toast.LENGTH_SHORT).show();
        }

        private void setupCrimeView() {
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);

            itemView.setOnClickListener(this);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        private final int BASIC_CRIME_VIEW_TYPE = 0;
        private final int SERIOUS_CRIME_VIEW_TYPE = 1;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            if (viewType == SERIOUS_CRIME_VIEW_TYPE) {
                return new CrimeHolder(layoutInflater, parent, R.layout.list_item_serious_crime);
            }

            return new CrimeHolder(layoutInflater, parent, R.layout.list_item_crime);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            holder.bind(getCrimeByPosition(position));
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        @Override
        public int getItemViewType(int position) {
            Crime crime = getCrimeByPosition(position);
            // Change to Contants
            int viewType = crime.isSerious() ? BASIC_CRIME_VIEW_TYPE : SERIOUS_CRIME_VIEW_TYPE;
            return viewType;
        }

        private Crime getCrimeByPosition(int position) {
            return mCrimes.get(position);
        }
    }
}
