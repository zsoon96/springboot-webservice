var main = {
    // index.js만의 유효범위를 만들어 사용하기 위한 함수 - init() > 중복되는 기능의 다른 JS와 겹칠 위험 방지
    // == index 객체 안에서만 funtion들이 유효
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
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
    }
};

main.init();