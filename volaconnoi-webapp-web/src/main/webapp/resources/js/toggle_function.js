/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('#reservation_toggle').click(function(){
  $('#reservation').slideDown();
});

$('#flights_toggle').click(function(){
  $('#flights').slideDown();
});

$('#data_user_toggle').click(function(){
  $('#data_user').slideDown();
});

$('tr.tableRow').hover(
    function () {
        $(this).addClass('selectedRow');
    },
    function () {
        $(this).removeClass('selectedRow');
    }
);
