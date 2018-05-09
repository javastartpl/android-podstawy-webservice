package pl.javastart.androidwebservice.location;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("")
    public Iterable<Location> getAllCategories() {
        return locationRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Location> getCategory(@PathVariable Long id) {
        return locationRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Location addLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    @DeleteMapping("/")
    public void deleteLocation(@RequestBody Location location) {
        locationRepository.delete(location);
    }

    @GetMapping("/test")
    public void test() {
        Location location = new Location();
        location.setName("Wroclaw");
        location.setLatitude(51.12435564007667);
        location.setLongitude(17.026131749153137);
        location.setZoom(7.0F);
        locationRepository.save(location);
    }


}
