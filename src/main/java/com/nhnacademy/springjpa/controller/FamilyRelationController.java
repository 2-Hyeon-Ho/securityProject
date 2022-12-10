package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.service.familyRelation.FamilyRelationInquiryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    //가족관계증명서
    @GetMapping("/family/relation/list")
    public String inquiryFamilyRelation(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(Objects.isNull(session)) {
            return "login";
        }
        String id = (String) session.getAttribute("id");

        CertificateIssue certificateIssue = familyRelationInquiryService.viewCertificateIssue(id);
        Resident resident = familyRelationInquiryService.getResident(id);
        List<ResidentDto> residents = familyRelationInquiryService.viewFamily(id);
        model.addAttribute("certificateIssue", certificateIssue);
        model.addAttribute("resident", resident);
        model.addAttribute("residents", residents);

        return "familyRelationView";
    }
}
