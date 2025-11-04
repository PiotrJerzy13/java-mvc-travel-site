package com.piotrwojnarowski.controller;

import com.piotrwojnarowski.converter.TravellingConverter;
import com.piotrwojnarowski.data.TravellingRepository;
import com.piotrwojnarowski.domain.Travelling;
import com.piotrwojnarowski.model.TravellingModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TravellingDetailsController {

    private final TravellingRepository repository;
    private final TravellingConverter converter;

    public TravellingDetailsController(TravellingRepository repository,
                                       TravellingConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @ModelAttribute("travelling")
    public TravellingModel travelling(@RequestParam("travellingId") long travellingId) {
        Travelling domain = repository.getById(travellingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid travellingId: " + travellingId));
        return converter.convert(domain);
    }

    @GetMapping("/travelling-details")
    public String details(@RequestParam("travellingId") long travellingId) {
        return "travelling-details";
    }

    @PostMapping("/update-travelling")
    public String updateTravelling(@RequestParam("travellingId") long travellingId,
                                   @ModelAttribute("travelling") TravellingModel travelling,
                                   BindingResult bindingResult,
                                   RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            return "travelling-details";
        }

        Travelling domain = repository.getById(travellingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid travellingId: " + travellingId));

        domain.setReview(travelling.getReview());
        repository.save(domain);

        ra.addFlashAttribute("success", "Review updated.");
        return "redirect:/travelling-details?travellingId=" + travellingId;
    }

}
