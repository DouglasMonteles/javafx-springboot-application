package com.doug.jfx.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoutesController {

    public static LoginController loginController;
    public static AdminController adminController;
    public static UserController userController;
    public static CategoryController categoryController;
    public static ProductController productController;
    public static OrderController orderController;

    @Autowired
    public RoutesController(final LoginController loginController,
                            final AdminController adminController,
                            final UserController userController,
                            final CategoryController categoryController,
                            final ProductController productController,
                            final OrderController orderController) {
        RoutesController.loginController = loginController;
        RoutesController.adminController = adminController;
        RoutesController.userController = userController;
        RoutesController.categoryController = categoryController;
        RoutesController.productController = productController;
        RoutesController.orderController = orderController;
    }

}
