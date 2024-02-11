package com.doug.jfx.store.controllers;

import com.doug.jfx.store.builders.PaginatedTableBuilder;
import com.doug.jfx.store.builders.impl.PaginatedTableBuilderImpl;
import com.doug.jfx.store.controllers.components.SideOptionsComponent;
import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.MoneyPayment;
import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.models.Product;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.OrderDTO;
import com.doug.jfx.store.models.dtos.ProductDTO;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.services.*;
import com.doug.jfx.store.utils.DateUtils;
import com.doug.jfx.store.utils.PriceUtils;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

@Component
public class AdminController implements Initializable {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderedItemService orderedItemService;

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

        try {
            MFXPaginatedTableView<?> salesTableComponent = salesTable
                    .setCurrentPage(0)
                    .setPagesToShow(5)
                    .setRowsPerPage(15)
                    .addColumn("Id", false, 0, Comparator.comparing(ProductDTO::getId), ProductDTO::getId)
                    .addColumn("Nome", true, 0, Comparator.comparing(ProductDTO::getName), ProductDTO::getName)
                    .addColumn("Descrição", true, 300, Comparator.comparing(ProductDTO::getDescription), ProductDTO::getDescription)
                    .addColumn("Preço", false, 0, Comparator.comparing(ProductDTO::getPrice), productDTO -> PriceUtils.pricePtBr(productDTO.getPrice()))
                    .addFilter(new LongFilter<>("Id", ProductDTO::getId))
                    .addFilter(new StringFilter<>("Nome", ProductDTO::getName))
                    .addFilter(new DoubleFilter<>("Preço", productDTO -> productDTO.getPrice().doubleValue()))
                    .setData(productService.findAll())
                    .build();

            salesTableComponent.getTableColumns().forEach(it -> {
                it.prefWidthProperty().addListener((observable, oldValue, newValue) -> {
                    it.setPrefWidth(it.getWidth());
                });
            });

            VBox optionsComponent = new VBox();
            optionsComponent.setPadding(new Insets(4));
            optionsComponent.setMinWidth(260);
            optionsComponent.setPadding(new Insets(5));
            optionsComponent.setSpacing(10);

            VBox selectedItemsComponent = new VBox();
            selectedItemsComponent.setSpacing(10);
            selectedItemsComponent.setPadding(new Insets(5));

            Label totalLabel = new Label();
            totalLabel.setFont(new Font(15));
            totalLabel.setAlignment(Pos.CENTER_RIGHT);
            totalLabel.setText("TOTAL: R$ 0,00");

            MFXButton addItemButton = new MFXButton("Adicionar item");
            addItemButton.setMinWidth(260);
            addItemButton.setStyle("-fx-background-color:green");
            addItemButton.setTextFill(Color.WHITE);
            addItemButton.setOnAction(addItemEvent -> {
                ProductDTO selectedProduct = (ProductDTO) salesTableComponent.getSelectionModel().getSelectedValues().getFirst();
                int productIndex = orderedItemService.findIndex(selectedProduct.getId());

                MFXCheckbox item = new MFXCheckbox();

                if (productIndex >= 0) {
                    // Atualizar item
                    orderedItemService.increaseQuantity(selectedProduct.getId());
                    item.setText(selectedProduct.getName() + " - " + PriceUtils.pricePtBr(selectedProduct.getPrice()) + " - Qtd. " + orderedItemService.getOrderedItem(selectedProduct.getId()).getQuantity());
                    selectedItemsComponent.getChildren().remove(orderedItemService.findIndex(selectedProduct.getId()));
                    selectedItemsComponent.getChildren().add(item);

                    totalLabel.setText("TOTAL: " + PriceUtils.pricePtBr(orderedItemService.getTotal()));
                } else {
                    // Adicionar item
                    OrderedItem orderedItem = new OrderedItem();
                    orderedItem.setOrder(null);
                    orderedItem.setProduct(new Product(selectedProduct));
                    orderedItem.increaseQuantity();
                    orderedItem.setDiscount(new BigDecimal(0));
                    orderedItem.setPrice(selectedProduct.getPrice());

                    item.setText(selectedProduct.getName() + " - " + PriceUtils.pricePtBr(selectedProduct.getPrice()) + " - Qtd. " + orderedItem.getQuantity());
                    selectedItemsComponent.getChildren().add(item);

                    orderedItemService.addCartItem(orderedItem);
                    totalLabel.setText("TOTAL: " + PriceUtils.pricePtBr(orderedItemService.getTotal()));
                }
            });

            HBox quantityContainer = new HBox();

            MFXButton increaseQuantityButton = new MFXButton("+");
            increaseQuantityButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 12pt");
            increaseQuantityButton.setPrefWidth(50);
            increaseQuantityButton.setOnAction(event1 -> {
                List<OrderedItem> cartItems = orderedItemService.getCartItems();

                for (int i = 0; i < cartItems.size(); i++) {
                    MFXCheckbox itemCheckbox = (MFXCheckbox) selectedItemsComponent.getChildren().get(i);

                    if (itemCheckbox.isSelected()) {
                        orderedItemService.increaseQuantity(cartItems.get(i).getProduct().getId());
                    }

                    ProductDTO selectedProduct = new ProductDTO(orderedItemService.getOrderedItem(i).getProduct());

                    itemCheckbox.setText(selectedProduct.getName() + " - " + PriceUtils.pricePtBr(selectedProduct.getPrice()) + " - Qtd. " + orderedItemService.getOrderedItem(selectedProduct.getId()).getQuantity());
                    selectedItemsComponent.getChildren().remove(orderedItemService.findIndex(selectedProduct.getId()));
                    selectedItemsComponent.getChildren().add(itemCheckbox);
                }
            });

            MFXButton decreaseQuantityButton = new MFXButton("-");
            decreaseQuantityButton.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 12pt");
            decreaseQuantityButton.setPrefWidth(50);

            quantityContainer.getChildren().addAll(increaseQuantityButton, decreaseQuantityButton);
            quantityContainer.setAlignment(Pos.BASELINE_CENTER);
            quantityContainer.setSpacing(100);

            MFXButton removeItemButton = new MFXButton("Remover item");
            removeItemButton.setMinWidth(260);
            removeItemButton.setStyle("-fx-background-color:red");
            removeItemButton.setTextFill(Color.WHITE);
            removeItemButton.setDisable(selectedItemsComponent.getChildren().isEmpty());
            removeItemButton.setOnAction(removeItemEvent -> {
                List<MFXCheckbox> items = new ArrayList<>(selectedItemsComponent.getChildren().stream().map(it -> (MFXCheckbox) it).toList());

                List<MFXCheckbox> itemsToRemove = new ArrayList<>();
                List<Long> cartItemsProductsToRemove = new ArrayList<>();

                for (int i = 0; i < items.size(); i++) {
                    MFXCheckbox item = items.get(i);

                    if (item.isSelected()) {
                        itemsToRemove.add(item);
                        cartItemsProductsToRemove.add(orderedItemService.getOrderedItem(i).getProduct().getId());
                    }
                }

                selectedItemsComponent.getChildren().removeAll(itemsToRemove);
                orderedItemService.removeCartItem(cartItemsProductsToRemove);

                totalLabel.setText("TOTAL: " + PriceUtils.pricePtBr(orderedItemService.getTotal()));
            });

            MFXButton finalizeItemButton = new MFXButton("Finalizar venda");
            finalizeItemButton.setMinWidth(260);
            finalizeItemButton.setStyle("-fx-background-color:blue");
            finalizeItemButton.setTextFill(Color.WHITE);
            finalizeItemButton.setDisable(selectedItemsComponent.getChildren().isEmpty());
            finalizeItemButton.setOnAction(finalizeEvent -> {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setClient(userService.getLoggedUser());
                orderDTO.setOrderedItems(orderedItemService.getCartItems());
                orderDTO.setPayment(new MoneyPayment(new BigDecimal(0)));
                orderDTO.setDate(DateUtils.getDateTime());

                OrderDTO orderedItem = orderService.insert(orderDTO);
                Dialog.infoDialog("Pedido confirmado", "O pedido foi realizado com sucesso", "Pedido " + orderedItem.getId() + " realizado às " + DateUtils.timePtBr(orderedItem.getDate()) + " do dia " + DateUtils.datePtBr(orderedItem.getDate()));

                selectedItemsComponent.getChildren().clear();
                salesTableComponent.getSelectionModel().clearSelection();
                salesTableComponent.getSelectionModel().selectIndex(0);
            });

            selectedItemsComponent.getChildren().addListener((ListChangeListener<Node>) c -> {
                removeItemButton.setDisable(selectedItemsComponent.getChildren().isEmpty());
                finalizeItemButton.setDisable(selectedItemsComponent.getChildren().isEmpty());
            });

            optionsComponent.getChildren().add(addItemButton);
            optionsComponent.getChildren().add(new MFXScrollPane(selectedItemsComponent));
            optionsComponent.getChildren().add(quantityContainer);
            optionsComponent.getChildren().add(removeItemButton);
            optionsComponent.getChildren().add(finalizeItemButton);

            VBox mainContainer = new VBox();
            mainContainer.setSpacing(10);

            salesTableComponent.setPrefWidth(Double.MAX_VALUE);

            HBox mainFooter = new HBox(totalLabel);
            mainFooter.setAlignment(Pos.CENTER_RIGHT);

            mainContainer.getChildren().add(salesTableComponent);
            mainContainer.getChildren().add(mainFooter);

            buildAdminScreen(mainContainer, optionsComponent);
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

    private void buildAdminScreen(Pane centerComponent, Pane rightComponent) {
        borderPane.setCenter(centerComponent);
        borderPane.setRight(rightComponent);
    }

}
