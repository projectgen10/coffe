package com.example.coffeshop.controller;

import com.example.coffeshop.model.dto.*;
import com.example.coffeshop.model.entity.Jenis;
import com.example.coffeshop.model.entity.Menu;
import com.example.coffeshop.repository.JenisRepository;
import com.example.coffeshop.repository.MenuRepository;
import com.example.coffeshop.service.ServiceMenuImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuRepository menurepository;

    @Autowired
    ServiceMenuImp serviceMenu;

    @Autowired
    JenisRepository jenisRepository;

    @PostMapping("/pilihan")
    public DefaultResponse pilihan(@RequestBody MenuDto menuDto) {

        DefaultResponse df = new DefaultResponse();
        Optional<Menu> OpsMenu = menurepository.findByidMenu(menuDto.getIdMenu());
        if (OpsMenu.isPresent()) {
            df.setStatus(Boolean.TRUE);
            df.setMessage("MENU YANG ANDA PESAN TELAH TERSIMPAN");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("MENU YANG ANDA PESAN TIDAK TERSEDIA");
        }
        return df;
    }

    @GetMapping({"/byid/{idMenu}"})
    public DefaultResponse getByIdMenu(@PathVariable String idMenu) {
        DefaultResponse df = new DefaultResponse();
        Optional<Menu> menuOps = menurepository.findById(idMenu);
        if (menuOps.isPresent()) {
            df.setStatus(Boolean.TRUE);
            df.setMessage("MENU YANG ANDA PESAN TELAH TERSIMPAN");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("MENU YANG ANDA PESAN TIDAK TERSEDIA");
        }
        return df;
    }

    @PostMapping("/savejenis")
    public DefaultResponse<JenisDto> saveJenis(@RequestBody JenisDto jenisDto) {
        Jenis jenis = convertDtoToEntity(jenisDto);
        DefaultResponse<JenisDto> response = new DefaultResponse<>();
        Optional<Jenis> optional = jenisRepository.findById(jenisDto.getIdJenis());
        if (optional.isPresent()) {
            response.setMessage("ERROR, DATA MENU TELAH TERSEDIA");
        } else {
            jenisRepository.save(jenis);
            response.setMessage("DATA MENU BERHASIL TERSIMPAN");
            response.setData(jenisDto);
        }
        return response;
    }

    @GetMapping("/listmenu")
    public List<MenuDto> getListMenu() {
        List<MenuDto> list = new ArrayList<>();
        for (Menu m : menurepository.findAll()) {
            list.add(convertEntityToDto(m));
        }
        return list;
    }

    @PutMapping({"/upload/{idMenu}"})
    public ResponseEntity<Menu> updateMenu(@PathVariable("idMenu") String idMenu, @RequestBody Menu menu) {
        serviceMenu.updateMenu(idMenu, menu);
        return new ResponseEntity<>(serviceMenu.getMenuByidMenu(idMenu), HttpStatus.OK);
    }

    @DeleteMapping({"/{idMenu}"})
    public ResponseEntity<Menu> deleteMenu(@PathVariable("idMenu") String idMenu) {
        serviceMenu.deleteMenu(idMenu);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, Menu menu) {
        String message = "";
        String data = "";
        try {
            serviceMenu.store(file, menu);
            data = "BERHASIL";
            message = "FILE BERHASI DIUPLOAD" + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, data));
        } catch (Exception e) {
            data = "GAGAL";
            message = "FILE TIDAK BERHASIL DIUPLOAD" + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, data));
        }
    }

//    @PutMapping("/upload/{id_menu}")
//    public ResponseEntity<Menu> updateFile(@PathVariable("id_menu") String idMenu, Menu menu) {
//        serviceMenu.updateMenu(idMenu, menu);
//        return new ResponseEntity<>(serviceMenu.getMenuByidMenu(idMenu), HttpStatus.OK);
    //   }

//    @PutMapping("/upload/{idMenu}")
//    public ResponseEntity<ResponseMessage> updateFile( @PathVariable("idMenu") @RequestParam("file") MultipartFile file, Menu menu, String idMenu) {
//        String message = "";
//        String data = "";
//        try {
//            serviceMenu.update(file,menu, idMenu);
//            data = "Berhasil";
//            message = "FILE BERHASI DIUBAH" + file.getOriginalFilename();
//            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, data));
//        } catch (Exception e) {
//            data = "Tidak Berhasil";
//            message = "FILE TIDAK BERHASIL DIUBAH" + file.getOriginalFilename() + "!";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, data));
//        }
//    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFile() {
        List<ResponseFile> files = serviceMenu.getAllFiles().map(dbMenu -> {
            String fileDownloadUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/files/").path(dbMenu.getIdMenu()).toUriString();
            return new ResponseFile(dbMenu.getNamaFile(), fileDownloadUrl, dbMenu.getType(), dbMenu.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping({"/files/{idMenu}"})
    public ResponseEntity<byte[]> getFile(@PathVariable String idMenu) {
        Menu menu = serviceMenu.getFile(idMenu);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                + menu.getNamaMenu() + "\"").body(menu.getData());
    }

    public Menu convertDtoToEntity(MenuDto dto) {
        Menu entity = new Menu();
        entity.setIdMenu(dto.getIdMenu());
        entity.setNamaMenu(dto.getNamaMenu());
        entity.setStock(dto.getStock());
        entity.setHarga(dto.getHarga());
        return entity;
    }

    public Jenis convertDtoToEntity(JenisDto dto) {
        Jenis entity = new Jenis();
        entity.setIdJenis(dto.getIdJenis());
        entity.setNamaJenis(dto.getNamaJenis());
        return entity;
    }

    public MenuDto convertEntityToDto(Menu entity) {
        MenuDto dto = new MenuDto();
        dto.setIdMenu(entity.getIdMenu());
        dto.setNamaJenis(entity.getJenis().getNamaJenis());
        dto.setType(entity.getType());
        dto.setName(entity.getNamaFile());
        dto.setNamaMenu(entity.getNamaMenu());
        dto.setStock(entity.getStock());
        dto.setHarga(entity.getHarga());
        return dto;
    }
    public JenisDto convertEntitytoDto(Jenis entity) {
        JenisDto dto = new JenisDto();
        dto.setIdJenis(entity.getIdJenis());
        dto.setNamaJenis(entity.getNamaJenis());
        return dto;
    }
}