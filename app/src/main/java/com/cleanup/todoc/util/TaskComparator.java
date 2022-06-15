package com.cleanup.todoc.util;

import com.cleanup.todoc.model.TaskEntity;

import java.util.Comparator;

public class TaskComparator {

    public static class TaskAZComparator implements Comparator<TaskEntity> {
        @Override
        public int compare(TaskEntity left, TaskEntity right) {
            return left.getName().compareTo(right.getName());
        }
    }
    /**
     Comparator to sort task from Z to A
     */
    public static class TaskZAComparator implements Comparator<TaskEntity> {
        @Override
        public int compare(TaskEntity left, TaskEntity right) {
            return right.getName().compareTo(left.getName());
        }
    }


    /** Comparator to sort task from last created to first created
     */

    public static class TaskRecentComparator implements Comparator<TaskEntity> {
        @Override
        public int compare(TaskEntity left, TaskEntity right) {
            return (int) (right.getCreationTimestamp() - left.getCreationTimestamp());
        }
    }


    /** Comparator to sort task from first created to last created

     */


    public static class TaskOldComparator implements Comparator<TaskEntity> {
        @Override
        public int compare(TaskEntity left, TaskEntity right) {
            return (int) (left.getCreationTimestamp() - right.getCreationTimestamp());
        }
    }

}
