package com.cleanup.todoc.model;



import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = ProjectEntity.class, parentColumns = "id", childColumns = "project_id", onDelete = CASCADE))

public class TaskEntity {


    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo (name = "project_id", index = true)
    private long projectId;

    @ColumnInfo (name = "name")
    private String name;

    @ColumnInfo (name = "creation_timestamp")
    private long creationTimestamp;

    public TaskEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long pId) {
        id = pId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long pProjectId) {
        projectId = pProjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long pCreationTimestamp) {
        creationTimestamp = pCreationTimestamp;
    }


    public TaskEntity(long pProjectId, @NonNull String pName, long pCreationTimestamp) {

        projectId = pProjectId;
        name = pName;
        creationTimestamp = pCreationTimestamp;
    }




}
