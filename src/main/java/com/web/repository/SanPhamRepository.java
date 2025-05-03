package com.web.repository;

import com.web.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query(value = "select distinct s.* from VoucherSanPhamSPCT vc \n" +
            "inner join SanPhamChiTiet spct on spct.id = vc.sanPhamChiTiet_id\n" +
            "inner join SanPham s on s.id = spct.sanPham_id where vc.voucherSanPham_id = ?1", nativeQuery = true)
    List<SanPham> findSanPhamGiamGia(Long idGiamGiaSp);

    @Query("SELECT DISTINCT s FROM SanPham s " +
            "JOIN s.sanPhamChiTiets spct " +
            "WHERE s.gia BETWEEN :smallPrice AND :maxPrice " +
            "AND (s.loaiSanPham.id IN :loaiSanPhamIds) " +
            "AND (spct.mauSac.id IN :mauSacIds)")
    Page<SanPham> searchFull(@Param("smallPrice") Double smallPrice,
                             @Param("maxPrice") Double maxPrice,
                             @Param("loaiSanPhamIds") List<Long> loaiSanPhamIds,
                             @Param("mauSacIds") List<Long> mauSacIds,
                             Pageable pageable);

    @Query("select s from SanPham s where s.tenSanPham like ?1")
    List<SanPham> findByParam(String param);
}
