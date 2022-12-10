package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.service.household.HouseholdInquiryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class ResidentRegisterListController {
    private final HouseholdInquiryService householdInquiryService;

    //주민등록등본
    @GetMapping("/family/resident/list")
    public String inquiryResidentRegister(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(Objects.isNull(session)) {
            return "login";
        }

        String id = (String) session.getAttribute("id");

        List<HouseholdCompositionResidentDto> residentRegisterList = householdInquiryService.viewResidentRegisterList(id);
        CertificateIssue certificateIssue = householdInquiryService.viewCertificateIssue(id);
        Household household = householdInquiryService.getHouseholdResident(id);
        model.addAttribute("residentRegisterList", residentRegisterList);
        model.addAttribute("certificateIssue", certificateIssue);
        model.addAttribute("household", household);
        return "residentRegisterView";
    }

    public ResidentRegisterListController(HouseholdInquiryService householdInquiryService) {
        this.householdInquiryService = householdInquiryService;
    }
}
