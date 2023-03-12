package com.company.platform.codesharing.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "code")
public class Code implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code_id")
    private UUID id;
    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private int time;

    @Column(name = "views")
    private int views;

    // constructors
    public Code () {}

    public Code(String code) {
        this.code = code;
    }

    // methods
    public static CodeViewModel codeToCodeModel(Code codeObject) {
        return new CodeViewModel(codeObject.getCode(), codeObject.getDate(),
                                codeObject.getTime(), codeObject.getViews());
    }

    public static Code checkTime(Code codeObject) {
        String d = codeObject.getDate().replace('T', ' ');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime dateTime = LocalDateTime.parse(d.substring(0, 26), formatter); // date at time of creation

        long duration = ChronoUnit.SECONDS.between(dateTime, LocalDateTime.now());
        int time = codeObject.getTime();
        if (time - duration > 0) { // if the allowed time (seconds) is greater than duration
            codeObject.setTime((int) (time - duration));
            System.out.println(duration + " duration " + time);
        } else {
            codeObject.setTime(0);
        }

        return codeObject;
    }

    public static Code checkViews(Code code) {
        int views = code.getViews();
        if (views > 0) {
            code.setViews(views - 1);
        }
        return code;
    }

    // getters and setters
    public UUID getID() {return id;}
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public String setDate(String date) {
        this.date = date;
        return date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
