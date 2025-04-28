package com.example.jpa.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Item;
import com.example.jpa.entity.QItem;
import com.example.jpa.entity.Item.ItemSellStatus;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void queryDslTest() {
        QItem item = QItem.item;
        itemRepository.findAll(item.itemNm.eq("티셔츠1"));

        itemRepository.findAll(item.itemNm.eq("티셔츠1"));

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(item.itemNm.eq("item2"));
        builder.and(item.price.gt(1000));

    }

    @Test
    public void insertTest() {
        IntStream.rangeClosed(0, 50).forEach(i -> {
            Item item = Item.builder().itemNm("itemNm" + i).itemDetail("detail" + i).price(i)
                    .itemSellStatus(ItemSellStatus.SELL).stockQueantity(i + 10).build();
            itemRepository.save(item);
        });
    }

    @Test
    public void bool() {
    }
}
