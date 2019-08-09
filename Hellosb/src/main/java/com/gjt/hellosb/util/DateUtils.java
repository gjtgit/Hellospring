package com.gjt.hellosb.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
    public static final String formatPattern = "yyyy-MM-dd";

    public static final String formatPattern_Short = "yyyyMMdd";

    public static final String formatPattern_full = "yyyy-MM-dd HH:mm:ss";
    
    public static final String formatPattern_full_for_UI_display = "yyyy/MM/dd HH:mm:ss";
    
    public static final String formatPattern_UI_yyMMddHHmm = "yyyy/MM/dd HH:mm";
    
    public static final String formatPattern_UI_yy_MM_ddHHmm = "yyyy-MM-dd HH:mm";
    
    public static final String formatPattern_SHORT_MM= "yyyyMMddHHmm";

    public static final String formatPattern_full_ss= "yyyyMMddHHmmss";
    
    static final int COUNTRY_ID_FRANCE = 706;
    
    static final int COUNTRY_ID_ITALY = 758;
     
    static final int COUNTRY_ID_UKI = 866;
    
    static final int COUNTRY_ID_GERMANY = 724;
    
    static final int COUNTRY_ID_SWEDEN = 846;
    
    static final int COUNTRY_ID_SWITZERLAND = 848;
    
    static final TimeZone tz = TimeZone.getTimeZone("GMT");
    
    static final TimeZone tzUTC = TimeZone.getTimeZone("UTC");
    /**
     * @return
     * @throws ParseException 
     */
    
    public static void main(String[] args) throws ParseException{
//    	String a = formatStringToLocalDateString("201608032300+0200");
//    	System.out.println(a);
        String[] timezoneArray = TimeZone.getAvailableIDs();
        for(String timezone : timezoneArray){
            System.out.println(timezone);
        }
    }
    public static String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_full);
        return format.format(new Date());
    }
    public static Date getCurrentDateUTC() {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_full);
        format.setTimeZone(tzUTC);
        return new Date();
    }
    
    public static String getISO8601DateString(Date date) {
    	SimpleDateFormat dateiso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    	dateiso8601.setTimeZone(tz);
    	return dateiso8601.format(date);
    }
    
    public static String getCurrentDateByCountry(int countryId, String datePattern) {
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        String zoneID = "";
        switch (countryId) {
            case COUNTRY_ID_FRANCE:
                zoneID = "Europe/Paris";
                break;
            case COUNTRY_ID_ITALY:
                zoneID = "Europe/Rome";
                break;
            case COUNTRY_ID_GERMANY:
                zoneID = "Europe/Berlin";
                break;
            case COUNTRY_ID_SWEDEN:
                zoneID = "Europe/Stockholm";
                break;
            case COUNTRY_ID_SWITZERLAND:
                zoneID = "Europe/Zurich";
                break;
            case COUNTRY_ID_UKI:
                zoneID = "Europe/London";
                break;
        }
        format.setTimeZone(TimeZone.getTimeZone(zoneID));
        return format.format(new Date());
    }
    
    public static String getGMTCurrentDateWithFullSS() {
    	Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_full_ss);
        format.setTimeZone(tz);
        return format.format(c.getTime());
    }
    
    public static String getGMTCurrentDateEarly30sWithFullSS() {
    	Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_full_ss);
        format.setTimeZone(tz);
        return format.format(new Date(c.getTimeInMillis()-30000));
    }
    
    public static String getGMTCurrentDateShort() {
    	Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_Short);
        format.setTimeZone(tz);
        return format.format(c.getTime());
    }
    
    public static String getGMTCurrentDateformatPattern_full() {
    	Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_full);
        format.setTimeZone(tz);
        return format.format(c.getTime());
    }
    
    public static Date getGMTCurrentDateformatPattern_fullInDate() {
    	return DateUtils.sdateByTimeZone(new Date(), "GMT");
    }
    /**
     * @param timeDiff
     * @return
     */
    public static String getDesignatedDate(long timeDiff) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        long nowTime = System.currentTimeMillis();
        long designTime = nowTime - timeDiff;
        return format.format(designTime);
    }

    /**
     * 
     * 
     */
    public static String getPrefixDate(String count) {
        Calendar cal = Calendar.getInstance();
        int day = 0 - Integer.parseInt(count);
        cal.add(Calendar.DATE, day);
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.format(datNew);
    }

    /**
     * Date transfer to String
     * @param date
     * @return String format: yyyy-MM-dd
     */
    public static String dateToStringDefault(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_Short);
        return format.format(date);
    }
    
    /**
     * Date transfer to String
     * @param date
     * @return String format: yyyyMMddHHmm
     *
     */
    public static String dateToStringFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_SHORT_MM);
        return format.format(date);
    }
    
    /**
     * Date transfer to DB String
     * @param date
     * @return String format: yyyy-MM-dd HH:mm:ss
     *
     */
    public static String dateToStringFormatDB(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_full);
        return format.format(date);
    }

    /**
     * @param str
     * @return
     */
    public static Date stringToDate(String str) {
        // str = " 2008-07-10 19:20:00 " 
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_full);
        if (!str.equals("") && str != null) {
            try {
                return format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * @param str
     * @return
     */
    public static Date stringToDate3(String str) {
        // str = " 2008-07-10 19:20" 
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_UI_yy_MM_ddHHmm);
        if (!str.equals("") && str != null) {
            try {
                return format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static Date stringToDate2(String str) {
        // str = " 2008-07-10" 
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        if (!str.equals("") && str != null) {
            try {
                return format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void timeSubtract() {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = null;
        Date end = null;
        try {
            begin = dfs.parse("2004-01-02 11:30:24");
            end = dfs.parse("2004-03-26 13:31:40");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long between = (end.getTime() - begin.getTime()) / 1000;

        long day1 = between / (24 * 3600);
        long hour1 = between % (24 * 3600) / 3600;
        long minute1 = between % 3600 / 60;
        long second1 = between % 60;

    }
    
    public static Date convertTimeFormat(String originTime) {
        if(StringUtils.isEmpty(originTime)){
            return null;
        }
        Date date = null;
        try{
            DateFormat df = new SimpleDateFormat(formatPattern_SHORT_MM);
            date = df.parse(originTime.trim());
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return date;
    }
    
    public static Date dateCalculation(Date originDate, String alterTime) {
        Date resultDate = null;
        if (StringUtils.isNotEmpty(alterTime) && originDate != null) {
            try {
                String[] time = alterTime.split(":");
                int hours = Integer.parseInt(time[0]);
                int minutes = Integer.parseInt(time[1]);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(originDate);
                calendar.add(Calendar.HOUR_OF_DAY, hours);
                calendar.add(Calendar.MINUTE, minutes);
                resultDate = calendar.getTime();

                DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
                String dateStr = df.format(resultDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            resultDate = originDate;
        }

        return resultDate;
    }
    
    public static String sumTime(String originTime, String increaseTime) {
        String result = "";
        if(originTime.indexOf(":") != -1 && increaseTime.indexOf(":") != -1){
            int hours1 = Integer.parseInt(originTime.split(":")[0]);
            int minutes1 = Integer.parseInt(originTime.split(":")[1]);
            int hours2 = Integer.parseInt(increaseTime.split(":")[0]);
            int minutes2 = Integer.parseInt(increaseTime.split(":")[1]);
            
            String hourstr = "";
            String minstr = "";
            int sumHours = hours1 + hours2;
            int sumMinutes = minutes1 + minutes2;
            // calculate minutes
            if(sumMinutes < 10){
                minstr = "0" + sumMinutes;
            }else{
                if(sumMinutes >= 60){
                    sumHours += 1;
                    sumMinutes -= 60;
                    if(sumMinutes < 10){
                        minstr = "0" + sumMinutes;
                    }else{
                        minstr = String.valueOf(sumMinutes);
                    }
                }else{
                    minstr = String.valueOf(sumMinutes);
                }
            }
            // calculate hours
            if(sumHours < 10){
                hourstr = "0"+sumHours;
            }else{
                hourstr = String.valueOf(sumHours);
            }
            
            result = hourstr + ":" + minstr;
        }else{
        	logger.info("sumTime() Wrong format while adding orginTIme : "+originTime+" and increaseTime : "+increaseTime);
        }
        
        return result;
    }
    
    /**
     * @param origin 1.08 , increaseTime 01:00
     * @return 2.08
     */
    public static double sumTimeWithDifferentFormat(String originTime, String increaseTime) {
        double result = 0;
        int timeInMinutes = time2Minutes (increaseTime);
        double timeInMinutes2 = Double.parseDouble(originTime)*60;
        result = (timeInMinutes + timeInMinutes2)/60;
        return result;
    }
    
    /**
     * @param HH:MM
     * @return int minutes
     */
    public static int time2Minutes(String originTime){
        int result = 0;
        if(originTime != null && originTime.indexOf(":") != -1){
            String hoursStr = originTime.split(":")[0];
            if(hoursStr.length()<=0)
            	hoursStr="0";
            String minutesStr = originTime.split(":")[1];
            result = Integer.parseInt(hoursStr)*60 + Integer.parseInt(minutesStr);
        }
        
        return result;
    }
    
    /**
     * @param Minutes
     * @return String HH:MM
     * example : 60 minutes to 01:00
     */
    public static String minutes2Time(int originTime){
        String result = "" ;
        int hours = originTime / 60; 
        int minutes = originTime % 60;
        result = String.format("%d:%02d", hours, minutes);
        return result;
    }
    
    public static boolean isWeekend(Date date) {
        boolean resultFlag = false;
        if(date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            if(weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY){
                resultFlag = true;
            }
        }
        
        return resultFlag;
    }
    
    public static boolean isSameDay(Date date1, Date date2) {
        boolean result = false;
        if (date1 != null && date2 != null) {
            SimpleDateFormat df = new SimpleDateFormat(formatPattern);
            String dateFormat1 = df.format(date1);
            String dateFormat2 = df.format(date2);
            if(dateFormat1 != null){
                result = dateFormat1.equals(dateFormat2);
            }
        }
        return result;
    }
    
    public static boolean isNextDay(Date destDate, Date referDate) {
        boolean result = false;
        if (destDate != null && referDate != null) {
            Calendar destCalendar = Calendar.getInstance();
            destCalendar.setTime(destDate);
            int destYear = destCalendar.get(Calendar.YEAR);
            int destDay = destCalendar.get(Calendar.DAY_OF_YEAR);
            
            Calendar referCalendar = Calendar.getInstance();
            referCalendar.setTime(referDate);
            int referYear = referCalendar.get(Calendar.YEAR);
            int referDay = referCalendar.get(Calendar.DAY_OF_YEAR);
            
            if (destYear > referYear) {
                result = true;
            } else {
                if(destYear == referYear && destDay > referDay) {
                    result = true;
                }
            }
        }
        return result;
    }
    
    public static int getHourOfDay(Date date) {
        int hour = 0;
        if(date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
        }
        return hour;
    }
    
    public static int getTime(Date date) {
        int result = 0;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            String hourStr = "00";
            if(hour < 10) {
                hourStr = "0" + hour;
            } else {
                hourStr = String.valueOf(hour);
            }
            
            int minute = calendar.get(Calendar.MINUTE);
            String minuteStr = "00";
            if(minute < 10) {
                minuteStr = "0" + minute;
            } else {
                minuteStr = String.valueOf(minute);
            }
            
            result = Integer.parseInt(hourStr + minuteStr);
        }
        
        return result;
    }
    
    public static int timeCompare(String originTime, String targetTime){
        if(originTime.indexOf(":") > 0 && targetTime.indexOf(":") > 0 ){
            String[] originTimeArray = originTime.split(":");
            String[] targetTimeArray = targetTime.split(":");
            if(Integer.parseInt(originTimeArray[0]) > Integer.parseInt(targetTimeArray[0])) {
                return 1;
            } else if(Integer.parseInt(originTimeArray[0]) < Integer.parseInt(targetTimeArray[0])) {
                return -1;
            } else {
                if(Integer.parseInt(originTimeArray[1]) < Integer.parseInt(targetTimeArray[1])) {
                    return 1;
                } else if(Integer.parseInt(originTimeArray[1]) < Integer.parseInt(targetTimeArray[1])) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
        
        return 99;
    }
    
    public static String formatStringToDateString(String dateString){
//    	String a = "201605181000";
    	return dateString.substring(0, 4)+"-"+dateString.substring(4, 6)+"-"+dateString.substring(6, 8) + " " 
    				+dateString.substring(8, 10)+":"+dateString.substring(10, 12);
    }
    
    public static String formatStringToLocalDateString(String dateString) throws ParseException{
    	String dateTemp = dateString.substring(0, 12);
    	String offset = dateString.substring(12);
    	SimpleDateFormat format = new SimpleDateFormat(formatPattern_SHORT_MM);
    	format.setTimeZone(TimeZone.getTimeZone("GMT"));
    	Date d = format.parse(dateTemp);
    	SimpleDateFormat local = new SimpleDateFormat(formatPattern_SHORT_MM);
    	local.setTimeZone(TimeZone.getTimeZone("GMT"+offset));
    	return local.format(d);
    }
    
    public static Date parseDate(String datetime, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(tz);
        if (!StringUtils.isEmpty(datetime)) {
            try {
                return format.parse(datetime);
            } catch (ParseException e) {
                if(logger.isErrorEnabled()) {
                	logger.error(e.getMessage());
                }
            }
        }
        return null;
    }
    
    public static String formatDateToString(Date d, String pattern) {
    	if(d != null) {
    		SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setTimeZone(tz);
            return format.format(d.getTime());
    	} 
        return null;
    }
    
    /*
     * Return the next business day
     */
    public static Date getNextBusinessDay()
    {	
		Calendar cal  = Calendar.getInstance();
	    int addDays = 1;
	    if(Calendar.FRIDAY == cal.get(Calendar.DAY_OF_WEEK))
	    {
	    	addDays = 3;
	    }
	    else if(Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK))
	    {
	    	addDays = 2;
	    }
	    else if(Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK))
	    {
	    	addDays = 1;
	    }
	    	
	    cal.add(Calendar.DATE, addDays);
	    return cal.getTime();
    }
        
    public static boolean isTodayWeekDay( Date date)
    {
       Calendar cal = Calendar.getInstance();
       cal.setTime(date);
      
       if(cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7)             
              return false;
       else return true ;
   
    }
    
    public static String formatDecimalTime(String strTime)
    {
    	if(strTime.indexOf(":") != -1)
    	{
    		return strTime;
    	}
    	
    	String formattedTime = "0:0";
		int hours;
		int minutes;		
		String temp;
		DecimalFormat df = new DecimalFormat("00");
		if(strTime == null || strTime.length()==0)
		{
			return formattedTime;
		}
		
		if(StringUtils.endsWith(strTime, "."))
			strTime = StringUtils.substring(strTime, 0, strTime.length()-1);
		
		if(strTime.indexOf(".")==-1)
		{
			formattedTime = df.format(Integer.parseInt(strTime)) + ":" + "00";	
		}
		else
		{
			if(strTime.length() == 2) {
				strTime = strTime + "0";
			}
			temp = strTime.split("\\.")[1];
			if(temp.length()==1)
			{
				temp = temp+"0";
			}
			if(temp.length() >3)
			{
				temp = StringUtils.left(temp, 2);
			}
			if (strTime.indexOf(".") == 0) {
				minutes = Math.round((Integer.parseInt(temp) * 60) / 100);
				formattedTime = "00" + ":" + df.format(minutes);
			} else if (strTime.indexOf(".") > 0) {
				hours = Integer.parseInt(strTime.split("\\.")[0]);
				minutes = Math.round(( Integer.parseInt(temp) * 60) / 100);			
				formattedTime = df.format(hours) + ":" + df.format(minutes);
			}
		}
		return formattedTime;
	}
    
    /**
     * Converts the given String to a Date using the given format of the string
     * 
     * @return Date - converted date
     * @throws IllegalStateException - if the input string is null/empty or the format of the string is invalid
     */
    public static Date stringToDate(String str, String format) {
    	if(str == null || str.trim().length() < 1) {
    		logger.error("Null or Empty date string[" + str + "] given to convert using format[" + format + "]");
    		throw new IllegalStateException("Can not convert a null/empty string to a Date");
    	}
    	
    	//parse and return, capturing and dealing with parse exceptions per the contract of this method
    	try {
    		return new SimpleDateFormat(format).parse(str);
    	} catch (ParseException pe) {
    		logger.error("ParseException attempting to convert date string[" + str + "] to a date using format[" + format + "]: " + pe.getMessage(), pe);
    		throw new IllegalStateException("Unexpected date string format[" + str + "] when expected: " + format);
    	}
    }
    
    
    /**
     * @param dateStr
     * @param timeZone
     * @return
     */
    //BR 01-24-2018 Defect 1638774
    public static String convertDateByTimezone(String dateStr, String timeZone) {
    	
		Date dt = new Date();
		
		logger.info("Optimizer timezone Getting server date: " + dt);
		
		dt = org.apache.commons.lang3.time.DateUtils.setDays(dt, 1);
		dt = org.apache.commons.lang3.time.DateUtils.setMonths(dt, 0);
			
		dt = org.apache.commons.lang3.time.DateUtils.setDays(dt, Integer.parseInt(StringUtils.substring(dateStr, 6, 8)));
		dt = org.apache.commons.lang3.time.DateUtils.setMonths(dt, Integer.parseInt(StringUtils.substring(dateStr, 4, 6)) - 1);
		dt = org.apache.commons.lang3.time.DateUtils.setYears(dt, Integer.parseInt(StringUtils.substring(dateStr, 0, 4)));
		
		logger.info("Optimizer timezone Getting updated date: " + dt);
				
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//Here you say to java the initial timezone. This is the secret
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));    	
    	return sdf.format(calendar.getTime());
    }
 
    
    /**
     * @param date
     * @param timeZone
     * @return
     */
    public static Date sdateByTimeZone(Date sDate, String timeZone) {
        Date date;
		try {
			String dateInString = sDate.toString();
			
			String DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";
	    	
			SimpleDateFormat defaultFormatter = new SimpleDateFormat(DATE_FORMAT);
			date = defaultFormatter.parse(dateInString);
			TimeZone defaultTZ = TimeZone.getDefault();

	        // Default Timezone
//	        System.out.println("TimeZone (Default) : " + defaultTZ.getID() + " - " + defaultTZ.getDisplayName());
//	        System.out.println("TimeZone (Default) : " + defaultTZ);
//	        System.out.println("Date (Default) : " + defaultFormatter.format(date));

	        // Converting from default timezone
	        SimpleDateFormat convertFormatter = new SimpleDateFormat(DATE_FORMAT);
	        TimeZone convertTZ = TimeZone.getTimeZone(timeZone);
	        convertFormatter.setTimeZone(convertTZ);

	        Calendar calendar = new GregorianCalendar();
	        calendar.setTime(date);
	        calendar.setTimeZone(convertTZ);

//	        System.out.println("TimeZone ("+timeZone+") : " + convertTZ.getID() + " - " + convertTZ.getDisplayName());
//	        System.out.println("TimeZone ("+timeZone+") : " + convertTZ);
//	        System.out.println("Date ("+timeZone+") : " + convertFormatter.format(calendar.getTime()));

	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
	        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	        int hour = calendar.get(Calendar.HOUR); // 12 hour clock
	        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
	        int minute = calendar.get(Calendar.MINUTE);
	        int second = calendar.get(Calendar.SECOND);
	        int ampm = calendar.get(Calendar.AM_PM); //0 = AM , 1 = PM
//	        System.out.println(year + " " + month + " " + dayOfMonth + " " + hourOfDay + " " + minute + " " + second);
	        //GregorianCalendar(year + 1900, month, date, hrs, min, sec)
//	        System.out.println("GregorianCalendar");
//	        System.out.println(new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second).getTime());
	        return new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
    }

    public static Date convertDateByTimezone(Date sDate, TimeZone timeZone) {
        Date date;
		try {
			String dateInString = sDate.toString();
			
			String DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";
	    	
			SimpleDateFormat defaultFormatter = new SimpleDateFormat(DATE_FORMAT);
			date = defaultFormatter.parse(dateInString);
			TimeZone defaultTZ = TimeZone.getDefault();

	        // Default Timezone
//	        System.out.println("TimeZone (Default) : " + defaultTZ.getID() + " - " + defaultTZ.getDisplayName());
//	        System.out.println("TimeZone (Default) : " + defaultTZ);
//	        System.out.println("Date (Default) : " + defaultFormatter.format(date));

	        // Converting from default timezone
	        SimpleDateFormat convertFormatter = new SimpleDateFormat(DATE_FORMAT);
	        TimeZone convertTZ = timeZone; //TimeZone.getTimeZone(timeZone);
	        convertFormatter.setTimeZone(convertTZ);

	        Calendar calendar = new GregorianCalendar();
	        calendar.setTime(date);
	        calendar.setTimeZone(convertTZ);

//	        System.out.println("TimeZone ("+timeZone+") : " + convertTZ.getID() + " - " + convertTZ.getDisplayName());
//	        System.out.println("TimeZone ("+timeZone+") : " + convertTZ);
//	        System.out.println("Date ("+timeZone+") : " + convertFormatter.format(calendar.getTime()));

	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
	        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	        int hour = calendar.get(Calendar.HOUR); // 12 hour clock
	        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
	        int minute = calendar.get(Calendar.MINUTE);
	        int second = calendar.get(Calendar.SECOND);
	        int ampm = calendar.get(Calendar.AM_PM); //0 = AM , 1 = PM
//	        System.out.println(year + " " + month + " " + dayOfMonth + " " + hourOfDay + " " + minute + " " + second);
	        //GregorianCalendar(year + 1900, month, date, hrs, min, sec)
//	        System.out.println("GregorianCalendar");
//	        System.out.println(new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second).getTime());
	        return new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
    }
    
    /**
     * @param timeZone
     * @return
     */
    public static Date sdateByTimeZone(String timeZone) {
        return sdateByTimeZone(new Date(), timeZone);
    }
    
    /**
     * @param date
     * @return
     */
    public static Date sdateByTimeZone(Date date) {
        return sdateByTimeZone(date, null);
    }	

    /**
     * @return
     */
    public static Date dateByTimeZone() {
    	/*Calendar tCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    	TimeZone tz = TimeZone.getTimeZone(SystemConstans.GSS_DEFAULT_TIME_ZONE);
    	tCalendar.setTimeZone(tz);
    	int dts = tz.getDSTSavings();
    	logger.info("DSTSavings >>>>>>>>"+dts);
    	System.out.println("DSTSavings >>>>>>"+dts);
    	//System.out.println(tCalendar.getTimeZone().getDisplayName());
    	tCalendar.add(Calendar.MILLISECOND, tz.getRawOffset() + dts);
        // Adjust to Daylight Savings
    	//alendar.add(Calendar.MILLISECOND, - tz.getDSTSavings());
    	
        return tCalendar.getTime();*/
    	
    	return dateByTimeZone(TimeZone.getDefault());
    }
    
    public static Date dateByTimeZone(String timeZone) {
    	return dateByTimeZone(TimeZone.getTimeZone(timeZone));
    }
    
    public static Date dateByTimeZone(TimeZone tz) {
    	Date date;
		try {
			String dateInString = new Date().toString();
			
			String DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";
	    	
			SimpleDateFormat defaultFormatter = new SimpleDateFormat(DATE_FORMAT);
			date = defaultFormatter.parse(dateInString);
			TimeZone defaultTZ = TimeZone.getDefault();

	        // Default Timezone
//	        System.out.println("TimeZone (Default) : " + defaultTZ.getID() + " - " + defaultTZ.getDisplayName());
//	        System.out.println("TimeZone (Default) : " + defaultTZ);
//	        System.out.println("Date (Default) : " + defaultFormatter.format(date));

	        // Converting from default timezone
	        SimpleDateFormat convertFormatter = new SimpleDateFormat(DATE_FORMAT);
	        TimeZone convertTZ = tz;//TimeZone.getTimeZone(timeZone);
	        convertFormatter.setTimeZone(convertTZ);

	        Calendar calendar = new GregorianCalendar();
	        calendar.setTime(date);
	        calendar.setTimeZone(convertTZ);

//	        System.out.println("TimeZone ("+timeZone+") : " + convertTZ.getID() + " - " + convertTZ.getDisplayName());
//	        System.out.println("TimeZone ("+timeZone+") : " + convertTZ);
//	        System.out.println("Date ("+timeZone+") : " + convertFormatter.format(calendar.getTime()));

	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
	        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	        int hour = calendar.get(Calendar.HOUR); // 12 hour clock
	        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
	        int minute = calendar.get(Calendar.MINUTE);
	        int second = calendar.get(Calendar.SECOND);
	        int ampm = calendar.get(Calendar.AM_PM); //0 = AM , 1 = PM

//	        System.out.println(year + " " + month + " " + dayOfMonth + " " + hourOfDay + " " + minute + " " + second);
	        //GregorianCalendar(year + 1900, month, date, hrs, min, sec)
//	        System.out.println("GregorianCalendar");
//	        System.out.println(new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second).getTime());
	        return new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
    }
    
    /**
     * dateInString="2345"
     * @return 23:45
     */
    public static String getTimeFromDate(String dateInString){
        String timeInFormat=dateInString.substring(0,2)+":"+dateInString.substring(2,4);
        return timeInFormat;
    }
    
    /**
     * return current time
     */

    public static String getCurrentTime()
    {
    	Date date = new Date();
    	DateFormat format = new SimpleDateFormat("HH:mm");
    	String time = format.format(date);
    	return time;
    }
	
    
	/**
	 * @param currentDate
	 * @param days
	 * @return
	 */
	public static Date AddDaystoDate(Date currentDate, int days) {
		return org.apache.commons.lang3.time.DateUtils.addDays(currentDate, days);
	}
	
	/**
	 * @param currentDate
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return
	 */
	public static Date AddHourMinSec(Date currentDate, int hours, int minutes, int seconds ) {
		currentDate =  org.apache.commons.lang3.time.DateUtils.setHours(currentDate, hours);
		currentDate =  org.apache.commons.lang3.time.DateUtils.setMinutes(currentDate, minutes);
		currentDate =  org.apache.commons.lang3.time.DateUtils.setSeconds(currentDate, seconds);
		return currentDate;
	}
	
	/**
	 * @param currentDate
	 * @param hours
	 * @return
	 */
	public static Date AddHourstoDate(Date currentDate, int hours) {
		return org.apache.commons.lang3.time.DateUtils.addHours(currentDate, hours);
	}	
    
	/**
	 * @param currentDate
	 * @param minutes
	 * @return
	 */
	public static Date AddMinutestoDate(Date currentDate, int minutes) {
		return org.apache.commons.lang3.time.DateUtils.addMinutes(currentDate, minutes);
	}	
	
	/**
	 * @param currentDate = "201812280059"
	 * @param minutes = "01"
	 * @return String return = "201812280159
	 */
	public static String AddMinutestoDate2(String currentDate, int minutes) {
		return DateUtils.dateToStringFormat(org.apache.commons.lang3.time.DateUtils
				.addMinutes(DateUtils.stringToDate(currentDate, "yyyyMMddHHmm"), minutes));
	}
	
    /**
     * @param cDate
     * @param minDate
     * @param maxDate
     * @return
     */
    public static boolean isWithinRange(Date cDate, Date minDate, Date maxDate) {
 	   return !(cDate.before(minDate) || cDate.after(maxDate));
 	}        
 
 /**
 * @param cDate
 * @param minDate
 * @return
 */
public static boolean isBefore (Date cDate, Date minDate) {
     return cDate.before(minDate);
 }

 /**
 * @param cDate
 * @param maxDate
 * @return
 */
public static boolean isAfter (Date cDate, Date maxDate) {
     return cDate.after(maxDate);
 }
    
    
    
    /**
     * dateInString="201703162306"
     * @return 23:06
     */
    public static String getTimeFromDate2(String dateInString){
        String timeInFormat=dateInString.substring(8,10)+":"+dateInString.substring(10,12);
        return timeInFormat;
    }
	
    /**
     * dateInString="20170202"
     * @return 2017-02-02
     */
	public static String getDateFromString(String dateInString) {
		if (!dateInString.equals("") && dateInString != null) {
			String date = dateInString.substring(0, 4) + "-" + dateInString.substring(4, 6) + "-"
					+ dateInString.substring(6, 8);
			return date;
		}
		return null;
	}
	
	/**
     * dateInString="201702021030"
     * @return 2017-02-02 10:30
     */
	public static String getFullDateFromString(String dateInString) {
		if (!dateInString.equals("") && dateInString != null) {
			String date = dateInString.substring(0, 4) + "-" + dateInString.substring(4, 6) + "-"
					+ dateInString.substring(6, 8) + " " + dateInString.substring(8, 10) + ":" + dateInString.substring(10, 12) ;
			return date;
		}
		return null;
	}
	
	public static String convertToYYYYMMDDHHMM(String date)
	{
		DateFormat originalFormat = new SimpleDateFormat(formatPattern_UI_yy_MM_ddHHmm);
		DateFormat targetFormat = new SimpleDateFormat(formatPattern_SHORT_MM);
		Date d= null;
		try{
		 d = originalFormat.parse(date);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(logger.isInfoEnabled()) logger.info("ConvertformatPattern_UI_yy_MM_ddHHmm (" + date + ") to formatPattern_SHORT_MM (" + date.toString() + ")");
		}
		String formattedDate = targetFormat.format(d);  
		return formattedDate;

		
	}
	
	 /**
     * curDate="2017-02-01"
     * @return 2017-02-02
     */
	public static String getNextDate(String  curDate) throws ParseException {
		  final SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		  final Date date = format.parse(curDate);
		  final Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.DAY_OF_YEAR, 1);
		  return format.format(calendar.getTime()); 
		}
	
	public static Date getGMTCurrentDateShortReturnDate()
	{
		String curentDate = getGMTCurrentDateWithFullSS();
		DateFormat converted = new SimpleDateFormat(formatPattern_full_ss);
		Date d= null;
		try{
		 d = converted.parse(curentDate);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(logger.isInfoEnabled()) logger.info("getGMTCurrentDateShortReturnDate (" + curentDate + ") to formatPattern_SHORT_MM (" + curentDate.toString() + ")");
		}
		
		return d;

		
	}
	
	public static int minutesDiff(Date earlierDate, Date laterDate)
	{
	    if( earlierDate == null || laterDate == null ) return 0;

	    return (int)((laterDate.getTime()/60000) - (earlierDate.getTime()/60000));
	}
	
	public static String convertDateFormat(Object dateString, String fromFormat, String toFormat) {
		// TODO Auto-generated method stub
		try {
			return new SimpleDateFormat(toFormat).format(new SimpleDateFormat(fromFormat).parse((String) dateString))
					.toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// Jerry 2017-12-13 RTC: 1619325
	public static Date convertDateTimeFormat(String originTime) {
        if(StringUtils.isEmpty(originTime)){
            return null;
        }
        Date date = null;
        DateFormat df = null;
        try{
            if (originTime.contains("-") && originTime.contains(":")) {
                df = new SimpleDateFormat(formatPattern_full);
            } else {
                df = new SimpleDateFormat(formatPattern_SHORT_MM);
            }
            date = df.parse(originTime.trim());
        } catch(Exception e) {
            e.printStackTrace();
        }

       return date;
    }
	
	public static String addLeadingZeroToSingleDigit(int digit) {
		String value = "" + digit;
		if (value.length() == 1) {
			value = "0" + value;
		}
		return value;
	}
}
