package ru.destered.semestr3sem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.destered.semestr3sem.dto.SearchForm;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.security.UserDetailsImpl;
import ru.destered.semestr3sem.services.interfaces.UserSearchService;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final UserSearchService userSearchService;

    // TODO: 20.03.2021 показать возможности Spring exLang
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<UserDto> getUsersBySearchForm(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody SearchForm searchForm) {
        // TODO: 20.03.2021 c userDetails придумайте здесь что делать самостоятельно - например уменьшать количество запросов на поиск в час/минуту
        return userSearchService.findAllByRequestBody(searchForm);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public String getSearchPage() {
        return "search";
    }
}
