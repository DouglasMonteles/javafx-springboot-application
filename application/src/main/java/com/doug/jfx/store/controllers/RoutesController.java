package com.doug.jfx.store.controllers;

import com.doug.jfx.store.controllers.components.FormUserRegisterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoutesController {

    public static LoginController loginController;
    public static AdminController adminController;
    public static UserController userController;
    public static CategoryController categoryController;

    @Autowired
    public RoutesController(final LoginController loginController,
                            final AdminController adminController,
                            final UserController userController,
                            final CategoryController categoryController) {
        RoutesController.loginController = loginController;
        RoutesController.adminController = adminController;
        RoutesController.userController = userController;
        RoutesController.categoryController = categoryController;
    }

}
