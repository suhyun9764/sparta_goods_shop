package com.sparta.sparta_goods_shop.service.goods;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sparta.sparta_goods_shop.dto.goods.GoodsRequestDto;
import com.sparta.sparta_goods_shop.dto.goods.GoodsResponseDto;
import com.sparta.sparta_goods_shop.entity.goods.Goods;
import com.sparta.sparta_goods_shop.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.sparta.sparta_goods_shop.constants.goods.Messages.INVALID_SORT_VALUE;
import static com.sparta.sparta_goods_shop.constants.goods.Messages.NOT_FOUND_GOODS;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override   // 상품 등록
    public GoodsResponseDto save(GoodsRequestDto requestDto) {
        Goods saveGoods = goodsRepository.save(new Goods(requestDto));
        return new GoodsResponseDto(saveGoods);
    }

    @Override   // 전체 상품 조회
    public List<GoodsResponseDto> findAll(String sortBy, boolean isAsc, int page, int size) {
        // 정렬 값 체크
        if (!isValidSortBy(sortBy)) {
            throw new IllegalArgumentException(INVALID_SORT_VALUE);
        }
        // 페이징 설정
        Pageable pageable = getPageable(sortBy, isAsc, page, size);

        return goodsRepository.findAll(pageable).stream().map(GoodsResponseDto::new).toList();
    }

    @Override   //선택한 상품 조회
    public GoodsResponseDto findById(Long goodsId) {
        Goods goods = getGoods(goodsId);

        return new GoodsResponseDto(goods);
    }

    @Override
    @Transactional
    public GoodsResponseDto uploadImage(Long goodsId, MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String fileUrl = "https://" + bucket + "/test" + fileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
            Goods goods = getGoods(goodsId);
            goods.uploadImage(fileUrl);
            return new GoodsResponseDto(goods);
        } catch (IOException e) {
            throw new IllegalArgumentException("잘못된 이미지입니다");
        }
    }

    private boolean isValidSortBy(String sortBy) {
        return "name".equalsIgnoreCase(sortBy) || "price".equalsIgnoreCase(sortBy);
    }

    private static Pageable getPageable(String sortBy, boolean isAsc, int page, int size) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return pageable;
    }

    private Goods getGoods(Long goodsId) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(() ->
                new NullPointerException(NOT_FOUND_GOODS));
        return goods;
    }

}
