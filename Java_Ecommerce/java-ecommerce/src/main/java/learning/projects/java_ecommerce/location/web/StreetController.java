package learning.projects.java_ecommerce.location.web;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learning.projects.java_ecommerce.location.dto.StreetDto;
import learning.projects.java_ecommerce.location.service.StreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/streets")
@RequiredArgsConstructor
public class StreetController {

    private final StreetService streetService;
    
    @GetMapping
    public List<StreetDto> getAllStreets() {
        return streetService.getAllStreets();
    }       

    @GetMapping("/{id}")
    public StreetDto getStreetById(@PathVariable UUID id) {
        return streetService.getStreetById(id);
    }
    
}
