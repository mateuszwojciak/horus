package org.horus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {

        return blocks
                .stream()
                .flatMap(s -> getAllBlocks(s).stream())
                .filter(s -> s.getColor().equalsIgnoreCase(color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {

        return blocks
                .stream()
                .flatMap(s -> getAllBlocks(s).stream())
                .filter(s -> s.getMaterial().equalsIgnoreCase(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {

        return blocks
                .stream()
                .mapToInt(s -> getAllBlocks(s).size())
                .sum();
    }

    private List<Block> getAllBlocks(Block block) {
        List<Block> listWithAllBlocks = new ArrayList<>();
        listWithAllBlocks.add(block);

        if (block instanceof CompositeBlock) {
            List<Block> children = ((CompositeBlock) block)
                    .getBlocks()
                    .stream()
                    .flatMap(s -> getAllBlocks(s).stream())
                    .collect(Collectors.toList());

            listWithAllBlocks.addAll(children);
        }

        return listWithAllBlocks;
    }
}
