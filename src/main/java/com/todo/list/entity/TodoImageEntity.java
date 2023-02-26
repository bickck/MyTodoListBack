package com.todo.list.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "TODO_IMAGE_ENTITY")
public class TodoImageEntity {

    @Id
    @Column(name = "TODO_IMAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TODO_IMAGE_UUID")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TODO_ID", nullable = false)
    private TodoEntity todoBoard;

    @Column(name = "FILEREALPATH", nullable = false)
    private String fileRealPath;

    @Column(name = "FILELOGICPATH", nullable = false)
    private String fileLogicPath;

    @Column(name = "ORIGINALFILENAME", nullable = false)
    private String originalFileName;

    @Column(name = "FILESIZE", nullable = false)
    private Long fileSize;

    @CreationTimestamp
    @Column(name = "CREATETIMESTAMP")
    private Timestamp createTimestamp;

    @UpdateTimestamp
    @Column(name = "UPDATETIMESTAMP")
    private Timestamp updateTimestamp;

    public TodoImageEntity() {
        // TODO Auto-generated constructor stub
    }



    public TodoImageEntity(TodoEntity todoBoard, String fileRealPath, String originalFileName, String fileLogicPath,
                           Long fileSize) {
        super();
        this.todoBoard = todoBoard;
        this.fileRealPath = fileRealPath;
        this.originalFileName = originalFileName;
        this.fileLogicPath = fileLogicPath;
        this.fileSize = fileSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TodoEntity getTodoBoard() {
        return todoBoard;
    }

    public void setTodoBoard(TodoEntity todoBoard) {
        this.todoBoard = todoBoard;
    }

    public String getFileRealPath() {
        return fileRealPath;
    }

    public void setFileRealPath(String fileRealPath) {
        this.fileRealPath = fileRealPath;
    }

    public String getFileLogicPath() {
        return fileLogicPath;
    }

    public void setFileLogicPath(String fileLogicPath) {
        this.fileLogicPath = fileLogicPath;
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

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
