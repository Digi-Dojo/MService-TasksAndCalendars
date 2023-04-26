package it.unibz.taskcalendarservice.domain.calendar;

public class CreateCalendarEventDTO {
        private String name;

        public CreateCalendarEventDTO() {
        }

        public CreateCalendarEventDTO(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
