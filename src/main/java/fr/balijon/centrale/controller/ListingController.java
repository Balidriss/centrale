package fr.balijon.centrale.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.entity.Listing;
import fr.balijon.centrale.entity.dto.ListingDTO;
import fr.balijon.centrale.entity.dto.ListingUpdateDTO;
import fr.balijon.centrale.jsonviews.JsonViews;
import fr.balijon.centrale.service.ListingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/listing", name = "app_listing_")
public class ListingController {

    private ListingService listingService;


    @GetMapping(name = "list")
    @JsonView(JsonViews.ListingListView.class)
    public List<Listing> list()
    {
        return listingService.list();
    }

    @GetMapping(value = "/{uuid}", name = "show")
    @JsonView(JsonViews.ListingShow.class)
    public Listing show(@PathVariable String uuid)
    {
        return listingService.findOneById(uuid);
    }


    @PostMapping(name = "create")
    public Listing create(@RequestBody ListingDTO listing, Principal principal)
    {
        return listingService.create(listing, principal);
    }

    @PutMapping(value = "/{id}", name = "update")
    public Listing update(@PathVariable String id, @RequestBody ListingUpdateDTO listing)
    {
        return listingService.update(listing, id);
    }
}
