
    //! moment.js locale configuration
    //! locale : spanish (es)
    //! author : Julio Napurí : https://github.com/julionc

    var monthsShortDot = 'ene._feb._mar._abr._may._jun._jul._ago._sep._oct._nov._dic.'.split('_'),
        es__monthsShort = 'ene_feb_mar_abr_may_jun_jul_ago_sep_oct_nov_dic'.split('_');

    moment.locale('es', {
        months : 'enero_febrero_marzo_abril_mayo_junio_julio_agosto_septiembre_octubre_noviembre_diciembre'.split('_'),
        monthsShort : function (m, format) {
            if (/-MMM-/.test(format)) {
                return es__monthsShort[m.month()];
            } else {
                return monthsShortDot[m.month()];
            }
        },
        weekdays : 'domingo_lunes_martes_miércoles_jueves_viernes_sábado'.split('_'),
        weekdaysShort : 'dom._lun._mar._mié._jue._vie._sáb.'.split('_'),
        weekdaysMin : 'Do_Lu_Ma_Mi_Ju_Vi_Sá'.split('_'),
        longDateFormat : {
            LT : 'H:mm',
            LTS : 'LT:ss',
            L : 'DD/MM/YYYY',
            LL : 'D [de] MMMM [de] YYYY',
            LLL : 'D [de] MMMM [de] YYYY LT',
            LLLL : 'dddd, D [de] MMMM [de] YYYY LT'
        },
        calendar : {
            sameDay : function () {
                return '[hoy a la' + ((this.hours() !== 1) ? 's' : '') + '] LT';
            },
            nextDay : function () {
                return '[mañana a la' + ((this.hours() !== 1) ? 's' : '') + '] LT';
            },
            nextWeek : function () {
                return 'dddd [a la' + ((this.hours() !== 1) ? 's' : '') + '] LT';
            },
            lastDay : function () {
                return '[ayer a la' + ((this.hours() !== 1) ? 's' : '') + '] LT';
            },
            lastWeek : function () {
                return '[el] dddd [pasado a la' + ((this.hours() !== 1) ? 's' : '') + '] LT';
            },
            sameElse : 'L'
        },
        relativeTime : {
            future : 'en %s',
            past : 'hace %s',
            s : 'unos segundos',
            m : 'un minuto',
            mm : '%d minutos',
            h : 'una hora',
            hh : '%d horas',
            d : 'un día',
            dd : '%d días',
            M : 'un mes',
            MM : '%d meses',
            y : 'un año',
            yy : '%d años'
        },
        ordinalParse : /\d{1,2}º/,
        ordinal : '%dº',
        week : {
            dow : 1, // Monday is the first day of the week.
            doy : 4  // The week that contains Jan 4th is the first week of the year.
        }
    });
   
function loadDatepicker(container,callBack,setDateCallBack){
	$('#'+container).daterangepicker({
        startDate: moment().subtract( 7,'days'),
        endDate: moment(),
        showDropdowns: true,
        showWeekNumbers: true,
        timePicker: true,
        timePickerIncrement: 1,
        timePicker12Hour: false,
        ranges: {
            'Hoy': [moment().startOf('day'), moment().endOf('day'),'Hoy'],
            'Ayer': [moment().subtract(1,'days').startOf('day'), moment().subtract( 1,'days').endOf('day'),'Ayer'],
            'Ultimas 24 horas': [moment().subtract(24, 'hours'), moment(),'Ultimas 24 horas'],
            'Ultimos 7 dias': [moment().subtract( 6,'days'), moment(),'Ultimos 7 dias'],
            'Ultimos 30 dias': [moment().subtract(29,'days'), moment(),'Ultimos 30 dias'],
            'Mes Pasado': [moment().subtract( 1,'month').startOf('month'), moment().subtract(1,'month').endOf('month'),'Mes Pasado'],
            'Ultimos 90 dias': [moment().subtract(89,'days'), moment(),'Ultimos 90 dias']
        },
        opens: 'left',
        buttonClasses: ['btn btn-default'],
        applyClass: 'btn-small btn-primary',
        cancelClass: 'btn-small btn-primary',
        format: 'DD/MM/YYYY',
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
	setDateCallBack(container);
}
function setDatesHtml(container){
	$('#'+container+' span').html( getDateFromLLL()+ ' - ' + getDateNowLLL());	
	setDateRange('#'+container,moment().subtract( 7,'days'), moment());
}
function setDatesHtmlOne(container){
	$('#'+container+' span').html( getDateSubtractLLL(1)+ ' - ' + getDateNowLLL());	
	setDateRange('#'+container,moment().subtract( 1,'days'), moment());
}
function getDateFromLLL(){
	return getDateSubtractLLL(7);
}
function getDateSubtractLLL(days){
	return moment().subtract( days,'days').format('LLL');
}
function getDateSubtract(days){
	return moment().subtract( days,'days').format('L HH:mm');
}
function getDateNowLLL(){
	return moment().format('LLL');
}
function setDateRange(div,start, end){
	$(div).data('daterangepicker').setStartDate(start);
	$(div).data('daterangepicker').setEndDate(end);
}
function loadFormatLLL(div,start, end, rangeSelect){
	setDateRange(div,start, end);
	$(div+' span').html(rangeSelect+' - '+start.format('LLL') + ' - ' + end.format('LLL'));
}
function getDateFromLHH(){
	return getDateSubtract(7);
}
function getDateNowLHH(){
	return moment().format('L HH:mm');
}

//Moment.js Time Display
var datetime = null,
    date = null;

var update = function() {
    date = moment(new Date())
    datetime.html(' <i class="ace-icon fa fa-clock-o bigger-120"></i>  '+date.format('LLLL:ss'));
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



