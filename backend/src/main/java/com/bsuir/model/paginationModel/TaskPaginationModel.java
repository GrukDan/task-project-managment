package com.bsuir.model.paginationModel;

import com.bsuir.model.viewModel.TaskViewModel;

import java.util.Arrays;

public class TaskPaginationModel {
    private int countPages;
    private int numberPage;
    private TaskViewModel taskViewModels[];

    public TaskPaginationModel() {
    }

    public TaskPaginationModel(int countPages, int numberPage, TaskViewModel[] taskViewModels) {
        this.countPages = countPages;
        this.numberPage = numberPage;
        this.taskViewModels = taskViewModels;
    }

    @Override
    public String toString() {
        return "TaskPaginationModel{" +
                "countPages=" + countPages +
                ", numberPage=" + numberPage +
                ", taskViewModels=" + Arrays.toString(taskViewModels) +
                '}';
    }

    public int getCountPages() {
        return countPages;
    }

    public void setCountPages(int countPages) {
        this.countPages = countPages;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public TaskViewModel[] getTaskViewModels() {
        return taskViewModels;
    }

    public void setTaskViewModels(TaskViewModel[] taskViewModels) {
        this.taskViewModels = taskViewModels;
    }
}
