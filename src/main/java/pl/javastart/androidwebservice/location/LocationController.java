package pl.javastart.androidwebservice.location;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.javastart.androidwebservice.Category;

@Controller
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @ResponseBody
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public Iterable<Location> getAllCategories() {
        return locationRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Location getCategory(@PathVariable Long id) {
        return locationRepository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Location addLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void deleteLocation(@RequestBody Location location) {
        locationRepository.delete(location);
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
        Location location = new Location();
        location.setName("Wroclaw");
        location.setLatitude(51.12435564007667);
        location.setLongitude(17.026131749153137);
        location.setZoom(7.0F);
        locationRepository.save(location);
    }


}
