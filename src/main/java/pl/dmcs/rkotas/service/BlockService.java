package pl.dmcs.rkotas.service;

import pl.dmcs.rkotas.domain.Block;

import java.util.List;
public interface BlockService {

    Block save(Block block);

    List<Block> getAllBlock();
}
