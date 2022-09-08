package org.horus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WallTestSuite {
    private Structure createStructure() {
        CompositeBlock compositeBlock = mock(CompositeBlock.class);
        when(compositeBlock.getColor()).thenReturn("white");
        when(compositeBlock.getMaterial()).thenReturn("brick");

        Block testBlockOne = mock(Block.class);
        when(testBlockOne.getColor()).thenReturn("white");
        when(testBlockOne.getMaterial()).thenReturn("brick");

        Block testBlockTwo = mock(Block.class);
        when(testBlockTwo.getColor()).thenReturn("black");
        when(testBlockTwo.getMaterial()).thenReturn("brick");

        when(compositeBlock.getBlocks()).thenReturn(Arrays.asList(testBlockOne, testBlockTwo));

        List<Block> blocks = Arrays.asList(compositeBlock);

        return new Wall(blocks);
    }

    @Test
    void shouldCountNestedBlocks() {
        //Given
        Structure wall = createStructure();

        //When & Then
        int count = wall.count();
        assertEquals(3, count);
    }

    @Test
    void shouldNotFindBlockByColor() {
        //Given
        Structure wall = createStructure();

        //When & Then
        Optional<Block> findBlueBlock = wall.findBlockByColor("blue");
        assertTrue(findBlueBlock.isEmpty());
    }

    @Test
    void shouldFindBlockByColor() {
        //Given
        Structure wall = createStructure();

        //When & Then
        Optional<Block> findWhiteBlock = wall.findBlockByColor("white");
        assertTrue(findWhiteBlock.isPresent());
    }

    @Test
    void shouldNotFindBlocksByMaterial() {
        //Given
        Structure wall = createStructure();

        //When & Then
        List<Block> findWoolBlocks = wall.findBlocksByMaterial("wool");
        assertTrue(findWoolBlocks.isEmpty());
    }

    @Test
    void shouldFindBlocksByMaterial() {
        //Given
        Structure wall = createStructure();

        //When & Then
        List<Block> findBrickBlocks = wall.findBlocksByMaterial("brick");
        assertEquals(3, findBrickBlocks.size());
    }
}
