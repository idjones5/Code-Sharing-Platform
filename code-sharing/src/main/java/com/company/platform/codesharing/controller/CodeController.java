package com.company.platform.codesharing.controller;

import com.company.platform.codesharing.models.Code;
import com.company.platform.codesharing.models.CodeViewModel;
import com.company.platform.codesharing.models.PostCode;
import com.company.platform.codesharing.service.ServiceLayer;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RestController
public class CodeController {
    @Autowired
    ServiceLayer serviceLayer;

    @Autowired
    public CodeController() {}

    @GetMapping(value = "/code/{id}")
    public ModelAndView getHtml(HttpServletResponse response, @PathVariable String id) {
        response.addHeader("Content-Type", "text/html");
        Code code = serviceLayer.findCodeObject(id);
        return serviceLayer.getHtmlCode(code);
    }

    @GetMapping(value = "/api/code/{id}")
    public CodeViewModel getApiCode(HttpServletResponse response, @PathVariable String id) {
        response.addHeader("Content-Type", "application/json");
        Code code = serviceLayer.findCodeObject(id);
        // update the views, save to repo, and return view model
        return serviceLayer.codeToCodeModel(code);
    }

    @GetMapping(value = "/code/new")
    public ModelAndView getNewCode(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return serviceLayer.getNewCodeHtml();
    }

    @PostMapping(value = "/api/code/new")
    @ResponseBody
    public PostCode createNewCode(@RequestBody Code newCode) {
        // saving to repo
        UUID id = serviceLayer.saveNewCodeObject(newCode);
        return new PostCode(id.toString());
    }

    @GetMapping(value = "/code/latest")
    public ModelAndView getLatestCode(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return serviceLayer.getLatestHtml();
    }

    @GetMapping(value = "/api/code/latest")
    public List<CodeViewModel> getLatestApiCode(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return serviceLayer.convertToCodeModel();
    }
}
