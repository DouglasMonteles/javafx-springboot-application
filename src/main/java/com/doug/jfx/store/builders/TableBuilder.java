package com.doug.jfx.store.builders;

import javafx.scene.control.TableView;

import java.util.List;

public interface TableBuilder {

    <T> TableBuilder addColumn(String label, String name);

    TableBuilder setData(List<?> tableData);

    TableView<?> build();

}
