package org.scut.model;

import java.util.Date;

public class Mistake {
    private Integer mistakeId;

    private Date creatTime;

    private Date lastTime;

    private Integer lastAnswerId;

    private Integer correctAnswer;

    private String description;

    private String picPath;

    private Integer subjectId;

    private String mistakeNote;

    private Integer mistakeType;

    private String resolve;

    public Integer getMistakeId() {
        return mistakeId;
    }

    public void setMistakeId(Integer mistakeId) {
        this.mistakeId = mistakeId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getLastAnswerId() {
        return lastAnswerId;
    }

    public void setLastAnswerId(Integer lastAnswerId) {
        this.lastAnswerId = lastAnswerId;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getMistakeNote() {
        return mistakeNote;
    }

    public void setMistakeNote(String mistakeNote) {
        this.mistakeNote = mistakeNote == null ? null : mistakeNote.trim();
    }

    public Integer getMistakeType() {
        return mistakeType;
    }

    public void setMistakeType(Integer mistakeType) {
        this.mistakeType = mistakeType;
    }

    public String getResolve() {
        return resolve;
    }

    public void setResolve(String resolve) {
        this.resolve = resolve == null ? null : resolve.trim();
    }
}