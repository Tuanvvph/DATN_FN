package com.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchDto {

    Double smallPrice;

    Double maxPrice;

    List<Long> loaiSanPhamIds = new ArrayList<>();

    List<Long> mauSacId = new ArrayList<>();

}
