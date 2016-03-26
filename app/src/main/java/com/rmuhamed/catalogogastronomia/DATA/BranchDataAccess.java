package com.rmuhamed.catalogogastronomia.DATA;

import android.content.Context;

import com.rmuhamed.catalogogastronomia.BUILDER.IBuilder;
import com.rmuhamed.catalogogastronomia.DATA.SingleDataAccess;
import com.rmuhamed.catalogogastronomia.MODEL.IEntity;

/**
 * Created by rmuhamed on sábado.
 */
public class BranchDataAccess extends SingleDataAccess {
    public static final String TABLE_NAME = "Branch";

    public BranchDataAccess(Context context, IBuilder builder) {
        super(context, builder);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getIdWhereClause(IEntity entity) {
        return null;
    }

    @Override
    protected String getIdWhereClauseForId(Object id) {
        return null;
    }
}
