package pl.dmcs.rkotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.rkotas.dao.BlockRepository;
import pl.dmcs.rkotas.domain.Block;

import java.util.List;

@Transactional
@Service
public class BlockServiceImpl implements BlockService {

    BlockRepository blockRepository;

    @Autowired
    public BlockServiceImpl(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }


    @Override
    public Block save(Block block) {
        return blockRepository.save(block);
    }

    @Override
    public List<Block> getAllBlock() {
        return blockRepository.findAll();
    }
}
