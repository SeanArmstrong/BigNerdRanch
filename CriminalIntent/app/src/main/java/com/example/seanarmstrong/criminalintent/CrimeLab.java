package com.example.seanarmstrong.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sean.armstrong on 26/02/2017.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    public static CrimeLab getInstance(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }


    public void deleteCrime(Crime crime) {
        mCrimes.remove(crime);
    }

    public void addCrime(Crime crime) {
        mCrimes.add(crime);
    }

    public int getNumberOfCrimes() {
        return mCrimes.size();
    }

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
    }
}
