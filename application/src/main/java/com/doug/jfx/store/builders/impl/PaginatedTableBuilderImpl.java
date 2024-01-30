package com.doug.jfx.store.builders.impl;

import com.doug.jfx.store.builders.PaginatedTableBuilder;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.base.AbstractFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class PaginatedTableBuilderImpl<S> implements PaginatedTableBuilder<S> {

    private final MFXPaginatedTableView<S> table;
    private ObservableList<S> tableData;

    public PaginatedTableBuilderImpl() {
        this.table = new MFXPaginatedTableView<>();
    }

    @Override
    public PaginatedTableBuilder<S> addColumn(String label, boolean resizable, Comparator<S> comparator, Function <S, ?> extractor) {
        if (isColumnAlreadyCreated(label)) {
            return this;
        }

        MFXTableColumn<S> column = new MFXTableColumn<>(label, resizable, comparator);
        column.setRowCellFactory(data -> new MFXTableRowCell<>(extractor));

        table.getTableColumns().add(column);

        return this;
    }

    @Override
    public PaginatedTableBuilder<S> addFilter(AbstractFilter<S, ?> filter) {
        this.table.getFilters().add(filter);
        return this;
    }

    public PaginatedTableBuilder<S> setData(List<?> tableData) {
        List<S> convertedTableData = tableData.stream().map(item -> (S) item).toList();

        if (this.tableData == null) {
            this.tableData = FXCollections.observableArrayList(convertedTableData);
        } else {
            this.tableData.clear();
            this.tableData.addAll(convertedTableData);
        }

        return this;
    }

    @Override
    public PaginatedTableBuilder<S> setCurrentPage(int currentPage) {
        this.table.setCurrentPage(currentPage);
        return this;
    }

    @Override
    public PaginatedTableBuilder<S> setPagesToShow(int pagesToShow) {
        this.table.setPagesToShow(pagesToShow);
        return this;
    }

    @Override
    public PaginatedTableBuilder<S> setRowsPerPage(int rowsPerPage) {
        this.table.setRowsPerPage(rowsPerPage);
        return this;
    }

    @Override
    public MFXPaginatedTableView<S> build() {
        this.table.setItems(this.tableData);
        return table;
    }

    private boolean isColumnAlreadyCreated(String label) {
        return table.getTableColumns()
                .stream()
                .anyMatch(column -> column.getText().equals(label));
    }

}
