package com.doug.jfx.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoutesController {

    public static LoginController loginController;
    public static AdminController adminController;

    @Autowired
    public RoutesController(final LoginController loginController,
                            final AdminController adminController) {
        RoutesController.loginController = loginController;
        RoutesController.adminController = adminController;
    }

}
