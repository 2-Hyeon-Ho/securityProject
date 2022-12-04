package com.nhnacademy.springjpa.controller.familyRelation;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.service.familyRelation.FamilyRelationInquiryService;
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
public class FamilyRelationController {
    private final FamilyRelationInquiryService familyRelationInquiryService;

    public FamilyRelationController(FamilyRelationInquiryService familyRelationInquiryService) {
        this.familyRelationInquiryService = familyRelationInquiryService;
    }

    @GetMapping("/family/relation/list")
    public String inquiryFamilyRelation(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(Objects.isNull(session)) {
            return "inquiryFamilyRelationForm";
        }
        String name = (String) session.getAttribute("name");
        String personId = (String) session.getAttribute("personId");
        List<ResidentDto> residents = familyRelationInquiryService.viewFamily(name, personId);
        model.addAttribute("residents", residents);

        return "familyRelationView";
    }

    @PostMapping("/family/relation/list")
    public String doInquiryFamilyRelation(@RequestParam("name") String name,
                                          @RequestParam("personId") String personId,
                                          HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        session.setAttribute("name", name);
        session.setAttribute("personId", personId);

        return "redirect:/family/relation/list";
    }
}
