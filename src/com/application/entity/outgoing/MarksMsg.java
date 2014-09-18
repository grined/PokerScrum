package com.application.entity.outgoing;

import java.util.List;

public class MarksMsg {
    private List<Double> allMarks;

    public MarksMsg() {
    }

    public MarksMsg(List<Double> allMarks) {
        this.allMarks = allMarks;
    }

    public List<Double> getAllMarks() {
        return allMarks;
    }
}
