package com.example.ludoteca.game;
import com.example.ludoteca.common.criteria.SearchCriteria;
import com.example.ludoteca.game.model.Game;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class GameSpecification implements Specification<Game> {

    private static final long serialVersionUID = 1L;

    private final SearchCriteria criteria;

    public GameSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

//    @Override
//    public Specification<Game> and(Specification<Game> other) {
//        return Specification.super.and(other);
//    }

    @Override
    public Predicate toPredicate(Root<Game> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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

    private Path<String> getPath(Root<Game> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }
}
