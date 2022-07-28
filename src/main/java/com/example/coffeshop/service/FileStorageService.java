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
public class FileStorageService {
    @Autowired
    private MenuRepository menuRepository;

    public Menu store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Menu Menu = new Menu(fileName, file.getContentType(), file.getBytes());
        return menuRepository.save(Menu);
    }

    public Menu getFile(String idMenu) {
        return menuRepository.findByidMenu(idMenu).get();
    }

    public Stream<Menu> getAllFiles() {
        return menuRepository.findAll().stream();
    }
}
