var hamDangGoi = 'loadProductIndex';
async function loadProductIndex(page) {
    var size = document.getElementById("maxsize").value;
    var sort = document.getElementById("sort").value;
    var url = 'http://localhost:8080/api/sanpham/public/find-all-page?page=' + page + '&size=' + size+'&sort='+sort;
    const response = await fetch(url, {
        method: 'GET'
    });
    var result = await response.json();
    console.log(result)
    var list = result.content;
    var totalPage = result.totalPages;

    var main = '';
    for (i = 0; i < list.length; i++) {
        var gia = list[i].gia
        var giaCu = null;
        if(list[i].sanPhamChiTiets.length > 0){
            gia = list[i].sanPhamChiTiets[0].gia
            if(list[i].sanPhamChiTiets[0].giaCu != null && list[i].sanPhamChiTiets[0].giaCu > 0){
                giaCu = list[i].sanPhamChiTiets[0].giaCu
            }
        }
        main +=
        `<div class="col-md-3 mb-4">
            <div class="card h-100 shadow-sm">
                <div class="ratio ratio-4x3">
                    <img src="${list[i].anh}" class="card-img-top object-fit-cover" alt="Túi xách 1">
                </div>
                <div class="card-body d-flex flex-column justify-content-between">
                    <h5 class="card-title">${list[i].tenSanPham}</h5>
                    <div>
                        <div style="position: relative">
                            <span class="card-text fw-bold text-danger mb-2">${formatmoney(gia)}</span>
                            ${giaCu == null?'':
           `<span class="giagiamcu">${formatmoney(giaCu)}</span>`}
                        </div><br>
                        <a href="product-detail?id=${list[i].id}" class="btn btn-primary w-100">Xem sản phẩm</a>
                    </div>
                </div>
            </div>
        </div>`
    }
    document.getElementById("listproductindex").innerHTML = main

    var mainpage = ''
    for (i = 1; i <= totalPage; i++) {
        // mainpage += `<li onclick="loadProductIndex(${(Number(i) - 1)})" class="page-item"><a class="page-link" href="#listsp">${i}</a></li>`
        mainpage += `<li class="page-item ${Number(i)-1 == page?'active':''}" onclick="loadProductIndex(${(Number(i) - 1)})"><a class="page-link">${i}</a></li>`
    }
    document.getElementById("pageable").innerHTML = mainpage
}



async function loadAProduct() {
    var uls = new URL(document.URL)
    var id = uls.searchParams.get("id");
    if (id != null) {
        var url = 'http://localhost:8080/api/sanpham/public/findById?id=' + id;
        const response = await fetch(url, {
            method: 'GET',
            headers: new Headers({
                'Authorization': 'Bearer ' + token
            })
        });
        var result = await response.json();
        document.getElementById("tensp").innerHTML = result.tenSanPham
        document.getElementById("mainImage").src = result.anh
        var main = `<img src="${result.anh}" class="img-thumbnail" style="width: 80px; cursor: pointer;" onclick="changeImage(this.src)">` 
        for(i=0; i<result.sanPhamChiTiets.length; i++){
            main += `<img src="${result.sanPhamChiTiets[i].anh}" class="img-thumbnail" style="width: 80px; cursor: pointer;" onclick="changeImage(this.src)">`
        }
        document.getElementById("listanhphu").innerHTML =main
        document.getElementById("description").innerHTML = result.moTa

        listspct = result.sanPhamChiTiets;

        const colors = Array.from(
            new Map(listspct.map(item => [item.mauSac.id, item.mauSac])).values()
        );
        
        var main = '';
        for (let i = 0; i < colors.length; i++) {
            main += `<div class="form-check">
                        <input class="form-check-input" type="radio" name="choncolor" id="color${colors[i].id}" 
                            value="${colors[i].id}" ${i == 0 ? 'checked' : ''} onchange="filterProductsByColor(this.value)">
                        <label class="form-check-label" for="color${colors[i].id}">${colors[i].ten}</label>
                    </div>`;
        }
        filterProductsByColor(colors[0].id);
        document.getElementById("listmausac").innerHTML = main;
        document.getElementById("giaban").innerHTML = formatmoney(listspct[0].gia)
        if(listspct[0].giaCu != null){
            document.getElementById("giacu").innerHTML = formatmoney(listspct[0].giaCu)
        }
    }
}


var listspct = [];
function filterProductsByColor(colorId) {
    var filteredProducts = listspct.filter(item => item.mauSac.id == colorId);
    console.log(filteredProducts); 
    renderProductList(filteredProducts);
}


function renderProductList(listCt) {
    var content = '';
    for (j=0; j<listCt.length; j++) {
        content += `<div class="form-check">
                        <input value="${listCt[j].id}" ${listCt[j].soLuong <= 0?'disabled':''} onchange="changeGiaBan(${listCt[j].gia}, ${listCt[j].giaCu})" class="form-check-input" type="radio" name="kichthuocchon" id="size${listCt[j].kichThuoc.id}" ${j==0 && listCt[j].soLuong > 0?'checked':''}>
                        <label class="form-check-label" for="size${listCt[j].kichThuoc.id}">${listCt[j].kichThuoc.ten} <span class="slcondetail">(${listCt[j].soLuong})</span></label>
                    </div>`;
    }
    document.getElementById("listikchthuoc").innerHTML = content;
    document.getElementById("giaban").innerHTML = formatmoney(listCt[0].gia)
    if(listCt[0].giaCu != null){
        document.getElementById("giacu").innerHTML = formatmoney(listCt[0].giaCu)
    }
}

function changeGiaBan(gia, giacu){
    document.getElementById("giaban").innerHTML = formatmoney(gia)
    if(giacu != null){
        document.getElementById("giacu").innerHTML = formatmoney(giacu)
    }
}


async function searchFullProduct(page) {
    // $("#exampleModal").modal("show")
    hamDangGoi = 'searchFullProduct'
    var khoanggia = document.getElementById("khoanggia").value.split("-");
    var sort = document.getElementById("sort").value;
    let checkboxes = document.querySelectorAll('input[name="danhmucsearc"]:checked');
    let danhmucs = Array.from(checkboxes).map(cb => cb.value);
    let checkboxesColor = document.querySelectorAll('input[name="mausacsearch"]:checked');
    let mausacs = Array.from(checkboxesColor).map(cb => cb.value);
    var size = document.getElementById("maxsize").value;
    var obj = {
        "smallPrice":khoanggia[0],
        "maxPrice":khoanggia[1],
        "loaiSanPhamIds":danhmucs,
        "mauSacId":mausacs,
    }
    console.log(obj);
    
    var url = 'http://localhost:8080/api/sanpham/public/searchFull?page=' + page + '&size=' + size+'&sort='+sort;
    const response = await fetch(url, {
        method: 'POST',
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
        body: JSON.stringify(obj)
    });
    var result = await response.json();
    console.log(result)
    // toastr.success("Có "+result.numberOfElements+" sản phẩm được tìm thấy")
    var list = result.content;
    var totalPage = result.totalPages;

    var main = '';
    for (i = 0; i < list.length; i++) {
        var gia = list[i].gia
        if(list[i].sanPhamChiTiets.length > 0) gia = list[i].sanPhamChiTiets[0].gia
        main +=
        `<div class="col-md-3 mb-4">
            <div class="card h-100 shadow-sm">
                <div class="ratio ratio-4x3">
                    <img src="${list[i].anh}" class="card-img-top object-fit-cover" alt="Túi xách 1">
                </div>
                <div class="card-body d-flex flex-column justify-content-between">
                    <h5 class="card-title">${list[i].tenSanPham}</h5>
                    <div>
                        <p class="card-text fw-bold text-danger mb-2">${formatmoney(gia)}</p>
                        <a href="product-detail?id=${list[i].id}" class="btn btn-primary w-100">Xem sản phẩm</a>
                    </div>
                </div>
            </div>
        </div>`
    }
    document.getElementById("listproductindex").innerHTML = main

    var mainpage = ''
    for (i = 1; i <= totalPage; i++) {
        mainpage += `<li class="page-item ${Number(i)-1 == page?'active':''}" onclick="loadProductIndex(${(Number(i) - 1)})"><a class="page-link">${i}</a></li>`
    }
    document.getElementById("pageable").innerHTML = mainpage
    // await new Promise(resolve => setTimeout(resolve, 500));
    // $("#exampleModal").modal("hide")
}

function changeSort(val){
    document.getElementById("sort").value = val;
    if(hamDangGoi == 'loadProductIndex'){
        loadProductIndex(0)
    }
    else{
        searchFullProduct(0);  
    }
}

function changeHienThi(){
    if(hamDangGoi == 'loadProductIndex'){
        loadProductIndex(0)
    }
    else{
        searchFullProduct(0);  
    }
}