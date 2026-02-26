package com.zakdevops.showcase.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemsController {

  private static final List<Map<String, Object>> ITEMS = List.of(
      Map.of("id", 1, "name", "docker"),
      Map.of("id", 2, "name", "terraform"),
      Map.of("id", 3, "name", "github-actions")
  );

  @GetMapping
  public List<Map<String, Object>> list() {
    return ITEMS;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> get(@PathVariable int id) {
    Optional<Map<String, Object>> found = ITEMS.stream()
        .filter(i -> ((Integer) i.get("id")) == id)
        .findFirst();

    return found.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}