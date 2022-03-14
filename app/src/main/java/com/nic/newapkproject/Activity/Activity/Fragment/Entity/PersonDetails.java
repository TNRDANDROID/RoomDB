package com.nic.newapkproject.Activity.Activity.Fragment.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class PersonDetails {
    @PrimaryKey(autoGenerate = true)
    private int perssion_id;

    @ColumnInfo(name = "total")
    private int total;

    @ColumnInfo(name = "pages")
    private int pages;

    @ColumnInfo(name = "page")
    private int page;

    @ColumnInfo(name = "limit")
    private int limit;

    @ColumnInfo(name = "previous")
    private String previous;

    @ColumnInfo(name = "current")
    private String current;

    @ColumnInfo(name = "next")
    private String next;

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "status")
    private String status;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @ColumnInfo(name = "comments")
    private String comments;


    /*
     * Getters and Setters
     * */

    public int getPerssion_id() {
        return perssion_id;
    }

    public void setPerssion_id(int perssion_id) {
        this.perssion_id = perssion_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
