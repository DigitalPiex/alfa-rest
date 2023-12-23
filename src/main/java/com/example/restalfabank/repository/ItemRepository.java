package com.example.restalfabank.repository;

import com.example.restalfabank.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    String FIND_BY_BOX_ID_SQL = """
            WITH RECURSIVE r AS (SELECT item.id, item.color, item.contained_in FROM box RIGHT JOIN item ON box.id = item.contained_in  WHERE box.id = ?
            UNION
            SELECT item.id, item.color, item.contained_in FROM box RIGHT JOIN item ON box.id = item.contained_in JOIN r ON box.contained_in = r.id)
            SELECT * FROM r
            """;

    String FIND_BY_BOX_ID_AND_COLOR_SQL = """
            WITH RECURSIVE r AS (SELECT item.id, item.color, item.contained_in FROM box RIGHT JOIN item ON box.id = item.contained_in WHERE box.id = ?
            UNION
            SELECT item.id, item.color, item.contained_in FROM box RIGHT JOIN item ON box.id = item.contained_in JOIN r ON box.contained_in = r.id)
            SELECT * FROM r WHERE color = ?
            """;

    List<Item> findByColor(String color);

    @Query(value = FIND_BY_BOX_ID_SQL, nativeQuery = true)
    List<Item> findByBoxId(Integer id);

    @Query(value = FIND_BY_BOX_ID_AND_COLOR_SQL, nativeQuery = true)
    List<Item> findByBoxIdAndColor(Integer id, String color);

}
