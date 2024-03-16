package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.client.RestaurantClient;
import com.batuhanozudogru.userservice.dao.UserRepository;
import com.batuhanozudogru.userservice.dto.request.RestaurantSaveRequest;
import com.batuhanozudogru.userservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.exception.UserNotFoundException;
import com.batuhanozudogru.userservice.general.message.Message;
import com.batuhanozudogru.userservice.general.result.Result;
import com.batuhanozudogru.userservice.general.result.ResultData;
import com.batuhanozudogru.userservice.general.result.ResultHelper;
import com.batuhanozudogru.userservice.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurant Controller", description = "Restaurant Management")
public class RestaurantController {

    private final RestaurantClient restaurantClient;

    private final UserRepository userRepository;

    @GetMapping
    @Operation(
            description = "Get all restaurants",
            summary = "Get Restaurants"
    )
    public ResultData<List<RestaurantResponse>> getRestaurants() {

        List<RestaurantResponse> restaurants = restaurantClient.getRestaurants().getData();

        return ResultHelper.success(restaurants);
    }

    @GetMapping("/get-by-name/{name}")
    @Operation(
            description = "Get the restaurants with the given name",
            summary = "Get Restaurants By Name"
    )
    public ResultData<List<RestaurantResponse>> getRestaurantsByName(@PathVariable String name) {

        List<RestaurantResponse> restaurants = restaurantClient.getRestaurantsByName(name).getData();

        return ResultHelper.success(restaurants);
    }

    @GetMapping("recommend-restaurants/{userId}")
    @Operation(
            description = "Get recommended restaurants for the user based on proximity and rating. Returns a maximum of 3 restaurant recommendations.",
            summary = "Get Recommended Restaurants"
    )
    public ResultData<Map<String, Long>> getRecommendRestaurants(@PathVariable Long userId) {

        Iterable<RestaurantResponse> restaurants = restaurantClient.getRestaurants().getData();

        User user = userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException(Message.USER_NOT_FOUND_BY_ID(userId)));

        Map<String, Long> recommendList = RecommendationService.recommendList(restaurants, user);

        return ResultHelper.success(recommendList);
    }

    @DeleteMapping("/delete-all")
    @Operation(
            description = "Deletes all restaurants",
            summary = "Delete All Restaurants"
    )
    public Result deleteAllRestaurants() {

        restaurantClient.deleteAllRestaurants();

        return ResultHelper.allDeleted();
    }

    @PostMapping("/save")
    @Operation(
            description = "Creates a new restaurant",
            summary = "Save Restaurant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant Save Request",
                    content ={
                            @Content(
                                    mediaType = "application/json",
                                    schema =@Schema(
                                            implementation = RestaurantSaveRequest.class
                                    ),
                                    examples={
                                            @ExampleObject(
                                                    name="Example Restaurant Save Request",
                                                    summary = "Please Select a Restaurant",
                                                    value = "{\n" +
                                                            "  \"name\": \"Restaurant Name\",\n" +
                                                            "  \"address\": \"Restaurant Address\",\n" +
                                                            "  \"latitude\": 41.0082,\n" +
                                                            "  \"longitude\": 28.9784,\n" +
                                                            "  \"rate\": \"5\"\n" +
                                                            "}",
                                                    description = "You have to send a request like this"
                                            ),
                                            @ExampleObject(
                                                    name="Example : Kanlıca Teras",
                                                    value = "{\n" +
                                                            "  \"name\": \"Kanlıca Teras\",\n" +
                                                            "  \"address\": \"Kanlıca Mahallesi/Beykoz\",\n" +
                                                            "  \"latitude\": 41.1004079,\n" +
                                                            "  \"longitude\": 29.0667945,\n" +
                                                            "  \"rate\": \"5\"\n" +
                                                            "}",
                                                    description = "Example Restaurant 1"
                                            ),
                                            @ExampleObject(
                                                    name="Example : Beykoz Sosyal Tesisleri",
                                                    value = "{\n" +
                                                            "  \"name\": \"Beykoz Sosyal Tesisleri\",\n" +
                                                            "  \"address\": \"Paşabahçe Mahallesi/Beykoz\",\n" +
                                                            "  \"latitude\": 41.1128871,\n" +
                                                            "  \"longitude\": 29.0856309,\n" +
                                                            "  \"rate\": \"3\"\n" +
                                                            "}",
                                                    description = "Example Restaurant 2"
                                            ),
                                            @ExampleObject(
                                                    name="Example : Beykoz Balık Ekmek",
                                                    value = "{\n" +
                                                            "  \"name\": \"Beykoz Balık Ekmek\",\n" +
                                                            "  \"address\": \"Gümüşsuyu Mahallesi / Beykoz\",\n" +
                                                            "  \"latitude\": 41.1303174,\n" +
                                                            "  \"longitude\": 29.0934711,\n" +
                                                            "  \"rate\": \"2\"\n" +
                                                            "}",
                                                    description = "Example Restaurant 3"
                                            ),

                                            @ExampleObject(
                                                    name="Example : Bayramoğlu Döner",
                                                    value = "{\n" +
                                                            "  \"name\": \"Bayramoğlu Döner\",\n" +
                                                            "  \"address\": \"Kavacık Mahallesi/Beykoz\",\n" +
                                                            "  \"latitude\": 41.09489,\n" +
                                                            "  \"longitude\": 29.099538,\n" +
                                                            "  \"rate\": \"5\"\n" +
                                                            "}",
                                                    description = "Example Restaurant 4"
                                            ),

                                            @ExampleObject(
                                                    name="Example : Kız Kulesi"
                                                    ,value = "{\n" +
                                                    "  \"name\": \"Kız Kulesi\",\n" +
                                                    "  \"address\": \"Salacak Mahallesi/Üsküdar\",\n" +
                                                    "  \"latitude\": 41.021111,\n" +
                                                    "  \"longitude\": 29.004722,\n" +
                                                    "  \"rate\": \"5\"\n" +
                                                    "}",
                                                    description = "Example Restaurant 5"
                                            )

                                    }
                            )
                    }
            )
    )
    public ResultData<RestaurantResponse> saveRestaurant(@RequestBody @Valid RestaurantSaveRequest request) {

        RestaurantResponse restaurantResponse = restaurantClient.saveRestaurant(request).getData();

        return ResultHelper.created(restaurantResponse);
    }

    @PutMapping("/update{id}")
    @Operation( description = "Updates the restaurant with the given id",
            summary = "Update Restaurant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant Save Request",
                    content ={
                            @Content(
                                    mediaType = "application/json",
                                    schema =@Schema(
                                            implementation = RestaurantSaveRequest.class
                                    ),
                                    examples={
                                            @ExampleObject(
                                                    name="Example Restaurant Save Request",
                                                    summary = "You have to send a request like this",
                                                    value = "{\n" +
                                                            "  \"name\": \"Restaurant Name\",\n" +
                                                            "  \"address\": \"Restaurant Address\",\n" +
                                                            "  \"latitude\": 41.0082,\n" +
                                                            "  \"longitude\": 28.9784,\n" +
                                                            "  \"rate\": \"5\"\n" +
                                                            "}",
                                                    description = "You have to send a request like this"
                                            )
                                    }
                            )
                    }
            )
    )
    public ResultData<RestaurantResponse> updateRestaurant(@PathVariable String id, @RequestBody @Valid RestaurantSaveRequest request) {

        RestaurantResponse restaurantResponse = restaurantClient.updateRestaurant(id, request).getData();

        return ResultHelper.updated(restaurantResponse);
    }

    @GetMapping("/get-by-id/{id}")
    @Operation(
            description = "Get the restaurant with the given id",
            summary = "Get Restaurant By Id"
    )
    public ResultData<RestaurantResponse> getById(@PathVariable("id") String id) {

        RestaurantResponse restaurantResponse = restaurantClient.getRestaurantById(id).getData();

        return ResultHelper.success(restaurantResponse);
    }

    @DeleteMapping("/delete-restaurant/{id}")
    @Operation(
            description = "Deletes the restaurant with the given id",
            summary = "Delete Restaurant By Id"
    )
    public Result deleteById(@PathVariable String id) {

        restaurantClient.deleteRestaurant(id);

        return ResultHelper.hardDeleted();
    }
}
