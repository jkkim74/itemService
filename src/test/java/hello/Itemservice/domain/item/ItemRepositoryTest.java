package hello.Itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("ItemA",10000,10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item item = new Item("ItemA",10000,10);
        Item item2 = new Item("ItemB",20000,10);
        Item item3 = new Item("ItemC",30000,10);

        itemRepository.save(item);
        itemRepository.save(item2);
        itemRepository.save(item3);

        // when
        List<Item> findItemList =itemRepository.findAll();

        // then
        assertThat(findItemList.size()).isEqualTo(3);
        assertThat(findItemList).contains(item,item2,item3);
    }

    @Test
    void update() {
        // given
        Item item = new Item("ItemA", 10000,10);
        Item savedItem = itemRepository.save(item);

        // when
        Item updateItem = savedItem;//new Item("ItemB", 10000,10);
        updateItem.setItemName("ItemB");
        itemRepository.update(savedItem.getId(),updateItem);

        // then
        Item result = itemRepository.findById(savedItem.getId());
        assertThat(result.getItemName()).isEqualTo("ItemB");
    }

    @Test
    void clearStore() {
    }
}