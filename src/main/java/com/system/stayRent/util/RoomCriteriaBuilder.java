package com.system.stayRent.util;

import com.system.stayRent.constant.RoomField;
import com.system.stayRent.dto.RoomFilterDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Objects;

public class RoomCriteriaBuilder {
    public static Criteria build(RoomFilterDTO filter){
        Criteria criteria = new Criteria();

        if(Objects.nonNull(filter.getName())){
            criteria.and(RoomField.NAME.value()).is(filter.getName());
        }
        if(filter.getFloor() != null){
            criteria.and(RoomField.FLOOR.value()).is(filter.getFloor());
        }
        if(Objects.nonNull(filter.getPrice()) && Objects.nonNull(filter.getPriceOp())){
            switch (filter.getPriceOp()){
                case "lt" -> criteria.and(RoomField.PRICE.value()).lt(filter.getPrice());
                case "lte" -> criteria.and(RoomField.PRICE.value()).lte(filter.getPrice());
                case "gt" -> criteria.and(RoomField.PRICE.value()).gt(filter.getPrice());
                case "gte" -> criteria.and(RoomField.PRICE.value()).gte(filter.getPrice());
                case "ltOr" -> criteria.and(RoomField.PRICE.value()).is(filter.getPrice());
                case "eq" -> criteria.and(RoomField.PRICE.value()).is(filter.getPrice());
            }
        }else if(Objects.nonNull(filter.getMinPrice()) && Objects.nonNull(filter.getMaxPrice())){
            criteria.and(RoomField.PRICE.value()).gte(filter.getMinPrice()).lte(filter.getMaxPrice());
        }

//       Query query = new Query(criteria);
        return criteria;
    }
    public static Sort sort(RoomFilterDTO filter) {
        //sort direction
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(filter.getDirection())) {
            direction = Sort.Direction.DESC;
        }
        if ("asc".equalsIgnoreCase(filter.getDirection())) {
            direction = Sort.Direction.ASC;
        }
        //sort field
        String sortField = filter.getSortBy();
        if(!sortField.contains(".")){
            if(sortField.equals("name")){
                sortField = "attributes."+sortField;
            }
            if (sortField.equals("price")){
                sortField = "price."+sortField;
            }
        }
        return Sort.by(direction, sortField);
    }

}