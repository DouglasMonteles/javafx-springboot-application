package com.doug.jfx.store.controllers;

import com.doug.jfx.store.controllers.components.CustomController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoutesController {

    public static LoginController loginController;
    public static AdminController adminController;
    public static UserController userController;

    @Autowired
    public RoutesController(final LoginController loginController,
                            final AdminController adminController,
                            final UserController userController,
                            final CustomController customController) {
        RoutesController.loginController = loginController;
        RoutesController.adminController = adminController;
        RoutesController.userController = userController;
    }

}
