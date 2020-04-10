package com.bsuir.model.paginationModel;

import com.bsuir.model.viewModel.ProjectViewModel;

import java.util.Arrays;

public class ProjectPaginationModel {
    private int countOfPages;
    private int numberOfPage;
    private ProjectViewModel projectViewModels[];

    public ProjectPaginationModel(){}

    public ProjectPaginationModel(int countOfPages, int numberOfPage, ProjectViewModel[] projectViewModels) {
        this.countOfPages = countOfPages;
        this.numberOfPage = numberOfPage;
        this.projectViewModels = projectViewModels;
    }

    @Override
    public String toString() {
        return "ProjectPaginationModel{" +
                "countOfPages=" + countOfPages +
                ", numberOfPage=" + numberOfPage +
                ", projectViewModels=" + Arrays.toString(projectViewModels) +
                '}';
    }

    public int getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(int countOfPages) {
        this.countOfPages = countOfPages;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public ProjectViewModel[] getProjectViewModels() {
        return projectViewModels;
    }

    public void setProjectViewModels(ProjectViewModel[] projectViewModels) {
        this.projectViewModels = projectViewModels;
    }
}
