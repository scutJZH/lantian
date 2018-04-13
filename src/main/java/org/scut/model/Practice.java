package org.scut.model;

import java.util.Date;

public class Practice {
    private Integer practiceId;

    private String practiceTitle;

    private Date practiceCreateTime;

    private Integer maxScore;

    private Integer semesterId;

    private Integer subjectId;

    public Integer getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Integer practiceId) {
        this.practiceId = practiceId;
    }

    public String getPracticeTitle() {
        return practiceTitle;
    }

    public void setPracticeTitle(String practiceTitle) {
        this.practiceTitle = practiceTitle == null ? null : practiceTitle.trim();
    }

    public Date getPracticeCreateTime() {
        return practiceCreateTime;
    }

    public void setPracticeCreateTime(Date practiceCreateTime) {
        this.practiceCreateTime = practiceCreateTime;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}