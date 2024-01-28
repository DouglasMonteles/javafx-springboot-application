package com.doug.jfx.store.builders.impl;

import com.doug.jfx.store.builders.TableBuilder;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TableBuilderImpl<S> implements TableBuilder {

    private final TableView<S> table;
    private ObservableList<S> tableData;

    public TableBuilderImpl() {
        this.table = new TableView<>();
        this.table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    @Override
    public <T> TableBuilder addColumn(String label, String name) {
        if (isColumnAlreadyCreated(label)) {
            return this;
        }

        var column = new TableColumn<S, T>();
        column.setText(label);
        column.setCellValueFactory(new PropertyValueFactory<>(name));

        table.getColumns().add(column);

        return this;
    }

    public TableBuilder setData(List<?> tableData) {
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
    public TableView<S> build() {
        this.table.setItems(this.tableData);
        return table;
    }

    private boolean isColumnAlreadyCreated(String label) {
        return table.getColumns()
                .stream()
                .anyMatch(column -> column.getText().equals(label));
    }

}
