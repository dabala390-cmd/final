package com.northwollo.tourism.service.impl;

import com.northwollo.tourism.entity.TourismPlace;
import com.northwollo.tourism.enums.PlaceStatus;
import com.northwollo.tourism.exception.PublicSearchException;
import com.northwollo.tourism.publicsearch.dto.PublicTourismSearchRequestDto;
import com.northwollo.tourism.publicsearch.dto.PublicTourismSearchResponseDto;
import com.northwollo.tourism.repository.PublicTourismSearchRepository;
import com.northwollo.tourism.service.PublicTourismSearchService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublicTourismSearchServiceImpl implements PublicTourismSearchService {

    private final PublicTourismSearchRepository repository;

    @Override
    public List<PublicTourismSearchResponseDto> search(
            PublicTourismSearchRequestDto request
    ) {
        try {
            Specification<TourismPlace> spec = (root, query, cb) -> {

                List<Predicate> predicates = new ArrayList<>();

                // ✅ ENFORCE: ACTIVE only
                predicates.add(cb.equal(root.get("status"), PlaceStatus.ACTIVE));

                // ✅ Categories filter
                if (request.getCategories() != null && !request.getCategories().isEmpty()) {
                    predicates.add(root.get("category").in(request.getCategories()));
                }

                // ✅ Keyword filter (name OR description)
                if (request.getKeyword() != null && !request.getKeyword().isBlank()) {
                    String keyword = "%" + request.getKeyword().toLowerCase() + "%";
                    predicates.add(
                            cb.or(
                                    cb.like(cb.lower(root.get("name")), keyword),
                                    cb.like(cb.lower(root.get("description")), keyword)
                            )
                    );
                }

                // ✅ Kebele filter
                if (request.getKebele() != null && !request.getKebele().isBlank()) {
                    predicates.add(
                            cb.equal(
                                    cb.lower(root.get("kebele")),
                                    request.getKebele().toLowerCase()
                            )
                    );
                }

                // ✅ Wereda filter
                if (request.getWereda() != null && !request.getWereda().isBlank()) {
                    predicates.add(
                            cb.equal(
                                    cb.lower(root.get("wereda")),
                                    request.getWereda().toLowerCase()
                            )
                    );
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            };

            return repository.findAll(spec)
                    .stream()
                    .map(this::mapToResponse)
                    .toList();

        } catch (Exception ex) {
            throw new PublicSearchException(
                    "Failed to search public tourism places", ex
            );
        }
    }

    /**
     * ✅ SAFE mapping
     * - No joins
     * - No missing columns
     * - No NullPointerException
     */
    private PublicTourismSearchResponseDto mapToResponse(TourismPlace place) {

        return new PublicTourismSearchResponseDto(
                place.getName(),
                place.getImages().isEmpty()
                        ? null
                        : place.getImages().get(0).getImageUrl(),
                place.getViewersCount()   // ✅ SAFE: always 0 or more
        );
    }

}
