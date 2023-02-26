package com.todo.list.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "TODO_IMAGE_TEMP_ENTITY")
public class TodoImageTempEntity {

    @Id
    @Column(name = "TODO_IMAGE_TEMP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEMP_TODO_UUID", nullable = false)
    private UUID uuid;

    @Column(name = "TEMP_IMAGE_REAL_PATH", nullable = false)
    private String imageRealPath;

    @Column(name = "TEMP_IMAGE_LOGIC_PATH", nullable = false)
    private String imageLogicPath;

    @Column(name = "TEMP_ORIGINAL_FILE_NAME", nullable = false)
    private String originalFileName;

    @Column(name = "FILESIZE", nullable = false)
    private Long fileSize;

    @Column(name = "CREATETIMESTAMP")
    private Timestamp createTimestamp;


    public TodoImageTempEntity() {

    }

    public TodoImageTempEntity(Long id, UUID uuid, String imageRealPath,
                               String imageLogicPath, String originalFileName,
                               Long fileSize, Timestamp createTimestamp) {
        this.uuid = uuid;
        this.imageRealPath = imageRealPath;
        this.imageLogicPath = imageLogicPath;
        this.originalFileName = originalFileName;
        this.fileSize = fileSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getImageRealPath() {
        return imageRealPath;
    }

    public void setImageRealPath(String imageRealPath) {
        this.imageRealPath = imageRealPath;
    }

    public String getImageLogicPath() {
        return imageLogicPath;
    }

    public void setImageLogicPath(String imageLogicPath) {
        this.imageLogicPath = imageLogicPath;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
