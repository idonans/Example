package com.idonans.example.entity.api;

import androidx.arch.core.util.Function;

import java.util.ArrayList;
import java.util.List;

public class GithubPage<T> {

    public int total_count;
    public List<T> items;

    public <E> com.idonans.example.entity.format.GithubPage<E> toFormat(Function<T, E> convert) {
        com.idonans.example.entity.format.GithubPage<E> target = new com.idonans.example.entity.format.GithubPage<>();
        target.total = this.total_count;
        target.items = new ArrayList<>();
        if (this.items != null) {
            for (T item : this.items) {
                if (item == null) {
                    continue;
                }
                E convertItem = convert.apply(item);
                if (convertItem != null) {
                    target.items.add(convertItem);
                }
            }
        }
        return target;
    }

}
