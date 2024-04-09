
window.onload = function(){
    var currentUrl = window.location.href;
    var namePage = currentUrl.substring(currentUrl.lastIndexOf("/") + 1);
    var url = "http://localhost:8080/Web_designer_API/ViewerCount?id=" + namePage;
   
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.send();
};

