$.fn.popup = function() {
    this.css('position', 'fixed').fadeIn('fast');
    this.css('top', 20 + '%');
    this.css('left', 40  + '%');
};

$.fn.popupFaq = function() {
    this.css('position', 'fixed').fadeIn('fast');
    this.css('top', 15 + '%');
    this.css('left', 78  + '%');
}

$(document).ready(function(){

    $('.popup-window').fadeOut('fast');
    $('.popup-window-small').fadeOut('fast');
    $('.background-shadow').fadeOut('fast');

    if (sessionStorage.getItem('manual') === 'open') {
        if(document.getElementById('manual')!=null){
            document.getElementById('manual').classList.add('active');
            $('.faq-window').popupFaq();
        }
    }

    if(document.getElementById('manual')!=null){
        document.getElementById('manual').addEventListener('click', () => {
            $('.faq-window').popupFaq();
            sessionStorage.setItem('manual', 'open');
        })
    }
    if(document.getElementById('close-faq')!=null){
        document.getElementById('close-faq').addEventListener('click', () => {
            $('.faq-window').fadeOut('fast');
            sessionStorage.removeItem('manual');
            if(document.getElementById('manual')){
                document.getElementById('manual').classList.remove('active');
            }
        })
    }


    document.querySelectorAll('.popup-open').forEach((link)=>{link.addEventListener('click',
                ()=>{$('.p-w-'+link.getAttribute('about')).popup(); $('.background-shadow').fadeIn('fast');})})

    document.querySelectorAll('.close', ).forEach((link)=>{link.addEventListener('click',
                ()=>{ $('.popup-window').fadeOut(); $('.background-shadow').fadeOut(); $('.popup-window-small').fadeOut('fast'); })})

    document.querySelectorAll('.background-shadow').forEach((link)=>{link.addEventListener('click',
                ()=>{ $('.popup-window').fadeOut(); $('.background-shadow').fadeOut(); $('.popup-window-small').fadeOut('fast'); })})

    if (document.location.search.substring(document.location.search.lastIndexOf('?'), document.location.search.lastIndexOf('='))==='?open') {
        $('.p-w-'+document.location.search.substring(document.location.search.lastIndexOf('=')+1, document.location.search.length)).popup();
        $('.background-shadow').fadeIn('fast');
    }

    if (document.location.search.includes('?status=success') || document.location.search.includes('&status=success')) {
            $('.p-w-success').popup();
            $('.background-shadow').fadeIn('fast');
    }

    if (document.location.search.includes('?status=dateError') || document.location.search.includes('&status=dateError')) {
        $('.p-w-dateError').popup();
        $('.background-shadow').fadeIn('fast');
    }

    if (document.location.search.includes('?status=timeError') || document.location.search.includes('&status=timeError')) {
        $('.p-w-timeError').popup();
        $('.background-shadow').fadeIn('fast');
    }

});