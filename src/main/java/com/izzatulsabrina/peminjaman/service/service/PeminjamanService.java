/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.izzatulsabrina.peminjaman.service.service;

import com.izzatulsabrina.peminjaman.service.VO.Anggota;
import com.izzatulsabrina.peminjaman.service.VO.ResponseTemplateVO;
import com.izzatulsabrina.peminjaman.service.entity.Peminjaman;
import com.izzatulsabrina.peminjaman.service.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@Service
public class PeminjamanService {
    @Autowired
    private PeminjamanRepository peminjamanRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    private Long peminjamanId;
    
    public Peminjaman savePeminjaman(Peminjaman peminjaman) {
        return peminjamanRepository.save(peminjaman);
    }
    
    public ResponseTemplateVO getPeminjaman(Long PeminjamanId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Peminjaman peminjaman = peminjamanRepository.findByPeminjamanId(peminjamanId);
        
        Anggota anggota = restTemplate.getForObject("http://localhost:9001/anggota/"+peminjaman.getAnggotaId(), Anggota.class);
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        return vo;
    }

   

    
}
