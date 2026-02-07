package com.system.stayRent.util;

import com.system.stayRent.dto.RoomFilterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.system.stayRent.constant.RoomConstants.*;

@Slf4j
public class RoomCriteriaBuilder {
    public static Criteria build(RoomFilterDTO filter){
        List<Criteria> criterias = new ArrayList<>();
        if(Objects.nonNull(filter.getName())){
            criterias.add(Criteria.where(FIELD_NAME).is(filter.getName()));
        }
        if(Objects.nonNull(filter.getFloor())){
            criterias.add(Criteria.where(FIELD_FLOOR).is(filter.getFloor()));
        }
        if(Objects.nonNull(filter.getPrice()) && Objects.nonNull(filter.getPriceOp())){
            switch (filter.getPriceOp()){
                case OP_LT -> criterias.add(Criteria.where(FIELD_PRICE).lt(filter.getPrice()));
                case OP_LTE -> criterias.add(Criteria.where(FIELD_PRICE).lte(filter.getPrice()));
                case OP_GT -> criterias.add(Criteria.where(FIELD_PRICE).gt(filter.getPrice()));
                case OP_GTE -> criterias.add(Criteria.where(FIELD_PRICE).gte(filter.getPrice()));
                case OP_EQ -> criterias.add(Criteria.where(FIELD_PRICE).is(filter.getPrice()));
                default -> throw new IllegalArgumentException(
                        "Invalid priceOp: " + filter.getPriceOp()
                );

            }
        }else if(Objects.nonNull(filter.getMinPrice()) && Objects.nonNull(filter.getMaxPrice())){
            criterias.add(Criteria.where(FIELD_PRICE).gte(filter.getMinPrice()).lte(filter.getMaxPrice()));
        }

//       Query query = new Query(criteria);
        return criterias.isEmpty() ? new Criteria() : new Criteria().andOperator(criterias.toArray(new Criteria[0]));
    }
    public static Sort sort(RoomFilterDTO filter) {
        //sort direction
        Sort.Direction direction = "desc".equalsIgnoreCase(filter.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        //sort field
        String sortField = Objects.nonNull(filter.getSortBy()) ? filter.getSortBy() : FIELD_NAME;
        if(!ALLOWED_SORT_FIELDS.contains(sortField)){
            throw new IllegalArgumentException("invalid sort field : " + sortField);
        }
        if (!sortField.equals(FIELD_NAME)) {
            sortField = ATT + sortField;
        }

        return Sort.by(direction, sortField);
    }

}