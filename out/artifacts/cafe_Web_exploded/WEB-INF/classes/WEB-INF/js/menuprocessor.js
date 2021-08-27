$(document).ready(function () {
    $('.c-navbar-item').css('width', (100 / document.querySelectorAll('.c-navbar-item').length) + '%')

    if (document.location.search.substring(document.location.search.lastIndexOf('?'), document.location.search.lastIndexOf('='))==='?key') {
        sessionStorage.removeItem('key');
        sessionStorage.setItem('key', document.location.search.substring(document.location.search.lastIndexOf('=')+1, document.location.search.length));
    }

    $('.c-navbar-item-'+sessionStorage.getItem('key')).addClass('c-navbar-item-active');

});