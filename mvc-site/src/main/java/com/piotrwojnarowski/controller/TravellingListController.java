package com.piotrwojnarowski.controller;

import com.piotrwojnarowski.converter.TravellingConverter;
import com.piotrwojnarowski.data.TravellingRepository;
import com.piotrwojnarowski.domain.Travelling;
import com.piotrwojnarowski.model.TravellingModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class TravellingListController {

    private final TravellingRepository repository;
    private final TravellingConverter converter;

    public TravellingListController(TravellingRepository repository,
                                    TravellingConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }
    @GetMapping("/travelling-list")
    public String home() {
        return "travelling-list";
    }
    @ModelAttribute("travellingList")
    public List<TravellingModel> populateTravellingList() {
        return converter.convert(repository.findAll());
    }
    @ModelAttribute("newTravelling")
    public TravellingModel newTravelling() {
        return new TravellingModel();
    }
    @PostMapping("/create-travelling")
    public String createTravelling(
            @Valid @ModelAttribute("newTravelling") TravellingModel newTravelling,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "travelling-list";
        }

        Travelling toSave = converter.convert(newTravelling);
        repository.create(toSave);

        redirectAttributes.addFlashAttribute("success", "Travelling created successfully.");
        return "redirect:/travelling-list";
    }
    }


