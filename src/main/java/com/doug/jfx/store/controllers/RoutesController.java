package com.doug.jfx.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoutesController {

    public static LoginController loginController;

    @Autowired
    public RoutesController(final LoginController loginController) {
        RoutesController.loginController = loginController;
    }

}
