package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping(value = {"/addsanpham"}, method = RequestMethod.GET)
    public String addsanpham() {
        return "admin/addsanpham.html";
    }

    @RequestMapping(value = {"/addsanphamchitiet"}, method = RequestMethod.GET)
    public String addsanphamchitiet() {
        return "admin/addsanphamchitiet.html";
    }

    @RequestMapping(value = {"/addvoucherdonhang"}, method = RequestMethod.GET)
    public String addvoucherdonhang() {
        return "admin/addvoucherdonhang.html";
    }

    @RequestMapping(value = {"/addvouchersanpham"}, method = RequestMethod.GET)
    public String addvouchersanpham() {
        return "admin/addvouchersanpham.html";
    }

    @RequestMapping(value = {"/banhang"}, method = RequestMethod.GET)
    public String banhang() {
        return "admin/banhang.html";
    }

    @RequestMapping(value = {"/giamgiadonhang"}, method = RequestMethod.GET)
    public String giamgiadonhang() {
        return "admin/giamgiadonhang.html";
    }

    @RequestMapping(value = {"/giamgiasanpham"}, method = RequestMethod.GET)
    public String giamgiasanpham() {
        return "admin/giamgiasanpham.html";
    }

    @RequestMapping(value = {"/hienthi"}, method = RequestMethod.GET)
    public String hienthi() {
        return "admin/hienthi.html";
    }

    @RequestMapping(value = {"/hoadon"}, method = RequestMethod.GET)
    public String hoadon() {
        return "admin/hoadon.html";
    }

    @RequestMapping(value = {"/in-don"}, method = RequestMethod.GET)
    public String inDon() {
        return "admin/in-don.html";
    }

    @RequestMapping(value = {"/kichthuoc"}, method = RequestMethod.GET)
    public String kichthuoc() {
        return "admin/kichthuoc.html";
    }

    @RequestMapping(value = {"/loaisanpham"}, method = RequestMethod.GET)
    public String loaisanpham() {
        return "admin/loaisanpham.html";
    }

    @RequestMapping(value = {"/magiamgia"}, method = RequestMethod.GET)
    public String magiamgia() {
        return "admin/magiamgia.html";
    }

    @RequestMapping(value = {"/mausac"}, method = RequestMethod.GET)
    public String mausac() {
        return "admin/mausac.html";
    }

    @RequestMapping(value = {"/sanpham"}, method = RequestMethod.GET)
    public String sanpham() {
        return "admin/sanpham.html";
    }

    @RequestMapping(value = {"/sanphamchitiet"}, method = RequestMethod.GET)
    public String sanphamchitiet() {
        return "admin/sanphamchitiet.html";
    }

    @RequestMapping(value = {"/taikhoan"}, method = RequestMethod.GET)
    public String taikhoan() {
        return "admin/taikhoan.html";
    }

    @RequestMapping(value = {"/thongke"}, method = RequestMethod.GET)
    public String thongke() {
        return "admin/thongke.html";
    }
}
