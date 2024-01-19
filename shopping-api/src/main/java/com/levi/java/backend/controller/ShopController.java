package com.levi.java.backend.controller;

import com.levi.java.backend.domain.Shop;
import com.levi.java.backend.mapper.requests.ShopPostRequest;
import com.levi.java.backend.mapper.responses.ShopReportResponse;
import com.levi.java.backend.mapper.responses.ShopResponse;
import com.levi.java.backend.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/all")
    public ResponseEntity<List<ShopResponse>> getAllNonPageable() {
        return ResponseEntity.ok(shopService.getAllNonPageable());
    }

    @GetMapping("/shopByUser/{userIdentifier}")
    public ResponseEntity<List<ShopResponse>> getUserShops(@PathVariable String userIdentifier) {
        return ResponseEntity.ok(shopService.getByUser(userIdentifier));
    }

    @GetMapping("/shopByDate")
    public ResponseEntity<List<ShopResponse>> getShops(@RequestBody ShopPostRequest shopPostRequest) {
        return ResponseEntity.ok(shopService.getByDate(shopPostRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> findById(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping()
    public ResponseEntity<Shop> newShop(@Valid @RequestBody ShopPostRequest shopPostRequest) {
        return new ResponseEntity<>(shopService.save(shopPostRequest), HttpStatus.CREATED);
    }

    //TODO: fix requests
    @GetMapping("/search/{startDate}/{endDate}/{minimumValue}")
    public ResponseEntity<List<ShopResponse>> getShopsByFilter(
            @PathVariable @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
            @PathVariable @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate,
            @PathVariable Float minimumValue) {

        return ResponseEntity.ok(shopService.getShopByFilters(startDate, endDate, minimumValue));

    }

    @GetMapping("/report")
    public ResponseEntity<ShopReportResponse> getReportDate(
            @RequestParam(name = "startDate", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
            @RequestParam(name = "endDate", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate) {

        return ResponseEntity.ok(shopService.getReportByDate(startDate, endDate));
    }
}
