package com.bsuir.model.paginationModel;

import com.bsuir.model.viewModel.ProjectViewModel;

import java.util.Arrays;

public class ProjectPaginationModel {
    private int countPages;
    private int numberPage;
    private ProjectViewModel projectViewModels[];

    public ProjectPaginationModel(){}

    public ProjectPaginationModel(int countPages, int numberPage, ProjectViewModel[] projectViewModels) {
        this.countPages = countPages;
        this.numberPage = numberPage;
        this.projectViewModels = projectViewModels;
    }

    @Override
    public String toString() {
        return "ProjectPaginationModel{" +
                "countOfPages=" + countPages +
                ", numberOfPage=" + numberPage +
                ", projectViewModels=" + Arrays.toString(projectViewModels) +
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

    public ProjectViewModel[] getProjectViewModels() {
        return projectViewModels;
    }

    public void setProjectViewModels(ProjectViewModel[] projectViewModels) {
        this.projectViewModels = projectViewModels;
    }
}
