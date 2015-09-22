Requirement
=================

----------


1	View Calendar
-------------



#### 1.1 Monthly View for selected year
#####1.1.1		User should be able to select year to view the events of the year.
#####1.1.2		User should be able to view the entire events of the month.
#####1.1.3		User should be able to delete events by selecting them or delete all the events in the month.

#### 1.2 Weekly View for the selected year and month
#####1.2.1		User should be able to select year and month for viewing of the events of the month.
#####1.2.2		User should be able to view all the views of the week.
#####1.2.3		User should be able to delete events by selecting them or delete all the events in the week.

####1.3	Daily View
#####1.3.1	User should be able to view all the events of the selected day.
#####1.3.2	User should be able to sort the events of the selected day by starting time sequence.	
#####1.3.3	User should be able to delete one event or entire events on the daily view page.

####1.4 Agenda View(option)
#####1.4.1	The user should be able to show all the events in the future as a list.
#####1.4.2	The user can select the next month, next year as the duration of the list.


2	Event
-------------

#### 2.1 Add Event
#####2.1.1	User can add event and its content.
#####2.1.2	User should be able to set the start date and time of the event as well as the end date and time.
#####2.1.3	More than one events can be added by the user no matter if the collapse will happen.
#####2.1.4	User should be able to select the alert method when adding event.
#####2.1.5	System will use the default alert method if the user fails to select the alert method when adding event.
#####2.1.6	User can set the system to repeat the event in time range.
#####2.1.7	The event can be repeated hourly, daily, weekly, monthly.
#####2.1.8	The event can marked for different category, like holiday, business, school, etc.
#####2.1.9	The time conflicts notify will be popped up once there is a time conflict.
####2.1.10	The user can add/modify/remove event category.
####2.1.11	The events whose category have been removed are marked as uncategorized.

####2.2 Modify Event
#####2.2.1	The alert method yields to the global setting.
#####2.2.2	The user should be able to modify the date and time of an event.
#####2.2.3	The user should be able to modify the alert style.
#####2.2.4	The user should be able to modify the content of the event.

####2.3 Delete Event
#####2.3.1	The user should be able to delete an event or the entire events for the selected date.
#####2.3.2	The user should be asked to select permanently or temporarily delete a repeated event.
#####2.3.3	If the user selects permanently deleting the repeated event, then the event should be deleted, other wise the system will just remove the event for one time. 



3	Alert
-------------
####	3.1	 Configure the alert style
#####3.1.1	The user should be able to choose different music as alert.
#####3.1.2	The system will play the default music in case of absence of the music.
#####3.1.3	The user should have option to choose different alert style, music, vibrator and music , vibrator only.
#####3.1.4	The user should be able to change the volume level of the music.
#####3.1.5	There should be one global alert set to control all the events.  All the events have to obey to the global set no matter what the alert style they have currently.
#####3.1.6	The user should be able to set when the alert starts to notify, say like 30 minutes ahead of the event.
#####3.1.7 	The alert will keep alerting until the user confirm the event.
#####3.1.8	The user can snooze the alert by tapping "remind me in 5 minutes", which will be reminded again in 5 minutes.


4	Share
-------------------
4.1 or 4.2
#####4.1 the calendar can send the calendar to another user's same calendar app through internet.
#####4.2 the target user's ip address is needed.

#####4.2	the calendar's database can synchronize with a backend database.
#####4.2.1 The login logout pages are required if there is a database at backend.



5	Miscellaneous
-------------------
####5.1	The event snippet  with different categories is displayed with different color.For example Holidays & weekends should be in special colors
####5.2	The screen should be able to zoom in/out.
####5.1	The scroll should be available if necessary.