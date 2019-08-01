package com.idonans.example.uniontype;

public class UnionTypeItemObject<T> {

    public final int unionType;
    public T itemObject;

    public UnionTypeItemObject(int unionType, T itemObject) {
        this.unionType = unionType;
        this.itemObject = itemObject;
    }

}
