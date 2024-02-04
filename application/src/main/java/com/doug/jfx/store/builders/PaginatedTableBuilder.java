package com.doug.jfx.store.builders;

import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.filter.base.AbstractFilter;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public interface PaginatedTableBuilder<S> {

    PaginatedTableBuilder<S> addColumn(String label, boolean resizable, double prefWidth, Comparator<S> comparator, Function <S, ?> extractor);

    PaginatedTableBuilder<S> addFilter(AbstractFilter<S, ?> filter);

    PaginatedTableBuilder<S> setData(List<?> tableData);

    PaginatedTableBuilder<S> setCurrentPage(int currentPage);

    PaginatedTableBuilder<S> setPagesToShow(int pagesToShow);

    PaginatedTableBuilder<S> setRowsPerPage(int rowsPerPage);

    MFXPaginatedTableView<S> build();

}
