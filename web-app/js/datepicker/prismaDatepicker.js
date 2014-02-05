

function loadDatepicker(container,callBack){
	$('#'+container).daterangepicker({
        startDate: moment().subtract('days', 29),
        endDate: moment(),
        showDropdowns: true,
        showWeekNumbers: true,
        timePicker: true,
        timePickerIncrement: 1,
        timePicker12Hour: false,
        ranges: {
            'Hoy': [moment(), moment(),'today'],
            'Ayer': [moment().subtract('days', 1), moment().subtract('days', 1),'yesterdar'],
            'Ultimos 7 dias': [moment().subtract('days', 6), moment(),'7days'],
            'Ultimos 30 dias': [moment().subtract('days', 29), moment(),'30days'],
            'Presente mes': [moment().startOf('month'), moment().endOf('month'),'presentMonth'],
            'Mes Pasado': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month'),'lastMonth']
        },
        opens: 'left',
        buttonClasses: ['btn btn-default'],
        applyClass: 'btn-small btn-primary',
        cancelClass: 'btn-small btn-primary',
        format: 'MM/DD/YYYY',
        separator: ' to ',
        locale: {
            applyLabel: 'OK',
            fromLabel: 'Desde',
            toLabel: 'Hasta',
            customRangeLabel: 'Seleccionar Rango',
            daysOfWeek: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
            monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'],
            firstDay: 1,
            weekLabel: 'S'
        }
    },
    callBack
);
//Set the initial state of the picker label
	$('#'+container+' span').html(moment().subtract('days', 29).format('LLL') + ' - ' + moment().format('LLLL'));
}


//Moment.js Time Display
var datetime = null,
    date = null;

var update = function() {
    date = moment(new Date())
    datetime.html(' <i class="icon-time bigger-120"></i>  '+date.format('LLLL:ss'));
};

$(document).ready(function() {
    datetime = $('#datetime')
    update();
    setInterval(update, 1000);
});

//Custom jQuery - Changes background on time tile based on the time of day.
$(document).ready(function() {
    datetoday = new Date(); // create new Date()
    timenow = datetoday.getTime(); // grabbing the time it is now
    datetoday.setTime(timenow); // setting the time now to datetoday variable
    hournow = datetoday.getHours(); //the hour it is

    if (hournow >= 18) // if it is after 6pm
        $('div.tile-img').addClass('evening');
    else if (hournow >= 12) // if it is after 12pm
        $('div.tile-img').addClass('afternoon');
    else if (hournow >= 6) // if it is after 6am
        $('div.tile-img').addClass('morning');
    else if (hournow >= 0) // if it is after midnight
        $('div.tile-img').addClass('midnight');
});



