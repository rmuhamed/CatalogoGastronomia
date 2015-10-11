package com.rmuhamed.catalogogastronomia.UTILS;

import com.rmuhamed.catalogogastronomia.MODEL.Branch;

import java.util.Comparator;

/**
 * Created by rmuhamed on 11/10/15.
 */
public class ElementoCatalagoComparator implements Comparator<Branch>{

    @Override
    public int compare(Branch branchA, Branch branchB) {
        int comparison = 0;
        if(branchA!=null && branchB!=null){
            String nombreBranchA = branchA.getName();
            String nombreBranchB = branchB.getName();

            comparison = nombreBranchA.compareTo(nombreBranchB);
        }
        return comparison;
    }
}
