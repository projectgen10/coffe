package com.example.coffeshop.service;

import com.example.coffeshop.model.entity.Menu;
import com.example.coffeshop.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ServiceMenuImp implements ServiceMenu {

    @Autowired
    MenuRepository menuRepository;

    public Menu store(MultipartFile file, Menu menu) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Menu menu1 = new Menu(fileName, file.getContentType(), file.getBytes());
        menu1.setNamaMenu(menu.getNamaMenu());
        menu1.setHarga(menu.getHarga());
        menu1.setStock(menu.getStock());
        return menuRepository.save(menu1);
    }

    public Menu update(MultipartFile file, Menu menu, String idMenu) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Menu menu1 =  new Menu(fileName, file.getContentType(), file.getBytes());
        Menu menu2 = menuRepository.findByidMenu(idMenu).get();
        System.out.println(menu2.toString());
        menu1.setNamaMenu(menu.getNamaMenu());
        menu1.setHarga(menu.getHarga());
        menu1.setStock(menu.getStock());
        return menuRepository.save(menu1);
    }

    public Menu getFile(String idMenu) {
        return menuRepository.findByidMenu(idMenu).get();
    }

    public Stream<Menu> getAllFiles() {
        return menuRepository.findAll().stream();
    }

    @Override
    public Menu getMenuByidMenu(String idMenu) {
        return menuRepository.findByidMenu(idMenu).get();
    }

    @Override
    public void updateMenu(String idMenu, Menu menu) {
        Menu menu1 = menuRepository.findByidMenu(idMenu).get();
        System.out.println(menu1.toString());
        menu1.setNamaMenu(menu.getNamaMenu());
        menu1.setStock(menu.getStock());
        menu1.setHarga(menu.getHarga());
        menuRepository.save(menu1);
    }

    @Override
    public void deleteMenu(String idMenu) { menuRepository.deleteById(idMenu); }
}
