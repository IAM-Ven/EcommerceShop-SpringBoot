package io.github.thang86.entities;

import io.github.thang86.enums.StoreHistoryStatus;
import io.github.thang86.enums.StoreHistoryType;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.Date;

/**
*  StoreHistory.java
* 
*  Version 1.0
*
*  Copyright
*
*  Modification Logs:
*  DATE		     AUTHOR		 DESCRIPTION
*  -------------------------------------
*  2018-12-12    ThangTX     Create
*/



@Entity
@Table(name = "StoreHistory")
@Inheritance( strategy = InheritanceType.JOINED )
public class StoreHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @IndexedEmbedded
    private User user;


    @ManyToOne
    @IndexedEmbedded
    private Store store;


    @Column(name = "message", nullable = false)
    @Field
    private String message;

    @Column(name = "dateTime", nullable = false)
    private Date dateTime;

    @Column(name = "type", nullable = false)
    private StoreHistoryType type;

    @Column(name = "status", nullable = false)
    private StoreHistoryStatus status;

    public Store getStore() {
        return store;
    }

    public StoreHistory() {

    }

    public StoreHistory(User user, Store store, String message, Date dateTime, StoreHistoryType type) {
        this.user = user;
        this.store = store;
        this.message = message;
        this.dateTime = dateTime;
        this.type = type;
        this.status = StoreHistoryStatus.UNDOABLE;
    }

    public StoreHistory(User user, Store store, String message, Date dateTime, StoreHistoryType type, StoreHistoryStatus status) {
        this.user = user;
        this.store = store;
        this.message = message;
        this.dateTime = dateTime;
        this.type = type;
        this.status = status;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public StoreHistoryType getType() {
        return type;
    }

    public void setType(StoreHistoryType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StoreHistoryStatus getStatus() {
        return status;
    }

    public void setStatus(StoreHistoryStatus status) {
        this.status = status;
    }
}
