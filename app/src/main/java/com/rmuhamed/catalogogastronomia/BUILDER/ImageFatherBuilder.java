package com.rmuhamed.catalogogastronomia.BUILDER;

import android.content.ContentValues;
import android.database.Cursor;

import com.rmuhamed.catalogogastronomia.MODEL.IEntity;

/**
 * Created by rmuhamed on sábado.
 */
public class ImageFatherBuilder implements IBuilder {
    @Override
    public IEntity build(Cursor cursor) {
        return null;
    }

    @Override
    public IEntity build(Cursor cursor, IEntity entity) {
        return null;
    }

    @Override
    public ContentValues createValues(IEntity entity) {
        return null;
    }

    @Override
    public String[] getFieldsNames() {
        return new String[0];
    }
}
