package com.todo.list.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "TODO_IMAGE_TEMP_ENTITY")
public class TodoImageTempEntity {

    @Id @Column(name = "TODO_IMAGE_TEMP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEMP_TODO_UUID")
    private UUID uuid;

    @Column(name = "TEMP_IMAGE_REAL_PATH")
    private String imageRealPath;

    @Column(name = "TEMP_IMAGE_LOGIC_PATH")
    private String imageLogicPath;

    @Column(name ="CREATETIMESTAMP")
    private Timestamp timestamp;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public UUID getUuid() {
        return uuid;
    }

    public void setImageRealPath(String imageRealPath) {
        this.imageRealPath = imageRealPath;
    }

    public String getImageRealPath() {
        return imageRealPath;
    }

    public void setImageLogicPath(String imageLogicPath) {
        this.imageLogicPath = imageLogicPath;
    }
    public String getImageLogicPath() {
        return imageLogicPath;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
