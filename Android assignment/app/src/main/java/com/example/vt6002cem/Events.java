package com.example.vt6002cem;

public class Events {
    private String key;
    private String EventName;
    private String EventLocation;
    private String EventDate;
    private String EventTime;
    private String EventHolder;
    private String Description;
    private String Capacity;

    public Events(String key, String EventName, String EventLocation, String EventDate, String EventTime, String EventHolder, String Description, String Capacity) {
        this.key = key;
        this.EventName = EventName;
        this.EventLocation = EventLocation;
        this.EventDate = EventDate;
        this.EventTime = EventTime;
        this.EventHolder = EventHolder;
        this.Description = Description;
        this.Capacity = Capacity;
    }

    public Events(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    public String getEventLocation() {
        return EventLocation;
    }

    public void setEventLocation(String EventLocation) {
        this.EventLocation = EventLocation;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String EventDate) {
        this.EventDate = EventDate;
    }

    public String getEventTime() {
        return EventTime;
    }

    public void setEventTime(String EventTime) {
        this.EventTime = EventTime;
    }

    public String getEventHolder() {
        return EventHolder;
    }

    public void setEventHolder(String EventHolder) {
        this.EventHolder = EventHolder;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String Capacity) {
        this.Capacity = Capacity;
    }
}
