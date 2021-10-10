package Controller;

import android.content.Context;
import android.util.Log;

import com.sheikh.allnews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class timeAgo {
    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static String getTimeAgo(String date1, Context ctx){
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(date1);
        } catch (ParseException e) {
            Log.d("myTIME", "getTimeAgo: "+e.getMessage().toString());
        }
        if(date == null) {
            return null;
        }

        long time = date.getTime();

        Date curDate = currentDate();
        long now = curDate.getTime();
        if (time > now || time <= 0) {
            return null;
        }

        int dim = getTimeDistanceInMinutes(time);

        String timeAgo = null;

        if (dim == 0) {
            timeAgo =  ctx.getResources().getString(R.string.date_util_unit_minute) + " " +  ctx.getResources().getString(R.string.date_util_term_a) + " " + ctx.getResources().getString(R.string.date_util_term_less);
        } else if (dim == 1) {
            return "1 " + ctx.getResources().getString(R.string.date_util_unit_minute);
        } else if (dim >= 2 && dim <= 59) {
            timeAgo = dim + " " + ctx.getResources().getString(R.string.date_util_unit_minutes);
        } else if (dim >= 60 && dim <= 119) {
            timeAgo =   ctx.getResources().getString(R.string.date_util_prefix_about) + " " +ctx.getResources().getString(R.string.date_util_unit_hour);
        } else if (dim >= 120 && dim <= 1439) {
            timeAgo = ctx.getResources().getString(R.string.date_util_prefix_about)+ " " + (Math.round(dim / 60)) + " " + ctx.getResources().getString(R.string.date_util_unit_hours) ;
        } else if (dim >= 1440 && dim <= 2519) {
            timeAgo =  ctx.getResources().getString(R.string.date_util_unit_day) + "1 ";
        } else if (dim >= 2520 && dim <= 43199) {
            timeAgo =  ctx.getResources().getString(R.string.date_util_unit_days) + " " + (Math.round(dim / 1440)) ;
        } else if (dim >= 43200 && dim <= 86399) {
            timeAgo =  ctx.getResources().getString(R.string.date_util_unit_month)+ " " +ctx.getResources().getString(R.string.date_util_prefix_about) ;
        } else if (dim >= 86400 && dim <= 525599) {
            timeAgo =  ctx.getResources().getString(R.string.date_util_unit_months) + " " + (Math.round(dim / 43200)) ;
        } else if (dim >= 525600 && dim <= 655199) {
            timeAgo = ctx.getResources().getString(R.string.date_util_unit_year)+ " "+ctx.getResources().getString(R.string.date_util_term_a)+ " " +ctx.getResources().getString(R.string.date_util_prefix_about) ;
        } else if (dim >= 655200 && dim <= 914399) {
            timeAgo = ctx.getResources().getString(R.string.date_util_unit_year)+ " "+ctx.getResources().getString(R.string.date_util_term_a)+ " " +ctx.getResources().getString(R.string.date_util_prefix_over);
        } else if (dim >= 914400 && dim <= 1051199) {
            timeAgo = ctx.getResources().getString(R.string.date_util_prefix_almost) + " 2 " + ctx.getResources().getString(R.string.date_util_unit_years);
        } else {
            timeAgo = ctx.getResources().getString(R.string.date_util_prefix_about) + " " + (Math.round(dim / 525600)) + " " + ctx.getResources().getString(R.string.date_util_unit_years);
        }

        return ctx.getResources().getString(R.string.date_util_suffix) + " " + timeAgo ;
    }

    private static int getTimeDistanceInMinutes(long time) {
        long timeDistance = currentDate().getTime() - time;
        return Math.round((Math.abs(timeDistance) / 1000) / 60);
    }
}
