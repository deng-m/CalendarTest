package com.example.calendartest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codingending.popuplayout.PopupLayout;
import com.necer.calendar.BaseCalendar;
import com.necer.calendar.MonthCalendar;
import com.necer.enumeration.CheckModel;
import com.necer.enumeration.DateChangeBehavior;
import com.necer.listener.OnCalendarChangedListener;

import org.joda.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    PopupLayout popupLayout;
    View calendarView;
    TextView mYear, mMonth, lastYear, nextYear, lastMonth, nextMonth;
    MonthCalendar monthCalendar;
    int currentYear, currentMonth;
    Button intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = findViewById(R.id.intent);
        calendarView = View.inflate(this, R.layout.calendar, null);
        popupLayout = PopupLayout.init(this, calendarView);

    }

    public void intent(View view) {
        initCalendar();
        popupLayout.show(PopupLayout.POSITION_CENTER);
    }

    public void initCalendar() {
        monthCalendar = calendarView.findViewById(R.id.month_calendar);
        mYear = calendarView.findViewById(R.id.year);
        mMonth = calendarView.findViewById(R.id.month);
        lastYear = calendarView.findViewById(R.id.lastYear);
        nextYear = calendarView.findViewById(R.id.nextYear);
        lastMonth = calendarView.findViewById(R.id.lastMonth);
        nextMonth = calendarView.findViewById(R.id.nextMonth);
        monthCalendar.setCheckMode(CheckModel.SINGLE_DEFAULT_UNCHECKED);

        monthCalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate, DateChangeBehavior dateChangeBehavior) {
                mYear.setText(String.valueOf(year));
                mMonth.setText(String.valueOf(month));
                intent.setText(String.valueOf(localDate));
                currentYear = year;
                currentMonth = month;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popupLayout.dismiss();
                    }
                },800);
            }
        });
    }

    public void lastMonth(View view) {
        monthCalendar.toLastPager();
    }

    public void nextMonth(View view) {
        monthCalendar.toNextPager();
    }

    public void nextYear(View view) {
        monthCalendar.jumpDate(currentYear + 1, currentMonth, 1);
    }

    public void lastYear(View view) {
        monthCalendar.jumpDate(currentYear - 1, currentMonth, 1);
    }
}