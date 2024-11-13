package model;

import java.time.LocalDate;

public class Task implements Comparable<Task>{
    String title;
    LocalDate date;
    String importance;
    String description;

    public Task(String title, LocalDate date, String importance, String description){
        this.title = title;
        this.date = date;
        this.importance = importance;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Task o) {
        if(date.isAfter(o.date)){
            return 1;
        } else if (date.isEqual(o.date)){
            return 0;
        } else {
            return -1;
        }
    }
}