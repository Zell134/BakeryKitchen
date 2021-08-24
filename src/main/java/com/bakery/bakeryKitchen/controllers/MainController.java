package com.bakery.bakeryKitchen.controllers;

import com.bakery.bakeryKitchen.models.Order;
import com.bakery.bakeryKitchen.models.User;
import com.bakery.bakeryKitchen.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final OrdersService service;

    @Autowired
    public MainController(OrdersService service) {
        this.service = service;
    }

    @RequestMapping("/list")
    public String index(Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("orders", service.findAll(pageable));
        return "list";
    }

    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/")
    public String list() {
        return "index";
    }

    @GetMapping("/list/info/{id}")
    public String orderInfo(@PathVariable("id") Order order, Model model) {
        service.setNotNew(order);
        model.addAttribute("order", order);
        return "info";
    }
    
    @PostMapping("/list/sortByDate")
    public String sortByDate(
            @DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam(required = false) String startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam(required = false) String endDate,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        model.addAttribute("error", null);
        Page<Order> page = service.findOrdersBetweenOrderDate(startDate, endDate, pageable);
        if (startDate == null || endDate == null || page == null) {
            page = service.findAll(pageable);
            model.addAttribute("error", "Необходимо установить дату!");
            model.addAttribute("orders", page);
            return "/list";
        }        
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("orders", page);
        return "/list";
    }

}
