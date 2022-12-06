package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.service.ResidentRegisterListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class ResidentRegisterListController {
    private final ResidentRegisterListService residentRegisterListService;

    public ResidentRegisterListController(ResidentRegisterListService residentRegisterListService) {
        this.residentRegisterListService = residentRegisterListService;
    }

    @GetMapping("/family/resident/list")
    public String inquiryResidentRegister(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(Objects.isNull(session)) {
            return "residentRegisterForm";
        }
        String name = (String) session.getAttribute("name");
        String personId = (String) session.getAttribute("personId");
        List<HouseholdCompositionResidentDto> residentRegisterList = residentRegisterListService.viewResidentRegisterList(name, personId);
        model.addAttribute("residentRegisterList", residentRegisterList);
        return "residentRegisterView";
    }

    @PostMapping("/family/resident/list")
    public String doInquiryFamilyRelation(@RequestParam("name") String name,
                                          @RequestParam("personId") String personId,
                                          HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        session.setAttribute("name", name);
        session.setAttribute("personId", personId);

        return "redirect:/family/resident/list";
    }
}
