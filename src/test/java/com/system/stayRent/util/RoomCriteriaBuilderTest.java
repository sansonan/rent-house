//package com.system.stayRent.util;
//
//
//import com.system.stayRent.dto.RoomFilterDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.query.Criteria;
//
//import java.util.Objects;
//
//import static com.system.stayRent.constant.RoomConstants.*;
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class RoomCriteriaBuilderTest {
//
//    @Test
//    void shouldReturnEmptyCriteria_whenNoFilterProvided() {
//        //given
//        RoomFilterDTO filter = new RoomFilterDTO();
//
//        //when
//        Criteria criteria = RoomCriteriaBuilder.build(filter);
//
//        //then
//        assertThat(criteria.getCriteriaObject()).isEmpty();
//    }
//
//    @Test
//    void name(){
//        RoomFilterDTO filter = new RoomFilterDTO();
//        filter.setName("Room A");
//        Criteria criteria = RoomCriteriaBuilder.build(filter);
//        String json = criteria.getCriteriaObject().toJson();
////
//        assertThat(json)
//                .contains(FIELD_NAME, "Room A");
//
//    }
//
//    @Test
//    void floor(){
//        RoomFilterDTO filter = new RoomFilterDTO();
//        filter.setFloor(1);
//        Criteria criteria = RoomCriteriaBuilder.build(filter);
//        String json = criteria.getCriteriaObject().toJson();
//        assertThat(json)
//                .contains(FIELD_FLOOR, "1");
//
//    }
//
//    @Test
//    void shouldAddPriceCriteria_whenAddPriceLT(){
//        RoomFilterDTO filter = new RoomFilterDTO();
//        filter.setPrice(60d);
//        filter.setPriceOp("lt");
//
//        Criteria criteria = RoomCriteriaBuilder.build(filter);
//        String json = criteria.getCriteriaObject().toJson();
//        assertThat(json).contains(FIELD_PRICE).contains("$lt");
//
//    }
//
//    @Test
//    void shouldAddPriceCriteria_whenAddPriceGT(){
//        RoomFilterDTO filter = new RoomFilterDTO();
//        filter.setPrice(60d);
//        filter.setPriceOp("gt");
//        Criteria criteria = RoomCriteriaBuilder.build(filter);
//        String json = criteria.getCriteriaObject().toJson();
//        assertThat(json).contains(FIELD_PRICE).contains("60");
//    }
//    @Test
//    void shouldAddPriceCriteria_whenAddPriceEQ(){
//        RoomFilterDTO filter = new RoomFilterDTO();
//        filter.setPrice(60d);
//        filter.setPriceOp("eq");
//        Criteria criteria = RoomCriteriaBuilder.build(filter);
//        String json = criteria.getCriteriaObject().toJson();
//        assertThat(json).contains(FIELD_PRICE).contains("60");
//    }
//
////    @Test
//    void shouldAddMinPriceCriteria_whenAddMinPriceLT(){
//        RoomFilterDTO filter = new RoomFilterDTO();
//        filter.setMinPrice(60d);
//        filter.setMaxPrice(90d);
//        Criteria criteria = RoomCriteriaBuilder.build(filter);
//        String json = criteria.getCriteriaObject().toJson();
//        assertThat(json).contains("price").contains("$gte").contains("$lte");
//
//    }
//    //sort
////    @Test
//    void sort_withValidFieldASC(){
//        RoomFilterDTO filter = new RoomFilterDTO();
//        filter.setDirection("asc");
//        filter.setSortBy("price");
//        // when
//        Sort sort = RoomCriteriaBuilder.sort(filter);
//        //then
//        assertThat(sort.getOrderFor("attributes.price")).isNotNull();
//        assertThat(Objects.requireNonNull(sort.getOrderFor("attributes.price")).getDirection()).isEqualTo(Sort.Direction.ASC);
//    }
////    @Test
//    void sort_withDefaultValueASC(){
//        RoomFilterDTO filter = new RoomFilterDTO(); // no sortBy and direction
//        Sort sort = RoomCriteriaBuilder.sort(filter);
//        assertThat(sort.getOrderFor("name")).isNotNull();
//        assertThat(Objects.requireNonNull(sort.getOrderFor("name")).getDirection()).isEqualTo(Sort.Direction.ASC);
//
//    }
//}
