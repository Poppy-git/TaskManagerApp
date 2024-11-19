package com.example.taskmanagerapp.entity;

public class Task {

        private int ID;
        private String taskTitle, taskDescription, taskTime;

        public Task(String taskTitle, String taskDescription, String taskTime) {
            this.taskTitle = taskTitle;
            this.taskDescription = taskDescription;
            this.taskTime = taskTime;
        }

        public Task(int ID, String taskTitle, String taskDescription, String taskTime) {
            this.ID = ID;
            this.taskTitle = taskTitle;
            this.taskDescription = taskDescription;
            this.taskTime = taskTime;
        }

        public int getID() {
            return ID;
        }

        public String gettaskTitle() {
            return taskTitle;
        }

        public String gettaskDescription() {
            return taskDescription;
        }

        public String gettaskTime() {
            return taskTime;
        }
    }


