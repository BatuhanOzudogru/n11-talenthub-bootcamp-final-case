package com.batuhanozudogru.restaurantservice.controller;


import com.batuhanozudogru.restaurantservice.controller.contract.RestaurantControllerContract;
import com.batuhanozudogru.restaurantservice.dto.request.RestaurantRequest;
import com.batuhanozudogru.restaurantservice.dto.response.RestaurantResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/restaurants")

public class RestaurantController {

//        private final RestaurantRepository restaurantRepository;
//
//        public RestaurantController(RestaurantRepository restaurantRepository) {
//            this.restaurantRepository = restaurantRepository;
//        }
//
//        @GetMapping
//        public Iterable<Restaurant> getAllRestaurants() {
//            return restaurantRepository.findAll();
//        }
//
//        @PostMapping("/save")
//        public Restaurant saveRestaurant(@RequestBody Restaurant restaurant) {
//            return restaurantRepository.save(restaurant);
//        }
//
////        @GetMapping
////        public List<RestaurantResponse> getAllRestaurants() {
////            RestaurantMapper restaurantMapper = new RestaurantMapper();
////            return restaurantMapper.convertToRestaurantResponseList((List<Restaurant>) restaurantRepository.findAll());
////
////        }
////
////        @PostMapping("/save")
////
////        public Restaurant saveRestaurant(@RequestBody RestaurantRequest request) {
////            RestaurantMapper restaurantMapper = new RestaurantMapper();
////            Restaurant restaurant = restaurantMapper.convertToRestaurant(request);
////            return restaurantRepository.save(restaurant);
////        }

    private final RestaurantControllerContract contract;

    public RestaurantController(RestaurantControllerContract contract) {
        this.contract = contract;
    }

    @GetMapping
    public Iterable<RestaurantResponse> getAllRestaurants() {
        return contract.getRestaurants();
    }

    @PostMapping("/save")
    public RestaurantResponse saveRestaurant(@RequestBody RestaurantRequest restaurant) {
        return contract.saveRestaurant(restaurant);
    }

    @GetMapping("/get-by-id/{id}")
    public RestaurantResponse getRestaurantById(@PathVariable String id) {
        return contract.getRestaurantById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRestaurantById(@PathVariable String id) {
        contract.deleteRestaurant(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllRestaurants() {
        contract.deleteAllRestaurants();
    }

    @PutMapping("/update/{id}")
    public RestaurantResponse updateRestaurant(@RequestParam String id,@RequestBody RestaurantRequest request) {
        return contract.updateRestaurant(id, request);
    }

}
