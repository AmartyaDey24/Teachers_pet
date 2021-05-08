package com.example.teacherspet.Calender

import android.icu.util.Calendar
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.teacherspet.R
import org.w3c.dom.Text


class CalenderOfEvents : AppCompatActivity() {

    var calender: CalendarView? = null
    var date_view: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender_of_events)

        //list of dates
        val events=ArrayList<Event>()
        events.add(Event("5-8-2020","Commencement of Classes."))
        events.add(Event("16-8-2020","Last Date for Payment of Fee."))
        events.add(Event("26-8-2020","Last Date for Payment of Fee (with fine)."))
        events.add(Event("3-9-2020","Last Date for Admission."))
        events.add(Event("11-9-2020","Last Date for Admission (with fine)."))
        events.add(Event("1-10-2020","Allotment of Registration No's."))
        events.add(Event("30-11-2020","Last Day for Submission of CIE Marks."))
        events.add(Event("9-12-2020","Semester End Examinations."))
        events.add(Event("4-12-2020","Last Working Day."))
        events.add(Event("13-1-2021","Announcement of Results."))
        events.add(Event("16-1-2021","Makeup Examinations."))
        events.add(Event("1-2-2021","Last Day for Registration of Courses."))
        events.add(Event("3-2-2021","Reopening of Classes."))
        events.add(Event("5-5-2021","My Birthday!!!."))

        calender = findViewById<View>(R.id.calender) as CalendarView
        date_view = findViewById<View>(R.id.date_view) as TextView
        val event_desc=findViewById<TextView>(R.id.calender_event_view)

        //initializing calender
        calender!!
                .setOnDateChangeListener { view, year, month, dayOfMonth ->

                    val Date = (dayOfMonth.toString() + "-"
                            + (month + 1) + "-" + year)

                    // set this date in TextView for Display
                    date_view!!.text = Date
                    events.forEach {
                        if(Date==it.date) {
                            event_desc.text=it.eventName
                        }
                    }

                }


    }
}



