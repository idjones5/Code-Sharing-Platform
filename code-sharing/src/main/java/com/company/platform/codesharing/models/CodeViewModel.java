package com.company.platform.codesharing.models;

import java.util.ArrayList;
import java.util.List;

public class CodeViewModel {

    private String code;
    private String date;

    private int time;

    private int views;

    // constructors
    public CodeViewModel(String code, String date, int time, int views) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
    }

    public CodeViewModel() {}
    // methods
    public static List<CodeViewModel> convertToCodeModel(List<Code> codeList) {
        List<CodeViewModel> viewModelList = new ArrayList<>();

        for (Code codeObject : codeList) {
            viewModelList.add(new CodeViewModel(codeObject.getCode(), codeObject.getDate(),
                                                codeObject.getTime(), codeObject.getViews()));
        }
        return viewModelList;
    }

    // getters and setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
