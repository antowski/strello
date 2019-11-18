
jQuery(document).ready(function(){
    jQuery("select#period").change(function() {

        var period = jQuery("select#period").val();

        jQuery("input#startDate").val(formatDate(getStartOfPeriod(period)));
        jQuery("input#endDate").val(formatDate(getEndOfPeriod(period)));

    });
});

function getStartOfPeriod(period) {

    var dt = luxon.DateTime.local();
    var unit = "day";

    switch (period) {

        case "last-q":  unit = "quarter"; dt = dt.minus({quarters: 1}); break;
        case "last-m":  unit = "month";   dt = dt.minus({months:   1}); break;
        case "last-w":  unit = "week";    dt = dt.minus({weeks:    1}); break;

        case "cur-q":   unit = "quarter"; break;
        case "cur-m":   unit = "month";   break;
        case "cur-w":   unit = "week";    break;

    }

    return dt.startOf(unit);

}

function getEndOfPeriod(period) {

    var dt = luxon.DateTime.local();
    var unit = "day";

    switch (period) {
        case "last-q": unit = "quarter"; dt = dt.minus({quarters: 1}); break;
        case "last-m": unit = "month";   dt = dt.minus({months: 1});   break;
        case "last-w": unit = "week";    dt = dt.minus({weeks: 1});    break;
    }

    return dt.endOf(unit);

}

// format date to string "yyyy-MM-dd"
function formatDate(dt) {
    return dt.toISODate();
}