package com.web.service;

import com.web.dto.SearchDto;
import com.web.entity.MauSac;
import com.web.entity.SanPham;
import com.web.exception.MessageException;
import com.web.repository.MauSacRepository;
import com.web.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    public SanPham save(SanPham sanPham) {
        if(sanPham.getId() == null){
            sanPham.setCreatedAt(new Date(System.currentTimeMillis()));
        }
        else{
            SanPham ex = sanPhamRepository.findById(sanPham.getId()).get();
            sanPham.setCreatedAt(ex.getCreatedAt());
            sanPham.setUpdatedAt(new Date(System.currentTimeMillis()));
        }
        SanPham result = sanPhamRepository.save(sanPham);
        return result;
    }


    public void delete(Long id) {
        try {
            sanPhamRepository.deleteById(id);
        }
        catch (Exception e){
            throw new MessageException("Đã có đơn hàng cho sản phẩm này, hãy xóa đơn hàng trước");
        }
    }


    public SanPham findById(Long id) {
        return sanPhamRepository.findById(id).get();
    }


    public List<SanPham> findAllList() {
        List<SanPham> list = sanPhamRepository.findAll();
        return list;
    }

    public Page<SanPham> findAllPage(Pageable pageable){
        Page<SanPham> sanPhams = sanPhamRepository.findAll(pageable);
        return sanPhams;
    }

    public Page<SanPham> searchFull(SearchDto dto, Pageable pageable){
        dto.getMauSacId().forEach(p->{
            System.out.println("idmau: "+p);
        });
        dto.getLoaiSanPhamIds().forEach(p->{
            System.out.println("loaisp: "+p);
        });
        Page<SanPham> sanPhams = sanPhamRepository.searchFull(dto.getSmallPrice(), dto.getMaxPrice(), dto.getLoaiSanPhamIds(), dto.getMauSacId(), pageable);
        return sanPhams;
    }

    public List<SanPham> findByParam(String s){
        s = "%"+s+"%";
        return sanPhamRepository.findByParam(s);
    }
}
