$(document).ready(function () {

    const header = document.getElementById('nav-header');
    const sticky = header.offsetTop;

    window.onscroll = function()
    {myFunction(header, sticky)};

    const key = document.location.pathname.substring(document.location.pathname.lastIndexOf('/'));
    console.log(key);
    document.getElementById(key=== '/' ? '/home' : key).classList.add('nav-active');

});



function myFunction(header, sticky) {
    if (window.pageYOffset > sticky){
        header.classList.add('sticky');
        $('nav-logo').fadeOut();
    } else {
        header.classList.remove('sticky');
        $('nav-logo').fadeIn();
    }
}