package com.keyin.bstapp.service;

import com.keyin.bstapp.model.BstNode;
import com.keyin.bstapp.model.dto.TreeResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BstService {

    private static final boolean IGNORE_DUPLICATES = true;

    public List<Integer> parseInput(String inputString) {
        if (inputString == null || inputString.isBlank()) return List.of();

        String[] tokens = inputString.split("[,\\s]+");
        List<Integer> numbers = new ArrayList<>();
        for (String t : tokens) {
            if (t.isBlank()) continue;
            try {
                numbers.add(Integer.parseInt(t.trim()));
            } catch (NumberFormatException ignored) {
            }
        }
        return numbers;
    }

    public BstNode buildSequential(List<Integer> numbers) {
        BstNode root = null;
        for (Integer n : numbers) {
            if (n == null) continue;
            root = insert(root, n);
        }
        return root;
    }

    public TreeResponse toResponse(BstNode root) {
        return new TreeResponse(copyToDto(root));
    }

    public BstNode toBalanced(BstNode root) {
        List<Integer> inOrder = new ArrayList<>();
        inOrderTraversal(root, inOrder);

        List<Integer> uniqueSorted = inOrder.stream().distinct().collect(Collectors.toList());
        return buildBalanced(uniqueSorted, 0, uniqueSorted.size() - 1);
    }

    private BstNode insert(BstNode node, int value) {
        if (node == null) return new BstNode(value);

        if (value < node.getValue()) {
            node.setLeft(insert(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insert(node.getRight(), value));
        } else {
            if (!IGNORE_DUPLICATES) {
                node.setRight(insert(node.getRight(), value));
            }
        }
        return node;
    }

    private void inOrderTraversal(BstNode node, List<Integer> out) {
        if (node == null) return;
        inOrderTraversal(node.getLeft(), out);
        out.add(node.getValue());
        inOrderTraversal(node.getRight(), out);
    }

    private BstNode buildBalanced(List<Integer> sorted, int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        BstNode root = new BstNode(sorted.get(mid));
        root.setLeft(buildBalanced(sorted, lo, mid - 1));
        root.setRight(buildBalanced(sorted, mid + 1, hi));
        return root;
    }

    private TreeResponse.TreeNode copyToDto(BstNode node) {
        if (node == null) return null;
        TreeResponse.TreeNode dto = new TreeResponse.TreeNode(node.getValue());
        dto.setLeft(copyToDto(node.getLeft()));
        dto.setRight(copyToDto(node.getRight()));
        return dto;
    }
}
