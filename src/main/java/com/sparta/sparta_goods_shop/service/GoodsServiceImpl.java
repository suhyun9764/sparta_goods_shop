package com.sparta.sparta_goods_shop.service;

import com.sparta.sparta_goods_shop.dto.GoodsRequestDto;
import com.sparta.sparta_goods_shop.dto.GoodsResponseDto;
import com.sparta.sparta_goods_shop.entity.goods.Goods;
import com.sparta.sparta_goods_shop.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sparta.sparta_goods_shop.constants.goods.Messages.INVALID_SORT_VALUE;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;

    @Override   // 상품 등록
    public GoodsResponseDto save(GoodsRequestDto requestDto) {
        Goods goods = goodsRepository.save(new Goods(requestDto));
        return new GoodsResponseDto(goods);
    }

    @Override   // 전체 상품 조회
    public List<GoodsResponseDto> findAll(String sortBy, boolean isAsc, int page, int size) {
        // 정렬 값 체크
        if(!isValidSortBy(sortBy)){
            throw new IllegalArgumentException(INVALID_SORT_VALUE);
        }
        // 페이징 설정
        Pageable pageable = getPageable(sortBy, isAsc, page, size);

        return goodsRepository.findAll(pageable).stream().map(GoodsResponseDto::new).toList();
    }

    private boolean isValidSortBy(String sortBy) {
        return "name".equalsIgnoreCase(sortBy) || "price".equalsIgnoreCase(sortBy);
    }

    private static Pageable getPageable(String sortBy, boolean isAsc, int page, int size) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size,sort);
        return pageable;
    }

}
