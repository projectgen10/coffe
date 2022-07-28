package com.example.coffeshop.service;

import com.example.coffeshop.model.entity.Menu;

public interface ServiceMenu {
    Menu getMenuByidMenu(String idMenu);
    void updateMenu(String idMenu, Menu menu);
    void deleteMenu(String idMenu);
}
