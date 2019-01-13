package com.example.azuredragon.business.Localfile;


import com.example.azuredragon.IPresenter;

import java.io.File;
import java.util.List;

public interface IImportBookPresenter extends IPresenter {
    void searchLocationBook();
    void stopSearchLocationBook();
    void importBooks(List<File> books);
}
