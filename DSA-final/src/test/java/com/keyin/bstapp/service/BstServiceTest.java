package com.keyin.bstapp.service;

import com.keyin.bstapp.model.BstNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BstServiceTest {

    private final BstService service = new BstService();

    @Test
    void buildsExpectedShapeFromSampleNumbers() {

        List<Integer> nums = List.of(8,3,10,1,6,14,4,7,13);
        BstNode root = service.buildSequential(nums);

        assertNotNull(root);
        assertEquals(8, root.getValue());
        assertEquals(3, root.getLeft().getValue());
        assertEquals(10, root.getRight().getValue());
        assertEquals(1, root.getLeft().getLeft().getValue());
        assertEquals(6, root.getLeft().getRight().getValue());
        assertEquals(14, root.getRight().getRight().getValue());
        assertEquals(4, root.getLeft().getRight().getLeft().getValue());
        assertEquals(7, root.getLeft().getRight().getRight().getValue());
        assertEquals(13, root.getRight().getRight().getLeft().getValue());
    }

    @Test
    void duplicateNumbersAreIgnoredByDefault() {

        List<Integer> nums = List.of(5,3,7,3,7,5);
        BstNode root = service.buildSequential(nums);

        assertEquals(5, root.getValue());
        assertEquals(3, root.getLeft().getValue());
        assertEquals(7, root.getRight().getValue());


        assertNull(root.getLeft().getLeft());
        assertNull(root.getLeft().getRight());
        assertNull(root.getRight().getLeft());
        assertNull(root.getRight().getRight());
    }

    @Test
    void balancedTreeHasSmallerOrEqualHeightOnSkewedInput() {

        List<Integer> nums = List.of(1,2,3,4,5,6,7,8,9);
        BstNode unbalanced = service.buildSequential(nums);
        BstNode balanced = service.toBalanced(unbalanced);

        int hUnbalanced = height(unbalanced);
        int hBalanced = height(balanced);

        assertTrue(hBalanced <= hUnbalanced, "balanced height should be <= unbalanced height");

        assertTrue(hBalanced < hUnbalanced, "balanced height should be smaller for skewed input");
    }

    private int height(BstNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
}
