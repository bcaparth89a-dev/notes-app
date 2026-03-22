package com.example.notesapp.controller;

import com.example.notesapp.model.Note;
import com.example.notesapp.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("notes", service.active());
        model.addAttribute("view", "active");
        return "index";
    }

    @GetMapping("/archive")
    public String archive(Model model) {
        model.addAttribute("notes", service.archive());
        model.addAttribute("view", "archive");
        return "index";
    }

    @GetMapping("/bin")
    public String bin(Model model) {
        model.addAttribute("notes", service.bin());
        model.addAttribute("view", "bin");
        return "index";
    }

    // 👉 OPEN NEW NOTE
    @GetMapping("/editor")
    public String create(Model model) {
        model.addAttribute("note", new Note());
        return "editor";
    }

    // 👉 EDIT NOTE
    @GetMapping("/editor/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("note", service.getById(id));
        return "editor";
    }

    // 👉 SAVE (CREATE + UPDATE)
    @PostMapping("/save")
    public String save(@RequestParam(required = false) Long id,
                       @RequestParam String title,
                       @RequestParam String content) {

        Note note;

        if (id != null && id != 0) {
            note = service.getById(id);
        } else {
            note = new Note();
        }

        note.setTitle(title);
        note.setContent(content);

        service.save(note);

        return "redirect:/";
    }

    @GetMapping("/archive/{id}")
    public String archiveNote(@PathVariable Long id) {
        service.archive(id);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        service.restore(id);
        return "redirect:/bin";
    }

    @GetMapping("/unarchive/{id}")
    public String unarchive(@PathVariable Long id) {
        service.unarchive(id);
        return "redirect:/archive";
    }

    @GetMapping("/permanent-delete/{id}")
    public String permanentDelete(@PathVariable Long id) {
        service.permanentDelete(id);
        return "redirect:/bin";
    }
}