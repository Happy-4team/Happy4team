/* ------------------필터 클릭, 호버--------------------- */
const $filter = $('.card-toolbar-item').eq(0);
const $filterDropdown = $('.menu-sub-dropdown');
const $filters = $('.card-toolbar-item');
const $filtersLast = $('.card-toolbar-itemBox').children().last()
let check1 = false;

$body.on('click', function (e) {
    if (check1) {
        if (e.target.closest('.menu-sub-dropdown') == e.currentTarget.querySelector('.menu-sub-dropdown').closest('.menu-sub-dropdown')) {
        } else {
            $filterDropdown.css('display', 'none');
            $filter.css('background-color', '#f6f8fa');
            $filter.css('color', '#009ef7');
            $filter.find('#filter-img').css({
                'background': `url('/imgs/admin/filterBlue.png')`,
                'background-size': '13px'
            });
            check1 = !check1;
        }
    }
})

$filter.on("click", function () {
    if ($filterDropdown.css('display') == 'none') {
        $filter.css('background-color', '#009ef7');
        $filter.css('color', '#fff');
        $filter.find('#filter-img').css({
            'background': `url('/imgs/admin/filterWhite.png')`,
            'background-size': '13px'
        });
        $filterDropdown.css('display', 'flex');
        setTimeout(() => {
            check1 = !check1;
        }, 100);
    }
})

$filters.on('mouseover', function () {
    $(this).css('background-color', '#009ef7');
    $(this).css('color', '#fff');
    $(this).find('#out-img').css({
        'background': `url('/imgs/admin/outWhite.png') no-repeat`,
        'background-size': '13px'
    })
    $(this).find('#plusBlue-img').css({
        'background': `url('/imgs/admin/plusWhite.png') no-repeat`,
        'background-size': '17px'
    })
    $(this).find('#filter-img').css({
        'background': `url('/imgs/admin/filterWhite.png')`,
        'background-size': '13px'
    })
})

$filters.on('mouseout', function () {
    $(this).css('background-color', '#f6f8fa');
    $(this).css('color', '#009ef7');
    $(this).find('#out-img').css({
        'background': `url('/imgs/admin/out.png') no-repeat`,
        'background-size': '13px'
    })
    $(this).find('#plusBlue-img').css({
        'background': `url('/imgs/admin/plusBlue.png') no-repeat`,
        'background-size': '17px'
    })
    $(this).find('#filter-img').css({
        'background': `url('/imgs/admin/filterBlue.png')`,
        'background-size': '13px'
    })
})
/* 삭제 모달*/
$filtersLast.on('click', function () {
    if ($('input:checkbox[name=check]:checked').length == 0) {
        $(".delete-modal1").css('display', 'block');
        body.style.overflow = 'hidden';
    } else {
        if (deleteModal.classList.contains('show')) {
            body.style.overflow = 'hidden';
        }
        deleteModal.classList.toggle('show');
    }
});

/* 확인 모달창 닫기 */
$(".delete-modal-cancel1").on('click', function () {
    $(".delete-modal1").css('display', 'none');
    body.style.overflow = 'unset';
})

/* ---------------------필터 서브-------------- */
const $selectOption = $('.menu-sub-dropdown-option-box');

$selectOption.on('click', function () {
    console.log('방가')
    if ($(this).find('input').is(':checked')) {
        $(this).find('.menu-sub-dropdown-option-sub').css('display', 'flex');
        $(this).find('input').prop('checked', true);
    } else {
        $(this).find('.menu-sub-dropdown-option-sub').css('display', 'none');
        $(this).find('input').prop('checked', false);
    }
})

/* ----------------- 필터 서브 옵션 호버---------------- */
const $subOption = $('.menu-sub-dropdown-option-sub-item');

$subOption.on('mouseover', function () {
    $(this).css('background-color', '#f4f6fa');
    $(this).css('color', '#009ef7');
})
$subOption.on('mouseout', function () {
    $(this).css('background-color', '#fff');
    $(this).css('color', '#5e6278');
})

$subOption.on('click', function () {
    $(this).closest('label').find('.menu-sub-dropdown-option-text').html($(this).html());
})

/* ------------------ 필터 서브 옵션 푸터 호버----------------*/

const $subOptionResetChoose = $('.menu-sub-dropdown-Botton').eq(0);
const $subOptionApllyChoose = $('.menu-sub-dropdown-Botton').eq(1);
$subOptionApllyChoose.css('background-color', '#009ef7');
$subOptionApllyChoose.css('color', '#fff');

$subOptionResetChoose.on('mouseover', function () {
    $(this).css('color', '#009ef7');
})
$subOptionResetChoose.on('mouseout', function () {
    $(this).css('color', '#7e8299');
})

$subOptionApllyChoose.on('mouseover', function () {
    $(this).css('background-color', '#0095e8');
})
$subOptionApllyChoose.on('mouseout', function () {
    $(this).css('background-color', '#009ef7');
})

/* ----------------체크박스-------------------- */
const $checkBox = $('.notice-checked');
const $checkBoxAll = $('.notice-checked-all');

$checkBoxAll.on('click', function () {
    if ($checkBoxAll.is(':checked')) {
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $checkBoxAll.prev().css('display', 'flex');
        $checkBox.closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $checkBox.prev().css('display', 'flex');
        $checkBox.prop('checked', true);
    } else {
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5');
        $checkBoxAll.prev().css('display', 'none');
        $checkBox.closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5');
        $checkBox.prev().css('display', 'none');
        $checkBox.prop('checked', false);
    }
})

$checkBox.on('click', function (e) {

    if ($(this).is(':checked')) {
        $(this).closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $(this).prev().css('display', 'flex')
    } else {
        $(this).closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5')
        $(this).prev().css('display', 'none')
        $checkBoxAll.prop('checked', false);
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5');
        $checkBoxAll.prev().css('display', 'none');
    }
    if ($checkBox.filter(":checked").length == $checkBox.length) {
        $checkBoxAll.prop('checked', true);
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $checkBoxAll.prev().css('display', 'flex');
    }
})

/* -----------삭제 모달창-------------- */
const $delete = $('.donate-outBox');
const $finalDelete = $('.delete-modal-delete');
const $finalDeleteCancel = $('.delete-modal-cancel');
const deleteModal = $('.delete-modal')[0];

$finalDelete.on('click', function () {
    deleteModal.classList.toggle('show');
    body.style.overflow = 'auto';
})

$finalDeleteCancel.on('click', function () {
    deleteModal.classList.toggle('show');
    body.style.overflow = 'auto';
})

$delete.on('click', function () {
    deleteModal.classList.toggle('show');

    if (deleteModal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }
})

$delete.on('mouseover', function () {
    $(this).css('color', '#009ef7');
    $(this).css('background-color', '#f1faff');
})
$delete.on('mouseout', function () {
    $(this).css('color', '#7e8299');
    $(this).css('background-color', '#f5f8fa');
})

/* -------------- 페이지 이동 호버 ---------------- */
const $pageNumberLink = $('.page-number-link');

$pageNumberLink.on('mouseover', function () {
    $(this).css('background-color', '#f4f6fa');
    $(this).css('color', '#009ef7');
})

$pageNumberLink.on('mouseout', function () {
    $(this).css('background-color', '#fff');
    $(this).css('color', '#5e6278');
})

/* ---------------메인 툴바 타입 호버----------------- */
const $userType = $('.toolbar-choose-usertype-flex');

$userType.on('mouseover', function () {
    $(this).css('color', '#009ef7');
})

$userType.on('mouseout', function () {
    $(this).css('color', '#5e6278');
})



/*===========Ajax===============================*/
/*==================보육원 선택==================*/
$schools =  $(".toolbar-choose-usertype-flex");

$schools.on("click",function(){

    $.ajax({
        url: "/adminRest/schools",
        type: "post",
        success: function (schools){
            let text = "";
            schools.forEach(function(school){
                text += `<tr>`
                text +=  `<th class="card-body-title-checkbox-padding" style="width: 3%; margin-top: 29px;padding-top: 0; padding-bottom: 31px;">`
                text += `<label class="card-body-title-user-checkbox">`
                text += `<div class="check-img"></div>`
                text += `<input class="notice-checked" type="checkbox" name="check">`
                text += `</label>`
                text += `</th>`
                text += `<th class="card-body-title-padding" style="width: 22%;">`
                text += `<div class="donater-info" style="height: 50px">`
                text += `<div class="donater-info-img1"></div>`
                text += `<div class="donater-info-text">`
                text += `<div class="donater-name">`+school.userName+`</div>`
                text += `<div>`+ school.userEmail +`</div>`
                text += `</div>`
                text += `</div>`
                text += `</th>`
                text += `<th class="card-body-title-padding" style="width: 18%;">`
                text += `<div class="donate-info-height">`+ school.userPhoneNumber+`</div>`
                text += `</th>`
                text += `<th class="card-body-title-padding" style="width: 17%;">`
                text += `<div class="donate-info-height">`+'일반 또는 보육원'+`</div>`
                text += `</th>`
                text += `<th class="card-body-title-padding" style="width: 18%;">`
                text += `<div class="donate-info-height">5분전</div>`
                text += `</th>`
                text += `<th class="card-body-title-padding" style="width: 22%;">`

                let date = new Date(school.createdDate);
                let year = date.getFullYear().toString().substr(2)+'년 ';
                let month = date.getMonth() + 1 +'월 ';
                let day = date.getDate()+'일';
                let createdDateView = year+month+day;

                text += `<div class="donate-info-height">`+ createdDateView +`</div>`
                text += `</th>`
                text += `</tr>`
            })
            $(".card-body-main-box").html(text)
        }
    })
})