package com.revature.java.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final Map<Integer, Map<String, String>> items = new HashMap<>();
    private int currentId = 1;

    @GetMapping
    public Collection<Map<String, String>> getAllItems() {
        return items.values();
    }

    @GetMapping("/{id}")
    public Map<String, String> getItemById(@PathVariable int id) {
        return items.get(id);
    }

    @PostMapping
    public Map<String, String> createItem(@RequestBody Map<String, String> item) {
        item.put("id", String.valueOf(currentId));
        items.put(currentId, item);
        currentId++;
        return item;
    }

    @PutMapping("/{id}")
    public Map<String, String> updateItem(@PathVariable int id, @RequestBody Map<String, String> item) {
        item.put("id", String.valueOf(id));
        items.put(id, item);
        return item;
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable int id) {
        items.remove(id);
        return "Item deleted with id: " + id;
    }
}