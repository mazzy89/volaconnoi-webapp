$(function() {
    $( ".from" ).datepicker({
      defaultDate: "+1w",
      dateFormat: "dd/mm/yy",
      changeMonth: true,
      numberOfMonths: 1,
      minDate: new Date(),
      onClose: function( selectedDate ) {
        $( ".to" ).datepicker( "option", "minDate", selectedDate);
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
    
     $( "#depdatepicker" ).datepicker({
      dateFormat: "dd/mm/yy",
      changeMonth: true,
      numberOfMonths: 2,
      minDate: new Date(),
      maxDate: "+1Y"
    },
  $.datepicker.regional[ "it" ]
    );
    
    
    
  });
  
  