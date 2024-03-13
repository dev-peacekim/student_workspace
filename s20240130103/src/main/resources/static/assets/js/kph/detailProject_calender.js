/**
 * 
 */

document.addEventListener("DOMContentLoaded", function () {
  var calendarEl = document.getElementById("calender");
  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: "dayGridMonth",
    height: 600,
    Boolean,
    default: true,
    events: [
      {
        // this object will be "parsed" into an Event Object
        title: "잘되냐이거", // a property!
        start: "2024-03-12", // a property!
        end: "2024-03-14", // a property! ** see important note below about 'end' **
        color: "#0d6efdb5",
      },
      {
        // this object will be "parsed" into an Event Object
        title: "잘되냐이거22", // a property!
        start: "2024-03-12", // a property!
        end: "2024-03-20", // a property! ** see important note below about 'end' **
        color: "red ",
      },
    ],
  });
  calendar.render();
});
