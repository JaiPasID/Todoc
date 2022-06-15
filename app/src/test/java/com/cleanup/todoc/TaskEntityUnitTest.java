package com.cleanup.todoc;

import com.cleanup.todoc.model.TaskEntity;
import com.cleanup.todoc.util.TaskComparator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Unit tests for tasks
 *
 * @author Gaëtan HERFRAY
 */
public class TaskEntityUnitTest {
    @Test
    public void test_projects() {
        final TaskEntity task1 = new TaskEntity(1, "task 1", new Date().getTime());
        final TaskEntity task2 = new TaskEntity(2, "task 2", new Date().getTime());
        final TaskEntity task3 = new TaskEntity(3, "task 3",  new Date().getTime());
        final TaskEntity task4 = new TaskEntity(4, "task 4" , new Date().getTime());

        assertEquals("Projet Tartampion", task1.getName());
        assertEquals("Projet Lucidia", task2.getName());
        assertEquals("Projet Circus", task3.getName());
        assertNull(task4.getProjectId());
    }

    @Test
    public void test_az_comparator() {
        final TaskEntity task1 = new TaskEntity(1, "aaa",  123);
        final TaskEntity task2 = new TaskEntity(2, "zzz",124);
        final TaskEntity task3 = new TaskEntity(3, "hhh", 125);

        final ArrayList<TaskEntity> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new TaskComparator.TaskAZComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task2);
    }

    @Test
    public void test_za_comparator() {
        final TaskEntity task1 = new TaskEntity(1, "aaa",  123);
        final TaskEntity task2 = new TaskEntity(2, "zzz",124);
        final TaskEntity task3 = new TaskEntity(3, "hhh", 125);

        final ArrayList<TaskEntity> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new TaskComparator.TaskZAComparator());

        assertSame(tasks.get(0), task2);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_recent_comparator() {
        final TaskEntity task1 = new TaskEntity(1, "aaa",  123);
        final TaskEntity task2 = new TaskEntity(2, "zzz",124);
        final TaskEntity task3 = new TaskEntity(3, "hhh", 125);

        final ArrayList<TaskEntity> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new TaskComparator.TaskRecentComparator());

        assertSame(tasks.get(0), task3);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_old_comparator() {
        final TaskEntity task1 = new TaskEntity(1, "aaa",  123);
        final TaskEntity task2 = new TaskEntity(2, "zzz",124);
        final TaskEntity task3 = new TaskEntity(3, "hhh", 125);


        final ArrayList<TaskEntity> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new TaskComparator.TaskOldComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task3);
    }
}