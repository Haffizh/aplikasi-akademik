package id.ac.tazkia.akademik.aplikasiakademik.controller;

import id.ac.tazkia.akademik.aplikasiakademik.dao.KurikulumDao;
import id.ac.tazkia.akademik.aplikasiakademik.dao.ProdiDao;
import id.ac.tazkia.akademik.aplikasiakademik.entity.Kurikulum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MataKuliahController {



    @GetMapping("/matakuliah/list")
    public void  listMatakuliah(){
    }

    @GetMapping("/matakuliah/form")
    public void  formMataKuliah(){
    }
}
