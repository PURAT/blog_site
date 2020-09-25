package com.example.blog.controllers;

import com.example.blog.models.Post;
import com.example.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public String mainPage(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog/main";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        return "blog/add";
    }

    @PostMapping("/add")
    public String addPage(@RequestParam String title, @RequestParam String anons,
                          @RequestParam String full_text) {
            Post post = new Post(title, anons, full_text);
            postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/{id}")
    public String detailsPage(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id))
            return "redirect:/blog";
        Optional<Post> result = postRepository.findById(id);
        ArrayList<Post> post = new ArrayList<>();
        result.ifPresent(post::add);
        model.addAttribute("post", post);
        return "/blog/details";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id))
            return "redirect:/blog";
        Optional<Post> result = postRepository.findById(id);
        ArrayList<Post> post = new ArrayList<>();
        result.ifPresent(post::add);
        model.addAttribute("post", post);
        return "/blog/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") long id, @RequestParam String title,
                           @RequestParam String anons, @RequestParam String full_text) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/{id}/remove")
    public String removePage(@PathVariable(value = "id") long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }
}
