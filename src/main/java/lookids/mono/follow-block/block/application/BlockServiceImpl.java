package Lookids.block.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import Lookids.block.domain.Block;
import Lookids.block.dto.in.BlockRequestDto;
import Lookids.block.dto.in.KafkaBlockRequestDto;
import Lookids.block.dto.out.BlockResponseDto;
import Lookids.block.dto.out.KafkaBlockResponseDto;
import Lookids.block.infrastructure.BlockRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {

	private final KafkaTemplate<String, KafkaBlockResponseDto> kafkaTemplate;
	private final BlockRepository blockRepository;

	@Override
	public void updateBlock(BlockRequestDto blockRequestDto) {
		Block block = blockRepository.findByUuidAndBlockedUuid(
			blockRequestDto.getUuid(), blockRequestDto.getBlockedUuid()).orElse(null);

		if (block != null) {
			blockRepository.save(BlockRequestDto.toUpdateEntity(block));
		} else {
			blockRepository.save(blockRequestDto.toEntity());
		}
	}

	@Override
	public Page<BlockResponseDto> readBlock(String userUuid, Pageable pageable) {
		Page<BlockResponseDto> blockedList = blockRepository.findByUuidAndStateTrue(userUuid, pageable);
		return blockedList;
	}

	@KafkaListener(topics = "block-request", groupId = "block-create", containerFactory = "BlockContainerFactory")
	public void blockListRequest(KafkaBlockRequestDto kafkaBlockRequestDto) {

		List<String> blockList = blockRepository.findByUuidAndStateTrue(kafkaBlockRequestDto.getUuid()).stream()
			.map(Block::getBlockedUuid).toList();

		KafkaBlockResponseDto kafkaBlockResponseDto = KafkaBlockResponseDto.builder()
			.uuid(kafkaBlockRequestDto.getUuid())
			.blockUuid(blockList)
			.build();

		kafkaTemplate.send("block-response", kafkaBlockResponseDto);

	}

}