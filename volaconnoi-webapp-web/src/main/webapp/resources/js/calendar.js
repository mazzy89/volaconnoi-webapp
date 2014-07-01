$(function() {
    $( ".from" ).datepicker({
      defaultDate: "+1w",
      dateFormat: "dd/mm/yy",
      changeMonth: true,
      numberOfMonths: 1,
      onClose: function( selectedDate ) {
        $( ".to" ).datepicker( "option", "minDate", selectedDate );
      }
  },
  $.datepicker.regional[ "it" ]
    );

    $( ".to" ).datepicker({
      defaultDate: "+1w",
      dateFormat: "dd/mm/yy",
      changeMonth: true,
      numberOfMonths: 1,
      onClose: function( selectedDate ) {
        $( ".from" ).datepicker( "option", "maxDate", selectedDate );
      }
    },
      $.datepicker.regional[ "it" ]

    );
  });
  
  