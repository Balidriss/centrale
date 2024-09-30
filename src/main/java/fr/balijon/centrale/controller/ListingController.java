package fr.balijon.centrale.controller;

import fr.balijon.centrale.entity.Listing;
import fr.balijon.centrale.service.ListingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/listing", name = "app_listing_")
public class ListingController {

    private ListingService listingService;

    @GetMapping(name = "list")
    public List<Listing> list()
    {
        return listingService.list();
    }

    @GetMapping(value = "/{uuid}", name = "show")
    public Listing show(@PathVariable String uuid)
    {
        return listingService.findOneById(uuid);
    }


    @PostMapping(name = "create")
    public Listing create(@RequestBody Listing listing)
    {
        return listingService.create(listing);
    }

    @PutMapping(value = "/{id}", name = "update")
    public Listing update(@PathVariable String id, @RequestBody Listing listing)
    {
        return listingService.update(listing, id);
    }
}
