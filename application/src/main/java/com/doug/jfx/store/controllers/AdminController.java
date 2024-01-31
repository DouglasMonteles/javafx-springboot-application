package com.doug.jfx.store.controllers;

import com.doug.jfx.store.builders.PaginatedTableBuilder;
import com.doug.jfx.store.builders.impl.PaginatedTableBuilderImpl;
import com.doug.jfx.store.controllers.components.SideOptionsComponent;
import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.Product;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.ProductDTO;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.services.CategoryService;
import com.doug.jfx.store.services.ProductService;
import com.doug.jfx.store.services.UserService;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.lang.model.AnnotatedConstruct;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.IntStream;

@Component
public class AdminController implements Initializable {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem home;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem insertUserMenuItem;

    @FXML
    private MenuItem listUsersMenuItem;

    @FXML
    private MenuItem listCategoriesMenuItem;

    @FXML
    private MenuItem insertCategoryMenuItem;

    @FXML
    public MenuItem listProductsMenuItem;

    @FXML
    public MenuItem insertProductMenuItem;

    @FXML
    private MenuItem listSalesMenuItem;

    @FXML
    private MenuItem openCashRegisterMenuItem;

    @Value("${messages.default.error}")
    private String defaultErrorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        redirectToHome(null);
    }

    @FXML
    public void handleUserRegister(ActionEvent event) {
        Routes.redirectTo(Routes.INSERT_USER);
    }

    @FXML
    public void handleCategoryRegister(ActionEvent event) {
        Routes.redirectTo(Routes.INSERT_CATEGORY);
    }

    @FXML
    public void handleProductRegister(ActionEvent event) {
        Routes.redirectTo(Routes.INSERT_PRODUCT);
    }

    @FXML
    public void listUsers(ActionEvent event) {
        var userTableContent = userService.buildUserTable();
        var sideOptionsContent = new SideOptionsComponent();

        userTableContent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            int selectedIndex = userTableContent.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                var selectedUser = (UserDTO) userTableContent.getItems().get(selectedIndex);

                UserController.setSelectedUser(selectedUser);

                sideOptionsContent.setInfoAction(() -> {
                    Routes.redirectTo(Routes.INFO_USER);
                });

                sideOptionsContent.setEditAction(() -> {
                    Routes.redirectTo(Routes.UPDATE_USER);
                });

                sideOptionsContent.setDeleteAction(() -> {
                    Dialog.confirmationDialog("Exclusão de usuário", "Deseja realmente apagar teste usuário?", "Se clicar em confirmar, o usuário será inativado do sistema.")
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(response -> {
                            var userDTO = userService.inactive(selectedUser.getId());

                            if (!userDTO.isActive()) {
                                Dialog.infoDialog("Exclusão de usuário", "Usuário inativado com sucesso!", "Usuário " + userDTO.getName() + " foi inativado");
                                userService.updateTableData();
                            } else {
                                Dialog.errorDialog("Exclusão de usuário", "Não foi possível inativar o usuário", defaultErrorMessage);
                            }
                        });
                });
            }

        });

        buildAdminScreen(userTableContent, sideOptionsContent);
    }

    @FXML
    public void redirectToHome(ActionEvent event) {
        borderPane.setCenter(new Label("Bem vindo!"));
    }

    public void listCategories(ActionEvent actionEvent) {
        var categoryTableComponent = categoryService.buildCategoryTable();
        var sideOptionsComponent = new SideOptionsComponent();

        categoryTableComponent.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            int selectedIndex = categoryTableComponent.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                var selectedCategory = (CategoryDTO) categoryTableComponent.getItems().get(selectedIndex);

                CategoryController.setSelectedCategory(selectedCategory);

                sideOptionsComponent.setInfoAction(() -> {
                    Routes.redirectTo(Routes.INFO_CATEGORY);
                });

                sideOptionsComponent.setEditAction(() -> {
                    Routes.redirectTo(Routes.UPDATE_CATEGORY);
                });

                sideOptionsComponent.setDeleteAction(() -> {
                    var categoryName = selectedCategory.getName().toUpperCase();

                    Dialog.confirmationDialog("Exclusão de categoria", "Deseja realmente apagar essa categoria?", "Se clicar em confirmar, a categoria \"" + categoryName + "\" será excluída do sistema.")
                            .filter(response -> response == ButtonType.OK)
                            .ifPresent(response -> {
                                categoryService.delete(selectedCategory.getId());
                                categoryService.updateTableData();
                                categoryTableComponent.getSelectionModel().selectFirst();
                            });
                });
            }
        });

        buildAdminScreen(categoryTableComponent, sideOptionsComponent);
    }

    public void listProducts(ActionEvent actionEvent) {
        var productTableComponent = productService.buildProductTable();
        var sideOptionsComponent = new SideOptionsComponent();

        productTableComponent.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            int selectedIndex = productTableComponent.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                var selectedProduct = (ProductDTO) productTableComponent.getItems().get(selectedIndex);

                ProductController.setSelectedProduct(selectedProduct);

                sideOptionsComponent.setInfoAction(() -> {
                    Routes.redirectTo(Routes.INFO_PRODUCT);
                });

                sideOptionsComponent.setEditAction(() -> {
                    Routes.redirectTo(Routes.UPDATE_PRODUCT);
                });

                sideOptionsComponent.setDeleteAction(() -> {
                    var productName = selectedProduct.getName().toUpperCase();

                    Dialog.confirmationDialog("Exclusão de Produto", "Deseja realmente apagar esse produto?", "Se clicar em confirmar, o produto \"" + productName + "\" será excluído do sistema.")
                            .filter(response -> response == ButtonType.OK)
                            .ifPresent(response -> {
                                productService.delete(selectedProduct.getId());
                                productService.updateTableData();
                                productTableComponent.getSelectionModel().selectFirst();
                            });
                });
            }
        });

        buildAdminScreen(productTableComponent, sideOptionsComponent);
    }

    @FXML
    public void listSales(ActionEvent event) {

    }

    @FXML
    public void openCashRegister(ActionEvent event) {
        PaginatedTableBuilder<ProductDTO> salesTable = new PaginatedTableBuilderImpl<>();

        List<ProductDTO> x = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ProductDTO p1 = new ProductDTO();
            p1.setId((long)(1+i));
            p1.setName("Teste " + (i+1));
            p1.setPrice(new BigDecimal(10));

            x.add(p1);
        }

        try {
            MFXPaginatedTableView<?> salesTableComponent = salesTable
                    .setCurrentPage(0)
                    .setPagesToShow(5)
                    .setRowsPerPage(15)
                    .addColumn("Id", false, Comparator.comparing(ProductDTO::getId), ProductDTO::getId)
                    .addColumn("Nome", true, Comparator.comparing(ProductDTO::getName), ProductDTO::getName)
                    .addFilter(new LongFilter<>("Id", ProductDTO::getId))
                    .addFilter(new StringFilter<>("Nome", ProductDTO::getName))
                    .setData(x)
                    .build();

            VBox optionsComponent = new VBox();
            optionsComponent.setPadding(new Insets(4));
            optionsComponent.setMinWidth(260);
            optionsComponent.setPadding(new Insets(5));
            optionsComponent.setSpacing(10);

            VBox selectedItemsComponent = new VBox();
            selectedItemsComponent.setSpacing(10);
            selectedItemsComponent.setPadding(new Insets(5));

            MFXButton addItemButton = new MFXButton("Adicionar item");
            addItemButton.setMinWidth(260);
            addItemButton.setStyle("-fx-background-color:green");
            addItemButton.setTextFill(Color.WHITE);
            addItemButton.setOnAction(addItemEvent -> {
                ProductDTO selectedProduct = (ProductDTO) salesTableComponent.getSelectionModel().getSelectedValues().getFirst();
                MFXCheckbox item = new MFXCheckbox();
                DecimalFormat priceFormat = new DecimalFormat("R$ #,##0.00");
                item.setText(selectedProduct.getName() + " - " + priceFormat.format(selectedProduct.getPrice()));

                selectedItemsComponent.getChildren().add(item);
            });

            MFXButton removeItemButton = new MFXButton("Remover item");
            removeItemButton.setMinWidth(260);
            removeItemButton.setStyle("-fx-background-color:red");
            removeItemButton.setTextFill(Color.WHITE);
            removeItemButton.setOnAction(removeItemEvent -> {
                List<MFXCheckbox> items = new ArrayList<>(selectedItemsComponent.getChildren().stream().map(it -> (MFXCheckbox) it).toList());

                List<MFXCheckbox> itemsToRemove = items.stream()
                                .filter(MFXCheckbox::isSelected)
                                .toList();

                selectedItemsComponent.getChildren().removeAll(itemsToRemove);
            });

            optionsComponent.getChildren().add(addItemButton);
            optionsComponent.getChildren().add(removeItemButton);
            optionsComponent.getChildren().add(new MFXScrollPane(selectedItemsComponent));

            buildAdminScreen(salesTableComponent, optionsComponent);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro ao tentar acessar o indice de uma lista, mas este indice não existe. Erro: " + e.getMessage());
        }
    }

    public void handleExitApplication(ActionEvent actionEvent) {
        Dialog.confirmationDialog("Finalizar a aplicação", "Você está prestes a finalizar a execução da aplicação", "Tem certeza que deseja prosseguir?")
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> System.exit(0));
    }

    private void buildAdminScreen(TableView<?> centerComponent, Node rightComponent) {
        centerComponent.setMinWidth(800);
        centerComponent.getSelectionModel().selectFirst();

        HBox.setHgrow(centerComponent, Priority.ALWAYS);
        HBox.setHgrow(rightComponent, Priority.ALWAYS);

        borderPane.setCenter(centerComponent);
        borderPane.setRight(rightComponent);
    }

    private void buildAdminScreen(MFXTableView<?> centerComponent, Node rightComponent) {
        centerComponent.setMinWidth(800);

        if (!centerComponent.getItems().isEmpty()) {
            centerComponent.getSelectionModel().selectIndex(0);
        }

        HBox.setHgrow(centerComponent, Priority.ALWAYS);
        HBox.setHgrow(rightComponent, Priority.ALWAYS);

        AnchorPane.setTopAnchor(centerComponent, 0.0);
        AnchorPane.setBottomAnchor(centerComponent, 0.0);
        AnchorPane.setLeftAnchor(centerComponent, 0.0);
        AnchorPane.setRightAnchor(centerComponent, 0.0);

        borderPane.setCenter(new AnchorPane(centerComponent));
        borderPane.setRight(rightComponent);
    }

}
