package com.example.ludoteca.rent;

import com.example.ludoteca.common.criteria.SearchCriteria;
import com.example.ludoteca.rent.model.Rent;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class RentSpecification implements Specification<Rent> {
    private static final long serialVersionUID = 1L;
    private final SearchCriteria criteria;

    public RentSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Specification<Rent> and(Specification<Rent> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Rent> or(Specification<Rent> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Rent> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null) {
            Path<String> path = getPath(root);
            if (path.getJavaType() == String.class) {
                return builder.like(path, "%" + criteria.getValue() + "%"); //para Strings
            } else {
                return builder.equal(path, criteria.getValue());// para números o fechas
            }
        }

        return null;
    }

    private Path<String> getPath(Root<Rent> root) {

        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }
}
