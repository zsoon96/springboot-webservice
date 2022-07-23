var main = {
    // index.js만의 유효범위를 만들어 사용하기 위한 함수 - init() > 중복되는 기능의 다른 JS와 겹칠 위험 방지
    // == index 객체 안에서만 funtion들이 유효
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        // btn-update라는 id를 가진 html 엘리먼트에 click 이벤트가 발생할 때, update() 실행 되도록 등록
        $('#btn-update').on('click', function () {
            _this.update();
        });
    },

    save : function () {
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("글이 등록되었습니다.");
            window.location.href = "/";

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () { // 신규로 추가될 수정 기능
        var data = {
            title : $('#title').val(),
            content : $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("글이 수정되었습니다.");
            window.location.href = "/";

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();