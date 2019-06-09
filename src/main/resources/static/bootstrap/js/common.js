function ajaxPost(url, data, callback) {
    try{
        $.ajax({
            type : "POST"
            , async : true
            , timeout : 30000
            , dataType: "text"
            , cache : false
            , contentType: 'application/json'
            , url : url
            , data : data
            , error : function(jqXHR,textStatus, errorThrown) { // jqXHR : xml http request object, textStatus : Jquery status code, errorThrown : exception object

/*
                alert("code:"+jqXHR.status+"\n"+"message:"+textStatus+"\n"+"error:"+errorThrown);
*/
               callback(jqXHR.responseJSON, jqXHR);
            }
            , success : function(data, textStatus, jqXHR){ // data : server response data, textStatus : Jquery status code, jqXHR : xml http request object
                callback(data, jqXHR);

            }
        });
    }catch (e) {
        alert("requestAjax :: " + e.message);

    }
}