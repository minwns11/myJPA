package com.softagape.myjpa.catCategory;

import com.softagape.myjpa.ICommonService;

import java.util.List;

public interface ICategoryService<T> extends ICommonService<T> {
    ICategory findByName(String name);
    List<ICategory> findAllByNameContains(String name);
}
