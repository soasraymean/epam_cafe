$(document).ready(function () {
    $('.p-w-s').fadeOut();
    $('.del-div').fadeOut();

    document.querySelectorAll('.delete-link img').forEach( (item) => {
        item.addEventListener('mouseenter', () => {
            $('.d-d-' + item.getAttribute('about')).css('position', 'absolute').fadeIn();
        });
    })
    document.querySelectorAll('.del-div').forEach((item)=>{
        item.addEventListener('mouseleave', () => {
            $(item).fadeOut();
        })
    })
});