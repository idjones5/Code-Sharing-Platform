package com.company.platform.codesharing.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import com.company.platform.codesharing.models.Code;
import com.company.platform.codesharing.models.Comment;
import com.company.platform.codesharing.repository.CodeRepository;
import com.company.platform.codesharing.repository.CommentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CodeRepository codeRepository;


    // get all the comments for a tutorial
    @GetMapping(value = "/code/{id}/comment")
    public ModelAndView getAllCommentsForCode(HttpServletResponse response, @PathVariable String id) {
        if (!codeRepository.existsById(UUID.fromString(id))) {
            throw new NoSuchElementException("No Code found with that id");
        }
        List<Comment> comments = commentRepository.findByCodeId(UUID.fromString(id));
        ModelAndView model = new ModelAndView();
        Code codeRepo = codeRepository.getById(UUID.fromString(id));

        model.addObject("codeBody", codeRepo.getCode());
        model.addObject("date", codeRepo.getDate());
        model.addObject("time", codeRepo.getTime());
        model.addObject("views", codeRepo.getViews());
        model.addObject("textArea", " ");

//        model.addObject("newComment");
        model.addObject("comList", comments);
        model.setViewName("codeComments");
        return model;
    }

    @PostMapping(value = "/code/{id}/comment")
    public void getNewCode(HttpServletResponse response, @PathVariable String id, @RequestBody Comment comment) {
        response.addHeader("Content-Type", "text/html");
        Comment newComment = codeRepository.findById(UUID.fromString(id)).map(code -> {
            comment.setCode(code);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new NoSuchElementException("Not found Code with id " + id));
    }
}
