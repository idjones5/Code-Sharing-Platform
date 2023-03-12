package com.company.platform.codesharing.service;

import com.company.platform.codesharing.repository.CodeRepository;
import com.company.platform.codesharing.models.Code;
import com.company.platform.codesharing.models.CodeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceLayer {

    @Autowired
    CodeRepository codeRepository;

    public ServiceLayer(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    // find object
    public Code findCodeObject(String id) {
        Code code = Code.checkTime(Code.checkViews(codeRepository.findById(UUID.fromString(id)).orElseGet(null)));
        codeRepository.save(code);
        return code;
    }

    // save new code object
    public UUID saveNewCodeObject(Code newCode) {
        newCode.setDate(String.valueOf(LocalDateTime.now()));
        return codeRepository.save(newCode).getID();
    }

    // serve new code HTML
    public ModelAndView getNewCodeHtml() {
        ModelAndView model = new ModelAndView();
        model.addObject("textArea");
        model.addObject("date");
        model.setViewName("new");
        return model;
    }

    // serve single code HTML view
    public ModelAndView getHtmlCode(Code code) {
        ModelAndView model = new ModelAndView();
        model.addObject("codeBody", code.getCode());
        model.addObject("date", code.getDate());
        //model.addObject("comment");

        if (code.getTime() > 0) {
            model.addObject("time", code.getTime());
        }
        if (code.getViews() > 0) {
            model.addObject("views", code.getViews());
        }

        System.out.println(code.getTime() + " starting time");
        model.setViewName("code");
        return model;
    }

    // serve the latest code list HTML view
    public ModelAndView getLatestHtml() {
        ModelAndView model = new ModelAndView();
        model.addObject("list", codeRepository.findLastTenAdded());
        model.setViewName("latest");
        return model;
    }

    // checks the restriction of the object
    public boolean checkDataBase(Code code) {
        if (Code.checkTime(code).getTime() <= 0 || Code.checkViews(code).getViews() < 0) {
            // then time is up or no more views are allowed
            codeRepository.deleteById(code.getID());
            return true;
        }
        return false;
    }

    // gets the 10 last added code object
    public List<CodeViewModel> convertToCodeModel() {
        List<CodeViewModel> viewModelList = new ArrayList<>();

        for (Code codeObject : codeRepository.findLastTenAdded()) {
            viewModelList.add(new CodeViewModel(codeObject.getCode(), codeObject.getDate(),
                    codeObject.getTime(), codeObject.getViews()));
        }
        return viewModelList;
    }

    // converts code object to code view model
    public CodeViewModel codeToCodeModel(Code codeObject) {
        return new CodeViewModel(codeObject.getCode(), codeObject.getDate(),
                codeObject.getTime(), codeObject.getViews());
    }
}
